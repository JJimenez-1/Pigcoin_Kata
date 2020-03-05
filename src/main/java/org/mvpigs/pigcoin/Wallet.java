package org.mvpigs.pigcoin;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Wallet {

    private PublicKey address = null;
    private PrivateKey sKey = null;
    private double balance = 0d;
    private double total_input = 0d;
    private double total_output = 0d;
    private List<Transaction> inputTransactions = new ArrayList<Transaction>();
    private List<Transaction> outputTransactions = new ArrayList<Transaction>();

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
    List<Transaction> getInputTransactions() {
        return inputTransactions;
    }
    List<Transaction> getOutputTransactions() {
        return outputTransactions;
    }

    public void loadCoins(BlockChain bChain) {
        Map<String, Double> pigcoins = bChain.loadWallet(getAddress());
        double balanceout = total_output + pigcoins.get("output");
        double balancein = total_input + pigcoins.get("input");
        double totalBalance = balance + balancein - balanceout;
        setBalance(totalBalance);
    }

    public void loadInputTransactions(BlockChain bChain) {
        for (Transaction transaccion : bChain.getBlockChain()) {
            if (transaccion.getpKey_recipient() == getAddress()) {
                inputTransactions.add(transaccion);
            }
        }
    }
    public void loadOutputTransactions(BlockChain bChain) {
        for (Transaction transaccion : bChain.getBlockChain()) {
            if (transaccion.getpKey_recipient() == getAddress()) {
                outputTransactions.add(transaccion);
            }
        }
    }

    @Override
    public String toString(){
        return '\n' + "Wallet = " + getAddress().hashCode() + '\n' + "Total input = "
                + total_input + '\n' + "Total output = "
                + total_output + '\n' + "Balance = " + balance + '\n';
    }
}
