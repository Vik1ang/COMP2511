package unsw.quaint.strategies;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Paint;
import unsw.quaint.ICanvasChange;
import unsw.quaint.state.SelectionState;
import unsw.quaint.tools.Tool;

/**
 * @author Braedon Wooding.
 */
public class SelectBoxStrategy implements IShapeStrategy {
    private WritableImage img;

    public static Tool GetTool() {
        return new Tool(new SelectBoxStrategy(), "../imgs/select-box.png", "Select a region to manipulate it", "Select");
    }

    @Override
    public void dragDrawOnContext(GraphicsContext context, double topLeftX, double topLeftY, double width, double height, boolean isGhost) {
        context.setStroke(Paint.valueOf("black"));

        if (!isGhost) {
            GraphicsContext ctx = context;
            img = new WritableImage((int)width + 1, (int)height + 1);
            SnapshotParameters sc = new SnapshotParameters();
            sc.setViewport(new Rectangle2D(topLeftX, topLeftY, width + 1, height + 1));
            ctx.getCanvas().snapshot(sc, img);
        } else {
            context.setLineDashes(25, 25);
            context.strokeRect(topLeftX, topLeftY, width, height);
        }
    }

    @Override
    public void setNextState(ICanvasChange canvasEvents, double topLeftX, double topLeftY, double width, double height) {
        dragDrawOnContext(canvasEvents.getFrontDrawingContext(), topLeftX, topLeftY, width, height, true);

        canvasEvents.changeMouseState(new SelectionState(canvasEvents, topLeftX, topLeftY, width + 1, height + 1, img, this));
    }
}
