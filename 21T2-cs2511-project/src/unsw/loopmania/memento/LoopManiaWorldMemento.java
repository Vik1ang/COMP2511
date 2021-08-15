package unsw.loopmania.memento;

import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import unsw.loopmania.LoopManiaWorld;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * The type Loop mania world memento.
 */
public class LoopManiaWorldMemento {
    private final String savingTime;
    private final JSONObject worldState;

    /**
     * Instantiates a new Loop mania world memento.
     *
     * @param world the world
     */
    public LoopManiaWorldMemento(LoopManiaWorld world){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        this.savingTime = sdf.format(date);
        worldState = getSaveData(world);
    }

    /**
     * Gets world state.
     *
     * @return the world state
     */
    public JSONObject getWorldState() {
        return worldState;
    }

    /**
     * Gets saving time.
     *
     * @return the saving time
     */
    public String getSavingTime() {
        return savingTime;
    }

    /**
     * Gets save data.
     *
     * @param world the world
     * @return the save data
     */
    public JSONObject getSaveData(LoopManiaWorld world) {
        JSONObject json = new JSONObject();

        // save width and height
        json.put("width", world.getWidth());
        json.put("height", world.getHeight());

        // save path information
        json.put("path", world.getInitialPathInfo());

        // save goals
        List<Pair<String, Integer>> goals = world.getGoals();
        JSONArray jsonGoals = new JSONArray();
        for (Pair<String, Integer> oneGoal : goals) {
            JSONObject oneJsonGoal = new JSONObject();
            oneJsonGoal.put("goal", oneGoal.getValue0());
            oneJsonGoal.put("quantity", oneGoal.getValue1());
            jsonGoals.put(oneJsonGoal);
        }
        json.put("goal-condition", jsonGoals);

        // save game_mode
        json.put("game_mode", world.saveGameMode());

        //save rare item
        List<String> rareItems = world.getRareList();
        JSONArray jsonRareItems = new JSONArray();
        for (String rareItem: rareItems) {
            jsonRareItems.put(rareItem);
        }
        json.put("rare_items", jsonRareItems);

        // save potion number, gold number, experience, health percentage
        JSONObject jsonCharacterInfo = new JSONObject();
        jsonCharacterInfo.put("potion_number", world.getEquippedHealthPotionsNum().get());
        jsonCharacterInfo.put("gold_number", world.getCharacterGold().get());
        jsonCharacterInfo.put("experience", world.getCharacterExp().get());
        jsonCharacterInfo.put("health", world.getCharacterHealth());
        jsonCharacterInfo.put("allies_number", world.getAlliedSoldierNum().get());
        jsonCharacterInfo.put("cycle_complete", world.getCycleComplete());
        json.put("character_info", jsonCharacterInfo);

        JSONArray jsonEntities = new JSONArray();
        // save building entities
        List<HashMap<String,String>> buildings = world.saveBuildings();
        saveEntities(jsonEntities, buildings);

        // save card entities
        List<HashMap<String,String>> cards = world.saveCards();
        saveEntities(jsonEntities, cards);

        // save inventory entities
        List<HashMap<String,String>> unEquippedItems = world.saveUnEquippedItems();
        saveEntities(jsonEntities, unEquippedItems);

        // save entities
        List<HashMap<String,String>> equippedItems = world.saveEquippedItems();
        saveEntities(jsonEntities, equippedItems);

        // save enemies
        List<HashMap<String,String>> enemies = world.saveEnemies();
        saveEntities(jsonEntities, enemies);

        json.put("entities", jsonEntities);
        return json;
    }

    /**
     * Save.
     *
     * @param fileName the file name
     */
    public void save(String fileName) {
        File jsonFile;
        FileOutputStream fileOutputStream;
        try {
            jsonFile = new File("src/../worlds/savings/" + fileName + ".json");
            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
            }
            // get the out put stream
            fileOutputStream = new FileOutputStream(jsonFile);

            // write the json to the file
            fileOutputStream.write(worldState.toString().getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveEntities(JSONArray jsonEntities, List<HashMap<String, String>> entities) {
        for (HashMap<String,String> entity: entities) {
            JSONObject jsonEntity = new JSONObject();
            jsonEntity.put("x", Integer.parseInt(entity.get("x")));
            jsonEntity.put("y", Integer.parseInt(entity.get("y")));
            jsonEntity.put("type", entity.get("type"));
            if (entity.containsKey("cycleComplete"))
                jsonEntity.put("cycleComplete", Integer.parseInt(entity.get("cycleComplete")));
            if (entity.containsKey("equipped"))
                jsonEntity.put("equipped", Boolean.parseBoolean(entity.get("equipped")));
            jsonEntities.put(jsonEntity);
        }
    }
}
