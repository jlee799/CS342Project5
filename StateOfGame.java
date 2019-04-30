package Server;

public class StateOfGame {
	
	private Player p1;
	private Player p2;
	private Player p3;
	private Player p4;
	
	public StateOfGame() {
		
		p1 = new Player();
		p2 = new Player();
		p3 = new Player();
		p4 = new Player();
	}
	
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
	
	// returns the first person with the highest roll. if two people have the highest roll it goes to the lowest number player
	public String rollWinner() {
		
		String winnerId = null;
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
			case "2":
				p2.setUsername( username);
			case "3":
				p3.setUsername( username);
			case "4":
				p4.setUsername( username);
			default:
				System.out.println( "Error: Id does not exist");
		}
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

}
