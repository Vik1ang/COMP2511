package unsw.loopmania.card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.WORLD_POSITION_TYPE;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.VillageBuilding;

/**
 * The type Village card.
 */
public class VillageCard extends Card{
    /**
     * Instantiates a new Village card.
     *
     * @param x the x
     * @param y the y
     */
    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType("VillageCard");
        setPlaceCardStrategy(new PathStrategy());
    }

    @Override
    public Building getBuilding(int x, int y) {
        Building village = new VillageBuilding(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        return village;
    }
}
