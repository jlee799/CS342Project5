package Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Separator;

public class FXClient extends Application {

    String ipAdd = "127.0.0.1";
    int portNum = 5555;
    String idNumStr;
    int idNumInt;
    private ClientNetworkConnectionRPS conn = null;
    String username;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Button> availableItems = new ArrayList<>();
    boolean didUseItem = false;
    Stage primaryStage;

    int windowWidth = 960;
    int windowHeight = 700;

    HashMap<String, Scene> sceneMap;
    Scene connectScene, itemSelectScene, playScene;

    /* connect scene */

    Button connect;
    Text portNumText;
    Text portInt;
    Text ipAddressText;
    Text ipAddressString;
    Text changePortText;
    TextField changePort;
    Text changeIpText;
    TextField changeIp;
    TextField enterNameTextField;

    /* select item scene */

    Button bronzeSwordButton = new Button();
    Button silverSwordButton = new Button();
    Button goldSwordButton = new Button();
    Button diamondSwordButton = new Button();

    Button bronzeShieldButton = new Button();
    Button silverShieldButton = new Button();
    Button goldShieldButton = new Button();
    Button diamondShieldButton = new Button();

    Button bronzeHelmetButton = new Button();
    Button silverHelmetButton = new Button();
    Button goldHelmetButton = new Button();
    Button diamondHelmetButton = new Button();

    Button bronzeChestButton = new Button();
    Button silverChestButton = new Button();
    Button goldChestButton = new Button();
    Button diamondChestButton = new Button();

    Button healthPotButton = new Button();
    Button attackPotButton = new Button();
    Button defensePotButton = new Button();
    Button bombButton = new Button();

    Text itemSelectMessageText = new Text("Waiting for players to connect...");
    Button rollButton = new Button("Roll");

    /* images */
    Image bronzeSwordImage = new Image("File:src/BronzeSword.png");
    Image silverSwordImage = new Image("File:src/SilverSword.png");
    Image goldSwordImage = new Image("File:src/GoldSword.png");
    Image diamondSwordImage = new Image("File:src/DiamondSword.png");

    Image bronzeShieldImage = new Image("File:src/BronzeShield.png");
    Image silverShieldImage = new Image("File:src/SilverShield.png");
    Image goldShieldImage = new Image("File:src/GoldShield.png");
    Image diamondShieldImage = new Image("File:src/DiamondShield.png");

    Image bronzeHelmetImage = new Image("File:src/BronzeHelmet.png");
    Image silverHelmetImage = new Image("File:src/SilverHelmet.png");
    Image goldHelmetImage = new Image("File:src/GoldHelmet.png");
    Image diamondHelmetImage = new Image("File:src/DiamondHelmet.png");

    Image bronzeChestImage = new Image("File:src/BronzeChest.png");
    Image silverChestImage = new Image("File:src/SilverChest.png");
    Image goldChestImage = new Image("File:src/GoldChest.png");
    Image diamondChestImage = new Image("File:src/DiamondChest.png");

    Image healthPotImage = new Image("File:src/HealthPot.png");
    Image attackPotImage = new Image("File:src/AttackPot.png");
    Image defensePotImage = new Image("File:src/DefensePot.png");
    Image bombImage = new Image("File:src/Bomb.png");

    /* temporary gui elements */

    Text nameText1 = new Text("Player 1");
    Text nameText2 = new Text("Player 2");
    Text nameText3 = new Text("Player 3");
    Text nameText4 = new Text("Player 4");

    Text healthText1 = new Text("Health: 100/100");
    Text healthText2 = new Text("Health: 100/100");
    Text healthText3 = new Text("Health: 100/100");
    Text healthText4 = new Text("Health: 100/100");

    Text readyText1 = new Text("READY");
    Text readyText2 = new Text("READY");
    Text readyText3 = new Text("waiting...");
    Text readyText4 = new Text("waiting...");

    /*------------------------*/

    /*Text[] nameTextArray;
    Text[] healthTextArray;
    Image[] swordImageArray;
    Image[] shieldImageArray;
    Image[] helmetImageArray;
    Image[] chestImageArray;
    Image[] itemImageArray;*/

    /* imageViews */

    ImageView swordImageView1 = new ImageView();
    ImageView swordImageView2 = new ImageView();
    ImageView swordImageView3 = new ImageView();
    ImageView swordImageView4 = new ImageView();

    ImageView shieldImageView1 = new ImageView();
    ImageView shieldImageView2 = new ImageView();
    ImageView shieldImageView3 = new ImageView();
    ImageView shieldImageView4 = new ImageView();

    ImageView helmetImageView1 = new ImageView();
    ImageView helmetImageView2 = new ImageView();
    ImageView helmetImageView3 = new ImageView();
    ImageView helmetImageView4 = new ImageView();

    ImageView chestImageView1 = new ImageView();
    ImageView chestImageView2 = new ImageView();
    ImageView chestImageView3 = new ImageView();
    ImageView chestImageView4 = new ImageView();

    ImageView itemImageView1 = new ImageView();
    ImageView itemImageView2 = new ImageView();
    ImageView itemImageView3 = new ImageView();
    ImageView itemImageView4 = new ImageView();

    /* stat texts */

    Text swordText1 = new Text();
    Text swordText2 = new Text();
    Text swordText3 = new Text();
    Text swordText4 = new Text();

    Text shieldText1 = new Text();
    Text shieldText2 = new Text();
    Text shieldText3 = new Text();
    Text shieldText4 = new Text();

    Text helmetText1 = new Text();
    Text helmetText2 = new Text();
    Text helmetText3 = new Text();
    Text helmetText4 = new Text();

    Text chestText1 = new Text();
    Text chestText2 = new Text();
    Text chestText3 = new Text();
    Text chestText4 = new Text();

    Text itemText1 = new Text();
    Text itemText2 = new Text();
    Text itemText3 = new Text();
    Text itemText4 = new Text();

