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
