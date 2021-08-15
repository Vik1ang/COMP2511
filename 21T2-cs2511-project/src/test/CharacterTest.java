package test;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import unsw.loopmania.character.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.items.Gold;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.Items;
import unsw.loopmania.items.weapon.Sword;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CharacterTest {
    /**
     * class Character {
     * pos: {field} PathPosition
     * cycleComplete: {field} IntegerProperty
     * attack: {field} IntegerProperty
     * defense: {field} IntegerProperty
     * health: {field} IntegerProperty
     * experience: {field} IntegerProperty
     * golds: {field} IntegerProperty
     * alliesNumber: {field} IntegerProperty
     * portionList: {field} List<Portion>
     * equipmentList: {field} List<Item>
     * bag: {field} List<Item>
     * cards: {field} List<Card>
     * <p>
     * gotAttacked(IntegerProperty): {method} void
     * checkDeath(): {method} BooleanProperty
     * buyItem(IntegerProperty cost, Items item): {method} BooleanProperty
     * collectItem(): void
     * useItem(Item item): {method} boolean
     * equipEquipment(Item item): {method} boolean
     * dropEquipment(Item item): {method} boolean
     * hasShield(): {method} boolean
     * }
     */

    Character character = null;
    List<Pair<Integer, Integer>> orderedPath = initOrderedPath();

    private List<Pair<Integer, Integer>> initOrderedPath() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(0, 0));
        orderedPath.add(Pair.with(1, 1));
        orderedPath.add(Pair.with(2, 2));
        orderedPath.add(Pair.with(3, 3));
        orderedPath.add(Pair.with(4, 4));
        orderedPath.add(Pair.with(5, 5));
        return orderedPath;
    }


    @Before
    public void initSet() {
        character = new Character(new PathPosition(0, orderedPath));
        assertNotNull(character);
    }

    @Test
    public void testInitCharacter() {
        character = new Character(new PathPosition(0, initOrderedPath()));
        assertNotNull(character);
    }

    @Test
    public void testInitCharacterAttribute() {
        /*
         *     pos: {field} PathPosition  0
         *     cycleComplete: {field} IntegerProperty 0
         *     attack: {field} IntegerProperty 10
         *     defense: {field} IntegerProperty 5
         *     health: {field} IntegerProperty 50
         *     experience: {field} IntegerProperty 0
         *     golds: {field} IntegerProperty 50
         *     alliesNumber: {field} IntegerProperty 0
         *     portionList: {field} List<Portion>
         *     equipmentList: {field} List<Item>
         *     bag: {field} List<Item>
         *     cards: {field} List<Card>
         */
        assertEquals(character.getCycleComplete(), 0);
        assertEquals(character.getHealth(), 100);
        assertEquals(character.getExperience(), 0);
        assertEquals(character.getGolds(), 0);
        assertFalse(character.IsWin());
        assertFalse(character.IsDead());
        assertEquals(character.attack(), 15);
    }

    @Test
    public void testGotAttacked() {

        assertEquals(character.getHealth(), 100);
    }

    @Test
    public void testCheckDeath() {
        /*
          checkDeath(): {method} BooleanProperty
         */

        assertEquals(character.checkDeath(), false);
        character.gotBite(100);
        assertEquals(character.checkDeath(), true);
    }

    @Test
    public void testCheckWin() {
        character.setGolds(90000);
        List<Pair<String, Integer>> goals  = new ArrayList<>();
        goals.add(new Pair<>("gold",90000));
        character.checkWinStatus(100,true, goals);
        assertEquals(character.IsWin(), true);
    }


    @Test
    public void testPickHealthPotion() {
        /*
         * collectItem(Item item): void
         */

        List<Items> itemsOnGround = new ArrayList<>();
        itemsOnGround.add(new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        character.pickUpHealthPotion(itemsOnGround);
        assertEquals(character.getHealth(), 100);
    }

    @Test
    public void testPickGold() {
        /*
         * collectItem(Item item): void
         */

        List<Items> itemsOnGround = new ArrayList<>();
        itemsOnGround.add(new Gold(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)));
        character.moveDownPath();
        character.pickUpGold(itemsOnGround);
        assertEquals(character.getGolds(), 1);
    }

    @Test
    public void testExperience() {
        assertEquals(0, character.getExperience());
        character.setExperience(50);
        assertEquals(50, character.getExperience());
    }

    @Test
    public void testCharacterCycleComplete() {
        assertEquals(0, character.getCycleComplete());

        for (int i = 0; i < 6; i++) {
            character.moveDownPath();
        }

        assertEquals(0, character.getX());
        assertEquals(0, character.getY());
        character.updateCycleComplete();
        assertEquals(1, character.cycleCompleteProperty().get());

        for (int i = 0; i < 6; i++) {
            character.moveDownPath();
        }

        character.incrementOneCycleComplete();
        assertEquals(2, character.cycleCompleteProperty().get());

    }

    @Test
    public void testWinStatus() {
        character.setExperience(100000);
        List<Pair<String, Integer>> goals  = new ArrayList<>();
        goals.add(new Pair<>("experience",90000));
        character.checkWinStatus(character.getCycleComplete(),true, goals);
        assertTrue(character.IsWin());

        initSet();
        for (int i = 0; i < 110; i++) {
            character.incrementOneCycleComplete();
        }
        List<Pair<String, Integer>> newGoals  = new ArrayList<>();
        newGoals.add(new Pair<>("cycle",120));
        character.checkWinStatus(character.getCycleComplete(),true,newGoals);
        assertTrue(!character.IsWin());

        initSet();
        character.setExperience(123456);
        character.setGolds(12000);
        List<Pair<String, Integer>> newGoalsOne  = new ArrayList<>();
        newGoalsOne.add(new Pair<>("experience",123456));
        newGoalsOne.add(new Pair<>("gold",12000));
        newGoalsOne.add(new Pair<>("boss",1));
        character.checkWinStatus(character.getCycleComplete(), true, newGoalsOne);
        assertTrue(character.IsWin());

        initSet();
        character.setExperience(123456);
        character.setGolds(12000);
        List<Pair<String, Integer>> newGoal  = new ArrayList<>();
        newGoal.add(new Pair<>("experience",123456));
        newGoal.add(new Pair<>("gold",12000));
        newGoal.add(new Pair<>("boss",1));
        character.checkWinStatus(character.getCycleComplete(), false, newGoal);
        assertTrue(!character.IsWin());


        initSet();
        character.setExperience(123456);
        character.setGolds(12000);
        List<Pair<String, Integer>> newGoal2  = new ArrayList<>();
        newGoal2.add(new Pair<>("experience",123456));
        newGoal2.add(new Pair<>("gold",12000));
        newGoal2.add(new Pair<>("boss",0));
        character.checkWinStatus(character.getCycleComplete(), false, newGoal2);
        assertTrue(character.IsWin());

        initSet();
        character.setExperience(123456);
        for (int i = 0; i < 110; i++) {
            character.incrementOneCycleComplete();
        }

        List<Pair<String, Integer>> newGoalsTwo  = new ArrayList<>();
        newGoalsTwo.add(new Pair<>("experience",123457));
        character.checkWinStatus(character.getCycleComplete(), true,newGoalsTwo);
        assertTrue(!character.IsWin());

        initSet();
        character.setIsWin(true);
        assertTrue(character.IsWin());
    }

    @Test
    public void CycleCompleteTest() {
        character.setCycleComplete(100);
        assertEquals(100, character.getCycleComplete());
    }
}
