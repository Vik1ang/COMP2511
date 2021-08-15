package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.utils.RandomUtils;

import java.util.Random;

/**
 * The type Doggie coin.
 */
public class DoggieCoin extends Items {
    private static final int LOW_SELLING_PRICE = 1;
    private static final int MEDIUM_SELLING_PRICE = 10;
    private static final int HIGH_SELLING_PRICE = 100;

    //static selling price for DoggieCoin
    private static SimpleIntegerProperty newSellingPrice = new SimpleIntegerProperty(0);

    private final int HIGH_PRICE_SEED = 0;
    //0  [60,29,15,91,19,77,73,95,84,41,43,24,52,3,92]
    private final int MEDIUM_PRICE_SEED = 4;
    // 4 [62,3,67,11,62,92,52,62,69,62,75,22,39]
    private final int LOW_PRICE_SEED = 6;
    // 6 [11,66,41,77,90,8,12,29,53,51,9,97,96,92,31,64,16]

    private String mode = "MEDIUM";
    private Random randomLowSellingPrice;
    private Random randomMediumSellingPrice;
    private Random randomHighSellingPrice;


    /**
     * Instantiates a new Doggie coin.
     *
     * @param x the x
     * @param y the y
     */
    public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        // randomly fluctuates in sellable price
        DoggieCoin.newSellingPrice.set(MEDIUM_SELLING_PRICE);
        setType(new SimpleStringProperty("DoggieCoin"));
        randomLowSellingPrice = new Random(LOW_PRICE_SEED);
        randomMediumSellingPrice = new Random(MEDIUM_PRICE_SEED);
        randomHighSellingPrice = new Random(HIGH_PRICE_SEED);

    }

    //highest fluctutation rate
    //if character alive, Elan Muske allive

    //medium fluctuation rate
    //if character alive, elan Muske doesn't exist->没出现

    //low fluctuation rate
    //if character alive, character killed elan muske

    /**
     * Sets new selling price mode.
     *
     * @param elanMode the elan mode
     */
    public void setNewSellingPriceMode(String elanMode) {
        switch(elanMode) {
            case "HIGH":
                setMode("HIGH");
                setSellingPrice(getHighSellingPrice());
                setNewSellingPrice(getHighSellingPrice());
                break;

            case "MEDIUM":
                setMode("MEDIUM");
                setSellingPrice(getMediumSellingPrice());
                setNewSellingPrice(getMediumSellingPrice());
                break;

            case "LOW":
                setMode("LOW");
                setSellingPrice(getLowSellingPrice());
                setNewSellingPrice(getLowSellingPrice());
                break;
        }
    }

    @Override
    public int getSellingPrice() {
        switch(mode) {
            case "HIGH":
                DoggieCoin.setNewSellingPrice(getHighSellingPrice());
                break;

            case "MEDIUM":
                DoggieCoin.setNewSellingPrice(getMediumSellingPrice());
                break;

            case "LOW":
                DoggieCoin.setNewSellingPrice(getLowSellingPrice());
                break;
        }

        return getNewSellingPrice();
    }

    private int getLowSellingPrice() {
        int randNumber = randomLowSellingPrice.nextInt(100);
        if (randNumber < 60) {
            return LOW_SELLING_PRICE + randNumber/100;
        }
        return LOW_SELLING_PRICE - randNumber/100;
    }

    private int getMediumSellingPrice() {
        //4 [62,3,67,11,62,92,52,62,69,62,75,22,39]
        int randNumber = randomMediumSellingPrice.nextInt(100);
        if (randNumber < 60) {
            return MEDIUM_SELLING_PRICE + randNumber/10;
        }
        return MEDIUM_SELLING_PRICE - randNumber/10;
    }

    private int getHighSellingPrice() {

        int randNumber = randomHighSellingPrice.nextInt(100);
        if (randNumber < 60) {
            return HIGH_SELLING_PRICE + randNumber;
        }
        return HIGH_SELLING_PRICE - randNumber;
    }


    //set the buying mode


    /**
     * Sets mode.
     *
     * @param mode the mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Gets new selling price.
     *
     * @return the new selling price
     */
    public static int getNewSellingPrice() {
        return newSellingPrice.get();
    }

    /**
     * Sets new selling price.
     *
     * @param newSellingPrice the new selling price
     */
    public static void setNewSellingPrice(int newSellingPrice) {
        DoggieCoin.newSellingPrice.set(newSellingPrice);
    }

    /**
     * New selling price property simple integer property.
     *
     * @return the simple integer property
     */
    public static SimpleIntegerProperty newSellingPriceProperty() {
        return newSellingPrice;
    }
}
