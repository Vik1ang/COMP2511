package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.json.JSONObject;
import unsw.loopmania.basicEnemies.*;
import unsw.loopmania.card.*;
import unsw.loopmania.items.DoggieCoin;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.rare.AndurilFlameOfTheWest;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.rare.TreeStump;
import unsw.loopmania.items.weapon.Staff;
import unsw.loopmania.items.weapon.Stake;
import unsw.loopmania.items.weapon.Sword;
import unsw.loopmania.utils.PreservedWord;

/**
 * The type Loop mania world json decode.
 */
public class LoopManiaWorldJsonDecode {
    /**
     * Decode entity.
     *
     * @param world      the world
     * @param jsonObject the json object
     * @return the entity
     */
    public static Entity decode(LoopManiaWorld world, JSONObject jsonObject) {
        int x = jsonObject.getInt("x");
        int y = jsonObject.getInt("y");
        Pair<Integer, Integer> pos = new Pair<>(x, y);
        Entity entity = null;
        switch (jsonObject.getString("type")) {
            // items
            case PreservedWord.itemArmour:
                entity = new Armour(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.itemHelmet:
                entity = new Helmet(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.itemShield:
                entity = new Shield(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.itemSword:
                entity = new Sword(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.itemStaff:
                entity = new Staff(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.itemStake:
                entity = new Stake(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.itemDoggieCoin:
                entity = new DoggieCoin(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            // cards
            case PreservedWord.cardBarracks:
                entity = new BarracksCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.cardCampfire:
                entity = new CampfireCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.cardTower:
                entity = new TowerCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.cardTrap:
                entity = new TrapCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.cardVampireCastle:
                entity = new VampireCastleCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.cardVillage:
                entity = new VillageCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.cardZombiePit:
                entity = new ZombiePitCard(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            // rare item
            case PreservedWord.itemTheOneRing:
                entity = new TheOneRing(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.itemAndurilFlameOfTheWest:
                entity = new AndurilFlameOfTheWest(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            case PreservedWord.itemTreeStump:
                entity = new TreeStump(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                break;
            // enemy
            case PreservedWord.enemyDoggie:
                entity = new Doggie(new PathPosition(world.getOrderedPath().indexOf(pos), world.getOrderedPath()));
                break;
            case PreservedWord.enemyElanMuske:
                entity = ElanMuske.getInstance(new PathPosition(world.getOrderedPath().indexOf(pos), world.getOrderedPath()));
                break;
            case PreservedWord.enemySlug:
                entity = new Slug(new PathPosition(world.getOrderedPath().indexOf(pos), world.getOrderedPath()));
                break;
            case PreservedWord.enemyVampire:
                entity = new Vampire(new PathPosition(world.getOrderedPath().indexOf(pos), world.getOrderedPath()));
                break;
            case PreservedWord.enemyZombie:
                entity = new Zombie(new PathPosition(world.getOrderedPath().indexOf(pos), world.getOrderedPath()));
                break;
        }
        return entity;
    }
}
