package unsw.loopmania.utils;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.character.Character;
import unsw.loopmania.items.DoggieCoin;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.Items;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.weapon.Staff;
import unsw.loopmania.items.weapon.Stake;
import unsw.loopmania.items.weapon.Sword;
import unsw.loopmania.mediaplayer.MacPlayer;
import unsw.loopmania.mediaplayer.WindowsPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Package helper.
 */
public class PackageHelper {


    /**
     * handle the case that the cards reaches the maximum limit
     * remove the oldest card from the list
     * return gold, experience & equipment
     *
     * @param character the character
     * @return the string
     */
    public static String cardsReachMax(Character character) {
        character.incrementGolds(1);
        character.incrementExperience(50);
        String equipment = AwardHelper.randomBattleItems(0.5, character.getRandomItem());
        return equipment;
    }

    /**
     * Items reach max.
     *
     * @param character the character
     */
    public static void itemsReachMax(Character character) {
        character.incrementGolds(1);
        character.incrementExperience(100);
    }

    /**
     * Gets items from string.
     *
     * @param itemName the item name
     * @return the items from string
     */
    public static Items getItemsFromString(String itemName) {
        switch(itemName) {
            case PreservedWord.itemSword:
                return new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            case PreservedWord.itemArmour:
                // now we insert the new sword, as we know we have at least made a slot available...
                return new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            case PreservedWord.itemHelmet:
                // now we insert the new sword, as we know we have at least made a slot available...
                return new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            case PreservedWord.itemShield:
                // now we insert the new sword, as we know we have at least made a slot available...
                return new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            case PreservedWord.itemStaff:
                // now we insert the new sword, as we know we have at least made a slot available...
                return new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            case PreservedWord.itemStake:
                // now we insert the new sword, as we know we have at least made a slot available...
                return new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            case PreservedWord.itemPotion:
                return new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            default:
                return null;
        }
    }

    /**
     * Gets files.
     *
     * @param path the path
     * @return the files
     */
    public static ArrayList<String> getFiles(String path) {
        ArrayList<String> files = new ArrayList<>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
            }
        }
        return files;
    }

    public static void processFilesToName(List<String> names, List<String> files) {
        for (String file: files) {
            String fileName;
            if (file.lastIndexOf('\\') != -1)
                fileName = new WindowsPlayer().mediaPlayer(file);
            else
                fileName = new MacPlayer().mediaPlayer(file);
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
            names.add(fileName);
        }
    }

}
