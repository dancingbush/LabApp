package com.example.rotundalaboratoryusermanual;

import java.util.ArrayList;

import org.apache.http.RequestLine;


import com.example.rotundalaboratoryusermanual.MenuItemsListeners.OnItemClickListener;
import com.example.rotundalaboratoryusermanual.MenuItemsListeners.OnItemSelectedListener;



import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.AbsoluteLayout;
import android.widget.AdapterView;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class circularmenu extends Activity implements OnItemClickListener,
		OnItemSelectedListener {

	/*
	 * This is the class where we make a object of the CircleMenu class which builds
	 * our menu by taking a array of menu items in the form of CircleMenuItem objects.
	 * Defals layout XML has a ImageView, TExtView and then a item to allow menu rotation
	 * 
	 * INpit items of circe menuItem class constructorconsist of a IMgvoew id no,
	 * a relative layout ID number (contains the imageVoew and TextView), and a strig whoch is the name of the menu item
	 * 
	 * Listeners to the items in the menu can be added here, see imaplmeneted interfaces listeners above
	 * 
	 * After we create a circle menu objecy, pass an array of cirleMenuItem objects to it,
	 * we finall need to call the Biuld method. This method assigns the 
	 * primary angle and intilsiation of the circle menu item postions.
	 * Whenever we add, remove, change the settings (ie raduis of circle menu), the no of circe menu items
	 * we need tocall Build again.
	 * 
	 * The number of itmes you want in the mene u need to change this in the holder_layout_array
	 * Each item will be dislayed according to layout in the hlder_layout.xml
	 * 
	 * 
	 */
	
	 CircleMenu cm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);

	
		
		//build object of circle menu class
		
		cm = new CircleMenu(this);

		// set circle item array to build it , to customise pass your own array here
		//InitMainarams is a default
		
		cm.setCircleMenuItemsArray(InitMainParams.getItems(this, cm));

		// set menu items listeners ( onItemClick and on ItemSelected )
		
		cm.setMenuItemsListeners(this, this);

		/*
		 you can access to circle menu item widgets by using this codes 
		 (use getHolderChildView() method of your sightly circle menu item and pass the method the id of sightly widget!!
		 
		 like below
		 */

		
		InitHolderItemsWidgets.init(cm);
		
		// after that you set your sightly widget for circle menu you must call build() method for circle menu
		
		cm.build();
		
		

		// after any change in number of circle item menus in circle menu , you must call build() method too rebuild the circle menu
		
	}


	
	public void onItemSelected(CircleMenuItem circleItem, String name) {
		// TODO Auto-generated method stub
//		 Log.d("________selected_________", name);
		
		// you can override this method to do your own task when a circle menu item is selected (the circle menu was stopped)
		

	}

	public void onItemClick(CircleMenuItem circleItem, String name) {
		// TODO Auto-generated method stub
//		 Log.d("________clicked_________", name);
		
		// you can override this method to do your own task when a circle menu item is clicked 
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		TouchEventState.setState(cm, event);
		return false;

	}

	


}

