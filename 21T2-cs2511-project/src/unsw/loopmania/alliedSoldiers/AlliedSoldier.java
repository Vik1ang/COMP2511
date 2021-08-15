package unsw.loopmania.alliedSoldiers;


import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

/**
 * The type Allied soldier.
 */
public class AlliedSoldier extends StaticEntity {
    private static final int ATTACK = 5;
    private static final int INIT_HEALTH = 20;
    private int health;
    private boolean transferred;

    /**
     * Instantiates a new Allied soldier.
     *
     * @param x the x
     * @param y the y
     */
    public AlliedSoldier(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        health = INIT_HEALTH;
        transferred = false;
    }

    /**
     * Soldier Attack int.
     *
     * @return the int
     */
    public int attack() {
        return ATTACK;
    }

    /**
     * Got attack from enemy.
     *
     * @param damage the damage
     */
    public void gotAttack(int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    /**
     * Check death boolean.
     *
     * @return the boolean
     */
    public boolean checkDeath() {
        return (this.health == 0);
    }

    /**
     * Is transferred boolean.
     *
     * @return the boolean
     */
    public boolean isTransferred() {
        return this.transferred;
    }

    /**
     * Sets transferred.
     * transfer the alliedSoldier if true
     * @param transferred the transferred
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

}
