package xyz.antgame.domain;

import java.util.ArrayList;

public class GameResultSet {
    private double gameDuration;
    private ArrayList<Integer[]> gameStatusResult;
    private double minTime;
    private double maxTime;
    private int poleLength;
    private int iteratorIndex;

    public int getIteratorIndex() {
        return iteratorIndex;
    }

    public void setIteratorIndex(int iteratorIndex) {
        this.iteratorIndex = iteratorIndex;
    }

    public int getPoleLength() {
        return poleLength;
    }

    public void setPoleLength(int poleLength) {
        this.poleLength = poleLength;
    }

    public ArrayList<Integer[]> getGameStatusResult() {
        return gameStatusResult;
    }

    public void setGameStatusResult(ArrayList<Integer[]> gameStatusResult) {
        this.gameStatusResult = gameStatusResult;
    }

    public double getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(double gameDuration) {
        this.gameDuration = gameDuration;
    }

    public double getMinTime() {
        return minTime;
    }

    public void setMinTime(double minTime) {
        this.minTime = minTime;
    }

    public double getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(double maxTime) {
        this.maxTime = maxTime;
    }
}
