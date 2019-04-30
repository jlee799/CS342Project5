import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;

public class ClientProcessing {
	
	String ipAdd;
	int portNum;
	String idNum;
	private ClientNetworkConnectionRPS conn = null;
	String username;
	ArrayList<Player> players = new ArrayList<Player>();
	
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
        		idNum = tokens[1];
        	case "roll":
        		
        	case "choose":
        		
        	case "picked":
        		
        	case "start":
        		
        	case "attack":
        		
        	case "defend":
        		
        	case "item":
        		
        	case "health":
        		updateHealth(tokens[1], tokens[2]);
        	case "nextRound":
        		startNextRound();
        }
        
	}
	
	public void updateHealth(String id, String health) {
		int idInt = Integer.parseInt(id);
		int healthInt = Integer.parseInt(health);
		players[idInt].setHealth = Integer.parseInt(health);
	}
	
	public void startNextRound() {
		
	}
	
	
	//Sending To Client
	
	public void sendUsername() {
		username = "";
		//Get username from GUI
		try {
			conn.send("connect-" + idNum + "-" + username);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void rollDie() {
		Random random = new Random();
		String numRolled = Integer.toString(random.nextInt(6) + 1);
		
		try {
			conn.send("roll-" + idNum + "-" + numRolled);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void pickItem(String itemStr) {
		try {
			conn.send("pick-" + idNum + "-" + itemStr);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void attack() {
		String playerToAttack = "";
		//Get player to attack here from GUI or as parameter
		
		try {
			conn.send("attack-" + idNum + "-" + playerToAttack);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void defend() {
		try {
			conn.send("defend-" + idNum);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void useItem() {
		String itemUsed = "";
		//Get item here from GUI or as parameter
		
		try {
			conn.send("item-" + idNum + "-" + itemUsed);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
