package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.character.Character;
import unsw.loopmania.utils.MathUtils;

/**
 * The type Barracks building.
 */
public class BarracksBuilding extends Building{

    /**
     * Instantiates a new Barracks building.
     *
     * @param x the x
     * @param y the y
     */
    public BarracksBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType("BarracksBuilding");
        setHelpStrategy(new NextToStrategy());
    }

}
