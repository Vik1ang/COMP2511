package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Test;
import unsw.loopmania.*;
import unsw.loopmania.basicEnemies.*;

import unsw.loopmania.basicEnemies.Zombie;
import unsw.loopmania.buildings.*;

import unsw.loopmania.card.*;
import unsw.loopmania.items.Gold;
import unsw.loopmania.items.Items;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.weapon.Sword;
import unsw.loopmania.character.Character;
import unsw.loopmania.items.weapon.Sword;
import unsw.loopmania.character.Character;
import unsw.loopmania.utils.LoopManiaUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class IntegrationTest {

    /*
        (0,41) (1,5) (2,30) (3,79) (4,92) (5,2) (6,88) (7,55) (8,87) (9,2) (10,92) (11,41) (12,31) (13,7) (14,38) (15,2)
        (16,67) (17,68) (18,23) (19,80) (20,28) (21,0) (22,55) (23,57) (24,0) (25,53) (26,11) (27,55) (28,70) (29,39)
        (30,3) (31,94) (32,58) (33,36) (34,38) (35,48) (36,59) (37,15) (38,33) (39,81) (40,61) (41,89) (42,0) (43,54)
        (44,60) (45,57) (46,21) (47,33) (48,61) (49,57) (50,31) (51,35) (52,55) (53,6) (54,14) (55,43) (56,95) (57,19)
        (58,22) (59,71) (60,85) (61,85) (62,22) (63,56) (64,74) (65,72) (66,84) (67,27) (68,0) (69,75) (70,15) (71,38)
        (72,81) (73,76) (74,44) (75,59) (76,84) (77,5) (78,9) (79,47) (80,96) (81,61) (82,75) (83,60) (84,63) (85,64)
        (86,24) (87,29) (88,32) (89,3) (90,95) (91,6) (92,87) (93,29) (94,72) (95,60) (96,30) (97,30) (98,76) (99,26)
    */

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
    public void testInitLoopManiaWorld() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());

        List<Entity> entities = loopmania.initNewCycleComplete();

        assertTrue(loopmania.getEnemies().size() != 0);
        assertTrue(loopmania.getGoldOnGround().size() != 0);
        assertTrue(loopmania.getHealthPotionsOnGround().size() != 0);

    }


    @Test
    public void testBattleSlug() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        Character character = loopmania.getCharacter();
        // (0,0)
        BasicEnemy slug1 = new Slug(new PathPosition(1, initOrderedPath()));
        BasicEnemy slug2 = new Slug(new PathPosition(2, initOrderedPath()));


        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        loopmania.getEnemies().add(slug1);
        loopmania.getEnemies().add(slug2);
        loopmania.runBattles();
        assertTrue(slug1.checkDeath());
        assertTrue(slug2.checkDeath());

        assertEquals(character.getExperience(), 0);
        assertEquals(character.getHealth(), 80);
        assertEquals(character.getGolds(), 0);

    }

    @Test
    public void testMove() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());

        List<Entity> entities = loopmania.initNewCycleComplete();

        Character character = loopmania.getCharacter();
        // System.out.println(character.getPosition().getCurrentPositionInPath());
        for (int i = 0; i < 8; i++) {
            character.moveDownPath();
        }
        // System.out.println(character.getPosition().getCurrentPositionInPath());
        List<BasicEnemy> enemies = loopmania.getEnemies();
        // System.out.println(enemies.get(0).getPosition().getCurrentPositionInPath());

        for (BasicEnemy enemy : enemies) {
            enemy.move(enemies, character, null);
        }
        // System.out.println(character.getPosition().getCurrentPositionInPath());
        // System.out.println(enemies.get(0).getPosition().getCurrentPositionInPath());

        // for (BasicEnemy enemy : enemies) {
        //     System.out.println(enemy);
        // }


    }

    @Test
    public void slugMoveInBattleRadiusTest() {
        List<Pair<Integer, Integer>> orderPath = generateLargePathPosition();
        Character character = new Character(new PathPosition(6, orderPath));
        List<BasicEnemy> basicEnemies = new ArrayList<>();
        Slug slug = new Slug(new PathPosition(5, orderPath));
        basicEnemies.add(slug);
        slug.move(basicEnemies, character, null);
        // System.out.println("(" + slug.getPosition().getX().get() + " , " + slug.getPosition().getY().get() + ")");
        // do not move if in character in battle radius
        assertEquals(0, slug.getPosition().getX().get());
        assertEquals(5, slug.getPosition().getY().get());
    }


    @Test
    public void slugMoveNotInBattleRadiusTest() {
        List<Pair<Integer, Integer>> orderPath = generateLargePathPosition();
        Character character = new Character(new PathPosition(0, orderPath));
        List<BasicEnemy> basicEnemies = new ArrayList<>();
        Slug slug = new Slug(new PathPosition(5, orderPath));
        basicEnemies.add(slug);
        slug.move(basicEnemies, character, null);
        assertEquals(0, slug.getPosition().getX().get());
        assertEquals(5, slug.getPosition().getY().get());
        slug.move(basicEnemies, character, null);
        assertEquals(0, slug.getPosition().getX().get());
        assertEquals(6, slug.getPosition().getY().get());
        slug.move(basicEnemies, character, null);
        assertEquals(0, slug.getPosition().getX().get());
        assertEquals(6, slug.getPosition().getY().get());
        slug.move(basicEnemies, character, null);
        slug.move(basicEnemies, character, null);
        slug.move(basicEnemies, character, null);
        assertEquals(1, slug.getPosition().getX().get());
        assertEquals(6, slug.getPosition().getY().get());
        for (int i = 0; i < 30; i++) {
            slug.move(basicEnemies, character, null);
        }
        assertEquals(1, slug.getPosition().getX().get());
        assertEquals(2, slug.getPosition().getY().get());
    }

    @Test
    public void slugZombieInBattleRadiusTest() {
        List<Pair<Integer, Integer>> orderPath = generateLargePathPosition();
        Character character = new Character(new PathPosition(0, orderPath));
        List<BasicEnemy> basicEnemies = new ArrayList<>();
        Zombie zombie = new Zombie(new PathPosition(3, orderPath));
        basicEnemies.add(zombie);
        zombie.move(basicEnemies, character, null);
        // do not move if in character in battle radius
        assertEquals(0, zombie.getPosition().getX().get());
        assertEquals(3, zombie.getPosition().getY().get());

        basicEnemies.clear();
        zombie = new Zombie(new PathPosition(6, orderPath));
        basicEnemies.add(zombie);
        zombie.move(basicEnemies, character, null);
        assertEquals(0, zombie.getPosition().getX().get());
        assertEquals(6, zombie.getPosition().getY().get());
        zombie.move(basicEnemies, character, null);
        assertEquals(0, zombie.getPosition().getX().get());
        assertEquals(5, zombie.getPosition().getY().get());
        zombie.move(basicEnemies, character, null);
        zombie.move(basicEnemies, character, null);
        zombie.move(basicEnemies, character, null);
        zombie.move(basicEnemies, character, null);
        assertEquals(0, zombie.getPosition().getX().get());
        assertEquals(5, zombie.getPosition().getY().get());
    }

    @Test
    public void vampireSupportSlugTest() {
        List<Pair<Integer, Integer>> orderPath = generateLargePathPosition();
        Character character = new Character(new PathPosition(0, orderPath));
        List<BasicEnemy> basicEnemies = new ArrayList<>();
        Vampire vampire = new Vampire(new PathPosition(7, orderPath));
        basicEnemies.add(vampire);
        Slug slug = new Slug(new PathPosition(2, orderPath));
        basicEnemies.add(slug);

        slug.move(basicEnemies, character, new ArrayList<>());
        vampire.move(basicEnemies, character, new ArrayList<>());
        assertEquals(0, slug.getPosition().getX().get());
        assertEquals(2, slug.getPosition().getY().get());
        assertEquals(0, vampire.getPosition().getX().get());
        assertEquals(6, vampire.getPosition().getY().get());
        slug.move(basicEnemies, character, new ArrayList<>());
        vampire.move(basicEnemies, character, new ArrayList<>());
        assertEquals(0, vampire.getPosition().getX().get());
        assertEquals(5, vampire.getPosition().getY().get());
        vampire.move(basicEnemies, character, new ArrayList<>());
        assertEquals(0, vampire.getPosition().getX().get());
        assertEquals(5, vampire.getPosition().getY().get());
    }

    @Test
    public void vampireSupportSlugMoveDown() {
        List<Pair<Integer, Integer>> orderPath = generateLargePathPosition();
        Character character = new Character(new PathPosition(0, orderPath));
        List<BasicEnemy> basicEnemies = new ArrayList<>();
        Vampire vampire = new Vampire(new PathPosition(15, orderPath));
        basicEnemies.add(vampire);
        Slug slug = new Slug(new PathPosition(6, orderPath));
        basicEnemies.add(slug);

        for (int i = 0; i < 20; i++) {
            slug.move(basicEnemies, character, new ArrayList<>());
            vampire.move(basicEnemies, character, new ArrayList<>());
        }
        assertEquals(2, vampire.getPosition().getX().get());
        assertEquals(1, vampire.getPosition().getY().get());

    }

    @Test
    public void zombieSupportSlugTest() {
        List<Pair<Integer, Integer>> orderPath = generateLargePathPosition();
        Character character = new Character(new PathPosition(0, orderPath));
        List<BasicEnemy> basicEnemies = new ArrayList<>();
        Zombie zombie = new Zombie(new PathPosition(6, orderPath));
        basicEnemies.add(zombie);
        Slug slug = new Slug(new PathPosition(2, orderPath));
        basicEnemies.add(slug);

        slug.move(basicEnemies, character, new ArrayList<>());
        zombie.move(basicEnemies, character, new ArrayList<>());
        assertEquals(0, zombie.getPosition().getX().get());
        assertEquals(6, zombie.getPosition().getY().get());

        slug.move(basicEnemies, character, new ArrayList<>());
        zombie.move(basicEnemies, character, new ArrayList<>());
        assertEquals(0, zombie.getPosition().getX().get());
        assertEquals(6, zombie.getPosition().getY().get());

        zombie.move(basicEnemies, character, new ArrayList<>());
        zombie.move(basicEnemies, character, new ArrayList<>());
        assertEquals(0, zombie.getPosition().getX().get());
        assertEquals(5, zombie.getPosition().getY().get());
    }

    @Test
    public void zombieCampFireTest() {
        List<Pair<Integer, Integer>> orderPath = generateLargePathPosition();
        Character character = new Character(new PathPosition(0, orderPath));
        CampFireBuilding campFireBuilding = new CampFireBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2));
        Vampire vampire = new Vampire(new PathPosition(6, orderPath));
        List<BasicEnemy> basicEnemies = new ArrayList<>();
        basicEnemies.add(vampire);

        List<Pair<Integer, Integer>> campFireList = new ArrayList<>();
        campFireList.add(Pair.with(campFireBuilding.getX(), campFireBuilding.getY()));
        vampire.move(basicEnemies, character, campFireList);
        assertEquals(1, vampire.getPosition().getX().get());
        assertEquals(6, vampire.getPosition().getY().get());
    }


    public List<Pair<Integer, Integer>> generateLargePathPosition() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(0, 0));
        orderedPath.add(Pair.with(0, 1));
        orderedPath.add(Pair.with(0, 2));
        orderedPath.add(Pair.with(0, 3));
        orderedPath.add(Pair.with(0, 4));
        orderedPath.add(Pair.with(0, 5));
        orderedPath.add(Pair.with(0, 6));
        orderedPath.add(Pair.with(1, 6));
        orderedPath.add(Pair.with(1, 5));
        orderedPath.add(Pair.with(1, 4));
        orderedPath.add(Pair.with(1, 3));
        orderedPath.add(Pair.with(1, 2));
        orderedPath.add(Pair.with(1, 1));
        orderedPath.add(Pair.with(1, 0));
        orderedPath.add(Pair.with(2, 0));
        orderedPath.add(Pair.with(2, 1));
        orderedPath.add(Pair.with(2, 2));
        orderedPath.add(Pair.with(2, 3));
        orderedPath.add(Pair.with(2, 4));
        orderedPath.add(Pair.with(2, 5));
        orderedPath.add(Pair.with(2, 6));
        orderedPath.add(Pair.with(3, 6));
        orderedPath.add(Pair.with(3, 5));
        orderedPath.add(Pair.with(3, 4));
        orderedPath.add(Pair.with(3, 3));
        orderedPath.add(Pair.with(3, 2));
        orderedPath.add(Pair.with(3, 1));
        return orderedPath;
    }

    @Test
    public void buyitemsHeroCastle() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(1, initOrderedPath());
        character = new Character(pathPosition);

        loopmania.setHeroCastleBuilding(new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        loopmania.getHeroCastleBuilding().setMode("Standard");
        loopmania.getCharacter().setGolds(100);
        loopmania.buyItem("Sword");
        loopmania.buyItem("Sword");
        assertEquals(loopmania.getCharacter().getGolds(), 70);
        assertEquals(loopmania.getUnequippedInventoryItems().get(0).getType(), "Sword");
        assertEquals(loopmania.getUnequippedInventoryItems().get(1).getType(), "Sword");

    }


    //too many items, being removed and added the experience and golds
    @Test
    public void tooManyItems() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(1, initOrderedPath());
        character = new Character(pathPosition);
        assertEquals(character.getGolds(), 0);

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                loopmania.addUnequippedItems("Sword");
            }
        }
        loopmania.addUnequippedItems("Helmet");
        assertEquals(loopmania.getUnequippedInventoryItems().get(15).getType(), "Helmet");
        assertEquals(loopmania.getCharacter().getGolds(), 1);
        assertEquals(loopmania.getCharacter().getExperience(), 100);

    }

    @Test
    //too many cards,being removed and added the experience and golds
    public void tooManyCards() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(1, initOrderedPath());
        character = new Character(pathPosition);
        assertEquals(character.getGolds(), 0);

        for (int y = 0; y < 7; y++) {
            loopmania.addCard("VampireCastleCard");
        }
        loopmania.addCard("TrapCard");
        assertEquals(loopmania.getCardEntities().get(7).getType(), "TrapCard");
