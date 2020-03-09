package org.mvpigs.pigcoin;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Double> pigcoins = new HashMap<String, Double>();
        pigcoins.put("input", 0d);
        pigcoins.put("output", 0d);
        for (Transaction transaccion : getBlockChain()) {
            if (transaccion.getpKey_recipient() == address) {
                pigcoins.put("input",pigcoins.get("input") + transaccion.getPigcoins());
            } else if (transaccion.getpKey_sender() == address) {
                pigcoins.put("output", pigcoins.get("output") + transaccion.getPigcoins());
            }
        } return pigcoins;
    }

    public void addOrigin(Transaction transaction) {
        blockChain.add(transaction);
    }
}
