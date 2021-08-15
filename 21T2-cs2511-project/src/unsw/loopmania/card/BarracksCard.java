package unsw.loopmania.card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.WORLD_POSITION_TYPE;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.buildings.Building;

/**
 * The type Barracks card.
 */
public class BarracksCard extends Card{
    /**
     * Instantiates a new Barracks card.
     *
     * @param x the x
     * @param y the y
     */
    public BarracksCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType("BarracksCard");
        this.setPlaceCardStrategy(new PathStrategy());
    }


    @Override
    public Building getBuilding(int x, int y) {
        Building barracks = new BarracksBuilding(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        return barracks;
    }

 /*   //Only on path tiles
    @Override
    public boolean canPlaceCard(int x, int y, WORLD_POSITION_TYPE worldPositionType[][]) {
        //if(worldPositionType[x][y].equals(WORLD_POSITION_TYPE.PATH)) {
        //    return true;
        //}
        //return false;
        return getPlaceCardStrategy().canPlaceCard(x, y, worldPositionType);
    }*/



}
