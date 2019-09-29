package xyz.antgame.service;

import xyz.antgame.domain.GameResultSet;
import xyz.antgame.iterator.AntDirectionsIterator;

import java.util.ArrayList;

public class PlayRoom{
    private CreepingGame creepingGame;
//    ���������õĵ����� һ��ģ����ͬһ��������
    private AntDirectionsIterator antDirectionsIterator;
    //    start�����Ҹо�����Ҫ ���Ȱ���ȥ���� ---guo
//    private boolean start
    private int incTime;
    private int antNum;
    private int maxTime;
    private int minTime;
    private int poleLength;
//    ���ڴ����Ϸ�����ϵĳ�ʼλ��
    private int[] antPositions;
//    ���ڴ����Ϸ�����ϵĳ�ʼ����
    private int[] antDirections;

    public CreepingGame getCreepingGame() {
        return creepingGame;
    }

    public void setCreepingGame(CreepingGame creepingGame) {
        this.creepingGame = creepingGame;
    }

    public AntDirectionsIterator getAntDirectionsIterator() {
        return antDirectionsIterator;
    }

    public void setAntDirectionsIterator(AntDirectionsIterator antDirectionsIterator) {
        this.antDirectionsIterator = antDirectionsIterator;
    }

    public int getIncTime() {
        return incTime;
    }

    public void setIncTime(int incTime) {
        this.incTime = incTime;
    }

    public int getAntNum() {
        return antNum;
    }

    public void setAntNum(int antNum) {
        this.antNum = antNum;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getPoleLength() {
        return poleLength;
    }

    public void setPoleLength(int poleLength) {
        this.poleLength = poleLength;
    }

    public int[] getAntPositions() {
        return antPositions;
    }

    public void setAntPositions(int[] antPositions) {
        this.antPositions = antPositions;
    }

    public int[] getAntDirections() {
        return antDirections;
    }

    public void setAntDirections(int[] antDirections) {
        this.antDirections = antDirections;
    }

//    ǰ����Ҫ�ṩ ����ʼ��Ϸ���Լ�����ʼ��һ�����İ�ť���ڵ����������
//    ��ʼ��ť��get��ʽ����/startGame
//    �������ÿ��һ�ΰ�ť����һ��
    public GameResultSet startSimulation() {
        if (this.creepingGame == null) {
            // ���û��creepGame���� ����һ��Ĭ�ϵ�CreepGame����
            this.creepingGame = new CreepingGame();
        }
        int[] antDirections;
        int gameTotalTime = 0;
        ArrayList<Integer[]> gameStatusResult = new ArrayList<Integer[]>();
        GameResultSet gameResultSet = new GameResultSet();

        if ((antDirections = buildAntDirections()) != null) {
//        ������
//        for (int iAntDirections:antDirections
//             ) {
//            System.out.print(iAntDirections);
//        }
//        System.out.println();
            // CreepingGame ���ڲ�������Ҫ�޸�gameStatusResultΪһ�����������ά���飩
            gameTotalTime = creepingGame.startGame(antDirections,gameStatusResult,antPositions,poleLength,incTime);
//            ������
//            System.out.println(gameTotalTime);

            minTime = Math.min(minTime, gameTotalTime);
            maxTime = Math.max(maxTime, gameTotalTime);
            gameResultSet.setGameDuration(gameTotalTime);
            gameResultSet.setGameStatusResult(gameStatusResult);
            gameResultSet.setMaxTime(maxTime);
            gameResultSet.setMinTime(minTime);
        }else{
//            ��ʾ����������Ѿ�ģ�����
//            ��ʱ������ֵ������Ҫ����
//            ��ʱ��minTime��maxTimeΪ���յ�min��max���
            gameResultSet.setGameDuration(-1);
            antDirectionsIterator = null;
        }
        gameResultSet.setPoleLength(poleLength);

        return gameResultSet;
    }

//    ǰ����Ҫ�ṩһ�����Զ�����Ϸ����ť����ִ���������
//    �����������ִ���û�ָ�������
//    ��Ҫ��ʾ���� ����startGame����
    public GameResultSet startGame() {
        if (this.creepingGame == null) {
            // ���û��creepGame���� ����һ��Ĭ�ϵ�CreepGame����
            this.creepingGame = new CreepingGame();
        }
        int gameTotalTime = 0;
        ArrayList<Integer[]> gameStatusResult = new ArrayList<>();

        gameTotalTime = creepingGame.startGame(antDirections,gameStatusResult,antPositions,poleLength,incTime);
//        System.out.println(gameTotalTime);

        GameResultSet gameResultSet = new GameResultSet();
        gameResultSet.setGameDuration(gameTotalTime);
        gameResultSet.setGameStatusResult(gameStatusResult);
        gameResultSet.setMaxTime(-1);
        gameResultSet.setMinTime(-1);
        gameResultSet.setPoleLength(poleLength);

        return gameResultSet;
    }


//    ��ǰ��ѡ����ֱ����ʾģ������ʱ�� �����������
//    ���յ�����˵ ����ͬһ��PlayRoom�����iteratorӦ����ͬһ�� ��˿���ֱ������߼�������
    public void printResult() {
//        �����������
        this.incTime = 1;
        this.antNum = 5;
        int length = 200;
        int[] antStatus;
//        �������
        int gameTotalTime;
        ArrayList<Integer[]> gameStatusResult = null;

        while ((antStatus = buildAntDirections()) != null) {
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
    public void getInputConfiguration() {
//        start = true;
    }

    //    �������ʹ��һ��AntStatus�������ڵ���ȫ����������ʼ�������
//    AntStatus����ʹ�õ�����ģʽ ����һ�����飬�����±�������ϱ�� 1������ 0������
//    ���������������ɺ� �ú�������null
    private int[] buildAntDirections() {
        if (antDirectionsIterator == null) {
            AntDirections antDirections = new AntDirections(antNum);
            this.antDirectionsIterator = (AntDirectionsIterator) antDirections.iterator();
        }
        if (antDirectionsIterator.hasNext()) {
            return (int[]) antDirectionsIterator.next();
        } else {
            return null;
        }
    }
}
