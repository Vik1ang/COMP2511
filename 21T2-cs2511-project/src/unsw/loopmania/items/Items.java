package unsw.loopmania.items;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import unsw.loopmania.StaticEntity;

/**
 * The type Items.
 */
public abstract class Items extends StaticEntity {

    private IntegerProperty buyingPrice;
    private IntegerProperty sellingPrice;
    private StringProperty type;

    /**
     * Instantiates a new Items.
     *
     * @param x the x
     * @param y the y
     */
    public Items(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        buyingPrice = new SimpleIntegerProperty(0);
        sellingPrice = new SimpleIntegerProperty(0);
    }

    /**
     * Gets buying price.
     *
     * @return the buying price
     */
    public int getBuyingPrice() {
        return buyingPrice.get();
    }

    /**
     * Buying price property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty buyingPriceProperty() {
        return buyingPrice;
    }

    /**
     * Sets buying price.
     *
     * @param buyingPrice the buying price
     */
    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice.set(buyingPrice);
    }

    /**
     * Gets selling price.
     *
     * @return the selling price
     */
    public int getSellingPrice() {
        return sellingPrice.get();
    }

    /**
     * Selling price property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty sellingPriceProperty() {
        return sellingPrice;
    }

    /**
     * Sets selling price.
     *
     * @param sellingPrice the selling price
     */
    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice.set(sellingPrice);
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type.get();
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(StringProperty type) {
        this.type = type;
    }

}
