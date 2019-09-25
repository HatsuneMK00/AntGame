package xyz.antgame.controller;

import java.util.ArrayList;
import java.util.HashMap;

public class CreepingGame {
    private int totalTime;
    private Pole pole;
    private ArrayList<Ant> antList;
    private HashMap<Integer,Integer> antPositions;
    public CreepingGame(){
        totalTime = 0;
        pole = new Pole();
        antPositions=new HashMap<>();
    }
    public int startGame(int[] antStatus, ArrayList<Integer[]> result,int[] position){
        Integer[] framePositions = new Integer[antStatus.length];
        for(int i=0;i<antStatus.length;i++){
            antList.add(new Ant(i,antStatus[i],position[i]));
            framePositions[i]=position[i];
        }
        result.add(framePositions);
        int finalTime = 0;
        while(!antList.isEmpty()){
            for(Ant ant : antList){
                ant.move();
                if(antPositions.containsKey(ant.getPosition()))
                    antPositions.put(ant.getPosition(),antPositions.get(ant.getPosition())+1);
                else antPositions.put(ant.getPosition(),1);
            }
            int i = 0;
            for(Ant ant : antList){

            }
        }
        return 0;
    }
    private int validatePosition(Ant ant){
        if(antPositions.get(ant.getPosition())>=2)
            return 1;
        if(ant.getPosition()>pole.getLength()||ant.getPosition()<0)
            return -1;
        return 0;
    }

    public void setAntPositions(HashMap<Integer, Integer> antPositions) {
        this.antPositions = antPositions;
    }
}
