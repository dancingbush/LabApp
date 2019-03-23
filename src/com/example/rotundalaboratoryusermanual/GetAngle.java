package com.example.rotundalaboratoryusermanual;




public class GetAngle {
	
	/*
	 * The utils package consists of classes or tools that are constantly
	 * used during the running of the libary. For instance we can mention getting
	 * andgle of each touch points, getting touch range etc
	 */


	  public static  double getAngle(CircleMenu cm,double x , double y)
	    {
	        double deltaY = x - cm.getCenterX();
	        double deltaX = y - cm.getCenterY();
	        
	        
	        double degreeAngle;
	        
	        if(deltaY>0)
	        {
	            if(deltaX>0)
	                degreeAngle = Math.toDegrees(Math.atan(deltaY/deltaX));
	            else
	                degreeAngle = 180+Math.toDegrees(Math.atan(deltaY/deltaX));
	        }
	        else
	        {
	            if(deltaX>0)
	                degreeAngle = 360+Math.toDegrees(Math.atan(deltaY/deltaX));
	            else
	                degreeAngle = 180+Math.toDegrees(Math.atan(deltaY/deltaX));
	        }
			return degreeAngle;
	        
}
}
