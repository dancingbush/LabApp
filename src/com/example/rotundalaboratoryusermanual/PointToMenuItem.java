package com.example.rotundalaboratoryusermanual;






import android.widget.ImageView;
import android.widget.LinearLayout;



public class PointToMenuItem {


	
	public static  int pointToPosition(CircleMenu cm,float x, float y) {
		
		 int deltaDisplay=0;
		
		deltaDisplay = getDeltaDisplay(cm);
		

		for (int i = 0; i <cm.getCircleMenuItemsArray().size(); i++) {

			ImageView item = (ImageView) cm.getCircleMenuItemsArray().get(i).getMenuItemImageView() ;
			

         		
			if (item.getX() < x && item.getX() + item.getWidth() > x
					&& item.getY()< y-deltaDisplay && item.getY() + item.getHeight() > y-deltaDisplay) {
			
				
				return i;
			}

		}
		
		
		return -1;
	}
	
	
	public static int getDeltaDisplay(CircleMenu cm){
		
		LinearLayout ln=(LinearLayout) cm.getActivity().findViewById(R.id.linearLayOut);
		
return Math.abs(DisplayParams.displayMetrics.heightPixels
		- ln.getHeight());
	}
	
}
