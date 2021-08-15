package unsw.quaint.strategies;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import unsw.quaint.ICanvasChange;
import unsw.quaint.state.SelectionState;
import unsw.quaint.state.ShapeState;

/**
 * @author Braedon Wooding.
 */
public class SelectedRegionStrategy implements IShapeStrategy {
    private WritableImage img;
    private double offsetX;
    private double offsetY;

    public SelectedRegionStrategy(WritableImage img, double offsetX, double offsetY) {
        this.img = img;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void dragDrawOnContext(GraphicsContext context, double topLeftX, double topLeftY, double width, double height, boolean isGhost) {
        if (!isGhost) {
            context.clearRect(offsetX, offsetY, img.getWidth(), img.getHeight());
        }
        context.drawImage(img, topLeftX, topLeftY);
    }

    @Override
    public void setNextState(ICanvasChange canvasEvents, double topLeftX, double topLeftY, double width, double height) {
        if (img == null) {
            canvasEvents.changeMouseState(new ShapeState(canvasEvents, 0, 0, new SelectBoxStrategy()));
        } else {
            canvasEvents.changeMouseState(new SelectionState(canvasEvents, topLeftX, topLeftY, img.getWidth(), img.getHeight(), img, new SelectBoxStrategy()));
        }
    }
}
