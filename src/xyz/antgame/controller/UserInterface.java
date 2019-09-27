package xyz.antgame.controller;

import xyz.antgame.domain.GameResultSet;
import xyz.antgame.service.UserRequest;

public interface UserInterface {
    GameResultSet startGame(UserRequest userRequest);
    GameResultSet startSimulation(UserRequest userRequest);
    void resetSimulationStatus();
}
