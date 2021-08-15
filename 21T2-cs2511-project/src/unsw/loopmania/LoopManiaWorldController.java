package unsw.loopmania;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.javatuples.Pair;
import org.json.JSONArray;
import unsw.loopmania.basicEnemies.*;
import unsw.loopmania.buildings.*;


import unsw.loopmania.card.*;
import unsw.loopmania.items.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.codefx.libfx.listener.handle.ListenerHandle;
import org.codefx.libfx.listener.handle.ListenerHandles;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import unsw.loopmania.card.VampireCastleCard;
import unsw.loopmania.items.defence.Armour;
import unsw.loopmania.items.defence.Defence;
import unsw.loopmania.items.defence.Helmet;
import unsw.loopmania.items.defence.Shield;
import unsw.loopmania.items.rare.AndurilFlameOfTheWest;
import unsw.loopmania.items.rare.Rare;
import unsw.loopmania.items.rare.TheOneRing;
import unsw.loopmania.items.rare.TreeStump;
import unsw.loopmania.items.weapon.Staff;
import unsw.loopmania.items.weapon.Stake;
import unsw.loopmania.items.weapon.Weapon;
import unsw.loopmania.memento.LoopManiaWorldCareTaker;
import unsw.loopmania.memento.LoopManiaWorldMemento;
import unsw.loopmania.utils.ImageLoader;
import unsw.loopmania.utils.PreservedWord;

import java.util.EnumMap;

import java.util.Optional;


/**
 * the draggable types.
 * If you add more draggable types, add an enum value here.
 * This is so we can see what type is being dragged.
 */
enum DRAGGABLE_TYPE {
    /**
     * Barracks draggable type.
     */
    BARRACKS,
    /**
     * Campfire draggable type.
     */
    CAMPFIRE,
    /**
     * Tower draggable type.
     */
    TOWER,
    /**
     * Trap draggable type.
     */
    TRAP,
    /**
     * Vampirecastle draggable type.
     */
    VAMPIRECASTLE,
    /**
     * Village draggable type.
     */
    VILLAGE,
    /**
     * Zombie draggable type.
     */
    ZOMBIE,
    /**
     * Armour draggable type.
     */
    ARMOUR,
    /**
     * Helmet draggable type.
     */
    HELMET,
    /**
     * Shield draggable type.
     */
    SHIELD,
    /**
     * Staff draggable type.
     */
    STAFF,
    /**
     * Stake draggable type.
     */
    STAKE,
    /**
     * Sword draggable type.
     */
    SWORD,

    /**
     * Andurilflameofthewest draggable type.
     */
    ANDURILFLAMEOFTHEWEST,

    /**
     * Theonering draggable type.
     */
    THEONERING,

    /**
     * Treestump draggable type.
     */
    TREESTUMP,

    /**
     * Doggiecoin draggable type.
     */
    DOGGIECOIN
}

/**
 * The enum Store operation.
 */
enum STORE_OPERATION {
    /**
     * Buy store operation.
     */
    BUY,
    /**
     * Sell store operation.
     */
    SELL,
    /**
     * None store operation.
     */
    NONE
}

/**
 * A JavaFX controller for the world.
 * <p>
 * All event handlers and the timeline in JavaFX run on the JavaFX application thread:
 * https://examples.javacodegeeks.com/desktop-java/javafx/javafx-concurrency-example/
 * Note in https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Application.html under heading "Threading", it specifies animation timelines are run in the application thread.
 * This means that the starter code does not need locks (mutexes) for resources shared between the timeline KeyFrame, and all of the  event handlers (including between different event handlers).
 * This will make the game easier for you to implement. However, if you add time-consuming processes to this, the game may lag or become choppy.
 * <p>
 * If you need to implement time-consuming processes, we recommend:
 * using Task https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Task.html by itself or within a Service https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Service.html
 * <p>
 * Tasks ensure that any changes to public properties, change notifications for errors or cancellation, event handlers, and states occur on the JavaFX Application thread,
 * so is a better alternative to using a basic Java Thread: https://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
 * The Service class is used for executing/reusing tasks. You can run tasks without Service, however, if you don't need to reuse it.
 * <p>
 * If you implement time-consuming processes in a Task or thread, you may need to implement locks on resources shared with the application thread (i.e. Timeline KeyFrame and drag Event handlers).
 * You can check whether code is running on the JavaFX application thread by running the helper method printThreadingNotes in this class.
 * <p>
 * NOTE: http://tutorials.jenkov.com/javafx/concurrency.html and https://www.developer.com/design/multithreading-in-javafx/#:~:text=JavaFX%20has%20a%20unique%20set,in%20the%20JavaFX%20Application%20Thread.
 * <p>
 * If you need to delay some code but it is not long-running, consider using Platform.runLater https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Platform.html#runLater(java.lang.Runnable)
 * This is run on the JavaFX application thread when it has enough time.
 */
public class LoopManiaWorldController {

    /**
     * squares gridpane includes path images, enemies, character, empty grass, buildings
     */
    @FXML private GridPane squares;

    /**
     * cards gridpane includes cards and the ground underneath the cards
     */
    @FXML private GridPane cards;

    /**
     * anchorPaneRoot is the "background". It is useful since anchorPaneRoot stretches over the entire game world,
     * so we can detect dragging of cards/items over this and accordingly update DragIcon coordinates
     */
    @FXML private AnchorPane anchorPaneRoot;

    /**
     * equippedItems gridpane is for equipped items (e.g. swords, shield, axe)
     */
    @FXML private GridPane equippedItems;

    @FXML private GridPane unequippedInventory;

    /**
     * the health progress bar
     */
    @FXML private ProgressBar healthBar;

    /**
     * the world's doggie coin price
     */
    @FXML private Label doggieCoinPrice;

    /**
     * the character experience
     */
    @FXML private Label experienceNum;

    /**
     * the character's gold number
     */
    @FXML private Label goldNum;

    /**
     * the potion number
     */
    @FXML private Label potionNum;

    /**
     * The game's goal
     */
    @FXML private Label goalsText;

