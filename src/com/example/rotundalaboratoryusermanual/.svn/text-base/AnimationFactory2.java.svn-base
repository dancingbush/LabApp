package com.example.rotundalaboratoryusermanual;

import android.annotation.TargetApi;
import android.view.View;
import android.view.animation.*;

@TargetApi(1)
public class AnimationFactory2 {

	/*contains the animations for android OS <4.0
	* these animations are implemented in the ViewListOfTests List Activity and the ItemAdpter
	* The array fro lost of animations is availble from the action bar menu coded in the ListActovited VoewlistOfDives
	* the animations are called from this class in the item adtpter by a switch:case statement form choice from ActionBar menu
	*/
	
	
	public static void NULL(View view, int position, int prevPosition, int displayWidth) {
		//do nothing, we want to be able to set the animation to null intially and when we do not want it
	}
	
	
	public static void doCurl(View view, int position, int prevPosition, int displayWidth) {
		AnimationSet animationSet = new AnimationSet(true);

		ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.0f, 1.0f, 1.0f);

		TranslateAnimation translateAnimation;
		RotateAnimation rotateAnimation;
		if (position > prevPosition) {
			rotateAnimation = new RotateAnimation(90, 0);
			translateAnimation = new TranslateAnimation(-displayWidth, 0.0f, 0.0f, 0.0f);
		} else {
			rotateAnimation = new RotateAnimation(-90, 0);
			translateAnimation = new TranslateAnimation(displayWidth, 0.0f, 0.0f, 0.0f);
		}

		animationSet.addAnimation(scaleAnimation);
		animationSet.addAnimation(translateAnimation);
		animationSet.addAnimation(rotateAnimation);
		animationSet.setDuration(300);

		view.startAnimation(animationSet);
	}

	public static void doZipper(View view, int position, int displayWidth) {

		AnimationSet animationSet = new AnimationSet(true);

		ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.0f, 1.0f, 1.0f);

		TranslateAnimation translateAnimation;
		if (position % 2 == 0) {
			translateAnimation = new TranslateAnimation(displayWidth, 0.0f, 0.0f, 0.0f);
		} else {
			translateAnimation = new TranslateAnimation(-displayWidth, 0.0f, 0.0f, 0.0f);
		}

		animationSet.addAnimation(translateAnimation);
		animationSet.addAnimation(scaleAnimation);
		animationSet.setDuration(300);

		view.startAnimation(animationSet);
	}

	public static void doFade(View view) {
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation.setDuration(300);
		view.startAnimation(alphaAnimation);
	}

	public static void doGrow(View view) {
		ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(300);
		view.startAnimation(scaleAnimation);
	}

	public static void doWave(View view, int displayWidth) {
		TranslateAnimation translateAnimation = new TranslateAnimation(-displayWidth, 0.0f, 0.0f, 0.0f);
		translateAnimation.setDuration(300);
		view.startAnimation(translateAnimation);
	}

	public static void doReverseFly(View view, int position, int prevPosition) {
		// TODO not implemented
	}

	public static void doFly(View view, int position, int prevPosition) {
		// TODO not implemented
	}

	public static void doFlip(View view, int position, int prevPosition) {
		// TODO not implemented
	}

	public static void doCards(View view, int position, int prevPosition) {
		// TODO not implemented
	}

	public static void doTwirl(View view, int position, int prevPosition) {
		// TODO not implemented
	}


}
