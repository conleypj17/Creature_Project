/*
 * Creature.java
 * Andrew Golias, goliasa1
 * Patrick Conley, conleyp1
 */
import java.util.Random;
public class Creature {
	
	public static Random rn = new Random();			// create random object
	private int strength, hitPoints;				// int instance variables
	private String name;							// String instance variable
	
	
	/**
	 * method sets instance variables upon creation of the object
	 * 
	 * @param name (String) - the player's name
	 * @param strength (int) - the random number for the strength of the player
	 * @param hitPoints	(int) - the random number for the hit point for the player
	 * @throws IllegalArgumentException - if the name or species name is invalid
	 */
	public Creature(String name, int strength, int hitPoints) throws IllegalArgumentException {
		
		this.setStrength(strength);
		this.setHitPoints(hitPoints);
		
		if(isNamed(name))
			this.name = name;
		else
			throw new IllegalArgumentException("Invalid name");
		
	}// end constructor
	
	
	/** @return (String) - name of the player */
	public String getName() { return name; }
	
	/** @return (String) - the species type */
	public String getSpecies() { return getSpecies();	}
	
	/**
	 * method sets instance variable strength to the parameter strength
	 * 
	 * @param strength (int) - the strength sent that will be checked
	 * @throws IllegalArgumentException - if the strength is not positive
	 */
	public void setStrength(int strength)throws IllegalArgumentException {
		if(strength <= 0)
			throw new IllegalArgumentException("Invalid value for strength");
		else 
			this.strength = strength;
		
	}// end setStrength method
	
	/** @return the value of strength */
	public int getStrength() { return strength;		}
	
	/**
	 * method sets instance variable hitPoints to the parameter hitPoints
	 * 
	 * @param hitPoints (int) - the hitPoints sent that will be checked
	 * @throws IllegalArgumentException - if the hitPoints is not positive
	 */
	public void setHitPoints(int hitPoints) throws IllegalArgumentException {
		if(hitPoints <= 0) 
			throw new IllegalArgumentException("Invalid value for hit points");
		else
			this.hitPoints = hitPoints;
	}// end setHitPoints method 
	
	/** @return the value of hitPoints */
	public int getHitPoints() { return hitPoints;	}
	
	/**
	 * method calculates the base amount of damage the player 
	 * 
	 * @return (int) - the amount of damage that player inflicts
	 */
	public int getDamage() { 
		 return rn.nextInt(getHitPoints()) + 1;	// generate random number 0 < r <= getHitPoints	
	}// end getDamage
	
	/**
	 * method damages player by removing amount of damage sent from the strength of the player
	 * 
	 * @param damage (int) - amount of damage player inflicts
	 */
	public void Damage(int damage) {
		if(damage < 0)
			throw new IllegalArgumentException("Invalid value for damage");
		else
			strength -= damage;
	}// end Damage
	
	/**
	 * method returns true if the strength is less than or equal to zero, otherwise it returns false
	 * 
	 * @return (boolean) - result based on strength
	 */
	public boolean isDead() {
		if(strength <= 0)
			return true;
		
		return false;
	}// end isDead method
		
	
	/**
	 * method checks that the input is not empty
	 * 
	 * @param name (String) - the input sent that is being checked
	 * @return (boolean) - true if the name is valid, false if not
	 */
	public boolean isNamed(String name) {
		if(name == null || name.length() == 0)
			return false;

		return true;
	}// end isNamed
			
		
}// end Creature class
