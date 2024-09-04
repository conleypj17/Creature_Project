import java.util.Scanner;	//importing Scanner
import java.util.ArrayList;	//importing ArrayLists
import java.lang.IndexOutOfBoundsException;	//importing IndexOutOfBoundsException

/*
 * Name: Patrick Conley
 * User Name: conleyp1
 * Assignment: CPS 150 Programming Assignment 1: Programming with Java ArrayLists
 * 
 * Description:
 * 	This program will fill two ArrayLists with user input for customers' purchase amounts and names
 * 	Then the program will display the name of the top customer
 * 	Then the program will display the names of the top N customers that the user enters
 * 	If there are fewer than topN customers, all customers will be displayed
 * 
 * Bug Report:
 * 	This program will work for all user input and handle any exceptions. There are not any bugs.
 * 	If the user enters invalid input, they will be asked to re-enter input.
 * 	If the user does not fill the ArrayLists at all, an exception will be thrown
 */
public class Supermarket 
{
	public static Scanner keyboard = new Scanner(System.in);	//creating global Scanner object
	
	public static void main(String[] args) 
	{
		//introduction
		System.out.println("This program will store each customer's name and purchase amount.");
		System.out.println("Then the program will display the best customer.");
		System.out.println("After that, the program will display the top number of customers that the user chooses.");
		
		
		//declaring ArrayLists
		ArrayList<Double> purchaseAmount = new ArrayList<Double>();
		ArrayList<String> names = new ArrayList<String>();
		try
		{
			fillArray(purchaseAmount, names);	//filling each of the ArrayLists using fillArray method
			
			//using nameOfBestCustomer method to print name of best customer
			System.out.println("Name of best customer: " + nameOfBestCustomer(purchaseAmount, names));
			
			System.out.println("How many customers would you like to recognize?");
			final int TOP_N = keyboard.nextInt();	//this will determine how many top customers are displayed
			
			//topCustomers ArrayList will come from nameOfBestCustomers method. names of the topN customers will be stored here
			ArrayList<String> topCustomers = nameOfBestCustomers(purchaseAmount, names, TOP_N);	
			System.out.println("Names of best customers in order: " + topCustomers);
		}	//end try block
		catch (IndexOutOfBoundsException ex)	//will catch IndexOutOfBoundsException 
		{
			System.out.println("Error: " + ex.getMessage());
			System.out.println("Make sure to enter data into the ArrayList before entering 0.");
		}	//end catch block
		catch (Exception ex)	//will catch everything else
		{
			System.out.println("Something went wrong: " + ex.getMessage());
		}	//end catch block

	}	//end main method
	
	/**
	 * nameOfBestCustomer(ArrayList<Double>, ArrayList<String>) -> String
	 * 
	 * calculates and returns the name of the customer with the highest purchase amount
	 * 
	 * @param sales holds the purchase amounts of the customers
	 * @param customers holds the names of the customers
	 * @return (String) the name of the best customer (the customer with the highest purchase amount)
	 */
	public static String nameOfBestCustomer(ArrayList<Double> sales, ArrayList<String> customers) throws IndexOutOfBoundsException
	{
		int index = 0;	//holds index of biggest purchase
		double biggestSale = sales.get(0);
		for (int i = 1; i < sales.size(); i++)
		{
			if (sales.get(i) > biggestSale) 
			{
				biggestSale = sales.get(i);
				index = i; 
			}	//end if statement
		}	//end for loop
		
		return customers.get(index);	//returning the name of the best customer
		
	}	//end nameOfBestCustomer method
	
	/**
	 * nameOfBestCustomers(ArrayList<Double>, ArrayList<String>, int) -> ArrayList<String>
	 * 
	 * calculates and returns the names of the topN customers
	 * 
	 * @param sales holds the purchase amounts of the customers
	 * @param customers	holds the names of the customers
	 * @param topN holds how many customers will be recognized
	 * @return (ArrayList<String>) will hold the names of the topN customers
	 */
	public static ArrayList<String> nameOfBestCustomers(ArrayList<Double> sales,  ArrayList<String> customers, int topN) throws IndexOutOfBoundsException
	{
		if (topN >= customers.size())	//more people being recognized than people in the ArrayList
		{
			System.out.println("There are fewer than " + topN + " customers. Displaying all of them.");
			ArrayList<String> allCustomers = new ArrayList<String>(customers);	//will be the same ArrayList as customers
			System.out.println(allCustomers);	//printing all the customers
			System.exit(0);	//ending program
			return allCustomers;	//returning the names of all the customers
		}	//end if statement
		ArrayList<String> bestCustomers = new ArrayList<String>();	//this will hold the names of the topN customers
		double biggestSale = Integer.MIN_VALUE;
		int index = 0;
		for (int j = 0; j < topN; j++)
		{
			for (int i = sales.size() - 1; i >= 0; i--)
			{
				 if (sales.get(i) > biggestSale)
				 {
					 biggestSale = sales.get(i);
					 index = i;
				 }	//end if statement
			}	//end inner for loop
			bestCustomers.add(customers.get(index));	//adding the customer name to bestCustomers
			sales.remove(index);	//removing the sales value
			customers.remove(index);	//removing the customer name
			biggestSale = Integer.MIN_VALUE;	//resetting biggestSale
		}	//end outer for loop
		
		return bestCustomers;
		
	}	//end nameOfBestCustomers method
	
	/**
	 * fillArray(ArrayList<Double>, ArrayList<String>)
	 * 
	 * this method will perform input validation and fill the ArrayLists
	 * 
	 * @param sales	ArrayList that holds the purchase amounts of the customers
	 * @param customer ArrayList that holds the name of the customers
	 */
	public static void fillArray(ArrayList<Double> sales, ArrayList<String> customer)
	{
		double price = 0;	//holds price for each customer
		int i = 0;	//holds index value
		String name = "";	//holds name of customer
		boolean flag = false;	//determines if while loop keeps running, will be set to true when user enters 0
		while (!flag)
		{
			System.out.println("Enter the purchase amount for customer " + (i+1) + ": (Enter 0 to stop)");
			if (keyboard.hasNextDouble())	//making sure user entered a positive double
			{
				price = keyboard.nextDouble();	//storing double entered in price
				if (price == 0)	//sentinel value
				{
					flag = true;	//flag variable true
					//System.out.println("Exiting input");	
					continue;	//will end loop
				}
				else if (price > 0)
				{
					sales.add(price);	//adding the price since it is a nonzero double
					keyboard.nextLine();	//clearing next line
					System.out.println("Enter the name for customer " + (i+1) + ": ");
					name = keyboard.nextLine();	//storing name that the user entered
					customer.add(name);	//adding name to the ArrayList
					i++;	//incrementing index
				}
				else
				{
					System.out.println("Please enter a positive input.");
					keyboard.nextLine();
				}
			}
			else	//user did not enter a double for price
			{
				System.out.println("Invalid Input. Please Try Again.");
				keyboard.nextLine();	//clearing next line
			}
		}	//end while loop for input validation
	}	//end fillArray method

}	//end Supermarket class
