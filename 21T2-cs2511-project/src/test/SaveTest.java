package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.basicEnemies.Vampire;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.buildings.HeroCastleBuilding;
import unsw.loopmania.buildings.ZombiePitBuilding;
import unsw.loopmania.buildings.VampireCastleBuilding;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.weapon.Sword;
import unsw.loopmania.basicEnemies.Slug;
import unsw.loopmania.basicEnemies.Zombie;
import unsw.loopmania.card.BarracksCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveTest {

    public List<Pair<Integer, Integer>> initOrderedPath() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(1, 1));
        orderedPath.add(Pair.with(1, 2));
        orderedPath.add(Pair.with(1, 3));
        orderedPath.add(Pair.with(1, 4));
        orderedPath.add(Pair.with(1, 5));
        orderedPath.add(Pair.with(2, 5));
        orderedPath.add(Pair.with(2, 4));
        orderedPath.add(Pair.with(2, 3));
        orderedPath.add(Pair.with(2, 2));
        orderedPath.add(Pair.with(2, 1));
        orderedPath.add(Pair.with(3, 1));
        orderedPath.add(Pair.with(3, 2));
        orderedPath.add(Pair.with(3, 3));
        orderedPath.add(Pair.with(3, 4));
        orderedPath.add(Pair.with(3, 5));
        orderedPath.add(Pair.with(4, 5));
        orderedPath.add(Pair.with(4, 3));
        orderedPath.add(Pair.with(4, 2));
        orderedPath.add(Pair.with(4, 1));
        return orderedPath;
    }

    @Test
    public void saveBuildingTest () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        //new vampirecastle
        VampireCastleBuilding vampireCastleBuilding = new VampireCastleBuilding(new SimpleIntegerProperty(4),new SimpleIntegerProperty(0));
        loopmania.getBuildingEntities().add(vampireCastleBuilding);
        //new zombiepit
        ZombiePitBuilding zombiePitBuilding = new ZombiePitBuilding(new SimpleIntegerProperty(4),new SimpleIntegerProperty(1));
        loopmania.getBuildingEntities().add(zombiePitBuilding);
        BarracksBuilding barracksBuilding = new BarracksBuilding(new SimpleIntegerProperty(5), new SimpleIntegerProperty(3));
        loopmania.getBuildingEntities().add(barracksBuilding);
        List<HashMap<String, String>> returnBuildings = loopmania.saveBuildings();
        assertEquals(returnBuildings.get(0).get("x"),"4");
        assertEquals(returnBuildings.get(0).get("y"),"0");
        assertEquals(returnBuildings.get(0).get("type"),"VampireCastleBuilding");
        assertEquals(returnBuildings.get(0).get("cycleComplete"),"5");
    }

    @Test
    public void saveGameModeTest () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania.setHeroCastleBuilding(new HeroCastleBuilding(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        loopmania.setMode("standard");
        assertEquals("standard",loopmania.saveGameMode());
    }

    @Test
    public void saveEquippedItemsTest () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        //new Armour
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Armour armour2 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2));
        Helmet helmet2 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(3));
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(4));
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(5));
        loopmania.getUnequippedInventoryItems().add(armour1);
        loopmania.getUnequippedInventoryItems().add(armour2);
        loopmania.getUnequippedInventoryItems().add(helmet1);
        loopmania.getUnequippedInventoryItems().add(helmet2);
        loopmania.getUnequippedInventoryItems().add(shield);
        loopmania.getUnequippedInventoryItems().add(sword);
        //null items
        loopmania.unequippedToEquipped(5,5,5,5);
        loopmania.unequippedToEquipped(0,0,1,1);
        loopmania.unequippedToEquipped(0,1,1,1);
        loopmania.unequippedToEquipped(0,2,1,0);
        loopmania.unequippedToEquipped(0,3,1,0);
        loopmania.unequippedToEquipped(0,4,2,1);
        loopmania.unequippedToEquipped(0,5,1,1);

        List<HashMap<String, String>> returnEquipped = loopmania.saveEquippedItems();

        assertEquals(returnEquipped.get(0).get("x"),"1");
        assertEquals(returnEquipped.get(0).get("y"),"1");
        assertEquals(returnEquipped.get(0).get("type"),"Armour");
        assertEquals(returnEquipped.get(0).get("equipped"),"true");

    }

    @Test
    public void saveEquippedItemsTest2 () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        List<HashMap<String, String>> returnEquipped = loopmania.saveEquippedItems();
        assertEquals(0,returnEquipped.size());
    }
    @Test
    public void saveUnEquippedItemsTest () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        //new Armour
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Armour armour2 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2));
        Helmet helmet2 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(3));
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(4));
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(5));
        loopmania.getUnequippedInventoryItems().add(armour1);
        loopmania.getUnequippedInventoryItems().add(armour2);
        loopmania.getUnequippedInventoryItems().add(helmet1);
        loopmania.getUnequippedInventoryItems().add(helmet2);
        loopmania.getUnequippedInventoryItems().add(shield);
        loopmania.getUnequippedInventoryItems().add(sword);

        List<HashMap<String, String>> returnEquipped = loopmania.saveUnEquippedItems();

        assertEquals(returnEquipped.get(0).get("x"),"0");
        assertEquals(returnEquipped.get(0).get("y"),"0");
        assertEquals(returnEquipped.get(0).get("type"),"Armour");
        assertEquals(returnEquipped.get(0).get("equipped"),"false");

    }

    public List<Pair<Integer, Integer>> generatePathPosition() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 1);
        Pair<Integer, Integer> pair2 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair3 = new Pair<>(1, 3);
        Pair<Integer, Integer> pair4 = new Pair<>(2, 3);
        Pair<Integer, Integer> pair5 = new Pair<>(2, 2);
        Pair<Integer, Integer> pair6 = new Pair<>(2, 1);
        orderedPath.add(pair1);
        orderedPath.add(pair2);
        orderedPath.add(pair3);
        orderedPath.add(pair4);
        orderedPath.add(pair5);
        orderedPath.add(pair6);
        return orderedPath;
    }

    @Test
    public void saveEnemiesTest () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        //new enemies
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));

        loopmania.getEnemies().add(slug);
        loopmania.getEnemies().add(vampire);
        loopmania.getEnemies().add(zombie);

        List<HashMap<String, String>> returnEnemies = loopmania.saveEnemies();

        assertEquals(returnEnemies.get(0).get("x"),"1");
        assertEquals(returnEnemies.get(0).get("y"),"2");
        assertEquals(returnEnemies.get(0).get("type"),"Slug");

    }

    @Test
    public void saveCardTest() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        BarracksCard card1 = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        BarracksCard card2 = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2));
        loopmania.getCardEntities().add(card1);
        loopmania.getCardEntities().add(card2);
        List<HashMap<String, String>> returnCards =  loopmania.saveCards();
        assertEquals(returnCards.get(0).get("x"),"0");
        assertEquals(returnCards.get(0).get("y"),"1");
        assertEquals(returnCards.get(0).get("type"),"BarracksCard");

    }
}

