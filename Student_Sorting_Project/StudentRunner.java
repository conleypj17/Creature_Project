import java.util.ArrayList;	//importing ArrayList
import java.util.Collections;	//importing Collections to sort through the ArrayList
import java.util.Scanner;	//importing Scanner
import java.io.File;	//importing File
import java.io.FileNotFoundException;	//importing FileNotFoundException
import java.io.PrintWriter;	//importing PrintWriter
import java.io.FileWriter;	//importing FileWriter
import java.io.IOException;	//importing IOException

/*
 * Patrick Conley, conleyp1
 * 
 * Description: This program will sort a large number of student records in a text file by student ID
 * 	This will use an ArrayList of Student objects in order to store the data
 * 	Then the program will print the sorted data to a new text file
 * 	
 * 
 * Bug Report:
 * 	This program will work for all input and throw any exceptions.
 * 	
 */	

public class StudentRunner 
{	
	/**
	 * The main method will create an ArrayList that holds Student data using the Student object class.
	 * Then it will sort the data and print it to a new file.
	 */
	public static void main(String[] args) throws IOException
	{
		//introduction
		System.out.println("This program will sort a large number of student records in a text file by student ID.");
		System.out.println("Then the program will print the sorted data to a new text file.");
		
		try {
		
			File file = new File("courses.txt");	//creating new file

			//creating Scanner object to access file
			Scanner keyboard = new Scanner(file);
		
			//creating ArrayList of Student objects
			ArrayList<Student> students = new ArrayList<Student>();
		
			//adding text file info to the ArrayList
			while (keyboard.hasNextLine())
			{
				//creating new Student objects for each line in the courses.txt file. The Student object class will use the String to fill in data fields
				students.add(new Student(keyboard.nextLine()));	
			}	//end while loop
		
			Collections.sort(students);	//sorting students ArrayList
		
			Scanner sc = new Scanner(System.in);	//creating new Scanner that will hold user input
		
			System.out.println("Enter the name of the file you would like to write to (Do not need to include .txt): ");
			String fileName = sc.nextLine();
		
			fileName += ".txt";	//adding .txt to the file name
		
			FileWriter info = new FileWriter(fileName);	//creating new FileWriter object
			PrintWriter write = new PrintWriter(info);	//creating new PrintWriter object
		
			for (int i = 0; i < students.size(); i++)
			{
				write.println(students.get(i).toString());	//printing each sorted line of student data to the new file
			}	//end for loop
			
			System.out.println("Information sorted and printed to: " + fileName);		
			sc.close();	//closing Scanner
			keyboard.close();	//closing Scanner
			info.close(); //closing FileWriter
			write.close();	//closing PrintWriter
		}	//end try block
		catch (FileNotFoundException ex)	//FileNotFoundException
		{
			System.out.println("Error, file not found.");
		}	//end catch block
		catch (Exception ex)
		{
			System.out.println("Error, something went wrong: " + ex.getMessage());
		}	//end catch block
		
	}	//end main method
}	//end StudentRunner class
