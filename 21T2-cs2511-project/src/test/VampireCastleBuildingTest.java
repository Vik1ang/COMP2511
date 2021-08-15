package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import unsw.loopmania.PathPosition;
import unsw.loopmania.PathTile;


import unsw.loopmania.buildings.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class VampireCastleBuildingTest {

    VampireCastleBuilding vampireBuilding;
    SimpleIntegerProperty x;
    SimpleIntegerProperty y;
    List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    Pair<Integer, Integer> orderPath;


    @Before
    public void setUp() {
        x = new SimpleIntegerProperty(4);
        y = new SimpleIntegerProperty(0);
        vampireBuilding = new VampireCastleBuilding(x, y);

        // create a square path 4*4
        for (int i = 0; i < 5; i++) {
            orderPath = new Pair<>(i, 0);
            orderedPath.add(orderPath);
        }
        for (int i = 1; i < 5; i++) {
            orderPath = new Pair<>(4, i);
            orderedPath.add(orderPath);
        }
        for (int i = 3; i >= 0; i--) {
            orderPath = new Pair<>(i, 4);
            orderedPath.add(orderPath);
        }
        for (int i = 3; i > 0; i--) {
            orderPath = new Pair<>(0, i);
            orderedPath.add(orderPath);
        }

    }

    @Test
    public void getterAndSetterTest() {
        assertEquals(x.get(), vampireBuilding.getX());
        assertEquals(y.get(), vampireBuilding.getY());

        //reset position of building and pathTile
        x = new SimpleIntegerProperty(15);
        y = new SimpleIntegerProperty(16);

        vampireBuilding = new VampireCastleBuilding(x,y);
        assertEquals(x.get(), vampireBuilding.getX());
        assertEquals(y.get(), vampireBuilding.getY());

        assertEquals(vampireBuilding.getType(),"VampireCastleBuilding");
    }

    @Test
    public void getterAndSetterTest2() {
        vampireBuilding = new VampireCastleBuilding(x, y,0);
        assertEquals(x.get(), vampireBuilding.getX());
        assertEquals(y.get(), vampireBuilding.getY());

        //reset position of building and pathTile
        x = new SimpleIntegerProperty(15);
        y = new SimpleIntegerProperty(16);

        vampireBuilding = new VampireCastleBuilding(x,y);
        assertEquals(x.get(), vampireBuilding.getX());
        assertEquals(y.get(), vampireBuilding.getY());

        assertEquals(vampireBuilding.getType(),"VampireCastleBuilding");
    }

    @Test
    public void canGiveHelpTest() {
        assertEquals(false, vampireBuilding.canGiveHelp(null));
    }



}
