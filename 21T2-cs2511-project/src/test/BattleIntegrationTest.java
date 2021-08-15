package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Test;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.alliedSoldiers.AlliedSoldier;
import unsw.loopmania.alliedSoldiers.TracedAlliedSoldier;
import unsw.loopmania.basicEnemies.*;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.HeroCastleBuilding;
import unsw.loopmania.buildings.TrapBuilding;
import unsw.loopmania.items.Items;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.rare.AndurilFlameOfTheWest;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.rare.TreeStump;
import unsw.loopmania.items.weapon.Staff;
import unsw.loopmania.items.weapon.Stake;
import unsw.loopmania.items.weapon.Sword;
import unsw.loopmania.utils.BattleHelper;

import java.util.ArrayList;
import java.util.List;

public class BattleIntegrationTest {
    public List<Pair<Integer, Integer>> initOrderedPath() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(0, 1));
        orderedPath.add(Pair.with(1, 1));
        orderedPath.add(Pair.with(2, 1));
        orderedPath.add(Pair.with(2, 2));
        orderedPath.add(Pair.with(1, 2));
        orderedPath.add(Pair.with(0, 2));
        return orderedPath;
    }

    @Test
    public void BattleVampireTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<BasicEnemy> enemies = world.getEnemies();
        BasicEnemy e = new Vampire(new PathPosition(1, initOrderedPath()));
        enemies.add(e);
        List<AlliedSoldier> a = world.getAlliedSoldiers();
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        world.runTickMoves();
        world.runBattles();
        // seed 4: 62 52 3 58 67 5 11 46
        // soldier all dead
        assertEquals(0, world.getAlliedSoldierNum().get());
        // character loss: 50 + 11(critical)
        assertEquals(49, world.getCharacter().getHealth());
        // vampire dead
        assertTrue(e.checkDeath());
    }

    @Test
    public void BattleStakeVampireTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<Items> items = world.getEquippedItems();
        items.add(new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        List<BasicEnemy> enemies = world.getEnemies();
        BasicEnemy e = new Vampire(new PathPosition(1, initOrderedPath()));
        enemies.add(e);
        List<AlliedSoldier> a = world.getAlliedSoldiers();
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        world.runTickMoves();
        world.runBattles();
        // seed 4: 62 52 3 58 67 5 11 46
        // soldier all dead
        assertEquals(0, world.getAlliedSoldierNum().get());
        assertEquals(100, world.getCharacter().getHealth());
        // vampire dead
        // 5 * 6 soldier + (15+30) allied soldier
        assertTrue(e.checkDeath());
    }

    @Test
    public void BattleZombieTest() {
        // transfer seed 0: 60 48 29 47 15 53 91 61  < 30
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<BasicEnemy> enemies = world.getEnemies();
        BasicEnemy e = new Zombie(new PathPosition(1, initOrderedPath()));
        enemies.add(e);
        List<AlliedSoldier> a = world.getAlliedSoldiers();
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        world.runTickMoves();
        world.runBattles();
        // 2 soldier dead
        // one transfer
        assertEquals(70, world.getCharacter().getHealth());
        // vampire dead
        assertTrue(e.checkDeath());
    }

    @Test
    public void BattleSlugZombieTest() {
        // transfer seed 0: 60 48 29 47 15 53 91 61  < 20
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<BasicEnemy> enemies = world.getEnemies();
        BasicEnemy e = new SlugZombie(new PathPosition(1, initOrderedPath()));
        enemies.add(e);
        List<AlliedSoldier> a = world.getAlliedSoldiers();
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        a.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        world.runTickMoves();
        world.runBattles();
        assertEquals(50, world.getCharacter().getHealth());
        assertTrue(e.checkDeath());
    }


    @Test
    public void BattleSlugTest() {
        // staff seed 0: 60 48 29 47 15 53 91 61  < 20
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<BasicEnemy> enemies = world.getEnemies();
        List<Items> items = world.getEquippedItems();
        items.add(new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        enemies.add(new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add(new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add(new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add(new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add(new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add(new Slug(new PathPosition(1, initOrderedPath())));
        world.runTickMoves();
        world.runBattles();
        // one slug transfer
        assertEquals(70, world.getCharacter().getHealth());
    }


    @Test
    public void BattleDoggieTest() {
        //  seed 4: 62 52 3 58 67 5 11 46
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<BasicEnemy> enemies = world.getEnemies();
        List<Items> items = world.getEquippedItems();
        items.add(new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        enemies.add(new Doggie(new PathPosition(1, initOrderedPath())));
        world.runTickMoves();
        world.runBattles();
        assertEquals(25, world.getCharacter().getHealth());
    }

    @Test
    public void BattleDoggieWithTracedSoldierTest() {
        //  seed 4: 62 52 3 58 67 5 11 46
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<AlliedSoldier> alliedSoldiers = world.getAlliedSoldiers();
        TracedAlliedSoldier a = new TracedAlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        a.setEnemy(new Slug(new PathPosition(1, initOrderedPath())));
        a.decreaseTranceRemain();
        a.decreaseTranceRemain();
        a.decreaseTranceRemain();
        a.decreaseTranceRemain();
        alliedSoldiers.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        alliedSoldiers.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        alliedSoldiers.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        alliedSoldiers.add(a);
        List<BasicEnemy> enemies = world.getEnemies();
        enemies.add(new Doggie(new PathPosition(1, initOrderedPath())));
        world.runTickMoves();
        world.runBattles();
        assertEquals(30, world.getCharacter().getHealth());
    }

    @Test
    public void BattleDoggieWithRareTest1() {
        //  seed 4: 62 52 3 58 67 5 11 46
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<BasicEnemy> enemies = world.getEnemies();
        List<Items> items = world.getEquippedItems();
        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        andruil.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        items.add(andruil);
        enemies.add(new Doggie(new PathPosition(1, initOrderedPath())));
        world.runTickMoves();
        world.runBattles();
        assertEquals(40, world.getCharacter().getHealth());
    }

    @Test
    public void BattleDoggieWithRareTest2() {
        //  seed 4: 62 52 3 58 67 5 11 46
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<BasicEnemy> enemies = world.getEnemies();
        List<Items> items = world.getEquippedItems();
        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        items.add(andruil);
        enemies.add(new Doggie(new PathPosition(1, initOrderedPath())));
        world.runTickMoves();
        world.runBattles();
        assertEquals(40, world.getCharacter().getHealth());
    }

    @Test
    public void BattleElanTest() {
        //  seed 4: 62 52 3 58 67 5 11 46
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<BasicEnemy> enemies = world.getEnemies();
        ElanMuske.setNull();
        enemies.add(ElanMuske.getInstance(new PathPosition(1, initOrderedPath())));
        world.runTickMoves();
        world.runBattles();
        assertEquals(0, world.getCharacter().getHealth());
        assertEquals(1, enemies.size());
        assertEquals(30, enemies.get(0).getHealth());
        ElanMuske.setNull();
    }

    @Test
    public void BattleElanFullEquippedTest() {
        //  seed 4: 62 52 3 58 67 5 11 46
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<BasicEnemy> enemies = world.getEnemies();
        List<Items> items = world.getEquippedItems();
        items.add(new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        ElanMuske.setNull();
        enemies.add(ElanMuske.getInstance(new PathPosition(1, initOrderedPath())));
        world.runTickMoves();
        world.runBattles();
        assertEquals(43, world.getCharacter().getHealth());
        assertEquals(0, enemies.size());;
        ElanMuske.setNull();
    }

    @Test
    public void BattleElanWithRareTest1() {
        //  seed 4: 62 52 3 58 67 5 11 46
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        ElanMuske.setNull();
        List<BasicEnemy> enemies = world.getEnemies();
        List<Items> items = world.getEquippedItems();
        enemies.add(ElanMuske.getInstance(new PathPosition(1, initOrderedPath())));
        AndurilFlameOfTheWest andruil = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        andruil.setSecondaryAbility(new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        items.add(andruil);
        world.runTickMoves();
        world.runBattles();
        assertEquals(68, world.getCharacter().getHealth());
        assertEquals(0, enemies.size());
        ElanMuske.setNull();
    }

    @Test
    public void BattleElanWithRareTest2() {
        //  seed 4: 62 52 3 58 67 5 11 46
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        ElanMuske.setNull();
        List<BasicEnemy> enemies = world.getEnemies();
        List<Items> items = world.getEquippedItems();
        enemies.add(ElanMuske.getInstance(new PathPosition(1, initOrderedPath())));
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        items.add(treeStump);
        world.runTickMoves();
        world.runBattles();
        assertEquals(4, world.getCharacter().getHealth());
        assertEquals(0, enemies.size());
        ElanMuske.setNull();
    }

    @Test
    public void BattleElanWithRareTest3() {
        //  seed 4: 62 52 3 58 67 5 11 46
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        ElanMuske.setNull();
        List<BasicEnemy> enemies = world.getEnemies();
        List<Items> items = world.getEquippedItems();
        enemies.add(ElanMuske.getInstance(new PathPosition(1, initOrderedPath())));
        AndurilFlameOfTheWest a = new AndurilFlameOfTheWest(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        items.add(a);
        world.runTickMoves();
        world.runBattles();
        assertEquals(50, world.getCharacter().getHealth());
        assertEquals(0, enemies.size());
        ElanMuske.setNull();
    }

    @Test
    public void BattleFullEquippedVampireTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<Items> items = world.getEquippedItems();
        items.add(new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        List<BasicEnemy> enemies = world.getEnemies();
        enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Vampire(new PathPosition(1, initOrderedPath())));
        world.runTickMoves();
        world.runBattles();
        assertEquals(53, world.getCharacter().getHealth());
    }

    @Test
    public void BattleDeadTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<Items> items = world.getEquippedItems();
        items.add(new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        // seed0: 60 48 29 47 15 53 91 61 (< 20)
        items.add(new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        List<BasicEnemy> enemies = world.getEnemies();
        enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Doggie(new PathPosition(1, initOrderedPath())));
        //enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Vampire(new PathPosition(1, initOrderedPath())));
        enemies.add( new Vampire(new PathPosition(1, initOrderedPath())));
        enemies.add( new Vampire(new PathPosition(1, initOrderedPath())));
        world.runTickMoves();
        world.runBattles();
        assertEquals(0, world.getCharacter().getHealth());
        assertEquals(1, enemies.size());
    }


    @Test
    public void BattleDeadWithRareTest1() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<Items> items = world.getEquippedItems();
        items.add(new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        // seed0: 60 48 29 47 15 53 91 61 (< 20)
        items.add(new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        List<Items> unequipped = world.getUnequippedInventoryItems();
        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        theOneRing.setSecondaryAbility(new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3)));
        unequipped.add(theOneRing);
        List<BasicEnemy> enemies = world.getEnemies();
        enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Doggie(new PathPosition(1, initOrderedPath())));
        //enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Vampire(new PathPosition(1, initOrderedPath())));
        enemies.add( new Vampire(new PathPosition(1, initOrderedPath())));
        enemies.add( new Vampire(new PathPosition(1, initOrderedPath())));
        world.runTickMoves();
        world.runBattles();
        assertEquals(100, world.getCharacter().getHealth());
        assertEquals(1, enemies.size());
        assertEquals(false, world.getCharacter().checkDeath());
    }

    @Test
    public void BattleDeadWithRareTest2() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<Items> items = world.getEquippedItems();
        items.add(new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        // seed0: 60 48 29 47 15 53 91 61 (< 20)
        items.add(new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        items.add(new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        List<Items> unequipped = world.getUnequippedInventoryItems();
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
        unequipped.add(treeStump);
        List<BasicEnemy> enemies = world.getEnemies();
        enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Doggie(new PathPosition(1, initOrderedPath())));
        //enemies.add( new Slug(new PathPosition(1, initOrderedPath())));
        enemies.add( new Vampire(new PathPosition(1, initOrderedPath())));
        enemies.add( new Vampire(new PathPosition(1, initOrderedPath())));
        enemies.add( new Vampire(new PathPosition(1, initOrderedPath())));
        world.runBattles();
        world.runTickMoves();
        assertEquals(0, world.getCharacter().getHealth());
        assertEquals(1, enemies.size());
        assertEquals(true, world.getCharacter().checkDeath());
    }

    @Test
    public void BattleTrapTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        List<Building> buildings = world.getBuildingEntities();
        buildings.add(new TrapBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(2)));
        // vampire init health: 60;
        // trap attack: 15
        List<BasicEnemy> enemies = world.getEnemies();
        BasicEnemy slug = new Slug(new PathPosition(3, initOrderedPath()));
        slug.gotAttack(15);
        enemies.add(slug);
        world.runTickMoves();
        world.runBattles();
        assertEquals(100, world.getCharacter().getHealth());
    }

}
