package unsw.quaint;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import unsw.quaint.state.State;
import unsw.quaint.strategies.CircularToolStrategy;
import unsw.quaint.strategies.EraserToolStrategy;
import unsw.quaint.strategies.FreehandToolStrategy;
import unsw.quaint.strategies.ImagePlaceStrategy;
import unsw.quaint.strategies.RectangleToolStrategy;
import unsw.quaint.strategies.SelectBoxStrategy;
import unsw.quaint.state.SimpleState;
import unsw.quaint.tools.Tool;

/**
 * @author Braedon Wooding.
 */
public class CanvasController implements ICanvasChange {
    @FXML
    private Canvas backCanvas, frontCanvas;

    @FXML
    private ToolBar toolbar;

    @FXML
    private Text currentStateName;

    private State currentState;

    private Tool currentTool;

    private List<Tool> tools = new ArrayList<>();

    @FXML
    private void onMousePressedOnCanvasListener(MouseEvent e) {
        if (this.currentState != null) {
            this.currentState.handleMouseButtonPressed(e.getButton());
        }
    }

    @FXML
    private void onMouseReleasedOnCanvasListener(MouseEvent e) {
        if (this.currentState != null) {
            this.currentState.handleMouseButtonReleased(e.getButton());
        }
    }

    @FXML
    private void onMouseMovedOnCanvasListener(MouseEvent e) {
        if (this.currentState != null) {
            this.currentState.handleMouseMove(e.getX(), e.getY());
        }
    }

    @FXML
    private void onMouseExitedFromCanvasListener(MouseEvent e) {
        if (this.currentState != null) {
            this.currentState.handleMouseExitedCanvas();
        }
    }

    @FXML
    private void onMouseEnteredIntoCanvasListener(MouseEvent e) {
        if (this.currentState != null) {
            this.currentState.handleMouseEnteredCanvas();
        }
    }

    @FXML
    private void onMouseDraggedOnCanvasListener(MouseEvent e) {
        if (this.currentState != null) {
            this.currentState.handleMouseMove(e.getX(), e.getY());
        }
    }

    @FXML
    private void onKeyPressedListener(KeyEvent e) {
        if (this.currentState != null) {
            this.currentState.handleKeyPressed(e.getCode());
        }
    }

    @FXML
    private void onKeyReleasedListener(KeyEvent e) {
        if (this.currentState != null) {
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                changeMouseState(new SimpleState(this, 0, 0));
            } else {
                this.currentState.handleKeyReleased(e.getCode(), e.isShiftDown());
            }
        }
    }

    @FXML
    public void save() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Image Files", ".png"));
        File file = fileChooser.showSaveDialog(backCanvas.getScene().getWindow());

        if (file != null) {
            try {
                WritableImage img = new WritableImage((int) backCanvas.getWidth() + 1,
                        (int) backCanvas.getHeight() + 1);
                SnapshotParameters sc = new SnapshotParameters();
                backCanvas.snapshot(sc, img);
                BufferedImage renderedImage = SwingFXUtils.fromFXImage(img, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @FXML
    public void initialize() {
        GraphicsContext ctx = this.backCanvas.getGraphicsContext2D();
        ctx.clearRect(0, 0, this.backCanvas.getWidth(), this.backCanvas.getHeight());

        tools.addAll(Arrays.asList(SelectBoxStrategy.GetTool(), EraserToolStrategy.getTool(),
                CircularToolStrategy.GetTool(), RectangleToolStrategy.GetTool(), FreehandToolStrategy.GetTool(),
                ImagePlaceStrategy.GetTool()));

        VBox widgetBox = new VBox();
        for (Tool tool : tools) {
            ImageView icon = new ImageView(tool.getIcon());
            icon.setFitWidth(32);
            icon.setFitHeight(32);
            Button toolButton = new Button(tool.getName(), icon);
            toolButton.setTooltip(new Tooltip(tool.getTooltip()));

            toolButton.setOnMouseClicked(e -> {
                this.currentTool = tool;

                try {
                    this.currentState.transitionTool(this.currentTool, widgetBox);
                } catch (Exception e1) {
                    // failed to swap tools :(
                    e1.printStackTrace();
                }
            });
            this.toolbar.getItems().add(toolButton);
        }
        this.toolbar.getItems().addAll(widgetBox);

        changeMouseState(new SimpleState(this, 0, 0));
    }

    @Override
    public void changeMouseState(State newState) {
        this.currentState = newState;
        this.currentStateName.setText(newState.getClass().getSimpleName());
    }

    @Override
    public GraphicsContext getFrontDrawingContext() {
        GraphicsContext ctx = this.frontCanvas.getGraphicsContext2D();
        ctx.clearRect(0, 0, this.frontCanvas.getWidth(), this.frontCanvas.getHeight());
        return ctx;
    }

    @Override
    public GraphicsContext getBackDrawingContext() {
        return this.backCanvas.getGraphicsContext2D();
    }
}
