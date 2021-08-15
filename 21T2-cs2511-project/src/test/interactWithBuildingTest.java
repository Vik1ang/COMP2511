package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Test;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.basicEnemies.Slug;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.HeroCastleBuilding;

import unsw.loopmania.buildings.VillageBuilding;
import unsw.loopmania.character.Character;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class interactWithBuildingTest {

    public List<Pair<Integer, Integer>> initOrderedPath() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(0, 1));
        orderedPath.add(Pair.with(1, 1));
        orderedPath.add(Pair.with(2, 1));
        orderedPath.add(Pair.with(2, 2));
        orderedPath.add(Pair.with(2, 3));
        orderedPath.add(Pair.with(1, 3));
        orderedPath.add(Pair.with(0, 3));
        orderedPath.add(Pair.with(0, 2));
        return orderedPath;
    }

    @Test
    public void testBarracksBuilding() {
        // set up
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        Building b = new BarracksBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(0));
        Character character = world.getCharacter();
        List<Building> buildingEntities = world.getBuildingEntities();
        // add a BarracksBuilding
        buildingEntities.add(b);
        // check current allied soldier
        assertEquals(0, world.getAlliedSoldierNum().get());
        // move character
        character.moveDownPath();
        world.interactWithBuildings();
        // check current allied soldier
        assertEquals(0, world.getAlliedSoldierNum().get());
        // move character to BarracksBuilding
        character.moveDownPath();
        world.interactWithBuildings();
        // check current allied soldier
        assertEquals(0, world.getAlliedSoldierNum().get());
        // move character
        character.moveDownPath();
        world.interactWithBuildings();
        // check nextTo
        assertEquals(0, world.getAlliedSoldierNum().get());
        character.moveDownPath();
        world.interactWithBuildings();
        // check in
        character.moveUpPath();
        character.moveUpPath();
        Building c = new BarracksBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(1));
        buildingEntities.add(c);
        world.interactWithBuildings();
        assertEquals(1, world.getAlliedSoldierNum().get());
    }


    @Test
    public void testVillageBuilding() {
        // set up
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        Building b = new VillageBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2));
        Building b2 = new VillageBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        Building b3 = new VillageBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(1));
        Character character = world.getCharacter();
        List<Building> buildingEntities = world.getBuildingEntities();
        List<BasicEnemy> enemies = world.getEnemies();
        // add a VillageBuilding
        buildingEntities.add(b);
        buildingEntities.add(b2);
        buildingEntities.add(b3);
        // add enemies
        enemies.add(new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add(new Slug(new PathPosition(1, initOrderedPath())));
        // check current allied soldier
        assertEquals(0, world.getAlliedSoldierNum().get());
        // move character
        character.moveDownPath();
        world.runBattles();
        world.interactWithBuildings();
        // check health
        assertEquals(80, character.getHealth());
        // move character
        character.moveDownPath();
        world.runBattles();
        world.interactWithBuildings();
        // check health
        assertEquals(85, character.getHealth());
    }
}
