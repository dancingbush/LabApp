package com.example.rotundalaboratoryusermanual;

import android.view.MotionEvent;


public class TouchEventState {

	
	
	public static void setState(CircleMenu cm,MotionEvent event){
	
		// TODO Auto-generated method stub
		try{
				float x = (float) event.getX();
				float y = (float) event.getY();

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:

					
					 if(event.getPointerCount()<=1)
					TouchEventsMethods.setDowning(cm,x, y);

					break;

				case MotionEvent.ACTION_MOVE:
					
		          if(event.getPointerCount()<=1)
					TouchEventsMethods.setMoving(cm,x, y);

					break;
				case MotionEvent.ACTION_UP:

					 if(event.getPointerCount()<=1)
					TouchEventsMethods.setUpping(cm,x, y);

				}

		  
				cm.getMGestureDetector().onTouchEvent(event);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
				
	}
}
