package com.example.rotundalaboratoryusermanual;



import android.content.Context;
import android.view.GestureDetector;

public class GestureDetectoring {

	/*
	 * This packages consists of classes for handling the fingertips
	 * touch on the screen. Classes have listeners for tiuch events such as
	 * moving fingertops on screen and moving them off
	 * These classes also identigy the move and speed type
	 */
	 
	
	public static void setGestureDetector(CircleMenu cm,Context ctx){
		
		
		cm.setMGestureDetector (new GestureDetector(ctx,
				new MyGestureListener(cm)));
	}
}
