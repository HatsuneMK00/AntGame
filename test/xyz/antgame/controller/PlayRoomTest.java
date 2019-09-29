package xyz.antgame.controller;

import org.junit.jupiter.api.Test;
import xyz.antgame.domain.GameResultSet;

import static org.junit.jupiter.api.Assertions.*;

class PlayRoomTest {

    @Test
    void startSimulation() {
        PlayRoom playRoom = new PlayRoom();
        GameResultSet gameResultSet;
        gameResultSet = playRoom.startSimulation();

        assertNotNull(gameResultSet);
        assertNotNull(gameResultSet.getGameStatusResult());
        assertNotEquals(0,gameResultSet.getGameStatusResult().size());
    }

    @Test
    void startSimulationTest(){
        PlayRoom playRoom = new PlayRoom();
        GameResultSet gameResultSet = new GameResultSet();
        gameResultSet = playRoom.startSimulation();

        while(gameResultSet.getGameDuration()!=-1){
            gameResultSet = playRoom.startSimulation();
        }
    }

    @Test
//     has defect!!
//    java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
//    Fixed already, by clearing ant list every time the CreepingGame.startGame called
    void startGame(){
        PlayRoom playRoom = new PlayRoom();
        GameResultSet gameResultSet = new GameResultSet();
        gameResultSet = playRoom.startGame();
        gameResultSet = playRoom.startGame();
        assertNotNull(gameResultSet);
        assertNotNull(gameResultSet.getGameStatusResult());
        assertNotEquals(0,gameResultSet.getGameStatusResult().size());
    }
}