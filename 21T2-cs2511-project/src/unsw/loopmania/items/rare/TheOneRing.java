package unsw.loopmania.items.rare;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.basicEnemies.BasicEnemy;


import java.util.HashSet;

/**
 * The type The one ring.
 */
public class TheOneRing extends Rare {

    private static final int HEALTH_REFILL = 100;

    /**
     * Instantiates a new The one ring.
     *
     * @param x the x
     * @param y the y
     */
    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType(new SimpleStringProperty("TheOneRing"));
    }


    /**
     * Gets health refill.
     *
     * @return the health refill
     */
    @Override
    public int getHealthRefill() {
        return HEALTH_REFILL;
    }
}
