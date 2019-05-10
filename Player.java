package Server;

public class Player {
	
	private String username;
	private String id;
	private int roll;
	
	private String weapon;
	private String helmet;
	private String chest;
	private String shield;
	private String item;
	
	private int health;
	private int atk;
	private int def;
	
	private String move;
	private boolean defending;
	private boolean usedItem;
	
	public Player(){
		username = "none";
		id = null;
		roll = 0;
		
		weapon = "null";
		helmet = "null";
		chest = "null";
		shield = "null";
		item = "null";
		
		health = 100;
		atk = 0;
		def = 0;
		
		move = null;
		defending = false;
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
	
	public String getShield() {
		return shield;
	}
	
	public void setShield( String sh) {
		shield = sh;
	}
	
	public String getItem() {
		return item;
	}
	
	public void setItem( String i) {
		item = i;
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
	
	public String getMove() {
		return move;
	}
	
	public void setMove( String moveString) {
		move = moveString;
	}
	
	public void setDefending( boolean bool) {
		defending = bool;
	}
	
	public boolean getDefending() {
		return defending;
	}
	

}
