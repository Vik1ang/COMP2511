package unsw.quaint.state;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import unsw.quaint.ICanvasChange;
import unsw.quaint.strategies.IShapeStrategy;

/**
 * @author Braedon Wooding.
 */
public class DraggingState extends State {
    private IShapeStrategy currentStrategy;
    private double startX, startY;

    public DraggingState(ICanvasChange canvasEvents, double x, double y, IShapeStrategy currentStrategy) {
        super(canvasEvents, x, y);
        this.startX = x;
        this.startY = y;
        this.currentStrategy = currentStrategy;
    }

    public DraggingState(DraggingState state) {
        this(state.getCanvasEvents(), state.getX(), state.getY(), state.currentStrategy);
        this.startX = state.startX;
        this.startY = state.startY;
    }

    @Override
    public void handleKeyPressed(KeyCode code) {
        if (code.equals(KeyCode.SHIFT)) {
            getCanvasEvents().changeMouseState(new ConstantAspectRatioDraggingState(this));
        }
    }

    protected void performDrawOnContext(GraphicsContext context, double topLeftX, double topLeftY, double width, double height, boolean isGhost) {
        if (isGhost) context.setLineDashes(10);
        else context.setLineDashes();
        this.currentStrategy.dragDrawOnContext(context, topLeftX, topLeftY, width, height, isGhost);
    }

    private void performDrawOnContextUsingCoords(GraphicsContext context, double startX, double startY, double endX, double endY, boolean isGhost) {
        double x_min = Math.min(startX, endX), x_max = Math.max(startX, endX);
        double y_min = Math.min(startY, endY), y_max = Math.max(startY, endY);
        double width = x_max - x_min, height = y_max - y_min;
        
        if (Math.abs(getX() - startX) > 1 && Math.abs(getY() - startY) > 1) {
            performDrawOnContext(context, x_min, y_min, width, height, isGhost);
        }

        if (!isGhost) {
            currentStrategy.setNextState(getCanvasEvents(), x_min, y_min, width, height);
        }
    }

    @Override
    public void handleMouseButtonReleased(MouseButton button) {
        super.handleMouseButtonReleased(button);

        if (button.equals(MouseButton.PRIMARY)) {
            performDrawOnContextUsingCoords(getCanvasEvents().getBackDrawingContext(), startX, startY, getX(), getY(), false);
        }
    }

    @Override
    public void handleMouseMove(double newX, double newY) {
        super.handleMouseMove(newX, newY);
        performDrawOnContextUsingCoords(getCanvasEvents().getFrontDrawingContext(), startX, startY, getX(), getY(), true);
    }
}
