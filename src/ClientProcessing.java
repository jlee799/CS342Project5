import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;

public class ClientProcessing {
	
	String ipAdd;
	int portNum;
	String idNumStr;
	int idNumInt;
	private ClientNetworkConnectionRPS conn = null;
	String username;
	ArrayList<Player> players = new ArrayList<Player>(4);
	//StateOfGame game;
	
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
        		//idNum = tokens[1];
        	case "roll":
        		setRoll(tokens[1], tokens[2]);
        	case "choose":
        		
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
	
	public void setRoll(String id, String numRolledStr) {
		int idInt = Integer.parseInt(id);
		int numRolled = Integer.parseInt(numRolledStr);
		players.get(idInt-1).setRoll(numRolled);
	}
	
	public void choose() {
		
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
			case "legs":
				players.get(idInt-1).setLegs(pick);
			case "item":
				players.get(idInt-1).setItem(pick);
		}
	}
	
	public void start() {
		
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
	
	public void sendUsername() {
		username = "";
		//Get username from GUI
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
	
	public void attack() {
		String playerToAttack = "";
		//Get player to attack here from GUI or as parameter
		
		try {
			conn.send("attack-" + idNumStr + "-" + playerToAttack);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void defend() {
		try {
			conn.send("defend-" + idNumStr);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void useItem() {
		String itemUsed = "";
		//Get item here from GUI or as parameter
		
		try {
			conn.send("item-" + idNumStr + "-" + itemUsed);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
