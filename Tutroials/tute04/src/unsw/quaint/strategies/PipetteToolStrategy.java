package unsw.quaint.strategies;

import javafx.beans.property.Property;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import unsw.quaint.tools.Tool;

/**
 * @author Braedon Wooding.
 */
public class PipetteToolStrategy implements IActionStrategy {
    private Property<Color> color;

    public static Tool GetTool(Property<Color> color) {
        return new Tool(new PipetteToolStrategy(color), "../imgs/pipette.png", "Pick a color", "Pipette");
    }

    public PipetteToolStrategy(Property<Color> color) {
        this.color = color;
    }

    @Override
    public boolean action(GraphicsContext context, double x, double y) {
        WritableImage img = new WritableImage(1, 1);
        SnapshotParameters sc = new SnapshotParameters();
        sc.setViewport(new Rectangle2D(x, y, 1, 1));
        context.getCanvas().snapshot(sc, img);
        this.color.setValue(img.getPixelReader().getColor(0, 0));
        return true;
    }
}
