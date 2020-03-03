package org.mvpigs.pigcoin;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private List<Transaction> blockChain = new ArrayList<Transaction>();

    BlockChain() {}

    public void summarize() {
        for(Transaction transaction : blockChain) {
            System.out.println(transaction);
        }
    }
    public void summarize(Integer index) {
        System.out.println(blockChain.get(index));
    }


    public void addOrigin(Transaction transaction) {
        blockChain.add(transaction);
    }
}
