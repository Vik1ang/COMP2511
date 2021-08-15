package unsw.loopmania.buildings;

import unsw.loopmania.MovingEntity;

/**
 * The interface Help strategy.
 */
public interface HelpStrategy {
    /**
     * Can give help boolean.
     *
     * @param building the building
     * @param entity   the entity
     * @return the boolean
     */
    public boolean canGiveHelp(Building building, MovingEntity entity);
}
