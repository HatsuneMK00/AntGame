package xyz.antgame.service;

public class UserRequest {
    private int antNum;
    private int poleLength;
    //    ���ڴ����Ϸ�����ϵĳ�ʼλ��
    private String antPositions;
    //    ���ڴ����Ϸ�����ϵĳ�ʼ����
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
