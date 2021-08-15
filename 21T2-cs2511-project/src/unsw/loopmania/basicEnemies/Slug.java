package unsw.loopmania.basicEnemies;

import org.javatuples.Pair;
import unsw.loopmania.PathPosition;
import unsw.loopmania.character.Character;

import java.util.List;

/**
 * The type Slug.
 */
public class Slug extends BasicEnemy {

    private static final double CARD_AWARD_POSSIBILITY = 0.2;
    private static final double ITEM_AWARD_POSSIBILITY = 0.1;
    private static final double HEALTH_POTION_AWARD_POSSIBILITY = 0.1;
    private static final double GOLD_AWARD_POSSIBILITY = 0.3;
    private static final double RARE_POSSIBILITY = 0.02;

    private final int INIT_HEALTH = 20;
    private final int EXP = 100;
    private final int BITE = 10;

    /**
     * Instantiates a new Slug.
     *
     * @param position the position
     */
    public Slug(PathPosition position) {
        super(position);
        setBite(BITE);
        setBattleRadius(2);
        setSupportRadius(3);
        setHealth(INIT_HEALTH);
        setCardAwardPossibility(CARD_AWARD_POSSIBILITY);
        setItemAwardPossibility(ITEM_AWARD_POSSIBILITY);
        setHealthPotionAwardPossibility(HEALTH_POTION_AWARD_POSSIBILITY);
        setGoldAwardPossibility(GOLD_AWARD_POSSIBILITY);
        setRareItemPossibility(RARE_POSSIBILITY);
        setExperience(EXP);
    }

    /**
     * move the enemy
     */
    @Override
    public void move(List<BasicEnemy> enemyList, Character character, List<Pair<Integer, Integer>> campFireList){
        super.move(enemyList, character, campFireList);
    }

}
