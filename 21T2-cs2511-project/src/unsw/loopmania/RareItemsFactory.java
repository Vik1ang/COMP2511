package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.exception.IllegalRareItemException;
import unsw.loopmania.items.rare.AndurilFlameOfTheWest;
import unsw.loopmania.items.rare.Rare;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.rare.TreeStump;
import unsw.loopmania.utils.PreservedWord;
import unsw.loopmania.utils.RandomUtils;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * The type Rare items factory.
 */
public class RareItemsFactory {
    private List<String> rareItemsList;
    String mode;
    Random random;
    private static final String DEFAULT_STR = "default";

    /**
     * Instantiates a new Rare items factory.
     *
     * @param rareItemsList the rare items list
     */
    public RareItemsFactory(List<String> rareItemsList) {
        this.rareItemsList = rareItemsList;
        this.mode = PreservedWord.gameModeStandard;
        random = new Random(120);
        // seed 120: 8 38 47 54 82 8 77 82 97 46
    }

    /**
     * Sets rare items list.
     *
     * @param rareItemsList the rare items list
     */
    public void setRareItemsList(List<String> rareItemsList) {
        this.rareItemsList = rareItemsList;
    }

    /**
     * Sets mode.
     *
     * @param mode the mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Gets instance.
     *
     * @param x the x
     * @param y the y
     * @return the instance
     */
    public Rare getInstance(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        String rareItemType = randomInstance();
        if (mode.equals(PreservedWord.gameModeConfusing)) return getInstanceConfusingMode(rareItemType, x, y);
        else return getInstanceNormalMode(rareItemType, x, y);
    }

    private String randomInstance(){
        if (rareItemsList.size() == 0) return DEFAULT_STR;
        int index = RandomUtils.generateIntegerRandomBySeed(rareItemsList.size(), 0, random);
        return rareItemsList.get(index);
    }

    private Rare getInstanceNormalMode(String rareItemType, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        switch (rareItemType) {
            case PreservedWord.itemTheOneRing:
                return new TheOneRing(x, y);
            case PreservedWord.itemAndurilFlameOfTheWest:
                return new TreeStump(x, y);
            case PreservedWord.itemTreeStump:
                return new AndurilFlameOfTheWest(x, y);
            default:
                return null;
        }
    }

    private Rare getInstanceConfusingMode(String rareItemType, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        int i = random.nextInt(100);
        switch (rareItemType) {
            case PreservedWord.itemTheOneRing:
                Rare theOneRing = new TheOneRing(x, y);
                if (i < 50)
                    theOneRing.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                else
                    theOneRing.setSecondaryAbility(new TreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                return theOneRing;
            case PreservedWord.itemAndurilFlameOfTheWest:
                Rare andurilFlameOfTheWest = new AndurilFlameOfTheWest(x, y);
                if (i < 50)
                    andurilFlameOfTheWest.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                else
                    andurilFlameOfTheWest.setSecondaryAbility(new TreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                return andurilFlameOfTheWest;
            case PreservedWord.itemTreeStump:
                TreeStump treeStump = new TreeStump(x, y);
                if (i < 50)
                    treeStump.setSecondaryAbility(new TheOneRing(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                else
                    treeStump.setSecondaryAbility(new AndurilFlameOfTheWest(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
                return treeStump;
            default:
                return null;
        }

    }
}
