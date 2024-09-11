
/*
 * LispList.java
 * Patrick Conley, conleyp1
 * 
 * This is the LispList interface
 */
public interface LispList 
{
	EmptyList NIL = new EmptyList();	//NIL constant
	LispList cons(Object o);	//cons method
	boolean empty();	//empty method
	Object head();	//head method
	LispList tail();	//tail method
	int length();	//length method
	LispList merge(LispList other);	//merge method
	boolean contains(Object obj);	//contains method
	LispList append(LispList other);	//append method
	
}	//end LispList interface
