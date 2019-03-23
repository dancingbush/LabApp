package com.example.rotundalaboratoryusermanual;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(12)
public class AnimationFactory {

	/*contains the animations for android OS <4.0
	* these animations are implemented in the ViewListOfTests List Activity and the ItemAdpter
	* The array fro lost of animations is availble from the action bar menu coded in the ListActovited VoewlistOfDives
	* the animations are called from this class in the item adtpter by a switch:case statement form choice from ActionBar menu
	*/
	
	public static void NULL(View view, int position, int prevPosition, int displayWidth) {
		//do nothing, we want to be able to set the animation to null intially and when we do not want it
	}
	
	public static void doCurl(View view, int position, int prevPosition, int displayWidth) {
		view.setScaleX(0.5f);
		if (position > prevPosition) {
			view.setRotation(90);
			view.setX(-displayWidth);
		} else {
			view.setRotation(-90);
			view.setX(displayWidth);
		}

		view.animate().rotation(0).x(0).scaleX(1.0f).start();
	}

	public static void doTwirl(View view, int position, int prevPosition) {
		view.setScaleX(0.5f);
		if (position > prevPosition) {
			view.setRotationX(-90);
			view.setRotationY(-90);
		} else {
			view.setRotationX(90);
			view.setRotationY(-90);
		}

		view.animate().rotationX(0).rotationY(0).scaleX(1.0f).start();
	}

	public static void doZipper(View view, int position, int displayWidth) {
		view.setScaleX(0.5f);
		if (position % 2 == 0) {
			view.setX(-displayWidth);
		} else {
			view.setX(displayWidth);
		}
		view.animate().x(0).scaleX(1.0f).start();
	}

	public static void doFade(View view) {
		view.setAlpha(0);
		view.animate().alpha(1).start();
	}

	public static void doReverseFly(View view, int position, int prevPosition) {
		view.setScaleX(0.5f);
		if (position > prevPosition) {
			view.setRotationX(180);
		} else {
			view.setRotationX(-180);
		}
		view.animate().rotationX(0).scaleX(1.0f).start();
	}

	public static void doFly(View view, int position, int prevPosition) {
		view.setScaleX(0.5f);
		if (position > prevPosition) {
			view.setRotationX(-180);
		} else {
			view.setRotationX(180);
		}
		view.animate().rotationX(0).scaleX(1.0f).start();
	}

	public static void doFlip(View view, int position, int prevPosition) {
		view.setScaleX(0.5f);
		if (position > prevPosition) {
			view.setRotationX(-90);
		} else {
			view.setRotationX(90);
		}
		view.animate().rotationX(0).scaleX(1.0f).start();
	}

	public static void doWave(View view, int displayWidth) {
		view.setX(-displayWidth);
		view.animate().x(0).start();
	}

	public static void doCards(View view, int position, int prevPosition) {
		view.setScaleX(0.5f);
		if (position > prevPosition) {
			view.setRotationX(90);
		} else {
			view.setRotationX(-90);
		}
		view.animate().rotationX(0).scaleX(1.0f).start();
	}

	public static void doGrow(View view) {
		view.setScaleY(0.0f);
		view.setScaleX(0.0f);
		view.animate().scaleY(1.0f).scaleX(1.0f).start();
	}
}
