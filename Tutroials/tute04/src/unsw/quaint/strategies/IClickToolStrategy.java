package unsw.quaint.strategies;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author Braedon Wooding.
 */
public interface IClickToolStrategy extends IToolStrategy {
    public void pointDraw(GraphicsContext context, double x, double y, boolean isGhost);
}
