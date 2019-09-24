package xyz.antgame.controller;

import xyz.antgame.iterator.Aggregate;
import xyz.antgame.iterator.AntStatusIterator;
import xyz.antgame.iterator.Iterator;

public class AntStatus implements Aggregate {
    private int antNum;

    public AntStatus(int antNum) {
        this.antNum = antNum;
    }

    @Override
    public Iterator iterator() {
        return new AntStatusIterator(antNum);
    }
}
