package org.mvpigs.pigcoin;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

public class Wallet {

    private PublicKey address = null;
    private PrivateKey sKey = null;
    private double balance = 0d;
    private double total_input = 0d;
    private double total_output = 0d;
    private List<Transaction> inputTransactions = null;
    private List<Transaction> outputTransactions = null;

    public Wallet() {}

    public void generateKeyPair() {
        KeyPair pair = org.mvpigs.pigcoin.GenSig.generateKeyPair();
        this.setAddress(pair.getPublic());
        this.setSK(pair.getPrivate());
    }
    public void setAddress(PublicKey pKey) {
        this.address = pKey;
    }
    public PublicKey getAddress() {
        return address;
    }
    public void setSK(PrivateKey sKey) {
        this.sKey = sKey;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }

    public PrivateKey getSKey() {
        return sKey;
    }

    public void setTotal_input(double total_input){
        this.total_input = total_input;
    }
    public double getTotalInput() {
        return this.total_input;
    }

    public void setTotal_output(double total_output) {
        this.total_output = total_output;
    }
    public double getTotalOutput() {
        return this.total_output;
    }

    public void setInputTransactions(List<Transaction> inputTransactions) {
        this.inputTransactions = inputTransactions;
    }
    List<Transaction> getInputTransactions() {
        return this.inputTransactions;
    }

    public void setOutputTransactions(List<Transaction> outputTransactions) {
        this.outputTransactions = outputTransactions;
    }
    List<Transaction> getOutputTransactions() {
        return this.outputTransactions;
    }

    public void updateBalance() {
        this.balance = this.getTotalInput() - this.getTotalOutput();
    }

    public void loadCoins(BlockChain bChain) {
       double[] pigcoins = {0d, 0d};
       pigcoins = bChain.loadWallet(getAddress());
       setTotal_input(pigcoins[0]);
       setTotal_output(pigcoins[1]);
       updateBalance();
    }

    public void loadInputTransactions(BlockChain bChain) {
        setInputTransactions(bChain.loadInputTransactions(getAddress()));
    }
    public void loadOutputTransactions(BlockChain bChain) {
        setOutputTransactions(bChain.loadOutputTransaction(getAddress()));
    }
    public Map<String, Double> collectCoins(double pigcoins) {

        Map<String, Double> collectedCoins = new LinkedHashMap<>();

        if (getInputTransactions() == null) {
            return null;
        }

        if (pigcoins > getBalance()) {
            return null;
        }

        Double achievedCoins = 0d;

        Set<String> consumedCoins = new HashSet<String>();
        if (getOutputTransactions() != null) {
            for (Transaction transaction : getOutputTransactions()) {
                consumedCoins.add(transaction.getPrev_hash());
            }
        }

        for (Transaction transaction : getInputTransactions()) {

            if (consumedCoins.contains(transaction.getHash())) {
                continue;
            }

            if (transaction.getPigcoins() == pigcoins) {
                collectedCoins.put(transaction.getHash(), transaction.getPigcoins());
                consumedCoins.add(transaction.getHash());
                break;
            } else if (transaction.getPigcoins() > pigcoins) {
                collectedCoins.put(transaction.getHash(), pigcoins);
                collectedCoins.put("CA_" + transaction.getHash(), transaction.getPigcoins() - pigcoins);
                consumedCoins.add(transaction.getHash());
                break;
            } else {
                collectedCoins.put(transaction.getHash(), transaction.getPigcoins());
                achievedCoins = transaction.getPigcoins();
                pigcoins = pigcoins - achievedCoins;
                consumedCoins.add(transaction.getHash());
            }

        }
        // getInputTransactions().removeAll(consumedCoins);
        return collectedCoins;
    }

    public byte[] signTransaction(String message) {
        return GenSig.sign(getSKey(), message);
    }

    public void sendCoins(PublicKey pkey_Recipient, Double coins, String message, BlockChain bChain) {
        Map<String, Double> consumedCoins = new LinkedHashMap<>();
        consumedCoins = collectCoins(coins);
        if(consumedCoins != null) {
            bChain.processTransactions(getAddress(), pkey_Recipient, consumedCoins, message, signTransaction(message));
        }
        this.loadCoins(bChain);
    }

    @Override
    public String toString(){
        return '\n' + "Wallet = " + getAddress().hashCode() + '\n' + "Total input = "
                + getTotalInput() + '\n' + "Total output = "
                + getTotalOutput() + '\n' + "Balance = " + getBalance() + '\n';
    }
}
