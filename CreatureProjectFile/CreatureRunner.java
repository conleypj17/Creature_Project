import java.util.*;
/*
 * Name: Patrick Conley, Andrew Golias
 * User Name: conleyp1, goliasa1
 * Assignment: CPS 150 Programming Assignment 3: Fantasy Role-Playing Game
 * 
 * Description:
 * 	This program will demonstrate a game where players choose a creature and have a certain amount of strength and hit points.
 * 	Each person (creature) will have an opportunity to attack, pass their turn, or quit
 * 	The last creature standing will win the game
 * 	
 * Bug Report:
 * 	This program will deal with all invalid inputs. Either the program will ask the user for another input, pass the turn to someone else, or throw an exception
 * 	
 */

public class CreatureRunner 
{
	public static Scanner keyboard = new Scanner(System.in);	//creating global Scanner object
	
	/**
	 * main method
	 * 
	 * The main method will introduce the game to the players, add players to the game, simulate a game, and declare the winner
	 */
	public static void main(String[] args)
	{	
		System.out.println("Welcome to the Fantasy Game");	//Introduction
		System.out.println("First, we will add players, then the last man standing wins");
		ArrayList<Creature> players = new ArrayList<>();	//declaring ArrayList
		addNames(players);
		
		if (players.size() <= 1)	//this means the user only entered one valid player, so the program will end
		{
			System.out.println("Cannot play with only zero people or only one person. Bye!");
			System.exit(0);	//ending program
		}  //end if statement
		
		display(players);	//will display the current players
		
		System.out.println("\nThe Players are Ready!\nLet the Battle Begin!");	//the game is now starting
		
		int numPlayers = players.size();    //holds the number of players
		String option = "";	//holds what option the user chooses
		int index_holder;
		while (numPlayers != 1)	//while there is more than one player the loop will go on
		{
			for (int i = 0; i < players.size(); i++)
			{
				option = chooseOption(players, i);	//this method will allow the user to choose what they want to do with their turn
				if (option.equals("attack"))	//user chose attack
				{
					index_holder = attackOther(players, i);	//attacking other player
					if (index_holder >= 0)	//meaning that another player was attacked
					{
						if (players.get(index_holder).isDead())	//testing to see if player is dead
						{
							System.out.println(players.get(index_holder).getName() + " is dead.");	//declaring player dead
							players.remove(index_holder);	//removing dead player
							numPlayers--;	//deincrementing the number of players
						}	//end innermost if statement
					}	//end middle if statement
				}	//end if statement
				else if (option.equals("pass"))	//user chose to pass
				{
					System.out.println("Passing your turn.");	//passing turn
				}	//end else if statement
				else if (option.isEmpty())	//this means there was invalid input, so it will pass to the next turn
				{
					//nothing needs to happen here
				}	//end else if statement
				else
				{
					System.out.println("Removing player.");	//this means the user chose to quit
					players.remove(players.get(i));	//removing the player that quit
					numPlayers--;	//deincrementing the number of players
				}	//end else statement
				
				if (numPlayers > 1)	//if there is more than one player, i.e. if nobody has won
					display(players);	//displaying the players and their stats
				
			}	//end for loop
		}	//end while loop that simulates the game
		
		System.out.println("\nCongratulations, " + players.get(0).getName() + ", you have won!");	//last player standing wins	
	}	//end main method
	
