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

    public void createTransaction(PublicKey pKey_sender, PublicKey pKey_recipient, Map<String, Double> consumedCoins,
                                  String message, byte[] signedTransaction) {

        PublicKey address_recipient = pKey_recipient;
        Integer lastBlock = 0;

        for (String prev_hash : consumedCoins.keySet()) {

            if (prev_hash.startsWith("CA_")) {
                pKey_recipient = pKey_sender;
            }

            lastBlock = blockChain.size() + 1;
            Transaction transaction = new Transaction("hash_" + lastBlock.toString(), prev_hash, pKey_sender,
                    pKey_recipient, consumedCoins.get(prev_hash), message);
            getBlockChain().add(transaction);

            pKey_recipient = address_recipient;
        }
    }

    public boolean isSignatureValid(PublicKey pKey, String message, byte[] signedTransaction) {
        return GenSig.verify(pKey, message, signedTransaction);
    }
    public boolean isConsumedCoinValid(Map<String, Double> consumedCoins){
        for (String hash : consumedCoins.keySet()) {
            for (Transaction transaction : blockChain) {
                if (hash.equals(transaction.getPrev_hash())) {
                    return false;
                }
            }
        }
        return true;
    }
    public void processTransactions(PublicKey pKey_sender, PublicKey pKey_Recipient, Map<String, Double> consumedCoins,
                                    String message, byte[] signedTransaction) {
        if (isSignatureValid(pKey_sender, message, signedTransaction) && isConsumedCoinValid(consumedCoins)) {
            createTransaction(pKey_sender, pKey_Recipient, consumedCoins, message, signedTransaction);
        }
    }

    public void addOrigin(Transaction transaction) {
        blockChain.add(transaction);
    }
}
