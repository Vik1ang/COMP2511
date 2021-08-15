package unsw.quaint;

import javafx.scene.canvas.GraphicsContext;
import unsw.quaint.state.State;

/**
 * @author Braedon Wooding.
 */
public interface ICanvasChange {
    /**
     * Change the canvas state to the one given.
     */
    public void changeMouseState(State newState);

    /**
     * Get the front drawing context that is used
     * to show visual effects like 'ghost' images.
     */
    public GraphicsContext getFrontDrawingContext();

    /**
     * Get the back drawing context that is used
     * to actually render changes onto the context.
     */
    public GraphicsContext getBackDrawingContext();
}
