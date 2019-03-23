package com.example.rotundalaboratoryusermanual;

import java.util.ArrayList;
import java.util.TreeSet;

import org.w3c.dom.Node;



import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CircleMenuItem {
	
	/*
	 * represents a singluar item on the menu
	 * Grouped into an array of type circleenuitem and passed to circle menu construvtor
	 * this class consists fo two parts:
	 * A. MenuItemImageview : this allows rotating the menu, place an image here ie arrow
	 * B.Holderlayout: A relativelaout that belongs to menuitem class, holds al information for menu ie image and text
	 * 
	 * It is possible tp set your own layout to the HolderLayout ain a construvtor of this class
	 * `The satndard XML realfuvelayout (called Holder) contains a Imagevoew and a textview
	 * 
	 */

	private double angle;
	
	private Activity activity;
	
	private ImageView menuItemImageView;
	
	private RelativeLayout holderItem;
	
	
	private String name;
	private double menuItemImageViewWidth;
	private double menuItemImageViewHeight;

	
	public CircleMenuItem(Activity activity, int menuImageId, int holderItemId,String name) {
		this.activity = activity;
        this.name=name;
		menuItemImageView = (ImageView) activity.findViewById(menuImageId);
		this.holderItem = (RelativeLayout) activity.findViewById(holderItemId);
		
		setMenuItemImageViewLayout(DisplayParams.displayMetrics);
		
	}


private void setMenuItemImageViewLayout(DisplayMetrics dm){
	
		menuItemImageViewWidth=menuItemImageView.getLayoutParams().width
			*(dm.density/2.0);
		menuItemImageViewHeight=menuItemImageView.getLayoutParams().height*(dm.density/2.0);
		menuItemImageView.setLayoutParams(new LinearLayout.LayoutParams((int) menuItemImageViewWidth,
				(int)menuItemImageViewHeight));
		
}

public double getMenuItemImageViewWidth() {
	return menuItemImageViewWidth;
}

public double getMenuItemImageViewHeight() {
	return menuItemImageViewHeight;
}

	public String getName() {
		return name;
		
	
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageView getMenuItemImageView() {
		
		return menuItemImageView;
	}

	public void setMenuItemImageView(ImageView menuItemImageView) {
		this.menuItemImageView = menuItemImageView;
	}

	public void setMenuItemImageView(int id) {

		menuItemImageView = (ImageView) activity.findViewById(id);
	}

	

	public RelativeLayout getHolderItem() {
		return holderItem;
	}

	public void setHolderItem(RelativeLayout holderItem) {
		this.holderItem = holderItem;
	}

	public void setHolderItem(int id) {
		this.holderItem = (RelativeLayout) activity.findViewById(id);
	}

public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public View getHolderChildView(int id){
		return holderItem.findViewById(id);
	}

}
