package unsw.loopmania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;

/**
 * The type Game help controller.
 */
public class GameHelpController {

    /**
     * The Help menu information.
     */
    public Label helpMenuInformation;
    /**
     * The Entities information grid.
     */
    public GridPane entitiesInformationGrid;

    //card
    private Button vampireCastleCardButton;
    private Button zombiePitCardButton;
    private Button towerCardButton;
    private Button villageCardButton;
    private Button barracksCardButton;
    private Button trapCardButton;
    private Button campfireCardButton;

    //building
    private Button vampireCastleBuildingButton;
    private Button zombiePitBuildingButton;
    private Button towerBuildingButton;
    private Button villageBuildingButton;
    private Button barracksBuildingButton;
    private Button trapBuildingButton;
    private Button campfireBuildingButton;

    // enemy
    private Button vampireButton;
    private Button zombieButton;
    private Button slugzombieButton;
    private Button slugButton;
    private Button doggieButton;
    private Button elanMuskeButton;

    // item Button
    private Button swordButton;
    private Button staffButton;
    private Button stakeButton;
    private Button armourButton;
    private Button shieldButton;
    private Button helmetButton;
    private Button goldButton;
    private Button healthPotionButton;
    private Button doggieCoinButton;


    // rare item Button
    private Button theOneRingButton;
    private Button andurilButton;
    private Button treeStumpButton;

    // main game stage
    private Parent mainGameStage;


    /**
     * Instantiates a new Game help controller.
     */
    public GameHelpController() {

    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        //card
        ImageView vampireCastleCardImage = new ImageView(new Image((new File("src/images/vampire_castle_card.png")).toURI().toString()));
        ImageView zombiePitCardImage = new ImageView(new Image((new File("src/images/zombie_pit_card.png")).toURI().toString()));
        ImageView towerCardImage = new ImageView(new Image((new File("src/images/tower_card.png")).toURI().toString()));
        ImageView villageCardImage = new ImageView(new Image((new File("src/images/village_card.png")).toURI().toString()));
        ImageView barracksCardImage = new ImageView(new Image((new File("src/images/barracks_card.png")).toURI().toString()));
        ImageView trapCardImage = new ImageView(new Image((new File("src/images/trap_card.png")).toURI().toString()));
        ImageView campfireCardImage = new ImageView(new Image((new File("src/images/campfire_card.png")).toURI().toString()));

        // enemy
        ImageView vampireImage = new ImageView(new Image((new File("src/images/vampire.png")).toURI().toString()));
        ImageView zombieImage = new ImageView(new Image((new File("src/images/zombie.png")).toURI().toString()));
        ImageView slugImage = new ImageView(new Image((new File("src/images/slug.png")).toURI().toString()));
        ImageView slugZombieImage = new ImageView(new Image((new File("src/images/slugzombie.png")).toURI().toString()));
        ImageView elanMuskeImage = new ImageView(new Image((new File("src/images/ElanMuske.png")).toURI().toString()));
        ImageView doggieImage = new ImageView(new Image((new File("src/images/doggie.png")).toURI().toString()));



        // equipment image
        ImageView swordImage = new ImageView(new Image((new File("src/images/basic_sword.png")).toURI().toString()));
        ImageView staffImage = new ImageView(new Image((new File("src/images/staff.png")).toURI().toString()));
        ImageView stakeImage = new ImageView(new Image((new File("src/images/stake.png")).toURI().toString()));
        ImageView shieldImage = new ImageView(new Image((new File("src/images/shield.png")).toURI().toString()));
        ImageView helmetImage = new ImageView(new Image((new File("src/images/helmet.png")).toURI().toString()));
        ImageView armourImage = new ImageView(new Image((new File("src/images/armour.png")).toURI().toString()));
        ImageView goldImage = new ImageView(new Image((new File("src/images/gold_pile.png")).toURI().toString()));
        ImageView healthPotionImage = new ImageView(new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString()));
        ImageView doggieCoinImage = new ImageView(new Image((new File("src/images/doggiecoin.png")).toURI().toString()));

        //buildings
        ImageView vampireCastleBuildingImage = new ImageView(new Image((new File("src/images/vampire_castle_building_purple_background.png")).toURI().toString()));
        ImageView zombiePitBuildingImage = new ImageView(new Image((new File("src/images/zombie_pit.png")).toURI().toString()));
        ImageView towerBuildingImage = new ImageView(new Image((new File("src/images/tower.png")).toURI().toString()));
        ImageView villageBuildingImage = new ImageView(new Image((new File("src/images/village.png")).toURI().toString()));
        ImageView barracksBuildingImage = new ImageView(new Image((new File("src/images/barracks.png")).toURI().toString()));
        ImageView trapBuildingImage = new ImageView(new Image((new File("src/images/trap.png")).toURI().toString()));
        ImageView campfireBuildingImage = new ImageView(new Image((new File("src/images/campfire.png")).toURI().toString()));

