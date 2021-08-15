package unsw.quaint.state;

import javafx.beans.property.Property;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import unsw.quaint.ICanvasChange;
import unsw.quaint.strategies.IActionStrategy;
import unsw.quaint.strategies.IClickToolStrategy;
import unsw.quaint.strategies.IShapeStrategy;
import unsw.quaint.strategies.PipetteToolStrategy;
import unsw.quaint.tools.Tool;

/**
 * @author Braedon Wooding.
 */
public abstract class State {
    private double x;
    private double y;
    private ICanvasChange canvasEvents;

    public State(ICanvasChange canvasEvents, double x, double y) {
        this.canvasEvents = canvasEvents;
        this.x = x;
        this.y = y;
    }

    // Rare case where protected is actually quite useful
    protected ICanvasChange getCanvasEvents() {
        return canvasEvents;
    }

    protected double getX() {
        return this.x;
    }

    protected double getY() {
        return this.y;
    }

    /**
     * Mouse has moved from a position (oldX, oldY) to (newX, newY)
     */
    public void handleMouseMove(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    /**
     * At the current position the mouse was clicked.
     */
    public void handleMouseButtonPressed(MouseButton button) {}

    /**
     * At the current position a mouse button was released.
     */
    public void handleMouseButtonReleased(MouseButton button) {}

    /**
     * Mouse left the canvas.
     */
    public void handleMouseExitedCanvas() {
        this.x = this.y = 0;
    }

    /**
     * Mouse entered the canvas.
     */
    public void handleMouseEnteredCanvas() {}

    /**
     * Key has been pressed.
     */
    public void handleKeyPressed(KeyCode code) {}

    /**
     * Key has been released.
     */
    public void handleKeyReleased(KeyCode code, boolean isShiftPressed) {}

    public void transitionTool(Tool currentTool, VBox widgetBox) {
        widgetBox.getChildren().clear();
        if (currentTool != null) currentTool.getToolActions().addWidgets(this, widgetBox);

        if (currentTool == null) {
            this.canvasEvents.changeMouseState(new SimpleState(canvasEvents, x, y));
        } else if (currentTool.getToolActions() instanceof IActionStrategy) {
            this.canvasEvents.changeMouseState(new CanvasActionState(canvasEvents, x, y, currentTool, this));
        } else if (currentTool.getToolActions() instanceof IClickToolStrategy) {
            this.canvasEvents.changeMouseState(new PaintingState(canvasEvents, x, y, currentTool));
        } else if (currentTool.getToolActions() instanceof IShapeStrategy) {
            this.canvasEvents.changeMouseState(new ShapeState(canvasEvents, x, y, (IShapeStrategy)currentTool.getToolActions()));
        }
    }

    public void addColorPicker(VBox widgetBox, String label, Property<Color> colorProp) {
        ColorPicker color = new ColorPicker(new Color(1, 1, 1, 1));
        color.maxWidth(30);
        color.prefWidth(30);
        color.setMaxWidth(30);

        Label labelNode = new Label(label);
        labelNode.maxWidth(20);
        color.valueProperty().bindBidirectional(colorProp);
        Tool tool = PipetteToolStrategy.GetTool(colorProp);

        ImageView pipette = new ImageView(tool.getIcon());
        pipette.setFitWidth(16);
        pipette.setFitHeight(16);
        Button toolButton = new Button("", pipette);

        toolButton.setOnMouseClicked(e -> {
            this.canvasEvents.changeMouseState(new CanvasActionState(canvasEvents, x, y, tool, this));
        });

        HBox box = new HBox(labelNode, color, toolButton);
        box.maxWidth(100);
        box.prefWidth(100);
        widgetBox.getChildren().add(box);
    }
}