    Text playerText = new Text("Player: ");
    TextField insertPlayerTextField = new TextField();

    Button attackButton;
    Button defendButton;
    Button useItemButton;

    String playMessageString = "";
    Text playMessageText = new Text("");

    Button exitButton;

    private void setItemSelectButtonActions() {

        /* sword buttons */

        bronzeSwordButton.setOnAction( event -> {
		pickItem("sword", "1");
        });

        silverSwordButton.setOnAction( event -> {
		pickItem("sword", "2");
        });

        goldSwordButton.setOnAction( event -> {
		pickItem("sword", "3");
        });

        diamondSwordButton.setOnAction( event -> {
		pickItem("sword", "4");
        });

        /* shield buttons */

        bronzeShieldButton.setOnAction( event -> {
		pickItem("shield", "1");
        });

        silverShieldButton.setOnAction( event -> {
		pickItem("shield", "2");
        });

        goldShieldButton.setOnAction( event -> {
		pickItem("shield", "3");
        });

        diamondShieldButton.setOnAction( event -> {
		pickItem("shield", "4");
        });

        /* helmet buttons */

        bronzeHelmetButton.setOnAction( event -> {
		pickItem("helmet", "1");
        });

        silverHelmetButton.setOnAction( event -> {
		pickItem("helmet", "2");
        });

        goldHelmetButton.setOnAction( event -> {
		pickItem("helmet", "3");
        });

        diamondHelmetButton.setOnAction( event -> {
		pickItem("helmet", "4");
        });

        /* chest buttons */

        bronzeChestButton.setOnAction( event -> {
		pickItem("chest", "1");
        });

        silverChestButton.setOnAction( event -> {
		pickItem("chest", "2");
        });

        goldChestButton.setOnAction( event -> {
		pickItem("chest", "3");
        });

        diamondChestButton.setOnAction( event -> {
		pickItem("chest", "4");
        });

        /* item buttons */

        healthPotButton.setOnAction( event -> {
		pickItem("item", "hPot");
        });

        attackPotButton.setOnAction( event -> {
		pickItem("item", "aPot");
        });

        defensePotButton.setOnAction( event -> {
		pickItem("item", "dPot");
        });

        bombButton.setOnAction( event -> {
		pickItem("item", "bomb");
        });

        rollButton.setOnAction( event -> {
            rollDie();
        });
    }

    private void setPlayButtonActions() {
        attackButton.setOnAction( event -> {
		    String playerToAttack = insertPlayerTextField.getText();
		    attack(playerToAttack);
		    disableMoveButtons();
            playMessageString = "";
            playMessageText.setText(playMessageString);
        });

        defendButton.setOnAction( event -> {
		    defend();
            disableMoveButtons();
            playMessageString = "";
            playMessageText.setText(playMessageString);
        });

        useItemButton.setOnAction( event -> {
		    useItem(players.get(idNumInt-1).getItem());
		    didUseItem = true;
            disableMoveButtons();
            playMessageString = "";
            playMessageText.setText(playMessageString);
        });

        exitButton.setOnAction( event -> {
		    quit();
        });
    }

    private void disableMoveButtons() {
        attackButton.setDisable(true);
        defendButton.setDisable(true);
        useItemButton.setDisable(true);
    }

    private void createConnect( Stage primaryStage) {

        connect = new Button("CONNECT");
        //connect.setFont(connectSceneText);

        portNumText = new Text("Port Number: ");
        portInt = new Text(Integer.toString(portNum));
        ipAddressText = new Text("IP Address: ");
        ipAddressString = new Text(ipAdd);
        changePortText = new Text("Change Port: ");
        changePort = new TextField();
        changeIpText = new Text("Change IP: ");
        changeIp = new TextField();
        enterNameTextField = new TextField();
        enterNameTextField.setPromptText("enter a username");
        enterNameTextField.setFocusTraversable(false);

        changePort.setOnAction(event -> {
            String portString;
            portString = changePort.getText();
            try {
                portNum = Integer.parseInt(portString);
                portInt.setText(Integer.toString(portNum));
            } catch (Exception e) {
                System.out.println("Cannot parse string to int");
            }

            changePort.clear();
        });

        changeIp.setOnAction(event -> {
            String portString;
            portString = changeIp.getText();
            ipAdd = portString;
            ipAddressString.setText(ipAdd);

            changeIp.clear();
        });

        connect.setOnAction(event -> {
            try {
                conn = createClient();
                conn.startConn();
                primaryStage.setScene(sceneMap.get("itemSelect"));
                primaryStage.show();
                //conn.send("connect");
            } catch (Exception e) {
                System.out.println("Failed to connect");
            }

        });

        HBox bottomConnect = new HBox(10, portNumText, portInt, ipAddressText, ipAddressString, changePortText, changePort, changeIpText, changeIp);

        BorderPane connectButtonBP = new BorderPane();
        connectButtonBP.setCenter(connect);
        connectButtonBP.setTop(enterNameTextField);
        BorderPane connectSceneBP = new BorderPane();
        connectSceneBP.setCenter(connectButtonBP);
        connectSceneBP.setBottom(bottomConnect);


        connectScene = new Scene(connectSceneBP, 800, 700);
        sceneMap.put("connect", connectScene);
    }

