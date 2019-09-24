package xyz.antgame.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayRoomTest {

    @Test
    void startGames() {
        PlayRoom playRoom = new PlayRoom();
        playRoom.inputConfiguration();
        playRoom.startGames();
        System.out.println(playRoom.getMaxTime());
        System.out.println(playRoom.getMinTime());
    }
}