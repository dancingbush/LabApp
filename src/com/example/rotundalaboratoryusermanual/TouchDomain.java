package com.example.rotundalaboratoryusermanual;



public class TouchDomain {

	
	public static boolean getDomain(CircleMenu cm,float y){
		
		if((y>=cm.getCenterY()-cm.getRadious()))
			return true;
		
		return false;
		
	}
}
