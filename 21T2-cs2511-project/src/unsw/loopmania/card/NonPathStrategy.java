package unsw.loopmania.card;

import unsw.loopmania.WORLD_POSITION_TYPE;

/**
 * The type Non path strategy.
 */
public class NonPathStrategy implements PlaceCardStrategy {

    @Override
    public boolean canPlaceCard(int x, int y, WORLD_POSITION_TYPE[][] worldPositionType) {
        if (worldPositionType[x][y].equals(WORLD_POSITION_TYPE.GRASS)) return true;
        return false;
    }
}
