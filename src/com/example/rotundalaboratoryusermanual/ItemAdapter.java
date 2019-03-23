package com.example.rotundalaboratoryusermanual;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;




import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

//--CURSOR ADPTER FOR POPULATING LIST VIEW WITH DB DATA--

public class ItemAdapter extends CursorAdapter{

	 private LayoutInflater mLayoutInflater;
	    private Context mContext;
	    private File imagePathFile;
	    private Bitmap resizedImage;
	 // for image resizing
		static int w = 50;
		static int h = 50;
		private static final String TAG ="ITEMADTAPTER CLASS: ";
		private int noOfRows;
		private Bitmap bitmap=null;
		//private ImageView displayImage;
		//private String diveImagePath;
		//public getBitmapImage getImageAsynch;
		public setImage setListIMageAycnh;
		private int noOfImagesloaded=0;
		private boolean displayImagesUserChoice=false;
		private View resultView;
		private ViewListOfTests.Animation animation;
		private int prevPosition;
		private int displayWidth;
	    
	    public ItemAdapter(Context context, Cursor c) {
	        super(context, c);
	        mContext = context;
	        mLayoutInflater = LayoutInflater.from(context); 
	        displayWidth = getDisplayWidth(context);
	        
	      // mContext.
	       // noOfRows = c.getCount()+1;//use row count to get no of dives
	    }//end constructor

	    
	    public ItemAdapter(Context context, Cursor c, boolean displayImages) {
	    	//overidden constructor to except boolean value of how to preview list wth or without defaul image
	    	//if false do not load images int IMageview and save time
	        super(context, c);
	        mContext = context;
	        mLayoutInflater = LayoutInflater.from(context); 
	        displayImagesUserChoice=displayImages;
	        
	      
	    }//end constructor

	    
	    public int getDisplayWidth(Context context) {
			final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			return display.getWidth();
		}
	    
	    public void setAnimation(ViewListOfTests.Animation animation) {
	    	
	    	//get referemce enum contstants representing animation choice to ListAvtivity 
			this.animation = animation;
		}

	   
	    
	    @Override
	    public View newView(Context context, Cursor cursor, ViewGroup parent) {
	        
	    	//called once when adpter needs the view
	    	
	    	View resultView = mLayoutInflater.inflate(R.layout.listview, parent, false);
	        //View v = null;
	        
	        //use s Holder object to hold refernces to widgets in LstVoew so they can be resused when not in view
	        
	        ViewHolder holder = new ViewHolder();
    		holder.textLabel = (TextView) resultView.findViewById(R.id.tv_DiveSiteListView);//large text
    		holder.textLabel2 =  (TextView) resultView.findViewById(R.id.tv_diveDateList);//samll text
    		holder.image = (ImageView) resultView.findViewById(R.id.iv_list_image); //image view
    		holder.textLabel3 = (TextView) resultView.findViewById(R.id.tv_diveNumberListView); //test number
    		
    		resultView.setTag(holder);//set the view fro the Cusror Object
	        
	        /*
	         * case switch to chose which animaion we want, chosen form ActionBar menu
    		*animations are coded in Animation classes and set to apdter in the List 
    		* activity ViewListOfDives
    		* If the android OS ver >4.0 ice cream sandwhich we call methods from Animation class
    		* else if <4.0 we call same method form Animation2 class
    		* We must get the position of which row the cursor is currently poiting too to pass
    		* to our anumation method call, this is synomous with baseApter getView(int pos, View, ViewGroup)
    		*  Cursoradpter is a subclass of BaseAdpter
    		*/
    		
    		//must get the poistion that we see in getView(poition, view, parent view group) fro out animtion calls
	    	 
	 	    
   		 /*
 	        int position = cursor.getPosition();//returns the cursor position or row it is pointing too
    		
    		switch (animation) {
    		case CARDS:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doCards(resultView, position, prevPosition);
    			}else{
    				AnimationFactory2.doCards(resultView, position, prevPosition);
    			}
    			break;
    		case CURL:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doCurl(resultView, position, prevPosition, displayWidth);
    			}else{
    				AnimationFactory2.doCurl(resultView, position, prevPosition, displayWidth);
    			}
    			break;
    		case FADE:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doFade(resultView);
    			}else{
    				AnimationFactory2.doFade(resultView);
    			}
    			break;
    		case FLIP:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doFlip(resultView, position, prevPosition);
    			}else{
    				AnimationFactory2.doFlip(resultView, position, prevPosition);
    			}
    			break;
    		case FLY:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doFly(resultView, position, prevPosition);
    			}else{
    				AnimationFactory2.doFly(resultView, position, prevPosition);
    			}
    			break;
    		case GROW:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doGrow(resultView);
    			}else{
    				AnimationFactory2.doGrow(resultView);
    			}
    			break;
    		case REVERSE_FLY:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doReverseFly(resultView, position, prevPosition);
    			}else{
    				AnimationFactory2.doReverseFly(resultView, position, prevPosition);
    			}
    			break;
    		case TWIRL:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doTwirl(resultView, position, prevPosition);
    			}else{
    				AnimationFactory2.doTwirl(resultView, position, prevPosition);
    			}
    			break;
    		case ZIPPER:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doZipper(resultView, position, displayWidth);
    			}else{
    				AnimationFactory2.doZipper(resultView, position, displayWidth);
    			}
    			break;
    		case WAVE:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doWave(resultView, displayWidth);
    			}else{
    				AnimationFactory2.doWave(resultView, displayWidth);
    			}
    			break;
    		}

    		prevPosition = position;
    		//return resultView;
    		
    		
    		
    		*/
    		
    		
	        return resultView;
	        //return v;
	    }//end newView

