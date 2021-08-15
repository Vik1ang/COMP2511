package unsw.loopmania.basicEnemies;

import org.javatuples.Pair;
import unsw.loopmania.PathPosition;
import unsw.loopmania.alliedSoldiers.AlliedSoldier;
import unsw.loopmania.character.Character;
import unsw.loopmania.utils.MathUtils;

import java.util.List;
import java.util.Random;

/**
 * The type Vampire.
 */
public class Vampire extends BasicEnemy {

    private final double CARD_AWARD_POSSIBILITY = 0.5;
    private final double ITEM_AWARD_POSSIBILITY = 0.3;
    private final double HEALTH_POTION_AWARD_POSSIBILITY = 0;
    private final double GOLD_AWARD_POSSIBILITY = 0.5;
    private static final double RARE_POSSIBILITY = 0.03;

    private final int INIT_HEALTH = 50;
    private final int EXP = 1000;
    private final int BITE = 40;
    private final int BITE_SEED = 0;
    private final int DAMAGE_SEED = 4;

    private Random randomCriticalBite;
    private Random randomCriticalBiteDamage;

    /**
     * Instantiates a new Vampire.
     *
     * @param position the position
     */
    public Vampire(PathPosition position) {
        super(position);
        setBattleRadius(5);
        setSupportRadius(6);
        setBite(BITE);
        setHealth(INIT_HEALTH);
        setCardAwardPossibility(CARD_AWARD_POSSIBILITY);
        setItemAwardPossibility(ITEM_AWARD_POSSIBILITY);
        setHealthPotionAwardPossibility(HEALTH_POTION_AWARD_POSSIBILITY);
        setGoldAwardPossibility(GOLD_AWARD_POSSIBILITY);
        setRareItemPossibility(RARE_POSSIBILITY);
        setExperience(EXP);
        randomCriticalBite = new Random(BITE_SEED);
        randomCriticalBiteDamage = new Random(DAMAGE_SEED);
    }

    /**
     * move the enemy
     */
    @Override
    public void move(List<BasicEnemy> enemyList, Character character, List<Pair<Integer, Integer>> campFireList) {
        // if has campfire
        if (campFireList.size() > 0) {
            int min = Integer.MAX_VALUE;
            Pair<Integer, Integer> minCampfire = null;
            for (Pair<Integer, Integer> campfire : campFireList) {
                int distance = (int) MathUtils.getDistance(campfire.getValue0(), campfire.getValue1(), this.getX(), this.getY());
                min = Math.min(distance, min);
                minCampfire = campfire;
            }
            if (this.getSupportRadius() > min) {
                if (minCampfire.getValue0() > this.getX() || minCampfire.getValue1() > this.getY()) {
                    moveUpPath();
                } else {
                    moveDownPath();
                }
            } else {
                super.move(enemyList, character, campFireList);
            }
        } else {
            super.move(enemyList, character, campFireList);
        }
    }


    /**
     * Gets bite.
     *
     * @param hasShield the has shield
     * @return the bite
     */
    @Override
    public int getBiteCharacter(Character character, Boolean hasShield) {
        int totalDamage = this.getBite();
        int randNumber = randomCriticalBite.nextInt(100);
        if ((randNumber >= 60 && hasShield) || (!hasShield))
            totalDamage += criticalBite();
        return totalDamage;
    }

    @Override
    public int getBiteAlliedSoldier(AlliedSoldier alliedSoldier) {
        return getBiteCharacter(null, false);
    }

    private int criticalBite() {
        int randNumber = randomCriticalBiteDamage.nextInt(100);
        if (randNumber < 30) return randNumber;
        else return 0;
    }


}
