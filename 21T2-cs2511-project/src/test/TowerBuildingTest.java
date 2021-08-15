package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;

import unsw.loopmania.*;
import unsw.loopmania.buildings.*;
import unsw.loopmania.basicEnemies.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.ArrayList;
import java.util.List;

public class TowerBuildingTest {
    TowerBuilding towerBuilding;
    SimpleIntegerProperty x;
    SimpleIntegerProperty y;
    List<Pair<Integer, Integer>> orderedPath  = new ArrayList<>();
    Pair<Integer, Integer> orderPath;


    @Before
    public void setUp() {
        x = new SimpleIntegerProperty(1);
        y = new SimpleIntegerProperty(1);
        towerBuilding = new TowerBuilding(x, y);

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
        assertEquals(x.get(), towerBuilding.getX());
        assertEquals(y.get(), towerBuilding.getY());

        //reset position of building and pathTile
        x = new SimpleIntegerProperty(15);
        y = new SimpleIntegerProperty(16);

        towerBuilding = new TowerBuilding(x, y);
        assertEquals(x.get(), towerBuilding.getX());
        assertEquals(y.get(), towerBuilding.getY());
        assertEquals(towerBuilding.getType(),"TowerBuilding");
    }

    @Test
    public void isInSupportRangeTest() {

        PathPosition pathPosition;
        BasicEnemy basicEnemy;

        // create a path position and enemy at (0,1)
        pathPosition = new PathPosition(1, orderedPath);
        basicEnemy = new Slug(pathPosition);
        assertTrue(towerBuilding.canGiveHelp(basicEnemy));

        pathPosition = new PathPosition(12, orderedPath);
        basicEnemy = new Vampire(pathPosition);
        assertFalse(towerBuilding.canGiveHelp(basicEnemy));

        pathPosition = new PathPosition(13, orderedPath);
        basicEnemy = new Zombie(pathPosition);
        assertFalse(towerBuilding.canGiveHelp(basicEnemy));

        pathPosition = new PathPosition(15, orderedPath);
        basicEnemy = new Slug(pathPosition);
        assertTrue(towerBuilding.canGiveHelp(basicEnemy));
        assertEquals(5,towerBuilding.getAttackIncrease());

    }

}
