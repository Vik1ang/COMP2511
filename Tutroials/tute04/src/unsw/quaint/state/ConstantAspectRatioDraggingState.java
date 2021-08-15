package unsw.quaint.state;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

/**
 * @author Braedon Wooding.
 */
public class ConstantAspectRatioDraggingState extends DraggingState {
    public ConstantAspectRatioDraggingState(DraggingState state) {
        super(state);
    }

    @Override
    public void handleKeyReleased(KeyCode code, boolean isShiftPressed) {
        if (code.equals(KeyCode.SHIFT) || isShiftPressed) {
            getCanvasEvents().changeMouseState(new DraggingState(this));
        }
    }

    @Override
    protected void performDrawOnContext(GraphicsContext context, double topLeftX, double topLeftY, double width,
            double height, boolean isGhost) {
        super.performDrawOnContext(context, topLeftX, topLeftY, Math.min(width, height), Math.min(width, height),
                isGhost);
    }
}