    /**
     * the store window
     */
    @FXML private VBox storeWindow;
    @FXML private Button buyItemYesButton;
    @FXML private Button nextRoundButton;
    @FXML private Button buyItemNoButton;
    private String itemToBuy = null;
    private ImageView itemToSell = null;
    private STORE_OPERATION buyOrSell = STORE_OPERATION.NONE;
    @FXML private Label storeReminder;
    @FXML private Label goldGoalText;
    @FXML private Label expGoalText;
    @FXML private Label cycleGoalText;

    /**
     * The Allies grid.
     */
    @FXML
    GridPane alliesGrid;

    // all image views including tiles, character, enemies, cards... even though cards in separate gridpane...
    private List<ImageView> entityImages;

    /**
     * when we drag a card/item, the picture for whatever we're dragging is set here and we actually drag this node
     */
    private DragIcon draggedEntity;

    /**
     * if the game is paused or not
     */
    private boolean isPaused = true;

    /**
     * The caretaker which can store the new world state
     */
    private LoopManiaWorldCareTaker loopManiaWorldCareTaker;

    /**
     * The JSONArray that stores the entities that need loading
      */
    JSONArray unLoadEntities;

    /**
     * the world state
     */
    private LoopManiaWorld world;

    /**
     * runs the periodic game logic - second-by-second moving of character through maze, as well as enemies, and running of battles
     */
    private Timeline timeline;
    private SimpleStringProperty gameSpeedProperty = new SimpleStringProperty(PreservedWord.gameSpeedSlow);
    private SimpleStringProperty gameMode = new SimpleStringProperty(PreservedWord.gameModeStandard);

    /**
     * the image currently being dragged, if there is one, otherwise null.
     * Holding the ImageView being dragged allows us to spawn it again in the drop location if appropriate.
     */
    private ImageView currentlyDraggedImage;

    /**
     * null if nothing being dragged, or the type of item being dragged
     */
    private DRAGGABLE_TYPE currentlyDraggedType;

    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped over its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged over the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragOver;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped in the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged into the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragEntered;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged outside of the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragExited;

    /**
     * object handling switching to the main menu
     */
    private MenuSwitcher settingsSwitcher;

    /**
     * a memento which stores the current world state's json Object
     */
    private LoopManiaWorldMemento memento;

    /**
     * current FileName Loaded from
     */
    private String currentFileName;

    private ImageView characterImageView;

    /**
     * Instantiates a new Loop mania world controller.
     *
     * @param world           world object loaded from file
     * @param initialEntities the initial JavaFX nodes (ImageViews) which should be loaded into the GUI
     * @param unLoadEntities  the un load entities
     */
    public LoopManiaWorldController(LoopManiaWorld world, List<ImageView> initialEntities, JSONArray unLoadEntities) {
        this.world = world;
        entityImages = new ArrayList<>(initialEntities);

        for (ImageView imageView: entityImages) {
            if (imageView.getImage().equals(ImageLoader.characterImage))
                characterImageView = imageView;
        }

        loopManiaWorldCareTaker = new LoopManiaWorldCareTaker();

        currentlyDraggedImage = null;
        currentlyDraggedType = null;

        // initialize them all...
        gridPaneSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragOver = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragEntered = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragExited = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);