        //rare items
        ImageView theOneRingImage = new ImageView(new Image((new File("src/images/the_one_ring.png")).toURI().toString()));
        ImageView andurilImage = new ImageView(new Image((new File("src/images/anduril_flame_of_the_west.jpg")).toURI().toString()));
        ImageView treeStumpImage = new ImageView(new Image((new File("src/images/tree_stump.png")).toURI().toString()));

        //card
        vampireCastleCardButton = new Button("", vampireCastleCardImage);
        vampireCastleCardButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "vampireCastleCardButton"));

        zombiePitCardButton = new Button("", zombiePitCardImage);
        zombiePitCardButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "zombiePitCardButton"));

        towerCardButton = new Button("", towerCardImage);
        towerCardButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "towerCardButton"));

        villageCardButton = new Button("", villageCardImage);
        villageCardButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "villageCardButton"));

        barracksCardButton = new Button("", barracksCardImage);
        barracksCardButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "barracksCardButton"));

        trapCardButton = new Button("", trapCardImage);
        trapCardButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "trapCardButton"));

        campfireCardButton = new Button("", campfireCardImage);
        campfireCardButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "campfireCardButton"));


        //building
        vampireCastleBuildingButton = new Button("",vampireCastleBuildingImage);
        vampireCastleBuildingButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "vampireCastleBuildingButton"));

        zombiePitBuildingButton = new Button("",zombiePitBuildingImage);
        zombiePitBuildingButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "zombiePitBuildingButton"));

        towerBuildingButton = new Button("",towerBuildingImage);
        towerBuildingButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "towerBuildingButton"));

        villageBuildingButton = new Button("",villageBuildingImage);
        villageBuildingButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "villageBuildingButton"));

        barracksBuildingButton = new Button("",barracksBuildingImage);
        barracksBuildingButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "barracksBuildingButton"));

        trapBuildingButton = new Button("",trapBuildingImage);
        trapBuildingButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "trapBuildingButton"));

        campfireBuildingButton = new Button("",campfireBuildingImage);
        campfireBuildingButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "campfireBuildingButton"));


        // enemy
        vampireButton = new Button("", vampireImage);
        vampireButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "vampireButton"));

        zombieButton = new Button("",zombieImage);
        zombieButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "zombieButton"));

        slugzombieButton = new Button("",slugZombieImage);
        slugzombieButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "slugzombieButton"));

        slugButton = new Button("",slugImage);
        slugButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "slugButton"));

        doggieButton = new Button("", doggieImage);
        doggieButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "doggieButton"));

        elanMuskeButton = new Button("", elanMuskeImage);
        elanMuskeButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "elanMuskeButton"));



        // equipment Button
        swordButton = new Button("",swordImage);
        swordButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "swordButton"));

        staffButton = new Button("",staffImage);
        staffButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "staffButton"));

        stakeButton = new Button("",stakeImage);
        stakeButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "stakeButton"));

        armourButton = new Button("",armourImage);
        armourButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "armourButton"));

        shieldButton = new Button("",shieldImage);
        shieldButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "shieldButton"));

        helmetButton = new Button("",helmetImage);
        helmetButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "helmetButton"));

        goldButton = new Button("",goldImage);
        goldButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "goldButton"));

        healthPotionButton = new Button("",healthPotionImage);
        healthPotionButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "healthPotionButton"));

        doggieCoinButton = new Button("",doggieCoinImage);
        doggieCoinButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "doggieCoinButton"));

        // rare item
        theOneRingButton = new Button("",theOneRingImage);
        theOneRingButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "theOneRingButton"));

        andurilButton = new Button("",andurilImage);
        andurilButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "andurilButton"));

        treeStumpButton = new Button("",treeStumpImage);
        treeStumpButton.setOnAction(actionEvent -> showEntityInformation(actionEvent, "treeStumpButton"));

    }

    /**
     * Print out game information.
     */
    public void printOutGameInformation() {
        entitiesInformationGrid.getChildren().clear();
        helpMenuInformation.setText("The game is called loopMania Hero. You need to help the little character on the map" +
                " to fight with the enemies on the map (click Enemies and items for details). It's your choice to put any building's card you gained on the map to help with " +
                "the character. Some buildings can give benefits to character, some are not (click Buildings and Card for details). Character will have to " +
                "encounter different kinds of enemies and fight with them. The battles are done automatically. If you lose" +
                " the battle, the game will end. If you win the battle, you will gain items based on the possibility (click Enemies and items for details)." +
                " The items you got will be put into inventory by default and it's your choice which equipment you wish the " +
                "character can be equipped with. There will be goals for every game. " +
                "You need to help the character to reach the goal and keep it alive before reaching the goal.\n" +
                "Hope you can enjoy the game!");
    }

    /**
     * Start a game information.
     */
    public void startAGameInformation() {
        entitiesInformationGrid.getChildren().clear();
        helpMenuInformation.setText("1. Click on the Button \"Start a new game\" on the main menu.\n\n" +
                "2.Click on the Button \"Continue next round\" once you start a new game" +
                "3.You can control the game speed and the game mode before you start a new game.\n\n" +
                "4.Once you enter a new game, you can press the \"Space\" in your keyboard to pause the game.\n\n" +
                "5.Once you enter a new game, you can switch back to settings menu to reset the speed but not the game mode\n\n" +
                "6.Press \"Enter\" on you keyboard to use Health Potion to help character if you have\n\n");
    }

    /**
     * Show enemy item.
     *
     * @param actionEvent the action event
     */
    public void showEnemyItem(ActionEvent actionEvent) {
        helpMenuInformation.setText("");
        entitiesInformationGrid.getChildren().clear();

        Label enemy = new Label("Enemy");
        entitiesInformationGrid.add(enemy, 0, 0);
        addButtonToImage(vampireButton, 0, 1);
        addButtonToImage(slugButton, 0, 2);
        addButtonToImage(zombieButton, 0, 3);
        addButtonToImage(doggieButton, 0, 4);
        addButtonToImage(elanMuskeButton, 0, 5);
        addButtonToImage(slugzombieButton, 0, 6);


        Label items = new Label("Item");
        entitiesInformationGrid.add(items, 1, 0);
        addButtonToImage(swordButton, 1, 1);
        addButtonToImage(staffButton, 1, 2);
        addButtonToImage(stakeButton, 1, 3);
        addButtonToImage(armourButton, 1, 4);
        addButtonToImage(shieldButton, 1, 5);
        addButtonToImage(helmetButton, 2, 1);
        addButtonToImage(goldButton, 2, 2);
        addButtonToImage(healthPotionButton, 2, 3);
        addButtonToImage(doggieCoinButton, 2, 4);

        Label rareItem = new Label("rareItem");
        entitiesInformationGrid.add(rareItem, 3, 0);
        addButtonToImage(theOneRingButton, 3, 1);
        addButtonToImage(andurilButton, 3, 2);
        addButtonToImage(treeStumpButton, 3, 3);
    }

    /**
     * Show card building.
     */
    public void showCardBuilding() {
        helpMenuInformation.setText("");
        entitiesInformationGrid.getChildren().clear();

        Label cards = new Label("Cards");
        entitiesInformationGrid.add(cards, 0, 0);
        addButtonToImage(towerCardButton, 0, 1);
        addButtonToImage(campfireCardButton, 0, 2);
        addButtonToImage(villageCardButton, 0, 3);
        addButtonToImage(barracksCardButton, 0, 4);
        addButtonToImage(trapCardButton, 0, 5);
        addButtonToImage(vampireCastleCardButton, 0, 6);
        addButtonToImage(zombiePitCardButton, 0, 7);

        Label buildings = new Label("Buildings");

        entitiesInformationGrid.add(buildings, 1, 0);
        addButtonToImage(towerBuildingButton, 1, 1);
        addButtonToImage(campfireBuildingButton, 1, 2);
        addButtonToImage(villageBuildingButton, 1, 3);
        addButtonToImage(barracksBuildingButton, 1, 4);
        addButtonToImage(trapBuildingButton, 1, 5);
        addButtonToImage(vampireCastleBuildingButton, 1, 6);
        addButtonToImage(zombiePitBuildingButton, 1, 7);
    }

    private void addButtonToImage(Button button, int col, int row) {
        entitiesInformationGrid.add(button, col, row);
    }

    /**
     * Close menu.
     */
    public void closeMenu() {
        ((Stage)helpMenuInformation.getScene().getWindow()).close();
        if (mainGameStage != null)
            mainGameStage.requestFocus();
    }

    /**
     * Sets main game stage.
     *
     * @param mainGameStage the main game stage
     */
    public void setMainGameStage(Parent mainGameStage) {
        this.mainGameStage = mainGameStage;
    }

    /**
     * Show entity information.
     *
     * @param actionEvent the action event
     * @param type        the type
     */
    public void showEntityInformation(ActionEvent actionEvent, String type) {
        switch (type) {
            //card
            case "vampireCastleCardButton": case "zombiePitCardButton": case "towerCardButton":
                helpMenuInformation.setText("Placement: Only on non-path tiles adjacent to the path");
                break;
            case "villageCardButton": case "barracksCardButton": case "trapCardButton":
                helpMenuInformation.setText("Placement: Only on path tiles");
                break;
            case "campfireCardButton":
                helpMenuInformation.setText("Placement: Any non-path tile");
                break;


            //building
            case "vampireCastleBuildingButton":
                helpMenuInformation.setText("Description: Produces vampires every 5 cycles of the path completed" +
                        " by the Character, spawning nearby on the path");
                break;
            case "zombiePitBuildingButton":
                helpMenuInformation.setText("Description: Produces zombies every cycle of the path " +
                        "completed by the Character, spawning nearby on the path");
                break;
            case "towerBuildingButton":
                helpMenuInformation.setText("Description: During a battle within its shoot" +
                        "ing radius, enemies will be attacked by the tower");
                break;
            case "villageBuildingButton":
                helpMenuInformation.setText("Description: Character regains health when passing through");
                break;
            case "barracksBuildingButton":
                helpMenuInformation.setText("Description: Produces allied soldier to join Character when passes through");
                break;
            case "trapBuildingButton":
                helpMenuInformation.setText("Description: When an enemy steps on a trap," +
                        " the enemy is damaged (and potentially killed if it loses all health) and the trap is destroyed");
                break;
            case "campfireBuildingButton":
                helpMenuInformation.setText("Description: Character deals " +
                        "double damage within campfire battle radius");
                break;


            // equipment Button
            case "swordButton":
                helpMenuInformation.setText("Description: A standard melee weapon. Increases damage dealt by Character\n\n" +
                        "Where can obtain: Purchase in Hero's Castle, loot from enemies, or obtained from" +
                        " cards lost due to being the oldest and replaced");
                break;

            case "staffButton":
                helpMenuInformation.setText("Description: A melee weapon with lower stats than the sword" +
                        ", but causes very high damage to vampires\n\n" +
                        "Where can obtain: Purchase in Hero's Castle, loot from enemies, " +
                        "or obtained from cards lost due to being the oldest and replaced");
                break;

            case "stakeButton":
                helpMenuInformation.setText("Description: A melee weapon with very low stats (lower than both the sword and stake), " +
                        "which has a random chance of inflicting a trance, " +
                        "which transforms the attacked enemy into an allied soldier temporarily " +
                        "(and fights alongside the Character). If the trance ends during the fight, " +
                        "the affected enemy reverts back to acting as an enemy which fights the Character. " +
                        "If the fight ends whilst the enemy is in a trance, the enemy dies\n\n" +
                        "Where can obtain: Purchase in Hero's Castle, loot from enemies, " +
                        "or obtained from cards lost due to being the oldest and replaced");
                break;

            case "armourButton":
                helpMenuInformation.setText("Description: Body armour, provides defence and halves enemy attack\n\n" +
                        "Where can obtain: Purchase in Hero's Castle, loot from enemies," +
                        " or obtained from cards lost due to being the oldest and replaced");
                break;

            case "shieldButton":
                helpMenuInformation.setText("Description: Defends against enemy attacks, " +
                        "critical vampire attacks have a 60% lower chance of occurring\n\n" +
                        "Where can obtain: Purchase in Hero's Castle, loot from enemies, " +
                        "or obtained from cards lost due to being the oldest and replaced");
                break;

            case "helmetButton":
                helpMenuInformation.setText("Description: Defends against enemy attacks, enemy attacks are reduced by a scalar value. " +
                        "The damage inflicted by the Character against enemies is reduced (since it is harder to see)\n\n" +
                        "Where can obtain: Purchase in Hero's Castle, loot from enemies," +
                        " or obtained from cards lost due to being the oldest and replaced");
                break;

            case "goldButton" :
                helpMenuInformation.setText("Description: Money, used to buy things\n\n" +
                        "Where can obtain: Obtain in Hero's Castle by selling items, loot from enemies, pick up off the ground, " +
                        "or obtained from cards/items lost due to being the oldest and replaced. Spawns randomly on path tiles");
                break;
            case "healthPotionButton" :
                helpMenuInformation.setText("Description: Refills Character health\n\n" +
                        "Where can obtain: Purchase from Hero's Castle, loot from enemies, pick up off the ground, " +
                        "or obtained from cards lost due to being the oldest and replaced." +
                        " Spawns randomly on path tiles");
                break;
            case "doggieCoinButton" :
                helpMenuInformation.setText("Description: A revolutionary asset type, " +
                        "which randomly fluctuates in sellable price to an extraordinary extent. Can sell at shop\n\n" +
                        "Where can obtain: Obtained when defeat Doggie");
                break;


                // enemies
            case "slugButton" :
                helpMenuInformation.setText("Description: A standard enemy type. Low health and low damage. " +
                        "The battle radius is the same as the support radius for a slug.\n\n" +
                        "Spawn conditions: Spawns randomly on path tiles");
                break;
            case "zombieButton" :
                helpMenuInformation.setText("Description: Zombies have low health, moderate damage, " +
                        "and are slower compared to other enemies. " +
                        "A critical bite from a zombie against an allied soldier (which has a random chance of occurring) will transform the allied soldier into a zombie, " +
                        "which will then proceed to fight against the Character until it is killed. " +
                        "Zombies have a higher battle radius than slugs\n\n" +
                        "Spawn conditions: Spawns from zombie pit every time the Character completes a cycle of the path");
                break;
            case "vampireButton" :
                helpMenuInformation.setText("Description: Vampires have high damage, " +
                        "are susceptible to the stake weapon, and run away from campfires. " +
                        "They have a higher battle radius than slugs, and an even higher support radius. " +
                        "A critical bite (which has a random chance of occurring) " +
                        "from a vampire causes random additional damage with every vampire attack, " +
                        "for a random number of vampire attacks\n\n" +
                        "Spawn conditions: Spawns from vampire castle every 5 cycles of the path completed by the Character");
                break;
            case "doggieButton" :
                helpMenuInformation.setText("Description: A special boss which spawns the DoggieCoin upon defeat, " +
                        "which randomly fluctuates in sellable price to an extraordinary extent. " +
                        "It has high health and can stun the character, which prevents the character from making an attack temporarily. " +
                        "The battle and support radii are the same as for slugs\n\n" +
                        "Spawn conditions: Spawns after 20 cycles");
                break;
            case "elanMuskeButton" :
                helpMenuInformation.setText("Description: An incredibly tough boss which, when appears, " +
                        "causes the price of DoggieCoin to increase drastically. " +
                        "Defeating this boss causes the price of DoggieCoin to plummet. " +
                        "Elan has the ability to heal other enemy NPCs. " +
                        "The battle and support radii are the same as for slugs\n\n" +
                        "Spawn conditions: Spawns after 40 cycles, and the player has reached 10000 experience points");
                break;
            case "slugzombieButton" :
                helpMenuInformation.setText("Description: SlugZombie looks similar as Zombie, " +
                        "It has 5 slugs on his body, which decrease the damage and the transfer rate. " +
                        "(SlugZombie can't see everything clearly) " +
                        "However, when SlugZombie dead, " +
                        "it will preduce 5 Slugs and those Slugs join the battle directly.\n\n" +
                        "Spawn conditions: After 5 cycles, spawns from zombie pit when the Character completes an odd cycle");
                break;

            case "theOneRingButton" :
                    helpMenuInformation.setText("Description: If the Character is killed, it respawns with full health up to a single time\n\n" +
                            "Where can obtain: Every time the Character wins a battle, there is a small chance of winning a Rare Item.");
                    break;
            case "andurilButton" :
                    helpMenuInformation.setText("Description: A very high damage sword which causes triple damage against bosses\n\n" +
                            "Where can obtain: Every time the Character wins a battle, there is a small chance of winning a Rare Item.");
                    break;
            case "treeStumpButton" :
                    helpMenuInformation.setText("Description: An especially powerful shield, which provides higher defence against bosses\n\n" +
                            "Where can obtain: Every time the Character wins a battle, there is a small chance of winning a Rare Item.");
                    break;
        }

    }

    /**
     * Show goals.
     */
    @FXML public void showGoals() {
        entitiesInformationGrid.getChildren().clear();
        helpMenuInformation.setText("To win the game, you need to achieve all the goals, which can be:\n" +
                "1. Gold number requirement\n" +
                "2. Experience number requirement\n" +
                "3. Cycle complete number requirement\n" +
                "4. Kill the Boss\n");
    }
}
