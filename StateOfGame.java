package Server;

public class StateOfGame {
	
	private Player p1;
	private Player p2;
	private Player p3;
	private Player p4;
	private String currPlayer;
	
	public StateOfGame() {
		
		p1 = new Player();
		p2 = new Player();
		p3 = new Player();
		p4 = new Player();
		currPlayer = "";
		p1.setId("1");
		p2.setId("2");
		p3.setId("3");
		p4.setId("4");
	}
	
	/*
	public void setRollId( int id, int roll) {
		
		if( id == 1) {
			p1.setRoll( roll);
		}
		else if( id == 2) {
			p2.setRoll( roll);
		}
		else if( id == 3) {
			p3.setRoll( roll);
		}
		else{	// 4
			p4.setRoll( roll);
		}
		
	}
	*/
	
	// returns the first person with the highest roll. if two people have the highest roll it goes to the lowest number player
	public String rollWinner() {
		
		String winnerId = "0";
		winnerId = p1.getId();
		if( p2.getRoll() > p1.getRoll()) {
			winnerId = p2.getId();
		}
		if( p3.getRoll() > p2.getRoll()) {
			winnerId = p3.getId();
		}
		if( p4.getRoll() > p3.getRoll()) {
			winnerId = p4.getId();
		}
		return winnerId;
	}
	
	// lets you know who plays next based on who played last
	public String nextPlayer( String playedLast) {
		switch( playedLast) {
			case "1":
				return "2";
			case "2":
				return "3";
			case "3":
				return "4";
			case "4":
				return "1";
			default:
				System.out.println( "Critical Error: Next player couldnt be resolved. Returning 1.");
				return "1";
		}
	}
	
	public void setUsername( String id, String username) {
		switch( id) {
			case "1":
				p1.setUsername( username);
				break;
			case "2":
				p2.setUsername( username);
				break;
			case "3":
				p3.setUsername( username);
				break;
			case "4":
				p4.setUsername( username);
				break;
			default:
				System.out.println( "Error: Id does not exist");
				break;
		}
	}
	
	public void addRoll( String id, String roll) {
		switch( id) {
			case "1":
				p1.setRoll(Integer.parseInt(roll));
				break;
			case "2":
				p2.setRoll(Integer.parseInt(roll));
				break;
			case "3":
				p3.setRoll(Integer.parseInt(roll));
				break;
			case "4":
				p4.setRoll(Integer.parseInt(roll));
				break;
		}
	}
	
