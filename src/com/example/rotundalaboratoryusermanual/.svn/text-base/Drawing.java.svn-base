package com.example.rotundalaboratoryusermanual;



import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;



public class Drawing {


	public static void drawItemImageviews(CircleMenu cm) {
		for (int i = 0; i < cm.getCircleMenuItemsArray().size(); i++) {

			magnify(cm, i,20);

			cm.getCircleMenuItemsArray().get(i).setAngle(cm.getCircleMenuItemsArray().get(i).getAngle()+cm.getDeltaAngle()) ;

			double x = cm.getCenterX()
					+ (cm.getRadious() * Math.cos(Math
							.toRadians(cm.getCircleMenuItemsArray().get(i).getAngle())))
					- (cm.getCircleMenuItemsArray().get(i).getMenuItemImageView().getWidth() / 2.0);

			double y = cm.getCenterY()
					- (cm.getRadious() * Math.sin(Math
							.toRadians(cm.getCircleMenuItemsArray().get(i).getAngle())))
					- (cm.getCircleMenuItemsArray().get(i).getMenuItemImageView().getHeight() / 2.0);

		
		
			
		
			cm.getCircleMenuItemsArray().get(i).getMenuItemImageView().setX((float) (x));
			cm.getCircleMenuItemsArray().get(i).getMenuItemImageView().setY((float) (y));


			
			if (cm.getCircleMenuItemsArray().get(i).getAngle() < 0) {
				cm.getCircleMenuItemsArray().get(i).setAngle(cm.getCircleMenuItemsArray().get(i).getAngle()+360) ;
			}
			cm.getCircleMenuItemsArray().get(i).setAngle(cm.getCircleMenuItemsArray().get(i).getAngle()%360) ;

		}
	}

	
	
	
	public static void drawHolders(CircleMenu cm){
	
		double arc=cm.getRadious()*Math.toRadians(cm.getTeta());
		
		
		for (int i = 0; i < cm.getCircleMenuItemsArray().size(); i++) {
			double deltaTeta;
		if(cm.getCircleMenuItemsArray().get(i).getAngle()>=90&&cm.getCircleMenuItemsArray().get(i).getAngle()<=270){
			deltaTeta=Math.abs(90-cm.getCircleMenuItemsArray().get(i).getAngle());
			deltaTeta=Math.toRadians(deltaTeta);
			double L=cm.getRadious()*deltaTeta;
			L=(L/arc)* cm.getCircleMenuItemsArray().get(i).getHolderItem().getWidth();
			
			cm.getCircleMenuItemsArray().get(i).getHolderItem().setX((float) -L);
		}
		
		else{
			if(cm.getCircleMenuItemsArray().get(i).getAngle()>=0){
				deltaTeta=Math.abs(90-cm.getCircleMenuItemsArray().get(i).getAngle());
				deltaTeta=Math.toRadians(deltaTeta);
				
				double L=cm.getRadious()*deltaTeta;
				L=(L/arc)* cm.getCircleMenuItemsArray().get(i).getHolderItem().getWidth();
			
				cm.getCircleMenuItemsArray().get(i).getHolderItem().setX((float) L);
			}
			
			else{
				double temp=cm.getCircleMenuItemsArray().get(i).getAngle();
				temp-=360;
				
				deltaTeta=Math.abs(90-temp);
				deltaTeta=Math.toRadians(deltaTeta);
				
				double L=cm.getRadious()*deltaTeta;
				L=(L/arc)* cm.getCircleMenuItemsArray().get(i).getHolderItem().getWidth();
			
				cm.getCircleMenuItemsArray().get(i).getHolderItem().setX((float) L);
			}
			
		}
		

		
		}
		
	
	
	}

	private static void magnify(CircleMenu cm,int i,int angle){
		double topDistance = Math.abs(cm.getCircleMenuItemsArray().get(i).getAngle() - 90);
	
					if ((topDistance < angle)) {

					
						LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
								(int) (cm.getCircleMenuItemsArray().get(i).getMenuItemImageViewWidth() + 1.1 * (angle - topDistance)),
								(int) (cm.getCircleMenuItemsArray().get(i).getMenuItemImageViewHeight() + 1.1 * (angle - topDistance)));
						cm.getCircleMenuItemsArray().get(i).getMenuItemImageView().setLayoutParams(layoutParams);
					}
	}
	
}
