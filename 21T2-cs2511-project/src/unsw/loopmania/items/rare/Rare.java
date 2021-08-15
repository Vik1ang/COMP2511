package unsw.loopmania.items.rare;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.items.Items;

import java.util.Set;

/**
 * The type Rare.
 */
public abstract class Rare extends Items {

    private Rare secondaryAbility = null;

    /**
     * Instantiates a new Rare.
     *
     * @param x the x
     * @param y the y
     */
    public Rare(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellingPrice(5);
    }

    public void setSecondaryAbility(Rare secondaryAbility) {
        this.secondaryAbility = secondaryAbility;
    }

    public Rare getSecondaryAbility() {
        return secondaryAbility;
    }

    public int getAttackToEnemy(BasicEnemy e) {
        int attack = 0;
        Rare secondaryAbility = getSecondaryAbility();
        if (secondaryAbility != null) attack += secondaryAbility.getAttackToEnemy(e);
        return attack;
    }

    public int damageDefend(int damage, BasicEnemy e) {
        Rare secondaryAbility = getSecondaryAbility();
        if (secondaryAbility != null) return secondaryAbility.damageDefend(damage, e);
        else return damage;
    }

    public int getHealthRefill() {
        int healthRefill = 0;
        Rare secondaryAbility = getSecondaryAbility();
        if (secondaryAbility != null)
            healthRefill = secondaryAbility.getHealthRefill();
        return healthRefill;
    }

    public boolean isWeapon() {
        return (this instanceof AndurilFlameOfTheWest ) ||
                (secondaryAbility != null && secondaryAbility instanceof AndurilFlameOfTheWest);
    }

    public boolean isShield() {
        return (this instanceof TreeStump ) ||
                (secondaryAbility != null && secondaryAbility instanceof TreeStump);
    }
}
