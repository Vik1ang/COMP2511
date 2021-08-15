package unsw.loopmania.card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.WORLD_POSITION_TYPE;
import unsw.loopmania.buildings.Building;

/**
 * a Card in the world
 * which doesn't move
 */
public abstract class Card extends StaticEntity {
    private String type;
    private PlaceCardStrategy placeCardStrategy;

    /**
     * Instantiates a new Card.
     *
     * @param x the x
     * @param y the y
     */
    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Gets building.
     *
     * @param x the x
     * @param y the y
     * @return the building
     */
//get the building of the card
    public abstract Building getBuilding(int x, int y);

    /**
     * Gets type.
     *
     * @return the type
     */
// add cards should be in the Human Character
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Can place card boolean.
     * Check weather card can be placed at the given position
     *
     * @param x                 the x
     * @param y                 the y
     * @param worldPositionType the world position type
     * @return the boolean
     */
    public boolean canPlaceCard(int x, int y, WORLD_POSITION_TYPE worldPositionType[][]) {
        return placeCardStrategy.canPlaceCard(x, y, worldPositionType);
    }

    /**
     * Sets place card strategy.
     *
     * @param placeCardStrategy the place card strategy
     */
    public void setPlaceCardStrategy(PlaceCardStrategy placeCardStrategy) {
        this.placeCardStrategy = placeCardStrategy;
    }
}
