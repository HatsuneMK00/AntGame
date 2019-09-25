package xyz.antgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/startGames")
    public void startSimulation() {
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
        int gameResult = 0;
        ArrayList<Integer[]> gameStatusResult = new ArrayList<Integer[]>();

        if ((antStatus = buildConfiguration()) != null) {
            // CreepingGame ���ڲ�������Ҫ�޸�gameStatusResultΪһ�����������ά���飩
            gameResult = creepingGame.startGame(antStatus,gameStatusResult,antPositions,length,incTime);
//            ������
            System.out.println(gameResult);

            minTime = Math.min(minTime, gameResult);
            maxTime = Math.max(maxTime, gameResult);
        }
    }

    //    ǰ����Ҫ�ṩһ�����Զ�����Ϸ����ť����ִ���������
//    �����������ִ���û�ָ�������
//    ��Ҫ��ʾ���� ����startGame����
    @Override
    public void startGame() {
//        �����������
        this.incTime = 1;
        this.antNum = 5;
        int []antPositions = {30,150,160,170,180};
        int length = 200;
        int[] antStatus;
        int[] antDirections = {-1,1,-1,-1,1};
//        �������
        int gameResult = 0;
        ArrayList<Integer[]> gameStatusResult = null;

        gameResult = creepingGame.startGame(antDirections,gameStatusResult,antPositions,length,incTime);
        System.out.println(gameResult);

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
        int gameResult;
        ArrayList<Integer[]> gameStatusResult = null;

        while ((antStatus = buildConfiguration()) != null) {
            // CreepingGame ���ڲ�������Ҫ�޸�gameStatusResultΪһ�����������ά���飩
            gameResult = creepingGame.startGame(antStatus,gameStatusResult,antPositions,length,incTime);
//            ������
            System.out.println(gameResult);

            minTime = Math.min(minTime, gameResult);
            maxTime = Math.max(maxTime, gameResult);
        }
        System.out.println(minTime);
        System.out.println(maxTime);
    }

    //   use temporary data to simulate
//   this will be the @controller function interact with front stage
//    ���������Ҫ��playRoom�����������
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
