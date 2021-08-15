package unsw.loopmania.items.weapon;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.items.Items;

/**
 * The type Weapon.
 */
public abstract class Weapon extends Items {

    private static final int BUYING_PRICE = 15;
    private static final int SELLING_PRICE = 1;
    private int attack;

    /**
     * Instantiates a new Weapon.
     *
     * @param x the x
     * @param y the y
     */
    public Weapon(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setBuyingPrice(BUYING_PRICE);
        setSellingPrice(SELLING_PRICE);
    }

    /**
     * Gets attack to enemy.
     *
     * @param enemy the BasicEnemy to attack
     * @return the attack to enemy (int)
     */
    public abstract int getAttackToEnemy(BasicEnemy enemy);

    /**
     * Gets attack.
     *
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Sets attack.
     *
     * @param attack the attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }


}
