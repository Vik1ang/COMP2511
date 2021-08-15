package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import unsw.loopmania.*;
import unsw.loopmania.basicEnemies.*;
import unsw.loopmania.character.Character;
import unsw.loopmania.items.DoggieCoin;
import unsw.loopmania.items.Gold;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.Items;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.defence.Defence;
import unsw.loopmania.items.rare.Rare;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.weapon.Staff;
import unsw.loopmania.items.weapon.Stake;
import unsw.loopmania.items.weapon.Sword;
import unsw.loopmania.items.weapon.Weapon;


public class ItemsTest {
    @Test
    public void StakeTest () {
        Stake StakeItem = new Stake(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BasicEnemy Zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy Slug = new Zombie(new PathPosition(1, generatePathPosition()));
        assertEquals(3, StakeItem.getAttackToEnemy(Zombie));
        assertEquals(3, StakeItem.getAttackToEnemy(Slug));
    }

    @Test
    public void StakeVampireTest () {
        Stake StakeItem = new Stake(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BasicEnemy Vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        assertEquals(10, StakeItem.getAttackToEnemy(Vampire));
    }

    @Test
    public void SwordTest () {
        Sword sword = new Sword(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BasicEnemy Zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy Slug = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy Vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        assertEquals(4, sword.getAttackToEnemy(Zombie));
        assertEquals(4, sword.getAttackToEnemy(Slug));
        assertEquals(4, sword.getAttackToEnemy(Vampire));
    }

    @Test
    public void StaffTest () {
        Weapon StaffItem = new Staff(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BasicEnemy Zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy Slug = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy Vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        assertEquals(2, StaffItem.getAttackToEnemy(Zombie) );
        assertEquals(2, StaffItem.getAttackToEnemy(Slug));
        assertEquals(2, StaffItem.getAttackToEnemy(Vampire));
    }


    @Test
    public void StaffTranceTest () {
        // seed0: 60 48 29 47 15 53 91 61
        Staff StaffItem = new Staff(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        BasicEnemy Zombie = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy Slug = new Zombie(new PathPosition(1, generatePathPosition()));
        BasicEnemy Vampire = new Vampire(new PathPosition(1, generatePathPosition()));
        assertEquals(Zombie.getTrance(), false);
        assertEquals(Slug.getTrance(), false);
        assertEquals(Vampire.getTrance(), false);
        StaffItem.getAttackToEnemy(Zombie);
        StaffItem.getAttackToEnemy(Slug);
        StaffItem.getAttackToEnemy(Vampire);
        assertEquals(Zombie.getTrance(), false);
        assertEquals(Slug.getTrance(), false);
        assertEquals(Vampire.getTrance(), false);
        StaffItem.getAttackToEnemy(Zombie);
        StaffItem.getAttackToEnemy(Slug);
        StaffItem.getAttackToEnemy(Vampire);
        assertEquals(Zombie.getTrance(), false);
        assertEquals(Slug.getTrance(), true);
        assertEquals(Vampire.getTrance(), false);
    }

    @Test
    public void HelmetDefendTest () {
        Helmet HelmetItem = new Helmet(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(HelmetItem instanceof Defence, true);
        assertEquals(HelmetItem.damageDefend(100), 97);
        assertEquals(HelmetItem.damageDefend(10), 7);
    }

    @Test
    public void HelmetAttackTest () {
        Helmet HelmetItem = new Helmet(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(3, HelmetItem.getAttackDecrease());
    }

    @Test
    public void ShieldTest () {
        Shield ShieldItem = new Shield(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(ShieldItem instanceof Defence, true);
        assertEquals(ShieldItem.damageDefend(100), 90);
        assertEquals(ShieldItem.damageDefend(10), 9);
    }

    @Test
    public void ArmourTest () {
        Defence armour = new Armour(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertTrue(armour instanceof Defence);
        assertTrue(armour instanceof Armour);
        assertEquals(armour.damageDefend(100), 50);
        assertEquals(armour.damageDefend(10), 5);
    }

    @Test
    public void GoldTest () {
        Gold GoldItem = new Gold(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(GoldItem.getAmount(), 1);

//        assertEquals(GoldItem.getAmount(), 1);
//
//        assertEquals(GoldItem.getAmount(), 3);
    }

    @Test
    public void HealthPotionTest () {
        HealthPotion healthPotionItem = new HealthPotion(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(healthPotionItem instanceof Items, true);
        assertEquals(healthPotionItem.getHealthRefill(), 20);
    }

    @Test
    public void HealthPotionPriceTest () {
        HealthPotion healthPotionItem = new HealthPotion(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(healthPotionItem.getBuyingPrice(), 5);
        assertEquals(healthPotionItem.getSellingPrice(), 2);
    }

    @Test
    public void WeaponPriceTest1 () {
        Weapon sword = new Sword(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        Weapon stake = new Stake(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(sword.getBuyingPrice(), 15);
        assertEquals(sword.getSellingPrice(), 1);
        assertEquals(stake.getBuyingPrice(), 10);
        assertEquals(stake.getSellingPrice(), 1);
    }

    @Test
    public void WeaponPriceTest2 () {
        Weapon staff = new Staff(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(staff.getBuyingPrice(), 15);
        assertEquals(staff.getSellingPrice(), 2);
    }

    @Test
    public void DefencePriceTest () {
        Defence helmet = new Helmet(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        Defence armour = new Armour(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        Defence shield = new Shield(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(helmet.getBuyingPrice(), 5);
        assertEquals(helmet.getSellingPrice(), 1);
        assertEquals(armour.getBuyingPrice(), 20);
        assertEquals(armour.getSellingPrice(), 1);
        assertEquals(shield.getBuyingPrice(), 10);
        assertEquals(shield.getSellingPrice(), 1);
    }

    @Test
    public void TheOneRingTest () {
        Items theOneRingItem = new TheOneRing(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(theOneRingItem instanceof Items, true);
        assertEquals(theOneRingItem instanceof Rare, true);
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
    public void getterandSetter() {
        Items item = new HealthPotion(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        item.setBuyingPrice(10);
        assertEquals(item.getBuyingPrice(),10);
        item.setSellingPrice(10);
        assertEquals(item.getSellingPrice(),10);
        assertEquals(item.buyingPriceProperty().get(),10);
        assertEquals(item.sellingPriceProperty().get(),10);
    }

    @Test
    public void testRareItem() {
        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals(100, theOneRing.getHealthRefill());
    }

    @Test
    public void testDoggieCoin() {
        DoggieCoin doggieCoin = new DoggieCoin(new SimpleIntegerProperty(2),new SimpleIntegerProperty(3));
        assertEquals("DoggieCoin", doggieCoin.getType());
        // seed4: 62 52 3 58 67 5
        // MEDIUM MODE
        assertEquals(4,doggieCoin.getSellingPrice());
        assertEquals(15,doggieCoin.getSellingPrice());
        assertEquals(10,doggieCoin.getSellingPrice());
        assertEquals(15,doggieCoin.getSellingPrice());
        assertEquals(4,doggieCoin.getSellingPrice());
        assertEquals(10,doggieCoin.getSellingPrice());

        // HIGH MODE
        doggieCoin.setNewSellingPriceMode("HIGH");
        //seed0: 60 48 29 47 15 53 91 61
        //assertEquals(148,doggieCoin.getSellingPrice());
        assertEquals(129,doggieCoin.getSellingPrice());
        assertEquals(147,doggieCoin.getSellingPrice());
        assertEquals(115,doggieCoin.getSellingPrice());
        assertEquals(153,doggieCoin.getSellingPrice());
        assertEquals(9,doggieCoin.getSellingPrice());
        assertEquals(39,doggieCoin.getSellingPrice());

        // low MODE
        doggieCoin.setNewSellingPriceMode("LOW");
        //seed6 [11,76,66,78,41,3,77,4]
        assertEquals(1,doggieCoin.getSellingPrice());
        assertEquals(1,doggieCoin.getSellingPrice());
        assertEquals(1,doggieCoin.getSellingPrice());
        assertEquals(1,doggieCoin.getSellingPrice());
        assertEquals(1,doggieCoin.getSellingPrice());
        assertEquals(1,doggieCoin.getSellingPrice());
        assertEquals(1,doggieCoin.getSellingPrice());

        // seed4: 62 52 3 58 67 5
        // MEDIUM MODE
        doggieCoin.setNewSellingPriceMode("MEDIUM");
        //assertEquals(14,doggieCoin.getSellingPrice());
        assertEquals(4,doggieCoin.getSellingPrice());
        assertEquals(12,doggieCoin.getSellingPrice());


    }

}
