package unsw.loopmania.character;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.basicEnemies.ElanMuske;
import unsw.loopmania.items.Gold;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.Items;
import unsw.loopmania.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {

    private final static int INIT_HEALTH = 100;
    private final static int ATTACK = 15;

    private SimpleIntegerProperty cycleComplete;
    private SimpleDoubleProperty healthPercentage;
    private SimpleIntegerProperty experience;
    private SimpleIntegerProperty golds;
    private SimpleBooleanProperty isWin;
    private SimpleBooleanProperty isDead;

    private Random randomCard;
    private Random randomItem;
    private Random randomGold;
    private Random randomHealthPotion;
    private Random randomRare;
    // seed0: 60 48 29 47 15 53 91 61
    private int CARD_SEED = 0;
    // seed1: 85 88 47 13 54 4 34 6
    private int ITEM_SEED = 1;
    // seed2: 8 72 40 67 89 50 6 19
    private int GOLD_SEED = 2;
    // seed3: 34 60 10 81 28 2 49 64
    private int HEALTH_POTION_SEED = 3;
    // seed3: 34 60 10 81 28 2 49 64
    private int RARE_SEED = -1;

    private int health;
    private boolean isStun;

    /**
     * Instantiates a new Character.
     *
     * @param position the position
     */
    public Character(PathPosition position) {
        super(position);
        this.cycleComplete = new SimpleIntegerProperty(0);
        this.experience = new SimpleIntegerProperty(0);
        this.golds = new SimpleIntegerProperty(0);
        this.isWin = new SimpleBooleanProperty(false);
        this.isDead = new SimpleBooleanProperty(false);
        this.health = INIT_HEALTH;
        this.isStun = false;
        this.healthPercentage = new SimpleDoubleProperty((double)health/INIT_HEALTH);
        // random
        randomCard = new Random(CARD_SEED);
        randomItem = new Random(ITEM_SEED);
        randomGold = new Random(GOLD_SEED);
        randomHealthPotion = new Random(HEALTH_POTION_SEED);
        randomRare = new Random(RARE_SEED);
    }

    /**
     * Gets cycle complete.
     *
     * @return the cycle complete
     */
    public int getCycleComplete() {
        return cycleComplete.get();
    }

    /**
     * Cycle complete property simple integer property.
     *
     * @return the simple integer property for cycle complete
     */
    public SimpleIntegerProperty cycleCompleteProperty() {
        return cycleComplete;
    }

    /**
     * Update cycle complete.
     */
    public void updateCycleComplete() {
        if (MathUtils.positionIsEqual(0, 0, getX(), getY()))
        this.cycleComplete.set(this.cycleComplete.get() + 1);
    }

    @Override
    public int getHealth() {
        return health;
    }

    /**
     * get the characters health in percentage (health/100)
     *
     * @return DoubleProperty of healthPercentage
     */
    public SimpleDoubleProperty getHealthPercentage() {
        return healthPercentage;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, INIT_HEALTH);
        healthPercentage.set((double)this.health/INIT_HEALTH);
        if (health <= 0)  this.setIsDead(true);
    }

    /**
     * Gets experience.
     *
     * @return the experience
     */
    public int getExperience() {
        return experience.get();
    }

    /**
     * Gets experience property.
     *
     * @return the experience property
     */
    public SimpleIntegerProperty getExperienceProperty() {
        return experience;
    }

    /**
     * Sets experience.
     *
     * @param experience the experience
     */
    public void setExperience(int experience) {
        this.experience.set(experience);
    }

    /**
     * Gets golds.
     *
     * @return the golds
     */
    public int getGolds() {
        return golds.get();
    }

    /**
     * Gets golds property.
     *
     * @return the golds property
     */
    public SimpleIntegerProperty getGoldsProperty() {
        return golds;
    }

    /**
     * Sets golds.
     *
     * @param golds the golds
     */
    public void setGolds(int golds) {
        this.golds.set(golds);
    }


    /**
     * Is stun boolean.
     *
     * @return the boolean
     */
    public boolean isStun() {
        return isStun;
    }

    /**
     * Sets stun.
     *
     * @param stun the stun
     */
    public void setStun(boolean stun) {
        isStun = stun;
    }

    /**
     * Got bite.
     *
     * @param damage the damage
     */
    public void gotBite(int damage) {
        this.health = Math.max(0, this.health - damage);
        this.healthPercentage.set(((double) this.health)/INIT_HEALTH);
        //if (this.health <= 0) this.setIsDead(true);
    }

    /**
     * Attack int.
     *
     * @return the int
     */
    public int attack() {
        return ATTACK;
    }

    /**
     * Check death boolean.
     *
     * @return the boolean
     */
    public boolean checkDeath() {
        return this.health <= 0;
    }

    /**
     * Is win boolean.
     *
     * @return the boolean
     */
    public boolean IsWin() {
        return isWin.get();
    }

    /**
     * Is win property simple boolean property.
     *
     * @return the simple boolean property
     */
    public SimpleBooleanProperty isWinProperty() {
        return isWin;
    }

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    public boolean IsDead() {
        return isDead.get();
    }

    /**
     * Is dead property simple boolean property.
     *
     * @return the simple boolean property
     */
    public SimpleBooleanProperty isDeadProperty() {
        return isDead;
    }

    /**
     * Sets is win.
     *
     * @param isWin the is win
     */
    public void setIsWin(boolean isWin) {
        this.isWin.set(isWin);
    }

    /**
     * Sets is dead.
     *
     * @param isDead the is dead
     */
    public void setIsDead(boolean isDead) {
        this.isDead.set(isDead);
    }

    /**
     * Check win status.
     *
     * @param cycle      the cycle
     * @param bossIsDead the boss is dead
     * @param goals      the goals
     */
    public void checkWinStatus(int cycle, boolean bossIsDead, List<Pair<String, Integer>> goals) {
        // Amassing 90000 gold OR completing 100 cycles
        // Obtaining 123456 experience points AND (completing 100 cycles OR amassing 11000 gold)
        for (Pair<String, Integer> goal: goals) {
            switch (goal.getValue0()) {
                case "experience":
                    if (getExperience() < goal.getValue1()) return;
                    break;
                case "gold":
                    if (getGolds() < goal.getValue1()) return;
                    break;
                case "cycle":
                    if (cycle < goal.getValue1()) return;
                    break;
                case "boss":
                    if (goal.getValue1() > 0 && !bossIsDead) return;
                    break;
            }
        }
        this.isWin.set(true);
    }

    /**
     * Increment experience.
     *
     * @param experienceValue the experience value
     */
    public void incrementExperience(int experienceValue) {
        experience.set(this.experience.get() + experienceValue);
    }

    /**
     * Increment one cycle complete.
     */
    public void incrementOneCycleComplete() {
        this.cycleComplete = new SimpleIntegerProperty(this.cycleComplete.get() + 1);
    }

    /**
     * Increase health.
     *
     * @param healthValue the health value
     */
    public void increaseHealth(int healthValue) {
        this.health += healthValue;
        this.health = Math.min(health, INIT_HEALTH);
        healthPercentage.set((double)this.health/INIT_HEALTH);
    }

    /**
     * Increment golds.
     *
     * @param goldValue the gold value
     */
    public void incrementGolds(int goldValue) {
        this.golds.set(this.golds.get() + goldValue);
    }

    /**
     * implementation for picking up items
     * check if the character can pick up any items in the given list
     * increase gold/refill health
     * remove them from list
     *
     * @param goldOnGround the gold on ground
     */
    public void pickUpGold(List<Items> goldOnGround) {

        List<Items> goldPickedUp = new ArrayList<>();

        for (Items item: goldOnGround) {
            // find the items with the same axis as character
            if (positionIsEqual(getX(), getY(), item.getX(), item.getY())) {
                // pick the item up
                if (item instanceof Gold) {
                    // for gold, increase the gold
                    incrementGolds(((Gold) item).getAmount());
                    goldPickedUp.add(item);
                }
            }
        }
        for (Items item: goldPickedUp) {
            // remove the collected items from the list
            goldOnGround.remove(item);
            // destroy the collected items
            item.destroy();
        }
    }

    /**
     * implementation for picking up items
     * check if the character can pick up any items in the given list
     * increase gold/refill health
     * remove them from list
     *
     * @param healthPotionsOnGround the health potions on ground
     * @return the list
     */
    public List<HealthPotion> pickUpHealthPotion(List<Items> healthPotionsOnGround) {

        List<HealthPotion> healthPotionPickedUp = new ArrayList<>();
        List<HealthPotion> healthPotionRes = new ArrayList<>();

        for (Items item: healthPotionsOnGround) {
            // find the items with the same axis as character
            if (positionIsEqual(getX(), getY(), item.getX(), item.getY())) {
                if (item instanceof HealthPotion) {;
                    healthPotionPickedUp.add((HealthPotion) item);
                }
            }
        }
        for (Items item: healthPotionPickedUp) {
            // remove the collected items from the list
            healthPotionsOnGround.remove(item);
            item.destroy();
            healthPotionRes.add(new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        }
        return  healthPotionRes;
    }

    /**
     * check if the 2 given axis are equal
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return true if if the 2 given axis are equal, false otherwise
     */
    private boolean positionIsEqual(int x1, int y1, int x2, int y2) {
        return (x1 == x2) && (y1 == y2);
    }


    /**
     * Gets random card.
     *
     * @return the random card
     */
    public Random getRandomCard() {
        return randomCard;
    }

    /**
     * Gets random item.
     *
     * @return the random item
     */
    public Random getRandomItem() {
        return randomItem;
    }

    /**
     * Gets random gold.
     *
     * @return the random gold
     */
    public Random getRandomGold() {
        return randomGold;
    }

    /**
     * Gets random health potion.
     *
     * @return the random health potion
     */
    public Random getRandomHealthPotion() {
        return randomHealthPotion;
    }

    /**
     * Gets random rare.
     *
     * @return the random rare
     */
    public Random getRandomRare() {
        return randomRare;
    }

    /**
     * Sets cycle complete.
     *
     * @param cycleComplete the cycle complete
     */
    public void setCycleComplete(int cycleComplete) {
        this.cycleComplete.set(cycleComplete);
    }
}
