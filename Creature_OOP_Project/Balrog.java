/*
 * Balrog.java
 * Andrew Golias, goliasa1
 * Patrick Conley, conleyp1
 */
public class Balrog extends Demon{
	
	private int secondTurn=2;				// allows the player to take 2 turns

	public Balrog(String name, int strength, int hitPoints) {
		super(name, strength, hitPoints);
	}
	
	/** @return (String) - name of the species */
	public String getSpecies() { return "Balrog";	}	
	
	/**
	 * method calls its super class to calculate initial damage, on every other run, the Balrog is allowed
	 * to attack again since it attacks twice in a row
	 * 
	 * @return (int) - damage dealt by 'Balrog' species type
	 */
	public int getDamage() {
		int damage = super.getDamage();
		
		if(secondTurn % 2 == 0) {
			damage += super.getDamage();	
		}
		secondTurn++;									// ever other run should allow the player to take a second attack
	
		return damage;	
	}//end getDamage
}// end Balrog subclass