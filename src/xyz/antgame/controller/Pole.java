package xyz.antgame.controller;

import java.util.ArrayList;

public class Pole {
    private int length;
    private ArrayList<Ant> antList;
    private int[] map;
    private int count;
    public Pole(int length){
        this.length = length;
        map = new int[length+1];
        count = 0;
    }
    public Pole(){
        length = 300;
        map = new int[301];
        count = 0;
    }
    public void setLength(int length) {
        this.length = length;
        map = new int[length+1];
    }
    public boolean hasCollision(int position){
        if(map[position-1]+map[position+1]>0) return true;
        if(map[position]>1) return true;
        return false;
    }
    public void setAntList(ArrayList<Ant> antList) {
        this.antList = antList;
        count = antList.size();
    }
    public void changeAntCount(int tem){
        count += tem;
    }
    public void destroyAnt(Ant ant){
        ant.suicide();
    }
    public int getLength() {
        return length;
    }
    public void positionMinus(int position){
        map[position]-=1;
    }
    public void positionPlus(int position){
        map[position]+=1;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Ant> getAntList() {
        return antList;
    }
}
