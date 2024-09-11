
/*
 * Patrick Conley, conleyp1
 * 
 * Description: 
 * 		This program uses OOP to simulate a LispList 	
 */	

public class LispListRunner 
{
	public static void main(String[] args)
	{
		 LispList list1 = new EmptyList();
	      System.out.println("[" + list1 + "]");
	      System.out.println("Expected: []");

	      LispList list2 = LispList.NIL.cons("E").cons("D").cons("C").cons("B").cons("A");
	      System.out.println(list2);
	      System.out.println("Expected: A B C D E");
	     
	      LispList list3 = LispList.NIL.cons("5").cons("4").cons("3").cons("2").cons("1");
	      System.out.println(list3);
	      System.out.println("Expected: 1 2 3 4 5");
	     
	      LispList list4 = list2.append(list3);
	      System.out.println(list4);
	      System.out.println("Expected: A B C D E 1 2 3 4 5");
	     
	      LispList list5 = list1.append(list2);
	      System.out.println(list5);
	      System.out.println("Expected: A B C D E");
	     
	      LispList list6 = list2.append(list1);
	      System.out.println(list6);
	      System.out.println("Expected: A B C D E");
	}	//end main method
}	//end LispListRunner method
