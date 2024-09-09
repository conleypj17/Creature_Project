/*
 * Elf.java
 * Andrew Golias, goliasa1
 * Patrick Conley, conleyp1
 */
public class Elf extends Creature{
	public Elf(String name, int strength, int hitPoints) {
		super(name, strength, hitPoints);		
	}// end constructor
	
	/** @return (String) - name of the species */
	public String getSpecies() { return "Elf";	}
	
	/**
	 * method calculates additional damage 'Elf' species can do
	 * 
	 * @return (int) - the additional amount of damage
	 */
	public int getDamage() {
		int damage = super.getDamage();
		int rand = rn.nextInt(100);							// generate random number for 10% chance
		if(rand < 10) 
			damage *= 2;
		
		return damage;
	}// end getDamage method
	
}// end Elf subclass