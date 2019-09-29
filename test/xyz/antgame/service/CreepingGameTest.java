package xyz.antgame.service;

import org.junit.jupiter.api.Test;

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