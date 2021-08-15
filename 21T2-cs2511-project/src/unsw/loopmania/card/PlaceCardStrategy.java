package unsw.loopmania.card;

import unsw.loopmania.WORLD_POSITION_TYPE;

/**
 * The interface Place card strategy.
 */
public interface PlaceCardStrategy {
    /**
     * Can place card boolean.
     *
     * @param x                 the x
     * @param y                 the y
     * @param worldPositionType the world position type
     * @return the boolean
     */
    public boolean canPlaceCard(int x, int y, WORLD_POSITION_TYPE worldPositionType[][]);
}
