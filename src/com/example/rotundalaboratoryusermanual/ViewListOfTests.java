package com.example.rotundalaboratoryusermanual;




//this is a List, using the customized CursorAdtper from ItemAdapter


import android.R.menu;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SpinnerAdapter;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class ViewListOfTests  extends ListActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener, 
ActionBar.OnNavigationListener{
	
	/*this is a list activity with animation and a search box
	*using a customised cursor adaoter form the Itemadpter class
	*The selected item passes the row number to the ViewSelectedTest class
	**/

	private static database data;
	private Cursor cursor;
	private ItemAdapter adapter;
	private ProgressDialog pd;
	private getCursor getCursorAysnch;
	private boolean preiwImagesInListView=false;
	private int value=1;
	private SearchView searchView;
	private boolean searchPerformed = false;
	private Cursor newCursor;
	private ItemAdapter searchAdapter;
	private int noOftimesOnCreateCalled=0;
	//animation constants
	public enum Animation {
		CURL, TWIRL, ZIPPER, FADE, FLY, REVERSE_FLY, FLIP, CARDS, GROW, WAVE, NULL;
	}
	
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		/*
		 * Position is the position on the list nd statrts from 0 from top to bttom 
		 * depending on number of items in list, which will change when we filter or search
		 * id is the row id of the list displayed or ow id of the item that was clicked
		 *  Send the row number of test selected to the SelectedTestDetails class
		 */
		
		
		
		super.onListItemClick(l, v, position, id);
		
		/*
		 * by assigning he row number here we keep sending row 7 (alcohol) to SeleectedTest, not sure why
		 * But when using a cursor the id = row number anyawy so dont need this
		 */
		
		//String row = cursor.getString(cursor.getColumnIndexOrThrow(database.KEY_ROWID));
		
		String row = String.valueOf(id);
		Intent i = new Intent("android.intent.action.SELECTEDTESTDETAILS");
		Bundle extras = new Bundle();
		extras.putString("row", row);
		i.putExtras(extras);
		startActivityForResult(i, 0);
		Log.d("LISTVIEW", "SENDING ROW TO SELECTED TEST: " + row +" POSITION PASSED IS: " + position + " ROW ID PASSED IS:" +id);
		//Toast.makeText(getBaseContext(), "Row selected: "+ row, Toast.LENGTH_LONG).show();
		finish();//free resources and when backbutton pressed in selected test activity it will go back to main meu

	}// end on list item clicked

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		//ask the user whch prevoew woth or without images , prob wont make a diff in this app
		//as not time consuming
		//previewDives();
		// Set up the screen to be full screen with no title bar etc using
				// WindowsManager. Must be called before seting content view
				/*
				this.requestWindowFeature(Window.FEATURE_NO_TITLE);
				this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
						WindowManager.LayoutParams.FLAG_FULLSCREEN);
		*/
		//set content view of this list activity so we can add a search bar to top of it (parent anchor)
		
				this.setContentView(R.layout.list_activity);
		
				//intilise the action bar to allow selection of animations
				 initActionBar();
				 
				 
				 // Associate searchable configuration with the SearchView
				 searchView = (SearchView) findViewById(R.id.search);
			        searchView.setIconifiedByDefault(false);
			        searchView.setOnQueryTextListener(this);
			        searchView.setOnCloseListener( this);
			        searchView.setSoundEffectsEnabled(true);
			        
		

		/*call the aysnch inner class to load cursor form Db and set teh customer adter to this list view
		*off the main UI thread, cast to type (getCursor)
		*Asynch class does not have a constructor in this case
		**/
			  
			        
			        //set default list with all tests
			        
			    getCursorAysnch = (getCursor) new getCursor().execute();
			
	}// end on create

	
	
	
	private void initActionBar() {
		// intilize action bar for drop down menu with choice of animation for the list view
		SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.animation_list,
				android.R.layout.simple_spinner_dropdown_item);

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setListNavigationCallbacks(adapter, this);
	}



	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	  super.onSaveInstanceState(savedInstanceState);
	  // Save UI state changes to the savedInstanceState.
	  // This bundle will be passed to onCreate if the process is
	  // killed and restarted ie when we go ot ItemAdter to craete a new adpter with serach 
	  
	  //savedInstanceState.putBoolean(key, false)
	  savedInstanceState.putBoolean("MyBoolean", true);
	  savedInstanceState.putDouble("myDouble", 1.9);
	  savedInstanceState.putInt("MyInt", 1);
	  savedInstanceState.putString("MyString", "Welcome back to Android");
	  // etc.
	}

	
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	  super.onRestoreInstanceState(savedInstanceState);
	  // Restore UI state from the savedInstanceState.
	  // This bundle has also been passed to onCreate.
	  searchPerformed = savedInstanceState.getBoolean("MyBoolean");
	  double myDouble = savedInstanceState.getDouble("myDouble");
	   noOftimesOnCreateCalled = savedInstanceState.getInt("MyInt");
	  String myString = savedInstanceState.getString("MyString");
	}
	
	
	/*
	@Override
	protected void onNewIntent(Intent intent) {
		// call handleIntent from here to handle the query from serah widget
		
		super.onNewIntent(intent);
		
		handleIntent(intent);
		
	}//end onNewIntent




	private void handleIntent(Intent intent) {
		// get the query form search and search database & return cursor
		
		if (Intent.ACTION_SEARCH.equals(intent.getAction())){
			String query = intent.getStringExtra(SearchManager.QUERY);
			
			Log.d("LISTACTIVITY", "QUERY = "+ query);
			
			Cursor c = data.getWordMatches(query, null);
			
			Log.d("LISTACTIVITY", "CURSOR QUERY ROWS/COLS = "+ c.getCount()+" "+c.getColumnCount());
			
			//now set bind cursor data to list view using custim ItemAdpter class
			adapter = new ItemAdapter(ViewListOfTests.this, c);
			this.setListAdapter(adapter);
			
			//search database
		}
		
	}//end handleIntent

*/


	private void previewDives() {
		//ask user if they wish to preview images in ist view as will take more time
				AlertDialog.Builder build = null;
				AlertDialog dialog = null;
				build = new AlertDialog.Builder(this);
				build.setCancelable(true);
				build.setMessage("Do you want to preview your Dive Site images? \nThis may take more time if you have a large number of dives");
				build.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// preview dives
								preiwImagesInListView= true;
								//call the asynch task to get cusror and create the adpter and set the list adpter
								getCursorAysnch = (getCursor) new getCursor().execute();
								value++;
								//return;//exit

							}// end on click
						});// end pos button take photo

				// get photo from SD card option
				build.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								preiwImagesInListView=false;
								//call the asynch task to get cusror and create the adpter and set the list adpter
								getCursorAysnch = (getCursor) new getCursor().execute();
								value++;
								//return;//exit to oncreate

							}// end onclick
						});// end pos button take photo
				dialog = build.create();
				dialog.setTitle("Preview Dive Images");
				dialog.show();
				
				//return preiwImagesInListView;
				
		
	}




	private void displayDialog(String message) {
		//display dialog of there are no entries in the database and finish the activity
		
		String theMessage= message;
		AlertDialog.Builder build = null;
		AlertDialog dialog = null;
		build = new AlertDialog.Builder(this);
		build.setCancelable(true);
		build.setMessage(theMessage);
		build.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// display deafuls adpter with all tests
						getCursorAysnch = (getCursor) new getCursor().execute();

					}// end on click
				});// end pos button take photo

		// get photo from SD card option
		build.setNegativeButton("Main Menu",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();

					}// end onclick
				});// end pos button take photo
		dialog = build.create();
		dialog.setTitle("Your Search");
		dialog.show();
		
		
	}//end display dalog





	
	
	private final class getCursor extends AsyncTask<Void, Void, Cursor>{

		protected void onPreExecute(){
			//show dialog while loading data
			pd = new ProgressDialog( ViewListOfTests.this, ProgressDialog.STYLE_HORIZONTAL);
			pd.setMessage("Loading all Labortory tests....");
			pd.show();
			
		}
		
		

		@Override
		protected void onPostExecute(Cursor cursor) {
			
			
						//check if data available in db and if so pass the cursor to the constructor of ItemAdtpter
						//and create a cutsomer adter t add to this ListView
			
			
						if(cursor!=null && cursor.getCount()>0){
						
							//previewDives();
							Log.d("View List AYCNH", "GETTOMG ADPTER IN VIEW LIST OF TESTS/ROWS :" + cursor.getCount());
							
						//adapter = new ItemAdapter(ViewListOfDives.this, cursor, preiwImagesInListView);
							adapter = new ItemAdapter(ViewListOfTests.this, cursor);
							
							//set deafult curl anmation to the adpter
							
							//adapter = new MyListAdapter(this);
							
							
							//adapter.setAnimation(Animation.GROW);
							adapter.setAnimation(Animation.NULL);
							//adapter.add(stringList);
							//adapter = new ItemAdapter(ViewListOfDives.this, cursor);
						}else{
							
								//display the databse and cursor s empty 
								displayDialog("Databse  is empty!");
							
						}
						
						
						//getListView().setAdapter(adapter);
						
						
						
						ViewListOfTests.this.setListAdapter(adapter);
						
						
						//cursor.close();
						
						//do not clpse db otherwise get a nullpointer exception in db.close() when assesing DB using mulyple threads
						//ie in doff activities
						
						//ViewListOfTests.this.data.close();
						//ViewListOfDives.this.setListAdapter(adapter);
						//ViewListOfDives.data.close();
						
			super.onPostExecute(cursor);
			// dispose dialog
						if(pd.isShowing()){
									pd.dismiss();
						}
			//}//end if value
		}



		
		@Override
		protected Cursor doInBackground(Void... params) {
			// get the cursor from database and pass to onPOstExccute to set adpter
			
			
			//if back button is pressedwe want to stop loading the cursor
			if(!isCancelled()){
				ViewListOfTests.data = new database(ViewListOfTests.this);
						//ViewListOfTests.data = new diveDataBase(ViewListOfDives.this);
						ViewListOfTests.data.open();
						// get cursor object holding all data, use a asynch inner class to load 
						
						cursor = data.getCursorData();
						

						
						//ViewListOfDives.data.close();
			}//edn if no cancelled by back button pressed
						
			return cursor;
		}
		
		
	}//end getCursor asynch






	@Override
	protected void onPause() {
		// try to quit cursoradpter from reriving and upload data when user clicks back button
		/*
		ViewListOfTests.data.close();
		cursor=null;
		//adapter=null;
		getCursorAysnch.cancel(true);
		//now cancel backgound process of Itemadatpetr class to free memory
				//adapter.getImageAsynch.cancel(true);
		Log.d("View Tests", "On pause back button clicked");
		*/
		super.onPause();
	}




	@Override
	public void onBackPressed() {
		// try to quit cursoradpter from reriving and upload data when user clicks back button
		super.onBackPressed();
		//cancel teh background process of asycn task
		/*
		getCursorAysnch.cancel(true);
		
		
		//now cancel backgound process of Itemadatpetr class to free memory
		adapter.getImageAsynch.cancel(true);
		Log.d("Vuiew List Dives:", "Back button pressed");
		
		
		//((adapter)ItemAdapter.).finish();
		//ViewListOfTests.data.close();
		cursor=null;
		adapter=null;
		//finish();
		*/
		
	}




	@Override
	public boolean onClose() {
		// load defult adpter when the searchview has been closed by user
		
		getCursorAysnch = (getCursor) new getCursor().execute();
		return false;
	}



	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		//handleIntent(intent);
		 if (!newText.isEmpty()){
			 displaySearchResults(newText);
	        } else {
	        	//set default adpter with all data off main thread
	        	getCursorAysnch = (getCursor) new getCursor().execute();
	        }
	 
		
		
		return false;
	}




	private void displaySearchResults(String newText) {
		// TODO Auto-generated method stub
		
		Log.d("LIST ACTIVITY SEARCH", "onQueryTextCahanged called: "+ newText);
		//data = new database(this);
		
		String queryText= newText + "*";
		
		//Cursor c = data.searchByInputText(queryText);
		
		//
	Cursor c = data.getWordMatches(newText, null);
		
		Log.d("LISTACTIVITY ", "DISPLAYSEARCHRESULTS MEHOD: CURSOR QUERY ROWS/COLS = "+ c.getCount()+" "+c.getColumnCount());
		
		//now set bind cursor data to list view using custim ItemAdpter class
		//ItemAdapter newAdapter = new ItemAdapter(ViewListOfTests.this, c);
		//newAdapter.notifyDataSetChanged();
		//ViewListOfTests.this.setListAdapter(newAdapter);
		searchAdapter= new ItemAdapter(this, c);
		
		int rows = c.getCount();
		
		searchAdapter.setAnimation(Animation.GROW);
		
		setListAdapter(searchAdapter);
		searchPerformed=true;
		
		//searchView.setQuery("", true);
		if(c.getCount()==0){
			displayDialog("No Results Found for: " + newText);
		}
		
	}




	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		displaySearchResults(query);
		return false;
	}




	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		//set animation to the default adpter , setAnimation is defined in Iteadpter Cursor adpter class
		
		adapter.setAnimation(Animation.values()[itemPosition]);
		return true;
	}
	
	

}// end class
