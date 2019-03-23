package com.example.rotundalaboratoryusermanual;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenu extends Activity implements OnClickListener{

	private Button allTests, buttonTwo, buttonThree, buttonFour;
	private database emptyDB;
	private static String TAG= "MAIN MENU:";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.mainmenu);
		
		
		//first open databse and copy DB from assets if doesnt exist on device
		//create a new instance of DataBaseHelper class and call 
				//the createDataBase() and openDataBase() methods
				 database myDbHelper = new database(this);
			        
			 
			        try {
			 
			        	myDbHelper.createDataBase();
			 
			 	} catch (IOException ioe) {
			 
			 		throw new Error("Unable to create database");
			 
			 	}
			 
			 	try {
			 
			 		myDbHelper.open();
			 
			 	}catch(SQLException sqle){
			 
			 		sqle.printStackTrace();
			 		Log.d("MAIN MENU", "SQL EXC: "+ sqle.getLocalizedMessage());
			 		throw sqle;
			 
			 	}
			 
			 	//test db contents
			 	//open database and get a;; contacts for list view
				try{
					//database db = new database(this);
					//db.open();
					String allTests = new String();
					allTests = myDbHelper.getAllDataAsString();
					
					Cursor c = myDbHelper.getCursorData();
					int cols = c.getColumnCount();
					int rows = c.getCount();
					int rows2 =myDbHelper.getNumberOfRows();
					
					//int bpog =00441142717404	;
					String details = myDbHelper.getTestName(1);
					//allData.setText("no of columsn: " +cols+ "no of rwos: "+rows + "Sepcfic Row data in DB: "+ details+ "And all data:\n" + allTests);
					Log.d(TAG, "DB NO OF COLUMNS: "+ myDbHelper.getCursorData().getColumnCount() + "NO OF ROWS: " + c.getCount() );
					
					//myDbHelper.close();
					c.close();
				}catch(Exception sql){
					sql.printStackTrace();
				}
				
				intilizeWidgets();	
				
			}//end onCreate

			

			
			
		
		
		
	
		


private void CopyDB(InputStream open, FileOutputStream fileOutputStream) throws IOException{
	// copy databse from assets to emulator device 1k byte at a time
	
	//first open a file DB taht was created earlier so can over right
	emptyDB.open();
	
	
	byte[] buffer = new byte[1024];
	int lenght;
	while ((lenght = open.read(buffer))>0){
		fileOutputStream.write(buffer, 0, lenght);
	}//end while
	open.close();
	fileOutputStream.close();
	emptyDB.close();
}//copyDB

	private void intilizeWidgets() {
		// intilize all widgets and set listeners to buttons
		
		allTests = (Button) this.findViewById(R.id.buttonAllTests);
		allTests.setOnClickListener(this);
		buttonTwo = (Button) this.findViewById(R.id.buttonTwo);
		buttonTwo.setOnClickListener(this);
		buttonThree = (Button) this.findViewById(R.id.buttonThree);
		buttonThree.setOnClickListener(this);
		buttonFour = (Button) this.findViewById(R.id.buttonFour);
		buttonFour.setOnClickListener(this);
		
	}//end intilizeWidgets

	@Override
	public void onClick(View button) {
		// listeners for buttons
		
		switch (button.getId()){
		
		case R.id.buttonAllTests:
			//call list view class
			//call the About_Us class
			Intent callClass = new Intent(MainMenu.this, ViewListOfTests.class);
			startActivity(callClass);
			
			//Intent callClass = new Intent("com.mooneycallans.buckystutorials.ABOUT_US");
			//startActivity(callClass);
			break;
			
		case R.id.buttonTwo:
			
			// Alternate way to create new activty via java class varible
			try {
				// get intent accroding to pacakagemname.className and start new activity
				Class ourClass = Class.forName("com.example.rotundalaboratoryusermanual.MainActivity");// append the list otem clicked to path fro intent
									// new actvity
				Intent ourIntent = new Intent(MainMenu.this, MainActivity.class);// (context,
																	// classNAme)
				this.startActivity(ourIntent);
				//finish();
			
			} catch (ClassNotFoundException e) {
				Toast.makeText(this, "Class Not Found ", Toast.LENGTH_SHORT);
				e.printStackTrace();
			}
			//Intent callClass2 = new Intent("com.example.rotundalaboratoryusermanual.MAINACTIVITY");
			//startActivity(callClass2);
			break;
			
		case R.id.buttonThree:
			
			
			Intent navigateIntent = new Intent(MainMenu.this, mainmenu2.class);
			startActivity(navigateIntent);
			
			break;
			
case R.id.buttonFour:
			
	
	/*
	 * call the circular menu class form circular menu project	which is dependcy in build path of this project
	 * Syntax  projectName.pacakagename.class
	 */
	
			Intent navigateIntent4 = new Intent(MainMenu.this, circularmenu.class);
			startActivity(navigateIntent4);
			
			break;
			
		}//end switch
	}//end onClickListener

	
	
	
	

}//edn amin menu
