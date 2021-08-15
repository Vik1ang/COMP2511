package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.character.Character;
import unsw.loopmania.PathPosition;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarracksBuildingTest {


    BarracksBuilding barracksBuilding;
    SimpleIntegerProperty x;
    SimpleIntegerProperty y;
    List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    Pair<Integer, Integer> orderPath;


    @Before
    public void setUp() {
        x = new SimpleIntegerProperty(4);
        y = new SimpleIntegerProperty(0);
        barracksBuilding = new BarracksBuilding(x, y);

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
        assertEquals(x.get(), barracksBuilding.getX());
        assertEquals(y.get(), barracksBuilding.getY());

        //reset position of building and pathTile
        x = new SimpleIntegerProperty(4);
        y = new SimpleIntegerProperty(0);

        barracksBuilding = new BarracksBuilding(x, y);
        assertEquals(x.get(), barracksBuilding.getX());
        assertEquals(y.get(), barracksBuilding.getY());
        assertEquals(barracksBuilding.getType(),"BarracksBuilding");
    }

    @Test
    public void isAtHereTest() {

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(1, orderedPath);
        character = new Character(pathPosition);
        assertFalse(barracksBuilding.canGiveHelp(character));
        assertEquals(barracksBuilding.canGiveHelp(character),false);

        // create a path position and character at (2,0)
        pathPosition = new PathPosition(2, orderedPath);
        character = new Character(pathPosition);
        assertFalse(barracksBuilding.canGiveHelp(character));
        assertEquals(barracksBuilding.canGiveHelp(character),false);

        // create a path position and character at (4,0)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertEquals(true, barracksBuilding.canGiveHelp(character));
        assertEquals(barracksBuilding.canGiveHelp(character),true);
    }

}
