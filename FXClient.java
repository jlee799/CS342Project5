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

    String ipAdd;
    int portNum;
    String idNumStr;
    int idNumInt;
    private ClientNetworkConnectionRPS conn = null;
    String username;
    ArrayList<Player> players = new ArrayList<>();

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

    Text playerText = new Text("Player: ");
    TextField insertPlayerTextField = new TextField();

    Button attackButton;
    Button defendButton;
    Button useItemButton;

    Text playMessageText = new Text("Test Message");

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
    }

    private void setPlayButtonActions() {
        attackButton.setOnAction( event -> {
		String playerToAttack = insertPlayerTextField.getText();
        });

        defendButton.setOnAction( event -> {
		defend();
        });

        useItemButton.setOnAction( event -> {
		
        });

        exitButton.setOnAction( event -> {
		quit();
        });
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
                sendUsername(enterNameTextField.getText());
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
        HBox swordImageView1HBox = new HBox(20, bronzeSwordButton, new Text("(1)"));

        ImageView swordImageView2 = new ImageView(silverSwordImage);
        swordImageView2.setFitHeight(75);
        swordImageView2.setPreserveRatio(true);
        silverSwordButton.setGraphic(swordImageView2);
        HBox swordImageView2HBox = new HBox(20, silverSwordButton, new Text("(1)"));

        ImageView swordImageView3 = new ImageView(goldSwordImage);
        swordImageView3.setFitHeight(75);
        swordImageView3.setPreserveRatio(true);
        goldSwordButton.setGraphic(swordImageView3);
        HBox swordImageView3HBox = new HBox(20, goldSwordButton, new Text("(1)"));

        ImageView swordImageView4 = new ImageView(diamondSwordImage);
        swordImageView4.setFitHeight(75);
        swordImageView4.setPreserveRatio(true);
        diamondSwordButton.setGraphic(swordImageView4);
        HBox swordImageView4HBox = new HBox(20, diamondSwordButton, new Text("(1)"));

        /* create shield image views */

        ImageView shieldImageView1 = new ImageView(bronzeShieldImage);
        shieldImageView1.setFitHeight(75);
        shieldImageView1.setPreserveRatio(true);
        bronzeShieldButton.setGraphic(shieldImageView1);
        HBox shieldImageView1HBox = new HBox(20, bronzeShieldButton, new Text("(1)"));

        ImageView shieldImageView2 = new ImageView(silverShieldImage);
        shieldImageView2.setFitHeight(75);
        shieldImageView2.setPreserveRatio(true);
        silverShieldButton.setGraphic(shieldImageView2);
        HBox shieldImageView2HBox = new HBox(20, silverShieldButton, new Text("(1)"));

        ImageView shieldImageView3 = new ImageView(goldShieldImage);
        shieldImageView3.setFitHeight(75);
        shieldImageView3.setPreserveRatio(true);
        goldShieldButton.setGraphic(shieldImageView3);
        HBox shieldImageView3HBox = new HBox(20, goldShieldButton, new Text("(1)"));

        ImageView shieldImageView4 = new ImageView(diamondShieldImage);
        shieldImageView4.setFitHeight(75);
        shieldImageView4.setPreserveRatio(true);
        diamondShieldButton.setGraphic(shieldImageView4);
        HBox shieldImageView4HBox = new HBox(20, diamondShieldButton, new Text("(1)"));

        /* create helmet image views */

        ImageView helmetImageView1 = new ImageView(bronzeHelmetImage);
        helmetImageView1.setFitHeight(75);
        helmetImageView1.setPreserveRatio(true);
        bronzeHelmetButton.setGraphic(helmetImageView1);
        HBox helmetImageView1HBox = new HBox(20, bronzeHelmetButton, new Text("(1)"));

        ImageView helmetImageView2 = new ImageView(silverHelmetImage);
        helmetImageView2.setFitHeight(75);
        helmetImageView2.setPreserveRatio(true);
        silverHelmetButton.setGraphic(helmetImageView2);
        HBox helmetImageView2HBox = new HBox(20, silverHelmetButton, new Text("(1)"));

        ImageView helmetImageView3 = new ImageView(goldHelmetImage);
        helmetImageView3.setFitHeight(75);
        helmetImageView3.setPreserveRatio(true);
        goldHelmetButton.setGraphic(helmetImageView3);
        HBox helmetImageView3HBox = new HBox(20, goldHelmetButton, new Text("(1)"));

        ImageView helmetImageView4 = new ImageView(diamondHelmetImage);
        helmetImageView4.setFitHeight(75);
        helmetImageView4.setPreserveRatio(true);
        diamondHelmetButton.setGraphic(helmetImageView4);
        HBox helmetImageView4HBox = new HBox(20, diamondHelmetButton, new Text("(1)"));

        /* create chest image views */

        ImageView chestImageView1 = new ImageView(bronzeChestImage);
        chestImageView1.setFitHeight(75);
        chestImageView1.setPreserveRatio(true);
        bronzeChestButton.setGraphic(chestImageView1);
        HBox chestImageView1HBox = new HBox(20, bronzeChestButton, new Text("(1)"));

        ImageView chestImageView2 = new ImageView(silverChestImage);
        chestImageView2.setFitHeight(75);
        chestImageView2.setPreserveRatio(true);
        silverChestButton.setGraphic(chestImageView2);
        HBox chestImageView2HBox = new HBox(20, silverChestButton, new Text("(1)"));

        ImageView chestImageView3 = new ImageView(goldChestImage);
        chestImageView3.setFitHeight(75);
        chestImageView3.setPreserveRatio(true);
        goldChestButton.setGraphic(chestImageView3);
        HBox chestImageView3HBox = new HBox(20, goldChestButton, new Text("(1)"));

        ImageView chestImageView4 = new ImageView(diamondChestImage);
        chestImageView4.setFitHeight(75);
        chestImageView4.setPreserveRatio(true);
        diamondChestButton.setGraphic(chestImageView4);
        HBox chestImageView4HBox = new HBox(20, diamondChestButton, new Text("(1)"));

        /* create item image views */

        ImageView itemImageView1 = new ImageView(healthPotImage);
        itemImageView1.setFitHeight(75);
        itemImageView1.setPreserveRatio(true);
        healthPotButton.setGraphic(itemImageView1);
        HBox itemImageView1HBox = new HBox(20, healthPotButton, new Text("(1)"));

        ImageView itemImageView2 = new ImageView(attackPotImage);
        itemImageView2.setFitHeight(75);
        itemImageView2.setPreserveRatio(true);
        attackPotButton.setGraphic(itemImageView2);
        HBox itemImageView2HBox = new HBox(20, attackPotButton, new Text("(1)"));

        ImageView itemImageView3 = new ImageView(defensePotImage);
        itemImageView3.setFitHeight(75);
        itemImageView3.setPreserveRatio(true);
        defensePotButton.setGraphic(itemImageView3);
        HBox itemImageView3HBox = new HBox(20, defensePotButton, new Text("(1)"));

        ImageView itemImageView4 = new ImageView(bombImage);
        itemImageView4.setFitHeight(75);
        itemImageView4.setPreserveRatio(true);
        bombButton.setGraphic(itemImageView4);
        HBox itemImageView4HBox = new HBox(20, bombButton, new Text("(1)"));

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

        setItemSelectButtonActions();

        itemSelectScene = new Scene(bp, windowWidth,windowHeight);
        sceneMap.put("itemSelect", itemSelectScene);
    }

    private void createPlayScene(Stage primaryStage) {
        BorderPane bp = new BorderPane();

        /* create sword image views */

        ImageView swordImageView1 = new ImageView(bronzeSwordImage);
        swordImageView1.setFitHeight(75);
        swordImageView1.setPreserveRatio(true);

        ImageView swordImageView2 = new ImageView(silverSwordImage);
        swordImageView2.setFitHeight(75);
        swordImageView2.setPreserveRatio(true);

        ImageView swordImageView3 = new ImageView(goldSwordImage);
        swordImageView3.setFitHeight(75);
        swordImageView3.setPreserveRatio(true);

        ImageView swordImageView4 = new ImageView(diamondSwordImage);
        swordImageView4.setFitHeight(75);
        swordImageView4.setPreserveRatio(true);

        /* create shield image views */

        ImageView shieldImageView1 = new ImageView(bronzeShieldImage);
        shieldImageView1.setFitHeight(75);
        shieldImageView1.setPreserveRatio(true);

        ImageView shieldImageView2 = new ImageView(silverShieldImage);
        shieldImageView2.setFitHeight(75);
        shieldImageView2.setPreserveRatio(true);

        ImageView shieldImageView3 = new ImageView(goldShieldImage);
        shieldImageView3.setFitHeight(75);
        shieldImageView3.setPreserveRatio(true);

        ImageView shieldImageView4 = new ImageView(diamondShieldImage);
        shieldImageView4.setFitHeight(75);
        shieldImageView4.setPreserveRatio(true);

        /* create helmet image views */

        ImageView helmetImageView1 = new ImageView(bronzeHelmetImage);
        helmetImageView1.setFitHeight(75);
        helmetImageView1.setPreserveRatio(true);

        ImageView helmetImageView2 = new ImageView(silverHelmetImage);
        helmetImageView2.setFitHeight(75);
        helmetImageView2.setPreserveRatio(true);

        ImageView helmetImageView3 = new ImageView(goldHelmetImage);
        helmetImageView3.setFitHeight(75);
        helmetImageView3.setPreserveRatio(true);

        ImageView helmetImageView4 = new ImageView(diamondHelmetImage);
        helmetImageView4.setFitHeight(75);
        helmetImageView4.setPreserveRatio(true);

        /* create chest image views */

        ImageView chestImageView1 = new ImageView(bronzeChestImage);
        chestImageView1.setFitHeight(75);
        chestImageView1.setPreserveRatio(true);

        ImageView chestImageView2 = new ImageView(silverChestImage);
        chestImageView2.setFitHeight(75);
        chestImageView2.setPreserveRatio(true);

        ImageView chestImageView3 = new ImageView(goldChestImage);
        chestImageView3.setFitHeight(75);
        chestImageView3.setPreserveRatio(true);

        ImageView chestImageView4 = new ImageView(diamondChestImage);
        chestImageView4.setFitHeight(75);
        chestImageView4.setPreserveRatio(true);

        /* create item image views */

        ImageView itemImageView1 = new ImageView(healthPotImage);
        itemImageView1.setFitHeight(75);
        itemImageView1.setPreserveRatio(true);

        ImageView itemImageView2 = new ImageView(attackPotImage);
        itemImageView2.setFitHeight(75);
        itemImageView2.setPreserveRatio(true);

        ImageView itemImageView3 = new ImageView(defensePotImage);
        itemImageView3.setFitHeight(75);
        itemImageView3.setPreserveRatio(true);

        ImageView itemImageView4 = new ImageView(bombImage);
        itemImageView4.setFitHeight(75);
        itemImageView4.setPreserveRatio(true);

        /* lay out scene */

        /* player 1 */

        VBox player1Info = new VBox(20, nameText1, healthText1);
        player1Info.setAlignment(Pos.CENTER);
        Separator player1VerticalSeparator1 = new Separator(Orientation.VERTICAL);
        // Change text number to reflect stats
        HBox player1Sword = new HBox(20, swordImageView1, new Text("(1)"));
        HBox player1Shield = new HBox(20, shieldImageView1, new Text("(2)"));
        HBox player1Helmet = new HBox(20, helmetImageView1, new Text("(3)"));
        HBox player1Chest = new HBox(20, chestImageView1, new Text("(4)"));
        HBox player1Item = new HBox(20, itemImageView1, new Text("(Gloves)"));
        HBox player1Stats = new HBox(20, player1Sword, player1Shield, player1Helmet, player1Chest, player1Item);
        player1Stats.setAlignment(Pos.CENTER);
        Separator player1VerticalSeparator2 = new Separator(Orientation.VERTICAL);
        HBox player1Ready = new HBox(20, readyText1);
        player1Ready.setAlignment(Pos.CENTER);
        HBox player1 = new HBox(20, player1Info, player1VerticalSeparator1, player1Stats, player1VerticalSeparator2, player1Ready);
        player1.setPadding(new Insets(20, 0, 0, 0));
        player1.setAlignment(Pos.CENTER);

        Separator horizontalSeparator1 = new Separator(Orientation.HORIZONTAL);

        /* player 2 */

        VBox player2Info = new VBox(20, nameText2, healthText2);
        player2Info.setAlignment(Pos.CENTER);
        Separator player2VerticalSeparator1 = new Separator(Orientation.VERTICAL);
        // Change text number to reflect stats
        HBox player2Sword = new HBox(20, swordImageView2, new Text("(1)"));
        HBox player2Shield = new HBox(20, shieldImageView2, new Text("(2)"));
        HBox player2Helmet = new HBox(20, helmetImageView2, new Text("(3)"));
        HBox player2Chest = new HBox(20, chestImageView2, new Text("(4)"));
        HBox player2Item = new HBox(20, itemImageView2, new Text("(Gloves)"));
        HBox player2Stats = new HBox(20, player2Sword, player2Shield, player2Helmet, player2Chest, player2Item);
        player2Stats.setAlignment(Pos.CENTER);
        Separator player2VerticalSeparator2 = new Separator(Orientation.VERTICAL);
        HBox player2Ready = new HBox(20, readyText2);
        player2Ready.setAlignment(Pos.CENTER);
        HBox player2 = new HBox(20, player2Info, player2VerticalSeparator1, player2Stats, player2VerticalSeparator2, player2Ready);
        player2.setAlignment(Pos.CENTER);

        Separator horizontalSeparator2 = new Separator(Orientation.HORIZONTAL);

        /* player 3 */

        VBox player3Info = new VBox(20, nameText3, healthText3);
        player3Info.setAlignment(Pos.CENTER);
        Separator player3VerticalSeparator1 = new Separator(Orientation.VERTICAL);
        // Change text number to reflect stats
        HBox player3Sword = new HBox(20, swordImageView3, new Text("(1)"));
        HBox player3Shield = new HBox(20, shieldImageView3, new Text("(2)"));
        HBox player3Helmet = new HBox(20, helmetImageView3, new Text("(3)"));
        HBox player3Chest = new HBox(20, chestImageView3, new Text("(4)"));
        HBox player3Item = new HBox(20, itemImageView3, new Text("(Gloves)"));
        HBox player3Stats = new HBox(20, player3Sword, player3Shield, player3Helmet, player3Chest, player3Item);
        player3Stats.setAlignment(Pos.CENTER);
        Separator player3VerticalSeparator2 = new Separator(Orientation.VERTICAL);
        HBox player3Ready = new HBox(20, readyText3);
        player3Ready.setAlignment(Pos.CENTER);
        HBox player3 = new HBox(20, player3Info, player3VerticalSeparator1, player3Stats, player3VerticalSeparator2, player3Ready);
        player3.setAlignment(Pos.CENTER);

        Separator horizontalSeparator3 = new Separator(Orientation.HORIZONTAL);

        /* player 4 */

        VBox player4Info = new VBox(20, nameText4, healthText4);
        player4Info.setAlignment(Pos.CENTER);
        Separator player4VerticalSeparator1 = new Separator(Orientation.VERTICAL);
        // Change text number to reflect stats
        HBox player4Sword = new HBox(20, swordImageView4, new Text("(1)"));
        HBox player4Shield = new HBox(20, shieldImageView4, new Text("(2)"));
        HBox player4Helmet = new HBox(20, helmetImageView4, new Text("(3)"));
        HBox player4Chest = new HBox(20, chestImageView4, new Text("(4)"));
        HBox player4Item = new HBox(20, itemImageView4, new Text("(Gloves)"));
        HBox player4Stats = new HBox(20, player4Sword, player4Shield, player4Helmet, player4Chest, player4Item);
        player4Stats.setAlignment(Pos.CENTER);
        Separator player4VerticalSeparator2 = new Separator(Orientation.VERTICAL);
        HBox player4Ready = new HBox(20, readyText4);
        player4Ready.setAlignment(Pos.CENTER);
        HBox player4 = new HBox(20, player4Info, player4VerticalSeparator1, player4Stats, player4VerticalSeparator2, player4Ready);
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneMap = new HashMap<>();
        createItemSelectScene(primaryStage);
        createPlayScene(primaryStage);
        createConnect(primaryStage);

        /* change this to itemSelectScene */
        //primaryStage.setScene(itemSelectScene);
        primaryStage.setScene(connectScene);
        primaryStage.show();

        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());

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
            case "connected":
                setUsername(tokens[1],tokens[2]);
            case "roll":
                setRoll(tokens[1], tokens[2]);
            case "choose":
		choose();
            case "picked":
                setPick(tokens[1], tokens[2], tokens[3]);
            case "start":
                start();
            case "atkStat":
                setAtkStat(tokens[1],tokens[2]);
            case "defStat":
                setDefStat(tokens[1],tokens[2]);
            case "health":
                updateHealth(tokens[1], tokens[2]);
            case "nextRound":
                startNextRound();
            case "winner":
                winner(tokens[1]);
            case "text":
                receiveMessage(tokens[1]);
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
    }

    public void setPick(String id, String pickType, String pick) {
        int idInt = Integer.parseInt(id);

        switch(pickType) {
            case "sword":
                players.get(idInt-1).setWeapon(pick);
            case "helmet":
                players.get(idInt-1).setHelmet(pick);
            case "chest":
                players.get(idInt-1).setChest(pick);
            case "shield":
                players.get(idInt-1).setShield(pick);
            case "item":
                players.get(idInt-1).setItem(pick);
        }
    }

    public void start() {
        //Enable buttons to make a move
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
    }

    public void startNextRound() {
        //Enable buttons to make a move
    }

    public void winner(String winnerID) {
        if(winnerID.equals("0")) {
            //No winner - everyone died
        }
        else {
            //Display winner
        }
    }

    public void receiveMessage(String message) {

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
    }

    public void pickItem(String pickType, String pick) {
        setPick(idNumStr, pickType, pick);
        try {
            conn.send("pick-" + idNumStr + "-" + pickType + "-" + pick);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void attack(String playerToAttack) {

        try {
            conn.send("attack-" + idNumStr + "-" + playerToAttack);
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

}
