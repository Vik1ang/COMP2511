package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.utils.MathUtils;

/**
 * The type Trap building.
 */
public class TrapBuilding extends Building{
    private final int ATTACK_POWER = 10;

    /**
     * Instantiates a new Trap building.
     *
     * @param x the x
     * @param y the y
     */
    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("TrapBuilding");
        this.setHelpStrategy(new NextToStrategy());
    }


    /**
     * Attack int.
     *
     * @return the int
     */
    public int attack() {
         return ATTACK_POWER;
    }


}
