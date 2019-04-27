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
	
	//StateOfGame serverGame = new StateOfGame();
	
	private NetworkConnectionRPS conn = null;
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
	
	private ServerRPS createServer() {
		return new ServerRPS( portNumInt, data-> { 
			Platform.runLater(()->{
				
				processInput( data.toString());
				
				
			});
		});
	}
	
	public void processInput( String data) {
		
	}
	
	
	// will add the new client to the end of the client list
	public void addToClientList( String clientNum) {
		clientListTA.appendText( clientNum+"\n");
	}
	
	public void addToInOutString( String line) {
		inOutTA.appendText( line+"\n");
	}
	
	
	public void updateClientList() {
		clientListTA.clear();
		int i = 0;
		int size = conn.getConnThread().getArrayCT().size();
		
		for( i = 0; i < size; i++) {
			addToClientList( conn.getConnThread().getArrayCT().get(i).getClientNum() );
		}
	}
	
	
	public void updateClientNum(){
		clientNum.setText( Integer.toString( conn.getClientThreadSize()));
	}
	

}
