package xyz.antgame.controller;

public class Ant {
    private int position;
    private int no;
    private int direction;
    public Ant(int no,int direction,int position){
        this.no =no;
        this.position=position;
        this.direction = direction;
    }
    public int move(){
        position += direction;
        return position;
    }

    public int getPosition() {
        return position;
    }
    public int changePosition(){
        position = -position;
        return position;
    }
}
