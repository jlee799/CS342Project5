package Server;

public class Player {
	
	private String username;
	private String id;
	private int roll;
	
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
		id = null;
		roll = 0;
		
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
	
	public void setHealth( int hp) {
		health = hp;
	}
	
	public int getRoll() {
		return roll;
	}
	
	public void setRoll( int val) {
		roll = val;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId( String val) {
		id = val;
	}
	
	public void setUsername( String name) {
		username = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getWeapon() {
		return weapon;
	}
	
	public void setWeapon( String weap) {
		weapon = weap;
	}
	
	public String getHelmet() {
		return helmet;
	}
	
	public void setHelmet( String helm) {
		helmet = helm;
	}
	
	public String getChest() {
		return chest;
	}
	
	public void setChest( String ches) {
		chest = ches;
	}
	
	public String getLegs() {
		return legs;
	}
	
	public void setLegs( String leg) {
		legs = leg;
	}
	
	public String getitem() {
		return item;
	}
	
	public void setItem( String leg) {
		legs = leg;
	}
	
	public void setAtk( int attack) {
		atk = attack;
	}
	
	public int getAtk() {
		return atk;
	}
	
	public void setDef( int defense) {
		def = defense;
	}
	
	public int getDef() {
		return def;
	}
	
	
	

}
