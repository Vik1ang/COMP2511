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
public class CircularToolStrategy implements IShapeStrategy {
    public static Tool GetTool() {
        return new Tool(new CircularToolStrategy(), "../imgs/compass.png", "Draw a circle!", "Oval");
    }

    private Property<Color> strokeProp = new SimpleObjectProperty<>();
    private Property<Color> fillProp = new SimpleObjectProperty<>();

    @Override
    public void dragDrawOnContext(GraphicsContext context, double topLeftX, double topLeftY, double width, double height, boolean isGhost) {
        context.setStroke(strokeProp.getValue());
        context.setFill(fillProp.getValue());
        context.fillOval(topLeftX, topLeftY, width, height);
        context.strokeOval(topLeftX, topLeftY, width, height);
    }

    @Override
    public void addWidgets(State state, VBox box) {
        state.addColorPicker(box, "Stroke", strokeProp);
        state.addColorPicker(box, "Fill", fillProp);
    }
}
