package xyz.antgame.domain;

import java.util.ArrayList;

public class GameResultSet {
    private int gameDuration;
    private ArrayList<Integer[]> gameStatusResult;
    private int minTime;
    private int maxTime;
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

    public int getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }

    public ArrayList<Integer[]> getGameStatusResult() {
        return gameStatusResult;
    }

    public void setGameStatusResult(ArrayList<Integer[]> gameStatusResult) {
        this.gameStatusResult = gameStatusResult;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }
}
