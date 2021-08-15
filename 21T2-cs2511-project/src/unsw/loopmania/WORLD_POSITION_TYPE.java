package unsw.loopmania;

import unsw.loopmania.alliedSoldiers.TracedAlliedSoldier;
import unsw.loopmania.basicEnemies.*;
import unsw.loopmania.buildings.*;
import unsw.loopmania.card.*;
import unsw.loopmania.items.DoggieCoin;
import unsw.loopmania.items.Gold;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.rare.AndurilFlameOfTheWest;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.rare.TreeStump;
import unsw.loopmania.items.weapon.Staff;
import unsw.loopmania.items.weapon.Stake;
import unsw.loopmania.items.weapon.Sword;

import javax.swing.text.rtf.RTFEditorKit;

public enum WORLD_POSITION_TYPE {
    // normal
    GRASS(),
    PATH(),

    // alliedSoldiers
    TRACED_ALLIED_SOLDIER(TracedAlliedSoldier.class, "tracedAlliedSoldierImage"),

    // basic enemy
    SLUG(Slug.class, "slugImage"),
    VAMPIRE(Vampire.class, "vampireImage"),
    ZOMBIE(Zombie.class, "zombieImage"),
    SLUGZOMBIE(SlugZombie.class, "slugZombieImage"),
    DOGGIE(Doggie.class, "doggieImage"),
    ELAN_MUSKE(ElanMuske.class, "elanMuskeImage"),

    // building
    HERO_CASTLE_BUILDING(HeroCastleBuilding.class, "heroCastleImage"),
    BARRACKS_BUILDING(BarracksBuilding.class, "barracksBuildingImage"),
    CAMPFIRE_BUILDING(CampFireBuilding.class, "campfireBuildingImage"),
    TOWER_BUILDING(TowerBuilding.class, "towerBuildingImage"),
    TRAP_BUILDING(TrapBuilding.class, "trapBuildingImage"),
    VAMPIRE_CASTLE_BUILDING(VampireCastleBuilding.class, "vampireCastleBuildingImage"),
    VILLAGE_BUILDING(VillageBuilding.class, "villageCardImage"),
    ZOMBIE_PIT_BUILDING(ZombiePitBuilding.class, "zombiePitCardImage"),

    // card
    BARRACKS_CARD(BarracksCard.class, "barracksCardImage"),
    CAMPFIRE_CARD(CampfireCard.class, "campfireCardImage"),
    TOWER_CARD(TowerCard.class, "towerCardImage"),
    TRAP_CARD(TrapCard.class, "trapCardImage"),
    VAMPIRE_CASTLE_CARD(VampireCastleCard.class, "vampireCastleCardImage"),
    VILLAGE_CARD(VillageCard.class, "villageCardImage"),
    ZOMBIE_PIT_CARD(ZombiePitCard.class, "zombiePitCardImage"),

    // character
    CHARACTER(),

    // items
    GOLD(Gold.class, "goldImage"),
    HEALTH_POTION(HealthPotion.class, "healthPotionImage"),
    DOGGIE_COIN(DoggieCoin.class, "doggieCoinImage"),

    // weapon
    STAFF(Staff.class, "staffImage"),
    STAKE(Stake.class, "stakeImage"),
    SWORD(Sword.class, "swordImage"),

    // defence
    ARMOUR(Armour.class, "armourImage"),
    HELMET(Helmet.class, "helmetImage"),
    SHIELD(Shield.class, "shieldImage"),

    // RARE
    THE_ONE_RING(TheOneRing.class, "theOneRingImage"),
    ANDURIL(AndurilFlameOfTheWest.class, "andurilImage"),
    TREE_STUMP(TreeStump.class, "treeStumpImage");


    private Class<?> clazz;
    private String imageName;

    WORLD_POSITION_TYPE() {
    }

    WORLD_POSITION_TYPE(Class<?> clazz, String imageName) {
        this.clazz = clazz;
        this.imageName = imageName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getImageName() {
        return imageName;
    }

    public static WORLD_POSITION_TYPE getImageName(Class<?> clazz) {
        WORLD_POSITION_TYPE[] values = WORLD_POSITION_TYPE.values();
        for (WORLD_POSITION_TYPE value : values) {
            if (value.getClazz() == null) {
                continue;
            }
            if (value.getClazz().equals(clazz)) {
                return value;
            }
        }
        return null;
    }
}