    private void createItemSelectScene(Stage primaryStage) {
        BorderPane bp = new BorderPane();

        /* create sword image views */

        ImageView swordImageView1 = new ImageView(bronzeSwordImage);
        swordImageView1.setFitHeight(75);
        swordImageView1.setPreserveRatio(true);
        bronzeSwordButton.setGraphic(swordImageView1);
        HBox swordImageView1HBox = new HBox(20, bronzeSwordButton, new Text("1"));

        ImageView swordImageView2 = new ImageView(silverSwordImage);
        swordImageView2.setFitHeight(75);
        swordImageView2.setPreserveRatio(true);
        silverSwordButton.setGraphic(swordImageView2);
        HBox swordImageView2HBox = new HBox(20, silverSwordButton, new Text("2"));

        ImageView swordImageView3 = new ImageView(goldSwordImage);
        swordImageView3.setFitHeight(75);
        swordImageView3.setPreserveRatio(true);
        goldSwordButton.setGraphic(swordImageView3);
        HBox swordImageView3HBox = new HBox(20, goldSwordButton, new Text("3"));

        ImageView swordImageView4 = new ImageView(diamondSwordImage);
        swordImageView4.setFitHeight(75);
        swordImageView4.setPreserveRatio(true);
        diamondSwordButton.setGraphic(swordImageView4);
        HBox swordImageView4HBox = new HBox(20, diamondSwordButton, new Text("4"));

        /* create shield image views */

        ImageView shieldImageView1 = new ImageView(bronzeShieldImage);
        shieldImageView1.setFitHeight(75);
        shieldImageView1.setPreserveRatio(true);
        bronzeShieldButton.setGraphic(shieldImageView1);
        HBox shieldImageView1HBox = new HBox(20, bronzeShieldButton, new Text("1"));

        ImageView shieldImageView2 = new ImageView(silverShieldImage);
        shieldImageView2.setFitHeight(75);
        shieldImageView2.setPreserveRatio(true);
        silverShieldButton.setGraphic(shieldImageView2);
        HBox shieldImageView2HBox = new HBox(20, silverShieldButton, new Text("2"));

        ImageView shieldImageView3 = new ImageView(goldShieldImage);
        shieldImageView3.setFitHeight(75);
        shieldImageView3.setPreserveRatio(true);
        goldShieldButton.setGraphic(shieldImageView3);
        HBox shieldImageView3HBox = new HBox(20, goldShieldButton, new Text("3"));

        ImageView shieldImageView4 = new ImageView(diamondShieldImage);
        shieldImageView4.setFitHeight(75);
        shieldImageView4.setPreserveRatio(true);
        diamondShieldButton.setGraphic(shieldImageView4);
        HBox shieldImageView4HBox = new HBox(20, diamondShieldButton, new Text("4"));

        /* create helmet image views */

        ImageView helmetImageView1 = new ImageView(bronzeHelmetImage);
        helmetImageView1.setFitHeight(75);
        helmetImageView1.setPreserveRatio(true);
        bronzeHelmetButton.setGraphic(helmetImageView1);
        HBox helmetImageView1HBox = new HBox(20, bronzeHelmetButton, new Text("1"));

        ImageView helmetImageView2 = new ImageView(silverHelmetImage);
        helmetImageView2.setFitHeight(75);
        helmetImageView2.setPreserveRatio(true);
        silverHelmetButton.setGraphic(helmetImageView2);
        HBox helmetImageView2HBox = new HBox(20, silverHelmetButton, new Text("2"));

        ImageView helmetImageView3 = new ImageView(goldHelmetImage);
        helmetImageView3.setFitHeight(75);
        helmetImageView3.setPreserveRatio(true);
        goldHelmetButton.setGraphic(helmetImageView3);
        HBox helmetImageView3HBox = new HBox(20, goldHelmetButton, new Text("3"));

        ImageView helmetImageView4 = new ImageView(diamondHelmetImage);
        helmetImageView4.setFitHeight(75);
        helmetImageView4.setPreserveRatio(true);
        diamondHelmetButton.setGraphic(helmetImageView4);
        HBox helmetImageView4HBox = new HBox(20, diamondHelmetButton, new Text("4"));

        /* create chest image views */

        ImageView chestImageView1 = new ImageView(bronzeChestImage);
        chestImageView1.setFitHeight(75);
        chestImageView1.setPreserveRatio(true);
        bronzeChestButton.setGraphic(chestImageView1);
        HBox chestImageView1HBox = new HBox(20, bronzeChestButton, new Text("1"));

        ImageView chestImageView2 = new ImageView(silverChestImage);
        chestImageView2.setFitHeight(75);
        chestImageView2.setPreserveRatio(true);
        silverChestButton.setGraphic(chestImageView2);
        HBox chestImageView2HBox = new HBox(20, silverChestButton, new Text("2"));

        ImageView chestImageView3 = new ImageView(goldChestImage);
        chestImageView3.setFitHeight(75);
        chestImageView3.setPreserveRatio(true);
        goldChestButton.setGraphic(chestImageView3);
        HBox chestImageView3HBox = new HBox(20, goldChestButton, new Text("3"));

        ImageView chestImageView4 = new ImageView(diamondChestImage);
        chestImageView4.setFitHeight(75);
        chestImageView4.setPreserveRatio(true);
        diamondChestButton.setGraphic(chestImageView4);
        HBox chestImageView4HBox = new HBox(20, diamondChestButton, new Text("4"));

        /* create item image views */

        ImageView itemImageView1 = new ImageView(healthPotImage);
        itemImageView1.setFitHeight(75);
        itemImageView1.setPreserveRatio(true);
        healthPotButton.setGraphic(itemImageView1);
        HBox itemImageView1HBox = new HBox(20, healthPotButton, new Text("Health Pot"));

        ImageView itemImageView2 = new ImageView(attackPotImage);
        itemImageView2.setFitHeight(75);
        itemImageView2.setPreserveRatio(true);
        attackPotButton.setGraphic(itemImageView2);
        HBox itemImageView2HBox = new HBox(20, attackPotButton, new Text("Attack Pot"));

        ImageView itemImageView3 = new ImageView(defensePotImage);
        itemImageView3.setFitHeight(75);
        itemImageView3.setPreserveRatio(true);
        defensePotButton.setGraphic(itemImageView3);
        HBox itemImageView3HBox = new HBox(20, defensePotButton, new Text("Defense Pot"));

        ImageView itemImageView4 = new ImageView(bombImage);
        itemImageView4.setFitHeight(75);
        itemImageView4.setPreserveRatio(true);
        bombButton.setGraphic(itemImageView4);
        HBox itemImageView4HBox = new HBox(20, bombButton, new Text("Bomb"));

        VBox swords = new VBox(20, swordImageView1HBox, swordImageView2HBox, swordImageView3HBox, swordImageView4HBox);
        VBox shields = new VBox(20, shieldImageView1HBox, shieldImageView2HBox, shieldImageView3HBox, shieldImageView4HBox);
        VBox helmets = new VBox(20, helmetImageView1HBox, helmetImageView2HBox, helmetImageView3HBox, helmetImageView4HBox);
        VBox chests = new VBox(20, chestImageView1HBox, chestImageView2HBox, chestImageView3HBox, chestImageView4HBox);
        VBox items = new VBox(20, itemImageView1HBox, itemImageView2HBox, itemImageView3HBox, itemImageView4HBox);
        swords.setAlignment(Pos.CENTER);
        shields.setAlignment(Pos.CENTER);
        helmets.setAlignment(Pos.CENTER);
        chests.setAlignment(Pos.CENTER);
        items.setAlignment(Pos.CENTER);

        HBox grid = new HBox(20, swords, shields, helmets, chests, items);
        grid.setAlignment(Pos.CENTER);

        bp.setCenter(grid);

        /* message text */

        HBox messageHBox = new HBox(20, itemSelectMessageText);
        messageHBox.setAlignment(Pos.CENTER);
        messageHBox.setPadding(new Insets(20, 20, 20, 20));

        bp.setTop(messageHBox);

        /* roll button */

        HBox rollHBox = new HBox(20, rollButton);
        rollHBox.setAlignment(Pos.CENTER);
        rollHBox.setPadding(new Insets(20, 20, 20, 20));

        bp.setBottom(rollHBox);

        /* adding items to available items - added code */

        availableItems.add(bronzeSwordButton);
        availableItems.add(silverSwordButton);
        availableItems.add(goldSwordButton);
        availableItems.add(diamondSwordButton);

        availableItems.add(bronzeShieldButton);
        availableItems.add(silverShieldButton);
        availableItems.add(goldShieldButton);
        availableItems.add(diamondShieldButton);

        availableItems.add(bronzeHelmetButton);
        availableItems.add(silverHelmetButton);
        availableItems.add(goldHelmetButton);
        availableItems.add(diamondHelmetButton);

        availableItems.add(bronzeChestButton);
        availableItems.add(silverChestButton);
        availableItems.add(goldChestButton);
        availableItems.add(diamondChestButton);

        availableItems.add(healthPotButton);
        availableItems.add(attackPotButton);
        availableItems.add(defensePotButton);
        availableItems.add(bombButton);

        disableAllButtons();

        setItemSelectButtonActions();

        itemSelectScene = new Scene(bp, windowWidth,windowHeight);
        sceneMap.put("itemSelect", itemSelectScene);
    }

