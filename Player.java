package Server;

public class Player {
	
	private String username;
	private int id;
	
	private String weapon;
	private String helmet;
	private String chest;
	private String legs;
	private String item;
	
	private int health;
	private int atk;
	private int def;
	
	private String move;
	
	public Player(){
		username = null;
		id = 0;
		
		weapon = null;
		helmet = null;
		chest = null;
		legs = null;
		item = null;
		
		health = 100;
		atk = 0;
		def = 0;
		
		move = null;
	}
	
	public int getHealth() {
		return health;
	}
	

}
