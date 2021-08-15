package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Test;
import unsw.loopmania.basicEnemies.*;
import unsw.loopmania.items.rare.AndurilFlameOfTheWest;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.rare.TreeStump;
import unsw.loopmania.utils.BattleHelper;
import unsw.loopmania.PathPosition;
import unsw.loopmania.alliedSoldiers.AlliedSoldier;
import unsw.loopmania.buildings.*;
import unsw.loopmania.character.Character;
import unsw.loopmania.items.Items;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.weapon.Staff;
import unsw.loopmania.items.weapon.Stake;

import java.util.ArrayList;
import java.util.List;

public class BattleTest {

    @Test
    public void alliedSoldierToSlugTest() {
        // alliedSoldier attack: 5
        // slug init health: 20;
        Slug slug = new Slug(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BattleHelper.alliedSoldierToEnemy(a, slug);
        assertEquals(20 - 5, slug.getHealth());
    }

    @Test
    public void alliedSoldierToVampireTest() {
        // alliedSoldier attack: 5
        // vampire init health: 60;
        Vampire vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BattleHelper.alliedSoldierToEnemy(a, vampire);
        assertEquals(45, vampire.getHealth());
    }

    @Test
    public void alliedSoldierToZombieTest() {
        // alliedSoldier attack: 5
        // zombie init health: 20;
        Zombie zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BattleHelper.alliedSoldierToEnemy(a, zombie);
        assertEquals(20 - 5, zombie.getHealth());
    }

    @Test
    public void alliedSoldierToElanTest() {
        // elanMuske attack: 60
        // alliedSoldier init health: 20;
        ElanMuske.setNull();
        ElanMuske elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BattleHelper.alliedSoldierToEnemy(a, elanMuske);
        assertEquals(60 - 5, elanMuske.getHealth());
    }

    @Test
    public void alliedSoldierToDoggieTest() {
        // alliedSoldier attack: 5
        // doggie init health: 80;
        Doggie doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BattleHelper.alliedSoldierToEnemy(a, doggie);
        assertEquals(80 - 5, doggie.getHealth());
    }



    @Test
    public void slugToAlliedSoldierTest() {
        // slug attack: 10
        // alliedSoldier init health: 20;
        Slug slug = new Slug(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BattleHelper.enemyToAlliedSoldier(a, slug);
        assertEquals(false, a.checkDeath());
        BattleHelper.enemyToAlliedSoldier(a, slug);
        assertTrue(a.checkDeath());
    }

    @Test
    public void zombieToAlliedSoldierTest() {
        // zombie attack: 20
        // alliedSoldier init health: 20;
        // seed0: 60 48 29 47 15 53 91 61
        Zombie zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a1 = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        AlliedSoldier a2 = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        AlliedSoldier a3 = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BattleHelper.enemyToAlliedSoldier(a1, zombie);
        assertTrue(a1.checkDeath());
        BattleHelper.enemyToAlliedSoldier(a2, zombie);
        assertTrue(a2.checkDeath());
        BattleHelper.enemyToAlliedSoldier(a2, zombie);
        assertTrue(a2.isTransferred());
    }

    @Test
    public void vampireToAlliedSoldierTest() {
        // vampire attack: 50
        // alliedSoldier init health: 20;
        // seed 0: 60 48 29 47 15 53 91 61
        // seed 4: 62 52 3  58 67 5  11 46 (< 30)
        Vampire vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BattleHelper.enemyToAlliedSoldier(a, vampire);
        assertTrue(a.checkDeath());
    }


    @Test
    public void doggieToAlliedSoldierTest() {
        // doggie attack: 15
        // alliedSoldier init health: 20;
        Doggie doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BattleHelper.enemyToAlliedSoldier(a, doggie);
        assertEquals(false, a.checkDeath());
    }

    @Test
    public void elanToAlliedSoldierTest() {
        // elanMuske attack: 50
        // alliedSoldier init health: 20;
        ElanMuske elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        AlliedSoldier a = new AlliedSoldier(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BattleHelper.enemyToAlliedSoldier(a, elanMuske);
        assertEquals(true, a.checkDeath());
    }


    @Test
    public void characterToSlugTest1() {
        // slug health: 20
        // character init health: 100
        // character attack: 15
        Slug slug = new Slug(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        BattleHelper.characterToEnemy(character, slug, new ArrayList<Items>(), new ArrayList<Building>());
        assertEquals(5, slug.getHealth());
    }

    @Test
    public void characterToSlugTest2() {
        // slug health: 20
        // character init health: 100
        // character attack: 15
        Slug slug = new Slug(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        // equip Stake
        ArrayList<Items> item = new ArrayList<>();
        item.add(new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        BattleHelper.characterToEnemy(character, slug, item, new ArrayList<Building>());
        assertEquals(2, slug.getHealth());
    }

    @Test
    public void characterToSlugTest3() {
        // slug health: 20
        // character init health: 100
        // character attack: 15
        // seed0: 60 48 29 47 15 53 91 61
        Slug slug = new Slug(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        // equip Staff
        ArrayList<Items> item = new ArrayList<>();
        item.add(new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        assertTrue(!slug.getTrance());
        BattleHelper.characterToEnemy(character, slug, item, new ArrayList<Building>());
        BattleHelper.characterToEnemy(character, slug, item, new ArrayList<Building>());
        BattleHelper.characterToEnemy(character, slug, item, new ArrayList<Building>());
        BattleHelper.characterToEnemy(character, slug, item, new ArrayList<Building>());
        assertTrue(!slug.getTrance());
        BattleHelper.characterToEnemy(character, slug, item, new ArrayList<Building>());
        assertTrue(slug.getTrance());
    }

    @Test
    public void characterToSlugTest4() {
        // slug health: 20
        // character init health: 100
        // character attack: 15
        // seed0: 60 48 29 47 15 53 91 61
        Slug slug = new Slug(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        // equip Staff
        ArrayList<Items> item = new ArrayList<>();
        item.add(new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        item.add(new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        BattleHelper.characterToEnemy(character, slug, item, new ArrayList<Building>());
        assertEquals(6, slug.getHealth());
    }

    @Test
    public void characterToVampireTest1() {
        // vampire init health: 60;
        // character init health: 100
        // character attack: 15
        Vampire vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        BattleHelper.characterToEnemy(character, vampire, new ArrayList<Items>(), new ArrayList<Building>());
        assertEquals(35, vampire.getHealth());
    }



    @Test
    public void characterToVampireTest2() {
        // vampire init health: 60;
        // character init health: 100
        // character attack: 15
        Vampire vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(new CampFireBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)));
        buildings.add(new CampFireBuilding(new SimpleIntegerProperty(5), new SimpleIntegerProperty(5)));
        BattleHelper.characterToEnemy(character, vampire, new ArrayList<Items>(), buildings);
        assertEquals(20, vampire.getHealth());
    }

    @Test
    public void characterToVampireTest3() {
        // vampire init health: 60;
        // character init health: 100
        // character attack: 15
        Vampire vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(new CampFireBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)));
        buildings.add(new CampFireBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(2)));
        BattleHelper.characterToEnemy(character, vampire, new ArrayList<Items>(), buildings);
        assertEquals(0, vampire.getHealth());
    }


    @Test
    public void characterToZombieTest() {
        // vampire init health: 20;
        // character init health: 100
        // character attack: 15
        Zombie zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        BattleHelper.characterToEnemy(character, zombie, new ArrayList<Items>(), new ArrayList<Building>());
        assertEquals(5, zombie.getHealth());
    }

    @Test
    public void characterToZombieRareTest() {
        // vampire init health: 20;
        // character init health: 100
        // character attack: 15
        Zombie zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Items> items = new ArrayList<>();
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        items.add(treeStump);
        BattleHelper.characterToEnemy(character, zombie, items, new ArrayList<Building>());
        assertEquals(0, zombie.getHealth());
    }

    @Test
    public void characterToDoggieTest() {
        // doggie init health: 80
        // character attack: 15
        Doggie doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        BattleHelper.characterToEnemy(character, doggie, new ArrayList<Items>(), new ArrayList<Building>());
        assertEquals(65, doggie.getHealth());
    }

    @Test
    public void characterToDoggieWithRareTest() {
        // doggie init health: 80
        // character attack: 15
        Doggie doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Items> items = new ArrayList<>();
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        items.add(treeStump);
        BattleHelper.characterToEnemy(character, doggie, items, new ArrayList<Building>());
        assertEquals(65, doggie.getHealth());
    }

    @Test
    public void characterToMuskeTest() {
        // elanMuske init health: 60;
        // character init health: 100
        // character attack: 15
        ElanMuske.setNull();
        ElanMuske elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        BattleHelper.characterToEnemy(character, elanMuske, new ArrayList<Items>(), new ArrayList<Building>());
        assertEquals(45, elanMuske.getHealth());
    }

    @Test
    public void characterToMuskeWithRareTest() {
        // elanMuske init health: 60;
        // character init health: 100
        // character attack: 15
        ElanMuske.setNull();
        ElanMuske elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Items> items = new ArrayList<>();
        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        andruil.setSecondaryAbility(new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        items.add(andruil);
        BattleHelper.characterToEnemy(character, elanMuske, items, new ArrayList<Building>());
        assertEquals(30, elanMuske.getHealth());
        ElanMuske.setNull();
    }


    @Test
    public void slugToCharacterTest() {
        // slug attack: 10
        // character init health: 100;
        Slug slug = new Slug(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        BattleHelper.enemyToCharacter(character, slug, new ArrayList<Items>());
        assertEquals(90, character.getHealth());
    }

    @Test
    public void zombieToCharacterTest() {
        // zombie attack: 20
        // character init health: 100;
        Zombie zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        BattleHelper.enemyToCharacter(character, zombie, new ArrayList<Items>());
        assertEquals(70, character.getHealth());
    }

    @Test
    public void vampireToCharacterTest() {
        // vampire attack: 50
        // character init health: 100;
        Vampire vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Items> item = new ArrayList<>();
        item.add(new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        item.add(new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        BattleHelper.enemyToCharacter(character, vampire, item);
        assertEquals(64, character.getHealth());
    }

    @Test
    public void vampireToCharacterWithRareTest() {
        // vampire attack: 50
        // character init health: 100;
        Vampire vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Items> items = new ArrayList<>();
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        items.add(treeStump);
        BattleHelper.enemyToCharacter(character, vampire, items);
        assertEquals(68, character.getHealth());
    }



    @Test
    public void doggieToCharacterTest() {
        // vampire attack: 50
        // character init health: 100;
        Doggie doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Items> item = new ArrayList<>();
        // stun seed 4: 62 52 3 58 67 5 11 46
        BattleHelper.enemyToCharacter(character, doggie, item);
        assertEquals(85, character.getHealth());
    }

    @Test
    public void doggieToCharacterWithRareTest() {
        // vampire attack: 50
        // character init health: 100;
        Doggie doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Items> items = new ArrayList<>();
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        treeStump.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        items.add(treeStump);
        // stun seed 4: 62 52 3 58 67 5 11 46
        BattleHelper.enemyToCharacter(character, doggie, items);
        assertEquals(88, character.getHealth());
    }

    @Test
    public void doggieToCharacterStunTest() {
        // vampire attack: 50
        // character init health: 100;
        Doggie doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Items> item = new ArrayList<>();
        // stun seed 4: 62 52 3 58 67 5 11 46
        BattleHelper.enemyToCharacter(character, doggie, item);
        BattleHelper.enemyToCharacter(character, doggie, item);
        assertEquals(70, character.getHealth());
        BattleHelper.enemyToCharacter(character, doggie, item);
        assertEquals(55, character.getHealth());
        assertTrue(character.isStun());
    }

    @Test
    public void muskeToCharacterTest() {
        // vampire attack: 50
        // character init health: 100;
        ElanMuske elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Items> item = new ArrayList<>();
        BattleHelper.enemyToCharacter(character, elanMuske, item);
        assertEquals(50, character.getHealth());
    }

    @Test
    public void muskeToCharacterWithRareItemTest() {
        // vampire attack: 50
        // character init health: 100;
        ElanMuske elanMuske = ElanMuske.getInstance(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        ArrayList<Items> items = new ArrayList<>();
        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        andruil.setSecondaryAbility(new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        items.add(andruil);
        BattleHelper.enemyToCharacter(character, elanMuske, items);
        assertEquals(68, character.getHealth());
        elanMuske.setNull();
    }

    @Test
    public void trapTest1() {
        // vampire init health: 60;
        // trap attack: 15
        Vampire vampire = new Vampire(new PathPosition(3, generatePathPosition()));
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        buildings.add(new TrapBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        BattleHelper.handleTrap(vampire, buildings);
        assertEquals(40, vampire.getHealth());
        assertEquals(1, buildings.size());
    }

    @Test
    public void trapTest2() {
        // vampire init health: 60;
        // trap attack: 15
        Vampire vampire = new Vampire(new PathPosition(3, generatePathPosition()));
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        buildings.add(new TrapBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        buildings.add(new TrapBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        BattleHelper.handleTrap(vampire, buildings);
        assertEquals(30, vampire.getHealth());
        assertEquals(1, buildings.size());
    }

    @Test
    public void towerTest2() {
        // vampire init health: 60;
        // trap attack: 15
        Vampire vampire = new Vampire(new PathPosition(3, generatePathPosition()));
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        buildings.add(new TowerBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        buildings.add(new TowerBuilding(new SimpleIntegerProperty(7), new SimpleIntegerProperty(7)));
        buildings.add(new TrapBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        BattleHelper.handleTowerBuilding(vampire, buildings);
        assertEquals(45, vampire.getHealth());
        assertEquals(4, buildings.size());
    }


    @Test
    public void DoggieToCharacterTest1() {
        // doggie init health: 80;
        // doggie damage: 15
        // stun seed 4: 62 52 3 58 67 5 11 46
        // character init health: 100
        // character attack: 15
        Doggie doggie = new Doggie(new PathPosition(1, generatePathPosition()));
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        BattleHelper.enemyToCharacter(character, doggie, new ArrayList<Items>());
        assertEquals(85, character.getHealth());
        BattleHelper.enemyToCharacter(character, doggie, new ArrayList<Items>());
        assertEquals(70, character.getHealth());
        BattleHelper.enemyToCharacter(character, doggie, new ArrayList<Items>());
        assertEquals(55, character.getHealth());
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
