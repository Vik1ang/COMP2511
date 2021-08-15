package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.alliedSoldiers.AlliedSoldier;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.basicEnemies.Slug;
import unsw.loopmania.basicEnemies.Vampire;
import unsw.loopmania.basicEnemies.Zombie;
import unsw.loopmania.PathPosition;
import unsw.loopmania.character.Character;

public class EnemyTest {

    @Test
    public void enemyHealthTest(){
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        assertEquals(slug.getHealth(),20);
        assertEquals(zombie.getHealth(),20);
        assertEquals(vampire.getHealth(),50);
    }

    @Test
    public void enemyDamageTest(){
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        Vampire vampire2 = (Vampire) vampire;
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        assertEquals(slug.getBiteCharacter(character, false),10);
        assertEquals(zombie.getBiteCharacter(character, false),30);
        assertEquals(vampire2.getBiteCharacter(character, false),40);
        assertEquals(vampire2.getBiteCharacter(character, false),40);
    }

    @Test
    public void enemyAliveTest(){
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        assertEquals(slug.checkDeath(), false);
        assertEquals(vampire.checkDeath(), false);
        assertEquals(zombie.checkDeath(), false);
    }

    @Test
    public void slugDeathTest(){
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        slug.gotAttack(20);
        assertTrue(slug.checkDeath());
        assertEquals(0, slug.getHealth());
        slug.gotAttack(20);
        assertEquals(0, slug.getHealth());
    }

    @Test
    public void vampireDeathTest(){
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        vampire.gotAttack(60);
        assertTrue(vampire.checkDeath());
        assertEquals(0, vampire.getHealth());
        vampire.gotAttack(60);
        assertEquals(0, vampire.getHealth());
    }

    @Test
    public void zombieDeathTest(){
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        zombie.gotAttack(20);
        assertTrue(zombie.checkDeath());
        assertEquals(0, zombie.getHealth());
        zombie.gotAttack(20);
        assertEquals(0, zombie.getHealth());
    }