	/**
	 * addNames(ArrayList)
	 * 
	 * This method will fill the ArrayList that holds Creatures with players
	 * @param players ArrayList of the players and their stats
	 */
	public static void addNames(ArrayList<Creature> players)
	{
		System.out.println("First, let's add some players!");
		String name = "";	//holds the name
		String species = "";	//holds the species
		boolean same_name = false;	//sees if two players have the same name
		while (!name.toLowerCase().equals("quit"))	//case insensitive
		{
			System.out.println("Enter player name ('quit' to end):");	//'quit' is the sentinel
			name = keyboard.nextLine();	//storing user input
			if (name.toLowerCase().equals("quit"))
			{
				continue;	//will end the while loop
			}	//end if statement
			else
			{
				for (int i = 0; i < players.size(); i++)
				{	//making sure the name has not already been entered
					if (players.size() > 0 && players.get(i).getName().equals(name.toLowerCase()))
					{
						System.out.println("Somebody already has this name; try again.");
						same_name = true;
					}	//end if statement
				}	//end for loop
			}	//end else statement
			if (same_name)
				continue;
			System.out.println("b/B: Balrog\nc/c: Cyberdemon\ne/E: Elf\nh/H: Human");		//displaying options
			System.out.println("Enter " + name + "'s species:");	
			species = keyboard.nextLine();	//storing user input
			//this if statement makes sure the user entered the correct first letter of a species (case insensitive)
			if (species.toLowerCase().charAt(0) != 'b' && species.toLowerCase().charAt(0) != 'c'	
					&& species.toLowerCase().charAt(0) != 'e' && species.toLowerCase().charAt(0) != 'h')	
			{
				System.out.println("Unknown species; try again.");	//user did not enter a correct first letter
				continue;	//going back to beginning of the while loop
			}	//end if statement
			else
			{
				species = species.toLowerCase();	//case insensitive
				species = addSpecies(species.charAt(0));	//storing the name of the species
				//creating new Creature object
				if (species.equals("Balrog"))
				{
					players.add(new Balrog(name,(int)(Math.random() * 100 + 1), (int)(Math.random() * 50 + 1)));
				}
				else if (species.equals("Cyberdemon"))
				{
					players.add(new Cyberdemon(name, (int)(Math.random() * 100 + 1), (int)(Math.random() * 50 + 1)));
				}
				else if (species.equals("Elf"))
				{
					players.add(new Elf(name, (int)(Math.random() * 100 + 1), (int)(Math.random() * 50 + 1)));
				}
				else
				{
					players.add(new Human(name, (int)(Math.random() * 100 + 1), (int)(Math.random() * 50 + 1)));
				}
			}	//end else statement
			
		}	//end while loop
		
	}	//end addNames method
	
	
	/**
	 * addSpecies(char) -> String
	 * 
	 * This method returns the name of the species based on the first character the user entered
	 * @param c - the lowercase first character of one of the four species
	 * @return (String) the name of the species that the user selected
	 */
	public static String addSpecies(char c)
	{	//b = balrog, c = cyberdemon, e = elf, h = human
		if (c == 'b')
			return "Balrog";
		else if (c == 'c')
			return "Cyberdemon";
		else if (c == 'e')
			return "Elf";
		else
			return "Human";
		
	}	//end addSpecies method
	
	
	/**
	 * display(ArrayList)
	 * 
	 * This method displays each of the player's names, species, strength, and hit points
	 * @param players - ArrayList of the players and their stats
	 */
	public static void display(ArrayList<Creature> players)
	{
		System.out.println("\nCurrent Players:");
		System.out.println("NAME  |  SPECIES  |  STRENGTH  |  HIT POINTS");
		for (int i = 0; i < players.size(); i++)	//displaying each of the players and their stats
		{
			System.out.println(players.get(i).getName() + "  |  " + players.get(i).getSpecies() + "  |  " +
					players.get(i).getStrength() + "  |  " + players.get(i).getHitPoints());
		}	//end for loop
		System.out.println("");	//organization
	}	//end display method
	
	/**
	 * chooseOption(ArrayList<Creature>, int) ->String
	 * 
	 * This method will allow the user to choose what they do with their turn.
	 * @param players ArrayList of the current players
	 * @param i index of the player whose turn it is
	 * @return (String) the user's choice; will return empty String if their is invalid input
	 */
	public static String chooseOption(ArrayList<Creature> players, int i)
	{
		System.out.println(players.get(i).getName() + ", select one of the following option:");
		System.out.println("\ta/A: Attack an opponent.");
		System.out.println("\tp/P: Pass (go to next player).");		//displaying the options to the user
		System.out.println("\tq/Q: Quit the game.");
		String answer = keyboard.nextLine();	//holds user choice
		answer = answer.toLowerCase();	//case insensitive
		if (answer.charAt(0) != 'a' && answer.charAt(0) != 'p' && answer.charAt(0) != 'q')
		{
			System.out.println("Invalid input \"" + answer + "\", passing to next player.");
			return "";	//returning an empty String for invalid input
		}
		else if (answer.charAt(0) == 'a')
			return "attack";	//returning attack
		else if (answer.charAt(0) == 'p')
			return "pass";	//returning pass
		else
			return "quit";	//returning quit
	}	//end chooseOption method
	
	/**
	 * attackOther(ArrayList<Creature>, int) -> int
	 * 
	 * This method will simulate one player attacking another and return the index of the player who was attacked
	 * @param players ArrayList of the current players
	 * @param i	the index of the player who initiated the attack
	 * @return (int) the index of the player who was attacked
	 */
	public static int attackOther(ArrayList<Creature> players, int i)
	{
		String opp_name = "";	//will hold the name of the person who is being attacked
		int damage = 0;	//holds the damage of the player who is attacking
		System.out.println(players.get(i).getName() + ", enter the name of your opponent");
		opp_name = keyboard.nextLine();	//holds who is being attacked
		for (int index = 0; index < players.size(); index++)
		{
			//testing to see if the opponent name matches one of the players
			if (opp_name.toLowerCase().equals(players.get(index).getName().toLowerCase()))
			{
				damage = players.get(i).getDamage();	//getting the damage
				players.get(index).Damage(damage);	//damaging the other player
				return index;	//returning the index of the player who was attacked
			}	//end if statement
		}	//end for loop
		System.out.println("Unknown player.");
		return -1;	//returning -1 if invalid input
	}	//end attackOther method
	
	
}	//end CreatureRunner class

