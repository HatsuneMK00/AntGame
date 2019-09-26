package xyz.antgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.antgame.domain.GameResultSet;
import xyz.antgame.iterator.AntStatusIterator;

import javax.management.loading.ClassLoaderRepository;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PlayRoom implements UserInterface {
    private CreepingGame creepingGame;
    private AntStatusIterator antStatusIterator;
    //    start�����Ҹо�����Ҫ ���Ȱ���ȥ���� ---guo
//    private boolean start
    private int incTime;
    private int antNum;
    private int maxTime;
    private int minTime;

    public int getIncTime() {
        return incTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getMinTime() {
        return minTime;
    }

//    ǰ����Ҫ�ṩ ����ʼ��Ϸ���Լ�����ʼ��һ�����İ�ť���ڵ����������
//    ��ʼ��ť��get��ʽ����/startGame
//    �������ÿ��һ�ΰ�ť����һ��
    @Override
    @ResponseBody
    @RequestMapping("/startSimulation")
    public GameResultSet startSimulation() {
        if (this.creepingGame == null) {
            // ���û��creepGame���� ����һ��Ĭ�ϵ�CreepGame����
            this.creepingGame = new CreepingGame();
        }
//        �����������Ĳ���
        this.incTime = 1;
        this.antNum = 5;
        int []antPositions = {30,150,160,170,180};
        int length = 200;
        int[] antStatus;
//        �����������
        int gameTotalTime = 0;
        ArrayList<Integer[]> gameStatusResult = new ArrayList<Integer[]>();

        if ((antStatus = buildConfiguration()) != null) {
            // CreepingGame ���ڲ�������Ҫ�޸�gameStatusResultΪһ�����������ά���飩
            gameTotalTime = creepingGame.startGame(antStatus,gameStatusResult,antPositions,length,incTime);
//            ������
//            System.out.println(gameTotalTime);

            minTime = Math.min(minTime, gameTotalTime);
            maxTime = Math.max(maxTime, gameTotalTime);
        }
        GameResultSet gameResultSet = new GameResultSet();
        gameResultSet.setGameDuration(gameTotalTime);
        gameResultSet.setGameStatusResult(gameStatusResult);
        gameResultSet.setMaxTime(maxTime);
        gameResultSet.setMinTime(minTime);

        return gameResultSet;
    }

    //    ǰ����Ҫ�ṩһ�����Զ�����Ϸ����ť����ִ���������
//    �����������ִ���û�ָ�������
//    ��Ҫ��ʾ���� ����startGame����
    @Override
    @ResponseBody
    @RequestMapping("/startGame")
    public GameResultSet startGame() {
        if (this.creepingGame == null) {
            // ���û��creepGame���� ����һ��Ĭ�ϵ�CreepGame����
            this.creepingGame = new CreepingGame();
        }
//        �����������
        this.incTime = 1;
        this.antNum = 5;
        int []antPositions = {30,150,160,170,180};
        int length = 200;
        int[] antStatus;
        int[] antDirections = {-1,1,-1,-1,1};
//        �������
        int gameTotalTime = 0;
        ArrayList<Integer[]> gameStatusResult = new ArrayList<>();

        gameTotalTime = creepingGame.startGame(antDirections,gameStatusResult,antPositions,length,incTime);
//        System.out.println(gameTotalTime);

        GameResultSet gameResultSet = new GameResultSet();
        gameResultSet.setGameDuration(gameTotalTime);
        gameResultSet.setGameStatusResult(gameStatusResult);
        gameResultSet.setMaxTime(-1);
        gameResultSet.setMinTime(-1);

        return gameResultSet;
    }


//    ��ǰ��ѡ����ֱ����ʾģ������ʱ�� �����������
//    ���յ�����˵ ����ͬһ��PlayRoom�����iteratorӦ����ͬһ�� ��˿���ֱ������߼�������
    @Override
    public void printResult() {
//        �����������
        this.incTime = 1;
        this.antNum = 5;
        int []antPositions = {30,150,160,170,180};
        int length = 200;
        int[] antStatus;
        int[] antDirections = {-1,1,-1,-1,1};
//        �������
        int gameTotalTime;
        ArrayList<Integer[]> gameStatusResult = null;

        while ((antStatus = buildConfiguration()) != null) {
            // CreepingGame ���ڲ�������Ҫ�޸�gameStatusResultΪһ�����������ά���飩
            gameTotalTime = creepingGame.startGame(antStatus,gameStatusResult,antPositions,length,incTime);
//            ������
            System.out.println(gameTotalTime);

            minTime = Math.min(minTime, gameTotalTime);
            maxTime = Math.max(maxTime, gameTotalTime);
        }
        System.out.println(minTime);
        System.out.println(maxTime);
    }

//   use temporary data to simulate
//   this will be the @controller function interact with front stage
//    ���������Ҫ��playRoom�����������
//   ���������Ҫ��д
    @Override
    public void getInputConfiguration() {
//        start = true;
    }

    //    �������ʹ��һ��AntStatus�������ڵ���ȫ����������ʼ�������
//    AntStatus����ʹ�õ�����ģʽ ����һ�����飬�����±�������ϱ�� 1������ 0������
//    ���������������ɺ� �ú�������null
    public int[] buildConfiguration() {
        if (antStatusIterator == null) {
            AntStatus antStatus = new AntStatus(antNum);
            this.antStatusIterator = (AntStatusIterator) antStatus.iterator();
        }
        if (antStatusIterator.hasNext()) {
            return (int[]) antStatusIterator.next();
        } else {
            return null;
        }
    }
}
