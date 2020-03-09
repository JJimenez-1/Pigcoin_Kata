package org.mvpigs.pigcoin;

import java.security.PublicKey;

public class Transaction {

    private String hash = null;
    private String prev_hash = null;
    private PublicKey pKey_sender = null;
    private PublicKey pKey_recipient = null;
    private double pigcoins = 0;
    private String message = null;
    private byte[] signature = new byte[] {};

    public Transaction () {}
    public Transaction(String hash, String prev_hash, PublicKey sender,
                       PublicKey recipient, double pigcoins, String message) {
        this.hash = hash;
        this.prev_hash = prev_hash;
        this.pKey_sender = sender;
        this.pKey_recipient = recipient;
        this.pigcoins = pigcoins;
        this.message = message;
    }

    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPrev_hash() {
        return prev_hash;
    }
    public void setPrev_hash(String prev_hash) {
        this.prev_hash = prev_hash;
    }

    public PublicKey getpKey_sender() {
        return pKey_sender;
    }
    public void setpKey_sender(PublicKey pKey_sender) {
        this.pKey_sender = pKey_sender;
    }

    public PublicKey getpKey_recipient() {
        return pKey_recipient;
    }
    public void setpKey_recipient(PublicKey pKey_recipient) {
        this.pKey_recipient = pKey_recipient;
    }


    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public double getPigcoins() {
        return pigcoins;
    }
    public void setPigcoins(double pigcoins) {
        this.pigcoins = pigcoins;
    }

    public byte[] getSignature() {return signature;}
    @Override
    public String toString() {
        return '\n' + "hash = " + getHash() + '\n' + "prev_hash = " +
                getPrev_hash() + '\n' + "pKey_sender = " + getpKey_sender().hashCode() +
                '\n' + "pKey_recipient = " + getpKey_recipient().hashCode() + '\n' +
                "pigcoins = " + getPigcoins() + '\n' + "message = " + getMessage()
                + '\n';
    }
}