    private void createPlayScene(Stage primaryStage) {
        BorderPane bp = new BorderPane();

        /* create sword image views */

        swordImageView1.setFitHeight(75);
        swordImageView1.setPreserveRatio(true);

        swordImageView2.setFitHeight(75);
        swordImageView2.setPreserveRatio(true);

        swordImageView3.setFitHeight(75);
        swordImageView3.setPreserveRatio(true);

        swordImageView4.setFitHeight(75);
        swordImageView4.setPreserveRatio(true);

        /* create shield image views */

        shieldImageView1.setFitHeight(75);
        shieldImageView1.setPreserveRatio(true);

        shieldImageView2.setFitHeight(75);
        shieldImageView2.setPreserveRatio(true);

        shieldImageView3.setFitHeight(75);
        shieldImageView3.setPreserveRatio(true);

        shieldImageView4.setFitHeight(75);
        shieldImageView4.setPreserveRatio(true);

        /* create helmet image views */

        helmetImageView1.setFitHeight(75);
        helmetImageView1.setPreserveRatio(true);

        helmetImageView2.setFitHeight(75);
        helmetImageView2.setPreserveRatio(true);

        helmetImageView3.setFitHeight(75);
        helmetImageView3.setPreserveRatio(true);

        helmetImageView4.setFitHeight(75);
        helmetImageView4.setPreserveRatio(true);

        /* create chest image views */

        chestImageView1.setFitHeight(75);
        chestImageView1.setPreserveRatio(true);

        chestImageView2.setFitHeight(75);
        chestImageView2.setPreserveRatio(true);

        chestImageView3.setFitHeight(75);
        chestImageView3.setPreserveRatio(true);

        chestImageView4.setFitHeight(75);
        chestImageView4.setPreserveRatio(true);

        /* create item image views */

        itemImageView1.setFitHeight(75);
        itemImageView1.setPreserveRatio(true);

        itemImageView2.setFitHeight(75);
        itemImageView2.setPreserveRatio(true);

        itemImageView3.setFitHeight(75);
        itemImageView3.setPreserveRatio(true);

        itemImageView4.setFitHeight(75);
        itemImageView4.setPreserveRatio(true);

        /* lay out scene */

        /* player 1 */

        VBox player1Info = new VBox(20, nameText1, healthText1);
        player1Info.setAlignment(Pos.CENTER);
        Separator player1VerticalSeparator1 = new Separator(Orientation.VERTICAL);
        // Change text number to reflect stats
        HBox player1Sword = new HBox(20, swordImageView1, swordText1);
        HBox player1Shield = new HBox(20, shieldImageView1, shieldText1);
        HBox player1Helmet = new HBox(20, helmetImageView1, helmetText1);
        HBox player1Chest = new HBox(20, chestImageView1, chestText1);
        HBox player1Item = new HBox(20, itemImageView1, itemText1);
        HBox player1Stats = new HBox(20, player1Sword, player1Shield, player1Helmet, player1Chest, player1Item);
        player1Stats.setAlignment(Pos.CENTER);
        Separator player1VerticalSeparator2 = new Separator(Orientation.VERTICAL);
        HBox player1Ready = new HBox(20, readyText1);
        player1Ready.setAlignment(Pos.CENTER);
        HBox player1 = new HBox(20, player1Info, player1VerticalSeparator1, player1Stats/*, player1VerticalSeparator2, player1Ready*/);
        player1.setPadding(new Insets(20, 0, 0, 0));
        player1.setAlignment(Pos.CENTER);

        Separator horizontalSeparator1 = new Separator(Orientation.HORIZONTAL);

        /* player 2 */

        VBox player2Info = new VBox(20, nameText2, healthText2);
        player2Info.setAlignment(Pos.CENTER);
        Separator player2VerticalSeparator1 = new Separator(Orientation.VERTICAL);
        // Change text number to reflect stats
        HBox player2Sword = new HBox(20, swordImageView2, swordText2);
        HBox player2Shield = new HBox(20, shieldImageView2, shieldText2);
        HBox player2Helmet = new HBox(20, helmetImageView2, helmetText2);
        HBox player2Chest = new HBox(20, chestImageView2, chestText2);
        HBox player2Item = new HBox(20, itemImageView2, itemText2);
        HBox player2Stats = new HBox(20, player2Sword, player2Shield, player2Helmet, player2Chest, player2Item);
        player2Stats.setAlignment(Pos.CENTER);
        Separator player2VerticalSeparator2 = new Separator(Orientation.VERTICAL);
        HBox player2Ready = new HBox(20, readyText2);
        player2Ready.setAlignment(Pos.CENTER);
        HBox player2 = new HBox(20, player2Info, player2VerticalSeparator1, player2Stats/*, player2VerticalSeparator2, player2Ready*/);
        player2.setAlignment(Pos.CENTER);

        Separator horizontalSeparator2 = new Separator(Orientation.HORIZONTAL);

        /* player 3 */

        VBox player3Info = new VBox(20, nameText3, healthText3);
        player3Info.setAlignment(Pos.CENTER);
        Separator player3VerticalSeparator1 = new Separator(Orientation.VERTICAL);
        // Change text number to reflect stats
        HBox player3Sword = new HBox(20, swordImageView3, swordText3);
        HBox player3Shield = new HBox(20, shieldImageView3, shieldText3);
        HBox player3Helmet = new HBox(20, helmetImageView3, helmetText3);
        HBox player3Chest = new HBox(20, chestImageView3, chestText3);
        HBox player3Item = new HBox(20, itemImageView3, itemText3);
        HBox player3Stats = new HBox(20, player3Sword, player3Shield, player3Helmet, player3Chest, player3Item);
        player3Stats.setAlignment(Pos.CENTER);
        Separator player3VerticalSeparator2 = new Separator(Orientation.VERTICAL);
        HBox player3Ready = new HBox(20, readyText3);
        player3Ready.setAlignment(Pos.CENTER);
        HBox player3 = new HBox(20, player3Info, player3VerticalSeparator1, player3Stats/*, player3VerticalSeparator2, player3Ready*/);
        player3.setAlignment(Pos.CENTER);

        Separator horizontalSeparator3 = new Separator(Orientation.HORIZONTAL);

        /* player 4 */

        VBox player4Info = new VBox(20, nameText4, healthText4);
        player4Info.setAlignment(Pos.CENTER);
        Separator player4VerticalSeparator1 = new Separator(Orientation.VERTICAL);
        // Change text number to reflect stats
        HBox player4Sword = new HBox(20, swordImageView4, swordText4);
        HBox player4Shield = new HBox(20, shieldImageView4, shieldText4);
        HBox player4Helmet = new HBox(20, helmetImageView4, helmetText4);
        HBox player4Chest = new HBox(20, chestImageView4, chestText4);
        HBox player4Item = new HBox(20, itemImageView4, itemText4);
        HBox player4Stats = new HBox(20, player4Sword, player4Shield, player4Helmet, player4Chest, player4Item);
        player4Stats.setAlignment(Pos.CENTER);
        Separator player4VerticalSeparator2 = new Separator(Orientation.VERTICAL);
        HBox player4Ready = new HBox(20, readyText4);
        player4Ready.setAlignment(Pos.CENTER);
        HBox player4 = new HBox(20, player4Info, player4VerticalSeparator1, player4Stats/*, player4VerticalSeparator2, player4Ready*/);
        player4.setAlignment(Pos.CENTER);

        Separator horizontalSeparator4 = new Separator(Orientation.HORIZONTAL);

        VBox playerInfo = new VBox (20, player1, horizontalSeparator1, player2, horizontalSeparator2, player3, horizontalSeparator3, player4, horizontalSeparator4);

        bp.setTop(playerInfo);

        /* bottom section */

        BorderPane bottomPane = new BorderPane();

        /* make move box */

        insertPlayerTextField.setPromptText("player to attack");
        insertPlayerTextField.setFocusTraversable(false);
        HBox insertPlayer = new HBox(20, playerText, insertPlayerTextField);
        attackButton = new Button("Attack");
        defendButton = new Button("Defend");
        useItemButton = new Button("Use Item");
        HBox moveButtons = new HBox(20, attackButton, defendButton, useItemButton);
        VBox makeMove = new VBox(20, insertPlayer, moveButtons);
        makeMove.setPadding(new Insets(0, 0, 20, 20));
        bottomPane.setLeft(makeMove);

        /* message area */

        bottomPane.setCenter(playMessageText);

        /* exit button */

        exitButton = new Button("Exit");
        VBox exitVBox = new VBox(20, exitButton);
        exitVBox.setPadding(new Insets(0, 20, 20, 0));
        exitVBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomPane.setRight(exitVBox);

        /*-------------*/

        bp.setBottom(bottomPane);

        setPlayButtonActions();

        playScene = new Scene(bp, windowWidth,windowHeight);
        sceneMap.put("play", playScene);
    }

