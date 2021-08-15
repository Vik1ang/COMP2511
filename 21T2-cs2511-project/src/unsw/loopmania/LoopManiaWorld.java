package unsw.loopmania;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.json.JSONObject;
import unsw.loopmania.alliedSoldiers.AlliedSoldier;
import unsw.loopmania.alliedSoldiers.TracedAlliedSoldier;
import unsw.loopmania.basicEnemies.*;
import unsw.loopmania.buildings.*;
import unsw.loopmania.card.*;
import unsw.loopmania.character.Character;
import unsw.loopmania.items.DoggieCoin;
import unsw.loopmania.items.Gold;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.Items;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.rare.AndurilFlameOfTheWest;
import unsw.loopmania.items.rare.Rare;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.rare.TreeStump;
import unsw.loopmania.items.weapon.Weapon;
import unsw.loopmania.utils.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * A backend world.
 * <p>
 * A world can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 */
public class LoopManiaWorld {
    /**
     * The constant unequippedInventoryWidth.
     */
    public static final int unequippedInventoryWidth = 4;
    /**
     * The constant unequippedInventoryHeight.
     */
    public static final int unequippedInventoryHeight = 4;

    /**
     * width of the world in GridPane cells
     */
    private final int width;

    /**
     * height of the world in GridPane cells
     */
    private final int height;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private final List<Entity> nonSpecifiedEntities;

    private Character character;

    private final List<BasicEnemy> enemies;

    private final List<AlliedSoldier> alliedSoldiers;

    private final List<Card> cardEntities;

    private final List<Items> healthPotionsOnGround;

    private final List<HealthPotion> equippedHealthPotions;

    private final List<Items> goldOnGround;

    private final List<Items> unequippedInventoryItems;

    private final List<Items> equippedItems;

    private final List<Building> buildingEntities;

    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse them
     */
    private final List<Pair<Integer, Integer>> orderedPath;

    private int cycleComplete;

    private final List<Entity> initLoopManiaEntityList;

    private HeroCastleBuilding heroCastleBuilding;

    private WORLD_POSITION_TYPE[][] worldPositionType;


    private Items equippedWeapon = null;

    private Items equippedArmor = null;

    private Items equippedHelmet = null;

    private Items equippedShield = null;

    private final SimpleIntegerProperty equippedHealthPotionsNum;

    private final SimpleIntegerProperty alliedSoldierNum;

    private final Random initRandom;

    private List<Pair<String, Integer>> goals;

    private String elanMode;

    private List<String> availableRareItems;

    private final RareItemsFactory rareItemsFactory;

    private JSONObject initialPathInfo;

    private boolean bossIsDead;

    /**
     * create the world (constructor)
     *
     * @param width       width of world in number of cells
     * @param height      height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = new Character(new PathPosition(0, orderedPath));
        enemies = new ArrayList<>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
        goldOnGround = new ArrayList<>();
        healthPotionsOnGround = new ArrayList<>();
        cycleComplete = 0;
        initLoopManiaEntityList = new ArrayList<>();
        alliedSoldiers = new ArrayList<>();
        equippedHealthPotions = new ArrayList<>();
        equippedItems = new ArrayList<>();
        equippedHealthPotionsNum = new SimpleIntegerProperty(0);
        initRandom = new Random(2511);
        goals = new ArrayList<>();
        alliedSoldierNum = new SimpleIntegerProperty(0);
        elanMode = "MEDIUM";
        availableRareItems = new ArrayList<>();
        rareItemsFactory = new RareItemsFactory(availableRareItems);
        bossIsDead = false;
        updateWorldPositionType();
    }


    /**
     * Sets hero castle building.
     *
     * @param heroCastleBuilding the hero castle building
     */
    public void setHeroCastleBuilding(HeroCastleBuilding heroCastleBuilding) {
        this.heroCastleBuilding = heroCastleBuilding;
    }