	    /**
	     * paramteres: The view passed in which the elements we set up here will be displayed
	     * Context: The runnng context where theis list view adapter will be active
	     * C: the Cursor object containing the test DB query results we will display
	     * We want to display the test Name, Department, Image
	     */
	    
	   
			
	    private static final class ViewHolder {
	    	
	    	/*
	    	* holder object which holds places for widgets in the ListView.xml
	    	* when a list row is out of view on screen it allows re-cycling so yhe list row 
	    	* object does not have to be created and inflated wasting time and memory
	    	*/
	    	
			TextView textLabel;   //larger text for test name
			TextView textLabel2;  //smaller sub text for Deprtnament name 
			ImageView image; //holds the image
			TextView textLabel3; //holds the test number
			
		}
	    
	    
	    @Override
	    public void bindView(View v, Context context, final Cursor c) {
	    	
	    	/*
	    	 * Binds the data from each row (stored in cursor object) to the ListView.xml
	    	 * first free up List object if not in view of the screen by holding a ref to it, therefore dont
	    	* waste memeory and tiome recreating each view when out of viewable list to user
	    	*/
	    
	    	//must get the poistion that we see in getView(poition, view, parent view group) fro out animtion calls
	    	 
	 	    
	    		 
	 	        //int pos = c.getPosition();//returns the cursor position or row it is pointing too
	 	    
	    	
	    	ViewHolder holder = (ViewHolder) v.getTag();
	    	
	    	
	    	Log.d(TAG, "CURSOR PASSED TO ITEMADPTER ROWS AND COLUMNS: " + c.getCount() + c.getColumnCount());
	    	
	    	//add row varibles to the VieWHolder object
	    	
	    	String testName = c.getString(c.getColumnIndexOrThrow(database.KEY_TESTNAME));
	    	holder.textLabel.setText(testName);
	    	
	    	String departmentName = c.getString(c.getColumnIndexOrThrow(database.KEY_DEPARTMENT));
	    	holder.textLabel2.setText(departmentName);
	    	
	    	int testNumber = c.getInt(c.getColumnIndexOrThrow(database.KEY_ROWID));
	    	
	    	//move cursor ro last row to get the row number of the test 
	        c.moveToLast();
	        noOfRows = Integer.parseInt(c.getString(c.getColumnIndexOrThrow(database.KEY_ROWID)));
	        
	        
	    	
	    	holder.textLabel3.setText(testNumber+"/"+noOfRows);
	    	Log.d(TAG, "BINDVIEW: TEST NO/TOTAL TESTS: " + testNumber + "/" + noOfRows);
	    	

	        /**
	         * Next set the dive site name of the List view XML
	         * Use the v passed to this method for v.findById
	         */

	       
	        
	        /**
	         * Display the image only of image not null
	         * First get image from String pathname as a file, then convert to Bitmap and resize
	         * fade image in and out animation code
	         */
	    	
	       
	        
	        
	        
	        //use asynch task to set background image of list view, pass teh department to determine the image to display
	        
	        setListIMageAycnh = (setImage) new setImage(v, holder).execute(departmentName);
	        
	        View resultView = v;
	   		 
 	        int position = c.getPosition();//returns the cursor position or row it is pointing too
    		
    		switch (animation) {
    		case CARDS:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doCards(resultView, position, prevPosition);
    			}else{
    				AnimationFactory2.doCards(resultView, position, prevPosition);
    			}
    			break;
    		case CURL:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doCurl(resultView, position, prevPosition, displayWidth);
    			}else{
    				AnimationFactory2.doCurl(resultView, position, prevPosition, displayWidth);
    			}
    			break;
    		case FADE:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doFade(resultView);
    			}else{
    				AnimationFactory2.doFade(resultView);
    			}
    			break;
    		case FLIP:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doFlip(resultView, position, prevPosition);
    			}else{
    				AnimationFactory2.doFlip(resultView, position, prevPosition);
    			}
    			break;
    		case FLY:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doFly(resultView, position, prevPosition);
    			}else{
    				AnimationFactory2.doFly(resultView, position, prevPosition);
    			}
    			break;
    		case GROW:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doGrow(resultView);
    			}else{
    				AnimationFactory2.doGrow(resultView);
    			}
    			break;
    		case REVERSE_FLY:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doReverseFly(resultView, position, prevPosition);
    			}else{
    				AnimationFactory2.doReverseFly(resultView, position, prevPosition);
    			}
    			break;
    		case TWIRL:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doTwirl(resultView, position, prevPosition);
    			}else{
    				AnimationFactory2.doTwirl(resultView, position, prevPosition);
    			}
    			break;
    		case ZIPPER:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doZipper(resultView, position, displayWidth);
    			}else{
    				AnimationFactory2.doZipper(resultView, position, displayWidth);
    			}
    			break;
    		case WAVE:
    			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    				AnimationFactory.doWave(resultView, displayWidth);
    			}else{
    				AnimationFactory2.doWave(resultView, displayWidth);
    			}
    			break;
    		}

    		prevPosition = position;
	       

	        
	        }//end newView
	    
	
	  

	    
	    
	    //asynch class to set the list voew image
	    class setImage extends AsyncTask<String, Void, Integer>{

	    	//constructor takes teh v of listview XML
	    	private View view;
	    	private ViewHolder holderB;
	    	
 			public setImage(View v, ViewHolder holder) {
			// TODO Auto-generated constructor stub, takes ViewGroup as arg
 				//ViewGroup parent;
 				view=v;
 				holderB = new ViewHolder();
		}
 			
			@Override
			protected Integer doInBackground(String... department) {
				// get image and set to background depending in the Department
				
				/*get a random drawable from resources but looks untidy to turn off for now
					
					int[] imageSelection= {R.drawable.buddycheck, R.drawable.logdive3, R.drawable.sea, R.drawable.weather, R.drawable.logo1};
					Random whichImage = new Random();
					//int theImage = whichImage.nextInt(imageSelection.length);
					int theImage = imageSelection[whichImage.nextInt(imageSelection.length)];
					
					displayImage.setBackgroundResource(theImage);
					
					*/
				/*enable to send image depending on dept for listview
				String departmentSelected = department[0];
				if (departmentSelected.contains("Haem")){
					return R.drawable.blood_clot;
				}
				if  (departmentSelected.contains("Trans")){
					return R.drawable.rbc_in_capillary;
				}
				if (departmentSelected.contains("Micro")){
					return R.drawable.micro3;
				}
				if (departmentSelected.contains("Bio")){
					return R.drawable.bio_dna_blue;
				}
				if (departmentSelected.contains("Ref")){
					return R.drawable.biochem_pipetting;
				}
				if (departmentSelected.contains("His")){
					return R.drawable.histology_prof_slides;
				}
				else {
					//retrun a defulat image
					return R.drawable.ic_launcher;
				}
				*/
				
				//enable jsut to send one image only for every list view
				return R.drawable.listview;
				
			}// end do in background

			@Override
			protected void onPostExecute(Integer result) {
				/* recive the int representaion of image to set the
				*backgound form do in back goround
				*and set the image here on UI main thread
				*/
				super.onPostExecute(result);
				
				
				
				//ImageView displayImage = (ImageView) view.findViewById(R.id.iv_list_image);
 				if(result!=null ){
 					
 					
 					//intilse holder to listview.xml ImageView
 					holderB.image = (ImageView) view.findViewById(R.id.iv_list_image); //image view
 					
 					
 					//set image to holder
 					holderB.image.setBackground(null);//claer default image asigned in ListView xml
 					
 					holderB.image.setBackgroundResource(result);
 					
 
 					
 					
 				}else{
 					
 					holderB.image.setBackgroundResource(R.drawable.camera4);
 					
 					Log.d(TAG, "BACKGORUND IMAGE DEAFULT SET IN ADTPER AS NO IMAGE IN ASYCH CLASS SET");
 					
 				}//edn else if result not null
 				
				
			}//end on post execute
			
			
	    	
	    }//end setIMage


}//end ItemADtater class

