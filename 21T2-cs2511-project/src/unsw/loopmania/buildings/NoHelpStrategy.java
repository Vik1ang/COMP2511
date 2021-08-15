package unsw.loopmania.buildings;

import unsw.loopmania.MovingEntity;

/**
 * The type No help strategy.
 */
public class NoHelpStrategy implements HelpStrategy {

    @Override
    public boolean canGiveHelp(Building building, MovingEntity entity) {
        return false;
    }
}
