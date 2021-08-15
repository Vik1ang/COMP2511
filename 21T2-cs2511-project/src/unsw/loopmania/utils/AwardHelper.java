package unsw.loopmania.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The type Award helper.
 */
public class AwardHelper {

    /**
     * Random card string.
     *
     * @param possibility the possibility
     * @param random      the random
     * @return the string
     */
    public static String randomCard(double possibility, Random random) {
        List<String> CARD_LIST = Collections.unmodifiableList(
                new ArrayList<String>() {{
                    add("VampireCastleCard");
                    add("ZombiePitCard");
                    add("TowerCard");
                    add("VillageCard");
                    add("BarracksCard");
                    add("TrapCard");
                    add("CampfireCard");
                }});
        int randomPossibility = random.nextInt(100);
        if (randomPossibility >= ((int)(possibility * 100))) return null;

        int randomNum = randomPossibility % CARD_LIST.size();
        return CARD_LIST.get(randomNum);
    }

    /**
     * Random battle items string.
     *
     * @param possibility the possibility
     * @param random      the random
     * @return the string
     */
    public static String randomBattleItems(double possibility, Random random) {
        List<String> ITEM_LIST = Collections.unmodifiableList(
                new ArrayList<String>() {{
                    add("Sword");
                    add("Stake");
                    add("Staff");
                    add("Armour");
                    add("Shield");
                    add("Helmet");
                }});
        int randomPossibility = random.nextInt(100);
        if (randomPossibility >= ((int)(possibility * 100))) return null;

        int randomNum = randomPossibility % ITEM_LIST.size();
        return ITEM_LIST.get(randomNum);
    }

    /**
     * Random health potion items string.
     *
     * @param possibility the possibility
     * @param random      the random
     * @return the string
     */
    public static String randomHealthPotionItems(double possibility, Random random) {

        int randomPossibility = random.nextInt(100);
        if (randomPossibility >= ((int)(possibility * 100))) return null;

        return "healthPotion";
    }

    /**
     * Random gold int.
     *
     * @param possibility the possibility
     * @param random      the random
     * @return the int
     */
    public static int randomGold(double possibility, Random random) {

        int randomPossibility = random.nextInt(100);
        if (randomPossibility < ((int)(possibility * 100))) return 1;
        else return 0;
    }


    public static String randomRareItems(double possibility, Random random) {
        int randomPossibility = random.nextInt(100);
        if (randomPossibility >= ((int)(possibility * 100))) return null;
        else return "Rare";
    }

}
