package org.mvpigs.pigcoin;

import org.mvpigs.pigcoin.GenSig;
import org.junit.Before;
import org.junit.Test;

import java.security.KeyPair;

import static org.junit.Assert.*;

public class TransactionTest {

    @Test
    public void firstTransactionTest() {
        Wallet wallet1 = new Wallet();
        KeyPair pair1 = GenSig.generateKeyPair();
        wallet1.setAddress(pair1.getPublic());
        Wallet wallet2 = new Wallet();
        KeyPair pair2 = GenSig.generateKeyPair();
        wallet2.setAddress(pair2.getPublic());
        Transaction transaction = new Transaction();
        transaction = new Transaction("hash_30", "29", wallet1.getAddress(), wallet2.getAddress(), 40, "Mimi!");
        assertEquals("hash_30", transaction.getHash());
        assertEquals("29", transaction.getPrev_hash());
        assertEquals(pair1.getPublic(), wallet1.getAddress());
        assertEquals(pair2.getPublic(), wallet2.getAddress());
        assertEquals(40, transaction.getPigcoins(),0);
        assertEquals("Mimi!", transaction.getMessage());
    }

    @Test
    public void secondTransactionTest() {
        Wallet wallet1 = new Wallet();
        KeyPair pair1 = GenSig.generateKeyPair();
        wallet1.setAddress(pair1.getPublic());
        Wallet wallet2 = new Wallet();
        KeyPair pair2 = GenSig.generateKeyPair();
        wallet2.setAddress(pair2.getPublic());
        Transaction transaction = new Transaction();
        transaction = new Transaction("hash_49", "48", wallet1.getAddress(), wallet2.getAddress(), 10, "Devuelvememelo");
        assertEquals("hash_49", transaction.getHash());
        assertEquals("48", transaction.getPrev_hash());
        assertEquals(pair1.getPublic(), wallet1.getAddress());
        assertEquals(pair2.getPublic(), wallet2.getAddress());
        assertEquals(10, transaction.getPigcoins(),0);
        assertEquals("Devuelvememelo", transaction.getMessage());
    }
}
