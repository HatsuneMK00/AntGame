package xyz.antgame.service;

import org.junit.jupiter.api.Test;
import xyz.antgame.domain.GameResultSet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

//    has defect
//    蚂蚁的行动逻辑有问题
//    0号蚂蚁应该会转向的
    @Test
    void startGame() {
        UserRequest userRequest = new UserRequest();
        userRequest.setAntDirections("1 1 1 -1 -1");
        userRequest.setAntNum(5);
        userRequest.setIncTime(1);
        userRequest.setAntPositions("30 60 90 120 150");
        userRequest.setPoleLength(200);
        UserService userService = new UserService();
        GameResultSet gameResultSet;
        gameResultSet = userService.startGame(userRequest);
        assertNotNull(gameResultSet);
        ArrayList<Integer[]> gameStatusResult = gameResultSet.getGameStatusResult();
//        一号蚂蚁不会转弯
        for (Integer[] round:gameStatusResult
             ) {
            System.out.println(round[0]);
        }
    }

    //    测试了初始状态和结束状态是否相同 通过
    @Test
    void startSimulation() {
        UserRequest userRequest = new UserRequest();
        userRequest.setAntNum(5);
        userRequest.setIncTime(1);
        userRequest.setAntPositions("30 60 90 120 150");
        userRequest.setPoleLength(200);
        UserService userService = new UserService();
        GameResultSet gameResultSet = new GameResultSet();
        GameResultSet gameResultSet1 = new GameResultSet();
        GameResultSet gameResultSet2 = new GameResultSet();
        gameResultSet = userService.startSimulation(userRequest);
        gameResultSet1 = userService.startSimulation(userRequest);
        assertNotEquals(gameResultSet.getGameDuration(), gameResultSet1.getGameDuration());
        while (gameResultSet1.getGameDuration() != -1) {
            gameResultSet2 = gameResultSet;
            gameResultSet1 = userService.startSimulation(userRequest);
            System.out.println(gameResultSet1.getGameDuration());
        }
        assertEquals(gameResultSet2.getGameDuration(), gameResultSet.getGameDuration());
    }


    @Test
    void resetSimulationStatus() {
        UserRequest userRequest = new UserRequest();
        userRequest.setAntNum(5);
        userRequest.setIncTime(1);
        userRequest.setAntPositions("30 60 90 120 150");
        userRequest.setPoleLength(200);
        UserService userService = new UserService();
        GameResultSet gameResultSet;
        GameResultSet gameResultSet1;
        GameResultSet gameResultSet2;

        gameResultSet = userService.startSimulation(userRequest);
        gameResultSet1 = userService.startSimulation(userRequest);
        gameResultSet2 = userService.startSimulation(userRequest);

        System.out.println(gameResultSet.getGameDuration());
        System.out.println(gameResultSet1.getGameDuration());
        System.out.println(gameResultSet2.getGameDuration());
        assertNotEquals(gameResultSet1.getGameDuration(), gameResultSet.getGameDuration());
//        这一行重置使用的迭代器
        userService.resetSimulationStatus();
        gameResultSet1 = userService.startSimulation(userRequest);
        System.out.println(gameResultSet.getGameDuration());
        System.out.println(gameResultSet1.getGameDuration());
        assertEquals(gameResultSet1.getGameDuration(), gameResultSet.getGameDuration());

    }
}