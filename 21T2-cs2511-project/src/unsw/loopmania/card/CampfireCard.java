package unsw.loopmania.card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.WORLD_POSITION_TYPE;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.CampFireBuilding;

/**
 * The type Campfire card.
 */
public class CampfireCard extends Card{
    /**
     * Instantiates a new Campfire card.
     *
     * @param x the x
     * @param y the y
     */
    public CampfireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType("CampfireCard");
        setPlaceCardStrategy(new NonPathStrategy());
    }
    @Override
    public Building getBuilding(int x, int y) {
        Building campfire = new CampFireBuilding(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        return campfire;
    }
/*
    //any non-path tile
    @Override
    public boolean canPlaceCard(int x, int y, WORLD_POSITION_TYPE worldPositionType[][]) {
        //if (worldPositionType[x][y].equals(WORLD_POSITION_TYPE.GRASS)) return true;
        //return false;
        return getPlaceCardStrategy().canPlaceCard(x, y, worldPositionType);
    }*/



}
