package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import unsw.loopmania.utils.AwardHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AwardTest {
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

    private int array0[] = {60, 48, 29, 47, 15, 53, 91, 61};
    private int array1[] = {85, 88, 47, 13, 54, 4, 34, 6};
    private int array2[] = {8, 72, 40, 67, 89, 50, 6, 19};
    private int array3[] = {34, 60, 10, 81, 28, 2, 49, 64};

    @Test
    public void GoldAwardTest1() {
        // seed2: 8 72 40 67 89 50 6 19
        Random random = new Random(2);
        assertEquals(1, AwardHelper.randomGold(0.5, random));
        assertEquals(0, AwardHelper.randomGold(0.5, random));
        assertEquals(1, AwardHelper.randomGold(0.5, random));
        assertEquals(0, AwardHelper.randomGold(0.5, random));
        assertEquals(0, AwardHelper.randomGold(0.5, random));
        assertEquals(0, AwardHelper.randomGold(0.5, random));
        assertEquals(1, AwardHelper.randomGold(0.5, random));
        assertEquals(1, AwardHelper.randomGold(0.5, random));
    }

    @Test
    public void GoldAwardTest2() {
        // seed2: 8 72 40 67 89 50 6 19
        Random random = new Random(2);
        assertEquals(1, AwardHelper.randomGold(0.2, random));
        assertEquals(0, AwardHelper.randomGold(0.2, random));
        assertEquals(0, AwardHelper.randomGold(0.2, random));
        assertEquals(0, AwardHelper.randomGold(0.2, random));
        assertEquals(0, AwardHelper.randomGold(0.2, random));
        assertEquals(0, AwardHelper.randomGold(0.2, random));
        assertEquals(1, AwardHelper.randomGold(0.2, random));
        assertEquals(1, AwardHelper.randomGold(0.2, random));
    }

    @Test
    public void HealthPotionAwardTest() {
        // seed3: 34 60 10 81 28 2 49 64
        Random random = new Random(3);
        for (int i = 0; i < 8; i++) {
            String h = AwardHelper.randomHealthPotionItems(0.2, random);
            if (array3[i] < 20) assertTrue(h != null);
            else assertTrue(h == null);
        }
    }

    @Test
    public void CardAwardTest() {
        // seed0: 60 48 29 47 15 53 91 61
        Random random = new Random(0);
        for (int i = 0; i < 8; i++) {
            String card = AwardHelper.randomCard(0.2, random);
            if (array0[i] < 20) {
                assertTrue(card != null);
                assertEquals(cardList.get(array0[i] % 7), card);
            }
            else assertTrue(card == null);
        }
    }

    @Test
    public void ItemAwardTest1() {
        // seed1: 85 88 47 13 54 4 34 6
        Random random = new Random(1);
        for (int i = 0; i < 8; i++) {
            String item = AwardHelper.randomBattleItems(0.2, random);
            if (array1[i] < 20) {
                assertTrue(item != null);
                assertEquals(itemList.get(array1[i] % 6), item);
            }
            else assertTrue(item == null);
        }
    }

    @Test
    public void ItemAwardTest2() {
        // seed1: 85 88 47 13 54 4 34 6
        Random random = new Random(1);
        for (int i = 0; i < 8; i++) {
            String item = AwardHelper.randomBattleItems(0.5, random);
            if (array1[i] < 50) {
                assertTrue(item != null);
                assertEquals(itemList.get(array1[i] % 6), item);
            }
            else assertTrue(item == null);
        }
    }




}
