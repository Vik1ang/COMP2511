package unsw.loopmania.items.weapon;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.basicEnemies.BasicEnemy;

/**
 * The type Sword.
 */
public class Sword extends Weapon{

    private static final int ATTACK = 4;

    /**
     * Instantiates a new Sword.
     *
     * @param x the x
     * @param y the y
     */
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setAttack(ATTACK);
        setType(new SimpleStringProperty("Sword"));
    }

    @Override
    public int getAttackToEnemy(BasicEnemy enemy) {
        return getAttack();
    }

}
