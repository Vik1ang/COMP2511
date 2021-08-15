package unsw.loopmania.items.rare;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.basicEnemies.ElanMuske;


import java.util.HashSet;

public class AndurilFlameOfTheWest extends Rare {

    private static final int ATTACK = 5;
    private static final int BOSS_ATTACK = ATTACK * 3;

    /**
     * Instantiates Anduril, Flame of the West
     *
     * @param x the x
     * @param y the y
     */
    public AndurilFlameOfTheWest(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public int getAttackToEnemy(BasicEnemy e) {
        int attack = 0;
        if (e instanceof ElanMuske)  attack += BOSS_ATTACK;
        else attack += ATTACK;
        return attack;
    }

}
