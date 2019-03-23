package com.example.rotundalaboratoryusermanual;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private static final String TAG="Main Menu CLASS";
	private EditText allData;
	private String allTests;
	private database emptyDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		intilizeWidgets();
		
		
		
		
		/*
		//copy  the pre made  database from the assest folder to the path pf emulator device
		try{
			//String dePath = this.getDatabasePath("testsdb");
			String desPath ="data/data/" + getPackageName() + "/databases/testsdb";
			//File f = new File(desPath);
			File f = getDatabasePath("testsdb");
			
			//copydb takes copied db from assets as inputstream and copies to empty db in desPath
			if (!f.exists()){
				
				//frst we must create an empty file to over right and avoid FileNoFoundException
				InputStream iStream = getBaseContext().getAssets().open("testsdb");
				 emptyDB = new database(this);
				 
				emptyDB.open();
				emptyDB.close();
				//important to close otherwise Tables may be overwritten with no data
				
				FileOutputStream oStream = new FileOutputStream(desPath);
				CopyDB(iStream, oStream);
				
				//CopyDB(getBaseContext().getAssets().open("testsdb"), new FileOutputStream(desPath));
				Log.d(TAG, "DB Copied succesfully!");
			}
			
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			Log.d(TAG, ex.getMessage());
			
			
		}catch(IOException e){
			e.printStackTrace();
			Log.d(TAG, e.getMessage());
		}
		
		*/
		
		
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
	 		Log.d(TAG, "SQL EXC: "+ sqle.getLocalizedMessage());
	 		throw sqle;
	 
	 	}
	 
		//open database and get a;; contacts for list view
		try{
			//database db = new database(this);
			//db.open();
			allTests = new String();
			allTests = myDbHelper.getAllDataAsString();
			
			Cursor c = myDbHelper.getCursorData();
			int cols = c.getColumnCount();
			int rows = c.getCount();
			int rows2 =myDbHelper.getNumberOfRows();
			
			//int bpog =00441142717404	;
			String details = myDbHelper.getTestName(1);
			allData.setText("no of columsn: " +cols+ "no of rwos: "+rows + "Sepcfic Row data in DB: "+ details+ "And all data:\n" + allTests);
			Log.d(TAG, "Databse no of columns: "+ myDbHelper.getCursorData().getColumnCount());
			
			myDbHelper.close();
			
		}catch(Exception sql){
			sql.printStackTrace();
		}
		
		
	}//end onCreate

	private void intilizeWidgets() {
		// TODO Auto-generated method stub
		allData = (EditText) this.findViewById(R.id.etData);
		
		
		
	}

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
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
		
		

	

}//end class
