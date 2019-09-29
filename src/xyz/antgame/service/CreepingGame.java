package xyz.antgame.service;

import java.util.ArrayList;

public class CreepingGame {
    private int totalTime;
    private Pole pole;
    private ArrayList<Ant> antList;

    public CreepingGame() {
        totalTime = 0;
        antList = new ArrayList<>();
    }

    public int startGame(int[] antDirections, ArrayList<Integer[]> result, int[] positions, int length, int incTime) {
        antList.clear();
        totalTime = 0;
        Integer[] framePositions = new Integer[antDirections.length];
        pole = new Pole(length);
        for (int i = 0; i < antDirections.length; i++) {
            antList.add(new Ant(i, antDirections[i], positions[i], pole));
            framePositions[i] = positions[i];
        }
        pole.setAntList(antList);
        result.add(framePositions);
        while (pole.getCount() > 0) {
            framePositions = new Integer[antDirections.length];
            int i = 0;
            for (Ant ant : antList) {
                if (ant.isAlive()) {
                    framePositions[i] = ant.move();
                    i++;
                    continue;
                }
                framePositions[i] = -1;
                i++;
            }
            result.add(framePositions);
            totalTime += incTime;
        }
        return totalTime;
    }

}