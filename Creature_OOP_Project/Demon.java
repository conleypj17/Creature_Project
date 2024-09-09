/*
 * Demon.java
 * Andrew Golias, goliasa1
 * Patrick Conley, conleyp1
 */
public class Demon extends Creature{
		
	public Demon(String name, int strength, int hitPoints) {
		super(name, strength, hitPoints);
	}// end constructor
	
	/** @return (String) - the species type	 */
	public String getSpecies() { return getSpecies();	}
	
	/**
	 * method calculates amount of damage a Demon species type deals
	 * 
	 * @return (int) - the amount of damage dealt by the 'Demon' species type
	 */
	public int getDamage() {
		int rand = rn.nextInt(100);							// generate random number for 5% chance
		int damage = super.getDamage();
		if(rand < 5)
			damage += 50;
		
		return damage;	
	}//end getDamage
		
		
		
		
}// end Demon subclass