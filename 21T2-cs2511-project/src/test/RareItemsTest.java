package test;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.Test;
import org.javatuples.Pair;


import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.basicEnemies.*;
import unsw.loopmania.items.Items;
import unsw.loopmania.items.rare.AndurilFlameOfTheWest;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.rare.TreeStump;

import static org.junit.jupiter.api.Assertions.*;


public class RareItemsTest {


    @Test
    public void SingleAndruilWithBasicEnemyTest () {
        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        assertEquals(5, andruil.getAttackToEnemy(slug));
        assertEquals(5, andruil.getAttackToEnemy(vampire));
        assertEquals(5, andruil.getAttackToEnemy(zombie));
        assertEquals(5, andruil.getAttackToEnemy(doggie));
        assertEquals(10, andruil.damageDefend(10, slug));
        assertEquals(10, andruil.damageDefend(10, vampire));
        assertEquals(10, andruil.damageDefend(10, zombie));
        assertEquals(10, andruil.damageDefend(10, doggie));
    }

    @Test
    public void SingleAndruilWithBossTest () {
        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        ElanMuske.setNull();
        BasicEnemy elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        assertEquals(15, andruil.getAttackToEnemy(elanMuske));
        assertEquals(10, andruil.damageDefend(10, elanMuske));
        ElanMuske.setNull();
    }

    @Test
    public void SingleAndruilHealthTest () {
        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
       assertEquals(0, andruil.getHealthRefill());
    }

    @Test
    public void SingleTreeStumpWithBasicEnemyTest () {
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        assertEquals(0, treeStump.getAttackToEnemy(slug));
        assertEquals(0, treeStump.getAttackToEnemy(vampire));
        assertEquals(0, treeStump.getAttackToEnemy(zombie));
        assertEquals(0, treeStump.getAttackToEnemy(doggie));
        assertEquals(8, treeStump.damageDefend(10, slug));
        assertEquals(8, treeStump.damageDefend(10, vampire));
        assertEquals(8, treeStump.damageDefend(10, zombie));
        assertEquals(8, treeStump.damageDefend(10, doggie));
    }

    @Test
    public void SingleTreeStumpWithBossTest () {
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        ElanMuske.setNull();
        BasicEnemy elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        assertEquals(0, treeStump.getAttackToEnemy(elanMuske));
        assertEquals(6, treeStump.damageDefend(10, elanMuske));
        ElanMuske.setNull();
    }

    @Test
    public void SingleTreeStumpHealthTest () {
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        assertEquals(0, treeStump.getHealthRefill());
    }

    @Test
    public void SingleTheOneRingWithBasicEnemyTest () {
        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        assertEquals(0, theOneRing.getAttackToEnemy(slug));
        assertEquals(0, theOneRing.getAttackToEnemy(vampire));
        assertEquals(0, theOneRing.getAttackToEnemy(zombie));
        assertEquals(0, theOneRing.getAttackToEnemy(doggie));
        assertEquals(10, theOneRing.damageDefend(10, slug));
        assertEquals(10, theOneRing.damageDefend(10, vampire));
        assertEquals(10, theOneRing.damageDefend(10, zombie));
        assertEquals(10, theOneRing.damageDefend(10, doggie));
    }

    @Test
    public void SingleTheOneRingWithBossTest () {
        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        ElanMuske.setNull();
        BasicEnemy elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        assertEquals(0, theOneRing.getAttackToEnemy(elanMuske));
        assertEquals(10, theOneRing.damageDefend(10, elanMuske));
        ElanMuske.setNull();
    }

    @Test
    public void SingleTheOneRingHealthTest () {
        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        assertEquals(100, theOneRing.getHealthRefill());
    }

