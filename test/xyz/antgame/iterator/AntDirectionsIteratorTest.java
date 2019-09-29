package xyz.antgame.iterator;

import org.junit.jupiter.api.Test;
import xyz.antgame.service.AntDirections;

class AntDirectionsIteratorTest {

    @Test
    void hasNext() {
        AntDirections antStatus = new AntDirections(5);
        AntDirectionsIterator iterator = (AntDirectionsIterator) antStatus.iterator();
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