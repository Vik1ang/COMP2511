package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.character.Character;
import unsw.loopmania.utils.MathUtils;

/**
 * The type Camp fire building.
 */
public class CampFireBuilding extends Building{

    private final int BATTLE_RADIUS = 1;

    /**
     * Instantiates a new Camp fire building.
     *
     * @param x the x
     * @param y the y
     */
    public CampFireBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("CampFireBuilding");
        this.setHelpStrategy(new InSupportRadiusStrategy());
    }

    public int getBattleRadius() {
        return BATTLE_RADIUS;
    }


    /**
     * Inside battle radius boolean.
     *
     * @param character the character
     * @return the boolean
     */
    public boolean canGiveHelp(Character character) {
        /*
        double distance = MathUtils.getDistance(getX(), getY(),
                                    character.getX(), character.getY());
        if(distance <= (double) BATTLE_RADIUS) return true;
        return false;*/
        return getHelpStrategy().canGiveHelp(this, character);
    }


}
