package unsw.quaint.strategies;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author Braedon Wooding.
 */
public interface IActionStrategy extends IToolStrategy {
    public boolean action(GraphicsContext context, double x, double y);
}
