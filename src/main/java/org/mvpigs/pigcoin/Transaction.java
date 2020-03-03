package org.mvpigs.pigcoin;

import java.security.PublicKey;

public class Transaction {

    private String hash = null;
    private String prev_hash = null;
    private PublicKey pKey_sender = null;
    private PublicKey pKey_recipient = null;
    private double pigcoins = 0d;
    private String message = null;
    private byte[] signature = new byte[] {};


    public Transaction(String hash, String prev_hash, PublicKey sender,
                       PublicKey recipient, double pigcoins, String message) {
        this.hash = hash;
        this.prev_hash = prev_hash;
        this.pKey_sender = sender;
        this.pKey_recipient = recipient;
        this.pigcoins = pigcoins;
        this.message = message;
    }

    @Override
    public String toString() {

    }
}
