
/*
 * NonEmptyList.java
 * Patrick Conley, conleyp1
 * 
 * This is the NonEmptyList object class
 * 		it implements the LispList interface
 */

public class NonEmptyList implements LispList
{
	private Object head;	//head Object
	private LispList tail;	//tail LispList
	
	//NonEmptyList constructor
	public NonEmptyList(Object h, LispList l)
	{
		head = h;	//initializing head
		tail = l;	//initializing tail
	}	//end NonEmptyList constructor

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
	
	//will always return false, as a NonEmptyList will never be empty
	public boolean empty() 
	{
		return false;
	}	//end empty method

	//head accessor method
	public Object head() 
	{
		return head;
	}	//end head method

	//tail accessor method
	public LispList tail() 
	{
		return tail;
	}	//end tail method
	
	//toString method
	public String toString()
	{
		return head() + " " + tail().toString();
	}	//end toString method

	/**
	 * length() -> int
	 * 
	 * this method will return 0 if the list is empty, otherwise it will recursive return the length of the list
	 */
	public int length() 
	{
		if (empty())
		{
			return 0;
		}
		return 1 + tail.length();
	}	//end length method

	/**
	 * append(LispList) -> LispList
	 * 
	 * this method will recursively append two LispLists together
	 */
	public LispList append(LispList other) 
	{
		if (other.empty())
		{
			return this;	//returning this LispList if the other one passed in is empty
		}
		return new NonEmptyList(this.head(), this.tail().append(other));	//recursively calling the append method to append the two LispLists
		
	}	//end append method
	
	/**
	 * merge(LispList) -> LispList
	 * 
	 * this method will recursively merge two LispLists together
	 */
	public LispList merge(LispList other) 
	{
		if (other.empty())
		{
			return this;	//returning this LispList if the other one is empty
		}
		LispList list = new NonEmptyList(this.head(), other.merge(this.tail()));	//recursively calling the merge method to merge the two LispLists
		
		return list;	//returning the LispList
		
	}	//end merge method

	/**
	 * contains(Object) -> boolean
	 * 
	 * this method will recursively test if a LispList contains an Object
	 */
	public boolean contains(Object obj)
	{
		if (this.head().equals(obj))
		{
			return true;	//returning true if the head of the list is the Object passed to the method
		}
		else
		{
			return this.tail().contains(obj);	//recursively calling the contains method to test if the rest of the list contains the Object
		}
	}	//end contains method

}	//end NonEmptyList class
