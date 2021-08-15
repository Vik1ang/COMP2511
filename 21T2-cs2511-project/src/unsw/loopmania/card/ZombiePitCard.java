package unsw.loopmania.card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.WORLD_POSITION_TYPE;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.ZombiePitBuilding;

/**
 * The type Zombie pit card.
 */
public class ZombiePitCard extends Card{
    /**
     * Instantiates a new Zombie pit card.
     *
     * @param x the x
     * @param y the y
     */
    public ZombiePitCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType("ZombiePitCard");
        setPlaceCardStrategy(new NonPathAdjacentStrategy());
    }

    @Override
    public Building getBuilding(int x, int y) {
        Building zombiePit = new ZombiePitBuilding(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        return zombiePit;
    }
}
