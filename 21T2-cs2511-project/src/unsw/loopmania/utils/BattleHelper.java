package unsw.loopmania.utils;

import unsw.loopmania.alliedSoldiers.AlliedSoldier;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.basicEnemies.Vampire;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.CampFireBuilding;
import unsw.loopmania.buildings.TowerBuilding;
import unsw.loopmania.buildings.TrapBuilding;
import unsw.loopmania.character.Character;
import unsw.loopmania.items.Items;
import unsw.loopmania.items.defence.Defence;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.rare.Rare;
import unsw.loopmania.items.weapon.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Battle helper.
 */
public class BattleHelper {

    /**
     * alliedSoldier fight to an Enemy
     *
     * @param alliedSoldier the allied soldier
     * @param enemy         the enemy
     */
    public static void alliedSoldierToEnemy(AlliedSoldier alliedSoldier, BasicEnemy enemy) {
        int alliedSoldierAttack = alliedSoldier.attack();
        enemy.gotAttack(alliedSoldierAttack);
    }

    /**
     * enemy bite to an alliedSoldier
     *
     * @param alliedSoldier the allied soldier
     * @param enemy         the enemy
     */
    public static void enemyToAlliedSoldier(AlliedSoldier alliedSoldier, BasicEnemy enemy) {
        int enemyBite = enemy.getBiteAlliedSoldier(alliedSoldier);
        // if the allied solder is not transferred
        // reduce health
        if (!alliedSoldier.isTransferred())
            alliedSoldier.gotAttack(enemyBite);
    }

    /**
     * character fight to an Enemy
     *
     * @param character     the character
     * @param enemy         the enemy
     * @param equippedItems the equipped items
     * @param buildings     the buildings
     */
    public static void characterToEnemy(Character character, BasicEnemy enemy,
                                        List<Items> equippedItems, List<Building> buildings) {

        // if the character is stunned, directly return
        if (character.isStun()) {
            character.setStun(false);
            return;
        }
        // get the attack from character
        int characterAttack = character.attack();
        // get campfire
        characterAttack <<= campfireInsideBattleRadius(buildings, character);
        // get the attack from weapons and check the equipped items first
        for (Items item : equippedItems) {
            if (item instanceof Weapon) {
                characterAttack += ((Weapon) item).getAttackToEnemy(enemy);
            } else if (item instanceof Helmet) {
                characterAttack -= ((Helmet) item).getAttackDecrease();
                characterAttack = Math.max(0, characterAttack);
            } else if (item instanceof Rare) {
                characterAttack += ((Rare) item).getAttackToEnemy(enemy);
            }
        }
        // if the enemy is not trance
        if (!enemy.getTrance())
            enemy.gotAttack(characterAttack);
    }

    /**
     * character fight to an Enemy
     *
     * @param character     the character
     * @param enemy         the enemy
     * @param equippedItems the equipped items
     */
    public static void enemyToCharacter(Character character, BasicEnemy enemy, List<Items> equippedItems) {
        int enemyBite = 0;
        enemyBite = enemy.getBiteCharacter(character, hasShield(equippedItems));

        // get damage defence form items
        for (Items item: equippedItems) {
            if (item instanceof Defence) enemyBite = ((Defence) item).damageDefend(enemyBite);
            else if (item instanceof Rare) enemyBite = ((Rare) item).damageDefend(enemyBite, enemy);
        }
        character.gotBite(enemyBite);
    }

    /**
     * Has shield boolean.
     *
     * @param equippedItems the equipped items
     * @return the boolean
     */
    public static boolean hasShield(List<Items> equippedItems) {
        for (Items items: equippedItems) {
            if (items instanceof Shield) return true;
        }
        return false;
    }


    /**
     * helper function to calculate the number of Campfires inside the battle radius
     * @param character
     * @param buildings
     * @return number of Campfires
     */
    private static int campfireInsideBattleRadius(List<Building> buildings, Character character) {
        int numberOfCampfire = 0;
        for (Building building: buildings) {
            if (building instanceof CampFireBuilding) {
                if (building.canGiveHelp(character))
                    numberOfCampfire ++;
            }
        }
        return numberOfCampfire;
    }

    /**
     * Handle trap.
     *
     * @param enemy     the enemy
     * @param buildings the buildings
     */
    public static void handleTrap(BasicEnemy enemy, List<Building> buildings) {
        if (enemy.checkDeath()) return;
        int trapHit = 0;
        ArrayList<Building> usedTrap = new ArrayList<>();
        for (Building b: buildings) {
            if (b instanceof TrapBuilding && b.canGiveHelp(enemy)) {
                trapHit += ((TrapBuilding) b).attack();
                usedTrap.add(b);
            }
        }
        enemy.gotAttack(trapHit);
        for (Building b: usedTrap) {
            buildings.remove(b);
            b.destroy();
        }
    }

    /**
     * Handle tower building.
     *
     * @param enemy     the enemy
     * @param buildings the buildings
     */
    public static void handleTowerBuilding(BasicEnemy enemy, List<Building> buildings) {
        if (enemy.checkDeath()) return;
        int towerBuildingHit = 0;
        for (Building b: buildings) {
            if (b instanceof TowerBuilding && b.canGiveHelp(enemy))
                towerBuildingHit += ((TowerBuilding) b).getAttackIncrease();
        }
        enemy.gotAttack(towerBuildingHit);
    }

}