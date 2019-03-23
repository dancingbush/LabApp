package com.example.rotundalaboratoryusermanual;



public class GetPixelScale {

	
	public static double getPixelScale(){
		return (DisplayParams.displayMetrics.densityDpi/240.0);
	}
}
