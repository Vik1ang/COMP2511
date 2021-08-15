package unsw.loopmania.card;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.WORLD_POSITION_TYPE;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.TrapBuilding;

/**
 * The type Trap card.
 */
public class TrapCard extends Card{
    /**
     * Instantiates a new Trap card.
     *
     * @param x the x
     * @param y the y
     */
    public TrapCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType("TrapCard");
        setPlaceCardStrategy(new PathStrategy());
    }

    @Override
    public Building getBuilding(int x, int y) {
        Building trap = new TrapBuilding(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        return trap;
    }
/*
    //Only on path tiles
    @Override
    public boolean canPlaceCard(int x, int y, WORLD_POSITION_TYPE worldPositionType[][]) {
        if(worldPositionType[x][y].equals(WORLD_POSITION_TYPE.PATH)) {
            return true;
        }
        return false;
    }*/
}
