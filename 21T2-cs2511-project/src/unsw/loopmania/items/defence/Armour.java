package unsw.loopmania.items.defence;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * The type Armour.
 */
public class Armour extends Defence{

    private final int BUYING_PRICE = 20;

    /**
     * Instantiates a new Armour.
     *
     * @param x the x
     * @param y the y
     */
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setType(new SimpleStringProperty("Armour"));
        setBuyingPrice(BUYING_PRICE);
    }

    @Override
    public int damageDefend(int damage) {
        return damage >> 1;
    }

}
