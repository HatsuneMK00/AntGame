package xyz.antgame.service;

import xyz.antgame.domain.GameResultSet;
import xyz.antgame.domain.UserRequest;

public interface UserInterface {
    GameResultSet startGame(UserRequest userRequest);
    GameResultSet startSimulation(UserRequest userRequest);
    void resetSimulationStatus();
}
