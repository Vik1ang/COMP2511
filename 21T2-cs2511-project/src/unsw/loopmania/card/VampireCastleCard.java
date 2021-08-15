package unsw.loopmania.card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.WORLD_POSITION_TYPE;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.VampireCastleBuilding;

/**
 * The type Vampire castle card.
 */
public class VampireCastleCard extends Card{

    /**
     * Instantiates a new Vampire castle card.
     *
     * @param x the x
     * @param y the y
     */
    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType("VampireCastleCard");
        setPlaceCardStrategy(new NonPathAdjacentStrategy());
    }

    @Override
    public Building getBuilding(int x, int y) {
        Building vampireCastle= new VampireCastleBuilding(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        return vampireCastle;
    }
}

