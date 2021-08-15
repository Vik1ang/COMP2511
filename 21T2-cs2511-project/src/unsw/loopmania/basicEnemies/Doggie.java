package unsw.loopmania.basicEnemies;

import org.javatuples.Pair;
import unsw.loopmania.PathPosition;
import unsw.loopmania.RareItemsFactory;
import unsw.loopmania.alliedSoldiers.AlliedSoldier;
import unsw.loopmania.character.Character;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Doggie extends BasicEnemy{

    private final double CARD_AWARD_POSSIBILITY = 0.3;
    private final double ITEM_AWARD_POSSIBILITY = 0.1;
    private final double HEALTH_POTION_AWARD_POSSIBILITY = 0.1;
    private final double GOLD_AWARD_POSSIBILITY = 0.3;
    private static final double RARE_POSSIBILITY = 0.04;

    private final int BITE = 15;
    private final int INIT_HEALTH = 80;
    private final int EXP = 1500;
    // seed 4: 62 52 3 58 67 5 11 46
    private final int STUN_SEED = 4;
    private Random randomStun;

    /**
     * Instantiates a new Basic enemy.
     *
     * @param position the position
     */
   public Doggie(PathPosition position) {
        super(position);
        setBattleRadius(2);
        setSupportRadius(3);
        setBite(BITE);
        setHealth(INIT_HEALTH);
        setCardAwardPossibility(CARD_AWARD_POSSIBILITY);
        setItemAwardPossibility(ITEM_AWARD_POSSIBILITY);
        setHealthPotionAwardPossibility(HEALTH_POTION_AWARD_POSSIBILITY);
        setGoldAwardPossibility(GOLD_AWARD_POSSIBILITY);
        setRareItemPossibility(RARE_POSSIBILITY);
        setExperience(EXP);
        randomStun = new Random(STUN_SEED);
    }

    /**
     * move the enemy
     */
    @Override
    public void move(List<BasicEnemy> enemyList, Character character, List<Pair<Integer, Integer>> campFireList){
        super.move(enemyList, character, campFireList);
    }

    @Override
    public HashMap<String, String> getAward(Character character) {
        HashMap<String, String> award = super.getAward(character);
        award.put("DoggieCoin", "DoggieCoin");
        return award;
    }

    @Override
    public int getBiteCharacter(Character character, Boolean hasShield) {
        int randomInt = randomStun.nextInt(100);
        if (randomInt < 10) character.setStun(true);
        return getBite();
    }




    @Override
    public int getBiteAlliedSoldier(AlliedSoldier alliedSoldier) {
        return super.getBite();
    }
}
