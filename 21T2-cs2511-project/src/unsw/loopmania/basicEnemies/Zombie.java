package unsw.loopmania.basicEnemies;

import org.javatuples.Pair;
import unsw.loopmania.PathPosition;
import unsw.loopmania.alliedSoldiers.AlliedSoldier;
import unsw.loopmania.character.Character;
import unsw.loopmania.utils.MathUtils;

import java.util.List;
import java.util.Random;

/**
 * The type Zombie.
 */
public class Zombie extends BasicEnemy {

    private final double CARD_AWARD_POSSIBILITY = 0.3;
    private final double ITEM_AWARD_POSSIBILITY = 0.1;
    private final double HEALTH_POTION_AWARD_POSSIBILITY = 0;
    private final double GOLD_AWARD_POSSIBILITY = 0.4;
    private static final double RARE_POSSIBILITY = 0.03;

    private final int INIT_HEALTH = 20;
    private final int BITE = 30;
    private final int EXP = 500;

    private final int TRANSFER_SEED = 0;
    private Random randomTransfer;
    private final int TRANSFER_RATE = 30;
    private int transferRate;

    /**
     * Instantiates a new Zombie.
     *
     * @param position the position
     */
    public Zombie(PathPosition position) {
        super(position);
        setBite(BITE);
        setBattleRadius(3);
        setSupportRadius(5);
        setHealth(INIT_HEALTH);
        setCardAwardPossibility(CARD_AWARD_POSSIBILITY);
        setItemAwardPossibility(ITEM_AWARD_POSSIBILITY);
        setHealthPotionAwardPossibility(HEALTH_POTION_AWARD_POSSIBILITY);
        setGoldAwardPossibility(GOLD_AWARD_POSSIBILITY);
        setRareItemPossibility(RARE_POSSIBILITY);
        setExperience(EXP);
        randomTransfer = new Random(TRANSFER_SEED);
        setTransferRate(TRANSFER_RATE);
    }

    /**
     * move the enemy
     */
    public void move(List<BasicEnemy> enemyList, Character character, List<Pair<Integer, Integer>> campFireList) {
        int characterX = character.getX();
        int characterY = character.getY();
        int x = this.getX();
        int y = this.getY();
        // 1. if in battleRadius, do not move
        int enemyCharacterDistance = (int) MathUtils.getDistance(characterX, characterY, x, y);
        if (enemyCharacterDistance <= this.getBattleRadius()) {
            return;
        }
        boolean flag = false;
        // 2. if not in battleRadius and in supportRadius it has alliedEnemy and alliedEnemy's battle radius has character
        for (BasicEnemy basicEnemy : enemyList) {
            if (basicEnemy == this) {
                continue;
            }
            int alliedEnemyX = basicEnemy.getX();
            int alliedEnemyY = basicEnemy.getY();
            enemyCharacterDistance = (int) MathUtils.getDistance(alliedEnemyX, alliedEnemyY, characterX, characterY);
            int enemyAlliedDistance = (int) MathUtils.getDistance(alliedEnemyX, alliedEnemyY, x, y);
            if (enemyCharacterDistance <= basicEnemy.getBattleRadius() && enemyAlliedDistance <= this.getSupportRadius()) {
                // move to
                int moveRand = this.getRandom().nextInt(100);
                if ( moveRand < 60) {
                    return;
                }
                if (alliedEnemyX > x || alliedEnemyY > y) {
                    moveDownPath();
                } else {
                    moveUpPath();
                }
                flag = true;
                break;
            }
        }
        // 3. just go random
        if (!flag) {
            // random move
            int directionChoice = super.getRandom().nextInt(100);
            if (directionChoice <= 10) {
                moveUpPath();
            } else if (directionChoice >= 90) {
                moveDownPath();
            }
        }
    }

    public int getTransferRate() {
        return transferRate;
    }

    public void setTransferRate(int transferRate) {
        this.transferRate = transferRate;
    }

    @Override
    public int getBiteAlliedSoldier(AlliedSoldier alliedSoldier) {
        int randNumber = randomTransfer.nextInt(100);
        if (randNumber < getTransferRate()) {
            transferAlliedSoldier(alliedSoldier);
            return 0;
        }
        return getBite();
    }

    /**
     * Transfer allied soldier.
     *
     * @param alliedSoldier the allied soldier
     */
    public void transferAlliedSoldier(AlliedSoldier alliedSoldier) {
        alliedSoldier.setTransferred(true);
    }
}
