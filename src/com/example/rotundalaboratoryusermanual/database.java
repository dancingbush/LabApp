package com.example.rotundalaboratoryusermanual;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class database {
	
	//defne constants representing fileds of database
	
	private static final String TAG="DATABASE CLASS";
	public static final String KEY_ROWID="_id";
	public static final String KEY_TESTCODE ="test_code";
	public static final String KEY_TESTNAME = "name";
	public static final String KEY__SAMPLETYEP="sample";
	public static final String KEY__COMMENTS = "comments";
	
	public static final String KEY_TAT="tat";
	public static final String KEY_TATURGENT="taturgent";
	public static final String KEY_REFRANGE="refreange";
	public static final String KEY__IN_OUT_HOUSE="in_out_house";
	public static final String KEY_DEPARTMENT="department";
	public static final String KEY_FIXATION="fixation";
	public static final String KEY_REFERRALCENTER="referralcenter";
	public static final String KEY_CONTACTNUMBER="contact_number";
	
	private static final String DATABASE_NAME="testsdb";
	private static final String DATABASE_TABLE =  "tests";
	
	public static final int DATABASE_VERSION = 1;//ver 2 for depth column
	private DbHelper  ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDataBase;
	private String[] columns;//holds the columns data in a row
	
	private static String DB_PATH= "data/data/com.example.rotundalaboratoryusermanual/databases/";
	private static String DB_NAME= "testsdb";
	
	//fro searching 
	private DbHelper dbHelperObject;
	
	
	//inner class off the main thead UI
	private class DbHelper extends SQLiteOpenHelper{
		
		public  DbHelper(Context context) {
			super(context, DATABASE_TABLE, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
			//dbHelperObject = new DbHelper(context);
			
		}//end DbHelper constructor
	
		
	 
	
	@Override 
	public void onCreate(SQLiteDatabase db) {
		// only called once when DB first created, if no databse exists in device fro SQLite file in emulator/device 
		//data path (/data/data/pakcagename of app/databases)
		
		//first check id db exists
		SQLiteDatabase checkDB = null;
		//String myPath = "data/data/" + getApplicationContext().getPackageName() + "/databases/testsdb";
		
		db.execSQL("CREATE TABLE "+ DATABASE_TABLE + " ("+
		KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
		KEY_TESTCODE + " TEXT , "+ 
		KEY_TESTNAME + " TEXT , " +
		KEY__SAMPLETYEP + " TEXT , " +
		KEY__COMMENTS + " TEXT , " +
		KEY_TAT + " TEXT , " +
		KEY_TATURGENT + " TEXT , " +
		KEY_REFRANGE + " TEXT , " +
		KEY__IN_OUT_HOUSE + " TEXT , " +
		KEY_DEPARTMENT + " TEXT , " +
		KEY_FIXATION + " TEXT , " +
		KEY_REFERRALCENTER + " TEXT , " +
		KEY_CONTACTNUMBER + " TEXT);"
		);
		
		//use for dtabase querys, n=13
		columns = new String[]{KEY_ROWID,  KEY_TESTCODE, KEY_TESTNAME, KEY__SAMPLETYEP, 
				KEY__COMMENTS, KEY_TAT,
				KEY_TATURGENT, KEY_REFRANGE, KEY__IN_OUT_HOUSE, KEY_DEPARTMENT, 
				KEY_FIXATION,KEY_REFERRALCENTER,KEY_CONTACTNUMBER, 
				};

	}//end onCreate DB inner helper class

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// if table exits, drop it (delete) and recreate it to upgrade it
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		onCreate(db);
		//need to call upgrade and add a int column for depth, newVersion is 2
		if (newVersion> oldVersion){
			//db.execSQL("ALTER TABLE "+DATABASE_TABLE+" ADD COLUMN "+KEY_DEPTH+" INTEGER DEFAULT 0");
		}

		
		
	}//end oDbHelpernUgrade inner helper class

}//end inner class DbJHelper
	
	
	
	//--OUTER MAIN CLASS--
	
	public database(Context c){
		ourContext=c;
	}//end outer class constructor
	
	/*
	public diveDataBase(ItemAdapter itemAdapter) {
		// for creating DB in ItemAdpter to get no of rows
		ourContext= itemAdapter;
	}//
*/
	
	
	/**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    	}else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
    		ourHelper = new DbHelper(ourContext);//craete a new helper inner class object
    		ourDataBase = ourHelper.getReadableDatabase();
    		//ourDataBase.getReadableDatabase();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }//end createDatabase
    
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	//return checkDB != null ? true : false;
    	return false; //set this if we want to copy a new data base from assets into the device or emuator
    }//end checkDataBase
    
    
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = ourContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
	public database open() throws SQLException{
		/*
		ourHelper = new DbHelper(ourContext);//craete a new helper inner class object
		ourDataBase = ourHelper.getWritableDatabase();//creare our SQLLite db from or helper object, this methdos calls on create which creates a empty DB
		*/
		
		//Open the database
        String myPath = DB_PATH + DB_NAME;
    	ourDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		
	return this;
	
	}//end open
	
	public void close(){
		//close dbHelper inner class
		ourHelper.close();
	}//end close

	
	/*req  only to insert to DB fro app, not req as this is pr made
	//data sent from LogDive class to be saved as a individual row of data
	public long createEntry(float diveRate, int bottomDiveTime, String diveBuddyString,
			String diveCenterString, String diveLocationString, int diveNum,
			String diveSiteString, int endBars, int startBars, int viz,
			int waterTemperature, String diveDateString, String comment,
			String conditionChoice, String savedImagePath, int depth) {
		// using a content values key:value paried structire, from logdive class to send data to this class fro saving in DB
		//table of 15 values
		ContentValues cv = new ContentValues();
		cv.put(KEY_DIVERATING, diveRate);
		cv.put(KEY_BOTTOMTIME, bottomDiveTime);
		cv.put(KEY_DIVEBUDDY, diveBuddyString);
		cv.put(KEY__DIVECENTER, diveCenterString);
		cv.put(KEY_DIVELOCATION, diveLocationString);
		cv.put(KEY__DIVENUMBER, diveNum);
		cv.put(KEY_DIVESITE, diveSiteString);
		cv.put(KEY_ENDBAR, endBars);
		cv.put(KEY_STARTBAR, startBars);
		cv.put(KEY_VIZIBILTY, viz);
		cv.put(KEY_WATERTEMP, waterTemperature);
		cv.put(KEY__DIVEDATE, diveDateString);
		cv.put(KEY_COMMENTS, comment);
		cv.put(KEY_CONDITIONS, conditionChoice);
		cv.put(KEY_DIVEPICTURE, savedImagePath);
		
		cv.put(KEY_DEPTH, depth);
		
		//write to DB with helper object
		return ourDataBase.insert(DATABASE_TABLE, null, cv);
		//returns -1 if fails
		
	}//end craeteEntry
	*/

	
	public Cursor getCursorData(){
		//use cursor object to read data from entire DB, can be returned to cutom list Cursor adapter
		
				Cursor c = this.ourDataBase.query(DATABASE_TABLE, columns, null, null, null, null, null);
				if(c!=null){
				return c;
				}
				return null;
	}//end hetGursor
	
	
	
	public String getAllDataAsString() {
		// retruns all data in the database
		
		
		//use cursor object to read data
		Cursor c = this.ourDataBase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result="";
		
		columns = new String[]{KEY_ROWID,  KEY_TESTCODE, KEY_TESTNAME, KEY__SAMPLETYEP, 
				KEY__COMMENTS, KEY_TAT,
				KEY_TATURGENT, KEY_REFRANGE, KEY__IN_OUT_HOUSE, KEY_DEPARTMENT, 
				KEY_FIXATION,KEY_REFERRALCENTER,KEY_CONTACTNUMBER, 
				};
		//read data from cursor object
		int iRow = c.getColumnIndex(KEY_ROWID);
		int itestcode = c.getColumnIndex(KEY_TESTCODE);
		int iTestName = c.getColumnIndex(KEY_TESTNAME);
		int iSampleType = c.getColumnIndex(KEY__SAMPLETYEP);
		int iComments = c.getColumnIndex(KEY__COMMENTS);
		int iTAT = c.getColumnIndex(KEY_TAT);
		int iTATUrgent = c.getColumnIndex(KEY_TATURGENT);
		int iRefRange = c.getColumnIndex(KEY_REFRANGE);
		int iIn_Out_House = c.getColumnIndex(KEY__IN_OUT_HOUSE);
		int iDept = c.getColumnIndex(KEY_DEPARTMENT);
		int iFixation = c.getColumnIndex(KEY_FIXATION);
		int iRef_Center = c.getColumnIndex(KEY_REFERRALCENTER);
		int iContactNumber = c.getColumnIndex(KEY_CONTACTNUMBER);
		
		
		
		//loop through cursor object data from DB, move to next as longs as not at last row (isAfterLast)
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + " " + c.getString(itestcode) + "\n" 
					+ c.getString(iTestName) + " " + c.getString(iSampleType) + "\n" 
					+ c.getString(iComments) + " " + c.getString(iTAT) + "\n"
					+ c.getString(iTATUrgent) + " " + c.getString(iRefRange) + "\n"
					+ c.getString(iIn_Out_House) + " " + c.getString(iDept) + "\n"
					+ c.getString(iFixation) + " " + c.getString(iRef_Center) + "\n"
					+ c.getString(iContactNumber) + "\n\n\n";
					
					
		}//end for
		
		return result;
	}//end gerData

	
	//get methods for a specific row number entered (long l)
	
	public int getNumberOfRows(){
		
		Cursor c = this.ourDataBase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		if(c!=null){
		
		int noOfRows = c.getCount()+1;
		return noOfRows;
		}
		return -1;//if eror occurs default return statment
	}//end 
	
	/*
	public int getLastDiveNumber(){
		
		try{
		Cursor c = this.ourDataBase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		if(c!=null){
		c.moveToLast();
		String diveNumberDB= c.getString(c.getColumnIndexOrThrow(diveDataBase.KEY__DIVENUMBER));
		String diveSiteCheck = c.getString(c.getColumnIndexOrThrow(diveDataBase.KEY_DIVESITE));
		int lastDive = (Integer.parseInt(diveNumberDB));
		//this.diveNumber.setText(String.valueOf(nextDive));
		return lastDive;
		}
		}catch(CursorIndexOutOfBoundsException e){
			e.printStackTrace();
			return 0;
		}
		return 0;
	}//end last dive no
	*/
	
	
	public String getTestCode(long l) throws SQLException{
		//l is row ID
		Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String number= c.getString(c.getColumnIndex(this.KEY_TESTCODE));//data from column containing the loaction
			return number;
			
		}//end cursor
		return null;
		
	}//end getDiveNumber
	
	
	public String getComments(long l) throws SQLException{
		//l is our row ID
		Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String location= c.getString(c.getColumnIndex(KEY__COMMENTS));//data fromcolumn containing the loaction
			return location;
			
		}//end cursor
		return null;
	}

	public String getIn_vs_OutSourced(long l) throws SQLException{
		//l is our row ID
				Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
				if(c!=null){
					c.moveToFirst();
					String site= c.getString(c.getColumnIndex(KEY__IN_OUT_HOUSE));//data fromcolumn containing the loaction
					return site;
					
				}//end cursor
		return null;
	}

	public String getSampleType(long l) throws SQLException{
		//l is our row ID
		Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String dtae= c.getString(c.getColumnIndex(KEY__SAMPLETYEP));//data fromcolumn containing the loaction
			return dtae;
			
		}//end cursor
		return null;
	}

	public String getContactNumber(long l) throws SQLException{
		//l is our row ID
		Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String rate= c.getString(c.getColumnIndex(KEY_CONTACTNUMBER));//data fromcolumn containing the loaction
			return rate;
			
		}//end cursor
		return "Not found";
	}

	public String getDepartment(long l) throws SQLException{
		//l is our row ID
		Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String bt= c.getString(c.getColumnIndex(KEY_DEPARTMENT));//data fromcolumn containing the loaction
			return bt;
			
		}//end cursor
		return null;
	}

	public String getFixation(long l) throws SQLException{
		//l is our row ID
		Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String buddy= c.getString(c.getColumnIndex(KEY_FIXATION));//data fromcolumn containing the loaction
			return buddy;
			
		}//end cursor
		return null;
	}

	public String getReferralCenter(long l) throws SQLException {
		//l is our row ID
		Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String center= c.getString(c.getColumnIndex(KEY_REFERRALCENTER));//data fromcolumn containing the loaction
			return center;
			
		}//end cursor
		return null;
	}

	public String getReferanceRange(long l) throws SQLException{
		//l is our row ID
		Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String EB= c.getString(c.getColumnIndex(KEY_REFRANGE));//data fromcolumn containing the loaction
			return EB;
			
		}//end cursor
		return null;
	}

	public String getTAT(long l) throws SQLException{
		//l is our row ID
		Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String sb= c.getString(c.getColumnIndex(KEY_TAT));//data fromcolumn containing the loaction
			return sb;
			
		}//end cursor
		return null;
	}

	public String getTATUrgent(long l) throws SQLException{
		//l is our row ID
		Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String viz= c.getString(c.getColumnIndex(KEY_TATURGENT));//data fromcolumn containing the loaction
			return viz;
			
		}//end cursor
		return null;
	}

	public String getTestName(long l) throws SQLException{
		//l is our row ID, cursor will always rrturn at one pos behing the query so need to moveTpFirst
		Cursor c = ourDataBase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c!=null){
			//cursor may not be null but can still be empty
			if(c.moveToFirst()){
				//if cursor not empty and we can move to first row
			
			String temperature= c.getString(c.getColumnIndex(KEY_TESTNAME));//data fromcolumn containing the loaction
			return temperature;
			
		}else{
			//cursor is empty
			Log.d(TAG, "Cursor IS EMPTY!");
		}//end esle
			
		}//end cursor
		c.close();
		return "Cursor empty";
		
	}//end getTestName

	

	
	
	
	/*
	//upadte a modified entry, data is sent from DB as Strings, then returned to here as Strings, so need to get te int from strimgs where needed
	public void updateEntry(long lRow, String diveRate, String bottomDiveTime,
			String diveBuddyString, String diveCenetrString,
			String diveLoctionString, String diveNum, String diveSiteString,
			String endBars, String startBars, String viz,
			String waterTemperature, String diveDateString, String comment,
			String conditionChoice, String savedImagePath, String depth) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_DIVERATING, diveRate);
		cvUpdate.put(KEY_BOTTOMTIME, bottomDiveTime);
		cvUpdate.put(KEY_DIVEBUDDY, diveBuddyString);
		cvUpdate.put(KEY__DIVECENTER, diveCenetrString);
		cvUpdate.put(KEY_DIVELOCATION, diveLoctionString);
		cvUpdate.put(KEY__DIVENUMBER, diveNum);
		cvUpdate.put(KEY_DIVESITE, diveSiteString);
		cvUpdate.put(KEY_ENDBAR, endBars);
		cvUpdate.put(KEY_STARTBAR, startBars);
		cvUpdate.put(KEY_VIZIBILTY, viz);
		cvUpdate.put(KEY_WATERTEMP, waterTemperature);
		cvUpdate.put(KEY__DIVEDATE, diveDateString);
		cvUpdate.put(KEY_COMMENTS, comment);
		cvUpdate.put(KEY_CONDITIONS, conditionChoice);
		cvUpdate.put(KEY_DIVEPICTURE, savedImagePath);
		cvUpdate.put(KEY_DEPTH, depth);
		//n=16
		ourDataBase.update(DATABASE_TABLE, cvUpdate, this.KEY_ROWID + "=" + lRow, null);
		
		
	}//end update modified entry
	*/

	public void deleteEntry(long lDeleteRow) throws SQLException{
		// deleted selected entry according to the row number chosen
		ourDataBase.delete(DATABASE_TABLE, KEY_ROWID + "=" +lDeleteRow , null);
		
		
		
	}//end delete entry
	
	//--END GET METHODS FOR INDVIDUAL COLUMN VALUES FIR A GIVEN ROW NUMBER (LONG l)--
	
	/*
	 * methods to search database with String query form searchable widget intent 
	*  in ViewListOfDives ListActivity search bar. Each search returns a cursor which is set to adpter
	*  in ViewListOfYesys List Activity
	*/
	
	
	//try alternate search
	public Cursor searchByInputText(String inputText) throws SQLException{
		
		/*
		dbHelperObject = new DbHelper(ourContext);
		dbHelperObject.getReadableDatabase();
		*/
		
		 String query = "SELECT  _id," +
	                KEY_TESTNAME +  " from " + DATABASE_TABLE +
	                " where " + KEY_TESTNAME + " LIKE '" + inputText + "';";
		
		 
		 Cursor mCursor =  ourDataBase.rawQuery(query, null);
		 
		 if(mCursor != null){
			 mCursor.moveToFirst();
		 }
		 return mCursor;
	
	
	}//end earchByInputTExt
	
	public Cursor getWordMatches(String query, String[] columns){
		
		//String selection = database.KEY_TESTNAME + " MATCH ?";
		//String[] selectionArgs = new String[] {query+"*"}; //wildcard *
		dbHelperObject = new DbHelper(ourContext);
		dbHelperObject.getReadableDatabase();
		
		
		//SO suggestion
		String selection = database.KEY_TESTNAME + " LIKE ?";         
		String[] selectionArgs = new String[] {"%"+query+"%"};
		
		
		return query(selection, selectionArgs, columns);
		
		
	}//end getWordMatches

	
	
	private Cursor query(String selection, String[] selectionArgs,
			String[] columns) {
		
		// return cursor form params passed getWordMatches(Styring query) from search widget ListActivity
		
		this.open();
		
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		builder.setTables(DATABASE_TABLE);
		
		dbHelperObject = new DbHelper(ourContext);
	
		//Cursor cursor = builder.query(dbHelperObject.getReadableDatabase(), columns, selection, selectionArgs, null, null, null);
		Cursor cursor = builder.query(ourDataBase, columns, selection, selectionArgs, null, null, null);
		
	
		
		//dbHelperObject.getReadableDatabase()
		if(cursor == null){
			return null;
		}else{ cursor.moveToFirst();
		}
		
		/*
		 * else if(!cursor.moveToFirst()){
			cursor.close();
			return null;
		}
		 */
		return cursor;
		
		
		
		
	}//end query
	
	
}//end class diveDB
