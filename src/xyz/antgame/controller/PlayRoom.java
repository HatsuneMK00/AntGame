package xyz.antgame.controller;

import java.util.HashMap;

public class PlayRoom implements UserInterface {
    private CreepingGame creepingGame;
    private boolean start;
    private int incTime;
    private int antNum;
    private int maxTime;
    private int minTime;
    private HashMap<String,Integer> configuration = new HashMap<String,Integer>();

    @Override
    public void startGames(int incTime) {
        this.incTime = incTime;
        new CreepingGame();
    }

    @Override
    public void startGames(int incTime, int antNum) {
        this.incTime = incTime;
        this.antNum = antNum;
    }

    @Override
    public void printResult() {

    }

    @Override
    public void inputConfiguration() {

    }

    void buildConfiguration(){
        configuration.put("incTime",incTime);
        configuration.put("antNum",antNum);
    }


}
