package unsw.loopmania;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import unsw.loopmania.card.*;
import unsw.loopmania.buildings.*;

import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.buildings.HeroCastleBuilding;
import unsw.loopmania.character.Character;
import unsw.loopmania.memento.LoopManiaWorldMemento;

import java.util.HashMap;
import java.util.List;

/**
 * Loads a world from a .json file.
 * <p>
 * By extending this class, a subclass can hook into entity creation.
 * This is useful for creating UI elements with corresponding entities.
 * <p>
 * this class is used to load the world.
 * it loads non-spawning entities from the configuration files.
 * spawning of enemies/cards must be handled by the controller.
 */
public abstract class LoopManiaWorldLoader {
    private JSONObject json;
    private JSONArray unloadEntities;

    /**
     * Instantiates a new Loop mania world loader.
     *
     * @param filename the filename
     * @throws FileNotFoundException the file not found exception
     */
    public LoopManiaWorldLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("worlds/" + filename)));
    }

    /**
     * Instantiates a new Loop mania world loader.
     *
     * @param loopManiaWorldMemento the loop mania world memento
     */
    public LoopManiaWorldLoader(LoopManiaWorldMemento loopManiaWorldMemento){
        this.json = loopManiaWorldMemento.getWorldState();
    }

    /**
     * Parses the JSON to create a world.
     *
     * @return the loop mania world
     */
    public LoopManiaWorld load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        // path variable is collection of coordinates with directions of path taken...
        List<Pair<Integer, Integer>> orderedPath = loadPathTiles(json.getJSONObject("path"), width, height);

        LoopManiaWorld world = new LoopManiaWorld(width, height, orderedPath);

        // save path info json object
        world.saveInitialPathInfo(json.getJSONObject("path"));

        JSONArray jsonEntities = json.getJSONArray("entities");

        // load non-path entities later so that they're shown on-top
        for (int i = jsonEntities.length() - 1; i >= 0; i--) {
            if (((String)jsonEntities.getJSONObject(i).get("type")).contains("Building")) {
                loadEntity(world, jsonEntities.getJSONObject(i), orderedPath);
                jsonEntities.remove(i);
            }
        }

        unloadEntities = jsonEntities;

        // load possible goals
        JSONArray jsonGoals = json.getJSONArray("goal-condition");
        List<Pair<String, Integer>> goals = new ArrayList<>();
        for (int i = 0; i < jsonGoals.length(); i++) {
            loadGoal(world, jsonGoals.getJSONObject(i), goals);
        }
        world.setGoals(goals);

        // load possible rare items
        JSONArray jsonRareItems = json.getJSONArray("rare_items");
        List<String> rareItems = new ArrayList<>();
        for (int i = 0; i < jsonRareItems.length(); i++) {
            rareItems.add(jsonRareItems.get(i).toString());
        }
        world.setAvailableRareItems(rareItems);
        return world;
    }

    private void loadGoal(LoopManiaWorld world, JSONObject currentJson, List<Pair<String, Integer>> goals) {
        String type = currentJson.getString("goal");
        int quantity = currentJson.getInt("quantity");
        goals.add(new Pair<>(type,quantity));
    }


    /**
     * load an entity into the world
     * @param world backend world object
     * @param currentJson a JSON object to parse (different from the )
     * @param orderedPath list of pairs of x, y cell coordinates representing game path
     */
    private void loadEntity(LoopManiaWorld world, JSONObject currentJson, List<Pair<Integer, Integer>> orderedPath) {
        String type = currentJson.getString("type");
        int x = currentJson.getInt("x");
        int y = currentJson.getInt("y");
        int indexInPath = orderedPath.indexOf(new Pair<>(x, y));
        assert indexInPath != -1;

        Entity entity = null;
        switch (type) {
        case "HeroCastleBuilding":
            HeroCastleBuilding heroCastleBuilding = new HeroCastleBuilding(new SimpleIntegerProperty(x) , new SimpleIntegerProperty(y));
            world.setHeroCastleBuilding(heroCastleBuilding);

            loadHelper(heroCastleBuilding);

            world.addEntity(heroCastleBuilding);
            Character character = new Character(new PathPosition(indexInPath, orderedPath));
            world.setCharacter(character);
            onLoad(character);
            entity = character;
            if (json.has("character_info")) {
                JSONObject jsonCharacterInformation = json.getJSONObject("character_info");
                HashMap<String,Integer> characterInformation = new HashMap<>();
                characterInformation.put("gold_number", (Integer)jsonCharacterInformation.get("gold_number"));
                characterInformation.put("potion_number", (Integer)jsonCharacterInformation.get("potion_number"));
                characterInformation.put("health", (Integer)jsonCharacterInformation.get("health"));
                characterInformation.put("experience", (Integer)jsonCharacterInformation.get("experience"));
                characterInformation.put("allies_number", (Integer)jsonCharacterInformation.get("allies_number"));
                characterInformation.put("cycle_complete", (Integer)jsonCharacterInformation.get("cycle_complete"));
                world.loadCharacterInfo(characterInformation);
            }
            break;
        case "VampireCastleBuilding": case "ZombiePitBuilding": case "VillageBuilding": case "CampFireBuilding":
        case "BarracksBuilding": case "TowerBuilding": case "TrapBuilding":
            Building newBuilding;
            if (type.compareTo("VampireCastleBuilding") == 0)
                newBuilding = new VampireCastleBuilding(new SimpleIntegerProperty(x),
                        new SimpleIntegerProperty(y), currentJson.getInt("cycleComplete"));
            else if (type.compareTo("ZombiePitBuilding") == 0)
                newBuilding = new ZombiePitBuilding(new SimpleIntegerProperty(x),
                        new SimpleIntegerProperty(y), currentJson.getInt("cycleComplete"));
            else if (type.compareTo("VillageBuilding") == 0)
                newBuilding = new VillageBuilding(new SimpleIntegerProperty(x),
                        new SimpleIntegerProperty(y));
            else if (type.compareTo("CampFireBuilding") == 0)
                newBuilding = new CampFireBuilding(new SimpleIntegerProperty(x),
                        new SimpleIntegerProperty(y));
            else if (type.compareTo("BarracksBuilding") == 0)
                newBuilding = new BarracksBuilding(new SimpleIntegerProperty(x),
                        new SimpleIntegerProperty(y));
            else if (type.compareTo("TowerBuilding") == 0)
                newBuilding = new TowerBuilding(new SimpleIntegerProperty(x),
                        new SimpleIntegerProperty(y));
            else newBuilding = new TrapBuilding(new SimpleIntegerProperty(x),
                    new SimpleIntegerProperty(y));
                world.initialLoad(newBuilding);
                loadHelper(newBuilding);
                break;
        case "path_tile":
            throw new RuntimeException("path_tile's aren't valid entities, define the path externally.");
        }
        world.addEntity(entity);
    }

    /**
     * load path tiles
     * @param path json data loaded from file containing path information
     * @param width width in number of cells
     * @param height height in number of cells
     * @return list of x, y cell coordinate pairs representing game path
     */
    private List<Pair<Integer, Integer>> loadPathTiles(JSONObject path, int width, int height) {
        if (!path.getString("type").equals("path_tile")) {
            throw new RuntimeException(
                    "Path object requires path_tile type.  No other path types supported at this moment.");
        }
        PathTile starting = new PathTile(new SimpleIntegerProperty(path.getInt("x")), new SimpleIntegerProperty(path.getInt("y")));
        if (starting.getY() >= height || starting.getY() < 0 || starting.getX() >= width || starting.getX() < 0) {
            throw new IllegalArgumentException("Starting point of path is out of bounds");
        }
        // load connected path tiles
        List<PathTile.Direction> connections = new ArrayList<>();
        for (Object dir: path.getJSONArray("path").toList()){
            connections.add(Enum.valueOf(PathTile.Direction.class, dir.toString()));
        }

        if (connections.size() == 0) {
            throw new IllegalArgumentException(
                "This path just consists of a single tile, it needs to consist of multiple to form a loop.");
        }

        // load the first position into the orderedPath
        PathTile.Direction first = connections.get(0);
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(starting.getX(), starting.getY()));

        int x = starting.getX() + first.getXOffset();
        int y = starting.getY() + first.getYOffset();

        // add all coordinates of the path into the orderedPath
        for (int i = 1; i < connections.size(); i++) {
            orderedPath.add(Pair.with(x, y));
            
            if (y >= height || y < 0 || x >= width || x < 0) {
                throw new IllegalArgumentException("Path goes out of bounds at direction index " + (i - 1) + " (" + connections.get(i - 1) + ")");
            }
            
            PathTile.Direction dir = connections.get(i);
            PathTile tile = new PathTile(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            x += dir.getXOffset();
            y += dir.getYOffset();
            if (orderedPath.contains(Pair.with(x, y)) && !(x == starting.getX() && y == starting.getY())) {
                throw new IllegalArgumentException("Path crosses itself at direction index " + i + " (" + dir + ")");
            }
            onLoad(tile, connections.get(i - 1), dir);
        }
        // we should connect back to the starting point
        if (x != starting.getX() || y != starting.getY()) {
            throw new IllegalArgumentException(String.format(
                    "Path must loop back around on itself, this path doesn't finish where it began, it finishes at %d, %d.",
                    x, y));
        }
        onLoad(starting, connections.get(connections.size() - 1), connections.get(0));
        return orderedPath;
    }

    /**
     * On load.
     *
     * @param character the character
     */
    public abstract void onLoad(Character character);

    /**
     * On load.
     *
     * @param pathTile the path tile
     * @param into     the into
     * @param out      the out
     */
    public abstract void onLoad(PathTile pathTile, PathTile.Direction into, PathTile.Direction out);

    /**
     * Load helper.
     *
     * @param building the building
     */
    public abstract void loadHelper(Building building);

    /**
     * Gets unload entities.
     *
     * @return the unload entities
     */
    public JSONArray getUnloadEntities() {
        return unloadEntities;
    }
}
