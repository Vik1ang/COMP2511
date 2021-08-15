package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import unsw.loopmania.character.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.CampFireBuilding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

public class CampFireBuildingTest {

    CampFireBuilding campFireBuilding;
    SimpleIntegerProperty x;
    SimpleIntegerProperty y;
    List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    Pair<Integer, Integer> orderPath;


    @Before
    public void setUp() {
        x = new SimpleIntegerProperty(4);
        y = new SimpleIntegerProperty(0);
        campFireBuilding = new CampFireBuilding(x, y);

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
        assertEquals(x.get(), campFireBuilding.getX());
        assertEquals(y.get(), campFireBuilding.getY());

        //reset position of building and pathTile
        x = new SimpleIntegerProperty(15);
        y = new SimpleIntegerProperty(16);

        campFireBuilding = new CampFireBuilding(x, y);
        assertEquals(x.get(), campFireBuilding.getX());
        assertEquals(y.get(), campFireBuilding.getY());
        assertEquals(campFireBuilding.getType(),"CampFireBuilding");
    }

    @Test
    public void isAtHereTest() {

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(1, orderedPath);
        character = new Character(pathPosition);
        assertFalse(campFireBuilding.canGiveHelp(character));

        // create a path position and character at (0,2)
        pathPosition = new PathPosition(2, orderedPath);
        character = new Character(pathPosition);
        assertFalse(campFireBuilding.canGiveHelp(character));

        // create a path position and character at (0,4)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertTrue(campFireBuilding.canGiveHelp(character));
    }

}
