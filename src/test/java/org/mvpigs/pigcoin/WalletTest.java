/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.mvpigs.pigcoin;

import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.junit.Test;

import java.security.KeyPair;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class WalletTest {

    @Test
    public void setygetAddressTest() {
        Wallet wallet = new Wallet();
        KeyPair pair = GenSig.generateKeyPair();
        wallet.setAddress(pair.getPublic());
        assertEquals(pair.getPublic(), wallet.getAddress());
    }

    @Test
    public void setygetSKeyTest() {
        Wallet wallet = new Wallet();
        KeyPair pair = GenSig.generateKeyPair();
        wallet.setSK(pair.getPrivate());
        assertEquals(pair.getPrivate(), wallet.getSKey());
    }

    @Test
    public void loadCoinsTest() {
        BlockChain blockchain = new BlockChain();
        KeyPair pair = GenSig.generateKeyPair();
        Wallet wallet = new Wallet();
        wallet.setAddress(pair.getPublic());
        Wallet wallet2 = new Wallet();
        wallet2.setAddress(pair.getPublic());
        Transaction transaction = new Transaction("hash_49", "48", wallet.getAddress(), wallet2.getAddress(), 10, "Devuelvememelo");
        Map<String, Double> pigcoins = blockchain.loadWallet(wallet.getAddress());
        wallet.loadCoins(blockchain);
        assertEquals(10, wallet2.getBalance(), 0.001);
    }
    /**
    @Test
    public void loadInputTransactionTest() {
        BlockChain blockchain = new BlockChain();
        KeyPair pair = GenSig.generateKeyPair();
        Wallet wallet = new Wallet();
        wallet.setAddress(pair.getPublic());
        Wallet wallet2 = new Wallet();
        wallet2.setAddress(pair.getPublic());
        Transaction transaction = new Transaction("hash_49", "48", wallet.getAddress(), wallet2.getAddress(), 10, "Devuelvememelo")
        wallet.loadInputTransactions(blockchain);
    }*/

    /**
    @Test
    public void generateKeyPairTest() {
        Wallet wallet = new Wallet();
        wallet.generateKeyPair();
        wallet.setAddress(wallet.getAddress());
        wallet.setSK(wallet.getSKey());
    }*/
}
