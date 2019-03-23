package com.example.rotundalaboratoryusermanual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class mainmenu2 extends Activity{

	/**
	 *This is the second menu  that appears when the app opens teh General Info, uses a GridLayout xml
	 *Its Used to be the Navigator for any section the User wants to open
	 *
	 * <!-- layout_appinfo.xml is the layout for every grid column, so set text and imageview sizes there -->
	 */

		//Gridview to display items, for customization open declaration in XML.
		private GridView mGridMain;
		//private Intent navigateIntent;
		String facebook_link;
		private Animation animateMenuItem;//android animantion, not this project classes aniamtion
		private int[] randomAnimation;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main_menu2);
			facebook_link = getResources().getString(R.string.facebook_link);
			
			
			//get a random animation, either clockwise or couter and add to menu item when selected
			
			int[] randomAnimation ={R.anim.clockwise360, R.anim.counterclockwise360};
			Random whichAnimation = new Random();
			final int animationRandom = randomAnimation[whichAnimation.nextInt(randomAnimation.length)];
			
			
			//fb button
			
			Button facebook = (Button) findViewById(R.id.btn_facebook);
			facebook.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent companyFacebook = new Intent();
					companyFacebook.setAction(Intent.ACTION_VIEW);
					companyFacebook.addCategory(Intent.CATEGORY_BROWSABLE);
					companyFacebook.setData(Uri.parse(facebook_link));
					startActivity(companyFacebook);
				}
			});
			
			
			
			//The following code used to place Icons on the Gridview using helper Adapters.
			
	        mGridMain = (GridView)findViewById(R.id.gvMain);
	        
	        //mGridMain.setColumnWidth(200);
	        mGridMain.setStretchMode(GridView.NO_STRETCH);
	        Resources res = getResources();
	        List<AppInfo> listAppInfo = new ArrayList<AppInfo>();
	        
	        
	        /*
	         * You can replace the existed icons with new ones by only replacing R.drawable.(your Icon).
	         * And you replace the text types under it by changing the last parameter.
	         * Example changing "News" to "RSS Feed", You can customize text written under
	         * every icon (Color/size..etc) in Textview: "tvName" found at the end of "AppInfoAdapter" class
	         */
	        listAppInfo.add(new AppInfo(BitmapFactory.decodeResource(res, R.drawable.news), "Contact & Deparments Detatils"));
	        listAppInfo.add(new AppInfo(BitmapFactory.decodeResource(res, R.drawable.courses), "Operating Times & On Call Service"));
	        listAppInfo.add(new AppInfo(BitmapFactory.decodeResource(res, R.drawable.map), "Map"));
	        listAppInfo.add(new AppInfo(BitmapFactory.decodeResource(res, R.drawable.media), "Gallery"));
	        listAppInfo.add(new AppInfo(BitmapFactory.decodeResource(res, R.drawable.about), "About"));
	        listAppInfo.add(new AppInfo(BitmapFactory.decodeResource(res, R.drawable.contact), "Contact"));
	        mGridMain.setAdapter(new AppInfoAdapter(this, listAppInfo));
		
		
		/**
		 * This is used to response for User click on the Gridview
		 */
	        
	        
		 mGridMain.setOnItemClickListener(new OnItemClickListener() {

			 	/*
			 	 * Note: item position counts from left to right
			 	 * i.e News have position 0, Courses would be position 1, Map = 2, etc...
			 	 */
			 
			 
			 
				@Override
				public void onItemClick(AdapterView<?> arg0, View buttonSelected, int position,
						long arg3) {
					//create Intent to Navigate through the program according on the click position
					final Intent navigateIntent;
					
					
					
					switch (position) {
					
					case 0: //Departmental details
						
						navigateIntent = new Intent(mainmenu2.this, DepartmentDetails.class);
						
						//anaimtimae menu button selected  360 degrees
						animateMenuItem = AnimationUtils.loadAnimation(getApplicationContext(), animationRandom);
						buttonSelected.setAnimation(animateMenuItem);
//craeate a thread that lasts 2 seconds to animation can play its full durarttion
						
						Thread pauseActivityDepts = new Thread(new Runnable(){

							@Override
							public void run() {
								// sleep activity for 2 seconds while anumation execsutes, then call startActivity
								try{
									Thread.sleep(2000);
									
								}catch(Exception ex){
									
									//if thread interrupted for some reason
									
									ex.printStackTrace();
								}finally{
									//this will always excite
									
									startActivity(navigateIntent);
								}
								
								
							}//end run
							
							
							
						});//end thread sllep
						pauseActivityDepts.start();
						
						
						break;
						
						
						
						/*
						try{
						if(isNetworkAvailable()){ //Check connection,if user is online:
							//Open the News section:
							//navigateIntent = new Intent(Main.this, News.class);
							//startActivity(navigateIntent);
						} else if(!isNetworkAvailable()){ //if user is offline
							Toast.makeText(getApplicationContext(), "Please check Internet connection", 2200).show();
							
							}
						} catch (Exception e) {
							//Else if website/rss is down, Toast this message:
							Toast.makeText(getApplicationContext(), "News unavailble try again later", 2200).show();
						}
						break;
						
						*/
						
						
						
					case 1: //on call service
						
						navigateIntent = new Intent(mainmenu2.this, onCallService.class);
						
					//startActivity(navigateIntent);
					
					//anaimtimae menu button selected  360 degrees
					animateMenuItem = AnimationUtils.loadAnimation(getApplicationContext(), animationRandom);
					buttonSelected.setAnimation(animateMenuItem);
//craeate a thread that lasts 2 seconds to animation can play its full durarttion
					
					Thread pauseActivitycall = new Thread(new Runnable(){

						@Override
						public void run() {
							// sleep activity for 2 seconds while anumation execsutes, then call startActivity
							try{
								Thread.sleep(2500);
								
							}catch(Exception ex){
								
								//if thread interrupted for some reason
								
								ex.printStackTrace();
							}finally{
								//this will always excite
								
								startActivity(navigateIntent);
							}
							
							
						}//end run
						
						
						
					});//end thread sllep
					pauseActivitycall.start();
					
					
					
					
					
					
					
						break;
						
						
						
						
						
					case 2: //Map
						
						navigateIntent = new Intent(mainmenu2.this, CompanyMap.class);
						
						//anaimtimae menu button selected  360 degrees
						animateMenuItem = AnimationUtils.loadAnimation(getApplicationContext(), animationRandom);
						buttonSelected.setAnimation(animateMenuItem);
//craeate a thread that lasts 2 seconds to animation can play its full durarttion
						
						Thread pauseActivityMap = new Thread(new Runnable(){

							@Override
							public void run() {
								// sleep activity for 2 seconds while anumation execsutes, then call startActivity
								try{
									Thread.sleep(2500);
									
								}catch(Exception ex){
									
									//if thread interrupted for some reason
									
									ex.printStackTrace();
								}finally{
									//this will always excite
									
									startActivity(navigateIntent);
								}
								
								
							}//end run
							
							
							
						});//end thread sllep
						pauseActivityMap.start();
						
						break;
						
						
					case 3: //Gallery
						//navigateIntent = new Intent(Main.this, GalleryActivity.class);
						//startActivity(navigateIntent);
						break;
						
						
						
						
					case 4: //About
						
						//anaimtimae menu button selected  360 degrees
						animateMenuItem = AnimationUtils.loadAnimation(getApplicationContext(), animationRandom);
						buttonSelected.setAnimation(animateMenuItem);
						
						navigateIntent = new Intent(mainmenu2.this, about.class);
						//craeate a thread that lasts 2 seconds to animation can play its full durarttion
						
						Thread pauseActivity = new Thread(new Runnable(){

							@Override
							public void run() {
								// sleep activity for 2 seconds while anumation execsutes, then call startActivity
								try{
									Thread.sleep(2500);
									
								}catch(Exception e){
									
									//if thread interrupted for some reason
									
									e.printStackTrace();
								}finally{
									//this will always excite
									startActivity(navigateIntent);
								}
								
								
							}//end run
							
							
							
						});//end thread sllep
						pauseActivity.start();
						//startActivity(navigateIntent);
						break;
						
						
					case 5: //Contact Us
						//navigateIntent = new Intent(Main.this, Contact_us.class);
						//startActivity(navigateIntent);
						break;
					default:
						break;
					}
				}
			});
	 }

	/**
	 * This method checks wether the User is online or not
	 * @return true if User is online, return false otherwise.
	 */
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(mainmenu2.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null;
	}
	

}
