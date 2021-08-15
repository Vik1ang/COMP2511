package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.utils.MathUtils;

/**
 * The type Tower building.
 */
public class TowerBuilding extends Building {
    private final int ATTACK_POWER = 5;
    private int SHOOTING_RADIUS = 2;

    /**
     * Instantiates a new Tower building.
     *
     * @param x the x
     * @param y the y
     */
    public TowerBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("TowerBuilding");
        this.setHelpStrategy(new InSupportRadiusStrategy());
    }


    /**
     * Gets attack increase.
     *
     * @return the attack increase
     */
    public int getAttackIncrease() {
        return ATTACK_POWER;
    }

    public int getShootingRadius() {
        return SHOOTING_RADIUS;
    }

}
