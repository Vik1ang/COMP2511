package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.character.Character;

/**
 * The type Vampire castle building.
 */
public class VampireCastleBuilding extends Building {

    /**
     * Instantiates a new Vampire castle building.
     *
     * @param x the x
     * @param y the y
     */
    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.setCycleComplete(5);
        super.setType("VampireCastleBuilding");
        this.setHelpStrategy(new NoHelpStrategy());
    }

    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y, int cycle) {
        super(x, y);
        this.setCycleComplete(cycle);
        super.setType("VampireCastleBuilding");
        this.setHelpStrategy(new NoHelpStrategy());
    }

}
