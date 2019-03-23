package com.example.rotundalaboratoryusermanual;




import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public class InitConstants {
	

	
	
	public static void initConstants(Activity activity,CircleMenu cm){
		
		InitDisplayParams.initDisplayMetrics(activity);
	
		cm.setCenterX(DisplayParams.displayMetrics.widthPixels / 2) ;
		cm.setCenterY(DisplayParams.displayMetrics.heightPixels+DisplayParams.displayMetrics.heightPixels/10) ;
		
		cm.setRadious(DisplayParams.displayMetrics.widthPixels / 2 + 50*(DisplayParams.displayMetrics.density/2.0)) ;
				
		cm.setFirstItem(0);

	InitDelays.initDelays(cm);
		
	}
}
