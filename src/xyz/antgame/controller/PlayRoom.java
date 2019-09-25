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
    //    start属性我感觉不需要 我先把他去掉了 ---guo
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

    //    前端需要提供 “开始游戏”以及“开始下一个”的按钮用于调用这个函数
//    开始按钮用get方式请求/startGame
//    这个函数每按一次按钮调用一次
    @Override
    @RequestMapping("/startGames")
    public void startSimulation() {
        if (this.creepingGame == null) {
            // 如果没有creepGame对象 创建一个默认的CreepGame对象
            this.creepingGame = new CreepingGame();
        }
//        处理请求传来的参数
        this.incTime = 1;
        this.antNum = 5;
        int []antPositions = {30,150,160,170,180};
        int length = 200;
        int[] antStatus;
//        处理请求结束
        int gameResult = 0;
        ArrayList<Integer[]> gameStatusResult = new ArrayList<Integer[]>();

        if ((antStatus = buildConfiguration()) != null) {
            // CreepingGame 的内部方法需要修改gameStatusResult为一个结果集（二维数组）
            gameResult = creepingGame.startGame(antStatus,gameStatusResult,antPositions,length,incTime);
//            测试用
            System.out.println(gameResult);

            minTime = Math.min(minTime, gameResult);
            maxTime = Math.max(maxTime, gameResult);
        }
    }

    //    前端需要提供一个“自定义游戏”按钮用于执行这个函数
//    这个方法用于执行用户指定的情况
//    需要显示过程 调用startGame函数
    @Override
    public void startGame() {
//        处理请求参数
        this.incTime = 1;
        this.antNum = 5;
        int []antPositions = {30,150,160,170,180};
        int length = 200;
        int[] antStatus;
        int[] antDirections = {-1,1,-1,-1,1};
//        处理结束
        int gameResult = 0;
        ArrayList<Integer[]> gameStatusResult = null;

        gameResult = creepingGame.startGame(antDirections,gameStatusResult,antPositions,length,incTime);
        System.out.println(gameResult);

    }


//    当前端选择了直接显示模拟结果的时候 调用这个函数
//    按照道理来说 对于同一个PlayRoom类对象iterator应该是同一个 因此可以直接在这边继续迭代
    @Override
    public void printResult() {
//        处理请求参数
        this.incTime = 1;
        this.antNum = 5;
        int []antPositions = {30,150,160,170,180};
        int length = 200;
        int[] antStatus;
        int[] antDirections = {-1,1,-1,-1,1};
//        处理结束
        int gameResult;
        ArrayList<Integer[]> gameStatusResult = null;

        while ((antStatus = buildConfiguration()) != null) {
            // CreepingGame 的内部方法需要修改gameStatusResult为一个结果集（二维数组）
            gameResult = creepingGame.startGame(antStatus,gameStatusResult,antPositions,length,incTime);
//            测试用
            System.out.println(gameResult);

            minTime = Math.min(minTime, gameResult);
            maxTime = Math.max(maxTime, gameResult);
        }
        System.out.println(minTime);
        System.out.println(maxTime);
    }

    //   use temporary data to simulate
//   this will be the @controller function interact with front stage
//    这个方法需要在playRoom被创建后调用
    @Override
    public void getInputConfiguration() {
//        start = true;
    }

    //    这个函数使用一个AntStatus对象用于迭代全部的蚂蚁起始方向情况
//    AntStatus对象使用迭代器模式 返回一个数组，数组下标代表蚂蚁编号 1代表朝右 0代表朝左
//    当所有情况迭代完成后 该函数返回null
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
