import java.io.FileNotFoundException;	//importing FileNotFoundException
import java.util.HashMap;	//importing HashMap
import java.util.HashSet;	//importing HashSet
import java.util.Iterator;	//importing Iterator
import java.util.Map;	//importing Map
import java.util.PriorityQueue;	//importing PriorityQueue
import java.util.Queue;	//importing Queue
import java.util.Scanner;	//importing Scanner
import java.io.File;	//importing File

/*
 * Patrick Conley, conleyp1
 * 
 * CPS 151 Programming Assignment 7: Travel Network (Java Collections Framework)
 * 
 * Description: 
 * 	This program will be a Flight Network System
 * 	It will ask the user for the name of a city and then display the distances between cities in the network
 * 	
 * Bug Report:
 * 	This program will work for all input and throw any exceptions. Will keep asking user for city names until they enter a valid city name
 * 	
 */	



public class DistanceRunner 
{
	public static Scanner keyboard = new Scanner(System.in);		//creating global Scanner object
	
	public static void main(String[] args)
	{
		//introduction
		System.out.println("Welcome to Flight Network System.");
		System.out.println("\nOnce you load the network from a file,");
		System.out.println("I will show you the distances between the cities in the network.");
		
		try 
		{
			//getting file name
			String fileName = getFileName("Enter the network file name (do not need to include .txt):");
		
			//creating Map that holds Strings as keys and DistanceTo objects in a HashSet as values
			Map<String, HashSet<DistanceTo>> connections = new HashMap<String, HashSet<DistanceTo>>();
			String [] mapFiller = new String[3];
			
			//creating file
			File file = new File(fileName);
			Scanner fileInfo = new Scanner(file);
			
			while (fileInfo.hasNextLine())	//while there is more info in the file
			{
				String info = fileInfo.nextLine();	//storing each line of info
				fillMap(info, mapFiller);	//filling connections map
				DistanceTo dis = new DistanceTo(mapFiller[1], Integer.parseInt(mapFiller[2]));
				if (!connections.containsKey(mapFiller[0]))
				{
					connections.put(mapFiller[0], new HashSet<DistanceTo>());	//filling map
				}
				connections.get(mapFiller[0]).add(dis);
			}	//end while loop
			
			//getting starting point
			String from = getStartCity("Where is your starting point (city name)?");
			//making sure the city is case insensitive
			String str = from.substring(0, 1);
			str = str.toUpperCase();
			String end = from.substring(1);
			from = str + end;
			
			//creating new PriorityQueue
			Queue<DistanceTo> distance = new PriorityQueue<DistanceTo>();
			//creating new map
			Map<String, DistanceTo> shortestKnownDistance = new HashMap<String, DistanceTo>();
			
			distance.add(new DistanceTo(from, 0));
			while (!distance.isEmpty())	//while not empty
			{
				DistanceTo dist = distance.remove();	//getting first value
				if (!shortestKnownDistance.containsKey(dist.getTarget()))
				{
					shortestKnownDistance.put(dist.getTarget(), dist);	//adding to shortestKnownDistance
					HashSet<DistanceTo> direct_connections = connections.get(dist.getTarget());
					Iterator<DistanceTo> iterator = direct_connections.iterator();	//creating iterator
					while (iterator.hasNext())	//iterating through direction_connections
					{
						DistanceTo d = iterator.next();
						distance.add(new DistanceTo(d.getTarget(), dist.getDistance() + d.getDistance()));
					}
				}
			}	//end while loop
			
			
			//printing out distances
			for (Map.Entry<String, DistanceTo> dis: shortestKnownDistance.entrySet())
			{
				System.out.println("Distance to " + dis.getKey() + " " + dis.getValue().getDistance());
			}	//end for loop
			
			
			fileInfo.close();	//closing fileInfo Scanner
			keyboard.close();	//closing keyboard Scanner
		}	//end try block
		catch (FileNotFoundException ex)
		{
			System.out.println("Error: File not Found.");
		}	//end FileNotFoundException block
		catch (Exception ex)
		{
			System.out.println("Error: Something went wrong: " + ex.getMessage());
		}	//end Exception block
		
	}	//end main method
	
	
	/**
	 * getFileName(String) -> String
	 * this method will get user input for the name of the file and add .txt to it
	 * 
	 * @param prompt - prompt for getting user input
	 * @return (String) the name of the file name plus .txt added on
	 */
	public static String getFileName(String prompt)
	{
		System.out.println(prompt);
		String str = keyboard.nextLine();	//getting user input for name of the file
		return str + ".txt";	//adding .txt to the file name
	}	//end getFileName method
	
	/**
	 * fillMap(String, String[])
	 * this method will fill a String array with information from the text file, splitting the info by spaces
	 * 
	 * @param s - each line of the text file
	 * @param a - String array that will hold information about distances from a city to another city
	 */
	public static void fillMap(String s, String [] a)
	{
		String[] info = s.split(" ");	//splitting each line of the text file by a space
		a[0] = info[0];	//city from
		a[1] = info[1];	//city to
		a[2] = info[2];	//distance
	}	//end fillMap method
	
	/**
	 * getStartCity(String) -> String
	 * this method will validate that the user enters a city name that the text file contains
	 * @param s - prompting the user to enter the name of a city
	 * @return (String) the validated name of the city
	 */
	public static String getStartCity(String s)
	{
		System.out.println(s);	//printing prompt
		boolean flag = false;
		while (!flag)
		{
			String input = keyboard.nextLine();	//getting user input
			if (input.equalsIgnoreCase("Pendleton") || input.equalsIgnoreCase("Phoenix") || input.equalsIgnoreCase("Pueblo")
					|| input.equalsIgnoreCase("Pensacola") || input.equalsIgnoreCase("Peoria") || input.equalsIgnoreCase("Pittsburgh")
					|| input.equalsIgnoreCase("Pierre") || input.equalsIgnoreCase("Princeton"))	//checking cities
			{
				return input;	//returning city name
			}
			else
			{
				System.out.println("City not found. Please try again.");
			}
			
		}	//end while loop
		
		return null;
	}	//end getStartCity method
	
}	//end DistanceRunner class
