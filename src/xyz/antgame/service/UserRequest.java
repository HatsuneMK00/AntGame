package xyz.antgame.service;

public class UserRequest {
    private int antNum;
    private int poleLength;
    //    用于存放游戏中蚂蚁的初始位置
    private String antPositions;
    //    用于存放游戏中蚂蚁的初始方向
    private String antDirections;
    private int incTime;

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

    public int getPoleLength() {
        return poleLength;
    }

    public void setPoleLength(int poleLength) {
        this.poleLength = poleLength;
    }

    public String getAntPositions() {
        return antPositions;
    }

    public void setAntPositions(String antPositions) {
        this.antPositions = antPositions;
    }

    public String getAntDirections() {
        return antDirections;
    }

    public void setAntDirections(String antDirections) {
        this.antDirections = antDirections;
    }
}
