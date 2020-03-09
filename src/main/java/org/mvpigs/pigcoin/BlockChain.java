package org.mvpigs.pigcoin;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlockChain {

    private List<Transaction> blockChain = new ArrayList<Transaction>();

    BlockChain() {}

    public void summarize() {
        for(Transaction transaction : blockChain) {
            System.out.println(transaction);
        }
    }

    public List<Transaction> getBlockChain() {
        return blockChain;
    }

    public void summarize(Integer index) {
        System.out.println(blockChain.get(index));
    }

    public double[] loadWallet(PublicKey address){

       double pigcoinsIn = 0d;
       double pigcoinsOut= 0d;

       for (Transaction transaction : getBlockChain()) {
           if (address.equals(transaction.getpKey_recipient())) {
               pigcoinsIn = pigcoinsIn + transaction.getPigcoins();
           }
           if (address.equals(transaction.getpKey_sender())) {
               pigcoinsOut = pigcoinsOut + transaction.getPigcoins();
           }
       }
       double[] pigcoins = {pigcoinsIn , pigcoinsOut};
       return pigcoins;
    }

    public List<Transaction> loadInputTransactions(PublicKey address) {
        List<Transaction> inputTransactions = getBlockChain().stream()
                .filter(transaction -> transaction.getpKey_recipient().equals(address))
                .collect(Collectors.toCollection(ArrayList<Transaction>::new));
        return inputTransactions;
    }
    public List<Transaction> loadOutputTransaction(PublicKey address) {
        List<Transaction> outputTransaction = getBlockChain().stream()
                .filter(transaction -> transaction.getpKey_sender().equals(address))
                .collect(Collectors.toCollection(ArrayList<Transaction>::new));
        return outputTransaction;
    }

    public void addOrigin(Transaction transaction) {
        blockChain.add(transaction);
    }
}