    public void disableItemButtons(boolean bool) {
    	bronzeSwordButton.setDisable(bool);
    	silverSwordButton.setDisable(bool);
    	goldSwordButton.setDisable(bool);
    	diamondSwordButton.setDisable(bool);
    	
    	bronzeShieldButton.setDisable(bool);
    	silverShieldButton.setDisable(bool);
    	goldShieldButton.setDisable(bool);
    	diamondShieldButton.setDisable(bool);
    	
    	bronzeHelmetButton.setDisable(bool);
    	silverHelmetButton.setDisable(bool);
    	goldHelmetButton.setDisable(bool);
    	diamondHelmetButton.setDisable(bool);
    	
    	bronzeChestButton.setDisable(bool);
    	silverChestButton.setDisable(bool);
    	goldChestButton.setDisable(bool);
    	diamondChestButton.setDisable(bool);
    	
    	healthPotButton.setDisable(bool);
    	attackPotButton.setDisable(bool);
    	defensePotButton.setDisable(bool);
    	bombButton.setDisable(bool);
    }
    
    public void disableMoveButtons(boolean bool) {
    	attackButton.setDisable(bool);
    	defendButton.setDisable(bool);
    	useItemButton.setDisable(bool);
    }
    
    public static void main(String[] args) {

        launch(args);
    }
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneMap = new HashMap<>();
        createItemSelectScene(primaryStage);
        createPlayScene(primaryStage);
        createConnect(primaryStage);
        this.primaryStage = primaryStage;

