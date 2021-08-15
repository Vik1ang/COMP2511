package unsw.loopmania.utils;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import unsw.loopmania.Entity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.RareItemsFactory;
import unsw.loopmania.WORLD_POSITION_TYPE;
import unsw.loopmania.basicEnemies.BasicEnemy;
import unsw.loopmania.basicEnemies.ElanMuske;
import unsw.loopmania.basicEnemies.SlugZombie;
import unsw.loopmania.basicEnemies.Vampire;
import unsw.loopmania.basicEnemies.Zombie;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.VampireCastleBuilding;
import unsw.loopmania.buildings.ZombiePitBuilding;
import unsw.loopmania.card.Card;
import unsw.loopmania.character.Character;
import unsw.loopmania.items.Items;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The type Loop mania utils.
 */
public class LoopManiaUtils {

    /**
     * Update world position world position type [ ] [ ].
     *
     * @param height                the height
     * @param width                 the width
     * @param enemies               the enemies
     * @param buildingEntities      the building entities
     * @param character             the character
     * @param goldOnGround          the gold on ground
     * @param healthPotionsOnGround the health potions on ground
     * @param orderedPath           the ordered path
     * @return the world position type [ ] [ ]
     */
    public static WORLD_POSITION_TYPE[][] updateWorldPosition(
            int height,
            int width,
            List<BasicEnemy> enemies,
            List<Building> buildingEntities,
            Character character,
            List<Items> goldOnGround,
            List<Items> healthPotionsOnGround,
            List<Pair<Integer, Integer>> orderedPath) {
        WORLD_POSITION_TYPE[][] worldPosition = new WORLD_POSITION_TYPE[width + 1][height + 1];

        for (BasicEnemy enemy : enemies) {
            int x = enemy.getX();
            int y = enemy.getY();
            switch (enemy.getClass().getSimpleName()) {
                case "Slug":
                    worldPosition[x][y] = WORLD_POSITION_TYPE.SLUG;
                    break;
                case "Zombie":
                    worldPosition[x][y] = WORLD_POSITION_TYPE.ZOMBIE;
                    break;
                case "Vampire":
                    worldPosition[x][y] = WORLD_POSITION_TYPE.VAMPIRE;
                default:
                    break;
            }
        }

        for (Building building : buildingEntities) {
            int x = building.getX();
            int y = building.getY();
            switch (building.getClass().getSimpleName()) {
                case "BarracksBuilding":
                    worldPosition[x][y] = WORLD_POSITION_TYPE.BARRACKS_BUILDING;
                    break;
                case "CampFireBuilding":
                    worldPosition[x][y] = WORLD_POSITION_TYPE.CAMPFIRE_BUILDING;
                    break;
                case "TowerBuilding":
                    worldPosition[x][y] = WORLD_POSITION_TYPE.TOWER_BUILDING;
                    break;
                case "TrapBuilding":
                    worldPosition[x][y] = WORLD_POSITION_TYPE.TRAP_BUILDING;
                    break;
                case "VampireCastleBuilding":
                    worldPosition[x][y] = WORLD_POSITION_TYPE.VAMPIRE_CASTLE_BUILDING;
                    break;
                case "VillageBuilding":
                    worldPosition[x][y] = WORLD_POSITION_TYPE.VILLAGE_BUILDING;
                    break;
                case "ZombiePitBuilding":
                    worldPosition[x][y] = WORLD_POSITION_TYPE.ZOMBIE_PIT_BUILDING;
                    break;
                case "HeroCastleBuilding":
                    worldPosition[x][y] = WORLD_POSITION_TYPE.HERO_CASTLE_BUILDING;
                    break;
                default:
                    break;
            }
        }

        if (character != null) {
            worldPosition[character.getX()][character.getY()] = WORLD_POSITION_TYPE.CHARACTER;
        }

        for (Items items : goldOnGround) {
            int x = items.getX();
            int y = items.getY();
            worldPosition[x][y] = WORLD_POSITION_TYPE.GOLD;
        }

        for (Items items : healthPotionsOnGround) {
            int x = items.getX();
            int y = items.getY();
            worldPosition[x][y] = WORLD_POSITION_TYPE.HEALTH_POTION;
        }

        for (Pair<Integer, Integer> pair : orderedPath) {
            Integer x = pair.getValue0();
            Integer y = pair.getValue1();
            if (worldPosition[x][y] == null) {
                worldPosition[x][y] = WORLD_POSITION_TYPE.PATH;
            }
        }

        for (int i = 0; i < worldPosition.length; i++) {
            for (int j = 0; j < worldPosition[i].length; j++) {
                if (worldPosition[i][j] == null) {
                    worldPosition[i][j] = WORLD_POSITION_TYPE.GRASS;
                }
            }
        }

        return worldPosition;
    }


