package unsw.loopmania.items.rare;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.basicEnemies.ElanMuske;

import java.util.HashSet;

public class TreeStump extends Rare {

    private static final double DEFENCE_RATE = 0.8;
    private static final double BOSS_DEFENCE_RATE = DEFENCE_RATE * DEFENCE_RATE;
    /**
     * Instantiates Tree Stump.
     *
     * @param x the x
     * @param y the y
     */
    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    @Override
    public int damageDefend(int damage, BasicEnemy e) {
        if (e instanceof ElanMuske) return (int)(damage * BOSS_DEFENCE_RATE);
        else return (int)(damage * DEFENCE_RATE);
    }

}
