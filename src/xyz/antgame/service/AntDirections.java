package xyz.antgame.service;

import xyz.antgame.iterator.Aggregate;
import xyz.antgame.iterator.AntDirectionsIterator;
import xyz.antgame.iterator.Iterator;

public class AntDirections implements Aggregate {
    private int antNum;

    public AntDirections(int antNum) {
        this.antNum = antNum;
    }

    @Override

    public Iterator iterator() {
        return new AntDirectionsIterator(antNum);
    }
}
