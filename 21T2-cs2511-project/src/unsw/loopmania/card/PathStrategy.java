package unsw.loopmania.card;

import unsw.loopmania.WORLD_POSITION_TYPE;

/**
 * The type Path strategy.
 */
public class PathStrategy implements PlaceCardStrategy {
    @Override
    public boolean canPlaceCard(int x, int y, WORLD_POSITION_TYPE[][] worldPositionType) {
        if(worldPositionType[x][y].equals(WORLD_POSITION_TYPE.PATH)) {
            return true;
        }
        return false;
    }
}
