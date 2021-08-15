package test;


import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import unsw.loopmania.Entity;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.alliedSoldiers.AlliedSoldier;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.basicEnemies.ElanMuske;
import unsw.loopmania.basicEnemies.Slug;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.buildings.HeroCastleBuilding;
import unsw.loopmania.buildings.VillageBuilding;
import unsw.loopmania.card.BarracksCard;
import unsw.loopmania.card.Card;
import unsw.loopmania.card.TrapCard;
import unsw.loopmania.items.DoggieCoin;
import unsw.loopmania.items.Gold;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.weapon.Sword;
import unsw.loopmania.items.weapon.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static unsw.loopmania.WORLD_POSITION_TYPE.*;



public class LoopManiaWorldTest {

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
    public void checkWinTest() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania.getCharacter().setGolds(90000);
        List<Pair<String, Integer>> goals  = new ArrayList<>();
        goals.add(new Pair<>("gold",90000));
        loopmania.setGoals(goals);
        loopmania.setBossIsDead(true);
        loopmania.checkCharacterWin();
        assertTrue(loopmania.getCharacter().IsWin());
        assertEquals(loopmania.getGoals().size(), 1);

        assertEquals(loopmania.getCycleCompleteProperty().get(), 0);
        assertEquals(0,loopmania.getRareList().size());
        assertEquals(100,loopmania.getCharacterHealth());

    }

    @Test
    public void checkCanPlaceHere() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
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

        assertFalse(loopmania.checkIfPlacedToCorrectSlot(0,0,2,1));
        assertFalse(loopmania.checkIfPlacedToCorrectSlot(0,0,2,2));

        assertFalse(loopmania.checkIfPlacedToCorrectSlot(0,4,0,1));
        assertFalse(loopmania.checkIfPlacedToCorrectSlot(0,4,1,5));

        assertTrue(loopmania.checkIfPlacedToCorrectSlot(0,2,1,0));
        assertTrue(loopmania.checkIfPlacedToCorrectSlot(0,1,1,1));
        assertEquals(false,loopmania.checkIfPlacedToCorrectSlot(5,5,1,1));

    }

    @Test
    public void interactWithBuildings() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania.getBuildingEntities().add(new HeroCastleBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)));
        loopmania.setElanMode("MEDIUM");
        loopmania.addUnequippedItems("DoggieCoin");
        loopmania.addUnequippedItems("Armour");
        assertEquals(loopmania.getUnequippedInventoryItems().get(0).getType(),"DoggieCoin");
        assertTrue(loopmania.interactWithBuildings());
        loopmania.removeUnequippedInventoryItemByCoordinates(5,5);

        //
        LoopManiaWorld loopmania2 = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania2.getBuildingEntities().add(new VillageBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)));
        loopmania2.getBuildingEntities().add(new VillageBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(2)));
        loopmania.getBuildingEntities().add(new HeroCastleBuilding(new SimpleIntegerProperty(3), new SimpleIntegerProperty(2)));
        loopmania2.getCharacter().x().set(1);
        loopmania2.getCharacter().y().set(1);
        loopmania2.getCharacter().setHealth(0);
        assertEquals(false, loopmania2.interactWithBuildings());
        assertEquals(5,loopmania2.getCharacter().getHealth());
        loopmania2.getCharacter().x().set(3);
        loopmania2.getCharacter().y().set(1);
        assertEquals(false, loopmania2.interactWithBuildings());


    }

    @Test
    public void shiftCardsTest() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        Card trapCard1 = new TrapCard(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        Card trapCard2 = new TrapCard(new SimpleIntegerProperty(1),new SimpleIntegerProperty(0));
        Card trapCard3 = new TrapCard(new SimpleIntegerProperty(2),new SimpleIntegerProperty(0));
        Card trapCard4 = new TrapCard(new SimpleIntegerProperty(3),new SimpleIntegerProperty(0));
        loopmania.getCardEntities().add(trapCard1);
        loopmania.getCardEntities().add(trapCard2);
        loopmania.getCardEntities().add(trapCard3);
        loopmania.getCardEntities().add(trapCard4);
        loopmania.shiftCardsDownFromXCoordinate(1);
        assertEquals(0,loopmania.getCardEntities().get(1).getX());
        assertEquals(1,loopmania.getCardEntities().get(2).getX());

    }

    @Test
    public void shiftItemsTest() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0),new SimpleIntegerProperty(1));
        Helmet helmet2 = new Helmet(new SimpleIntegerProperty(0),new SimpleIntegerProperty(2));

        loopmania.getUnequippedInventoryItems().add(helmet1);
        loopmania.getUnequippedInventoryItems().add(helmet2);
        loopmania.shiftItemsFront(0,2);
    }

    @Test
    public void initNewCycle() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania.setCycleComplete(40);
        loopmania.getCharacter().setExperience(20001);
        List<Entity> entity = loopmania.initNewCycleComplete();
    }

    @Test
    public void initHeroCastle() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        BarracksBuilding barracksBuilding = new BarracksBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(2));
        HeroCastleBuilding heroCastleBuilding = new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        loopmania.getBuildingEntities().add(barracksBuilding);
        loopmania.getBuildingEntities().add(heroCastleBuilding);
        assertEquals(heroCastleBuilding,loopmania.getHeroCastle());
        loopmania.addEntity(barracksBuilding);
    }

    @Test
    public void convertCardToBuilding() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        BarracksCard barracksCard = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        loopmania.getCardEntities().add(barracksCard);
    }

    @Test
    public void checkIfPlaceHere() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2));
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(4));
        Weapon sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(5));
        loopmania.getUnequippedInventoryItems().add(armour1);
        loopmania.getUnequippedInventoryItems().add(helmet1);
        loopmania.getUnequippedInventoryItems().add(shield);
        loopmania.getUnequippedInventoryItems().add(sword);
        assertEquals(false,loopmania.checkIfPlacedToCorrectSlot(0,0,0,0));
        assertEquals(false,loopmania.checkIfPlacedToCorrectSlot(0,0,2,1));
        assertEquals(false,loopmania.checkIfPlacedToCorrectSlot(0,0,0,1));
        assertEquals(false,loopmania.checkIfPlacedToCorrectSlot(0,0,1,0));
        assertEquals(false,loopmania.checkIfPlacedToCorrectSlot(0,5,1,1));

        loopmania.unequippedToEquipped(0,0,0,1);
        loopmania.unequippedToEquipped(0,0,2,2);
        loopmania.unequippedToEquipped(0,0,1,0);
        loopmania.unequippedToEquipped(0,0,2,1);
        loopmania.unequippedToEquipped(0,5,0,1);
        List<HashMap<String,String>> returedEquipped = loopmania.saveEquippedItems();
        assertEquals(1,returedEquipped.size());

        BarracksCard barracksCard = new BarracksCard(new SimpleIntegerProperty(2), new SimpleIntegerProperty(2));
        loopmania.getCardEntities().add(barracksCard);
        assertEquals(false, loopmania.canplaceCard(0,0,0,0));
        assertEquals(false, loopmania.canplaceCard(2,0,0,0));
        loopmania.sellItem(5,5);
    }


    @Test
    public void canCompleteCycle() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania.getGoldOnGround().add(new Gold(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        loopmania.getGoldOnGround().add(new Gold(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        loopmania.getGoldOnGround().add(new Gold(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        loopmania.getEquippedHealthPotionsNum().set(5);
        loopmania.setCycleComplete(2);
        List<Entity> newEntity = loopmania.initNewCycleComplete();

    }

    @Test
    public void getEnemyPossition() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        BasicEnemy slug1 = new Slug(new PathPosition(3, generatePathPosition()));
        BasicEnemy slug2 = new Slug(new PathPosition(4, generatePathPosition()));
        loopmania.getEnemies().add(slug1);
        loopmania.getEnemies().add(slug2);
        assertEquals(null,loopmania.possiblyGetBasicEnemySpawnPosition());

    }

    @Test
    public void canPlaceCard() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        TrapCard trapCard = new TrapCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        loopmania.getCardEntities().add(trapCard);
        assertEquals(false, loopmania.canplaceCard(0,1,1,1));

    }

    @Test
    public void alliedSoldierTest() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        BasicEnemy slug1 = new Slug(new PathPosition(3, generatePathPosition()));
//        BasicEnemy slug2 = new Slug(new PathPosition(4, generatePathPosition()));
//        AlliedSoldier allied = new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
//        loopmania.getAlliedSoldiers().add(allied);
//        loopmania.getEnemies().add(slug1);
//        loopmania.getEnemies().add(slug2);
        loopmania.alliedSolderAndEnemyBattle(slug1);
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        loopmania.getUnequippedInventoryItems().add(armour1);
        loopmania.sellItem(0,3);

    }

    @Test
    public void shiftItemstest() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Armour armour2 = new Armour(new SimpleIntegerProperty(1), new SimpleIntegerProperty(0));
        loopmania.getUnequippedInventoryItems().add(armour1);
        loopmania.getUnequippedInventoryItems().add(armour2);
        loopmania.shiftItemsFront(1,0);
    }

    @Test
    public void convertCardtest() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        BarracksCard barracksCard = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        BarracksCard barracksCard1 = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2));
        loopmania.getCardEntities().add(barracksCard);
        loopmania.getCardEntities().add(barracksCard1);
        assertTrue(loopmania.convertCardToBuildingByCoordinates(0,2,1,2) instanceof BarracksBuilding);
    }

    @Test
    public void unequippedToEquipped() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2));
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(4));
        Weapon sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(5));
        loopmania.getUnequippedInventoryItems().add(armour1);
        loopmania.getUnequippedInventoryItems().add(helmet1);
        loopmania.getUnequippedInventoryItems().add(shield);
        loopmania.getUnequippedInventoryItems().add(sword);
        loopmania.unequippedToEquipped(0,0,0,4);
        loopmania.unequippedToEquipped(0,0,1,4);
        loopmania.unequippedToEquipped(0,0,2,4);
    }

    @Test
    public void runTickMove() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania.getUnequippedInventoryItems().add(new DoggieCoin(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        loopmania.getUnequippedInventoryItems().add(new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1)));
        loopmania.runTickMoves();
    }

}
