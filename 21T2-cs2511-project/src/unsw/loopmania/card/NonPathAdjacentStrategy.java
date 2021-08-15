package unsw.loopmania.card;

import unsw.loopmania.WORLD_POSITION_TYPE;

/**
 * The type Non path adjacent strategy.
 */
public class NonPathAdjacentStrategy implements PlaceCardStrategy {
    @Override
    public boolean canPlaceCard(int x, int y, WORLD_POSITION_TYPE[][] worldPositionType) {
        if (!worldPositionType[x][y].equals(WORLD_POSITION_TYPE.GRASS)) return false;
        //non path tile
        else {
            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y - 1; j < y + 2; j++) {
                    //invalid indexes
                    if (i < 0 || j < 0 || i >= worldPositionType.length || j >= worldPositionType[i].length) {
                        continue;
                    }
                    if (worldPositionType[i][j].equals(WORLD_POSITION_TYPE.PATH)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