    @Test
    public void zombieMoveTest1(){
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition2()));
        List<BasicEnemy> enemyList = new ArrayList<>();
        enemyList.add(zombie);
        Character character = new Character(new PathPosition(10, generatePathPosition2()));
        List<Pair<Integer, Integer>> campFireList = new ArrayList<>();
        zombie.move(enemyList, character, campFireList);
        assertEquals(1, zombie.getX());
        assertEquals(2, zombie.getY());
        zombie.move(enemyList, character, campFireList);
        assertEquals(1, zombie.getX());
        assertEquals(1, zombie.getY());
        zombie.move(enemyList, character, campFireList);
        assertEquals(1, zombie.getX());
        assertEquals(1, zombie.getY());
        zombie.move(enemyList, character, campFireList);
        assertEquals(1, zombie.getX());
        assertEquals(1, zombie.getY());
        zombie.move(enemyList, character, campFireList);
        assertEquals(1, zombie.getX());
        assertEquals(2, zombie.getY());
    }

    @Test
    public void zombieMoveTest2(){
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition2()));
        List<BasicEnemy> enemyList = new ArrayList<>();
        enemyList.add(zombie);
        enemyList.add(new Slug(new PathPosition(5, generatePathPosition2())));
        Character character = new Character(new PathPosition(5, generatePathPosition2()));
        List<Pair<Integer, Integer>> campFireList = new ArrayList<>();
        zombie.move(enemyList, character, campFireList);
        enemyList.add(new Slug(new PathPosition(5, generatePathPosition2())));
        zombie.move(enemyList, character, campFireList);
        enemyList.add(new Slug(new PathPosition(5, generatePathPosition2())));
        zombie.move(enemyList, character, campFireList);
        enemyList.add(new Slug(new PathPosition(5, generatePathPosition2())));
        zombie.move(enemyList, character, campFireList);
        assertEquals(2, zombie.getX());
        assertEquals(2, zombie.getY());
    }

    @Test
    public void zombieTransferRandomTest(){
        Zombie zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a1 = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        AlliedSoldier a2 = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        AlliedSoldier a3 = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        AlliedSoldier a4 = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        AlliedSoldier a5 = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        AlliedSoldier a6 = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        AlliedSoldier a7 = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        AlliedSoldier a8 = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        // seed 0: 60 48 29 47 15 53 91 61
        zombie.getBiteAlliedSoldier(a1);
        zombie.getBiteAlliedSoldier(a2);
        zombie.getBiteAlliedSoldier(a3);
        zombie.getBiteAlliedSoldier(a4);
        zombie.getBiteAlliedSoldier(a5);
        zombie.getBiteAlliedSoldier(a6);
        zombie.getBiteAlliedSoldier(a7);
        zombie.getBiteAlliedSoldier(a8);
        assertEquals(a1.isTransferred(), false);
        assertEquals(a2.isTransferred(), false);
        assertEquals(a3.isTransferred(), true);
        assertEquals(a4.isTransferred(), false);
        assertEquals(a5.isTransferred(), true);
        assertEquals(a6.isTransferred(), false);
        assertEquals(a7.isTransferred(), false);
        assertEquals(a8.isTransferred(), false);

    }


    @Test
    public void zombieTransferAlliedSoldierTest(){
        Zombie zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        AlliedSoldier alliedSoldier = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(alliedSoldier.isTransferred(), false);
        zombie.transferAlliedSoldier(alliedSoldier);
        assertEquals(alliedSoldier.isTransferred(), true);
    }

    @Test
    public void biteAlliedSoldierTest(){
        AlliedSoldier a = new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        assertEquals(slug.getBite(), slug.getBiteAlliedSoldier(a));
        assertTrue(zombie.getBite()==zombie.getBiteAlliedSoldier(a) || a.isTransferred());
    }

    @Test
    public void vampireBiteAlliedSoldierTest(){
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a = new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        // seed 4: 62 52 3 58 67 5 11 46
        assertEquals(40, vampire.getBiteAlliedSoldier(a));
        assertEquals(40, vampire.getBiteAlliedSoldier(a));
        assertEquals(43, vampire.getBiteAlliedSoldier(a));
        assertEquals(40, vampire.getBiteAlliedSoldier(a));
        assertEquals(40, vampire.getBiteAlliedSoldier(a));
        assertEquals(45, vampire.getBiteAlliedSoldier(a));
        assertEquals(51, vampire.getBiteAlliedSoldier(a));
        assertEquals(40, vampire.getBiteAlliedSoldier(a));
    }

    @Test
    public void vampireBiteCharacterTest(){
        Vampire vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        // seed 4: 62 52 3 58 67 5 11 46
        assertEquals(40, vampire.getBiteCharacter(character, false));
        assertEquals(40, vampire.getBiteCharacter(character, false));
        assertEquals(43, vampire.getBiteCharacter(character, false));
        assertEquals(40, vampire.getBiteCharacter(character, false));
        assertEquals(40, vampire.getBiteCharacter(character, false));
        assertEquals(45, vampire.getBiteCharacter(character, false));
        assertEquals(51, vampire.getBiteCharacter(character, false));
        assertEquals(40, vampire.getBiteCharacter(character, false));
    }

    @Test
    public void vampireBiteCharacterShieldTest(){
        Vampire vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        // bite seed   0: 60 48 29 47 15 53 91 61
        // damage seed 4: 62                52  3 58 67  5 11 46
        assertEquals(40, vampire.getBiteCharacter(character,true));
        assertEquals(40, vampire.getBiteCharacter(character,true));
        assertEquals(40, vampire.getBiteCharacter(character,true));
        assertEquals(40, vampire.getBiteCharacter(character,true));
        assertEquals(40, vampire.getBiteCharacter(character,true));
        assertEquals(40, vampire.getBiteCharacter(character,true));
        assertEquals(40, vampire.getBiteCharacter(character,true));
        assertEquals(43, vampire.getBiteCharacter(character,true));
    }


    @Test
    public void EnemyGetExpTest() {
        BasicEnemy slug = new Slug(new PathPosition(1, generatePathPosition()));
        BasicEnemy vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        BasicEnemy zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        assertEquals(slug.getExperience(),100);
        assertEquals(zombie.getExperience(),500);
        assertEquals(vampire.getExperience(),1000);
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

    public List<Pair<Integer, Integer>> generatePathPosition2() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 1);
        Pair<Integer, Integer> pair2 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair3 = new Pair<>(2, 2);
        Pair<Integer, Integer> pair4 = new Pair<>(3, 2);
        Pair<Integer, Integer> pair5 = new Pair<>(4, 2);
        Pair<Integer, Integer> pair6 = new Pair<>(5, 2);
        Pair<Integer, Integer> pair7 = new Pair<>(6, 2);
        Pair<Integer, Integer> pair8 = new Pair<>(7, 2);
        Pair<Integer, Integer> pair9 = new Pair<>(8, 2);
        Pair<Integer, Integer> pair10 = new Pair<>(9, 2);
        Pair<Integer, Integer> pair11 = new Pair<>(9, 1);
        Pair<Integer, Integer> pair12 = new Pair<>(8, 1);
        Pair<Integer, Integer> pair13 = new Pair<>(7, 1);
        Pair<Integer, Integer> pair14 = new Pair<>(6, 1);
        Pair<Integer, Integer> pair15 = new Pair<>(5, 1);
        Pair<Integer, Integer> pair16 = new Pair<>(4, 1);
        Pair<Integer, Integer> pair17 = new Pair<>(3, 1);
        Pair<Integer, Integer> pair18 = new Pair<>(2, 1);
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
        orderedPath.add(pair11);
        orderedPath.add(pair12);
        orderedPath.add(pair13);
        orderedPath.add(pair14);
        orderedPath.add(pair15);
        orderedPath.add(pair16);
        orderedPath.add(pair17);
        orderedPath.add(pair18);
        return orderedPath;
    }

}

