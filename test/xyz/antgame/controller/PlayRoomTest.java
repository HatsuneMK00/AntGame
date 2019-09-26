package xyz.antgame.controller;

import org.junit.jupiter.api.Test;
import xyz.antgame.domain.GameResultSet;

import static org.junit.jupiter.api.Assertions.*;

class PlayRoomTest {

    @Test
    void startGames() {
        PlayRoom playRoom = new PlayRoom();
        GameResultSet gameResultSet = new GameResultSet();
        gameResultSet = playRoom.startSimulation();
        assertNotNull(gameResultSet);
        assertNotNull(gameResultSet.getGameStatusResult());
        assertNotEquals(0,gameResultSet.getGameStatusResult().size());
    }
}