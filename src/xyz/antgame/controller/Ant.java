package xyz.antgame.controller;

public class Ant {
    private int position;
    private int no;
    private int direction;
    private boolean alive;
    private Pole pole;
    public Ant(int no,int direction,int position,Pole pole){
        this.no =no;
        this.position=position;
        this.direction = direction;
        this.pole = pole;
        alive =true;
    }
    public void suicide(){
        pole.changeAntCount(-1);
        alive = false;
    }
    public boolean isAlive(){
        return alive;
    }
    private boolean isBeyond(){
        return position>pole.getLength()||position<0;
    }
    public int move(){
        if(isBeyond()){
            pole.destroyAnt(this);
            position=-1;
            return position;
        }
        if(pole.hasCollision(position))
            changeDirection();
        pole.positionMinus(position);
        position += direction;
        if(isBeyond()){
            pole.destroyAnt(this);
            position=-1;
            return position;
        }
        if(pole.hasCollision(position))
            changeDirection();
        pole.positionPlus(position);
        return position;
    }
    public int getPosition() {
        return position;
    }
    public int changeDirection(){
        direction = -direction;
        return position;
    }
}
