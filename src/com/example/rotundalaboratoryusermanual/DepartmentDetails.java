package com.example.rotundalaboratoryusermanual;



import java.util.ArrayList;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class DepartmentDetails extends ListActivity {

	
	//selected from grid menu, a list view holding deot name and number 
	
private static final String TAG = DepartmentDetails.class.getName();

@Override
protected void onListItemClick(ListView l, View v, int position, long id) {
	// not impmented as only a list of depts with nos
	
	
	super.onListItemClick(l, v, position, id);
	
	
}





@Override
protected void onCreate(Bundle savedInstanceState) {
	
	/*
	 * populate a list view with customised arrayadpter using a 
	 * a ArrayList<Pair<String,String>> and overidden getView()
	 */
	
	
	super.onCreate(savedInstanceState);
	
	//craete a paired array adpter holding two strings, dept name + Number, and tests availble
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
	ArrayList<Pair<String, String>> depatrmentAndTestsArray = new ArrayList<Pair<String, String>>();
	
	depatrmentAndTestsArray.add(Pair.create("Laboratory Manager", ""));
	depatrmentAndTestsArray.add(Pair.create("Clinical Consultation", "Contact Recption or relevant dept"));
	depatrmentAndTestsArray.add(Pair.create("Laboratory Office ", "Reort requets, General Inquires"));
	depatrmentAndTestsArray.add(Pair.create("Biocehmistry/Endocinology 1345", "Renal/Liver function, Endocine etc"));
	depatrmentAndTestsArray.add(Pair.create("Haematology 1464","FBC, Films, Klehaiuer, Coag, Lupus\nThrombophilia"));
	depatrmentAndTestsArray.add(Pair.create("Haemoviglance Offiecr- bleep ", "Haemoviglance inquires/advise"));
	depatrmentAndTestsArray.add(Pair.create("Histology ", "Plancetal/Biospsy pathology etc"));
	depatrmentAndTestsArray.add(Pair.create("Laboratorty IT", "Apex, LIS etc"));
	depatrmentAndTestsArray.add(Pair.create("Point Of Care", "Co-ordinator 1345"));
	depatrmentAndTestsArray.add(Pair.create("Microbiology - ", "Blood cultuers, CSf's, Sensitivties etc"));
	depatrmentAndTestsArray.add(Pair.create("Quality Manager", "Quality Issues, Compliants "));
	depatrmentAndTestsArray.add(Pair.create("Serology ", "TORCH, HIV, Hepatiti A/B/c etc"));
	depatrmentAndTestsArray.add(Pair.create("Transfusion 1241", "Anti-D queries, Gropuing and Screening"));
	depatrmentAndTestsArray.add(Pair.create("Transfusion 1463", "Crosstmatch, blood products"));
	depatrmentAndTestsArray.add(Pair.create("Training & Devolpment", "CPD information, Student placement inquires"));
	
	
	
	/*
	 * Create arrayadpter to place this paried string Arraylist. Set the listadpter (this) with it.
	 * Override getView() to populate the List View.xml textviews with items from the arralList
	 */
	
	
	ArrayAdapter<Pair<String, String>> listAdpter = new ArrayAdapter<Pair<String,String>>(DepartmentDetails.this,
			R.layout.list_row2, R.id.tv_code, depatrmentAndTestsArray){
		
		public View getView(int position, View convertView, ViewGroup container){
			
			if(convertView == null){
				
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row2, container, false);
				
			}
			
			TextView deptName = (TextView) convertView.findViewById(R.id.tv_code);
					Pair<String, String> item = getItem(position);
					deptName.setText(item.first);
					TextView otherDetails = (TextView) convertView.findViewById(R.id.tv_course_name);
					otherDetails.setText(item.second);
					
					return convertView;
			
		}//edn getview
		
	};//end arrayapter
	
	this.setListAdapter(listAdpter);
	
}//end onCreate
	



}//end class
