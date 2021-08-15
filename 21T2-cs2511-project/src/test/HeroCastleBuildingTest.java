package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import unsw.loopmania.buildings.HeroCastleBuilding;
import unsw.loopmania.character.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.utils.PreservedWord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

public class HeroCastleBuildingTest {

    HeroCastleBuilding heroCastleBuilding;
    SimpleIntegerProperty x;
    SimpleIntegerProperty y;
    List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    Pair<Integer, Integer> orderPath;


    @Before
    public void setUp() {
        x = new SimpleIntegerProperty(4);
        y = new SimpleIntegerProperty(0);
        heroCastleBuilding = new HeroCastleBuilding(x, y);

        // create a square path 4*4
        for (int i = 0; i < 5; i++) {
            orderPath = new Pair<>(i, 0);
            orderedPath.add(orderPath);
        }
        for (int i = 1; i < 5; i++) {
            orderPath = new Pair<>(4, i);
            orderedPath.add(orderPath);
        }
        for (int i = 3; i >= 0; i--) {
            orderPath = new Pair<>(i, 4);
            orderedPath.add(orderPath);
        }
        for (int i = 3; i > 0; i--) {
            orderPath = new Pair<>(0, i);
            orderedPath.add(orderPath);
        }

    }

    @Test
    public void getterAndSetterTest() {
        assertEquals(x.get(), heroCastleBuilding.getX());
        assertEquals(y.get(), heroCastleBuilding.getY());

        //reset position of building and pathTile
        x = new SimpleIntegerProperty(15);
        y = new SimpleIntegerProperty(16);

        heroCastleBuilding = new HeroCastleBuilding(x, y);
        assertEquals(x.get(), heroCastleBuilding.getX());
        assertEquals(y.get(), heroCastleBuilding.getY());

        assertEquals(heroCastleBuilding.getType(),"HeroCastleBuilding");

        heroCastleBuilding.setMode("Standard Mode");
        assertEquals(heroCastleBuilding.getMode(),"Standard Mode");
    }

    @Test
    public void isAtHereTest() {

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(1, orderedPath);
        character = new Character(pathPosition);
        assertFalse(heroCastleBuilding.canGiveHelp(character));

        // create a path position and character at (0,2)
        pathPosition = new PathPosition(2, orderedPath);
        character = new Character(pathPosition);
        assertFalse(heroCastleBuilding.canGiveHelp(character));

        // create a path position and character at (0,4)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertTrue(heroCastleBuilding.canGiveHelp(character));
    }

    @Test
    public void BasicTest() {
        heroCastleBuilding.startBuyAndSell();
        assertEquals(heroCastleBuilding.getHealthPotion(),0);
        assertEquals(heroCastleBuilding.getProtectiveGear(),0);
        assertEquals(heroCastleBuilding.isAtHeroCastle().get(),true);
        assertEquals(heroCastleBuilding.getHeroCastleItems().size(),7);

    }

    @Test
    public void BuyItemsNotHereTest() {
        heroCastleBuilding.startBuyAndSell();

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(1, orderedPath);
        character = new Character(pathPosition);
        assertEquals(heroCastleBuilding.canGiveHelp(character),false);
        assertEquals(heroCastleBuilding.canBuy(character,"Sword"),false);
    }

    @Test
    public void BuyItemsNoMoneyTest() {
        heroCastleBuilding.startBuyAndSell();

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertEquals(heroCastleBuilding.canGiveHelp(character),true);
        assertEquals(heroCastleBuilding.canBuy(character,"Sword"),false);
    }

    // standard mode
    @Test
    public void BuyItemsStandardSuccessTest() {
        heroCastleBuilding.startBuyAndSell();

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertEquals(heroCastleBuilding.canGiveHelp(character),true);
        character.setGolds(20);
        heroCastleBuilding.setMode("Standard Mode");
        assertEquals(heroCastleBuilding.canBuy(character,"Sword"),true);
    }

    // Survival Mode Success
    @Test
    public void BuyItemsSurvivalSuccessTest() {
        heroCastleBuilding.startBuyAndSell();

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertEquals(heroCastleBuilding.canGiveHelp(character),true);
        character.setGolds(20);
        heroCastleBuilding.setMode("Survival Mode");
        assertEquals(heroCastleBuilding.canBuy(character,"Sword"),true);
    }

    @Test
    public void BuyItemsConfusingSuccessTest() {
        heroCastleBuilding.startBuyAndSell();

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertEquals(heroCastleBuilding.canGiveHelp(character),true);
        character.setGolds(20);
        heroCastleBuilding.setMode(PreservedWord.gameModeConfusing);
        assertEquals(heroCastleBuilding.canBuy(character,"Sword"),true);
    }

    //Berserker Mode
    @Test
    public void BuyItemsBerserkerSuccessTest() {
        heroCastleBuilding.startBuyAndSell();

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertEquals(heroCastleBuilding.canGiveHelp(character),true);
        character.setGolds(20);
        heroCastleBuilding.setMode("Berserker Mode");
        assertEquals(heroCastleBuilding.canBuy(character,"Sword"),true);
    }


    //too many health potion + Survival Mode
    @Test
    public void BuyItemsTooManyHealthPotionTest() {
        heroCastleBuilding.startBuyAndSell();

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertEquals(heroCastleBuilding.canGiveHelp(character),true);
        character.setGolds(20);
        heroCastleBuilding.setMode("Survival Mode");
        assertEquals(heroCastleBuilding.canBuy(character,"Potion"),true);
        heroCastleBuilding.buyItem(character,new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        assertEquals(heroCastleBuilding.canBuy(character,"Potion"),false);
    }


    //too many protective gears + Berserker Mode
    @Test
    public void BuyItemsTooManyProtectiveTest() {
        heroCastleBuilding.startBuyAndSell();

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertEquals(heroCastleBuilding.canGiveHelp(character),true);
        character.setGolds(30);
        heroCastleBuilding.setMode("Berserker Mode");
        assertEquals(heroCastleBuilding.canBuy(character,"Shield"),true);
        heroCastleBuilding.buyItem(character,new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        assertEquals(heroCastleBuilding.canBuy(character,"Armour"),false);
        heroCastleBuilding.buyItem(character,new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
    }

    @Test
    public void noMode() {
        heroCastleBuilding.startBuyAndSell();

        // create a path position and character
        PathPosition pathPosition;
        Character character;

        // create a path position and character at (0,1)
        pathPosition = new PathPosition(4, orderedPath);
        character = new Character(pathPosition);
        assertEquals(heroCastleBuilding.canGiveHelp(character),true);
        character.setGolds(20);
        heroCastleBuilding.setType("HeroCastleBuilding");
        heroCastleBuilding.setMode("");
        assertEquals(heroCastleBuilding.canBuy(character,"Shield"),false);
    }

    @Test
    public void startBuyAndSellTest() {
        for (int i = 0; i < 20; i++) {
            heroCastleBuilding.startBuyAndSell();

            if (i == 0 || i == 2 || i == 5 || i == 9 || i == 14)
                assertEquals(true, heroCastleBuilding.isAtHeroCastle().get());
            else
                assertEquals(false, heroCastleBuilding.isAtHeroCastle().get());

            heroCastleBuilding.endBuyAndSell();
        }
    }



}
