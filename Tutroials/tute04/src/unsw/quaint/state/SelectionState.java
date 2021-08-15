package unsw.quaint.state;

import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import unsw.quaint.ICanvasChange;
import unsw.quaint.strategies.IShapeStrategy;
import unsw.quaint.strategies.SelectedRegionStrategy;

/**
 * @author Braedon Wooding.
 */
public class SelectionState extends ShapeState {
    private double startX, startY;
    private double width, height;
    private IShapeStrategy oldStrategy;

    public SelectionState(ICanvasChange canvasEvents, double x, double y, double width, double height, WritableImage img, IShapeStrategy oldStrategy) {
        super(canvasEvents, x, y, new SelectedRegionStrategy(img, x, y));
        this.startX = x;
        this.startY = y;
        this.width = width;
        this.height = height;
        this.oldStrategy = oldStrategy;
    }

    @Override
    public void handleKeyReleased(KeyCode code, boolean isShiftPressed) {
        if (isShiftPressed && code.equals(KeyCode.D)) {
            getCanvasEvents().getFrontDrawingContext();
            getCanvasEvents().getBackDrawingContext().clearRect(startX, startY, width, height);
            getCanvasEvents().changeMouseState(new ShapeState(getCanvasEvents(), getX(), getY(), oldStrategy));
        } else if (isShiftPressed && code.equals(KeyCode.V)) {
            getCanvasEvents().getFrontDrawingContext();
            getStrategy().dragDrawOnContext(getCanvasEvents().getBackDrawingContext(), getX(), getY(), width, height, true);
            getStrategy().setNextState(getCanvasEvents(), getX(), getY(), width, height);
        }
    }

    @Override
    public void handleMouseButtonPressed(MouseButton button) {
        getCanvasEvents().getFrontDrawingContext();
        if (getX() < startX || getY() < startY || getX() > startX + width || getY() > startY + height) {
            getCanvasEvents().changeMouseState(new ShapeState(getCanvasEvents(), getX(), getY(), oldStrategy));
            return;
        }

        super.handleMouseButtonPressed(button);
    }
}
