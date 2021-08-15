package unsw.quaint.strategies;

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import unsw.quaint.tools.Tool;

/**
 * @author Braedon Wooding.
 */
public class ImagePlaceStrategy implements IActionStrategy {
    public static Tool GetTool() {
        return new Tool(new ImagePlaceStrategy(), "../imgs/img.png", "Place some images", "Image");
    }

    @Override
    public boolean action(GraphicsContext context, double x, double y) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Image Files", ".png"));
        File file = fileChooser.showOpenDialog(context.getCanvas().getScene().getWindow());
        if (file != null) {
            Image img = new Image(file.toURI().toString());
            context.drawImage(img, x, y - img.getHeight());
        }
        return false;
    }
}
