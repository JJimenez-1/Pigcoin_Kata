package org.mvpigs.pigcoin;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {

    private PublicKey address = null;
    private PrivateKey sKey = null;
    private double balance = 0d;
    private double total_input = 0d;
    private double total_output = 0d;

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
    public PrivateKey getSKey() {
        return sKey;
    }

    @Override
    public String toString(){
        return '\n' + "Wallet = " + getAddress().hashCode() + '\n' + "Total input = "
                + total_input + '\n' + "Total output = "
                + total_output + '\n' + "Balance = " + balance + '\n';
    }
}
