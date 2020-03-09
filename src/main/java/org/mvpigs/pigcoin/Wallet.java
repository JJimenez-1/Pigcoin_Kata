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