    @Test
    public void AndruilTreeStumpTest () {
        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        andruil.setSecondaryAbility(new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        ElanMuske.setNull();
        BasicEnemy elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        assertEquals(5, andruil.getAttackToEnemy(slug));
        assertEquals(5, andruil.getAttackToEnemy(vampire));
        assertEquals(5, andruil.getAttackToEnemy(zombie));
        assertEquals(5, andruil.getAttackToEnemy(doggie));
        assertEquals(15, andruil.getAttackToEnemy(elanMuske));
        assertEquals(8, andruil.damageDefend(10, slug));
        assertEquals(8, andruil.damageDefend(10, vampire));
        assertEquals(8, andruil.damageDefend(10, zombie));
        assertEquals(8, andruil.damageDefend(10, doggie));
        assertEquals(6, andruil.damageDefend(10, elanMuske));
        assertEquals(0, andruil.getHealthRefill());
        ElanMuske.setNull();
    }



    @Test
    public void TreeStumpAndruilTest () {
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        ElanMuske.setNull();
        BasicEnemy elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        assertEquals(5, treeStump.getAttackToEnemy(slug));
        assertEquals(5, treeStump.getAttackToEnemy(vampire));
        assertEquals(5, treeStump.getAttackToEnemy(zombie));
        assertEquals(5, treeStump.getAttackToEnemy(doggie));
        assertEquals(15, treeStump.getAttackToEnemy(elanMuske));
        assertEquals(8, treeStump.damageDefend(10, slug));
        assertEquals(8, treeStump.damageDefend(10, vampire));
        assertEquals(8, treeStump.damageDefend(10, zombie));
        assertEquals(8, treeStump.damageDefend(10, doggie));
        assertEquals(6, treeStump.damageDefend(10, elanMuske));
        assertEquals(0, treeStump.getHealthRefill());
        ElanMuske.setNull();
    }

    @Test
    public void TreeStumpHealthTest () {
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        ElanMuske.setNull();
        BasicEnemy elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        assertEquals(0, treeStump.getAttackToEnemy(slug));
        assertEquals(0, treeStump.getAttackToEnemy(vampire));
        assertEquals(0, treeStump.getAttackToEnemy(zombie));
        assertEquals(0, treeStump.getAttackToEnemy(doggie));
        assertEquals(0, treeStump.getAttackToEnemy(elanMuske));
        assertEquals(8, treeStump.damageDefend(10, slug));
        assertEquals(8, treeStump.damageDefend(10, vampire));
        assertEquals(8, treeStump.damageDefend(10, zombie));
        assertEquals(8, treeStump.damageDefend(10, doggie));
        assertEquals(6, treeStump.damageDefend(10, elanMuske));
        ElanMuske.setNull();
        assertEquals(100, treeStump.getHealthRefill());
        assertEquals(100, treeStump.getHealthRefill());
    }

    @Test
    public void HealthTreeStumpTest () {
        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        theOneRing.setSecondaryAbility(new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        ElanMuske.setNull();
        BasicEnemy elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        assertEquals(0, theOneRing.getAttackToEnemy(slug));
        assertEquals(0, theOneRing.getAttackToEnemy(vampire));
        assertEquals(0, theOneRing.getAttackToEnemy(zombie));
        assertEquals(0, theOneRing.getAttackToEnemy(doggie));
        assertEquals(0, theOneRing.getAttackToEnemy(elanMuske));
        assertEquals(8, theOneRing.damageDefend(10, slug));
        assertEquals(8, theOneRing.damageDefend(10, vampire));
        assertEquals(8, theOneRing.damageDefend(10, zombie));
        assertEquals(8, theOneRing.damageDefend(10, doggie));
        assertEquals(6, theOneRing.damageDefend(10, elanMuske));
        ElanMuske.setNull();
        assertEquals(100, theOneRing.getHealthRefill());
        assertEquals(100, theOneRing.getHealthRefill());
    }

    @Test
    public void AndruilHealthTest () {
        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        andruil.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        ElanMuske.setNull();
        BasicEnemy elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        assertEquals(5, andruil.getAttackToEnemy(slug));
        assertEquals(5, andruil.getAttackToEnemy(vampire));
        assertEquals(5, andruil.getAttackToEnemy(zombie));
        assertEquals(5, andruil.getAttackToEnemy(doggie));
        assertEquals(15, andruil.getAttackToEnemy(elanMuske));
        assertEquals(10, andruil.damageDefend(10, slug));
        assertEquals(10, andruil.damageDefend(10, vampire));
        assertEquals(10, andruil.damageDefend(10, zombie));
        assertEquals(10, andruil.damageDefend(10, doggie));
        assertEquals(10, andruil.damageDefend(10, elanMuske));
        ElanMuske.setNull();
        assertEquals(100, andruil.getHealthRefill());
        assertEquals(100, andruil.getHealthRefill());
    }

    @Test
    public void RareIsWeaponSingleTest() {

        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        assertEquals(false, treeStump.isWeapon());

        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        assertEquals(true, andruil.isWeapon());

        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        assertEquals(false, theOneRing.isWeapon());
    }

    @Test
    public void RareIsShieldSingleTest() {

        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        assertEquals(true, treeStump.isShield());

        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        assertEquals(false, andruil.isShield());

        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        assertEquals(false, theOneRing.isShield());
    }

    @Test
    public void RareIsWeaponCombineTest() {

        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        assertEquals(true, treeStump.isWeapon());

        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        andruil.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        assertEquals(true, andruil.isWeapon());

        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        assertEquals(false, theOneRing.isWeapon());

        TreeStump treeStump2 = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        assertEquals(false, treeStump2.isWeapon());
    }

    @Test
    public void RareIsShieldCombineTest() {

        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        assertEquals(true, treeStump.isShield());

        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        andruil.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        assertEquals(false, andruil.isShield());

        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        assertEquals(false, theOneRing.isShield());

        TreeStump treeStump2 = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        assertEquals(true, treeStump2.isShield());
    }

    @Test
    public void RareCheckPlaceCorrectTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, generatePathPosition());
        List<Items> unequippedItems = world.getUnequippedInventoryItems();

        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        treeStump.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));

        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        theOneRing.setSecondaryAbility(new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));

        TheOneRing theOneRing2 = new TheOneRing(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2));
        theOneRing2.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));

        TreeStump treeStump2 = new TreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(3));
        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(0), new SimpleIntegerProperty(4));

        unequippedItems.add(treeStump);
        unequippedItems.add(theOneRing);
        unequippedItems.add(theOneRing2);
        unequippedItems.add(treeStump2);
        unequippedItems.add(andruil);

        assertTrue(world.checkIfPlacedToCorrectSlot(0, 0, 2, 1));
        assertTrue(world.checkIfPlacedToCorrectSlot(0, 1, 2, 1));
        assertTrue(!world.checkIfPlacedToCorrectSlot(0, 2, 2, 1));
        assertTrue(world.checkIfPlacedToCorrectSlot(0, 3, 2, 1));
        assertTrue(!world.checkIfPlacedToCorrectSlot(0, 4, 2, 1));

        assertTrue(world.checkIfPlacedToCorrectSlot(0, 0, 0, 1));
        assertTrue(!world.checkIfPlacedToCorrectSlot(0, 1, 0, 1));
        assertTrue(world.checkIfPlacedToCorrectSlot(0, 2, 0, 1));
        assertTrue(!world.checkIfPlacedToCorrectSlot(0, 3, 0, 1));
        assertTrue(world.checkIfPlacedToCorrectSlot(0, 4, 0, 1));
    }



    @Test
    public void RareUnequippedToEquippedShieldTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, generatePathPosition());
        List<Items> unequippedItems = world.getUnequippedInventoryItems();
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        treeStump.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        theOneRing.setSecondaryAbility(new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        unequippedItems.add(treeStump);
        unequippedItems.add(theOneRing);

        world.unequippedToEquipped(0, 1, 2, 1);
        assertTrue(world.getEquippedItems().contains(theOneRing));

        world.unequippedToEquipped(0, 0, 2, 1);
        assertTrue(world.getEquippedItems().contains(treeStump));
        assertTrue(!world.getEquippedItems().contains(theOneRing));
    }

    @Test
    public void RareUnequippedToEquippedWeaponTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, generatePathPosition());
        List<Items> unequippedItems = world.getUnequippedInventoryItems();
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        treeStump.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        AndurilFlameOfTheWest andurilFlameOfTheWest = new AndurilFlameOfTheWest(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        andurilFlameOfTheWest.setSecondaryAbility(new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        unequippedItems.add(treeStump);
        unequippedItems.add(andurilFlameOfTheWest);

        world.unequippedToEquipped(0, 1, 0, 1);
        assertTrue(world.getEquippedItems().contains(andurilFlameOfTheWest));

        world.unequippedToEquipped(0, 0, 0, 1);
        assertTrue(world.getEquippedItems().contains(treeStump));
        assertTrue(!world.getEquippedItems().contains(andurilFlameOfTheWest));
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
}
