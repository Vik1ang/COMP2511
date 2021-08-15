package test;

import org.javatuples.Pair;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import unsw.loopmania.PathPosition;
import unsw.loopmania.basicEnemies.*;
import unsw.loopmania.character.Character;


public class EnemyNewTest {

    @Test
    public void enemyHealthTest() {
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        BasicEnemy elan = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        assertEquals(doggie.getHealth(), 80);
        assertEquals(elan.getHealth(), 60);
    }

    @Test
    public void enemyDamageTest() {
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        BasicEnemy elan = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        assertEquals(doggie.getBiteCharacter(character, false), 15);
        assertEquals(elan.getBiteCharacter(character, false), 50);
    }

    @Test
    public void enemyAliveTest() {
        ElanMuske.setNull();
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        BasicEnemy elan = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        assertEquals(doggie.checkDeath(), false);
        assertEquals(elan.checkDeath(), false);
    }

    @Test
    public void doggieDeathTest() {
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        doggie.gotAttack(80);
        assertTrue(doggie.checkDeath());
        assertEquals(0, doggie.getHealth());
        doggie.gotAttack(10);
        assertEquals(0, doggie.getHealth());
    }

    @Test
    public void elanDeathTest() {
        BasicEnemy elan = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        elan.gotAttack(60);
        assertTrue(elan.checkDeath());
        assertEquals(0, elan.getHealth());
        elan.gotAttack(20);
        assertEquals(0, elan.getHealth());
    }

    @Test
    public void doggieMoveTest() {
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        doggie.move(new ArrayList<>(), new Character(new PathPosition(0, generatePathPosition())), new ArrayList<>());
        assertEquals(0, doggie.getX());
        assertEquals(1, doggie.getY());
        doggie.move(new ArrayList<>(), new Character(new PathPosition(0, generatePathPosition())), new ArrayList<>());
        assertEquals(0, doggie.getX());
        assertEquals(1, doggie.getY());
        doggie.move(new ArrayList<>(), new Character(new PathPosition(0, generatePathPosition())), new ArrayList<>());
        assertEquals(0, doggie.getX());
        assertEquals(1, doggie.getY());
    }

    @Test
    public void elanMoveTest() {
        BasicEnemy elan = ElanMuske.getInstance(new PathPosition(0, generatePathPosition()));
        Character character = new Character(new PathPosition(0, generatePathPosition()));
        // MOVE_SPEED = 0;
        // seed0: 60 48 29 47 15 53 91 61
        elan.move(new ArrayList<>(), character, new ArrayList<>());
        assertEquals(2, elan.getX());
        assertEquals(3, elan.getY());
        elan.move(new ArrayList<>(), character, new ArrayList<>());
        assertEquals(1, elan.getX());
        assertEquals(3, elan.getY());
        elan.move(new ArrayList<>(), character, new ArrayList<>());
        assertEquals(2, elan.getX());
        assertEquals(3, elan.getY());
        elan.move(new ArrayList<>(), character, new ArrayList<>());
        assertEquals(1, elan.getX());
        assertEquals(3, elan.getY());
        elan.move(new ArrayList<>(), character, new ArrayList<>());
        assertEquals(1, elan.getX());
        assertEquals(3, elan.getY());
    }

    @Test
    public void doggieStunTest() {
        BasicEnemy doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        // seed 4: 62 52 3 58 67 5 11 46
        assertEquals(doggie.getBiteCharacter(character, false), 15);
        assertEquals(false, character.isStun());
        assertEquals(doggie.getBiteCharacter(character, false), 15);
        assertEquals(false, character.isStun());
        assertEquals(doggie.getBiteCharacter(character, false), 15);
        assertEquals(true, character.isStun());
    }

    @Test
    public void ZombieTransferRate() {
        Zombie zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        zombie.setTransferRate(20);
        assertEquals(20, zombie.getTransferRate());
    }

    @Test
    public void SlugZombieHandleDeathTest() {
        SlugZombie slugZombie = new SlugZombie(new PathPosition(1, generatePathPosition()));
        List<BasicEnemy> enemyList = new ArrayList<>();
        slugZombie.HandleDeath(enemyList);
        assertEquals(5, enemyList.size());
        for (int i = 0; i < 5; i++) {
            assertTrue(enemyList.get(i) instanceof Slug);
        }
    }

    public List<Pair<Integer, Integer>> generatePathPosition() {
        java.util.List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        Pair<Integer, Integer> pair1 = new Pair<>(0, 0);
        Pair<Integer, Integer> pair2 = new Pair<>(0, 1);
        Pair<Integer, Integer> pair3 = new Pair<>(1, 1);
        Pair<Integer, Integer> pair4 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair5 = new Pair<>(1, 3);
        Pair<Integer, Integer> pair6 = new Pair<>(2, 3);
        Pair<Integer, Integer> pair7 = new Pair<>(2, 2);
        Pair<Integer, Integer> pair8 = new Pair<>(2, 1);
        Pair<Integer, Integer> pair9 = new Pair<>(2, 0);
        Pair<Integer, Integer> pair10 = new Pair<>(1, 0);
        orderedPath.add(pair1);
        orderedPath.add(pair2);
        orderedPath.add(pair3);
        orderedPath.add(pair4);
        orderedPath.add(pair5);
        orderedPath.add(pair6);
        orderedPath.add(pair7);
        orderedPath.add(pair8);
        orderedPath.add(pair9);
        orderedPath.add(pair10);
        return orderedPath;
    }
}
