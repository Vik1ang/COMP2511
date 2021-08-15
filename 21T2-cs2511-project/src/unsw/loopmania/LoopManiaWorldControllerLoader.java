package unsw.loopmania;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.geometry.Rectangle2D;
import unsw.loopmania.buildings.*;
import unsw.loopmania.character.Character;
import unsw.loopmania.memento.LoopManiaWorldMemento;
import unsw.loopmania.utils.ImageLoader;

/**
 * A LoopManiaLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * <p>
 * this should NOT be used to load any entities which spawn, or might be removed (use controller for that)
 * since this doesnt add listeners or teardown functions (so it will be very hacky to remove event handlers)
 */
public class LoopManiaWorldControllerLoader extends LoopManiaWorldLoader {

    private List<ImageView> entities;

    /**
     * Instantiates a new Loop mania world controller loader.
     *
     * @param filename the filename
     * @throws FileNotFoundException the file not found exception
     */
    public LoopManiaWorldControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
    }

    /**
     * Instantiates a new Loop mania world controller loader.
     *
     * @param loopManiaWorldMemento the loop mania world memento
     */
    public LoopManiaWorldControllerLoader(LoopManiaWorldMemento loopManiaWorldMemento) {
        super(loopManiaWorldMemento);
        entities = new ArrayList<>();
    }

    @Override
    public void onLoad(Character character) {
        ImageView view = new ImageView(ImageLoader.characterImage);
        view.toFront();
        addEntity(character, view);
    }

    @Override
    public void loadHelper(Building building){
        String imageName = WORLD_POSITION_TYPE.getImageName(building.getClass()).getImageName();
        try {
            ImageView view = new ImageView((Image) ImageLoader.class.getDeclaredField(imageName).get(this));
            addEntity(building, view);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * load path tile ImageView based on configuration in file.
     * Note how src/images/32x32GrassAndDirtPath.png has 8 images within it
     * x and y values we produce here are the coordinates of the top left of our sub-image, taken from the top-left of the pathTilesImage
     */
    @Override
    public void onLoad(PathTile pathTile, PathTile.Direction into, PathTile.Direction out) {
        // note https://stackoverflow.com/a/58041962
        // we need to find the offset within the rectangle, we can do this from adjacencies
        ImageView view = new ImageView(ImageLoader.pathTilesImage);
        int x;
        int y;

        if (into == out && (into == PathTile.Direction.DOWN || into == PathTile.Direction.UP)) {
            // vertical, no corners
            x = 32;
            y = 0;
        } else if ((into == PathTile.Direction.DOWN && out == PathTile.Direction.RIGHT) || (into == PathTile.Direction.LEFT && out == PathTile.Direction.UP)) {
            // corner piece, turning left up, or turning down right
            x = 0;
            y = 64;
        } else if ((into == PathTile.Direction.UP && out == PathTile.Direction.RIGHT) || (into == PathTile.Direction.LEFT && out == PathTile.Direction.DOWN)) {
            // corner piece, turning up right, or turning left down
            x = 32;
            y = 32;
        } else if ((into == PathTile.Direction.DOWN && out == PathTile.Direction.LEFT) || (into == PathTile.Direction.RIGHT && out == PathTile.Direction.UP)) {
            // corner piece, turning down left, or turning right up
            x = 32;
            y = 64;
        } else if ((into == PathTile.Direction.RIGHT && out == PathTile.Direction.DOWN) || (into == PathTile.Direction.UP && out == PathTile.Direction.LEFT)) {
            // corner piece, turning right down, or turning up left
            x = 0;
            y = 32;
        } else if (into == out && (into == PathTile.Direction.LEFT || into == PathTile.Direction.RIGHT)) {
            // horizontal, no corners
            x = 0;
            y = 96;
        } else {
            throw new RuntimeException("Not a valid adjacency mapping " + into + "->" + out);
        }
        Rectangle2D imagePart = new Rectangle2D(x, y, 32, 32);
        view.setViewport(imagePart);
        addEntity(pathTile, view);
    }
    
    /**
     * pair the  backend entity and view, so the view tracks the coordinates of the entity
     * @param entity backend entity
     * @param view frontend image to be paired with the backend entity
     */
    private void addEntity(Entity entity, ImageView view) {
        trackPositionOfNonSpawningEntities(entity, view);
        entities.add(view);
    }

    /**
     * Track the position of entities which don't spawn or require removal.
     * We only setup the node to follow the coordinates of the backend entity.<br>
     * Items which potentially need to be removed should be spawned by controller, and have listener handles and teardown functions added.
     * @param entity backend entity
     * @param node frontend image to track the coordinates of the backend entity
     */
    private static void trackPositionOfNonSpawningEntities(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });

    }

    /**
     * Create a controller that can be attached to the LoopManiaView with all the
     * loaded entities.
     *
     * @return loop mania world controller
     */
    public LoopManiaWorldController loadController() {
        return new LoopManiaWorldController(load(), entities, getUnloadEntities());
    }

}
