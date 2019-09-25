package xyz.antgame.controller;

import java.util.ArrayList;

public class Pole {
    private int length;
    private ArrayList<Ant> antList;
    public Pole(){
        length = 600;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public void setAntList(ArrayList<Ant> antList) {
        this.antList = antList;
    }
    public void destroyAnt(Ant ant){
        antList.remove(ant);
    }
    public int getLength() {
        return length;
    }

    public ArrayList<Ant> getAntList() {
        return antList;
    }
}
