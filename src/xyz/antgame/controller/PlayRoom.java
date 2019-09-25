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
    public void startGames() {
        if (this.creepingGame == null) {
            // ���û��creepGame���� ����һ��Ĭ�ϵ�CreepGame����
            this.creepingGame = new CreepingGame();
        }
        this.incTime = 1;
        this.antNum = 5;
        HashMap<Integer, Integer> antPosition = new HashMap<>();
        antPosition.put(0, 30);
        antPosition.put(1, 60);

        ArrayList<Integer[]> gameStatusResult = null;
        int gameResult = 0;
        int[] antStatus;
        int[] position;
        if ((antStatus = buildConfiguration()) != null) {
            // CreepingGame ���ڲ�������Ҫ�޸�gameStatusResultΪһ�����������ά���飩
//            creepGame��ִ�е�ʱ�� �Լ�����һ��getIncTime()������ȡincTime �ҾͲ���ֵ��   ---guo
            gameResult = creepingGame.startGame(antStatus, gameStatusResult);
            minTime = Math.min(minTime, gameResult);
            maxTime = Math.max(maxTime, gameResult);
        }
    }

    //    ǰ����Ҫ�ṩһ����ģ��ȫ���������ť����ִ���������
//    �����������һ����ִ�������п��ܵ������ֻ����ǰ�˷���һ�����(���ʱ�����Сʱ��)
//    ��Ҫ��ʾ���� ����startGame����
    @Override
    public void startGame() {

    }

    @Override
    public void printResult() {

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