    /**
     * Gets index of ordered path.
     *
     * @param mapList the map list
     * @param x       the x
     * @param y       the y
     * @return the index of ordered path
     */
    public static int getIndexOfOrderedPath(List<Pair<Integer, Integer>> mapList, int x, int y) {
        for (int i = 0; i < mapList.size(); i++) {
            Pair<Integer, Integer> pair = mapList.get(i);
            if (pair.getValue0().equals(x) && pair.getValue1().equals(y)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Generate vampire or zombie.
     *
     * @param building                the building
     * @param worldPositionType       the world position type
     * @param mapList                 the map list
     * @param initLoopManiaEntityList the init loop mania entity list
     * @param clazz                   the clazz
     */
    public static void generateVampireOrZombie(Building building, WORLD_POSITION_TYPE[][] worldPositionType, List<Pair<Integer, Integer>> mapList, List<Entity> initLoopManiaEntityList, Class<?> clazz) {
            int x = building.getX();
            int y = building.getY();
            for (int i = -1; i < 2; i++) {
                boolean flag = false;
                for (int j = -1; j < 2; j++) {
                    WORLD_POSITION_TYPE world_position_type = null;
                    try {
                        world_position_type = worldPositionType[x + i][y + j];
                    } catch (Exception e) {
                        continue;
                    }
                    if (world_position_type == null) {
                        continue;
                    }
                    if (world_position_type.equals(WORLD_POSITION_TYPE.PATH)) {
                        int indexOfOrderedPath = LoopManiaUtils.getIndexOfOrderedPath(mapList, x + i, y + 1);
                        if (indexOfOrderedPath != -1) {
                            BasicEnemy enemy = null;
                            if (clazz.equals(Zombie.class)) {
                                enemy = new Zombie(new PathPosition(indexOfOrderedPath, mapList));
                            } else if (clazz.equals(Vampire.class)) {
                                enemy = new Vampire(new PathPosition(indexOfOrderedPath, mapList));
                            } else if (clazz.equals(SlugZombie.class)) {
                                enemy = new SlugZombie(new PathPosition(indexOfOrderedPath, mapList));
                            }
                            if (enemy != null) {
                                initLoopManiaEntityList.add(enemy);
                                mapList.remove(indexOfOrderedPath);
                                if (building instanceof ZombiePitBuilding) {
                                    if (building.getCycleComplete() == 0) {
                                        building.setCycleComplete(2);
                                    }
                                } else if (building instanceof VampireCastleBuilding) {
                                    building.setCycleComplete(5);
                                }
                                flag = true;
                                break;
                            }
                        }
                    }
                }
                if (flag) {
                    break;
                }
            }
    }

    /**
     * Add entity in init cycle helper.
     *
     * @param size                    the size
     * @param initLoopManiaEntityList the init loop mania entity list
     * @param clazz                   the clazz
     * @param mapList                 the map list
     * @param orderedPath             the ordered path
     * @param initRandom              the init random
     * @throws NoSuchMethodException     the no such method exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InstantiationException    the instantiation exception
     * @throws IllegalAccessException    the illegal access exception
     */
    public static void addEntityInInitCycleHelper(int size, List<Entity> initLoopManiaEntityList, Class<?> clazz, List<Pair<Integer, Integer>> mapList, List<Pair<Integer, Integer>> orderedPath, Random initRandom) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < size; i++) {
            Pair<Integer, Integer> axies = null;
            boolean flag = false;
            int index = 0;
            while (!flag) {
                axies = RandomUtils.generateRandomValid(orderedPath, initRandom);
                for (int j = 0; j < mapList.size(); j++) {
                    Pair<Integer, Integer> tempList = mapList.get(j);
                    if (tempList.getValue0().equals(axies.getValue0()) && tempList.getValue1().equals(axies.getValue1())) {
                        mapList.remove(j);
                        index = j;
                        flag = true;
                        break;
                    }
                }
            }
            Object[] params = {new SimpleIntegerProperty(axies.getValue0()), new SimpleIntegerProperty(axies.getValue1())};
            try {
                initLoopManiaEntityList.add((Entity) clazz.getDeclaredConstructor(SimpleIntegerProperty.class, SimpleIntegerProperty.class).newInstance(params));
            } catch (Exception e) {
                switch (clazz.getSimpleName()) {
                    case "Slug":
                    case "Doggie":
                        initLoopManiaEntityList.add((Entity) clazz.getDeclaredConstructor(PathPosition.class).newInstance(new PathPosition(index, orderedPath)));
                        break;
                    case "ElanMuske":
                        initLoopManiaEntityList.add(ElanMuske.getInstance(new PathPosition(index, orderedPath)));
                        break;
                }
            }
        }
    }

    public static Card addCardHelper(String type, List<Card> cardEntities) {
        String cardClassPrefix = "unsw.loopmania.card.";
        String cardClassName = cardClassPrefix + type;
        try {
            Class<?> cardClass = Class.forName(cardClassName);
            // new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0)
            Object[] params = {new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0)};
            Card card = (Card) cardClass.getDeclaredConstructor(SimpleIntegerProperty.class, SimpleIntegerProperty.class).newInstance(params);
            cardEntities.add(card);
            return card;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Items addUnequippedItemsHelper(String type, Pair<Integer, Integer> firstAvailableSlot, List<Items> unequippedInventoryItems,
                                                RareItemsFactory rareItemsFactory) {
        List<String> prefixList = new ArrayList<>();
        prefixList.add("unsw.loopmania.items.");
        prefixList.add("unsw.loopmania.items.weapon.");
        prefixList.add("unsw.loopmania.items.rare.");
        prefixList.add("unsw.loopmania.items.defence.");
        Items item = null;
        for (int i = 0; i < 4; i++) {
            String itemsClassName = prefixList.get(i) + type;
            // new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()))
            try {
                if (type.equals("Rare")) {
                    item = rareItemsFactory.getInstance(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
                } else {
                    Class<?> itemsClass = Class.forName(itemsClassName);
                    Object[] params = {new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1())};
                    if (i == prefixList.size() - 1) {
                        params = new Object[]{new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1())};
                    }
                    item = (Items) itemsClass.getDeclaredConstructor(SimpleIntegerProperty.class, SimpleIntegerProperty.class).newInstance(params);

                }
                unequippedInventoryItems.add(item);
                break;
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {
            }
        }
        if (item == null) System.err.println("null pointer AAAAAA");
        return item;
    }

    public static Building cardToBuildingHelper(Card card, List<Building> buildingEntities, WORLD_POSITION_TYPE[][] worldPositionType, int buildingNodeX, int buildingNodeY, int cardNodeX, int cardNodeY) {
        String cardClassName = card.getClass().getSimpleName();
        Building building = card.getBuilding(buildingNodeX, buildingNodeY);
        buildingEntities.add(building);
        worldPositionType[buildingNodeX][buildingNodeY] = WORLD_POSITION_TYPE.getImageName(building.getClass());
        return building;
    }
}