        /* change this to itemSelectScene */
        //primaryStage.setScene(itemSelectScene);
        primaryStage.setScene(connectScene);
        primaryStage.show();

        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());

        players.get(0).setId("1");
        players.get(1).setId("2");
        players.get(2).setId("3");
        players.get(3).setId("4");

    }

    private ClientRPS createClient() {

        return new ClientRPS( ipAdd, portNum, data-> {
            Platform.runLater(()->{

                processInput( data.toString() );

            });
        });
    }

    //Receiving From Client

    public void processInput( String data) {
        String[] tokens = data.split("-");
        String message = "";

        switch(tokens[0]) {

            case "id":
                setID(tokens[1]);
                sendUsername(enterNameTextField.getText());
                break;
            case "connected":
                setUsername(tokens[1],tokens[2]);
                break;
            case "roll":
                setRoll(tokens[1], tokens[2]);
                break;
            case "choose":
		        choose();
                break;
            case "picked":
                setPick(tokens[1], tokens[2], tokens[3]);
                break;
            case "start":
                start();
                break;
            case "atkStat":
                setAtkStat(tokens[1],tokens[2]);
                break;
            case "defStat":
                setDefStat(tokens[1],tokens[2]);
                break;
            case "health":
                updateHealth(tokens[1], tokens[2]);
                break;
            case "nextRound":
                startNextRound();
                break;
            case "winner":
                winner(tokens[1]);
                break;
            case "text":
                receiveMessage(tokens[1]);
                break;
        }

    }

    public void setID(String id) {
        idNumStr = id;
        idNumInt = Integer.parseInt(id);
    }

    public void setUsername(String id, String username) {
        int idInt = Integer.parseInt(id);
        players.get(idInt-1).setUsername(username);
    }

    public void setRoll(String id, String numRolledStr) {
        int idInt = Integer.parseInt(id);
        int numRolled = Integer.parseInt(numRolledStr);
        players.get(idInt-1).setRoll(numRolled);
    }

    public void choose() {
        //enable buttons for items still available
        for (Button button : availableItems) {
            button.setDisable(false);
        }
    }

    public void setPick(String id, String pickType, String pick) {
        int idInt = Integer.parseInt(id);

        removeFromAvailableItems(pickType, pick);

        switch(pickType) {
            case "sword":
                players.get(idInt-1).setWeapon(pick);
                break;
            case "helmet":
                players.get(idInt-1).setHelmet(pick);
                break;
            case "chest":
                players.get(idInt-1).setChest(pick);
                break;
            case "shield":
                players.get(idInt-1).setShield(pick);
                break;
            case "item":
                players.get(idInt-1).setItem(pick);
                break;
        }
    }

    public void start() {
        //Enable buttons to make a move

        /* set username texts */
        nameText1.setText(players.get(0).getUsername());
        nameText2.setText(players.get(1).getUsername());
        nameText3.setText(players.get(2).getUsername());
        nameText4.setText(players.get(3).getUsername());
        makePlayerNameBold();
        /* set health texts */
        healthText1.setText("Health: " + players.get(0).getHealth());
        healthText2.setText("Health: " + players.get(1).getHealth());
        healthText3.setText("Health: " + players.get(2).getHealth());
        healthText4.setText("Health: " + players.get(3).getHealth());
        /* set correct images */
        swordImageView1.setImage(swordImage(players.get(0).getWeapon()));
        swordImageView2.setImage(swordImage(players.get(1).getWeapon()));
        swordImageView3.setImage(swordImage(players.get(2).getWeapon()));
        swordImageView4.setImage(swordImage(players.get(3).getWeapon()));
        shieldImageView1.setImage(shieldImage(players.get(0).getShield()));
        shieldImageView2.setImage(shieldImage(players.get(1).getShield()));
        shieldImageView3.setImage(shieldImage(players.get(2).getShield()));
        shieldImageView4.setImage(shieldImage(players.get(3).getShield()));
        helmetImageView1.setImage(helmetImage(players.get(0).getHelmet()));
        helmetImageView2.setImage(helmetImage(players.get(1).getHelmet()));
        helmetImageView3.setImage(helmetImage(players.get(2).getHelmet()));
        helmetImageView4.setImage(helmetImage(players.get(3).getHelmet()));
        chestImageView1.setImage(chestImage(players.get(0).getChest()));
        chestImageView2.setImage(chestImage(players.get(1).getChest()));
        chestImageView3.setImage(chestImage(players.get(2).getChest()));
        chestImageView4.setImage(chestImage(players.get(3).getChest()));
        itemImageView1.setImage(itemImage(players.get(0).getItem()));
        itemImageView2.setImage(itemImage(players.get(1).getItem()));
        itemImageView3.setImage(itemImage(players.get(2).getItem()));
        itemImageView4.setImage(itemImage(players.get(3).getItem()));
        /* set correct stat texts */
        swordText1.setText(players.get(0).getWeapon());
        swordText2.setText(players.get(1).getWeapon());
        swordText3.setText(players.get(2).getWeapon());
        swordText4.setText(players.get(3).getWeapon());
        shieldText1.setText(players.get(0).getShield());
        shieldText2.setText(players.get(1).getShield());
        shieldText3.setText(players.get(2).getShield());
        shieldText4.setText(players.get(3).getShield());
        helmetText1.setText(players.get(0).getHelmet());
        helmetText2.setText(players.get(1).getHelmet());
        helmetText3.setText(players.get(2).getHelmet());
        helmetText4.setText(players.get(3).getHelmet());
        chestText1.setText(players.get(0).getChest());
        chestText2.setText(players.get(1).getChest());
        chestText3.setText(players.get(2).getChest());
        chestText4.setText(players.get(3).getChest());
        itemText1.setText(players.get(0).getItem());
        itemText2.setText(players.get(1).getItem());
        itemText3.setText(players.get(2).getItem());
        itemText4.setText(players.get(3).getItem());
        /* show new scene */
        primaryStage.setScene(sceneMap.get("play"));
        primaryStage.show();
    }

    public void setAtkStat(String id, String atkStat) {
        int atkInt = Integer.parseInt(atkStat);
        int idInt = Integer.parseInt(id);
        players.get(idInt-1).setAtk(atkInt);
    }

    public void setDefStat(String id, String defStat) {
        int defInt = Integer.parseInt(defStat);
        int idInt = Integer.parseInt(id);
        players.get(idInt-1).setDef(defInt);
    }

    public void updateHealth(String id, String health) {
        int idInt = Integer.parseInt(id);
        int healthInt = Integer.parseInt(health);
        players.get(idInt-1).setHealth(healthInt);
        if (idInt == 1) {
            healthText1.setText("Health: " + players.get(0).getHealth());
        }
        else if (idInt == 2) {
            healthText2.setText("Health: " + players.get(1).getHealth());
        }
        else if (idInt == 3) {
            healthText3.setText("Health: " + players.get(2).getHealth());
        }
        else if (idInt == 4) {
            healthText4.setText("Health: " + players.get(3).getHealth());
        }
    }

    public void startNextRound() {
        //Enable buttons to make a move
        attackButton.setDisable(false);
        defendButton.setDisable(false);
        if (didUseItem == false) {
            useItemButton.setDisable(false);
        }
    }

    public void winner(String winnerID) {
        if(winnerID.equals("0")) {
            //No winner - everyone died
            playMessageString = "No winner";
        }
        else {
            //Display winner
            if (winnerID.equals("1")) {
                playMessageString = players.get(0).getUsername() + "won.";
            }
            else if (winnerID.equals("2")) {
                playMessageString = players.get(1).getUsername() + "won.";
            }
            else if (winnerID.equals("3")) {
                playMessageString = players.get(2).getUsername() + "won.";
            }
            else if (winnerID.equals("4")) {
                playMessageString = players.get(3).getUsername() + "won.";
            }
        }
        playMessageText.setText(playMessageString);
    }

    public void receiveMessage(String message) {
        playMessageString =  playMessageString + message + "\n";
        playMessageText.setText(playMessageString);
    }



    //Sending To Client

    public void sendUsername(String username) {
        this.username = username;
        players.get(idNumInt-1).setUsername(username);
        try {
            conn.send("connect-" + idNumStr + "-" + username);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void rollDie() {
        Random random = new Random();
        String numRolled = Integer.toString(random.nextInt(6) + 1);

        try {
            conn.send("roll-" + idNumStr + "-" + numRolled);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        rollButton.setDisable(true);
    }

    public void pickItem(String pickType, String pick) {
        setPick(idNumStr, pickType, pick);
        try {
            conn.send("picked-" + idNumStr + "-" + pickType + "-" + pick);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        removeTypeFromAvailable(pickType);
        disableAllButtons();
    }

    public void attack(String playerToAttack) {

        try {
            conn.send("attack-" + idNumStr + "-" + usernameToID(playerToAttack));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String usernameToID(String name) {
		for (int i = 0; i < 4; i++) {
			if (players.get(i).getUsername().equals(name))
				return players.get(i).getId();
		}
		return "";
	}

    public void defend() {
        try {
            conn.send("defend-" + idNumStr);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void useItem(String itemUsed) {
        //Make sure player isn't able to reuse item

        try {
            conn.send("item-" + idNumStr + "-" + itemUsed);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void quit() {
        try {
            conn.send("quit-"+ idNumStr);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
	    
	try {
            conn.closeConn();
            Platform.exit();
    	}
    	catch(Exception e) {
            System.out.println("Failed to quit properly");
    	}
    }

    public void removeFromAvailableItems(String type, String rarity) {
        if (type.equals("sword")) {
            if (rarity.equals("1")) {
                availableItems.remove(bronzeSwordButton);
            }
            else if (rarity.equals("2")) {
                availableItems.remove(silverSwordButton);
            }
            else if (rarity.equals("3")) {
                availableItems.remove(goldSwordButton);
            }
            else if (rarity.equals("4")) {
                availableItems.remove(diamondSwordButton);
            }
        }
        else if (type.equals("shield")) {
            if (rarity.equals("1")) {
                availableItems.remove(bronzeShieldButton);
            }
            else if (rarity.equals("2")) {
                availableItems.remove(silverShieldButton);
            }
            else if (rarity.equals("3")) {
                availableItems.remove(goldShieldButton);
            }
            else if (rarity.equals("4")) {
                availableItems.remove(diamondShieldButton);
            }
        }
        else if (type.equals("helmet")) {
            if (rarity.equals("1")) {
                availableItems.remove(bronzeHelmetButton);
            }
            else if (rarity.equals("2")) {
                availableItems.remove(silverHelmetButton);
            }
            else if (rarity.equals("3")) {
                availableItems.remove(goldHelmetButton);
            }
            else if (rarity.equals("4")) {
                availableItems.remove(diamondHelmetButton);
            }
        }
        else if (type.equals("chest")) {
            if (rarity.equals("1")) {
                availableItems.remove(bronzeChestButton);
            }
            else if (rarity.equals("2")) {
                availableItems.remove(silverChestButton);
            }
            else if (rarity.equals("3")) {
                availableItems.remove(goldChestButton);
            }
            else if (rarity.equals("4")) {
                availableItems.remove(diamondChestButton);
            }
        }
        else if (type.equals("item")) {
            if (rarity.equals("hPot")) {
                availableItems.remove(healthPotButton);
            }
            else if (rarity.equals("aPot")) {
                availableItems.remove(attackPotButton);
            }
            else if (rarity.equals("dPot")) {
                availableItems.remove(defensePotButton);
            }
            else if (rarity.equals("bomb")) {
                availableItems.remove(bombButton);
            }
        }
    }

    public void removeTypeFromAvailable(String type) {
        if (type.equals("sword")) {
            availableItems.remove(bronzeSwordButton);
            availableItems.remove(silverSwordButton);
            availableItems.remove(goldSwordButton);
            availableItems.remove(diamondSwordButton);
        }
        else if (type.equals("shield")) {
            availableItems.remove(bronzeShieldButton);
            availableItems.remove(silverShieldButton);
            availableItems.remove(goldShieldButton);
            availableItems.remove(diamondShieldButton);
        }
        else if (type.equals("helmet")) {
            availableItems.remove(bronzeHelmetButton);
            availableItems.remove(silverHelmetButton);
            availableItems.remove(goldHelmetButton);
            availableItems.remove(diamondHelmetButton);
        }
        else if (type.equals("chest")) {
            availableItems.remove(bronzeChestButton);
            availableItems.remove(silverChestButton);
            availableItems.remove(goldChestButton);
            availableItems.remove(diamondChestButton);
        }
        else if (type.equals("item")) {
            availableItems.remove(healthPotButton);
            availableItems.remove(attackPotButton);
            availableItems.remove(defensePotButton);
            availableItems.remove(bombButton);
        }
    }

    public void disableAllButtons() {

        /* disabling buttons by default */

        bronzeSwordButton.setDisable(true);
        silverSwordButton.setDisable(true);
        goldSwordButton.setDisable(true);
        diamondSwordButton.setDisable(true);

        bronzeShieldButton.setDisable(true);
        silverShieldButton.setDisable(true);
        goldShieldButton.setDisable(true);
        diamondShieldButton.setDisable(true);

        bronzeHelmetButton.setDisable(true);
        silverHelmetButton.setDisable(true);
        goldHelmetButton.setDisable(true);
        diamondHelmetButton.setDisable(true);

        bronzeChestButton.setDisable(true);
        silverChestButton.setDisable(true);
        goldChestButton.setDisable(true);
        diamondChestButton.setDisable(true);

        healthPotButton.setDisable(true);
        attackPotButton.setDisable(true);
        defensePotButton.setDisable(true);
        bombButton.setDisable(true);

    }

    public Image swordImage(String sword) {
        if (sword.equals("1")) {
            return bronzeSwordImage;
        }
        else if (sword.equals("2")) {
            return silverSwordImage;
        }
        else if (sword.equals("3")) {
            return goldSwordImage;
        }
        else if (sword.equals("4")) {
            return diamondSwordImage;
        }
        return null;
    }

    public Image shieldImage(String shield) {
        if (shield.equals("1")) {
            return bronzeShieldImage;
        }
        else if (shield.equals("2")) {
            return silverShieldImage;
        }
        else if (shield.equals("3")) {
            return goldShieldImage;
        }
        else if (shield.equals("4")) {
            return diamondShieldImage;
        }
        return null;
    }

    public Image helmetImage(String helmet) {
        if (helmet.equals("1")) {
            return bronzeHelmetImage;
        }
        else if (helmet.equals("2")) {
            return silverHelmetImage;
        }
        else if (helmet.equals("3")) {
            return goldHelmetImage;
        }
        else if (helmet.equals("4")) {
            return diamondHelmetImage;
        }
        return null;
    }

    public Image chestImage(String chest) {
        if (chest.equals("1")) {
            return bronzeChestImage;
        }
        else if (chest.equals("2")) {
            return silverChestImage;
        }
        else if (chest.equals("3")) {
            return goldChestImage;
        }
        else if (chest.equals("4")) {
            return diamondChestImage;
        }
        return null;
    }

    public Image itemImage(String item) {
        if (item.equals("hPot")) {
            return healthPotImage;
        }
        else if (item.equals("aPot")) {
            return attackPotImage;
        }
        else if (item.equals("dPot")) {
            return defensePotImage;
        }
        else if (item.equals("bomb")) {
            return bombImage;
        }
        return null;
    }

    public void makePlayerNameBold() {
        if (idNumInt == 1) {
            nameText1.setText(nameText1.getText().concat(" (You)"));
        }
        else if (idNumInt == 2) {
            nameText2.setText(nameText2.getText().concat(" (You)"));
        }
        else if (idNumInt == 3) {
            nameText3.setText(nameText3.getText().concat(" (You)"));
        }
        else if (idNumInt == 4) {
            nameText4.setText(nameText4.getText().concat(" (You)"));
        }
    }

}
