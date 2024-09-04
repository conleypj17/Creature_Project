
/*
 * DistanceTo.java
 * Patrick Conley, conleyp1
 * 
 * This is the DistanceTo object class
 * 
 * 		author is nbashias1
 */


public class DistanceTo implements Comparable<DistanceTo>
{
   private String target;	//target
   private int distance;	//distance

   public DistanceTo(String city, int dist) 
   { 
	   target = city; 
	   distance = dist; 
   }	//end DistanceTo constructor

   public String getTarget() 
   { 
	   return target; 
   }	//end getTarget method

   public int getDistance() 
   { 
	   return distance; 
   }	//end getDistance method

   public int compareTo(DistanceTo other) 
   { 
	   return distance - other.distance; 
   }	//end compareTo method
   
   public String toString()
   {
	   return "Target: " + target + " Distance: " + distance;
   }	//end toString method
   
}	//end DistanceTo class
