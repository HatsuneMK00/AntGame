package xyz.antgame.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestExecutionListeners;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PoleTest {
    @Test
    public void hasCollisionTest(){
         Pole tem = new Pole(10);
         ArrayList<Ant> lis = new ArrayList<>();
         Ant ant1 = new Ant(1,-1,1,tem);
        Ant ant2 = new Ant(2,-1,2,tem);
        Ant ant3 = new Ant(3,-1,1,tem);
        lis.add(ant1);
        lis.add(ant2);
        lis.add(ant3);
        tem.setAntList(lis);
        System.out.println(tem.hasCollision(1));
    }
}