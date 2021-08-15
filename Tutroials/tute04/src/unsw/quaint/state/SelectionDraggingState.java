package unsw.quaint.state;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author Braedon Wooding.
 */
public class SelectionDraggingState extends DraggingState {
    public SelectionDraggingState(DraggingState state) {
        super(state);
    }

    @Override
    protected void performDrawOnContext(GraphicsContext context, double topLeftX, double topLeftY, double width,
            double height, boolean isGhost) {
        super.performDrawOnContext(context, getX(), getY(), width, height, isGhost);
    }
}
