package unsw.loopmania.buildings;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.character.Character;
import unsw.loopmania.items.DoggieCoin;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.Items;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Defence;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.weapon.Staff;
import unsw.loopmania.items.weapon.Stake;
import unsw.loopmania.items.weapon.Sword;
import unsw.loopmania.utils.MathUtils;
import unsw.loopmania.utils.PackageHelper;
import unsw.loopmania.utils.PreservedWord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Hero castle building.
 */
public class HeroCastleBuilding extends Building {

    private final int PROTECTIVE_GEAR_MAX = 1;
    private final int HEALTH_POTION_MAX = 1;

    private int healthPotion;
    private int protectiveGear;
    private String mode;
    private int roundRemain;
    private int purchaseTimes;

    private SimpleBooleanProperty atHeroCastle;

    private final List<Items> ITEM_LIST = Collections.unmodifiableList(
            new ArrayList<>() {{
                add(new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                add(new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                add(new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                add(new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                add(new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                add(new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                add(new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
            }});

    /**
     * Instantiates a new Hero castle building.
     *
     * @param x the x
     * @param y the y
     */
    public HeroCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.init();
        atHeroCastle = new SimpleBooleanProperty(true);
        this.setAtHeroCastle(true);
        super.setType("HeroCastleBuilding");
        mode = new String();
        this.roundRemain = 0;
        this.purchaseTimes = roundRemain;
        this.setHelpStrategy(new NextToStrategy());

    }


    /**
     * Is at hero castle simple boolean property.
     * check if the character is at HeroCastle
     * @return the simple boolean property
     */
    public SimpleBooleanProperty isAtHeroCastle() {
        return atHeroCastle;
    }

    /**
     * Sets at hero castle.
     *
     * @param atHeroCastle the at hero castle
     */
    public void setAtHeroCastle(boolean atHeroCastle) {
        this.atHeroCastle.set(atHeroCastle);
    }

    /**
     * Start buy and sell.
     */
    public void startBuyAndSell(){
        if (roundRemain > 0) {
            roundRemain--;
            return;
        } else {
            roundRemain = purchaseTimes + 1;
            purchaseTimes = purchaseTimes + 1;
        }
        this.init();
        setAtHeroCastle(true);
    }

    /**
     * End buy and sell.
     */
    public void endBuyAndSell(){
        this.init();
        setAtHeroCastle(false);
    }

    /**
     * Gets hero castle items.
     *
     * @return the hero castle items
     */
    public List<Items> getHeroCastleItems() {
        return ITEM_LIST;
    }

    /**
     * Can buy boolean.
     *
     * @param character  the character
     * @param itemString the item string
     * @return the boolean
     */
    public boolean canBuy(Character character, String itemString) {
        Items item = PackageHelper.getItemsFromString(itemString);

        if (item == null) return false;
        if (! hasEnoughGold(character, item)) return false;

        switch (this.mode) {
            case PreservedWord.gameModeStandard:
            case PreservedWord.gameModeConfusing:
                return true;
            case PreservedWord.gameModeSurvival:
                if (item instanceof HealthPotion && this.healthPotion >= HEALTH_POTION_MAX)
                    return false;
                else
                    return true;
            case PreservedWord.gameModeBerserk:
                if (item instanceof Defence && this.protectiveGear >= PROTECTIVE_GEAR_MAX)
                    return false;
                else
                    return true;
        }
        return false;
    }

    /**
     * Buy item.
     *
     * @param character the character
     * @param item      the item
     */
    public void buyItem(Character character, Items item) {
        character.incrementGolds(-item.getBuyingPrice());
        if (item instanceof HealthPotion) {
            healthPotion ++;
        } else {
            if (item instanceof Defence) protectiveGear ++;
        }

    }


    @Override
    public void setType(String type) {
        super.setType("HeroCastleBuilding");
    }


    /**
     * Gets health potion.
     *
     * @return the health potion
     */
    public int getHealthPotion() {
        return healthPotion;
    }

    /**
     * Gets protective gear.
     *
     * @return the protective gear
     */
    public int getProtectiveGear() {
        return protectiveGear;
    }

    /**
     * Sets health potion.
     *
     * @param healthPotion the health potion
     */
    public void setHealthPotion(int healthPotion) {
        this.healthPotion = healthPotion;
    }

    /**
     * Sets protective gear.
     *
     * @param protectiveGear the protective gear
     */
    public void setProtectiveGear(int protectiveGear) {
        this.protectiveGear = protectiveGear;
    }

    private boolean hasEnoughGold(Character character, Items item) {
        return character.getGolds() >= item.getBuyingPrice();
    }

    private void init() {
        this.setHealthPotion(0);
        this.setProtectiveGear(0);
    }

    /**
     * Gets mode.
     *
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets mode.
     *
     * @param mode the mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }
}
