package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import unsw.loopmania.character.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.*;
import unsw.loopmania.buildings.*;
import unsw.loopmania.basicEnemies.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

public class TrapBuildingTest {


    TrapBuilding trapBuilding;
    SimpleIntegerProperty x;
    SimpleIntegerProperty y;
    List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    Pair<Integer, Integer> orderPath;


    @Before
    public void setUp() {
        x = new SimpleIntegerProperty(4);
        y = new SimpleIntegerProperty(0);
        trapBuilding = new TrapBuilding(x, y);

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
        assertEquals(x.get(), trapBuilding.getX());
        assertEquals(y.get(), trapBuilding.getY());

        //reset position of building and pathTile
        x = new SimpleIntegerProperty(15);
        y = new SimpleIntegerProperty(16);

        trapBuilding = new TrapBuilding(x,y);
        assertEquals(x.get(), trapBuilding.getX());
        assertEquals(y.get(), trapBuilding.getY());
        assertEquals(trapBuilding.getType(),"TrapBuilding");
    }

    @Test
    public void isInTrapTest() {
        PathPosition pathPosition;

        // create a path position and enemy at (0,1)
        pathPosition = new PathPosition(3, orderedPath);
        Slug basicEnemy = new Slug(pathPosition);


        assertEquals(10, trapBuilding.attack());
        assertEquals(trapBuilding.canGiveHelp(basicEnemy),false);

        pathPosition = new PathPosition(4, orderedPath);
        Slug slug = new Slug(pathPosition);


        assertEquals(trapBuilding.canGiveHelp(slug),true);

    }

}
