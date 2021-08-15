package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Test;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.basicEnemies.*;
import unsw.loopmania.card.*;
import unsw.loopmania.character.Character;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.rare.AndurilFlameOfTheWest;
import unsw.loopmania.items.rare.Rare;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.weapon.Stake;
import unsw.loopmania.items.weapon.Sword;
import unsw.loopmania.utils.PreservedWord;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AwardIntegrationTest {
    public List<Pair<Integer, Integer>> initOrderedPath() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(0, 3));
        orderedPath.add(Pair.with(1, 3));
        orderedPath.add(Pair.with(2, 3));
        orderedPath.add(Pair.with(2, 4));
        orderedPath.add(Pair.with(1, 4));
        orderedPath.add(Pair.with(0, 4));
        return orderedPath;
    }

    List<String> itemList = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("Sword");
                add("Stake");
                add("Staff");
                add("Armour");
                add("Shield");
                add("Helmet");
            }});

    List<String> cardList = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("VampireCastleCard");
                add("ZombiePitCard");
                add("TowerCard");
                add("VillageCard");
                add("BarracksCard");
                add("TrapCard");
                add("CampfireCard");
            }});

    List<String> availableRareItems = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add(PreservedWord.itemTheOneRing);
                add(PreservedWord.itemAndurilFlameOfTheWest);
                add(PreservedWord.itemTreeStump);
            }});

    @Test
    public void ZombieAwardTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        world.setMode(PreservedWord.gameModeStandard);
        Character character = world.getCharacter();
        BasicEnemy zombie = new Zombie(new PathPosition(1, initOrderedPath()));
        // card-seed0:   60 48 29 47 15 53 91 61
        // item-seed1:   85 88 47 13 54 4  34 6
        // gold-seed2:   8  72 40 67 89 50 6  19
        // health-seed3: 34 60 10 81 28 2  49 64
        // CARD_AWARD_POSSIBILITY = 0.4;
        // ITEM_AWARD_POSSIBILITY = 0.2;
        // HEALTH_POTION_AWARD_POSSIBILITY = 0.2;
        // GOLD_AWARD_POSSIBILITY = 0.4;
        // 1
        List<StaticEntity> award = world.getAward(zombie);
        assertTrue(award.size() == 0);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 2
        award = world.getAward(zombie);
        assertTrue(award.size() == 0);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 3
        award = world.getAward(zombie);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof ZombiePitCard);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 4
        award = world.getAward(zombie);
        assertTrue(award.size() == 0);
        //assertTrue(award.get(0) instanceof Stake);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 5
        award = world.getAward(zombie);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof ZombiePitCard);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 6
        award = world.getAward(zombie);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof Shield);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 7
        award = world.getAward(zombie);
        assertTrue(award.size() == 0);
        assertEquals(2, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 8
        award = world.getAward(zombie);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof Sword);
        assertEquals(3, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
    }

    @Test
    public void SlugAwardTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        world.setMode(PreservedWord.gameModeStandard);
        Character character = world.getCharacter();
        BasicEnemy slug = new Slug(new PathPosition(1, initOrderedPath()));
        // card-seed0:   60 48 29 47 15 53 91 61
        // item-seed1:   85 88 47 13 54 4  34 6
        // gold-seed2:   8  72 40 67 89 50 6  19
        // health-seed3: 34 60 10 81 28 2  49 64
        // CARD_AWARD_POSSIBILITY = 0.3;
        // ITEM_AWARD_POSSIBILITY = 0.1;
        // HEALTH_POTION_AWARD_POSSIBILITY = 0.1;
        // GOLD_AWARD_POSSIBILITY = 0.3;
        // 1
        List<StaticEntity> award = world.getAward(slug);
        assertTrue(award.size() == 0);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 2
        award = world.getAward(slug);
        assertTrue(award.size() == 0);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 3
        award = world.getAward(slug);
        assertTrue(award.size() == 0);
        //assertTrue(award.get(0) instanceof ZombiePitCard);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 4
        award = world.getAward(slug);
        assertTrue(award.size() == 0);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 5
        award = world.getAward(slug);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof ZombiePitCard);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 6
        award = world.getAward(slug);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof Shield);
        assertEquals(1, character.getGolds());
        assertEquals(1, world.getEquippedHealthPotionsNum().get());
        // 7
        award = world.getAward(slug);
        assertTrue(award.size() == 0);
        assertEquals(2, character.getGolds());
        assertEquals(1, world.getEquippedHealthPotionsNum().get());
        // 8
        award = world.getAward(slug);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof Sword);
        assertEquals(3, character.getGolds());
        assertEquals(1, world.getEquippedHealthPotionsNum().get());
    }

    @Test
    public void VampireAwardTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        Character character = world.getCharacter();
        BasicEnemy vampire = new Vampire(new PathPosition(1, initOrderedPath()));
        // card-seed0:   60 48 29 47 15 53 91 61
        // item-seed1:   85 88 47 13 54 4  34 6
        // gold-seed2:   8  72 40 67 89 50 6  19
        // health-seed3: 34 60 10 81 28 2  49 64
        // CARD_AWARD_POSSIBILITY = 0.5;
        // ITEM_AWARD_POSSIBILITY = 0.3;
        // HEALTH_POTION_AWARD_POSSIBILITY = 0.3;
        // GOLD_AWARD_POSSIBILITY = 0.5;
        // 1
        List<StaticEntity> award = world.getAward(vampire);
        assertTrue(award.size() == 0);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 2
        award = world.getAward(vampire);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof CampfireCard);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 3
        award = world.getAward(vampire);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof ZombiePitCard);
        assertEquals(2, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 4
        award = world.getAward(vampire);
        assertTrue(award.size() == 2);
        assertTrue(award.get(0) instanceof Stake);
        assertTrue(award.get(1) instanceof TrapCard);
        assertEquals(2, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 5
        award = world.getAward(vampire);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof ZombiePitCard);
        assertEquals(2, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 6
        award = world.getAward(vampire);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof Shield);
        assertEquals(2, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 7
        award = world.getAward(vampire);
        assertTrue(award.size() == 0);
        assertEquals(3, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 8
        award = world.getAward(vampire);
        assertTrue(award.size() == 1);
        assertTrue(award.get(0) instanceof Sword);
        assertEquals(4, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
    }


    @Test
    public void DoggieAwardTest() {
        // card-seed0:   60 48 29 47 15 53 91 61
        // item-seed1:   85 88 47 13 54 4  34 6
        // gold-seed2:   8  72 40 67 89 50 6  19
        // health-seed3: 34 60 10 81 28 2  49 64
        // private final double CARD_AWARD_POSSIBILITY = 0.3;
        // private final double ITEM_AWARD_POSSIBILITY = 0.1;
        // private final double HEALTH_POTION_AWARD_POSSIBILITY = 0.1;
        // private final double GOLD_AWARD_POSSIBILITY = 0.3;

        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        Character character = world.getCharacter();
        BasicEnemy doggie = new Doggie(new PathPosition(1, initOrderedPath()));


        // 1
        List<StaticEntity> award = world.getAward(doggie);
        assertTrue(award.size() == 1);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 2
        award = world.getAward(doggie);
        assertTrue(award.size() == 1);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 3
        award = world.getAward(doggie);
        assertTrue(award.size() == 2);
        assertTrue(award.get(1) instanceof ZombiePitCard);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 4
        award = world.getAward(doggie);
        assertTrue(award.size() == 1);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 5
        award = world.getAward(doggie);
        assertTrue(award.size() == 2);
        assertTrue(award.get(1) instanceof ZombiePitCard);
        assertEquals(1, character.getGolds());
        assertEquals(0, world.getEquippedHealthPotionsNum().get());
        // 6
        award = world.getAward(doggie);
        assertTrue(award.size() == 2);
        assertTrue(award.get(0) instanceof Shield);
        assertEquals(1, character.getGolds());
        assertEquals(1, world.getEquippedHealthPotionsNum().get());
        // 7
        award = world.getAward(doggie);
        assertTrue(award.size() == 1);
        assertEquals(2, character.getGolds());
        assertEquals(1, world.getEquippedHealthPotionsNum().get());
        // 8
        award = world.getAward(doggie);
        assertTrue(award.size() == 2);
        assertTrue(award.get(0) instanceof Sword);
        assertEquals(3, character.getGolds());
        assertEquals(1, world.getEquippedHealthPotionsNum().get());
    }


    @Test
    public void SlugRareAwardStandardTest() {
        // 13 25 79 39 4  38 77 78 65 31
        // 8  12 17 96 42 50 69 72 86 55
        // 17 57 63 43 20 0 < 2
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        world.setMode(PreservedWord.gameModeStandard);
        world.setAvailableRareItems(availableRareItems);
        BasicEnemy slug = new Slug(new PathPosition(1, initOrderedPath()));
        for (int i = 0; i < 30; i++) {
            List<StaticEntity> award = world.getAward(slug);
            int rareNum = 0;
            for (StaticEntity items: award) {
                if (items instanceof TheOneRing) rareNum++;
            }
            if (i == 25) assertEquals(1, rareNum);
            else assertEquals(0, rareNum);
        }
    }

    @Test
    public void SlugRareAwardConfusingTest() {
        // 13 25 79 39 4  38 77 78 65 31
        // 8  12 17 96 42 50 69 72 86 55
        // 17 57 63 43 20 0 < 2
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        world.setMode(PreservedWord.gameModeConfusing);
        world.setAvailableRareItems(availableRareItems);
        BasicEnemy slug = new Slug(new PathPosition(1, initOrderedPath()));
        for (int i = 0; i < 30; i++) {
            List<StaticEntity> award = world.getAward(slug);
            int rareNum = 0;
            TheOneRing rare = null;
            for (StaticEntity items: award) {
                if (items instanceof TheOneRing) {
                    rareNum++;
                    rare = (TheOneRing) items;
                }
            }
            if (i == 25) {
                assertEquals(1, rareNum);
                assertTrue(rare.getSecondaryAbility() instanceof AndurilFlameOfTheWest);
            }
            else assertEquals(0, rareNum);
        }
    }

    @Test
    public void ZombieAwardFullCardTest() {
        LoopManiaWorld world = new LoopManiaWorld(7, 17, initOrderedPath());
        world.setMode(PreservedWord.gameModeStandard);
        ArrayList<Card> cards = (ArrayList<Card>) world.getCardEntities();
        // add 10 cards
        for (int i = 0; i < 10; i++) {
            world.addCard(PreservedWord.cardTower);
        }
        Character character = world.getCharacter();
        BasicEnemy zombie = new SlugZombie(new PathPosition(1, initOrderedPath()));
        world.getAward(zombie);
        world.getAward(zombie);
        world.getAward(zombie);
        world.getAward(zombie);
        world.getAward(zombie);

        assertEquals(3, character.getGolds());
        assertEquals(2600, character.getExperience());
        assertEquals(10, world.getCardEntities().size());
    }
}
