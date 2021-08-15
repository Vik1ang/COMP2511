package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.character.Character;

/**
 * The type Building.
 */
public abstract class Building extends StaticEntity {

    private String type;

    private int cycleComplete;


    private HelpStrategy helpStrategy;

    /**
     * Instantiates a new Building.
     *
     * @param x the x
     * @param y the y
     */
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    public HelpStrategy getHelpStrategy() {
        return helpStrategy;
    }

    public void setHelpStrategy(HelpStrategy helpStrategy) {
        this.helpStrategy = helpStrategy;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
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
     * Sets cycle complete.
     *
     * @param cycleComplete the cycle complete
     */
    public void setCycleComplete(int cycleComplete) {
        this.cycleComplete = cycleComplete;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Can give help boolean.
     *
     * @param entity the MovingEntity
     * @return the boolean
     */
    public boolean canGiveHelp(MovingEntity entity) {
        return helpStrategy.canGiveHelp(this, entity);
    }

}
