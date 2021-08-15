package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import unsw.loopmania.character.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

public class VillageBuildingTest {


    VillageBuilding villageBuilding;
    SimpleIntegerProperty x;
    SimpleIntegerProperty y;
    List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    Pair<Integer, Integer> orderPath;


    @Before
    public void setUp() {
        x = new SimpleIntegerProperty(4);
        y = new SimpleIntegerProperty(0);
        villageBuilding = new VillageBuilding(x, y);

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
        assertEquals(x.get(), villageBuilding.getX());
        assertEquals(y.get(), villageBuilding.getY());

        //reset position of building and pathTile
        x = new SimpleIntegerProperty(15);
        y = new SimpleIntegerProperty(16);

        villageBuilding = new VillageBuilding(x, y);
        assertEquals(x.get(), villageBuilding.getX());
        assertEquals(y.get(), villageBuilding.getY());
    }

    @Test
    public void isAtHereTest() {

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(1, orderedPath);
        character = new Character(pathPosition);
        assertFalse(villageBuilding.canGiveHelp(character));

        // create a path position and character at (0,2)
        pathPosition = new PathPosition(2, orderedPath);
        character = new Character(pathPosition);
        assertFalse(villageBuilding.canGiveHelp(character));

        // create a path position and character at (0,4)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertTrue(villageBuilding.canGiveHelp(character));
    }

    @Test
    public void isNotAtTest() {
        // create a path position and character
        PathPosition pathPosition;
        Character character;
        // create a path position and character at (0,2)
        pathPosition = new PathPosition(2, orderedPath);
        character = new Character(pathPosition);
        assertFalse(villageBuilding.canGiveHelp(character));
        assertEquals(false, villageBuilding.canGiveHelp(character));
        assertEquals(5,villageBuilding.getHealthRefill());
    }

    @Test
    public void isAtVillage() {
        // create a path position and character
        PathPosition pathPosition;
        Character character;
        // create a path position and character at (0,2)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertTrue(villageBuilding.canGiveHelp(character));
        assertEquals(true, villageBuilding.canGiveHelp(character));
        assertEquals(5,villageBuilding.getHealthRefill());
    }


}
