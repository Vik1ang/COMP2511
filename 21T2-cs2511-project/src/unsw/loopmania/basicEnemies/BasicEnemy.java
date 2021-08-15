package unsw.loopmania.basicEnemies;

import org.javatuples.Pair;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.RareItemsFactory;
import unsw.loopmania.alliedSoldiers.AlliedSoldier;
import unsw.loopmania.character.Character;
import unsw.loopmania.utils.AwardHelper;
import unsw.loopmania.utils.MathUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * a basic form of enemy in the world
 */
public abstract class BasicEnemy extends MovingEntity {
    private double cardAwardPossibility;
    private double itemAwardPossibility;
    private double healthPotionAwardPossibility;
    private double goldAwardPossibility;
    private double rareItemPossibility;

    private boolean trance;
    private int battleRadius;
    private int supportRadius;
    private int experience;
    private int bite;
    private boolean doggieCoinAward;
    private Random random;

    /**
     * Instantiates a new Basic enemy.
     *
     * @param position the position
     */
    public BasicEnemy(PathPosition position) {
        super(position);
        random = new Random(209);
        doggieCoinAward = false;
    }

    /**
     * Gets random.
     *
     * @return the random
     */
    public Random getRandom() {
        return random;
    }

    /**
     * move the enemy
     *
     * @param enemyList    the enemy list
     * @param character    the character
     * @param campFireList the camp fire list
     */
    public void move(List<BasicEnemy> enemyList, Character character, List<Pair<Integer, Integer>> campFireList) {
        int characterX = character.getX();
        int characterY = character.getY();
        int x = this.getX();
        int y = this.getY();
        // 1. if in battleRadius, do not move
        //int enemyCharacterDistance = (int) MathUtils.getDistance(characterX, characterY, x, y);
        //if (enemyCharacterDistance <= battleRadius) {
        //    return;
        //}
        if (insideBattleRadius(characterX, characterY)) return;
        boolean flag = false;
        // 2. if not in battleRadius and in supportRadius it has alliedEnemy and alliedEnemy's battle radius has character
        for (BasicEnemy basicEnemy : enemyList) {
            if (basicEnemy == this) {
                continue;
            }
            int alliedEnemyX = basicEnemy.getX();
            int alliedEnemyY = basicEnemy.getY();
            if (basicEnemy.insideBattleRadius(characterX, characterY) && insideSupportRadius(alliedEnemyX, alliedEnemyY)) {
                // move to
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
            int directionChoice = random.nextInt(100);
            if (directionChoice < 15) {
                moveDownPath();
            } else if (directionChoice < 30) {
                moveUpPath();
            }
        }
    }

    /**
     * Sets bite.
     *
     * @param bite the bite
     */
    public void setBite(int bite) {
        this.bite = bite;
    }

    /**
     * Gets bite.
     *
     * @return the bite
     */
    public int getBite() {
        return this.bite;
    }

    public int getBiteCharacter(Character character, Boolean hasShield) {
        return getBite();
    }
    /**
     * Gets bite allied soldier.
     *
     * @param alliedSoldier the allied soldier
     * @return the bite allied soldier
     */
    public int getBiteAlliedSoldier(AlliedSoldier alliedSoldier) {
        return getBite();
    }

    /**
     * Sets trance.
     *
     * @param trance the trance
     */
    public void setTrance(boolean trance) {
        this.trance = trance;
    }

    /**
     * Gets trance.
     *
     * @return the trance
     */
    public boolean getTrance() {
        return trance;
    }

    /**
     * Got attack.
     *
     * @param attack the attack
     */
    public void gotAttack(int attack) {
        int currHealth = getHealth();
        setHealth(Math.max(0, currHealth - attack));
    }

    /**
     * Gets experience.
     *
     * @return the experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Sets experience.
     *
     * @param experience the experience
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Gets battle radius.
     *
     * @return the battle radius
     */
    public int getBattleRadius() {
        return battleRadius;
    }

    /**
     * Gets support radius.
     *
     * @return the support radius
     */
    public int getSupportRadius() {
        return supportRadius;
    }

    /**
     * Sets battle radius.
     *
     * @param battleRadius the battle radius
     */
    public void setBattleRadius(int battleRadius) {
        this.battleRadius = battleRadius;
    }

    /**
     * Sets support radius.
     *
     * @param supportRadius the support radius
     */
    public void setSupportRadius(int supportRadius) {
        this.supportRadius = supportRadius;
    }



    /**
     * Sets card award possibility.
     *
     * @param cardAwardPossibility the card award possibility
     */
    public void setCardAwardPossibility(double cardAwardPossibility) {
        this.cardAwardPossibility = cardAwardPossibility;
    }


    /**
     * Sets item award possibility.
     *
     * @param itemAwardPossibility the item award possibility
     */
    public void setItemAwardPossibility(double itemAwardPossibility) {
        this.itemAwardPossibility = itemAwardPossibility;
    }

    /**
     * Sets health potion award possibility.
     *
     * @param healthPotionAwardPossibility the health potion award possibility
     */
    public void setHealthPotionAwardPossibility(double healthPotionAwardPossibility) {
        this.healthPotionAwardPossibility = healthPotionAwardPossibility;
    }

    /**
     * Sets gold award possibility.
     *
     * @param goldAwardPossibility the gold award possibility
     */
    public void setGoldAwardPossibility(double goldAwardPossibility) {
        this.goldAwardPossibility = goldAwardPossibility;
    }

    /**
     * Sets rare item possibility.
     *
     * @param rareItemPossibility the rare item possibility
     */
    public void setRareItemPossibility(double rareItemPossibility) {
        this.rareItemPossibility = rareItemPossibility;
    }

    /**
     * Inside battle radius boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean insideBattleRadius(int x, int y) {
        int distance = (int) MathUtils.getDistance(x, y, getX(), getY());
        if (getBattleRadius() >= distance) return true;
        return false;
    }

    public boolean insideSupportRadius(int x, int y) {
        int distance = (int) MathUtils.getDistance(x, y, getX(), getY());
        if (getSupportRadius() >= distance) return true;
        return false;
    }


    /**
     * Check death boolean.
     *
     * @return the boolean
     */
    public boolean checkDeath() {
        if (getHealth() <= 0) return true;
        return false;
    }


    /**
     * Gets gold award.
     *
     * @param random the random
     * @return the gold award
     */
    public int getGoldAward(Random random) {
        return AwardHelper.randomGold(goldAwardPossibility, random);
    }

    /**
     * Gets card award.
     *
     * @param random the random
     * @return the card award
     */
    public String getCardAward(Random random) {
        return AwardHelper.randomCard(cardAwardPossibility, random);
    }

    /**
     * Gets health potion award.
     *
     * @param random the random
     * @return the health potion award
     */
    public String getHealthPotionAward(Random random) {
        return AwardHelper.randomHealthPotionItems(healthPotionAwardPossibility, random);
    }

    /**
     * Gets battle items award.
     *
     * @param random the random
     * @return the battle items award
     */
    public String getBattleItemsAward(Random random) {
        return AwardHelper.randomBattleItems(itemAwardPossibility, random);
    }


    public String getRareItemsAward(Random random) {
        return AwardHelper.randomRareItems(rareItemPossibility, random);
    }


    /**
     * Gets award.
     *
     * @param character the character
     * @return HashMap of award
     */
    public HashMap<String, String> getAward(Character character) {
        HashMap<String, String> award = new HashMap<>();
        if (!getTrance()) {
            // random awards
            int gold = getGoldAward(character.getRandomGold());
            String healthPotion = getHealthPotionAward(character.getRandomHealthPotion());
            String item = getBattleItemsAward(character.getRandomItem());
            String card = getCardAward(character.getRandomCard());
            String rare = getRareItemsAward(character.getRandomRare());
            // update loop mania world info
            // update gold
            character.incrementGolds(gold);
            // increase experience
            character.incrementExperience(getExperience());
            if (item != null) award.put("item", item);
            if (card != null) award.put("card", card);
            if (healthPotion != null) award.put("healthPotion", healthPotion);
            if (rare != null) award.put("rare", rare);
        }
        return award;
    }
}


