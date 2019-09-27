package xyz.antgame.iterator;

public class AntDirectionsIterator implements Iterator {
    private int statusNum;
    private int antNum;
    private int index;

    public AntDirectionsIterator(int antNum) {
        this.statusNum = (int) Math.pow(2, antNum);
        this.antNum = antNum;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < statusNum;
    }

    @Override
    public Object next() {
        int[] antStatus = new int[antNum];
        int i, tempIndex;
        tempIndex = index;
        for (i = antStatus.length - 1; i >= 0; i--) {
            antStatus[i] = tempIndex % 2;
            tempIndex /= 2;
        }
        index++;
        // Ϊ��CreepingGame���ʵ�ַ��� ������1��-1��ʾ����
        for (i = 0; i < antStatus.length; i++) {
            if(antStatus[i] == 0) antStatus[i] = -1;
        }
        return antStatus;
    }
}
