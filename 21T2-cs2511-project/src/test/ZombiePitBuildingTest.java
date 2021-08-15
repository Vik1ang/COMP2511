package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import unsw.loopmania.PathTile;
import unsw.loopmania.buildings.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZombiePitBuildingTest {
    ZombiePitBuilding zombieBuilding;
    SimpleIntegerProperty x;
    SimpleIntegerProperty y;
    List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    Pair<Integer, Integer> orderPath;


    @Before
    public void setUp() {
        x = new SimpleIntegerProperty(4);
        y = new SimpleIntegerProperty(0);
        zombieBuilding = new ZombiePitBuilding(x, y);

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
        assertEquals(x.get(), zombieBuilding.getX());
        assertEquals(y.get(), zombieBuilding.getY());

        //reset position of building and pathTile
        x = new SimpleIntegerProperty(15);
        y = new SimpleIntegerProperty(16);

        zombieBuilding = new ZombiePitBuilding(x,y);
        assertEquals(x.get(), zombieBuilding.getX());
        assertEquals(y.get(), zombieBuilding.getY());
    }

    @Test
    public void getterAndSetterTest2() {
        zombieBuilding = new ZombiePitBuilding(x, y, 3);
        assertEquals(x.get(), zombieBuilding.getX());
        assertEquals(y.get(), zombieBuilding.getY());

        //reset position of building and pathTile
        x = new SimpleIntegerProperty(15);
        y = new SimpleIntegerProperty(16);

        zombieBuilding = new ZombiePitBuilding(x,y);
        assertEquals(x.get(), zombieBuilding.getX());
        assertEquals(y.get(), zombieBuilding.getY());
    }

    @Test
    public void canGiveHelpTest() {
        assertEquals(false, zombieBuilding.canGiveHelp(null));
    }
}