//        assertEquals(loopmania.getCharacter().getGolds(), 1);
//        assertEquals(loopmania.getCharacter().getExperience(), 50);

    }

    @Test
    public void useHealthPotionTest() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(1, initOrderedPath());
        character = new Character(pathPosition);

        assertEquals(loopmania.getHeight(), 17);
        loopmania.getGoldOnGround().add(new Gold(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        loopmania.pickUpItemsOnGround();
        loopmania.useHealthPotion();
        //assertEquals(loopmania.getCharacter().getGolds(),1);
    }

    @Test
    public void useHealthPotionWorldTest() {
        LoopManiaWorld loopmania = new LoopManiaWorld(7, 17, initOrderedPath());
        loopmania.setMode("standard");
        HeroCastleBuilding heroCastleBuilding = new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        loopmania.setHeroCastleBuilding(heroCastleBuilding);
        loopmania.setMode("standard");
        loopmania.buyItem("Potion");
        loopmania.increaseEquippedHealthPotionsNum(1);
        loopmania.getCharacter().setHealth(40);
        loopmania.useHealthPotion();
        assertEquals(60, loopmania.getCharacter().getHealth());
        assertEquals(0.6, loopmania.getCharacterHealthPercentage().get());
        assertEquals(-5, loopmania.getCharacterGold().get());
        assertEquals(0, loopmania.getCharacterExp().get());
        assertFalse(loopmania.getCharacterWin().get());
        assertFalse(loopmania.getCharacterDeath().get());
    }


    @Test
    public void testMinioWorld1() {
        List<Pair<Integer, Integer>> orderedPath = generateLargePathPosition();
        LoopManiaWorld loopmania = new LoopManiaWorld(8, 8, orderedPath);
        HeroCastleBuilding heroCastleBuilding = new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        loopmania.addEntity(heroCastleBuilding);
        loopmania.setHeroCastleBuilding(heroCastleBuilding);
        Character character = new Character(new PathPosition(0, orderedPath));
        loopmania.setCharacter(character);
        assertEquals(0, loopmania.getCycleComplete());

        List<Building> buildingEntities = loopmania.getBuildingEntities();
        VampireCastleBuilding vampireCastleBuilding = new VampireCastleBuilding(new SimpleIntegerProperty(3), new SimpleIntegerProperty(5));
        ZombiePitBuilding zombiePitBuilding = new ZombiePitBuilding(new SimpleIntegerProperty(3), new SimpleIntegerProperty(3));
        buildingEntities.add(vampireCastleBuilding);
        buildingEntities.add(zombiePitBuilding);

        CampfireCard campfireCard = new CampfireCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        loopmania.getCardEntities().add(campfireCard);

        loopmania.destroyCard(campfireCard, 0);


        WORLD_POSITION_TYPE[][] worldPositionTypes = LoopManiaUtils.updateWorldPosition(
                loopmania.getHeight(),
                loopmania.getWidth(),
                loopmania.getEnemies(),
                loopmania.getBuildingEntities(),
                loopmania.getCharacter(),
                loopmania.getGoldOnGround(),
                loopmania.getHealthPotionsOnGround(),
                orderedPath);

        loopmania.setWorldPositionType(worldPositionTypes);
        loopmania.possiblySpawnEnemies();

        for (int i = 0; i < worldPositionTypes.length; i++) {
            for (int j = 0; j < worldPositionTypes[i].length; j++) {
                if (worldPositionTypes[i][j].equals(WORLD_POSITION_TYPE.GRASS)) {
                    worldPositionTypes[i][j] = WORLD_POSITION_TYPE.PATH;
                }
            }
        }
        loopmania.setWorldPositionType(worldPositionTypes);
        worldPositionTypes = loopmania.getWorldPositionType();
        assertNotNull(worldPositionTypes);

        vampireCastleBuilding.setCycleComplete(0);
        zombiePitBuilding.setCycleComplete(0);
        loopmania.initRandomByCycleComplete();
        loopmania.runTickMoves();
        assertNotNull(loopmania.getInitLoopManiaEntityList());
    }

    @Test
    public void testMinioWorldWithFrontendCheck() {
        List<Pair<Integer, Integer>> orderedPath = generateLargePathPosition();
        LoopManiaWorld loopmania = new LoopManiaWorld(8, 8, orderedPath);
        assertFalse(loopmania.interactWithBuildings());
        assertNull(loopmania.getHeroCastle());
        assertFalse(loopmania.canBuyItem("Sword"));
        HeroCastleBuilding heroCastleBuilding = new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        List<Building> buildingEntities = loopmania.getBuildingEntities();
        loopmania.setHeroCastleBuilding(heroCastleBuilding);
        buildingEntities.add(heroCastleBuilding);
        assertNotNull(loopmania.getHeroCastle());
        Character character = new Character(new PathPosition(0, orderedPath));
        loopmania.setCharacter(character);


        BarracksBuilding barracksBuilding = new BarracksBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        buildingEntities.add(barracksBuilding);
        VillageBuilding villageBuilding = new VillageBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        buildingEntities.add(villageBuilding);

        loopmania.finishBuyAndSellAtCastle();
        assertTrue(loopmania.interactWithBuildings());

        loopmania.addCard("BarracksCard");
        loopmania.addCard("CampfireCard");
        loopmania.addCard("TowerCard");
        loopmania.addCard("VillageCard");
        loopmania.addCard("test");
        assertEquals(4, loopmania.getCardEntities().size());
        WORLD_POSITION_TYPE[][] worldPositionTypes = LoopManiaUtils.updateWorldPosition(
                loopmania.getHeight(),
                loopmania.getWidth(),
                loopmania.getEnemies(),
//                loopmania.getAlliedSoldiers(),
                loopmania.getBuildingEntities(),
                loopmania.getCharacter(),
                loopmania.getGoldOnGround(),
                loopmania.getHealthPotionsOnGround(),
                orderedPath);

        loopmania.setWorldPositionType(worldPositionTypes);
        assertFalse(loopmania.canplaceCard(0, 0, 0, 0));

        loopmania.addUnequippedItems("Armour");
        //loopmania.addUnequippedItems("TheOneRing");
        loopmania.addUnequippedItems("Staff");
        loopmania.addUnequippedItems("test");
        assertEquals(2, loopmania.getUnequippedInventoryItems().size());

        assertFalse(loopmania.canBuyItem("Sword"));

        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        loopmania.removeUnequippedInventoryItemByCoordinates(0, 0);
        loopmania.sellItem(sword.getX(), sword.getY());
        assertEquals(2, loopmania.getCharacter().getGolds());

        List<Items> unequippedInventoryItems = loopmania.getUnequippedInventoryItems();
        unequippedInventoryItems.add(new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        unequippedInventoryItems.add(new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2)));
        unequippedInventoryItems.add(new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(3)));
        unequippedInventoryItems.add(new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(4)));
        loopmania.unequippedToEquipped(0, 0, 0, 1);
        loopmania.unequippedToEquipped(0, 2, 1, 1);
        loopmania.unequippedToEquipped(0, 3, 1, 0);
        loopmania.unequippedToEquipped(0, 4, 2, 1);

        assertTrue(loopmania.isAtHeroCastle().get());
    }


    @Test
    public void testMainaWorldCardToBuilding() {
        List<Pair<Integer, Integer>> orderedPath = generateLargePathPosition();
        LoopManiaWorld loopmania = new LoopManiaWorld(8, 8, orderedPath);
        HeroCastleBuilding heroCastleBuilding = new HeroCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        List<Building> buildingEntities = loopmania.getBuildingEntities();
        loopmania.setHeroCastleBuilding(heroCastleBuilding);
        buildingEntities.add(heroCastleBuilding);
        assertNotNull(loopmania.getHeroCastle());
        Character character = new Character(new PathPosition(0, orderedPath));
        loopmania.setCharacter(character);
        WORLD_POSITION_TYPE[][] worldPositionTypes = LoopManiaUtils.updateWorldPosition(
                loopmania.getHeight(),
                loopmania.getWidth(),
                loopmania.getEnemies(),
//                loopmania.getAlliedSoldiers(),
                loopmania.getBuildingEntities(),
                loopmania.getCharacter(),
                loopmania.getGoldOnGround(),
                loopmania.getHealthPotionsOnGround(),
                orderedPath);
        List<Card> cardEntities = loopmania.getCardEntities();
        cardEntities.clear();

        worldPositionTypes[0][1] = WORLD_POSITION_TYPE.BARRACKS_CARD;
        BarracksCard barracksCard = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        cardEntities.add(barracksCard);
        loopmania.setWorldPositionType(worldPositionTypes);
        loopmania.convertCardToBuildingByCoordinates(0, 1, 5, 5);

        cardEntities.clear();
        worldPositionTypes[0][2] = WORLD_POSITION_TYPE.VAMPIRE_CASTLE_CARD;
        VampireCastleCard vampireCastleCard = new VampireCastleCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2));
        cardEntities.add(vampireCastleCard);
        loopmania.setWorldPositionType(worldPositionTypes);
        loopmania.convertCardToBuildingByCoordinates(0, 2, 5, 6);

        cardEntities.clear();
        worldPositionTypes[0][3] = WORLD_POSITION_TYPE.TOWER_CARD;
        TowerCard towerCard = new TowerCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(3));
        cardEntities.add(towerCard);
        loopmania.setWorldPositionType(worldPositionTypes);
        loopmania.convertCardToBuildingByCoordinates(0, 3, 5, 4);

        cardEntities.clear();
        worldPositionTypes[0][4] = WORLD_POSITION_TYPE.CAMPFIRE_CARD;
        CampfireCard campfireCard = new CampfireCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(4));
        cardEntities.add(campfireCard);
        loopmania.setWorldPositionType(worldPositionTypes);
        loopmania.convertCardToBuildingByCoordinates(0, 4, 5, 3);

        cardEntities.clear();
        worldPositionTypes[0][5] = WORLD_POSITION_TYPE.TRAP_CARD;
        TrapCard trapCard = new TrapCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(5));
        cardEntities.add(trapCard);
        loopmania.setWorldPositionType(worldPositionTypes);
        loopmania.convertCardToBuildingByCoordinates(0, 5, 5, 2);

        cardEntities.clear();
        worldPositionTypes[0][6] = WORLD_POSITION_TYPE.ZOMBIE_PIT_CARD;
        ZombiePitCard zombiePitCard = new ZombiePitCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(6));
        cardEntities.add(zombiePitCard);
        loopmania.setWorldPositionType(worldPositionTypes);
        loopmania.convertCardToBuildingByCoordinates(0, 6, 5, 1);

        cardEntities.clear();
        worldPositionTypes[1][0] = WORLD_POSITION_TYPE.VILLAGE_CARD;
        VillageCard villageCard = new VillageCard(new SimpleIntegerProperty(1), new SimpleIntegerProperty(0));
        cardEntities.add(villageCard);
        loopmania.setWorldPositionType(worldPositionTypes);
        loopmania.convertCardToBuildingByCoordinates(1, 0, 6, 0);

    }

    @Test
    public void EmptyHeroCastleTest() {
        LoopManiaWorld loopmania = new LoopManiaWorld(8, 8, generateLargePathPosition());
        assertEquals(null, loopmania.getHeroCastle());
        assertEquals(null, loopmania.buyItem(" "));
    }


    @Test
    public void RunTickMoveCampfireTest() {
        LoopManiaWorld world = new LoopManiaWorld(10, 17, generatePathPosition2());
        List<BasicEnemy> enemies = world.getEnemies();
        BasicEnemy vampire1 = new Vampire(new PathPosition(10, generatePathPosition2()));
        BasicEnemy vampire2 = new Vampire(new PathPosition(0, generatePathPosition2()));
        enemies.add(vampire1);
        enemies.add(vampire2);
        List<Building> buildings = world.getBuildingEntities();
        buildings.add(new CampFireBuilding(new SimpleIntegerProperty(9), new SimpleIntegerProperty(1)));
        world.runTickMoves();
        assertEquals(8, vampire1.getX());
        assertEquals(1, vampire1.getY());
        assertEquals(1, vampire2.getX());
        assertEquals(1, vampire2.getY());
        //world.runTickMoves();
    }

    @Test
    public void initNewCycleCompleteTest() {
        LoopManiaWorld world = new LoopManiaWorld(10, 17, generatePathPosition2());
        HeroCastleBuilding heroCastleBuilding = new HeroCastleBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        world.setHeroCastleBuilding(heroCastleBuilding);
        world.setCycleComplete(40);
        world.getCharacter().incrementExperience(10000);
        world.initRandomByCycleComplete();
        assertTrue(world.getEnemies().contains(ElanMuske.getInstance(new PathPosition(1, generatePathPosition2()))));
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


