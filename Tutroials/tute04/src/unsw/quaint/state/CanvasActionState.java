package unsw.quaint.state;

import javafx.scene.input.MouseButton;
import unsw.quaint.ICanvasChange;
import unsw.quaint.strategies.IActionStrategy;
import unsw.quaint.tools.Tool;

/**
 * @author Braedon Wooding.
 */
public class CanvasActionState extends State {
    private Tool currentTool;
    private State oldAction;

    public CanvasActionState(ICanvasChange canvasEvents, double x, double y, Tool currentTool, State oldAction) {
        super(canvasEvents, x, y);
        this.currentTool = currentTool;
        this.oldAction = oldAction;
    }

    @Override
    public void handleMouseButtonReleased(MouseButton button) {
        super.handleMouseButtonReleased(button);

        if (button.equals(MouseButton.PRIMARY)) {
            if (((IActionStrategy)this.currentTool.getToolActions()).action(this.getCanvasEvents().getBackDrawingContext(), this.getX(), this.getY())) {
                this.getCanvasEvents().changeMouseState(oldAction);
            }
        }
    }
}