	public boolean everyoneRolled() {
		
		if( (p1.getRoll() != 0) && (p2.getRoll() != 0) && (p3.getRoll() != 0) && (p4.getRoll() != 0)  ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addChoice( String id, String itemType, String level) {
		switch( id) {
		case "1":
			if( itemType == "sword") {
				p1.setWeapon( level);
			}
			else if( itemType == "helmet") {
				p1.setHelmet( level);
			}
			else if( itemType == "chest") {
				p1.setChest( level);
			}
			else if( itemType == "shield") {
				p1.setShield( level);
			}
			else{				// usable item
				p1.setItem( level);	// level here is really item name. hPot, aPot, dPot, bomb
			}
			break;
		case "2":
			if( itemType == "sword") {
				p2.setWeapon( level);
			}
			else if( itemType == "helmet") {
				p2.setHelmet( level);
			}
			else if( itemType == "chest") {
				p2.setChest( level);
			}
			else if( itemType == "shield") {
				p2.setShield( level);
			}
			else{				// usable item
				p2.setItem( level);	// level here is really item name. hPot, aPot, dPot, bomb
			}
			break;
		case "3":
			if( itemType == "sword") {
				p3.setWeapon( level);
			}
			else if( itemType == "helmet") {
				p3.setHelmet( level);
			}
			else if( itemType == "chest") {
				p3.setChest( level);
			}
			else if( itemType == "shield") {
				p3.setShield( level);
			}
			else{				// usable item
				p3.setItem( level);	// level here is really item name. hPot, aPot, dPot, bomb
			}
			break;
		case "4":
			if( itemType == "sword") {
				p4.setWeapon( level);
			}
			else if( itemType == "helmet") {
				p4.setHelmet( level);
			}
			else if( itemType == "chest") {
				p4.setChest( level);
			}
			else if( itemType == "shield") {
				p4.setShield( level);
			}
			else{				// usable item
				p4.setItem( level);	// level here is really item name. hPot, aPot, dPot, bomb
			}
			break;
		}
	}
	
	public void addMoveToPlayer( String id, String move) {
		switch( id) {
		case "1":
			p1.setMove( move);
			break;
		case "2":
			p2.setMove( move);
			break;
		case "3":
			p3.setMove( move);
			break;
		case "4":
			p4.setMove( move);
			break;
		}
	}
	
	public boolean allAliveMadeMove() {
		
		boolean bool = true;
		if( (p1.getHealth() > 0) && (p1.getMove() == null) ) {
			bool = false;
		}
		if( (p2.getHealth() > 0) && (p2.getMove() == null) ) {
			bool = false;
		}
		if( (p3.getHealth() > 0) && (p3.getMove() == null) ) {
			bool = false;
		}
		if( (p4.getHealth() > 0) && (p4.getMove() == null) ) {
			bool = false;
		}
		return bool;
	}
	

	
	public void processAttack( String attacker, String attackee) {
		int atk = 0;
		switch( attacker) {
		case "1":
			atk = p1.getAtk();
			dealDmg( atk, attackee);
			break;
		case "2":
			atk = p2.getAtk();
			dealDmg( atk, attackee);
			break;
		case "3":
			atk = p3.getAtk();
			dealDmg( atk, attackee);
			break;
		case "4":
			atk = p4.getAtk();
			dealDmg( atk, attackee);
			break;
		}
	}
	
	public void dealDmg( int atk, String attackee) {
		if( getAttackeeDefending( attackee)) {
		
		}
		else {
			switch( attackee) {
			case "1":
				p1.setHealth( p1.getHealth() - (atk - ((p1.getDef()/ 100)*atk)) );
				break;
			case "2":
				p2.setHealth( p2.getHealth() - (atk - ((p2.getDef()/ 100)*atk)) );
				break;
			case "3":
				p3.setHealth( p3.getHealth() - (atk - ((p3.getDef()/ 100)*atk)) );
				break;
			case "4":
				p4.setHealth( p4.getHealth() - (atk - ((p4.getDef()/ 100)*atk)) );
				break;
			}
			resetDefending( attackee);
		}
		
	}
	
	public boolean getAttackeeDefending( String id) {
		switch( id) {
		case "1":
			return p1.getDefending();
		case "2":
			return p2.getDefending();
		case "3":
			return p3.getDefending();
		case "4":
			return p4.getDefending();
		default:
			return false;
		}
	}
	
	public void resetDefending( String id) {
		switch( id) {
		case "1":
			p1.setDefending( false);
			break;
		case "2":
			p2.setDefending( false);
			break;
		case "3":
			p3.setDefending( false);
			break;
		case "4":
			p4.setDefending( false);
			break;

		}
	}
	
	
	public void processItem( String id, String item) {
		int hRestore = 25;
		int atkUp = 10;
		int defUp = 10;
		int bombDmg = 10;
		switch( id) {
		case "1":
			if( item == "hPot") {
				p1.setHealth( p1.getHealth() + hRestore);
			}
			else if( item == "aPot") {
				p1.setAtk( p1.getAtk() + atkUp);
			}
			else if( item == "dPot") {
				p1.setDef( p1.getDef() + defUp);
			}
			else {			// if item is bomb, deals flat damage ignoring armor
				p2.setHealth( p2.getHealth() - bombDmg);
				p3.setHealth( p3.getHealth() - bombDmg);
				p4.setHealth( p4.getHealth() - bombDmg);
			}
			break;
		case "2":
			if( item == "hPot") {
				p2.setHealth( p2.getHealth() + hRestore);
			}
			else if( item == "aPot") {
				p2.setAtk( p2.getAtk() + atkUp);
			}
			else if( item == "dPot") {
				p2.setDef( p2.getDef() + defUp);
			}
			else {			// if item is bomb, deals flat damage ignoring armor
				p1.setHealth( p1.getHealth() - bombDmg);
				p3.setHealth( p3.getHealth() - bombDmg);
				p4.setHealth( p4.getHealth() - bombDmg);
			}
			break;
		case "3":
			if( item == "hPot") {
				p3.setHealth( p3.getHealth() + hRestore);
			}
			else if( item == "aPot") {
				p3.setAtk( p3.getAtk() + atkUp);
			}
			else if( item == "dPot") {
				p3.setDef( p3.getDef() + defUp);
			}
			else {			// if item is bomb, deals flat damage ignoring armor
				p1.setHealth( p1.getHealth() - bombDmg);
				p2.setHealth( p2.getHealth() - bombDmg);
				p4.setHealth( p4.getHealth() - bombDmg);
			}
			break;
		case "4":
			if( item == "hPot") {
				p4.setHealth( p4.getHealth() + hRestore);
			}
			else if( item == "aPot") {
				p4.setAtk( p4.getAtk() + atkUp);
			}
			else if( item == "dPot") {
				p4.setDef( p4.getDef() + defUp);
			}
			else {			// if item is bomb, deals flat damage ignoring armor
				p1.setHealth( p1.getHealth() - bombDmg);
				p2.setHealth( p2.getHealth() - bombDmg);
				p3.setHealth( p3.getHealth() - bombDmg);
			}
			break;
		default:
			break;
			
		}
	}
	
	public int pickingOver() {
		int itemsPicked = 0;
		if( p1.getWeapon() != null) {
			itemsPicked++;
		}
		if( p1.getHelmet() != null) {
			itemsPicked++;
		}
		if( p1.getChest() != null) {
			itemsPicked++;
		}
		if( p1.getShield() != null) {
			itemsPicked++;
		}
		if( p1.getItem() != null) {
			itemsPicked++;
		}
		
		if( p2.getWeapon() != null) {
			itemsPicked++;
		}
		if( p2.getHelmet() != null) {
			itemsPicked++;
		}
		if( p2.getChest() != null) {
			itemsPicked++;
		}
		if( p2.getShield() != null) {
			itemsPicked++;
		}
		if( p2.getItem() != null) {
			itemsPicked++;
		}
		
		if( p3.getWeapon() != null) {
			itemsPicked++;
		}
		if( p3.getHelmet() != null) {
			itemsPicked++;
		}
		if( p3.getChest() != null) {
			itemsPicked++;
		}
		if( p3.getShield() != null) {
			itemsPicked++;
		}
		if( p3.getItem() != null) {
			itemsPicked++;
		}
		
		if( p4.getWeapon() != null) {
			itemsPicked++;
		}
		if( p4.getHelmet() != null) {
			itemsPicked++;
		}
		if( p4.getChest() != null) {
			itemsPicked++;
		}
		if( p4.getShield() != null) {
			itemsPicked++;
		}
		if( p4.getItem() != null) {
			itemsPicked++;
		}
		return itemsPicked;
		
	}
	
	public void calcStats() {
		p1.setAtk( weaponStr( p1.getWeapon() ));
		p2.setAtk( weaponStr( p2.getWeapon() ));
		p3.setAtk( weaponStr( p3.getWeapon() ));
		p4.setAtk( weaponStr( p4.getWeapon() ));
		
		p1.setDef( calcDef( p1.getHelmet(), p1.getChest(), p1.getShield() ));
		p2.setDef( calcDef( p2.getHelmet(), p2.getChest(), p2.getShield() ));
		p3.setDef( calcDef( p3.getHelmet(), p3.getChest(), p3.getShield() ));
		p4.setDef( calcDef( p4.getHelmet(), p4.getChest(), p4.getShield() ));
	}
	
	public int weaponStr( String level) {
		switch( level) {
		case "1":
			return 20;
		case "2":
			return 25;
		case "3":
			return 28;
		case "4":
			return 30;
		default:
			return 20;
		}
	}
	
	public int helmetDef( String level) {
		switch( level) {
		case "1":
			return 5;
		case "2":
			return 7;
		case "3":
			return 9;
		case "4":
			return 12;
		default:
			return 5;
		}
	}
	
	public int chestDef( String level) {
		switch( level) {
		case "1":
			return 10;
		case "2":
			return 13;
		case "3":
			return 17;
		case "4":
			return 20;
		default:
			return 10;
		}
	}
	
	public int shieldDef( String level) {
		switch( level) {
		case "1":
			return 10;
		case "2":
			return 13;
		case "3":
			return 17;
		case "4":
			return 20;
		default:
			return 10;
		}
	}
	
	public int calcDef( String hLevel, String cLevel, String lLevel) {
		return helmetDef( hLevel) + chestDef( cLevel) + shieldDef( lLevel);
	}
	
	public Player getP1() {
		return p1;
	}
	
	public Player getP2() {
		return p2;
	}
	
	public Player getP3() {
		return p3;
	}
	
	public Player getP4() {
		return p4;
	}
	
	public String getCurrPlayer() {
		return currPlayer;
	}
	
	public void setCurrPlayer( String id) {
		currPlayer = id;
	}
	
	public String getUsername( String id) {
		switch( id) {
		case "1":
			return p1.getUsername();
		case "2":
			return p2.getUsername();
		case "3":
			return p3.getUsername();
		case "4":
			return p4.getUsername();
		default:
			return "error";
		}
	}
	
	public String getItemName( String id) {
		switch( id) {
		case "1":
			if( p1.getItem() == "hPot") {
				return " health potion.";
			}
			else if( p1.getItem() == "aPot") {
				return " attack potion.";
			}
			else if( p1.getItem() == "dPot") {
				return " defense potion.";
			}
			else {
				return " bomb.";
			}
		case "2":
			if( p2.getItem() == "hPot") {
				return " health potion.";
			}
			else if( p2.getItem() == "aPot") {
				return " attack potion.";
			}
			else if( p2.getItem() == "dPot") {
				return " defense potion.";
			}
			else {
				return " bomb.";
			}
		case "3":
			if( p3.getItem() == "hPot") {
				return " health potion.";
			}
			else if( p3.getItem() == "aPot") {
				return " attack potion.";
			}
			else if( p3.getItem() == "dPot") {
				return " defense potion.";
			}
			else {
				return " bomb.";
			}
		case "4":
			if( p4.getItem() == "hPot") {
				return " health potion.";
			}
			else if( p4.getItem() == "aPot") {
				return " attack potion.";
			}
			else if( p4.getItem() == "dPot") {
				return " defense potion.";
			}
			else {
				return " bomb.";
			}
		default:
			return "error";
		}
	}
	
	public int checkForWinner() {
		int alive = 0;
		if( p1.getHealth() > 0) {
			alive++;
		}
		if( p2.getHealth() > 0) {
			alive++;
		}
		if( p3.getHealth() > 0) {
			alive++;
		}
		if( p4.getHealth() > 0) {
			alive++;
		}
		if( alive == 0) {
			return 0;
		}
		if( alive > 1) {
			return -1;
		}
		else {
			if( p1.getHealth() > 0) {
				return 1;
			}
			else if( p2.getHealth() > 0) {
				return 2;
			}
			else if( p3.getHealth() > 0) {
				return 3;
			}
			else {
				return 4;
			}
		}
	}

}
