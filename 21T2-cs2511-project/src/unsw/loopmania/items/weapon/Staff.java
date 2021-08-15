package unsw.loopmania.items.weapon;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.basicEnemies.BasicEnemy;

import java.util.Random;

/**
 * The type Staff.
 */
public class Staff extends Weapon{

    private static final int SELLING_PRICE = 2;
    private static final int ATTACK = 2;
    private static final int TRANCE_SEED = 0;

    private Random randomTrance;

    /**
     * Instantiates a new Staff.
     *
     * @param x the x
     * @param y the y
     */
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setAttack(ATTACK);
        setSellingPrice(SELLING_PRICE);
        setType(new SimpleStringProperty("Staff"));
        randomTrance = new Random(TRANCE_SEED);
    }

    @Override
    public int getAttackToEnemy(BasicEnemy enemy) {
        // staff seed 0: 60 48 29 47 15 53 91 61  < 20
        int randNumber = randomTrance.nextInt(100);
        if (randNumber < 20) trance(enemy);
        return getAttack();
    }

    /**
     * Trance the given enemy.
     *
     * @param enemy the BasicEnemy
     */
    private void trance(BasicEnemy enemy) {
        enemy.setTrance(true);
    }
}
