/*
 * Human.java
 * Andrew Golias, goliasa1
 * Patrick Conley, conleyp1
 */
public class Human extends Creature{
	public Human(String name, int strength, int hitPoints) {
		super(name, strength, hitPoints);		
	}// end constructor
	
	/** @return (String) - name of the species */
	public String getSpecies() { return "Human";	}
		
}// end Human subclass