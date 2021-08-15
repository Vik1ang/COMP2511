package unsw.quaint.strategies;

import javafx.scene.canvas.GraphicsContext;
import unsw.quaint.ICanvasChange;
import unsw.quaint.state.ShapeState;

/**
 * @author Braedon Wooding.
 */
public interface IShapeStrategy extends IToolStrategy {
    public void dragDrawOnContext(GraphicsContext context, double topLeftX, double topLeftY, double width, double height, boolean isGhost);

    public default void setNextState(ICanvasChange canvasEvents, double startX, double startY, double endX, double endY) {
        canvasEvents.changeMouseState(new ShapeState(canvasEvents, 0, 0, this));
    }
}
