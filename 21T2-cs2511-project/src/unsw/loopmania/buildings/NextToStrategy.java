package unsw.loopmania.buildings;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.utils.MathUtils;

/**
 * The type Next to strategy.
 */
public class NextToStrategy implements HelpStrategy {


    @Override
    public boolean canGiveHelp(Building building, MovingEntity entity) {
        return MathUtils.positionIsEqual(building.getX(), building.getY(), entity.getX(), entity.getY());
    }
}
