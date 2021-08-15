package unsw.loopmania.buildings;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.card.CampfireCard;
import unsw.loopmania.utils.MathUtils;

/**
 * The type In support radius strategy.
 */
public class InSupportRadiusStrategy implements HelpStrategy {

    @Override
    public boolean canGiveHelp(Building building, MovingEntity entity) {
        double distance = MathUtils.getDistance(building.getX(), building.getY(),
                entity.getX(), entity.getY());
        int radius = 0;
        if (building instanceof TowerBuilding) radius = ((TowerBuilding) building).getShootingRadius();
        else if (building instanceof CampFireBuilding) radius = ((CampFireBuilding) building).getBattleRadius();
        if(distance <= (double) radius) return true;
        return false;
    }
}
