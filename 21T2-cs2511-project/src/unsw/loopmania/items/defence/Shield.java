package unsw.loopmania.items.defence;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * The type Shield.
 */
public class Shield extends Defence{

    private static final double DAMAGE_DEFENCE_RATE = 0.9;
    private final int BUYING_PRICE = 10;

    /**
     * Instantiates a new Shield.
     *
     * @param x the x
     * @param y the y
     */
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType(new SimpleStringProperty("Shield"));
        setBuyingPrice(BUYING_PRICE);
    }

    public int damageDefend(int damage) {
        return (int)(damage * DAMAGE_DEFENCE_RATE);
    }



}
