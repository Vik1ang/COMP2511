package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.character.Character;
import unsw.loopmania.utils.MathUtils;

/**
 * The type Village building.
 */
public class VillageBuilding extends Building{

    private final int HEALTH_REFILL = 5;

    /**
     * Instantiates a new Village building.
     *
     * @param x the x
     * @param y the y
     */
    public VillageBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("VillageBuilding");
        this.setHelpStrategy(new NextToStrategy());
    }

    /**
     * Gets health refill.
     *
     * @return the health refill
     */
    public int getHealthRefill() {
        return HEALTH_REFILL;
    }

}
