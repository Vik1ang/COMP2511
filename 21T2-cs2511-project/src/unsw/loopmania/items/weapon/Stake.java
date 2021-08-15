package unsw.loopmania.items.weapon;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.basicEnemies.Vampire;

/**
 * The type Stake.
 */
public class Stake extends Weapon{

    private static final int ATTACK = 3;
    private static final int VAMPIRE_ATTACK = 10;
    private static final int BUYING_PRICE = 10;

    /**
     * Instantiates a new Stake.
     *
     * @param x the x
     * @param y the y
     */
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setAttack(ATTACK);
        setBuyingPrice(BUYING_PRICE);
        setType(new SimpleStringProperty("Stake"));
    }

    @Override
    public int getAttackToEnemy(BasicEnemy enemy) {
        // causes very high damage to vampires
        if (enemy instanceof Vampire) return VAMPIRE_ATTACK;
        return getAttack();
    }

}
