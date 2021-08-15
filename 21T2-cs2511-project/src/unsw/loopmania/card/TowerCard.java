package unsw.loopmania.card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.WORLD_POSITION_TYPE;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.TowerBuilding;

/**
 * The type Tower card.
 */
public class TowerCard extends Card{

    public TowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType("TowerCard");
        setPlaceCardStrategy(new NonPathAdjacentStrategy());
    }

    @Override
    public Building getBuilding(int x, int y) {
        Building tower = new TowerBuilding(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        return tower;
    }
}
