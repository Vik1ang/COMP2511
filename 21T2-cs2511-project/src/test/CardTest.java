package test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.WORLD_POSITION_TYPE;
import unsw.loopmania.buildings.*;
import unsw.loopmania.card.VampireCastleCard;
import unsw.loopmania.card.*;

import unsw.loopmania.PathTile;
import unsw.loopmania.buildings.CampFireBuilding;
import unsw.loopmania.character.Character;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.weapon.Staff;
import unsw.loopmania.items.weapon.Stake;
import unsw.loopmania.items.weapon.Sword;
import unsw.loopmania.utils.PackageHelper;
import unsw.loopmania.utils.PreservedWord;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class CardTest {

    SimpleIntegerProperty x;
    SimpleIntegerProperty y;

    private WORLD_POSITION_TYPE[][] worldPositionType = new WORLD_POSITION_TYPE[5][5];


    @Before
    public void setUp() {
        // create a square path 2*2
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 1) {
                    worldPositionType[i][j] = WORLD_POSITION_TYPE.PATH;
                    continue;
                }
                worldPositionType[i][j] = WORLD_POSITION_TYPE.GRASS;
            }
        }

    }

    //VamPireCardTest
    @Test
    public void canPlace_vampire (){
        setUp();
        Card vampireCastleCard = new VampireCastleCard(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        assertEquals(vampireCastleCard.getType(),"VampireCastleCard");
        assertEquals(vampireCastleCard.getBuilding(1, 1) instanceof VampireCastleBuilding, true);
        assertEquals(true, vampireCastleCard.canPlaceCard(1, 2, worldPositionType));
        assertEquals(false, vampireCastleCard.canPlaceCard(1, 3, worldPositionType));
        assertEquals(true,vampireCastleCard.canPlaceCard(1, 0, worldPositionType));
        assertEquals(true,vampireCastleCard.canPlaceCard(1, 0, worldPositionType));
        assertEquals(false,vampireCastleCard.canPlaceCard(4, 4, worldPositionType));

    }


    //BarracksCardTest

    @Test
    public void canPlace_barracks (){
        setUp();
        Card barracksCard = new BarracksCard(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));

        assertEquals(barracksCard.getType(),"BarracksCard");
        assertEquals(true, barracksCard.canPlaceCard(1, 1, worldPositionType));
        assertEquals(false, barracksCard.canPlaceCard(1, 2, worldPositionType));
        assertEquals(false, barracksCard.canPlaceCard(1, 3, worldPositionType));

    }

    //CampfireCardTest
    @Test
    public void canPlace_campfire (){
        setUp();
        Card campfireCard = new CampfireCard(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));

        assertEquals(campfireCard.getType(),"CampfireCard");
        assertEquals(campfireCard.getBuilding(1,1) instanceof  CampFireBuilding, true);
        assertEquals(false, campfireCard.canPlaceCard(1, 1, worldPositionType));
        assertEquals(true, campfireCard.canPlaceCard(1, 2, worldPositionType));
        assertEquals(true, campfireCard.canPlaceCard(1, 3, worldPositionType));
    }

    //TowerCardTest
    @Test
    public void canPlace_towercard (){
        setUp();
        TowerCard towerCard = new TowerCard(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));

        assertEquals(towerCard.getType(),"TowerCard");
        assertEquals(towerCard.getBuilding(1,1) instanceof TowerBuilding, true);
        assertEquals(false, towerCard.canPlaceCard(1, 1, worldPositionType));
        assertEquals(true, towerCard.canPlaceCard(1, 2, worldPositionType));
        assertEquals(false, towerCard.canPlaceCard(1, 3, worldPositionType));
        assertEquals(true,towerCard.canPlaceCard(0, 0, worldPositionType));
        assertEquals(false,towerCard.canPlaceCard(4, 4, worldPositionType));
    }

    //VillageCardTest
    @Test
    public void canPlace_villagecard (){
        setUp();
        Card villageCard = new VillageCard(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));

        assertEquals(villageCard.getType(),"VillageCard");
        assertEquals(villageCard.getBuilding(1, 1) instanceof VillageBuilding, true);
        assertEquals(true, villageCard.canPlaceCard(1, 1, worldPositionType));
        assertEquals(false, villageCard.canPlaceCard(1, 2, worldPositionType));
        assertEquals(false, villageCard.canPlaceCard(1, 3, worldPositionType));
    }

    //ZoombiepitCardTest
    @Test
    public void canPlace_zoombiepit (){
        setUp();
        Card zombiepitCard = new ZombiePitCard(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));

        assertEquals(zombiepitCard.getType(),"ZombiePitCard");
        assertEquals(zombiepitCard.getBuilding(2,3) instanceof ZombiePitBuilding, true);
        assertEquals(true, zombiepitCard.canPlaceCard(1, 2, worldPositionType));
        assertEquals(false, zombiepitCard.canPlaceCard(1, 3, worldPositionType));
        assertEquals(true,zombiepitCard.canPlaceCard(1, 0, worldPositionType));
        assertEquals(true,zombiepitCard.canPlaceCard(0, 0, worldPositionType));
        assertEquals(false,zombiepitCard.canPlaceCard(4, 4, worldPositionType));
    }

    //trapCardTest

    @Test
    public void canPlace_trapcard (){
        setUp();
        Card trapCard = new TrapCard(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));

        assertEquals(trapCard.getType(),"TrapCard");
        assertEquals(trapCard.getBuilding(2,3) instanceof TrapBuilding, true);
        assertEquals(true, trapCard.canPlaceCard(1, 1, worldPositionType));
        assertEquals(false, trapCard.canPlaceCard(1, 2, worldPositionType));
        assertEquals(false, trapCard.canPlaceCard(1, 3, worldPositionType));
    }


    @Test
    public void CardReachMaxUnitTest1() {
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        assertEquals(0, character.getGolds());
        assertEquals(0, character.getExperience());
        PackageHelper.cardsReachMax(character);
        assertEquals(1, character.getGolds());
        assertEquals(50, character.getExperience());
        PackageHelper.cardsReachMax(character);
        assertEquals(2, character.getGolds());
        assertEquals(100, character.getExperience());
    }

    @Test
    public void CardReachMaxUnitTest2() {
        Character character = new Character(new PathPosition(1, generatePathPosition()));
        // seed1: 85 88 47 13 54 4 34 6 < 50
        String card = null;
        card = PackageHelper.cardsReachMax(character);
        assertEquals(null, card);
        card = PackageHelper.cardsReachMax(character);
        assertEquals(null, card);
        card = PackageHelper.cardsReachMax(character);
        assertEquals(PreservedWord.itemHelmet, card);
        card = PackageHelper.cardsReachMax(character);
        assertEquals(PreservedWord.itemStake, card);
        card = PackageHelper.cardsReachMax(character);
        assertEquals(null, card);
        card = PackageHelper.cardsReachMax(character);
        assertEquals(PreservedWord.itemShield, card);
        card = PackageHelper.cardsReachMax(character);
        assertEquals(PreservedWord.itemShield, card);
        card = PackageHelper.cardsReachMax(character);
        assertEquals(PreservedWord.itemSword, card);
    }

    @Test
    public void CardToItemsTest() {
        assertTrue(PackageHelper.getItemsFromString(PreservedWord.itemHelmet) instanceof Helmet);
        assertTrue(PackageHelper.getItemsFromString(PreservedWord.itemArmour) instanceof Armour);
        assertTrue(PackageHelper.getItemsFromString(PreservedWord.itemShield) instanceof Shield);
        assertTrue(PackageHelper.getItemsFromString(PreservedWord.itemStaff) instanceof Staff);
        assertTrue(PackageHelper.getItemsFromString(PreservedWord.itemStake) instanceof Stake);
        assertTrue(PackageHelper.getItemsFromString(PreservedWord.itemSword) instanceof Sword);
        assertTrue(PackageHelper.getItemsFromString(PreservedWord.itemDoggieCoin) == null);
    }

    public List<Pair<Integer, Integer>> generatePathPosition() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 1);
        Pair<Integer, Integer> pair2 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair3 = new Pair<>(1, 3);
        Pair<Integer, Integer> pair4 = new Pair<>(2, 3);
        Pair<Integer, Integer> pair5 = new Pair<>(2, 2);
        Pair<Integer, Integer> pair6 = new Pair<>(2, 1);
        orderedPath.add(pair1);
        orderedPath.add(pair2);
        orderedPath.add(pair3);
        orderedPath.add(pair4);
        orderedPath.add(pair5);
        orderedPath.add(pair6);
        return orderedPath;
    }

    @Test
    public void tooManyCards(){
        setUp();
        Card trapCard1 = new TrapCard(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        LoopManiaWorld loopManiaWorld = new LoopManiaWorld(2,17,generatePathPosition());
        loopManiaWorld.getCardEntities().add(trapCard1);
        assertEquals(null,loopManiaWorld.removeCard(0));


    }


}