        this.unLoadEntities = unLoadEntities;
    }


    /**
     * Initialize.
     */
    @FXML public void initialize() {
        Image pathTilesImage = new Image((new File("src/images/32x32GrassAndDirtPath.png")).toURI().toString());
        Image inventorySlotImage = new Image((new File("src/images/empty_slot.png")).toURI().toString());
        Rectangle2D imagePart = new Rectangle2D(0, 0, 32, 32);

        // initialize the shop window if needed and bind
        storeWindow.setVisible(true);
        buyItemYesButton.setVisible(false);
        buyItemNoButton.setVisible(false);
        nextRoundButton.setVisible(true);

        // bind the shop state
        world.isAtHeroCastle().addListener((observableValue, oldValue, newValue) -> {
            if (newValue) {
                pause();
                storeWindow.setVisible(true);
                buyItemYesButton.setVisible(false);
                buyItemNoButton.setVisible(false);
                nextRoundButton.setVisible(true);
                autoSaveGame();
            }
        });

        //initialize the allies number
        world.getAlliedSoldierNum().addListener((a, oldValue, newValue)-> {
            for (int i = 0; i < 4; i++)
                alliesGrid.getChildren().get(i).setVisible(true);
            for (int i = (int)newValue; i < 4; i++)
                alliesGrid.getChildren().get(i).setVisible(false);
        });

        // initialize the health bar and bind it with character health
        healthBar.setStyle("-fx-accent: red");
        healthBar.setProgress(world.getCharacterHealthPercentage().get());

        // initialize the health value and bind
        world.getCharacterHealthPercentage().addListener((observableValue, oldDouble, newDouble) -> {
            healthBar.setProgress((double) newDouble);
        });

        // initialize the doggie coin price
        doggieCoinPrice.setText(DoggieCoin.newSellingPriceProperty().getValue().toString());
        DoggieCoin.newSellingPriceProperty().addListener((observableValue, oldDouble, newDouble) -> {
            doggieCoinPrice.setText(newDouble.toString());
        });

        // initialize the exp value and bind
        experienceNum.setText(world.getCharacterExp().getValue().toString());
        world.getCharacterExp().addListener((observableValue, oldValue, newValue) -> {
            experienceNum.setText(newValue.toString());
        });

        // initialize the gold value and bind
        goldNum.setText(world.getCharacterGold().getValue().toString());
        world.getCharacterGold().addListener((observableValue, oldValue, newValue) -> {
            goldNum.setText(newValue.toString());
        });

        // initialize the potion value and bind
        potionNum.setText(world.getEquippedHealthPotionsNum().getValue().toString());
        world.getEquippedHealthPotionsNum().addListener((observableValue, oldValue, newValue) -> {
            potionNum.setText(newValue.toString());
        });

        // add listener to if the character wins
        world.getCharacterWin().addListener((observableValue, oldValue, newValue) -> {
            endGame("Congratulation! You win the game!", "I am the best!");
        });

        // check if character died
        world.getCharacterDeath().addListener((observableValue, oldValue, newValue) -> {
            endGame("You Lose the game!", "I will come back!");
        });

        List<Pair<String, Integer>> goals = world.getGoals();

        for (Pair<String, Integer> goal: goals) {
            switch (goal.getValue0()) {
                case "experience":
                    expGoalText.setText("Exp: " +  world.getCharacterExp().getValue().toString() + "/" + goal.getValue1());
                    world.getCharacterExp().addListener((observableValue, oldValue, newValue) -> {
                        expGoalText.setText("Exp:" + newValue.toString() + "/" + goal.getValue1());
                    });
                    break;
                case "gold":
                    goldGoalText.setText("Gold: " + world.getCharacterGold().getValue().toString() + "/" + goal.getValue1());
                    world.getCharacterGold().addListener((observableValue, oldValue, newValue) -> {
                        goldGoalText.setText("Gold:" + newValue.toString() + "/" + goal.getValue1());
                    });
                    break;
                case "cycle":
                    cycleGoalText.setText("Cycle: " + world.getCycleCompleteProperty().getValue().toString() + "/" + goal.getValue1());
                    world.getCycleCompleteProperty().addListener((observableValue, oldValue, newValue) -> {
                        cycleGoalText.setText("Cycle:" + newValue.toString() + "/" + goal.getValue1());
                    });
                    break;
            }
        }


        // Add the ground first so it is below all other entities (inculding all the twists and turns)
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                ImageView groundView = new ImageView(pathTilesImage);
                groundView.setViewport(imagePart);
                squares.add(groundView, x, y);
            }
        }

        // load entities loaded from the file in the loader into the squares gridpane
        for (ImageView entity : entityImages) {
            squares.getChildren().add(entity);
        }

        // add the ground underneath the cards
        for (int x = 0; x < world.getWidth(); x++) {
            ImageView groundView = new ImageView(pathTilesImage);
            groundView.setViewport(imagePart);
            cards.add(groundView, x, 0);
        }

        // add the empty slot images for the unequipped inventory
        for (int x = 0; x < LoopManiaWorld.unequippedInventoryWidth; x++) {
            for (int y = 0; y < LoopManiaWorld.unequippedInventoryHeight; y++) {
                ImageView emptySlotView = new ImageView(inventorySlotImage);
                unequippedInventory.add(emptySlotView, x, y);
            }
        }

        // initialize the cards/items
        for (int i = 0; i < unLoadEntities.length(); i++) {
            Entity entity = LoopManiaWorldJsonDecode.decode(world, unLoadEntities.getJSONObject(i));
            // load the cards
            if (((String)unLoadEntities.getJSONObject(i).get("type")).contains("Card")) {
                onLoadGeneric(entity);
                world.initialLoad((Card) entity);
            }
            // load the draggable items/equipped items
            else if ((unLoadEntities.getJSONObject(i).has("equipped"))) {
                if ((unLoadEntities.getJSONObject(i).getBoolean("equipped"))) {
                    world.loadEquippedItems((Items) entity);
                    String imageName = WORLD_POSITION_TYPE.getImageName(entity.getClass()).getImageName();
                    try {
                        ImageView imageView = new ImageView((Image) ImageLoader.class.getDeclaredField(imageName).get(this));
                        equippedItems.add(imageView, entity.getX(), entity.getY(), 1, 1);
                        imageView.toFront();
                        world.loadEquippedItems((Items) entity);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    world.initialLoad((Items) entity);
                    onLoadGeneric(entity);
                }
            }
            // load enemy
            else {
                world.initialLoad((BasicEnemy) entity);
                onLoadGeneric(entity);
            }
        }

        // set game mode
        world.setMode(gameMode.get());

        // create the draggable icon
        draggedEntity = new DragIcon();
        draggedEntity.setVisible(false);
        draggedEntity.setOpacity(0.7);
        anchorPaneRoot.getChildren().add(draggedEntity);

        // save the initial memento and add it to the memento list
        LoopManiaWorldMemento loopManiaWorldMemento = new LoopManiaWorldMemento(world);
        setWorldMemento(loopManiaWorldMemento);
        loopManiaWorldCareTaker.add(getWorldMemento());
    }

    /**
     * create and run the timer
     */
    public void startTimer() {
        System.out.println("starting timer");
        isPaused = false;
        // switch the game speed
        int gameSpeed;
        if (gameSpeedProperty.get().compareTo(PreservedWord.gameSpeedSlow) == 0) {
            gameSpeed = 1;
        } else if (gameSpeedProperty.get().compareTo(PreservedWord.gameSpeedMedium) == 0) {
            gameSpeed = 3;
        } else
            gameSpeed = 6;

        // trigger adding code to process main game logic to queue. JavaFX will target framerate of 0.3 seconds
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.6 / gameSpeed), event -> {

            // init random entities if each cycle complete
            List<Entity> initRandomEntities = world.initRandomByCycleComplete();
            if (initRandomEntities != null) {
                for (Entity initRandomEntity : initRandomEntities) {
                    onLoadGeneric(initRandomEntity);
                }
            }

            // run by one move
            world.runTickMoves();

            // check if items on the ground will be pick up
            world.pickUpItemsOnGround();

            // interact with buildings
            // continue if interact with herocastle
            world.interactWithBuildings();

            // start battle
            List<BasicEnemy> defeatedEnemies = world.runBattles();
            for (BasicEnemy e : defeatedEnemies) {
                reactToEnemyDefeat(e);
            }

            // spawn enemies
            List<BasicEnemy> newEnemies = world.possiblySpawnEnemies();
            for (BasicEnemy newEnemy : newEnemies) {
                onLoadGeneric(newEnemy);
            }

            world.checkCharacterWin();

            printThreadingNotes("HANDLED TIMER");
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }


    /**
     * pause the execution of the game loop
     * the human player can still drag and drop items during the game pause
     */
    public void pause() {
        if (!isPaused) {
            isPaused = true;
            System.out.println("pausing");
            timeline.stop();
        }
    }

    /**
     * Terminate.
     */
    public void terminate() {
        pause();
    }

    /**
     * pair the entity an view so that the view copies the movements of the entity.
     * add view to list of entity images
     *
     * @param entity backend entity to be paired with view
     * @param view   frontend imageview to be paired with backend entity
     */
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entityImages.add(view);
        view.toFront();
    }

    /**
     * run GUI events after an enemy is defeated, such as spawning items/experience/gold
     *
     * @param enemy defeated enemy for which we should react to the death of
     */
    private void reactToEnemyDefeat(BasicEnemy enemy) {
        // react to character defeating an enemy
        // in starter code, spawning extra card/weapon...
        // give award to character
        List<StaticEntity> awardList = world.getAward(enemy);

        for (StaticEntity award : awardList) {
             if (award instanceof Card) onLoadGeneric((Card) award);
             else if (award instanceof Weapon) onLoadGeneric((Weapon) award);
             else if (award instanceof Defence) onLoadGeneric((Defence) award);
             else if (award instanceof Rare) onLoadGeneric((Rare) award);
             else if (award instanceof DoggieCoin) onLoadGeneric((DoggieCoin) award);
        }
        if (enemy instanceof ElanMuske) world.setBossIsDead(true);
    }

    /**
     * Load helper.
     *
     * @param image  the image
     * @param entity the entity
     */
    public void loadHelper(Image image, Entity entity) {
        if (entity instanceof BasicEnemy) {
            ImageView view = new ImageView(image);
            addEntity(entity, view);
            squares.getChildren().add(view);
        }
        else if (entity instanceof Gold || entity instanceof  HealthPotion) {
            ImageView view = new ImageView(image);
            addEntity(entity, view);
            squares.getChildren().add(view);
        }
        else if (entity instanceof Card) {
            ImageView view = new ImageView(image);
            DRAGGABLE_TYPE draggableType;
            if (entity instanceof BarracksCard)
                draggableType = DRAGGABLE_TYPE.BARRACKS;
            else if (entity instanceof CampfireCard)
                draggableType = DRAGGABLE_TYPE.CAMPFIRE;
            else if (entity instanceof TowerCard)
                draggableType = DRAGGABLE_TYPE.TOWER;
            else if (entity instanceof TrapCard)
                draggableType = DRAGGABLE_TYPE.TRAP;
            else if (entity instanceof VampireCastleCard)
                draggableType = DRAGGABLE_TYPE.VAMPIRECASTLE;
            else if (entity instanceof VillageCard)
                draggableType = DRAGGABLE_TYPE.VILLAGE;
            else
                draggableType = DRAGGABLE_TYPE.ZOMBIE;

            addDragEventHandlers(view, draggableType, cards, squares);
            addEntity(entity, view);
            cards.getChildren().add(view);
        }
        else if (entity instanceof Defence || entity instanceof Weapon || entity instanceof Rare || entity instanceof DoggieCoin) {
            ImageView view = new ImageView(image);
            DRAGGABLE_TYPE draggableType;
            if (entity instanceof Armour)
                draggableType = DRAGGABLE_TYPE.ARMOUR;
            else if (entity instanceof Helmet)
                draggableType = DRAGGABLE_TYPE.HELMET;
            else if (entity instanceof Shield)
                draggableType = DRAGGABLE_TYPE.SHIELD;
            else if (entity instanceof Staff)
                draggableType = DRAGGABLE_TYPE.STAFF;
            else if (entity instanceof Stake)
                draggableType = DRAGGABLE_TYPE.STAKE;
            else if (entity instanceof  TheOneRing)
                draggableType = DRAGGABLE_TYPE.THEONERING;
            else if (entity instanceof AndurilFlameOfTheWest)
                draggableType = DRAGGABLE_TYPE.ANDURILFLAMEOFTHEWEST;
            else if (entity instanceof TreeStump)
                draggableType = DRAGGABLE_TYPE.TREESTUMP;
            else if (entity instanceof DoggieCoin)
                draggableType = DRAGGABLE_TYPE.DOGGIECOIN;
            else
                draggableType = DRAGGABLE_TYPE.SWORD;
            addDragEventHandlers(view, draggableType, unequippedInventory, equippedItems);
            addEntity(entity, view);
            unequippedInventory.getChildren().add(view);
            addContextMenu(view);
        }
        else if (entity instanceof Building) {
            ImageView view = new ImageView(image);
            addEntity(entity, view);
            squares.getChildren().add(view);
        }
    }

    /**
     * On load generic.
     *
     * @param <T>    the type parameter
     * @param entity the entity
     */
    public <T extends Entity> void onLoadGeneric(T entity) {
        String imageName = WORLD_POSITION_TYPE.getImageName(entity.getClass()).getImageName();
        try {
            loadHelper((Image) ImageLoader.class.getDeclaredField(imageName).get(this), entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * add drag event handlers for dropping into gridpanes, dragging over the background, dropping over the background.
     * These are not attached to invidual items such as swords/cards.
     *
     * @param draggableType  the type being dragged - card or item
     * @param sourceGridPane the gridpane being dragged from
     * @param targetGridPane the gridpane the human player should be dragging to (but we of course cannot guarantee they will do so)
     */
    private void buildNonEntityDragHandlers(DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane) {
        // for example, in the specification, villages can only be dropped on path, whilst vampire castles cannot go on the path
        gridPaneSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /*
                 *you might want to design the application so dropping at an invalid location drops at the most recent valid location hovered over,
                 * or simply allow the card/item to return to its slot (the latter is easier, as you won't have to store the last valid drop location!)
                 */
                if (currentlyDraggedType == draggableType) {
                    // problem = event is drop completed is false when should be true...
                    // https://bugs.openjdk.java.net/browse/JDK-8117019
                    // putting drop completed at start not making complete on VLAB...

                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();
                    int nodeX = GridPane.getColumnIndex(currentlyDraggedImage);
                    int nodeY = GridPane.getRowIndex(currentlyDraggedImage);

                    if (node != targetGridPane && db.hasImage()) {
                        Integer cIndex = GridPane.getColumnIndex(node);
                        Integer rIndex = GridPane.getRowIndex(node);
                        int x = cIndex == null ? 0 : cIndex;
                        int y = rIndex == null ? 0 : rIndex;
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        ImageView image = new ImageView(db.getImage());
                            switch (draggableType) {
                                case BARRACKS: case CAMPFIRE:
                                case TOWER: case TRAP: case VAMPIRECASTLE:
                                case VILLAGE: case ZOMBIE:
                                    node.setOpacity(1);
                                    if (targetGridPane != squares || !world.canplaceCard(nodeX, nodeY, x, y) ) {
                                        currentlyDraggedImage.setX(nodeX);
                                        currentlyDraggedImage.setY(nodeY);
                                        event.setDropCompleted(false);
                                        return;
                                    }
                                    else {
                                        Building newBuilding = convertCardToBuildingByCoordinates(nodeX, nodeY, x, y);
                                        removeDraggableDragEventHandlers(draggableType, targetGridPane);
                                        onLoadGeneric(newBuilding);
                                        characterImageView.toFront();
                                        draggedEntity.setVisible(false);
                                        draggedEntity.setMouseTransparent(false);
                                        // remove drag event handlers before setting currently dragged image to null
                                        currentlyDraggedImage = null;
                                        currentlyDraggedType = null;
                                        event.setDropCompleted(true);
                                    }
                                    break;
                                case SHIELD: case STAFF: case STAKE:
                                case SWORD: case HELMET: case ARMOUR: case DOGGIECOIN: case TREESTUMP:
                                case ANDURILFLAMEOFTHEWEST: case THEONERING:
                                    if (world.checkIfPlacedToCorrectSlot(nodeX, nodeY, x, y)) {
                                        world.unequippedToEquipped(nodeX, nodeY, x, y);
//                                        targetGridPane.getChildren().remove();
                                        removeDraggableDragEventHandlers(draggableType, targetGridPane);
                                        removeItemByCoordinates(nodeX, nodeY);
                                        targetGridPane.add(image, x, y, 1, 1);
                                        image.toFront();
                                        draggedEntity.setVisible(false);
                                        draggedEntity.setMouseTransparent(false);
                                        
                                        // remove drag event handlers before setting currently dragged image to null
                                        currentlyDraggedImage = null;
                                        currentlyDraggedType = null;
                                        event.setDropCompleted(true);
                                    }
                                    else {
                                        node.setOpacity(1);
                                        currentlyDraggedImage.setX(nodeX);
                                        currentlyDraggedImage.setY(nodeY);
                                        event.setDropCompleted(false);
                                        return;
                                    }
                                    break;
                            }
                        // remove drag event handlers before setting currently dragged image to null
                        printThreadingNotes("DRAG DROPPED ON GRIDPANE HANDLED");
                    }
                    else {
                        currentlyDraggedImage.setX(nodeX);
                        currentlyDraggedImage.setY(nodeY);
                        event.setDropCompleted(false);
                        return;
                    }
                }
//                event.setDropCompleted(true);
                // consuming prevents the propagation of the event to the anchorPaneRoot (as a sub-node of anchorPaneRoot, GridPane is prioritized)
                // https://openjfx.io/javadoc/11/javafx.base/javafx/event/Event.html#consume()
                // to understand this in full detail, ask your tutor or read https://docs.oracle.com/javase/8/javafx/events-tutorial/processing.htm
                event.consume();
            }
        });

        // this doesn't fire when we drag over GridPane because in the event handler for dragging over GridPanes, we consume the event
        anchorPaneRootSetOnDragOver.put(draggableType, new EventHandler<DragEvent>() {
            // https://github.com/joelgraff/java_fx_node_link_demo/blob/master/Draggable_Node/DraggableNodeDemo/src/application/RootLayout.java#L110
            @Override
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType) {
                    if (event.getGestureSource() != anchorPaneRoot && event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    else if (event.getGestureSource() == anchorPaneRoot && event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.NONE);
                    }
                }
                if (currentlyDraggedType != null) {
                    draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                }
                event.consume();
            }
        });

        // this doesn't fire when we drop over GridPane because in the event handler for dropping over GridPanes, we consume the event
        anchorPaneRootSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType) {
                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();
                    if (node != anchorPaneRoot && db.hasImage()) {
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        currentlyDraggedImage.setVisible(true);
                        draggedEntity.setVisible(false);
                        draggedEntity.setMouseTransparent(false);
                        // remove drag event handlers before setting currently dragged image to null
                        removeDraggableDragEventHandlers(draggableType, targetGridPane);

                        currentlyDraggedImage = null;
                        currentlyDraggedType = null;
                        event.setDropCompleted(false);
                        return;
                    }
                }
                //let the source know whether the image was successfully transferred and used
                event.setDropCompleted(true);
                event.consume();
            }
        });
    }

    /**
     * remove the card from the world, and spawn and return a building instead where the card was dropped
     *
     * @param cardNodeX     the x coordinate of the card which was dragged, from 0 to width-1
     * @param cardNodeY     the y coordinate of the card which was dragged (in starter code this is 0 as only 1 row of cards)
     * @param buildingNodeX the x coordinate of the drop location for the card, where the building will spawn, from 0 to width-1
     * @param buildingNodeY the y coordinate of the drop location for the card, where the building will spawn, from 0 to height-1
     * @return building entity returned from the world
     */
    private Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        return world.convertCardToBuildingByCoordinates(cardNodeX, cardNodeY, buildingNodeX, buildingNodeY);
    }

    /**
     * remove an item from the unequipped inventory by its x and y coordinates in the unequipped inventory gridpane
     *
     * @param nodeX x coordinate from 0 to unequippedInventoryWidth-1
     * @param nodeY y coordinate from 0 to unequippedInventoryHeight-1
     */
    private void removeItemByCoordinates(int nodeX, int nodeY) {
        world.removeUnequippedInventoryItemByCoordinates(nodeX, nodeY);
    }

    /**
     * add drag event handlers to an ImageView
     *
     * @param view           the view to attach drag event handlers to
     * @param draggableType  the type of item being dragged - card or item
     * @param sourceGridPane the relevant gridpane from which the entity would be dragged
     * @param targetGridPane the relevant gridpane to which the entity would be dragged to
     */
    private void addDragEventHandlers(ImageView view, DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane) {
        view.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                currentlyDraggedImage = view; // set image currently being dragged, so squares setOnDragEntered can detect it...
                currentlyDraggedType = draggableType;
                //Drag was detected, start drap-and-drop gesture
                //Allow any transfer node
                Dragboard db = view.startDragAndDrop(TransferMode.MOVE);

                //Put ImageView on dragboard
                ClipboardContent cbContent = new ClipboardContent();
                cbContent.putImage(view.getImage());
                db.setContent(cbContent);
                view.setVisible(false);

                buildNonEntityDragHandlers(draggableType, sourceGridPane, targetGridPane);

                draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                switch (draggableType) {
                    case BARRACKS: case CAMPFIRE:
                    case TOWER: case TRAP: case VAMPIRECASTLE:
                    case VILLAGE: case ZOMBIE:
                    case SHIELD:
                    case STAFF:
                    case STAKE:
                    case SWORD:
                    case HELMET:
                    case ARMOUR:
                    case THEONERING:
                    case ANDURILFLAMEOFTHEWEST:
                    case TREESTUMP:
                    case DOGGIECOIN:
                        draggedEntity.setImage(view.getImage());
                        break;
                    default:
                        break;
                }

                draggedEntity.setVisible(true);
                draggedEntity.setMouseTransparent(true);
                draggedEntity.toFront();

                // IMPORTANT!!!
                // to be able to remove event handlers, need to use addEventHandler
                // https://stackoverflow.com/a/67283792
                targetGridPane.addEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

                for (Node n : targetGridPane.getChildren()) {
                    // events for entering and exiting are attached to squares children because that impacts opacity change
                    // these do not affect visibility of original image...
                    // https://stackoverflow.com/questions/41088095/javafx-drag-and-drop-to-gridpane
                    gridPaneNodeSetOnDragEntered.put(draggableType, new EventHandler<DragEvent>() {
                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType) {
                                if (event.getGestureSource() != n && event.getDragboard().hasImage()) {
                                    int nodeX = GridPane.getColumnIndex(n);
                                    int nodeY = GridPane.getRowIndex(n);
                                    int x = GridPane.getColumnIndex(currentlyDraggedImage);
                                    int y = GridPane.getRowIndex(currentlyDraggedImage);
                                    switch (currentlyDraggedType) {
                                        case BARRACKS:
                                        case CAMPFIRE:
                                        case TOWER:
                                        case TRAP:
                                        case VAMPIRECASTLE:
                                        case VILLAGE:
                                        case ZOMBIE:
                                            if (world.canplaceCard(x, y, nodeX, nodeY)) {
                                                n.setOpacity(0.7);
                                            }
                                            break;
                                        case SHIELD: case STAFF: case STAKE: case SWORD: case HELMET:
                                        case ARMOUR: case DOGGIECOIN: case THEONERING: case ANDURILFLAMEOFTHEWEST: case TREESTUMP:
                                            if (world.checkIfPlacedToCorrectSlot(nodeX, nodeY, x, y))
                                                n.setOpacity(0.7);
                                            break;
                                    }
                                    //The drag-and-drop gesture entered the target
                                    //show the user that it is an actual gesture target
                                }
                            }
                            event.consume();
                        }
                    });
                    gridPaneNodeSetOnDragExited.put(draggableType, new EventHandler<DragEvent>() {
                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType) {
                                n.setOpacity(1);
                            }
                            event.consume();
                        }
                    });
                    n.addEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
                    n.addEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
                }
                event.consume();
            }

        });
    }

    /**
     * remove drag event handlers so that we don't process redundant events
     * this is particularly important for slower machines such as over VLAB.
     *
     * @param draggableType  either cards, or items in unequipped inventory
     * @param targetGridPane the gridpane to remove the drag event handlers from
     */
    private void removeDraggableDragEventHandlers(DRAGGABLE_TYPE draggableType, GridPane targetGridPane) {
        // remove event handlers from nodes in children squares, from anchorPaneRoot, and squares
        targetGridPane.removeEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));

        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

        for (Node n : targetGridPane.getChildren()) {
            n.removeEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
            n.removeEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
        }
    }

    /**
     * handle the pressing of keyboard keys.
     * Specifically, we should pause when pressing SPACE
     * we should consume when pressing ENTER
     * @param event some keyboard key press
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case SPACE:
                if (!storeWindow.isVisible() && !world.isAtHeroCastle().get()) {
                    if (isPaused) {
                        startTimer();
                    } else {
                        pause();
                    }
                }
                break;
            case ENTER:
                world.useHealthPotion();
            default:
                break;
        }
    }

    /**
     * Sets main menu switcher.
     *
     * @param mainMenuSwitcher the main menu switcher
     */
    public void setMainMenuSwitcher(MenuSwitcher mainMenuSwitcher) {
        this.settingsSwitcher = mainMenuSwitcher;
    }

    /**
     * this method is triggered when click button to go to main menu in FXML
     *
     * @throws IOException
     */
    @FXML
    private void switchToSettings(){
        if (!storeWindow.isVisible() && !isPaused)
            pause();
        settingsSwitcher.switchMenu();
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the world.
     * <p>
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * <p>
     * note that this is put in the controller rather than the loader because we need to track positions of spawned entities such as enemy
     * or items which might need to be removed should be tracked here
     * <p>
     * NOTE teardown functions setup here also remove nodes from their GridPane. So it is vital this is handled in this Controller class
     *
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());

        ChangeListener<Number> xListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        };
        ChangeListener<Number> yListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        };

        // if need to remove items from the equipped inventory, add code to remove from equipped inventory gridpane in the .onDetach part
        ListenerHandle handleX = ListenerHandles.createFor(entity.x(), node)
                .onAttach((o, l) -> o.addListener(xListener))
                .onDetach((o, l) -> {
                    o.removeListener(xListener);
                    entityImages.remove(node);
                    squares.getChildren().remove(node);
                    cards.getChildren().remove(node);
                    equippedItems.getChildren().remove(node);
                    unequippedInventory.getChildren().remove(node);
                })
                .buildAttached();
        ListenerHandle handleY = ListenerHandles.createFor(entity.y(), node)
                .onAttach((o, l) -> o.addListener(yListener))
                .onDetach((o, l) -> {
                    o.removeListener(yListener);
                    entityImages.remove(node);
                    squares.getChildren().remove(node);
                    cards.getChildren().remove(node);
                    equippedItems.getChildren().remove(node);
                    unequippedInventory.getChildren().remove(node);
                })
                .buildAttached();
        handleX.attach();
        handleY.attach();

        // this means that if we change boolean property in an entity tracked from here, position will stop being tracked
        // this wont work on character/path entities loaded from loader classes
        entity.shouldExist().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obervable, Boolean oldValue, Boolean newValue) {
                handleX.detach();
                handleY.detach();
            }
        });
    }

    /**
     * we added this method to help with debugging so you could check your code is running on the application thread.
     * By running everything on the application thread, you will not need to worry about implementing locks, which is outside the scope of the course.
     * Always writing code running on the application thread will make the project easier, as long as you are not running time-consuming tasks.
     * We recommend only running code on the application thread, by using Timelines when you want to run multiple processes at once.
     * EventHandlers will run on the application thread.
     */
    private void printThreadingNotes(String currentMethodLabel) {
        System.out.println("\n###########################################");
        System.out.println("current method = " + currentMethodLabel);
        System.out.println("In application thread? = " + Platform.isFxApplicationThread());
        System.out.println("Current system time = " + java.time.LocalDateTime.now().toString().replace('T', ' '));
    }

    /**
     * Gets game speed property.
     *
     * @return the game speed property
     */
    public SimpleStringProperty getGameSpeedProperty() {
        return gameSpeedProperty;
    }

    /**
     *
     * If the user choose to buy the item, things will be updated.
     */
    @FXML private void storeYesItemPush() {
        if (buyOrSell == STORE_OPERATION.BUY) {
            Entity entity;
            entity = world.buyItem(itemToBuy);
            if (itemToBuy.compareTo(PreservedWord.itemArmour) == 0
                    || itemToBuy.compareTo(PreservedWord.itemHelmet) == 0
                    || itemToBuy.compareTo(PreservedWord.itemShield) == 0 ) {
                onLoadGeneric((Defence) entity);
                storeReminder.setText("You successfully buy one " + itemToBuy + "!" );
            }
            else if (itemToBuy.compareTo(PreservedWord.itemStaff) == 0
                    || itemToBuy.compareTo(PreservedWord.itemStake) == 0
                    || itemToBuy.compareTo(PreservedWord.itemSword) == 0 ) {
                onLoadGeneric((Weapon) entity);
                storeReminder.setText("You successfully buy one " + itemToBuy + "!" );
            }
            else {
                storeReminder.setText("You successfully buy one potion!");
            }
            itemToBuy = null;
            buyItemYesButton.setVisible(false);
            buyItemNoButton.setVisible(false);
        }
        else if (buyOrSell == STORE_OPERATION.SELL) {
            int x = GridPane.getColumnIndex(itemToSell);
            int y = GridPane.getRowIndex(itemToSell);
            storeReminder.setText("You successfully sell the item!");
            world.sellItem(x, y);
        }
        buyItemYesButton.setVisible(false);
        buyItemNoButton.setVisible(false);
        anchorPaneRoot.getScene().getRoot().requestFocus();
    }

    /**
     * if player refuse to buy the item, there will be nothing done.
     */
    @FXML private void storeNoItemPush() {
        storeReminder.setText("Welcome to store!");
        itemToBuy = null;
        itemToSell = null;
        buyItemYesButton.setVisible(false);
        buyItemNoButton.setVisible(false);
        buyOrSell = STORE_OPERATION.NONE;
    }

    /**
     * send a request if they want to buy anything,
     * if player is eligible to do that, he will be given a double reminder, otherwise he will receive a message that
     * he cannot buy it
     * @param actionEvent
     */
    @FXML private void buyItemRequest(ActionEvent actionEvent) {
        if (((MenuItem)actionEvent.getTarget()).getId().compareTo("potion") == 0)
            itemToBuy = PreservedWord.itemPotion;
        else if (((MenuItem)actionEvent.getTarget()).getId().compareTo("armour") == 0)
            itemToBuy = PreservedWord.itemArmour;
        else if (((MenuItem)actionEvent.getTarget()).getId().compareTo("helmet") == 0)
            itemToBuy = PreservedWord.itemHelmet;
        else if (((MenuItem)actionEvent.getTarget()).getId().compareTo("shield") == 0)
            itemToBuy = PreservedWord.itemShield;
        else if (((MenuItem)actionEvent.getTarget()).getId().compareTo("staff") == 0)
            itemToBuy = PreservedWord.itemStaff;
        else if (((MenuItem)actionEvent.getTarget()).getId().compareTo("sword") == 0)
            itemToBuy = PreservedWord.itemSword;
        else
            itemToBuy = PreservedWord.itemStake;
        if (world.canBuyItem(itemToBuy)) {
            storeReminder.setText("Are you sure you want to buy a " + ((MenuItem)actionEvent.getTarget()).getId() + "?");
            buyItemYesButton.setVisible(true);
            buyItemNoButton.setVisible(true);
            buyOrSell = STORE_OPERATION.BUY;
        }
        else {
            buyOrSell = STORE_OPERATION.NONE;
            storeReminder.setText("You cannot buy " + ((MenuItem)actionEvent.getTarget()).getId() + " for some reason.");
        }
    }

    /**
     * the button for continue the next round
     */
    @FXML private void continueNextRound() {
        Scene currentScene = anchorPaneRoot.getScene();
        anchorPaneRoot.getScene().getRoot().requestFocus();
        world.finishBuyAndSellAtCastle();
        startTimer();
        storeWindow.setVisible(false);
        nextRoundButton.setVisible(false);
    }

    /**
     * Prepare to start timer.
     */
    public void prepareToStartTimer() {
        world.setMode(gameMode.get());
        if (!storeWindow.isVisible())
            this.startTimer();
    }

    /**
     * Gets game mode.
     *
     * @return the game mode
     */
    public SimpleStringProperty getGameMode() {
        return gameMode;
    }

    /**
     * Lose game.
     *
     * @param winOrLose       the win or lose
     * @param reminderMessage the reminder message
     */
    public void endGame(String winOrLose, String reminderMessage) {
        timeline.stop();
        Stage secondStage = new Stage();
        secondStage.setResizable(false);
        secondStage.setTitle(winOrLose);

        secondStage.setOnCloseRequest((event) -> {
            Platform.exit();
        });

        FXMLLoader loseLoader = new FXMLLoader(getClass().getResource("GameEndView.fxml"));
        Parent gameLoseRoot;
        try {
            gameLoseRoot = loseLoader.load();
            Scene gameLoseWindow = new Scene(gameLoseRoot);
            GameEndController gameEndController = loseLoader.getController();

            gameEndController.setGameEndLabelText(winOrLose);

            Stage gameStage = (Stage) storeWindow.getScene().getWindow();
            gameEndController.setMainWindow(gameStage);
            gameEndController.setFinishButtonText(reminderMessage);


            gameLoseRoot.requestFocus();
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.initOwner(gameStage);
            secondStage.setScene(gameLoseWindow);
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * add a context menu for the image view so we can sell
     * @param view
     */
    private void addContextMenu(ImageView view) {
        ContextMenu contextMenu = new ContextMenu();
        //Creating the menu Items for the context menu
        MenuItem item1 = new MenuItem("Sell");
        contextMenu.getItems().addAll(item1);
        item1.setOnAction((ActionEvent e) -> {
            askIfWantToSell(view);
        });
        view.setOnContextMenuRequested(event -> {
            contextMenu.show(view.getScene().getWindow(), event.getScreenX(), event.getScreenY());
        });
    }

    /**
     * ask the user if they really want to sell things
     * @param view
     */
    private void askIfWantToSell(ImageView view) {
        if (world.isAtHeroCastle().get()) {
            itemToSell = view;
            buyItemYesButton.setVisible(true);
            buyItemNoButton.setVisible(true);
            storeReminder.setText("Are you sure you want to sell?");
            buyOrSell = STORE_OPERATION.SELL;
        }
    }

    /**
     * open the settings menu
     * @throws IOException
     */
    @FXML private void openTheHelpSettings() throws IOException {
        if (!isPaused)
            pause();
        Stage helpMenuStage = new Stage();
        helpMenuStage.setResizable(false);
        helpMenuStage.setTitle("Help Menu");

        FXMLLoader helpMenuLoader = new FXMLLoader(getClass().getResource("GameHelpView.fxml"));
        Parent helpMenuRoot = helpMenuLoader.load();
        Scene helpMenuWindow = new Scene(helpMenuRoot);

        GameHelpController gameHelpController = helpMenuLoader.getController();
        gameHelpController.setMainGameStage(anchorPaneRoot.getScene().getRoot());


        helpMenuRoot.requestFocus();
        helpMenuStage.setScene(helpMenuWindow);
        helpMenuStage.show();

        helpMenuStage.setOnCloseRequest((event) -> {
            anchorPaneRoot.getScene().getRoot().requestFocus();
        });
    }

    @FXML private void saveTheGame() throws IOException {
        if (!isPaused)
            pause();

        if (!world.isAtHeroCastle().get())  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getButtonTypes().clear();
            ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().addAll(okButton);
            alert.setTitle("Save game error");
            alert.setHeaderText("Save game error");
            alert.setContentText("You cannot save the game \nwhen you are not at hero castle!");
            alert.showAndWait();
        }
        else {
            TextInputDialog dialog = new TextInputDialog("my_world");
            dialog.setTitle("Save Game");
            dialog.setHeaderText("");
            dialog.setContentText("Please enter the file name\n(repeated file name will overwrite):");
            dialog.getDialogPane().getButtonTypes().clear();

            ButtonType yesType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType noType = new ButtonType("Cancel", ButtonBar.ButtonData.NO);
            dialog.getDialogPane().getButtonTypes().addAll(noType, yesType);

            Optional<String> result = dialog.showAndWait();
            boolean inValidCharacter;
            while (true) {
                inValidCharacter = false;
                if (result.isPresent()) {
                    String fileName = result.get();
                    for (int i = 0; i < fileName.length(); i++) {
                        if (!(fileName.charAt(i) >= '0' && fileName.charAt(i) <= '9')
                                && !(fileName.charAt(i) >= 'a' && fileName.charAt(i) <= 'z')
                                && !(fileName.charAt(i) >= 'A' && fileName.charAt(i) <= 'Z')
                                && fileName.charAt(i) != '_') {
                            inValidCharacter = true;
                            dialog.close();
                            dialog = new TextInputDialog("my_world");
                            dialog.setTitle("Save Game");
                            dialog.setHeaderText("Invalid Character, please retype\n(Valid character: 0-9, a-z, A-Z, '_')");
                            dialog.setContentText("Please enter the file name\n(repeated file name will overwrite):");
                            dialog.getDialogPane().getButtonTypes().clear();
                            dialog.getDialogPane().getButtonTypes().addAll(noType, yesType);
                            result = dialog.showAndWait();
                            break;
                        }
                    }
                    if (!inValidCharacter) {
                        LoopManiaWorldMemento loopManiaWorldMemento = new LoopManiaWorldMemento(world);
                        setWorldMemento(loopManiaWorldMemento);
                        loopManiaWorldCareTaker.add(getWorldMemento());
                        loopManiaWorldMemento.save(fileName);
                    }
                }
                if (!inValidCharacter)
                    break;
            }
        }
        anchorPaneRoot.requestFocus();
    }

     private void autoSaveGame() {
         LoopManiaWorldMemento loopManiaWorldMemento = new LoopManiaWorldMemento(world);
         setWorldMemento(loopManiaWorldMemento);
         loopManiaWorldCareTaker.add(getWorldMemento());
     }

     private void setWorldMemento(LoopManiaWorldMemento loopManiaWorldMemento) {
        this.memento = loopManiaWorldMemento;
     }

    private LoopManiaWorldMemento getWorldMemento() {
        return this.memento;
    }

    public LoopManiaWorldCareTaker getLoopManiaWorldCareTaker() {
        return loopManiaWorldCareTaker;
    }

    public void setCurrentFileName(String currentFileName) { this.currentFileName = currentFileName; }

    public String getCurrentFileName() { return this.currentFileName; }
}
