package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.basicEnemies.Slug;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.buildings.HeroCastleBuilding;
import unsw.loopmania.card.BarracksCard;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.weapon.Sword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static unsw.loopmania.WORLD_POSITION_TYPE.*;

public class LoadTest {
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
    public void laodGameModeTest () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania.setHeroCastleBuilding(new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        loopmania.loadGameMode("standard", "MEDIUM");
        assertEquals(loopmania.getHeroCastleBuilding().getMode(),"standard");
        assertEquals("MEDIUM",loopmania.getElanMode());
    }

    @Test
    public void loadBuildingTest () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania.initialLoad(new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        loopmania.initialLoad(new BarracksBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1)));
        assertEquals(loopmania.getBuildingEntities().get(0).getType(),"HeroCastleBuilding");
        assertEquals(HERO_CASTLE_BUILDING,loopmania.getWorldPositionType()[0][0]);
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
    public void loadEnemiesTest () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        //new enemies
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        loopmania.initialLoad(slug);
        assertTrue(loopmania.getEnemies().get(0) instanceof Slug);
        assertEquals(SLUG,loopmania.getWorldPositionType()[1][2]);
    }

    @Test
    public void loadCardTest () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        //new enemies
        BarracksCard barracksCard = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        loopmania.initialLoad(barracksCard);
        assertTrue(loopmania.getCardEntities().get(0) instanceof BarracksCard);
        assertEquals(BARRACKS_CARD,loopmania.getWorldPositionType()[0][0]);
    }

    @Test
    public void loadUnequippedTest () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        loopmania.initialLoad(armour1);
        assertTrue(loopmania.getUnequippedInventoryItems().get(0) instanceof Armour);
        assertEquals(ARMOUR,loopmania.getWorldPositionType()[0][0]);
    }

    @Test
    public void loadEquippedTest () {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2));
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(4));
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(5));
        loopmania.loadEquippedItems(armour1);
        assertTrue(loopmania.getEquippedItems().get(0) instanceof Armour);
        assertEquals(ARMOUR,loopmania.getWorldPositionType()[0][0]);

        loopmania.loadEquippedItems(helmet1);
        assertTrue(loopmania.getEquippedItems().get(1) instanceof Helmet);
        assertEquals(HELMET,loopmania.getWorldPositionType()[0][2]);

        loopmania.loadEquippedItems(shield);
        assertTrue(loopmania.getEquippedItems().get(2) instanceof Shield);
        assertEquals(SHIELD,loopmania.getWorldPositionType()[0][4]);

        loopmania.loadEquippedItems(sword);
        assertTrue(loopmania.getEquippedItems().get(3) instanceof Sword);
        assertEquals(SWORD,loopmania.getWorldPositionType()[0][5]);
    }

    @Test
    public void loadCharacterInfo() {
        Map<String,Integer> charac = new HashMap<>();
        charac.put("gold_number", 10);
        charac.put("potion_number", 10);
        charac.put("health", 10);
        charac.put("experience", 10);
        charac.put("cycle_complete", 10);
        charac.put("allies_number", 10);

        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania.loadCharacterInfo(charac);
        assertEquals(loopmania.getCharacter().getGolds(),10);
        assertEquals(loopmania.getEquippedHealthPotionsNum().get(),10);
        assertEquals(loopmania.getCharacter().getExperience(),10);
        assertEquals(loopmania.getCharacter().getHealth(),10);
        assertEquals(loopmania.getCycleComplete(),10);
        assertEquals(loopmania.getAlliedSoldierNum().get(),10);

    }

    @Test
    public void loadJson() {
        JSONObject json = new JSONObject();
        json.put("test1", "value1");
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania.saveInitialPathInfo(json);
        assertEquals(json, loopmania.getInitialPathInfo());
        assertEquals(initOrderedPath(),loopmania.getOrderedPath());
    }

}
