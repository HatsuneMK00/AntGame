package xyz.antgame.iterator;

import xyz.antgame.controller.AntStatus;

import static org.junit.jupiter.api.Assertions.*;

class AntStatusIteratorTest {

    @org.junit.jupiter.api.Test
    void hasNext() {
        AntStatus antStatus = new AntStatus(5);
        AntStatusIterator iterator = (AntStatusIterator) antStatus.iterator();
        while (iterator.hasNext()) {
            int[] status = (int[]) iterator.next();
            for (int num : status
            ) {
                System.out.print(num);
            }
            System.out.println();
        }
    }
}