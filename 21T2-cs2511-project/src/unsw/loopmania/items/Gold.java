package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * The type Gold.
 */
public class Gold extends Items {
    private static final int AMOUNT = 1;

    /**
     * Instantiates a new Gold.
     *
     * @param x the positionX
     * @param y the positionY
     */
    public Gold(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);

        setType(new SimpleStringProperty("Gold"));
    }

    /**
     * Gets amount of gold.
     *
     * @return the amount of gold (i.e. 1)
     */
    public int getAmount() {
        return AMOUNT;
    }
}