
/*
 * EmptyList.java
 * Patrick Conley, conleyp1
 * 
 * This is the EmptyList object class
 * 		it implements the LispList interface
 */

public class EmptyList implements LispList	//implementing the LispList interface
{
	/**
	 * cons(Object) -> LispList
	 * 
	 * this method returns a NonEmptyList with the Object argument as the head
	 * 		and the rest of the list as the tail
	 */
	public LispList cons(Object o)
	{
		return new NonEmptyList(o, this);
	}	//end cons method
	
	//empty method for EmptyList, will always return true
	public boolean empty() 
	{
		return true;
	}	//end empty method

	//cannot have a head of an EmptyList, so will throw exception
	public Object head() 
	{
		throw new UnsupportedOperationException("Error: Unsupported Operation");
	}	//end head method

	//cannot have a tail of an EmptyList, so will throw exception
	public LispList tail() 
	{
		throw new UnsupportedOperationException("Error: Unsupported Operation");
	}	//end tail method

	//will return an empty String, as an EmptyList does not have anything to print
	public String toString()
	{
		return "";
	}	//end toString method

	//will return 0, as the length of an EmptyList is always 0
	public int length() 
	{
		return 0;
	}	//end length method

	//will just return the other LispList, as the EmptyList does not have anything to append
	public LispList append(LispList other) 
	{
		return other;
	}	//end append method

	//will just return the other LispList, as the EmptyList does not have anything to merge
	public LispList merge(LispList other) 
	{
		return other;
	}	//end merge method

	//will return false, as an EmptyList will not contain any Objects
	public boolean contains(Object obj) 
	{
		return false;
	}	//end contains method
}	//end EmptyList class
