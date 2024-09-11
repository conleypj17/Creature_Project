
/*
 * Student.java
 * Patrick Conley, conleyp1
 * 
 * This is the Student object class
 */


public class Student implements Comparable
{
	private int studentID; 	//holds student ID number
	private String courseNumber;	//holds course number
	private int sectionNumber;	//holds section number
	private String semester;	//holds semester
	private int year;	//holds year
	private String[] info;	//this will hold each of the data fields of information that are separated by tabs
	
	//constructor that will call mutator methods to set the data fields
	public Student(String information)
	{
		info = information.split("\\t");	//this will split each line of information using tabs to distinguish between data
		setStudentID(info[0]);	//setting studentId number
		setCourseNumber(info[1]);	//setting courseNumber
		setSectionNumber(info[2]);	//setting sectionNumber
		setSemester(info[3]);	//setting semester
		setYear(info[4]);	//setting year
		
	}	//end Student constructor

	//validates and sets student ID number
	public void setStudentID(String studentID)
	{
		if (studentID.length() != 6)	//the ID number must be six digits
		{
			throw new IllegalArgumentException("Invalid Student ID: " + studentID);
		}
		this.studentID = Integer.parseInt(studentID);	//converting from String to integer
	}	//end setStudentID method
	
	//validates and sets course number
	public void setCourseNumber(String courseNumber)
	{
		if (courseNumber.length() != 7)	//the course number must be 3 letters and 4 digits => length 7
		{
			throw new IllegalArgumentException("Invalid Course Number: " + courseNumber);
		}
		this.courseNumber = courseNumber;
	}	//end setCourseNumber method
	
	//validates and sets section number
	public void setSectionNumber(String sectionNumber)
	{
		if (sectionNumber.length() != 1)	//section number must be one digit
		{
			throw new IllegalArgumentException("Invalid Section Number: " + sectionNumber);
		}
		this.sectionNumber = Integer.parseInt(sectionNumber);	//converting from String to integer
	}	//end setSectionNumber method
	
	//validates and sets semester
	public void setSemester(String semester)
	{
		if (!semester.contains("Fall") && !semester.contains("Spring") && !semester.contains("Summer"))	//semester must be one of these
		{
			throw new IllegalArgumentException("Invalid Semester: " + semester);
		}
		this.semester = semester;
	}	//end setSemester method
	
	//validates and sets year
	public void setYear(String year)
	{
		if (Integer.parseInt(year) < 1950 || Integer.parseInt(year) > 2050)	//year must be between 1950 and 2050
		{
			throw new IllegalArgumentException("Invalid Year:" + year);
		}
		this.year = Integer.parseInt(year);	//converting from String to integer
	}	//end setYear method
	
	//accessor method that returns studentID
	public int getStudentID()
	{
		return studentID;	//returning studentID
	}
	
	//toString method that returns the student's information
	public String toString()
	{
		return "Student ID: " + studentID + "\tCourse Number: " + courseNumber + "\tSection Number: "
				+ sectionNumber + "\tSemester: " + semester + "\tYear: " + year;	//returning String with Student data
	}	//end toString method

	//compareTo method
	public int compareTo(Object other) 
	{
		if (this.getStudentID() > ((Student) other).getStudentID())
		{
			return 1;	//positive number 
		}
		else if ((this.getStudentID() < ((Student) other).getStudentID()))
		{
			return -1;	//negative number
		}
		return 0;	//0, meaning two id numbers are the same
		
	}	//end compareTo method
	
}	//end Student class
