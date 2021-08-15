package unsw.loopmania;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A backend entity in the world.
 */
public abstract class Entity {
    /**
     * shouldExist field holds whether the entity should exist as a node within the GUI.
     * using a BooleanProperty here allows us to attach JavaFX ChangeListeners, so that when this BooleanProperty switches to False,
     * a teardown function for the corresponding Node is triggered.
     * This is an example of the Observer pattern!
     * 
     * Note for some entitites this field is redundant e.g. for character, since character is never removed
     * However we have control externally over which ChangeListeners are added (should be done from Controller)
     * 
     * A nice feature of ChangeListeners attached in another class is that the variable scope of the ChangeListener matches the method it was declared in.
     * For example, when setting the ChangeListener for shouldExist for enemies from within the controller, we are able to apply a teardown function which can remove the corresponding JavaFX node from its GridPane
     * which is within the scope of the method defining the ChangeListener.
     * This is why we don't have to track which JavaFX node corresponds to which backend entity explicitly! (for example, in a Hashmap)
     */
    private BooleanProperty shouldExist;

    /**
     * create an Entity
     * this constructor should be called for subclass Entities
     */
    public Entity(){
        shouldExist = new SimpleBooleanProperty(true);
    }

     /**
      * specify that this entity should destroy itself
      * this method will trigger any ChangeListeners attached to shouldExist
      */
    public void destroy() {
        shouldExist.set(false);
    }

    /**
     * return the internally stored BooleanProperty
     * this method is used in the starter code so the LoopManiaWorldController and LoopManiaWorldControllerLoader can attach ChangeListeners
     */
    public BooleanProperty shouldExist() {
        return shouldExist;
    }

    // x, y coordinates will be stored as some form of IntegerProperty so that change listeners can be added
    // which react when the coordinates are updated
    // this is used in the starter code to ensure the coordinates of a JavaFX node are always updated to match its backend Entity

    /**
     * obtain the IntegerProperty representing the x coordinate.
     * We can attach a ChangeListener to this so the x coordinates of the JavaFX node paired with this entity always match.
     * @return IntegerProperty representing x coordinate
     */
    public abstract IntegerProperty x();

    /**
     * obtain the IntegerProperty representing the y coordinate.
     * We can attach a ChangeListener to this so the y coordinate of the JavaFX node paired with this entity always match.
     * @return IntegerProperty representing y coordinate
     */
    public abstract IntegerProperty y();

    /**
     * obtain the current s coordinate as an int.
     * @return s coordinate, as number from 0 to width-1
     */
    public abstract int getX();

    /**
     * obtain the current y coordinate as an int.
     * @return y coordinate, as number from 0 to height-1
     */
    public abstract int getY();
}
