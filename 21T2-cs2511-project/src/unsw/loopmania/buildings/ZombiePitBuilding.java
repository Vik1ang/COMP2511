package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.character.Character;

/**
 * The type Zombie pit building.
 */
public class ZombiePitBuilding extends Building {


    /**
     * Instantiates a new Zombie pit building.
     *
     * @param x the x
     * @param y the y
     */
    public ZombiePitBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.setCycleComplete(5);
        setType("ZombiePitBuilding");
        setHelpStrategy(new NoHelpStrategy());
    }

    /**
     * Instantiates a new Zombie pit building.
     *
     * @param x             the x
     * @param y             the y
     * @param cycleComplete the cycle complete
     */
    public ZombiePitBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y, int cycleComplete) {
        super(x, y);
        this.setCycleComplete(cycleComplete);
        setType("ZombiePitBuilding");
        setHelpStrategy(new NoHelpStrategy());
    }


}
