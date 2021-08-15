package unsw.quaint.state;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import unsw.quaint.ICanvasChange;
import unsw.quaint.strategies.EraserToolStrategy;
import unsw.quaint.strategies.IClickToolStrategy;
import unsw.quaint.tools.Tool;

/**
 * @author Braedon Wooding.
 */
public class PaintingState extends State {
    private boolean primaryHeld = false;
    private Tool currentTool;
    private Tool backupTool = EraserToolStrategy.getTool();

    public PaintingState(ICanvasChange canvasEvents, double x, double y, Tool currentTool) {
        super(canvasEvents, x, y);
        this.currentTool = currentTool;
    }

    @Override
    public void handleMouseButtonPressed(MouseButton button) {
        super.handleMouseButtonPressed(button);

        if (button.equals(MouseButton.PRIMARY)) {
            primaryHeld = true;
            ((IClickToolStrategy)this.currentTool.getToolActions()).pointDraw(this.getCanvasEvents().getBackDrawingContext(), this.getX(), this.getY(), false);
        }
    }

    @Override
    public void handleMouseButtonReleased(MouseButton button) {
        super.handleMouseButtonReleased(button);

        if (button.equals(MouseButton.PRIMARY)) {
            primaryHeld = false;
        } else if (button.equals(MouseButton.SECONDARY)) {
            Tool tmp = backupTool;
            backupTool = currentTool;
            currentTool = tmp;
        }
    }

    @Override
    public void handleMouseMove(double newX, double newY) {
        super.handleMouseMove(newX, newY);

        if (primaryHeld) {
            ((IClickToolStrategy)this.currentTool.getToolActions()).pointDraw(this.getCanvasEvents().getBackDrawingContext(), this.getX(), this.getY(), false);
        }

        GraphicsContext ctx = this.getCanvasEvents().getFrontDrawingContext();
        ((IClickToolStrategy)this.currentTool.getToolActions()).pointDraw(ctx, this.getX(), this.getY(), true);
    }
}
