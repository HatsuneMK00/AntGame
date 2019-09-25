package xyz.antgame.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreepingGameTest {

    @Test
    void startGame() {
        PlayRoom playRoom = new PlayRoom();
        playRoom.startSimulation();
    }

    @Test
    void startGameTest(){
        PlayRoom playRoom = new PlayRoom();
        playRoom.startGame();
    }
}