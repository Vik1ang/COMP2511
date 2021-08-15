package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Test;
import unsw.loopmania.RareItemsFactory;
import unsw.loopmania.items.rare.AndurilFlameOfTheWest;
import unsw.loopmania.items.rare.Rare;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.rare.TreeStump;
import unsw.loopmania.utils.LoopManiaUtils;
import unsw.loopmania.utils.PreservedWord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RareItemsFactoryTest {


    List<String> availableRareItems = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add(PreservedWord.itemTheOneRing);
                add(PreservedWord.itemAndurilFlameOfTheWest);
                add(PreservedWord.itemTreeStump);
            }});

    @Test
    public void FactoryStandardTest() {
        RareItemsFactory rareItemsFactory = new RareItemsFactory(availableRareItems);
        rareItemsFactory.setMode(PreservedWord.gameModeStandard);
        Rare rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TheOneRing);
        assertTrue(rare.getSecondaryAbility() == null);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TreeStump);
        assertTrue(rare.getSecondaryAbility() == null);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TreeStump);
        assertTrue(rare.getSecondaryAbility() == null);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof AndurilFlameOfTheWest);
        assertTrue(rare.getSecondaryAbility() == null);
    }

    @Test
    public void FactoryStandardNullTest() {
        RareItemsFactory rareItemsFactory = new RareItemsFactory(new ArrayList<String>());
        rareItemsFactory.setMode(PreservedWord.gameModeStandard);
        Rare rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare == null);
    }

    @Test
    public void FactoryBerserkTest() {
        RareItemsFactory rareItemsFactory = new RareItemsFactory(availableRareItems);
        rareItemsFactory.setMode(PreservedWord.gameModeBerserk);
        Rare rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        assertTrue(rare instanceof TheOneRing);
        assertTrue(rare.getSecondaryAbility() == null);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TreeStump);
        assertTrue(rare.getSecondaryAbility() == null);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TreeStump);
        assertTrue(rare.getSecondaryAbility() == null);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof AndurilFlameOfTheWest);
        assertTrue(rare.getSecondaryAbility() == null);
    }

    @Test
    public void FactorySurvivalTest() {
        RareItemsFactory rareItemsFactory = new RareItemsFactory(availableRareItems);
        rareItemsFactory.setMode(PreservedWord.gameModeSurvival);
        Rare rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(3), new SimpleIntegerProperty(3));
        assertTrue(rare instanceof TheOneRing);
        assertTrue(rare.getSecondaryAbility() == null);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TreeStump);
        assertTrue(rare.getSecondaryAbility() == null);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TreeStump);
        assertTrue(rare.getSecondaryAbility() == null);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof AndurilFlameOfTheWest);
        assertTrue(rare.getSecondaryAbility() == null);
    }

    @Test
    public void FactoryConfusingTest() {
        RareItemsFactory rareItemsFactory = new RareItemsFactory(availableRareItems);
        rareItemsFactory.setRareItemsList(availableRareItems);
        rareItemsFactory.setMode(PreservedWord.gameModeConfusing);
        Rare rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TheOneRing);
        assertTrue(rare.getSecondaryAbility() instanceof AndurilFlameOfTheWest);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof AndurilFlameOfTheWest);
        assertTrue(rare.getSecondaryAbility() instanceof TreeStump);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TreeStump);
        assertTrue(rare.getSecondaryAbility() instanceof TheOneRing);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TheOneRing);
        assertTrue(rare.getSecondaryAbility() instanceof TreeStump);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TreeStump);
        assertTrue(rare.getSecondaryAbility() instanceof TheOneRing);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof AndurilFlameOfTheWest);
        assertTrue(rare.getSecondaryAbility() instanceof TheOneRing);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TreeStump);
        assertTrue(rare.getSecondaryAbility() instanceof TheOneRing);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TreeStump);
        assertTrue(rare.getSecondaryAbility() instanceof TheOneRing);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TheOneRing);
        assertTrue(rare.getSecondaryAbility() instanceof AndurilFlameOfTheWest);
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof AndurilFlameOfTheWest);
        assertTrue(rare.getSecondaryAbility() instanceof TreeStump);
        rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare instanceof TreeStump);
        assertTrue(rare.getSecondaryAbility() instanceof AndurilFlameOfTheWest);
    }

    @Test
    public void FactoryConfusingNullTest() {
        RareItemsFactory rareItemsFactory = new RareItemsFactory(new ArrayList<String>());
        rareItemsFactory.setMode(PreservedWord.gameModeConfusing);
        Rare rare = rareItemsFactory.getInstance(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(rare == null);
    }
}
