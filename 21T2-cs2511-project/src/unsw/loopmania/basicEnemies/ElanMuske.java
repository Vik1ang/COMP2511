package unsw.loopmania.basicEnemies;

import org.javatuples.Pair;
import unsw.loopmania.PathPosition;
import unsw.loopmania.character.Character;
import unsw.loopmania.utils.MathUtils;

import java.util.List;
import java.util.Random;

public class ElanMuske extends BasicEnemy {
    private final double CARD_AWARD_POSSIBILITY = 0.5;
    private final double ITEM_AWARD_POSSIBILITY = 0.3;
    private final double HEALTH_POTION_AWARD_POSSIBILITY = 0.1;
    private final double GOLD_AWARD_POSSIBILITY = 0.5;
    private static final double RARE_POSSIBILITY = 0.05;

    private final int BITE = 50;
    private final int INIT_HEALTH = 60;
    private final int EXP = 1500;

    private final int MOVE_SPEED = 0;
    private Random randomMove;

    private static ElanMuske elanMuske;
    private int cycleComplete;

    /**
     * Instantiates a new Basic enemy.
     *
     * @param position the position
     */
    private ElanMuske(PathPosition position) {
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
        randomMove = new Random(MOVE_SPEED);
        cycleComplete = 0;
    }

    public static synchronized ElanMuske getInstance(PathPosition position) {
        if (elanMuske == null)
            elanMuske = new ElanMuske(position);
        return elanMuske;
    }

    public static void setNull() {
        elanMuske = null;
    }

    public static boolean isElanMuskeCreated() {
        return elanMuske != null;
    }

    /**
     * move the enemy
     */
    @Override
    public void move(List<BasicEnemy> enemyList, Character character, List<Pair<Integer, Integer>> campFireList) {
        PathPosition position = getPosition();
        List<Pair<Integer, Integer>> orderedPath = position.getOrderedPath();
        super.moveToPath(randomMove.nextInt(100) % orderedPath.size());
        if (cycleComplete < 5) {
            int loop = 0;
            while (insideBattleRadius(character.getX(), character.getY()) && loop < 10) {
                super.moveToPath(randomMove.nextInt(100) % orderedPath.size());
                loop++;
            }
        }
        if (character.getX() == 0 && character.getY() == 0) cycleComplete++;


    }
}