    /**
     * Gets hero castle building.
     *
     * @return the hero castle building
     */
    public HeroCastleBuilding getHeroCastleBuilding() {
        return heroCastleBuilding;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Increase equipped health potions num.
     *
     * @param num the num
     */
    public void increaseEquippedHealthPotionsNum(int num) {
        this.equippedHealthPotionsNum.set(this.equippedHealthPotionsNum.get() + num);
    }

    /**
     * Use health potion.
     */
    public void useHealthPotion() {
        if (equippedHealthPotionsNum.get() > 0) {
            equippedHealthPotionsNum.set(equippedHealthPotionsNum.get() - 1);
            HealthPotion h = equippedHealthPotions.get(0);
            character.increaseHealth(h.getHealthRefill());
            equippedHealthPotions.remove(h);
            h.destroy();
        }
    }

    /**
     * Gets equipped health potions num.
     *
     * @return the equipped health potions num
     */
    public SimpleIntegerProperty getEquippedHealthPotionsNum() {
        return equippedHealthPotionsNum;
    }

    /**
     * Gets rare list.
     *
     * @return the rare list
     */
    public List<String> getRareList() {
        return availableRareItems;
    }

    /**
     * Sets mode.
     *
     * @param mode the mode
     */
    public void setMode(String mode) {
        rareItemsFactory.setMode(mode);
        HeroCastleBuilding h = getHeroCastleBuilding();
        if (h == null) return;
        h.setMode(mode);

    }


    /**
     * Gets character health percentage.
     *
     * @return the character health percentage
     */
    public SimpleDoubleProperty getCharacterHealthPercentage() {
        return character.getHealthPercentage();
    }

    /**
     * Gets character health.
     *
     * @return the character health
     */
    public int getCharacterHealth() {
        return character.getHealth();
    }


    /**
     * Gets character gold.
     *
     * @return the character gold
     */
    public SimpleIntegerProperty getCharacterGold() {
        return character.getGoldsProperty();
    }


    /**
     * Gets character exp.
     *
     * @return the character exp
     */
    public SimpleIntegerProperty getCharacterExp() {
        return character.getExperienceProperty();
    }

    /**
     * Sets allied soldier num.
     *
     * @param alliedSoldierNum the allied soldier num
     */
    public void setAlliedSoldierNum(int alliedSoldierNum) {
        this.alliedSoldierNum.set(alliedSoldierNum);
    }

    /**
     * Gets allied soldier num.
     *
     * @return the allied soldier num
     */
    public SimpleIntegerProperty getAlliedSoldierNum() {
        setAlliedSoldierNum(alliedSoldiers.size());
        return alliedSoldierNum;
    }

    /**
     * Gets allied soldiers.
     *
     * @return the allied soldiers
     */
    public List<AlliedSoldier> getAlliedSoldiers() {
        return alliedSoldiers;
    }

    /**
     * Gets character win.
     *
     * @return the character win
     */
    public SimpleBooleanProperty getCharacterWin() {
        return character.isWinProperty();
    }

    /**
     * Check character win.
     */
    public void checkCharacterWin() {
        character.checkWinStatus(this.getCycleComplete(), this.bossIsDead,this.goals);
    }

    /**
     * Gets character death.
     *
     * @return the character death
     */
    public SimpleBooleanProperty getCharacterDeath() {
        return character.isDeadProperty();
    }


    /**
     * Gets character.
     *
     * @return the character
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Gets unequipped inventory items.
     *
     * @return the unequipped inventory items
     */
    public List<Items> getUnequippedInventoryItems() {
        return unequippedInventoryItems;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     *
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Gets cycle complete.
     *
     * @return the cycle complete
     */
    public int getCycleComplete() {
        return cycleComplete;
    }

    /**
     * Gets init loop mania entity list.
     *
     * @return the init loop mania entity list
     */
    public List<Entity> getInitLoopManiaEntityList() {
        return initLoopManiaEntityList;
    }

    /**
     * Gets card entities.
     *
     * @return the card entities
     */
    public List<Card> getCardEntities() {
        return cardEntities;
    }

    /**
     * Gets enemies.
     *
     * @return the enemies
     */
    public List<BasicEnemy> getEnemies() {
        return enemies;
    }

    /**
     * Gets health potions on ground.
     *
     * @return the health potions on ground
     */
    public List<Items> getHealthPotionsOnGround() {
        return healthPotionsOnGround;
    }

    /**
     * Gets gold on ground.
     *
     * @return the gold on ground
     */
    public List<Items> getGoldOnGround() {
        return goldOnGround;
    }

    /**
     * Gets equipped items.
     *
     * @return the equipped items
     */
    public List<Items> getEquippedItems() {
        return equippedItems;
    }

    /**
     * Sets world position type.
     *
     * @param worldPositionType the world position type
     */
    public void setWorldPositionType(WORLD_POSITION_TYPE[][] worldPositionType) {
        this.worldPositionType = worldPositionType;
    }

    /**
     * Gets goals.
     *
     * @return the goals
     */
    public List<Pair<String, Integer>> getGoals() {
        return goals;
    }

    /**
     * Sets goals.
     *
     * @param goals the goals
     */
    public void setGoals(List<Pair<String, Integer>> goals) {
        this.goals = goals;
    }

    /**
     * Sets boss is dead.
     *
     * @param bossIsDead the boss is dead
     */
    public void setBossIsDead(boolean bossIsDead) {
        this.bossIsDead = bossIsDead;
    }

    /**
     * Gets cycle complete property.
     *
     * @return the cycle complete property
     */
    public SimpleIntegerProperty getCycleCompleteProperty() {
        return character.cycleCompleteProperty();
    }

    /**
     * Get world position type world position type [ ] [ ].
     *
     * @return the world position type [ ] [ ]
     */
    public WORLD_POSITION_TYPE[][] getWorldPositionType() {
        return worldPositionType;
    }

    /**
     * Gets hero castle.
     *
     * @return the hero castle
     */
    public HeroCastleBuilding getHeroCastle() {
        for (Building b : buildingEntities) {
            if (b instanceof HeroCastleBuilding) return (HeroCastleBuilding) b;
        }
        return null;
    }


    /**
     * Sets available rare items.
     *
     * @param availableRareItems the available rare items
     */
    public void setAvailableRareItems(List<String> availableRareItems) {
        this.availableRareItems = availableRareItems;
        this.rareItemsFactory.setRareItemsList(availableRareItems);
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     *
     * @param entity the entity
     */
    public void addEntity(Entity entity) {
        if (entity instanceof HeroCastleBuilding) {
            buildingEntities.add((HeroCastleBuilding) entity);
        }
        // for adding non-specific entities (ones without another dedicated list)
        nonSpecifiedEntities.add(entity);
    }

    /**
     * spawns enemies if the conditions warrant it, adds to world
     *
     * @return list of the enemies to be displayed on screen
     */
    public List<BasicEnemy> possiblySpawnEnemies() {
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
        List<BasicEnemy> spawningEnemies = new ArrayList<>();
       if (pos != null) {
            int indexInPath = orderedPath.indexOf(pos);
            // 0 - 3: Slug, 4 - 5: Zombie 6: Vampire
            int choice = RandomUtils.generateIntegerRandomBySeed(6, 0, initRandom);
            BasicEnemy enemy = null;
            if (choice >= 0 && choice <= 3) {
                enemy = new Slug(new PathPosition(indexInPath, orderedPath));
            }
           if (enemy != null) {
            enemies.add(enemy);
            spawningEnemies.add(enemy);
           }
       }

        worldPositionType = LoopManiaUtils.updateWorldPosition(
                height,
                width,
                enemies,
//                alliedSoldiers,
                buildingEntities,
                character,
                goldOnGround,
                healthPotionsOnGround,
                orderedPath);

        return spawningEnemies;
    }

    /**
     * kill an enemy
     *
     * @param enemy enemy to be killed
     */
    private void killEnemy(BasicEnemy enemy) {
        if (enemy instanceof ElanMuske) {
            elanMode = "LOW";
        }
        enemy.destroy();
        enemies.remove(enemy);
    }

    private void killAlliedSoldier(AlliedSoldier alliedSoldier) {
        alliedSoldier.destroy();
        alliedSoldiers.remove(alliedSoldier);
    }

    /**
     * run the expected battles in the world, based on current world state
     *
     * @return list of enemies which have been killed
     */
    public List<BasicEnemy> runBattles() {
        List<BasicEnemy> defeatedEnemies = new ArrayList<>();
        defeatedEnemies.sort((o1, o2) -> o1.getBite() - o2.getBite());
        boolean lostTheBattle = false;
        for (int i = 0; i < enemies.size(); i++) {
            BattleHelper.handleTrap(enemies.get(i), buildingEntities);
            if (enemies.get(i).checkDeath()) {
                defeatedEnemies.add(enemies.get(i));
                continue;
            }
            if (enemies.get(i) instanceof ElanMuske) {
                elanMode = "HIGH";
            }
            // Pythagoras: a^2+b^2 < radius^2 to see if within radius
            if (enemies.get(i).insideBattleRadius(character.getX(), character.getY())) {
                // fight...
                // tower
                BattleHelper.handleTowerBuilding(enemies.get(i), buildingEntities);
                // allied soldiers fight against enemy
                alliedSolderAndEnemyBattle(enemies.get(i));
                // character fight against enemy
                characterAndEnemyBattle(enemies.get(i));

                // handle death
                if (character.checkDeath()) {
                    lostTheBattle = true;
                    break;
                } else {
                    BasicEnemy e = enemies.get(i);
                    defeatedEnemies.add(e);
                    if (e instanceof SlugZombie)
                        ((SlugZombie) e).HandleDeath(enemies);
                }
            }
        }
        removeTracedAlliedSoldier();
        setAlliedSoldierNum(alliedSoldiers.size());
        for (BasicEnemy e : defeatedEnemies) {
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from the enemies list
            // if we killEnemy in prior loop, we get java.util.ConcurrentModificationException
            // due to mutating list we're iterating ove
            killEnemy(e);
        }
        if (lostTheBattle) {
            // if the character die and there exist TheOneRing
            applyTheOneRing();
            if (character.getHealth() <= 0) character.setIsDead(true);

        }
        return defeatedEnemies;
    }

    private void applyTheOneRing() {
        Items theOneRing = null;
        for (Items items : unequippedInventoryItems) {
            if (items instanceof Rare && ((Rare) items).getHealthRefill() > 0) {
                theOneRing = items;
                character.increaseHealth(((Rare) items).getHealthRefill());
                break;
            }
        }
        if (theOneRing != null) removeUnequippedInventoryItem(theOneRing);
    }

    private void removeTracedAlliedSoldier() {
        ArrayList<AlliedSoldier> traced = new ArrayList<>();
        for (AlliedSoldier a : alliedSoldiers) {
            if (a instanceof TracedAlliedSoldier) traced.add(a);
        }
        for (AlliedSoldier a : traced) {
            killAlliedSoldier(a);
        }
    }


    /**
     * check if the character can pick up any items (gold and health potions)
     * pick those items up
     * increase gold/refill health
     * remove them from list
     */
    public void pickUpItemsOnGround() {
        character.pickUpGold(goldOnGround);
        List<HealthPotion> healthPotionPickedUp = character.pickUpHealthPotion(healthPotionsOnGround);
        equippedHealthPotions.addAll(healthPotionPickedUp);
        equippedHealthPotionsNum.set(equippedHealthPotionsNum.get() + healthPotionPickedUp.size());
    }


    /**
     * Allied solder and enemy battle.
     *
     * @param e the e
     */
    public void alliedSolderAndEnemyBattle(BasicEnemy e) {
        while (!alliedSoldiers.isEmpty() && !e.checkDeath()) {
            AlliedSoldier alliedSoldier = alliedSoldiers.get(0);
            // check if both the enemy and the alliedSoldier is alive
            if (e.checkDeath() || e.getTrance()) break;
            if (alliedSoldier.checkDeath() || alliedSoldier.isTransferred()) {
                // if the current alliedSoldier dead or transferred by Zombie
                // kill it
                killAlliedSoldier(alliedSoldier);
                // continue the next loop if there's more alliedSoldiers
                continue;
            }
            // if the alliedSoldier is a traced enemy
            if (alliedSoldier instanceof TracedAlliedSoldier) {
                TracedAlliedSoldier soldier = (TracedAlliedSoldier) alliedSoldier;
                soldier.decreaseTranceRemain();
                // if the trace finish
                if (soldier.getTranceRemain() < 0) {
                    // turn it back to enemy
                    BasicEnemy enemy = soldier.getEnemy();
                    enemies.add(enemy);
                    // kill the soldier
                    killAlliedSoldier(alliedSoldier);
                    continue;
                }
            }

            // if both the enemy and the alliedSoldier is alive
            // start battle
            while (!alliedSoldier.checkDeath() && !e.checkDeath()) {
                /*  alliedSoldier ---> enemy  */
                BattleHelper.alliedSoldierToEnemy(alliedSoldier, e);
                // if the current enemy dead, remove it
                if (e.checkDeath()) break;
                /*  enemy ---> alliedSoldier  */
                BattleHelper.enemyToAlliedSoldier(alliedSoldier, e);
                if (alliedSoldier.isTransferred()) {
                    PathPosition position = e.getPosition();
                    // create a new Zombie
                    Zombie newZombie = new Zombie(position);
                    enemies.add(newZombie);
                    break;
                }
            }
        }
    }


    /**
     * Character and enemy battle.
     *
     * @param e the e
     */
    public void characterAndEnemyBattle(BasicEnemy e) {
        while (!character.checkDeath() && !e.checkDeath()) {

            /*  character ---> enemy  */
            BattleHelper.characterToEnemy(character, e, equippedItems, buildingEntities);
            // check if the current enemy dead
            if (e.checkDeath()) {
                break;
            }
            // if the enemy is traced & not die
            // change it to allied soldier
            if (e.getTrance()) {
                TracedAlliedSoldier soldier = new TracedAlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
                if (e instanceof Slug) soldier.setEnemy(new Slug(e.getPosition()));
                else if (e instanceof Vampire) soldier.setEnemy(new Vampire(e.getPosition()));
                else if (e instanceof Zombie) soldier.setEnemy(new Zombie(e.getPosition()));

                alliedSoldiers.add(soldier);
                break;
            }

            /*  enemy ---> character  */
            BattleHelper.enemyToCharacter(character, e, equippedItems);
        }
    }

    /**
     * Gets award.
     *
     * @param e the e
     * @return the award
     */
    public List<StaticEntity> getAward(BasicEnemy e) {
        List<StaticEntity> awardList = new ArrayList<>();
        HashMap<String, String> award = e.getAward(character);
        if (award.containsKey("item")) {
            awardList.add(addUnequippedItems(award.get("item")));
        }
        if (award.containsKey("DoggieCoin")) {
            awardList.add(addUnequippedItems(award.get("DoggieCoin")));
        }
        if (award.containsKey("card")) {
            if (cardEntities.size() >= getWidth()) {
                String item = removeCard(0);
                if (item != null) {
                awardList.add(addUnequippedItems(item));
                }
            }
            awardList.add(addCard(award.get("card")));
        }
        if (award.containsKey("healthPotion")) {
            HealthPotion healthPotion = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            this.equippedHealthPotions.add(healthPotion);
            equippedHealthPotionsNum.set(equippedHealthPotionsNum.get() + 1);
        }
        if (award.containsKey("rare")) {
            awardList.add(addUnequippedItems(award.get("rare")));
        }
        return awardList;
    }

    /**
     * Interact with buildings boolean.
     *
     * @return the boolean
     */
    public boolean interactWithBuildings() {
        for (Building b : buildingEntities) {
            if (b instanceof BarracksBuilding) {
                if (b.canGiveHelp(character)) {
                    AlliedSoldier alliedSoldier = new AlliedSoldier(null, null);
                    alliedSoldiers.add(alliedSoldier);
                }
            }
            if (b instanceof VillageBuilding) {
                if (b.canGiveHelp(character)) {
                    character.increaseHealth(((VillageBuilding) b).getHealthRefill());
                }
            }
            if (b instanceof HeroCastleBuilding) {
                if (MathUtils.positionIsEqual(b.getX(), b.getY(), character.getX(), character.getY())) {
                    for (Items items : unequippedInventoryItems) {
                        if (items instanceof DoggieCoin) {
                            ((DoggieCoin) items).setNewSellingPriceMode(elanMode);
                        }
                    }
                    ((HeroCastleBuilding) b).startBuyAndSell();
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Finish buy and sell at castle.
     */
    public void finishBuyAndSellAtCastle() {
        for (Building b : buildingEntities) {
            if (b instanceof HeroCastleBuilding) {
                ((HeroCastleBuilding) b).endBuyAndSell();
            }
        }
    }

    /*
    check if a card can be placed in a specific place
    */

    /**
     * Can place card boolean.
     *
     * @param cardNodeX the card node x
     * @param cardNodeY the card node y
     * @param posX      the pos x
     * @param posY      the pos y
     * @return the boolean
     */
    public boolean canplaceCard(int cardNodeX, int cardNodeY, int posX, int posY) {
        Card card = null;
        for (Card c : cardEntities) {
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)) {
                card = c;
                break;
            }
        }
        return card != null && card.canPlaceCard(posX, posY, worldPositionType);
    }


    /**
     * spawn a card in the world and return the card entity
     *
     * @param type the type
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public Card addCard(String type) {
        return LoopManiaUtils.addCardHelper(type, cardEntities);
    }

    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     *
     * @param index the index of the card, from 0 to length-1
     * @return the string
     */
    public String removeCard(int index) {
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        String item = PackageHelper.cardsReachMax(character);
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
        return item;
    }

    /**
     * spawn an item in the world and return the sword entity
     *
     * @param type the type
     * @return an item to be spawned in the controller as a JavaFX node
     */
    public Items addUnequippedItems(String type) {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            Items item = unequippedInventoryItems.get(0);
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        return LoopManiaUtils.addUnequippedItemsHelper(type, firstAvailableSlot, unequippedInventoryItems, rareItemsFactory);
    }

    /**
     * Can buy item boolean.
     *
     * @param item the item
     * @return the boolean
     */
    public boolean canBuyItem(String item) {
        HeroCastleBuilding building = getHeroCastleBuilding();
        if (building == null) return false;
        return building.canBuy(character, item);
    }

    /**
     * Buy item items.
     *
     * @param item the item
     * @return the items
     */
    public Items buyItem(String item) {
        HeroCastleBuilding building = getHeroCastleBuilding();
        if (building == null) {
            return null;
        }
        Items newItem = null;
        if (item.equals("Potion")) {
            newItem = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            equippedHealthPotions.add((HealthPotion) newItem);
            equippedHealthPotionsNum.set(equippedHealthPotionsNum.get() + 1);
        } else
            newItem = addUnequippedItems(item);
        building.buyItem(character, newItem);
        return newItem;
    }

    /**
     * Sell item.
     *
     * @param x the x
     * @param y the y
     */
    public void sellItem(int x, int y) {
        Items newItem = null;
        for (Items item : unequippedInventoryItems) {
            if (item.getX() == x && item.getY() == y) {
                newItem = item;
            }
        }
        if (newItem != null) {
            character.incrementGolds(newItem.getSellingPrice());
            removeUnequippedInventoryItem(newItem);
        }
        shiftItemsFront(x, y);
    }


    /**
     * remove an item by x,y coordinates
     *
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y) {
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        if (item == null) {
            return;
        }
        removeUnequippedInventoryItem(item);
        shiftItemsFront(x, y);
    }

    /**
     * run moves which occur with every tick without needing to spawn anything immediately
     */
    public void runTickMoves() {
        character.moveDownPath();
        character.updateCycleComplete();
        moveBasicEnemies();
        cycleComplete = character.getCycleComplete();
        for (Items items: unequippedInventoryItems) {
            if (items instanceof DoggieCoin) {
                ((DoggieCoin) items).setNewSellingPriceMode(elanMode);
            }
        }

    }

    /**
     * remove an item from the unequipped inventory
     *
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item) {
        item.destroy();
        unequippedInventoryItems.remove(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     *
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Entity getUnequippedInventoryItemEntityByCoordinates(int x, int y) {
        for (Entity e : unequippedInventoryItems) {
            if ((e.getX() == x) && (e.getY() == y)) {
                return e;
            }
        }
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list (this is ordered based on age in the starter code)
     *
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index) {
        Entity item = unequippedInventoryItems.get(index);
        PackageHelper.itemsReachMax(character);
        int x = item.getX();
        int y = item.getY();
        item.destroy();
        unequippedInventoryItems.remove(index);
        shiftItemsFront(x, y);
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the unequipped inventory
     *
     * @return x, y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem() {
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available slot defined by looking row by row
        for (int y = 0; y < unequippedInventoryHeight; y++) {
            for (int x = 0; x < unequippedInventoryWidth; x++) {
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null) {
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     *
     * @param x x coordinate which can range from 0 to width-1
     */
    public void shiftCardsDownFromXCoordinate(int x) {
        for (Card c : cardEntities) {
            if (c.getX() >= x) {
                c.x().set(c.getX() - 1);
            }
        }
    }

    /**
     * shift unequipped items coordinates down starting from x coordinate
     *
     * @param x x coordinate which can range from 0 to width-1
     * @param y the y
     */
    public void shiftItemsFront(int x, int y) {
        int maxWidth = 3;
        for (Items item : unequippedInventoryItems) {
            if (item.getY() < y || (item.getY() == y && item.getX() < x)) {
                continue;
            }
            //x == 0 then move to the last row
            if (item.getX() == 0) {
                //column
                item.x().set(maxWidth);
                //row
                item.y().set(item.getY() - 1);
            } else {
                //column
                item.x().set(item.getX() - 1);
            }
        }
    }


    /**
     * move all enemies
     */
    private void moveBasicEnemies() {
        List<Pair<Integer, Integer>> campFireList = new ArrayList<>();
        for (BasicEnemy e : enemies) {
            for (Entity building : buildingEntities) {
                if (building instanceof CampFireBuilding) {
                    campFireList.add(new Pair<>(building.getX(), building.getY()));
                }
            }
            e.move(enemies, character, campFireList);
        }
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     *
     * @return null if random choice is that wont be spawning an enemy or it isn't possible, or random coordinate pair if should go ahead
     */
    public Pair<Integer, Integer> possiblyGetBasicEnemySpawnPosition() {
        if (enemies.size() < 2) {
            List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
            int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
            int startNotAllowed = (indexPosition - 2 + orderedPath.size()) % orderedPath.size();
            int endNotAllowed = (indexPosition + 3) % orderedPath.size();
            for (int i = endNotAllowed; i != startNotAllowed; i = (i + 1) % orderedPath.size()) {
                Integer x = orderedPath.get(i).getValue0();
                Integer y = orderedPath.get(i).getValue1();
                if (worldPositionType[x][y].equals(WORLD_POSITION_TYPE.PATH)) {
                    orderedPathSpawnCandidates.add(orderedPath.get(i));
                }
            }
//            if (orderedPathSpawnCandidates.size() == 0) {
//                return null;
//            }
            return orderedPathSpawnCandidates.get(RandomUtils.generateIntegerRandomBySeed(orderedPathSpawnCandidates.size(), 0, initRandom));
        }
        return null;
    }

    /**
     * remove a card by its x, y coordinates
     *
     * @param cardNodeX     x index from 0 to width-1 of card to be removed
     * @param cardNodeY     y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     * @return the building
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c : cardEntities) {
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)) {
                card = c;
                break;
            }
        }
        Building building = LoopManiaUtils.cardToBuildingHelper(card, buildingEntities, worldPositionType, buildingNodeX, buildingNodeY, cardNodeX, cardNodeY);
        destroyCard(card, cardNodeX);
        return building;
    }

    /**
     * Destroy card.
     *
     * @param card      the card
     * @param cardNodeX the card node x
     */
    public void destroyCard(Card card, int cardNodeX) {
        card.destroy();
        cardEntities.remove(card);
        shiftCardsDownFromXCoordinate(cardNodeX);
    }

    /**
     * Gets building entities.
     *
     * @return the building entities
     */
    public List<Building> getBuildingEntities() {
        return buildingEntities;
    }

    /**
     * Init new cycle complete list.
     *
     * @return the list
     */
    public List<Entity> initNewCycleComplete() {
        initLoopManiaEntityList.clear();
        List<Pair<Integer, Integer>> mapList = new ArrayList<>(orderedPath);

        int goldNumber = 0;
        if (goldOnGround.size() < 2) {
            goldNumber = RandomUtils.generateIntegerRandomBySeed(2, 1, initRandom);
        }
        int slugNumber = RandomUtils.generateIntegerRandomBySeed(2, 1, initRandom);
        int healthPotionNumber = 0;
        if (getEquippedHealthPotionsNum().get() < 5) {
            healthPotionNumber = RandomUtils.generateIntegerRandomBySeed(2, 1, initRandom);
        }
        int doggieNum = 0;
        if (getCycleComplete() >= 20 && getCycleComplete() % 5 == 0) {
            doggieNum = 1;
        }
        int elanMuskeNum = 0;
        if (getCycleComplete() >= 40 && character.getExperience() >= 10000 && !ElanMuske.isElanMuskeCreated()) {
            elanMuskeNum = 1;
        }

        try {
            LoopManiaUtils.addEntityInInitCycleHelper(goldNumber, initLoopManiaEntityList, Gold.class, mapList, orderedPath, initRandom);
            LoopManiaUtils.addEntityInInitCycleHelper(slugNumber, initLoopManiaEntityList, Slug.class, mapList, orderedPath, initRandom);
            LoopManiaUtils.addEntityInInitCycleHelper(healthPotionNumber, initLoopManiaEntityList, HealthPotion.class, mapList, orderedPath, initRandom);
            LoopManiaUtils.addEntityInInitCycleHelper(doggieNum, initLoopManiaEntityList, Doggie.class, mapList, orderedPath, initRandom);
            LoopManiaUtils.addEntityInInitCycleHelper(elanMuskeNum, initLoopManiaEntityList, ElanMuske.class, mapList, orderedPath, initRandom);
            for (Building building : buildingEntities) {
                if (building instanceof ZombiePitBuilding) {
                    if (building.getCycleComplete() == 0) {
                        LoopManiaUtils.generateVampireOrZombie(building, worldPositionType, mapList, initLoopManiaEntityList, SlugZombie.class);
                    } else {//if (building.getCycleComplete() % 2 == 0) {
                        LoopManiaUtils.generateVampireOrZombie(building, worldPositionType, mapList, initLoopManiaEntityList, Zombie.class);
                    }
                } else if (building instanceof VampireCastleBuilding) {
                    if (building.getCycleComplete() == 0) {
                        LoopManiaUtils.generateVampireOrZombie(building, worldPositionType, mapList, initLoopManiaEntityList, Vampire.class);
                    }
                }
            }
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {
        }
        for (Entity entity : initLoopManiaEntityList) {
            if (entity instanceof Gold) {
                goldOnGround.add((Items) entity);
            } else if (entity instanceof HealthPotion) {
                healthPotionsOnGround.add((Items) entity);
            } else if (entity instanceof BasicEnemy && !enemies.contains(entity)) {
                enemies.add((BasicEnemy) entity);
            }
        }

        updateWorldPositionType();

        return initLoopManiaEntityList;
    }


    /**
     * Update world position type.
     */
    public void updateWorldPositionType() {
        worldPositionType = LoopManiaUtils.updateWorldPosition(
                height,
                width,
                enemies,
                buildingEntities,
                character,
                goldOnGround,
                healthPotionsOnGround,
                orderedPath);
    }

    /**
     * Init random by cycle complete list.
     *
     * @return the list
     */
    public List<Entity> initRandomByCycleComplete() {
        int x = character.getX();
        int y = character.getY();
        if (x == heroCastleBuilding.getX() && y == heroCastleBuilding.getY()) {
            List<Entity> entities = initNewCycleComplete();
            for (Building buildingEntity : buildingEntities) {
                int tempCycleComplete = buildingEntity.getCycleComplete();
                if ((buildingEntity instanceof VampireCastleBuilding || buildingEntity instanceof ZombiePitBuilding) && tempCycleComplete > 0) {
                    buildingEntity.setCycleComplete(tempCycleComplete - 1);
                }
            }
            return entities;
        }
        return new ArrayList<>();
    }

    /**
     * Check if placed to correct slot boolean.
     *
     * @param unequippedX the unequipped x
     * @param unequippedY the unequipped y
     * @param slotX       the slot x
     * @param slotY       the slot y
     * @return the boolean
     */
    public boolean checkIfPlacedToCorrectSlot(int unequippedX, int unequippedY, int slotX, int slotY) {
        Items findItem = null;
        for (Items unequippedInventoryItem : unequippedInventoryItems) {
            if (unequippedInventoryItem.getX() == unequippedX && unequippedInventoryItem.getY() == unequippedY) {
                findItem = unequippedInventoryItem;
            }
        }
        if (findItem == null) {
            return false;
        }
        boolean canPlaceHere = false;
        if (slotX == 2 && slotY == 1) {
            if (findItem instanceof Shield ||
                    (findItem instanceof Rare && ((Rare) findItem).isShield())) {
                canPlaceHere = true;
            }
        } else if (slotX == 0 && slotY == 1) {
            if (Weapon.class.isAssignableFrom(findItem.getClass()) ||
                    (findItem instanceof Rare && ((Rare) findItem).isWeapon())) {
                canPlaceHere = true;
            }
        } else if (slotX == 1 && slotY == 0) {
            if (findItem instanceof Helmet) {
                canPlaceHere = true;
            }
        } else if (slotX == 1 && slotY == 1) {
            if (findItem instanceof Armour) {
                canPlaceHere = true;
            }
        }
        return canPlaceHere;
    }

    /**
     * Unequipped to equipped.
     *
     * @param x     the x
     * @param y     the y
     * @param slotX the slot x
     * @param slotY the slot y
     */
    public void unequippedToEquipped(int x, int y, int slotX, int slotY) {
        Items item = (Items) getUnequippedInventoryItemEntityByCoordinates(x, y);
        if (item == null) {
            return;
        }
        if (slotX == 0 && slotY == 1) {
            if (Weapon.class.isAssignableFrom(item.getClass()) ||
                    (item instanceof Rare && ((Rare) item).isWeapon())) {
                if (equippedWeapon != null) {
                    equippedItems.remove(equippedWeapon);
                    equippedWeapon.destroy();
                }
                equippedWeapon = item;
                equippedItems.add(equippedWeapon);
            }
        } else if (slotX == 1 && slotY == 1) {
            if (item instanceof Armour) {
                if (equippedArmor != null) {
                    equippedItems.remove(equippedArmor);
                    equippedArmor.destroy();
                }
                equippedArmor = item;
                equippedItems.add(equippedArmor);
            }
        } else if (slotX == 1 && slotY == 0) {
            if (item instanceof Helmet) {
                if (equippedHelmet != null) {
                    equippedItems.remove(equippedHelmet);
                    equippedHelmet.destroy();
                }
                equippedHelmet = item;
                equippedItems.add(equippedHelmet);
            }
        } else if (slotX == 2 && slotY == 1) {
            if (item instanceof Shield ||
                    (item instanceof Rare && ((Rare) item).isShield())) {
                if (equippedShield != null) {
                    equippedItems.remove(equippedShield);
                    equippedShield.destroy();
                }
                equippedShield = item;
                equippedItems.add(equippedShield);
            }
        }
    }

    /**
     * Is at hero castle simple boolean property.
     *
     * @return the simple boolean property about if is at hero
     */
    public SimpleBooleanProperty isAtHeroCastle() {
        return heroCastleBuilding.isAtHeroCastle();
    }

    //save games

    /**
     * Save game mode string.
     *
     * @return the string
     */
    public String saveGameMode() {
        return heroCastleBuilding.getMode();
    }

    /**
     * Save buildings list.
     *
     * @return the list
     */
    public List<HashMap<String, String>> saveBuildings() {
        List<HashMap<String, String>> returnBuildings = new ArrayList<>();

        for (Building building : buildingEntities) {
            HashMap<String, String> saveBuildings = new HashMap<>();
            saveBuildings.put("x", String.valueOf(building.getX()));
            saveBuildings.put("y", String.valueOf(building.getY()));
            saveBuildings.put("type", building.getClass().getSimpleName());
            if (building instanceof VampireCastleBuilding || building instanceof ZombiePitBuilding) {
                String buildingName = String.valueOf(building.getCycleComplete());
                saveBuildings.put("cycleComplete", buildingName);
            }
            returnBuildings.add(saveBuildings);
        }
        return returnBuildings;
    }

    /**
     * Save equipped items list.
     *
     * @return the list
     */
    public List<HashMap<String, String>> saveEquippedItems() {
        ArrayList<HashMap<String, String>> returnEquippedItems = new ArrayList<>();
        if (equippedArmor != null) {
            addSaveEquippedItems(returnEquippedItems, equippedArmor, 1, 1);
        }
        if (equippedHelmet != null) {
            addSaveEquippedItems(returnEquippedItems, equippedHelmet, 1, 0);
        }
        if (equippedShield != null) {
            addSaveEquippedItems(returnEquippedItems, equippedShield, 2, 1);
        }
        if (equippedWeapon != null) {
            addSaveEquippedItems(returnEquippedItems, equippedWeapon, 0, 1);
        }
        return returnEquippedItems;
    }

    private void addSaveEquippedItems(ArrayList<HashMap<String, String>> returnEquippedItems, Items item, int x, int y) {
        HashMap<String, String> saveItems = new HashMap<>();
        saveItems.put("x", String.valueOf(x));
        saveItems.put("y", String.valueOf(y));
        saveItems.put("type", item.getClass().getSimpleName());
        saveItems.put("equipped", "true");
        returnEquippedItems.add(saveItems);
    }

    /**
     * Save un equipped items list.
     *
     * @return the list
     */
    public List<HashMap<String, String>> saveUnEquippedItems() {
        ArrayList<HashMap<String, String>> returnUnEquippedItems = new ArrayList<>();
        for (Items items : unequippedInventoryItems) {
            HashMap<String, String> saveItems = new HashMap<>();
            saveItems.put("x", String.valueOf(items.getX()));
            saveItems.put("y", String.valueOf(items.getY()));
            saveItems.put("type", items.getClass().getSimpleName());
            saveItems.put("equipped", "false");
            returnUnEquippedItems.add(saveItems);
        }
        return returnUnEquippedItems;
    }

    /**
     * Save enemies list.
     *
     * @return the list
     */
    public List<HashMap<String, String>> saveEnemies() {

        return saveEntities(enemies);
    }


    /**
     * Save entities list.
     *
     * @param entities the entities
     * @return the list
     */
    public List<HashMap<String, String>> saveEntities(List<?> entities) {
        ArrayList<HashMap<String, String>> returnEntities = new ArrayList<>();
        for (Object entity : entities) {
            HashMap<String, String> saveEntity = new HashMap<>();
            saveEntity.put("x", String.valueOf(((Entity) entity).getX()));
            saveEntity.put("y", String.valueOf(((Entity) entity).getY()));
            saveEntity.put("type", entity.getClass().getSimpleName());
            returnEntities.add(saveEntity);
        }
        return returnEntities;
    }

    /**
     * Save cards list.
     *
     * @return the list
     */
    public List<HashMap<String, String>> saveCards() {
        return saveEntities(cardEntities);
    }

    //load games

    /**
     * Load game mode.
     *
     * @param mode     the mode
     * @param elanMode the elan mode
     */
//load game mode
    public void loadGameMode(String mode, String elanMode) {
        setMode(mode);
        setElanMode(elanMode);
    }

    /**
     * Initial load.
     *
     * @param building the building
     */
//load buildings on the map
    public void initialLoad(Building building) {
        buildingEntities.add(building);
        if (building instanceof HeroCastleBuilding) {
            setHeroCastleBuilding((HeroCastleBuilding) building);
        }
        worldPositionType[building.getX()][building.getY()] = WORLD_POSITION_TYPE.getImageName(building.getClass());
    }

    /**
     * Initial load.
     *
     * @param enemy the enemy
     */
//load enemies
    public void initialLoad(BasicEnemy enemy) {
        enemies.add(enemy);
        worldPositionType[enemy.getX()][enemy.getY()] = WORLD_POSITION_TYPE.getImageName(enemy.getClass());
    }

    /**
     * Initial load.
     *
     * @param card the card
     */
//load cards
    public void initialLoad(Card card) {
        cardEntities.add(card);
        worldPositionType[card.getX()][card.getY()] = WORLD_POSITION_TYPE.getImageName(card.getClass());
    }

    /**
     * Initial load.
     *
     * @param entity the entity
     */
//load InventoryItems
    public void initialLoad(Items entity) {
        unequippedInventoryItems.add(entity);
        worldPositionType[entity.getX()][entity.getY()] = WORLD_POSITION_TYPE.getImageName(entity.getClass());
    }

    /**
     * Load equipped items.
     *
     * @param item the item
     */
//load equipped items
    public void loadEquippedItems(Items item) {
        if (item instanceof Weapon) {
            equippedWeapon = item;
            equippedItems.add(equippedWeapon);
        } else if (item instanceof Armour) {
            equippedArmor = item;
            equippedItems.add(equippedArmor);
        } else if (item instanceof Helmet) {
            equippedHelmet = item;
            equippedItems.add(equippedHelmet);
       }  else {
            //item instanceof Shield
            equippedShield = item;
            equippedItems.add(equippedShield);
        }
        worldPositionType[item.getX()][item.getY()] = WORLD_POSITION_TYPE.getImageName(item.getClass());
    }

    /**
     * Load character info.
     *
     * @param info the info
     */
    public void loadCharacterInfo(Map<String, Integer> info) {
        character.setGolds(info.get("gold_number"));
        equippedHealthPotionsNum.set(info.get("potion_number"));
        for (int i = 0; i < equippedHealthPotionsNum.get(); i++) {
            equippedHealthPotions.add(new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        }
        character.setExperience(info.get("experience"));
        character.setHealth(info.get("health"));
        character.setCycleComplete(info.get("cycle_complete"));
        setCycleComplete(info.get("cycle_complete"));
        alliedSoldierNum.set(info.get("allies_number"));
        for (int i = 0; i < alliedSoldierNum.get(); i++) {
            alliedSoldiers.add(new AlliedSoldier(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        }
    }

    /**
     * Save initial path info.
     *
     * @param initialPathInfo the initial path info
     */
    public void saveInitialPathInfo(JSONObject initialPathInfo) {
        this.initialPathInfo = initialPathInfo;
    }

    /**
     * Gets initial path info.
     *
     * @return the initial path info
     */
    public JSONObject getInitialPathInfo() {
        return initialPathInfo;
    }

    /**
     * Sets elan mode.
     *
     * @param elanMode the elan mode
     */
    public void setElanMode(String elanMode) {
        this.elanMode = elanMode;
    }

    /**
     * Sets cycle complete.
     *
     * @param cycleComplete the cycle complete
     */
    public void setCycleComplete(int cycleComplete) {
        this.cycleComplete = cycleComplete;
    }

    /**
     * Gets ordered path.
     *
     * @return the ordered path
     */
    public List<Pair<Integer, Integer>> getOrderedPath() {
        return orderedPath;
    }

    /**
     * Gets elan mode.
     *
     * @return the elan mode
     */
    public String getElanMode() {
        return elanMode;
    }
}
