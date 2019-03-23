package com.example.rotundalaboratoryusermanual;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;






public class FinalBalancing {


	
	public static void finalBalancing(final CircleMenu cm){
		
		for (int i = 0; i < cm.getCircleMenuItemsArray().size(); i++) {
			int index = (cm.getFirstItem() + i) % (cm.getCircleMenuItemsArray().size());
			cm.getCircleMenuItemsArray().get(index).setAngle(90.0 + (i * cm.getTeta()));
			
			
		
		}
	
		cm.setDeltaAngle(0) ;
		cm.getHandler().sendMessage(cm.getHandler()
				.obtainMessage());
		
		cm.setFlingRunning(false);
		
//		cm.activity.runOnUiThread(new Runnable() {
//			
//			public void run() {
//				// TODO Auto-generated method stub
//				for (int i = 0; i < cm.getCircleMenuItemsArray().size(); i++) {
//					
//				    Animation shake = AnimationUtils.loadAnimation(cm.activity, R.anim.shake);
//				   cm.getCircleMenuItemsArray().get(i).menuItemImageView.startAnimation(shake);
//				  
//			}	
//			}
//		});
		
		
	}
	
}
