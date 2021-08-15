package unsw.quaint.strategies;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import unsw.quaint.state.State;
import unsw.quaint.tools.Tool;

/**
 * @author Braedon Wooding.
 */
public class FreehandToolStrategy implements IClickToolStrategy {
    private double oldX;
    private double oldY;

    public static Tool GetTool() {
        return new Tool(new FreehandToolStrategy(), "../imgs/ink-pen.png", "Draw stuff", "Ink Pen");
    }

    private Property<Color> strokeProp = new SimpleObjectProperty<>();

    @Override
    public void pointDraw(GraphicsContext context, double x, double y, boolean isGhost) {
        if (isGhost) return;

        if (oldX == 0 && oldY == 0) {
            oldX = x;
            oldY = y;
        }

        context.setStroke(strokeProp.getValue());
        context.setLineWidth(10);
        context.strokeLine(x, y, x, y);

        oldX = x;
        oldY = y;
    }

    @Override
    public void addWidgets(State state, VBox box) {
        state.addColorPicker(box, "Stroke", strokeProp);
    }
}
