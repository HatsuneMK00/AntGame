package xyz.antgame.service;

import java.util.ArrayList;
import java.util.HashMap;

public class CreepingGame {
    private double totalTime;
    private Pole pole;
    private ArrayList<Ant> antList;

    public CreepingGame() {
        totalTime = 0;
        antList = new ArrayList<>();
    }

    public double startGame(int[] antDirections, ArrayList<Integer[]> result, int[] positions, int length, double incTime) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int position : positions){
            if(map.containsKey(position))
                return -1;
            map.put(position,1);
        }
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
