package xyz.antgame.service;

import xyz.antgame.domain.GameResultSet;
import xyz.antgame.iterator.AntDirectionsIterator;

import java.util.ArrayList;

public class PlayRoom{
    private CreepingGame creepingGame;
//    迭代方向用的迭代器 一轮模拟用同一个迭代器
    private AntDirectionsIterator antDirectionsIterator;
    //    start属性我感觉不需要 我先把他去掉了 ---guo
//    private boolean start
    private int incTime;
    private int antNum;
    private int maxTime;
    private int minTime;
    private int poleLength;
//    用于存放游戏中蚂蚁的初始位置
    private int[] antPositions;
//    用于存放游戏中蚂蚁的初始方向
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

//    前端需要提供 “开始游戏”以及“开始下一个”的按钮用于调用这个函数
//    开始按钮用get方式请求/startGame
//    这个函数每按一次按钮调用一次
    public GameResultSet startSimulation() {
        if (this.creepingGame == null) {
            // 如果没有creepGame对象 创建一个默认的CreepGame对象
            this.creepingGame = new CreepingGame();
        }
        int[] antDirections;
        int gameTotalTime = 0;
        ArrayList<Integer[]> gameStatusResult = new ArrayList<Integer[]>();
        GameResultSet gameResultSet = new GameResultSet();

        if ((antDirections = buildAntDirections()) != null) {
//        测试用
//        for (int iAntDirections:antDirections
//             ) {
//            System.out.print(iAntDirections);
//        }
//        System.out.println();
            // CreepingGame 的内部方法需要修改gameStatusResult为一个结果集（二维数组）
            gameTotalTime = creepingGame.startGame(antDirections,gameStatusResult,antPositions,poleLength,incTime);
//            测试用
//            System.out.println(gameTotalTime);

            minTime = Math.min(minTime, gameTotalTime);
            maxTime = Math.max(maxTime, gameTotalTime);
            gameResultSet.setGameDuration(gameTotalTime);
            gameResultSet.setGameStatusResult(gameStatusResult);
            gameResultSet.setMaxTime(maxTime);
            gameResultSet.setMinTime(minTime);
        }else{
//            表示所有情况都已经模拟完毕
//            此时其他的值都不需要考虑
//            此时的minTime和maxTime为最终的min和max结果
            gameResultSet.setGameDuration(-1);
            antDirectionsIterator = null;
        }
        gameResultSet.setPoleLength(poleLength);

        return gameResultSet;
    }

//    前端需要提供一个“自定义游戏”按钮用于执行这个函数
//    这个方法用于执行用户指定的情况
//    需要显示过程 调用startGame函数
    public GameResultSet startGame() {
        if (this.creepingGame == null) {
            // 如果没有creepGame对象 创建一个默认的CreepGame对象
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


//    当前端选择了直接显示模拟结果的时候 调用这个函数
//    按照道理来说 对于同一个PlayRoom类对象iterator应该是同一个 因此可以直接在这边继续迭代
    public void printResult() {
//        处理请求参数
        this.incTime = 1;
        this.antNum = 5;
        int length = 200;
        int[] antStatus;
//        处理结束
        int gameTotalTime;
        ArrayList<Integer[]> gameStatusResult = null;

        while ((antStatus = buildAntDirections()) != null) {
            // CreepingGame 的内部方法需要修改gameStatusResult为一个结果集（二维数组）
            gameTotalTime = creepingGame.startGame(antStatus,gameStatusResult,antPositions,length,incTime);
//            测试用
            System.out.println(gameTotalTime);

            minTime = Math.min(minTime, gameTotalTime);
            maxTime = Math.max(maxTime, gameTotalTime);
        }
        System.out.println(minTime);
        System.out.println(maxTime);
    }

//   use temporary data to simulate
//   this will be the @controller function interact with front stage
//    这个方法需要在playRoom被创建后调用
//   这个方法需要重写
    public void getInputConfiguration() {
//        start = true;
    }

    //    这个函数使用一个AntStatus对象用于迭代全部的蚂蚁起始方向情况
//    AntStatus对象使用迭代器模式 返回一个数组，数组下标代表蚂蚁编号 1代表朝右 0代表朝左
//    当所有情况迭代完成后 该函数返回null
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
