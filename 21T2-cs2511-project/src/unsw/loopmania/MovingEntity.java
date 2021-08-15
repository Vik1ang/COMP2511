package unsw.loopmania;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The moving entity
 */
public abstract class MovingEntity extends Entity {

    /**
     * object holding position in the path
     */
    private PathPosition position;
    private SimpleIntegerProperty health = new SimpleIntegerProperty(0);

    /**
     * Create a moving entity which moves up and down the path in position
     * @param position represents the current position in the path
     */
    public MovingEntity(PathPosition position) {
        super();
        this.position = position;
        health.set(0);
    }


    /**
     * move clockwise through the path
     */
    public void moveDownPath() {
        position.moveDownPath();
    }

    /**
     * move anticlockwise through the path
     */
    public void moveUpPath() {
        position.moveUpPath();
    }

    public void moveToPath(int pos) {
        position.moveToPath(pos);
    }


    public SimpleIntegerProperty x() {
        return position.getX();
    }

    public SimpleIntegerProperty y() {
        return position.getY();
    }

    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public PathPosition getPosition() {
        return position;
    }

    public int getHealth() {
        return health.getValue();
    }

    public void setHealth(int healthValue) {
        health.set(healthValue);
    }
/*
    public void increaseHealth(int healthToIncrease) {
        int healthValue = health.get() + healthToIncrease;
        health.set(healthValue);
    }

    public void decreaseHealth(int healthToDecrease) {
        int healthValue = health.get() + healthToDecrease;
        health.set(healthValue);
    }
*/
    public SimpleIntegerProperty health() {
        return health;
    }
}
