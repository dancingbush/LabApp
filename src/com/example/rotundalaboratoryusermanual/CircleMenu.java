package com.example.rotundalaboratoryusermanual;

import java.util.ArrayList;

import com.example.rotundalaboratoryusermanual.MenuItemsListeners.OnItemClickListener;
import com.example.rotundalaboratoryusermanual.MenuItemsListeners.OnItemSelectedListener;



import android.app.Activity;
import android.app.DownloadManager;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CircleMenu {
	
	/*
	 * 
	 * each object of this class holds an array of circlemenuItem objects fro diaply in menu
	 * each circlemenuitem object is reprsents each item on teh menu
	 * 
	 * Set item listeners and nameOnItemSelected methods are in this class 
	 */
	

	private boolean flingRunning;

	private ArrayList<CircleMenuItem> circleMenuItemsArray;
	private MenuItemsListeners menuItemsListeners;
	private int firstItem;
	private double teta;

	
	private int flingDelay;
	private int balancingDelay;
	private double radious;
	private int centerX;
	private int centerY;
	

	

	private GestureDetector mGestureDetector;

	private Activity activity;

	

	private double deltaDistance = 0;
	private double MoveX = 0;
	private double MoveY = 0;
	private long s1;
	private long s2;
	private double startAngle = 90;
	private double deltaAngle = 0;
	private double currentAngle = 90;

	public CircleMenu(Activity activity, int radious, int centerX, int centerY,
			int flingDelay, int balancingDelay) {
		circleMenuItemsArray = new ArrayList<CircleMenuItem>();
		flingRunning = false;
		menuItemsListeners = new MenuItemsListeners();

		this.activity = activity;

		this.radious = radious;
		this.centerX = centerX;
		this.centerY = centerY;
		this.firstItem = 0;

		this.flingDelay = flingDelay;
		this.balancingDelay = balancingDelay;

		InitDisplayParams.initDisplayMetrics(activity);

		GestureDetectoring.setGestureDetector(this, activity);
	}

	public CircleMenu(Activity activity, int radious, int centerX, int centerY) {
		circleMenuItemsArray = new ArrayList<CircleMenuItem>();
		flingRunning = false;
		menuItemsListeners = new MenuItemsListeners();

		this.activity = activity;

		this.radious = radious;
		this.centerX = centerX;
		this.centerY = centerY;
		this.firstItem = 0;

		InitDisplayParams.initDisplayMetrics(activity);
		InitDelays.initDelays(this);

		GestureDetectoring.setGestureDetector(this, activity);

	}

	public CircleMenu(Activity activity) {
		circleMenuItemsArray = new ArrayList<CircleMenuItem>();
		flingRunning = false;
		menuItemsListeners = new MenuItemsListeners();

		this.activity = activity;

		InitConstants.initConstants(activity, this);

		GestureDetectoring.setGestureDetector(this, activity);
	}

	public void setCircleMenuItemsArray(ArrayList<CircleMenuItem> circleMenuItemsArray) {

		this.circleMenuItemsArray = circleMenuItemsArray;
		
		visibleLayout();
	}
	
	public void addCircleMenuItems(ArrayList<CircleMenuItem> circleMenuItems) {
		for (int i = 0; i < circleMenuItems.size(); i++) {
			circleMenuItemsArray.add(circleMenuItems.get(i));
		}
		visibleLayout();
	}

	public ArrayList<CircleMenuItem> getCircleMenuItemsArray() {
		return circleMenuItemsArray;
	}

	public void addCircleMenuItem(CircleMenuItem circleMenuItem) {
		circleMenuItemsArray.add(circleMenuItem);
		
		visibleLayout();
	}

	public double getRadious() {
		return radious;
	}

	public void setRadious(double radious) {
		this.radious = radious;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public GestureDetector getMGestureDetector() {
		return mGestureDetector;
	}

	public void setMGestureDetector(GestureDetector mGestureDetector) {
		this.mGestureDetector = mGestureDetector;
	}

	public void removeCircleMenuItem(CircleMenuItem circleMenuItem) {
		circleMenuItemsArray.remove(circleMenuItem);
	}

	public boolean getFlingRunning() {
		return flingRunning;
	}

	public void setFlingRunning(boolean flingRunning) {
		this.flingRunning = flingRunning;
	}

	public int getFirstItem() {
		return firstItem;
	}

	public void setFirstItem(int firstItem) {
		this.firstItem = firstItem;
	}

	public double getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}

	public double getDeltaAngle() {
		return deltaAngle;
	}

	public void setDeltaAngle(double deltaAngle) {
		this.deltaAngle = deltaAngle;
	}

	public double getCurrentAngle() {
		return currentAngle;
	}

	public void setCurrentAngle(double currentAngle) {
		this.currentAngle = currentAngle;
	}


	public double getTeta() {
		return teta;
	}

	
	public void build() {
		initCircleMenuItemsAngles();

		InitCircleMenuItemsPositions.initPositions(this);

		
	}

	public void setMenuItemsListeners(OnItemClickListener oicl,
			OnItemSelectedListener oisl) {

		SetMenuItemsListeners.initListeners(menuItemsListeners, oicl, oisl);

	}

	public MenuItemsListeners getMenuItemsListeners() {

		return menuItemsListeners;

	}

	public int getFlingDelay() {
		return flingDelay;
	}

	public void setFlingDelay(int flingDelay) {
		this.flingDelay = flingDelay;
	}

	public int getBalancingDelay() {
		return balancingDelay;
	}

	public void setBalancingDelay(int balancingDelay) {
		this.balancingDelay = balancingDelay;
	}



	public void setDeltaDistance(double deltaDistance) {
		this.deltaDistance = deltaDistance;
	}

	public void setMoveX(double moveX) {
		MoveX = moveX;
	}

	public void setMoveY(double moveY) {
		MoveY = moveY;
	}

	public void setS1(long s1) {
		this.s1 = s1;
	}

	public void setS2(long s2) {
		this.s2 = s2;
	}

	public double getDeltaDistance() {
		return deltaDistance;
	}

	public double getMoveX() {
		return MoveX;
	}

	public double getMoveY() {
		return MoveY;
	}

	public long getS1() {
		return s1;
	}

	public long getS2() {
		return s2;
	}

	public Activity getActivity() {
		return activity;
	}
	
	private void initCircleMenuItemsAngles() {
		teta = 360.0 / (circleMenuItemsArray.size());
		for (int i = 0; i < circleMenuItemsArray.size(); i++) {
			circleMenuItemsArray.get(i).setAngle(90 + i * teta);
		}

	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			Drawing.drawItemImageviews(CircleMenu.this);
			Drawing.drawHolders(CircleMenu.this);

		}
	};

	public Handler getHandler() {
		return handler;
	}

	private void visibleLayout(){
		LinearLayout ln=(LinearLayout) activity.findViewById(R.id.circlemenulinearlayout);
		ln.setVisibility(View.VISIBLE);
	}
	
}
