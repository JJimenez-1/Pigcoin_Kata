package org.mvpigs.pigcoin;

import org.junit.Test;
import java.security.KeyPair;
import org.junit.Assert.*;
public class BlockChainTest {

    @Test
    public void summarizeAllChain() {
        BlockChain block = new BlockChain();
        Wallet wallet1 = new Wallet();
        KeyPair pair1 = GenSig.generateKeyPair();
        wallet1.setAddress(pair1.getPublic());
        Wallet wallet2 = new Wallet();
        KeyPair pair2 = GenSig.generateKeyPair();
        wallet2.setAddress(pair2.getPublic());
        Transaction transaction = new Transaction();
        transaction = new Transaction("hash_3", "hash_1", wallet1.getAddress(),
                wallet2.getAddress(), 20, "a flying pig!");
        block.addOrigin(transaction);
    }

    @Test
    public void loadWalletTest() {
        BlockChain block = new BlockChain();
        Wallet wallet1 = new Wallet();
        KeyPair pair1 = GenSig.generateKeyPair();
        wallet1.setAddress(pair1.getPublic());
        Wallet wallet2 = new Wallet();
        KeyPair pair2 = GenSig.generateKeyPair();
        wallet2.setAddress(pair2.getPublic());
        Transaction transaction = new Transaction();
        transaction = new Transaction("hash_3", "hash_1", wallet1.getAddress(),
                wallet2.getAddress(), 20, "a flying pig!");
    }
    /**
    @Test
    public void addOriginTest() {
        BlockChain block = new BlockChain();
        Wallet wallet1 = new Wallet();
        KeyPair pair1 = GenSig.generateKeyPair();
        wallet1.setAddress(pair1.getPublic());
        Wallet wallet2 = new Wallet();
        KeyPair pair2 = GenSig.generateKeyPair();
        wallet2.setAddress(pair2.getPublic());
        Transaction transaction = new Transaction();
        transaction = new Transaction("hash_3", "hash_1", wallet1.getAddress(),
                wallet2.getAddress(), 20, "a flying pig!");
        block.addOrigin(transaction);
        assertEquals(block.getBlockChain(), transaction );
    }*/
}
