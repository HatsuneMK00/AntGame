package xyz.antgame.controller;

import org.junit.jupiter.api.Test;
import xyz.antgame.domain.GameResultSet;
import xyz.antgame.domain.UserRequest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

//    has defect
//    ���ϵ��ж��߼�������
//    0������Ӧ�û�ת���
    @Test
    void startGame() {
        UserRequest userRequest = new UserRequest();
        userRequest.setAntDirections("1 -1 1 -1 -1");
        userRequest.setAntNum(5);
        userRequest.setIncTime(1);
        userRequest.setAntPositions("30 30 90 120 150");
        userRequest.setPoleLength(200);
        UserController userController = new UserController();
        GameResultSet gameResultSet;
        gameResultSet = userController.startGame(userRequest);
        assertNotNull(gameResultSet);
        ArrayList<Integer[]> gameStatusResult = gameResultSet.getGameStatusResult();
//        һ�����ϲ���ת��
        for (Integer[] round:gameStatusResult
             ) {
            System.out.println(round[0]);
        }
    }

    //    �����˳�ʼ״̬�ͽ���״̬�Ƿ���ͬ ͨ��
    @Test
    void startSimulation() {
        UserRequest userRequest = new UserRequest();
        userRequest.setAntNum(5);
        userRequest.setIncTime(1);
        userRequest.setAntPositions("30 60 90 120 150");
        userRequest.setPoleLength(200);
        UserController userController = new UserController();
        GameResultSet gameResultSet = new GameResultSet();
        GameResultSet gameResultSet1 = new GameResultSet();
        GameResultSet gameResultSet2 = new GameResultSet();
        gameResultSet = userController.startSimulation(userRequest);
        gameResultSet1 = userController.startSimulation(userRequest);
        assertNotEquals(gameResultSet.getGameDuration(), gameResultSet1.getGameDuration());
        while (gameResultSet1.getGameDuration() != -1) {
            gameResultSet2 = gameResultSet;
            gameResultSet1 = userController.startSimulation(userRequest);
            System.out.println(gameResultSet1.getGameDuration());
        }
        assertEquals(gameResultSet2.getGameDuration(), gameResultSet.getGameDuration());
    }

    @Test
    void allSimulation(){
        UserRequest userRequest = new UserRequest();
        userRequest.setAntNum(5);
        userRequest.setIncTime(1);
        userRequest.setAntPositions("30 60 90 120 150");
        userRequest.setPoleLength(200);
        UserController userController = new UserController();
        GameResultSet gameResultSet = new GameResultSet();
        gameResultSet = userController.startSimulation(userRequest);
        System.out.print(gameResultSet.getGameDuration() + " ");
        while(gameResultSet.getGameDuration()!=-1){
            gameResultSet = userController.startSimulation(userRequest);
            System.out.print(gameResultSet.getGameDuration() + " ");
        }
    }


    @Test
    void resetSimulationStatus() {
        UserRequest userRequest = new UserRequest();
        userRequest.setAntNum(5);
        userRequest.setIncTime(1);
        userRequest.setAntPositions("30 60 90 120 150");
        userRequest.setPoleLength(200);
        UserController userController = new UserController();
        GameResultSet gameResultSet;
        GameResultSet gameResultSet1;
        GameResultSet gameResultSet2;

        gameResultSet = userController.startSimulation(userRequest);
        gameResultSet1 = userController.startSimulation(userRequest);
        gameResultSet2 = userController.startSimulation(userRequest);

        System.out.println(gameResultSet.getGameDuration());
        System.out.println(gameResultSet1.getGameDuration());
        System.out.println(gameResultSet2.getGameDuration());
        assertNotEquals(gameResultSet1.getGameDuration(), gameResultSet.getGameDuration());
//        ��һ������ʹ�õĵ�����
        userController.resetSimulationStatus();
        gameResultSet1 = userController.startSimulation(userRequest);
        System.out.println(gameResultSet.getGameDuration());
        System.out.println(gameResultSet1.getGameDuration());
        assertEquals(gameResultSet1.getGameDuration(), gameResultSet.getGameDuration());

    }
}