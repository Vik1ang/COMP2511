package unsw.loopmania.alliedSoldiers;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.basicEnemies.BasicEnemy;

/**
 * The type Traced allied soldier.
 */
public class TracedAlliedSoldier extends AlliedSoldier{

    private BasicEnemy enemy = null;
    private int tranceRemain;

    /**
     * Instantiates a new Traced allied soldier.
     *
     * @param x the x
     * @param y the y
     */
    public TracedAlliedSoldier(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        tranceRemain = 3;
    }

    /**
     * Sets enemy to trance back.
     *
     * @param enemy the enemy
     */
    public void setEnemy(BasicEnemy enemy) {
        this.enemy = enemy;
    }

    /**
     * Gets enemy to trance back.
     *
     * @return the enemy
     */
    public BasicEnemy getEnemy() {
        return enemy;
    }

    /**
     * Gets trance remain round.
     *
     * @return the trance remain
     */
    public int getTranceRemain() {
        return tranceRemain;
    }

    /**
     * Decrease trance remain round by 1.
     */
    public void decreaseTranceRemain() {
        this.tranceRemain -= 1;
    }
}
