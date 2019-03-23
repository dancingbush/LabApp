package com.example.rotundalaboratoryusermanual;

import android.graphics.Bitmap;

public class AppInfo {
	
	
	/*
	 * Used for populating the grid voew with AppInfoAdpter which populates list to
	 * put ntot he gridview in the mainmenu2 xml
	 * 
	 * 
	 */
	private Bitmap mIcon;
	private String mName;
	
	public AppInfo(Bitmap icon, String name) {
		mIcon = icon;
		mName = name;
	}
	
	public void setIcon(Bitmap icon) {
		mIcon = icon;
	}
	public Bitmap getIcon() {
		return mIcon;
	}
	
	public void setName(String name) {
		mName = name;
	}
	public String getName() {
		return mName;
	}

}
