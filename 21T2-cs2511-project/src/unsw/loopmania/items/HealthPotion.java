package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * The type Health potion.
 */
public class HealthPotion extends Items{

    private static final int SELLING_PRICE = 2;
    private static final int BUYING_PRICE = 5;
    private static final int HEALTH_REFILL = 20;

    /**
     * Instantiates a new Health potion.
     *
     * @param x the positionX
     * @param y the positionY
     */
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellingPrice(SELLING_PRICE);
        setBuyingPrice(BUYING_PRICE);
        setType(new SimpleStringProperty("HealthPotion"));
    }

    /**
     * Gets health refill.
     *
     * @return the health refill
     */
    public int getHealthRefill() {
        return HEALTH_REFILL;
    }

}
