package com.example.rotundalaboratoryusermanual;





import android.view.View;


public class BalancingCircleItems {

/*
 * This package iinclcudes classes for cuntionality of rotating the 
 * and balancing teh circle menu
 */

	
	public static void setBalance(CircleMenu cm) {

		
		int minIndex = 0;
		for (int i = 1; i < cm.getCircleMenuItemsArray().size(); i++) {
			if (Math.abs(cm.getCircleMenuItemsArray().get(i).getAngle() - 90) < Math.abs(cm.getCircleMenuItemsArray().get(minIndex).getAngle() - 90))

				minIndex = i;
		}

		if ((cm.getCircleMenuItemsArray().get(minIndex).getAngle() != 90 )){
		
			RotateToCenter.balancing(cm,cm.getCircleMenuItemsArray().get(minIndex).getMenuItemImageView(),cm.getBalancingDelay());
			
		}
	
	}
	
	public static void setBalance(CircleMenu cm,View v) {

		
		int minIndex=findIndex(cm, v);

		if ((cm.getCircleMenuItemsArray().get(minIndex).getAngle() != 90 )){
		
			RotateToCenter.balancing(cm,cm.getCircleMenuItemsArray().get(minIndex).getMenuItemImageView(),cm.getBalancingDelay());
			
		}
	
	}
	
	public static int findIndex(CircleMenu cm,View v){
		
	for(int i=0;i<cm.getCircleMenuItemsArray().size();i++){
		
		if(v.equals(cm.getCircleMenuItemsArray().get(i).getMenuItemImageView())){
			return i;
		}
		
	}
	
	
		return -1;
		
	
	}
	
	
}
