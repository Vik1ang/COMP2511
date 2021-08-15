package unsw.loopmania.items.defence;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.items.Items;

/**
 * The type Defence.
 */
public abstract class Defence extends Items {

    private final int SELLING_PRICE = 1;

    /**
     * Instantiates a new Defence.
     *
     * @param x the x
     * @param y the y
     */
    public Defence(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellingPrice(SELLING_PRICE);
    }

    /**
     * Damage defend int.
     *
     * @param damage the damage defend of the item
     * @return the int
     */
    public abstract int damageDefend(int damage);

}
