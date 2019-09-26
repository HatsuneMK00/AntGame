package xyz.antgame.controller;

import xyz.antgame.domain.GameResultSet;

public interface UserInterface {
    GameResultSet startGame();
    GameResultSet startSimulation();
    void printResult();
    void getInputConfiguration();
}
