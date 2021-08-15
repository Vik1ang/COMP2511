package unsw.loopmania.items.defence;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * The type Helmet.
 */
public class Helmet extends Defence{

    private static final int DAMAGE_DEFENCE = 3;
    private static final int ATTACK_DECREASE = 3;
    private final int BUYING_PRICE = 5;

    /**
     * Instantiates a new Helmet.
     *
     * @param x the x
     * @param y the y
     */
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType(new SimpleStringProperty("Helmet"));
        setBuyingPrice(BUYING_PRICE);
    }

    public int damageDefend(int damage) {
        return Math.max(damage - DAMAGE_DEFENCE, 0) ;
    }

    /**
     * Gets attack decrease for item.
     *
     * @return the attack decrease
     */
    public int getAttackDecrease() {
        return ATTACK_DECREASE;
    }



}
