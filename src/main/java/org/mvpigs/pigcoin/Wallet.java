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

    /**
    public void generateKeyPair() {
        KeyPair pair = generateKeyPair();
        this.setAddress(pair.getPublic());
        this.
    }
     */
    public void setAddress(PublicKey pKey) {
        this.address = pKey;
    }
    public PublicKey getAddress() {
        return address;
    }
    public void setSkey(PrivateKey sKey) {
        this.sKey = sKey;
    }
    public PrivateKey getSKey() {
        return sKey;
    }
}
