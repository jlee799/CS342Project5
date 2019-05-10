package Server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class FXServer extends Application {
	
	StateOfGame game = new StateOfGame();
	
	private NetworkConnection conn = null;
	private int portNumInt = 5555;
	private String inOutString;
	private String clientListString;

	Font textSize = new Font( 20);
	
	// on off button nodes
	Button on, off;
	
	// client number nodes
	Text clientNum, clientText;
	
	// strings incoming and out going text area
	Text inOutText, clientListText;
	TextArea inOutTA, clientListTA;
	
	// port nodes
	Text currPort, chanPort, portNum;
	TextField inputPort;
	
	
	
	private Parent createServerGUI() {
		
		
		// left side
		on = new Button("ON");
		off = new Button("OFF");
		on.setFont( textSize);
		off.setFont( textSize);
		VBox onOff = new VBox( 10, on, off);
		
		off.setDisable(true);
		
		on.setOnAction( event -> {
			try{
				conn = createServer();
				conn.startConn();
				off.setDisable(false);
				on.setDisable(true);
				inputPort.setDisable(true);
				updateClientNum();
			}
			catch( Exception e){
				
			}
			addToInOutString("Server ON");
		});
		
		off.setOnAction( event -> {
			try{
				conn.closeConn();
				Platform.exit();
			}
			catch( Exception e){
				
			}
		});
		
		// top of gui
		clientText = new Text("Clients Connected: ");
		clientNum = new Text();
		clientText.setFont( textSize);
		clientNum.setFont( textSize);
		HBox clientsCon = new HBox( 10, clientText, clientNum);
		
		// middle of gui
		inOutText = new Text("Strings coming in and going out:");
		clientListText = new Text("Client List:");
		inOutTA = new TextArea( inOutString);
		clientListTA = new TextArea( clientListString);
		VBox inOutVB = new VBox( inOutText, inOutTA);
		VBox clientListVB = new VBox( clientListText, clientListTA);
		HBox middleHB = new HBox( inOutVB, clientListVB);
		
		// bottom of GUI
		currPort = new Text("Current Port: ");
		portNum = new Text( Integer.toString( portNumInt) );
		chanPort = new Text("Change Port: ");
		inputPort = new TextField();
		currPort.setFont( textSize);
		portNum.setFont( textSize);
		chanPort.setFont( textSize);
		HBox portBox = new HBox( 10, currPort, portNum, chanPort, inputPort);
		
		inputPort.setOnAction( event -> {
			String portString;
			portString = inputPort.getText();
			try { portNumInt = Integer.parseInt( portString);
					portNum.setText( portString);
			}catch( Exception e) {
				System.out.println("Cannot parse string to int");
			}
			
			inputPort.clear();
		});
		
		BorderPane serverGUI = new BorderPane();

		serverGUI.setLeft( onOff);
		serverGUI.setTop( clientsCon);
		serverGUI.setCenter( middleHB);
		serverGUI.setBottom( portBox);
		
		return serverGUI;
	}
	
	public static void main(String[] args){
		 launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		inOutString = new String();
		clientListString = new String();
		primaryStage.setScene( new Scene(createServerGUI(), 800, 300));
		primaryStage.show();
		
	}
	
	private Server createServer() {
		return new Server( portNumInt, data-> { 
			Platform.runLater(()->{
				
				processInput( data.toString());
				
				
			});
		});
	}
	
	public void processInput( String data) {
		
		addToInOutString( "In: "+data);
		String[] tokens = data.split("-",-2);
		
		switch( tokens[0]) {
			case "connect":
				connectUser( tokens[1], tokens[2]);
			case "roll":
				game.addRoll( tokens[1], tokens[2]);
				if( game.everyoneRolled()) {
					game.setCurrPlayer( game.rollWinner() );
					try {
						conn.sendClient( game.getCurrPlayer(), "choose-"+ game.getCurrPlayer());
						addToInOutString( "Out: "+"choose-"+ game.getCurrPlayer());
					}
					catch(Exception e) {
						addToInOutString( "Error: failed to send message to have player choose.");
					}
				}
			case "picked":
				game.addChoice(tokens[1], tokens[2], tokens[3]);
				if( game.pickingOver() == 20) {
					try {
						conn.sendAll("start");
						addToInOutString( "Out: "+"start");
					}
					catch(Exception e) {
						addToInOutString( "Error: failed to send all players start message.");
					}
					game.calcStats();
					sendStats();
				}
				else {
					game.setCurrPlayer( game.nextPlayer( game.getCurrPlayer()));
					try {
						conn.sendClient( game.getCurrPlayer(), "choose-"+ game.getCurrPlayer());
						addToInOutString( "Out: "+"choose-"+ game.getCurrPlayer());
					}
					catch(Exception e) {
						addToInOutString( "Error: failed to send message to have next player choose.");
					}
				}
			case "attack":
				game.addMoveToPlayer( tokens[1], data);
				if( game.allAliveMadeMove() ) {
					processMoves();
				}
			case "defend":
				game.addMoveToPlayer( tokens[1], data);
				if( game.allAliveMadeMove() ) {
					processMoves();
				}
			case "item":
				game.addMoveToPlayer( tokens[1], data);
				if( game.allAliveMadeMove() ) {
					processMoves();
				}
			case "quit":
				if( tokens[1] == "1") {
					game.getP1().setHealth(0);
				}
				else if( tokens[1] == "2") {
					game.getP2().setHealth(0);
				}
				else if( tokens[1] == "3") {
					game.getP3().setHealth(0);
				}
				else {
					game.getP4().setHealth(0);
				}
				try {
					conn.closeConnNum( tokens[1]);
				}
				catch(Exception e) {
					addToInOutString("Error: failled to close conn.");
				}
				if( game.allAliveMadeMove() ) {
					processMoves();
				}
		}
	}
	
	// this will add the players username to the state of game and send the player the people already connected
	public void connectUser( String id, String username) {
		
		switch(id) {
			case "1":
				game.setUsername( id, username);
			case "2":
				try {
					conn.sendClient( id, "connected-1-"+ game.getP2().getUsername());
					addToInOutString( "Out: "+"connected-1-"+ game.getP2().getUsername());
					if( game.getP1().getRoll() != 0) {
						conn.sendClient( id, "roll-1-"+game.getP1().getRoll());
					}
					conn.sendAll( "connected-"+id+"-"+username);
					addToInOutString( "Out: "+"connected-"+id+"-"+username);
					game.setUsername( id, username);
					addToClientList( id, username);
				}
				catch( Exception e) {
					addToInOutString( "Error failed to send already connected clients.");
				}
			case "3":
				try {
					conn.sendClient( id, "connected-1-"+ game.getP1().getUsername());
					addToInOutString( "Out: "+"connected-1-"+ game.getP1().getUsername());
					conn.sendClient( id, "connected-2-"+ game.getP2().getUsername());
					addToInOutString( "Out: "+"connected-2-"+ game.getP2().getUsername());
					if( game.getP1().getRoll() != 0) {
						conn.sendClient( id, "roll-1-"+game.getP1().getRoll());
						addToInOutString( "Out: "+"roll-1-"+game.getP1().getRoll());
					}
					if( game.getP2().getRoll() != 0) {
						conn.sendClient( id, "roll-2-"+game.getP2().getRoll());
						addToInOutString( "Out: "+"roll-2-"+game.getP2().getRoll());
					}
					game.setUsername( id, username);
					addToClientList( id, username);
					conn.sendAll( "connected-"+id+"-"+username);
					addToInOutString( "Out: "+"connected-"+id+"-"+username);
				}
				catch( Exception e) {
					addToInOutString( "Error failed to send already connected clients.");
				}
			case "4":
				try {
					conn.sendClient( id, "connected-1-"+ game.getP1().getUsername());
					addToInOutString( "Out: "+"connected-1-"+ game.getP1().getUsername());
					conn.sendClient( id, "connected-2-"+ game.getP2().getUsername());
					addToInOutString( "Out: "+"connected-2-"+ game.getP2().getUsername());
					conn.sendClient( id, "connected-3-"+ game.getP3().getUsername());
					addToInOutString( "Out: "+"connected-3-"+ game.getP3().getUsername());
					if( game.getP1().getRoll() != 0) {
						conn.sendClient( id, "roll-1-"+game.getP1().getRoll());
						addToInOutString( "Out: "+"roll-1-"+game.getP1().getRoll());
					}
					if( game.getP2().getRoll() != 0) {
						conn.sendClient( id, "roll-2-"+game.getP2().getRoll());
						addToInOutString( "Out: "+"roll-2-"+game.getP2().getRoll());
					}
					if( game.getP3().getRoll() != 0) {
						conn.sendClient( id, "roll-3-"+game.getP3().getRoll());
						addToInOutString( "Out: "+"roll-3-"+game.getP3().getRoll());
					}
					game.setUsername( id, username);
					addToClientList( id, username);
					conn.sendAll( "connected-"+id+"-"+username);
					addToInOutString( "Out: "+"connected-"+id+"-"+username);
				}
				catch( Exception e) {
					addToInOutString( "Error failed to send already connected clients.");
				}
		}
	}
	
	
	
	// will add the new client to the end of the client list
	public void addToClientList( String clientNum, String username) {
		clientListTA.appendText( "Id: "+ clientNum+ "Username: " + username + "\n");
	}
	
	public void addToInOutString( String line) {
		inOutTA.appendText( line+"\n");
	}
	
	public void sendStats() {
		try {
			// sending atk stat of each player to everyone after calculation
			conn.sendAll( "atkStat-1-"+ game.getP1().getAtk() );
			addToInOutString( "Out: "+"atkStat-1-"+ game.getP1().getAtk());
			conn.sendAll( "atkStat-2-"+ game.getP2().getAtk() );
			addToInOutString( "Out: "+"atkStat-2-"+ game.getP2().getAtk());
			conn.sendAll( "atkStat-3-"+ game.getP3().getAtk() );
			addToInOutString( "Out: "+"atkStat-3-"+ game.getP3().getAtk());
			conn.sendAll( "atkStat-4-"+ game.getP4().getAtk() );
			addToInOutString( "Out: "+"atkStat-4-"+ game.getP4().getAtk());
			// sending def stat of each player to everyone after calculation
			conn.sendAll( "defStat-1-"+ game.getP1().getDef() );
			addToInOutString( "Out: "+"defStat-1-"+ game.getP1().getDef());
			conn.sendAll( "defStat-2-"+ game.getP2().getDef() );
			addToInOutString( "Out: "+"defStat-2-"+ game.getP2().getDef());
			conn.sendAll( "defStat-3-"+ game.getP3().getDef() );
			addToInOutString( "Out: "+"defStat-3-"+ game.getP3().getDef());
			conn.sendAll( "defStat-4-"+ game.getP4().getDef() );
			addToInOutString( "Out: "+"defStat-4-"+ game.getP4().getDef());
		}
		catch(Exception e) {
			addToInOutString( "Error failed to send stats to clients.");
		}
	}
	
	public void processMoves() {
		processDefendMoves();
		processItemMoves();
		processAttackMoves();
		if( game.getP1().getMove() != null) {
			sendTextForMoves( game.getP1().getMove());
		}
		if( game.getP2().getMove() != null) {
			sendTextForMoves( game.getP2().getMove());
		}
		if( game.getP3().getMove() != null) {
			sendTextForMoves( game.getP3().getMove());
		}
		if( game.getP4().getMove() != null) {
			sendTextForMoves( game.getP4().getMove());
		}
		game.getP1().setMove(null);
		game.getP2().setMove(null);
		game.getP3().setMove(null);
		game.getP4().setMove(null);
		sendHealth();
		int winner = game.checkForWinner();
		if( winner == 0) {
			try {
				conn.sendAll("winner-0");
				addToInOutString( "Out: "+"winner-0");
			}
			catch(Exception e) {
				addToInOutString( "Error failed to send winner.");
			}
		}
		else if( winner == -1) {
			try {
				conn.sendAll("nextRound");
				addToInOutString( "Out: "+"nextRound");
			}
			catch(Exception e) {
				addToInOutString( "Error failed to send next round.");
			}
		}
		else {
			try {
				conn.sendAll("winner-"+winner);
				addToInOutString( "Out: "+"winner-"+winner);
			}
			catch(Exception e) {
				addToInOutString( "Error failed to send winner.");
			}
		}
	}
	
	public void sendTextForMoves( String move) {
		String[] tokens = move.split("-",-2);
		switch(tokens[0]) {
		case "attack":
			try {
				conn.sendAll( "text-"+ game.getUsername(tokens[1]) + " attacked " + game.getUsername(tokens[2]));
				addToInOutString( "Out: "+"text-"+ game.getUsername(tokens[1]) + " attacked " + game.getUsername(tokens[2]));
			}
			catch(Exception e) {
				addToInOutString( "Error failed to send text.");
			}
		case "defend":
			try {
				conn.sendAll( "text-"+ game.getUsername(tokens[1]) + " is defending." );
				addToInOutString( "Out: "+"text-"+ game.getUsername(tokens[1]) + " is defending.");
			}
			catch(Exception e) {
				addToInOutString( "Error failed to send text.");
			}
		case "item":
			try {
				conn.sendAll( "text-"+ game.getUsername(tokens[1]) + " used " + game.getItemName( tokens[1]));
				addToInOutString( "Out: "+"text-"+ game.getUsername(tokens[1]) + " used " + game.getItemName( tokens[1]));
			}
			catch(Exception e) {
				addToInOutString( "Error failed to send text.");
			}
		default:
			break;
		}
	}
	
	public void processDefendMoves() {
		String[] p1Tokens = game.getP1().getMove().split("-",-2);
		if( p1Tokens[0] == "defend") {
			game.getP1().setDefending( true);
		}
		String[] p2Tokens = game.getP2().getMove().split("-",-2);
		if( p2Tokens[0] == "defend") {
			game.getP2().setDefending( true);
		}
		String[] p3Tokens = game.getP3().getMove().split("-",-2);
		if( p3Tokens[0] == "defend") {
			game.getP3().setDefending( true);
		}
		String[] p4Tokens = game.getP4().getMove().split("-",-2);
		if( p4Tokens[0] == "defend") {
			game.getP4().setDefending( true);
		}
	}
	
	public void processItemMoves() {
		// player 1 if not bomb
		String[] p1Tokens = game.getP1().getMove().split("-",-2);
		if( (p1Tokens[0] == "item") && (p1Tokens[2] != "bomb")) {
			game.processItem( p1Tokens[1], p1Tokens[2]);
		}
		// player 2 if not bomb
		String[] p2Tokens = game.getP2().getMove().split("-",-2);
		if( (p2Tokens[0] == "item") && (p2Tokens[2] != "bomb")) {
			game.processItem( p2Tokens[1], p2Tokens[2]);
		}
		// player 3 if not bomb
		String[] p3Tokens = game.getP3().getMove().split("-",-2);
		if( (p3Tokens[0] == "item") && (p3Tokens[2] != "bomb")) {
			game.processItem( p3Tokens[1], p3Tokens[2]);
		}
		// player 4 if not bomb
		String[] p4Tokens = game.getP4().getMove().split("-",-2);
		if( (p4Tokens[0] == "item") && (p4Tokens[2] != "bomb")) {
			game.processItem( p4Tokens[1], p4Tokens[2]);
		}
		// if it is the bomb
		if( (p1Tokens[0] == "item") && (p1Tokens[2] == "bomb")) {
			game.processItem( p1Tokens[1], p1Tokens[2]);
		}
		if( (p2Tokens[0] == "item") && (p2Tokens[2] == "bomb")) {
			game.processItem( p2Tokens[1], p2Tokens[2]);
		}
		if( (p3Tokens[0] == "item") && (p3Tokens[2] == "bomb")) {
			game.processItem( p3Tokens[1], p3Tokens[2]);
		}
		if( (p4Tokens[0] == "item") && (p4Tokens[2] == "bomb")) {
			game.processItem( p4Tokens[1], p4Tokens[2]);
		}
		
	}
	
	public void processAttackMoves() {
		// if player 1 is attacking
		String[] p1Tokens = game.getP1().getMove().split("-",-2);
		if( p1Tokens[0] == "attack") {
			game.processAttack( p1Tokens[1], p1Tokens[2]);
		}
		// if player 2 is attacking
		String[] p2Tokens = game.getP2().getMove().split("-",-2);
		if( p2Tokens[0] == "attack") {
			game.processAttack( p2Tokens[1], p2Tokens[2]);
		}
		// if player 3 is attacking
		String[] p3Tokens = game.getP3().getMove().split("-",-2);
		if( p3Tokens[0] == "attack") {
			game.processAttack( p3Tokens[1], p3Tokens[2]);
		}
		// if player 4 is attacking
		String[] p4Tokens = game.getP4().getMove().split("-",-2);
		if( p4Tokens[0] == "attack") {
			game.processAttack( p4Tokens[1], p4Tokens[2]);
		}
	}
	
	public void sendHealth() {
		try {
			conn.sendAll("health-1-"+game.getP1().getHealth());
			addToInOutString( "Out: "+"health-1-"+game.getP1().getHealth());
			conn.sendAll("health-2-"+game.getP2().getHealth());
			addToInOutString( "Out: "+"health-2-"+game.getP2().getHealth());
			conn.sendAll("health-3-"+game.getP3().getHealth());
			addToInOutString( "Out: "+"health-3-"+game.getP3().getHealth());
			conn.sendAll("health-4-"+game.getP4().getHealth());
			addToInOutString( "Out: "+"health-4-"+game.getP4().getHealth());
		}
		catch(Exception e) {
			addToInOutString( "Error: failed to send updated health.");
		}
	}
	
	// TODO: implement players quitting by killing them then disconnecting them
	
	public void updateClientList() {
		clientListTA.clear();
		if( conn.clientExists( "1")) {
			addToClientList( "1", game.getP1().getUsername());
		}
		if( conn.clientExists( "2")) {
			addToClientList( "2", game.getP2().getUsername());
		}
		if( conn.clientExists( "3")) {
			addToClientList( "3", game.getP3().getUsername());
		}
		if( conn.clientExists( "4")) {
			addToClientList( "4", game.getP4().getUsername());
		}
		updateClientNum();
	}
	
	
	public void updateClientNum(){
		clientNum.setText( Integer.toString( conn.getClientThreadSize()));
	}
	

}
