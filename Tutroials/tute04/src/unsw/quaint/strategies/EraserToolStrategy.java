package unsw.quaint.strategies;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import unsw.quaint.tools.Tool;

/**
 * @author Braedon Wooding.
 */
public class EraserToolStrategy implements IClickToolStrategy {
    public static Tool getTool() {
        return new Tool(new EraserToolStrategy(), "../imgs/eraser.png", "Erase your mistakes", "Eraser");
    }

    @Override
    public void pointDraw(GraphicsContext context, double x, double y, boolean isGhost) {
        if (isGhost) {
            context.setStroke(Paint.valueOf("black"));
            context.setLineDashes(10);
            context.strokeRect(x - 50, y - 50, 100, 100);
        } else {
            context.clearRect(x - 50, y - 50, 100, 100);
        }
    }
}
