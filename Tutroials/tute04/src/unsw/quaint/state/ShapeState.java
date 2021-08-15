package unsw.quaint.state;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import unsw.quaint.ICanvasChange;
import unsw.quaint.strategies.IShapeStrategy;

/**
 * @author Braedon Wooding.
 */
public class ShapeState extends State {
    private IShapeStrategy currentStrategy;
    private boolean recordedShift;

    public ShapeState(ICanvasChange canvasEvents, double x, double y, IShapeStrategy currentStrategy) {
        super(canvasEvents, x, y);
        this.currentStrategy = currentStrategy;
    }

    protected IShapeStrategy getStrategy() {
        return currentStrategy;
    }

    @Override
    public void handleKeyPressed(KeyCode code) {
        if (code.equals(KeyCode.SHIFT)) {
            recordedShift = true;
        }
    }

    @Override
    public void handleKeyReleased(KeyCode code, boolean isShiftPressed) {
        if (code.equals(KeyCode.SHIFT) || isShiftPressed) {
            recordedShift = false;
        }
    }

    @Override
    public void handleMouseButtonPressed(MouseButton button) {
        super.handleMouseButtonPressed(button);

        if (button.equals(MouseButton.PRIMARY)) {
            DraggingState state = new DraggingState(getCanvasEvents(), getX(), getY(), currentStrategy);
            if (recordedShift) state = new ConstantAspectRatioDraggingState(state);

            getCanvasEvents().changeMouseState(state);
        }
    }
}
