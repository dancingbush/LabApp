package com.example.rotundalaboratoryusermanual;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedTestDetails extends Activity implements OnClickListener {

	/*
	 * Display teh selected test from the list view. Pass teh row number via
	 * Bundled data from Listview class ListOfTests
	 */
	private int rowNumberForTest;
	private final String TAG = "SELECTED_TEST";
	private EditText refRangesET;
	private TextView textNameTV, departmentNameTV, inHouse_vs_RefferedTV,
			sepcimanRequirmentsTV, TAT_TV, TAT_UrgentTV, testCodeTV,
			fixationTV, referralLabTV, refRangesTV, phineNumberTV, commentsTV;
	private ImageView displineImageIV, testImageIV;
	private ImageButton likeIV;
	private String textName, departmentName, inHouse_vs_Reffered,
			sepcimanRequirments, TAT, TAT_Urgent, testCode, fixation,
			referralLab, refRanges, phineNumber;
	private String OLCHwebsiteURL, kingsColleggeWebSiteURL, TDLWebsiteURL, SJHwebsiteURL, stVincentswebsiteURL,
			TSHwebsiteURL, drugTreamentCenterwebsiteURL, IBTSwebsiteURL, materWebsiteURL, addenbrokesWebsiteURL,
			sheffieldNHSwebsiteURL, manchesterUHwebsiteURL, beumountWebsiteURL, cherryOrcahrdWebsiteURL,
			NVRLWebsiteURL, AMNCHwebsiteURL, TDLGenticsWebsiteURL, OLCHGenticsURL,
			NCHCDWebsiteURL, IBGRLBristolWebsiteURL, biominisClaymonWebsiteURL, medLabPathologyWebsiteURL,
			newcastleRIWebsiteURL, royalBromptonNHSWebsiteURL, willLinkNHSWebsiteURL, nationalHospitalLondonWebsiteURL,
			westernScotlandGenticsWebsiteURL, stateLabWebsiteURL, instittueOfNerologyLondoneURL, belfastCityHospitalURL;
	//private AlertDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// constructor for activity

		super.onCreate(savedInstanceState);

		// set the xml will be using
		setContentView(R.layout.testdeailswithtab);

		// intilise all xml widgets
		intialiseWidgets();

		// get bundle data with row number, open database and get cursor object
		// for that row
		getRowNumberSelected();

	}// end onCreate

	
	
	
	private void getRowNumberSelected() {
		/*
		 * get data from bundle intent in the form of the row of the test
		 * selected from the list view menu (ViewListOftests class).First get
		 * the bundle data containg the row number and open databaseand get
		 * cursor according to the row number Intent is sent from ListView as a
		 * intent.puString with a kew string value of 'row' = the row number
		 */

		Bundle rowNumber = this.getIntent().getExtras();
		if (rowNumber != null) {
			rowNumberForTest = Integer.parseInt(rowNumber.getString("row"));
		}
		Log.d(TAG, "Row number from Lsit menu Intent is : " + rowNumberForTest);

		// get cursor object rep of the row & get data from database, pass this
		// class as context parameter
		database db = new database(this);
		db.open();
		long row = Long.parseLong(String.valueOf(rowNumberForTest));

		/*
		 * row num must be a type long as defined in databse methods
		 */

		// set all widgest and voews according to data obtained from database
		// row number
		// textviews
		
		
		// now set auto scrolling methods if more than > 2 lines of text we cant
				// see
				TAT_TV.setMovementMethod(ScrollingMovementMethod.getInstance());
				TAT_UrgentTV.setMovementMethod(ScrollingMovementMethod.getInstance());
				//referralLabTV.setMovementMethod(ScrollingMovementMethod.getInstance());
				//refRangesTV.setMovementMethod(ScrollingMovementMethod.getInstance());

				
			
		textNameTV.setText(db.getTestName(row));
		String testNameRowNumber = db.getTestName(row);
		Log.d(TAG, "TEST NAME AND ROW NUMBER FORM SELECTED TEST: "+ testNameRowNumber + " / "+ row);
		departmentNameTV.setText(db.getDepartment(row));

		
		
		String inHouse_vs_Referred = db.getIn_vs_OutSourced(row);

		if (inHouse_vs_Referred.toLowerCase().contains("ex")) {
			inHouse_vs_RefferedTV.setText("Reffered Test");
		} else if (!inHouse_vs_Referred.toLowerCase().contains("ex")) {
			inHouse_vs_RefferedTV.setText("In-house test");
		}

		/*
		 * If the input from databse for ref ranges etc is log and we dont see all text
		 * we will increase the height of the edittext to make it clearer
		 */
		
		/*
		String lenght = db.getReferanceRange(row);
		Log.d(TAG, "LENGHT OF STRING= "+ lenght.length());
		int paddingPixel=30;
		//float density
		//if(lenght.length() > 10){
		if(!lenght.toLowerCase().contains("n/a") && !lenght.toLowerCase().contains("contact")){
			android.view.ViewGroup.LayoutParams params = refRangesET.getLayoutParams();
		params.height =  getResources().getDimensionPixelSize(R.dimen.new_layout_height);
		
		refRangesET.setLayoutParams(params);
		refRangesET.setPadding(30, 30, 30, 30);
		refRangesTV.setLayoutParams(params);
		refRangesTV.setPadding(30, 30, 30, 30);;
		}
		
		*/
		
		//this.s
		sepcimanRequirmentsTV.setText("Sample type: \n"+db.getSampleType(row));
		TAT_TV.setText(db.getTAT(row));
		TAT_UrgentTV.setText(db.getTATUrgent(row));
		testCodeTV.setText(db.getTestCode(row));
		fixationTV.setText(db.getFixation(row));
		referralLabTV.setText(db.getReferralCenter(row));
		refRangesTV.setText(db.getReferanceRange(row));
		//refRangesET.setText(db.getReferanceRange(row));
		phineNumberTV.setText(db.getContactNumber(row));
		commentsTV.setText("Comments: \n"+db.getComments(row));
		
		//test adding a hyperlink to a webpage holding a OLCH for to be completed in test
		addLinkToReferralForm(textNameTV);
		
				
				
		//check if need to activte a dialog box sowing results hat will not fit nto table
		checkIfDisplayDialog(referralLabTV, db.getReferralCenter(row));
		checkIfDisplayDialog(refRangesTV, db.getReferanceRange(row));
		

		/*
		 * to set the imageviews depending on diplicpnie we select randomly from
		 * a array of resource bitmaps depending in the displine. Same for
		 * imageview bitmap of test tube image depending on what sepciman
		 * requirments are. 
		 */

		
		//clear teh current default image of image view by setting arg to 0
		//testImageIV.setBackgroundResource(0);
		//testImageIV.setBackground(null);
		//displineImageIV.setBackground(null);
		//testImageIV.setImageResource(0);
		//displineImageIV.setImageResource(0);
		testImageIV.setBackgroundDrawable(null);
		displineImageIV.setBackgroundDrawable(null);
		
		//set up animation fro imageview rendering of resources
		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
		fadeIn.setDuration(3000);

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
		fadeOut.setStartOffset(1000);
		fadeOut.setDuration(1000);

		AnimationSet animation = new AnimationSet(false); // change to false
		animation.addAnimation(fadeIn);

		
		int setBitmapResourceForDiscipline = getBitMapResopurceForTheDispline();
		int setBitmapResourceForTestTube = getBitmapResourceForTestTubetype();

		testImageIV.setAnimation(fadeIn);
		testImageIV.setBackgroundResource(setBitmapResourceForTestTube);
		//displineImageIV.setAnimation(fadeIn);
		displineImageIV
				.setBackgroundResource(setBitmapResourceForDiscipline);

		/*
		 * normally donet need to close a databse as VM will do this for u but
		 * // ALWAYS close Cursors fade image in and out animation code
		 */

		
		//nullpointer here but should never need to clos ethe database as vm will do this
		//db.close();

	}// end getNumberSelected for row

	private void addLinkToReferralForm(TextView textNameTV2) {
		// add link to commenst section if required foe form, then add listener onCLick to naviagte too
		
		String test= textNameTV.getText().toString();
		
		
		String comments = commentsTV.getText().toString();
				if(test.toLowerCase().contains("pcr")){
					
					commentsTV.append(Html.fromHtml("<br><i><font color='#5a9aa8'>Download the Referral OLCH form here and view TAT</font></i></br>"));
					commentsTV.setOnClickListener(this);
					
				}
		
	}//end addLinkToRefferal




	private void checkIfDisplayDialog(TextView theView, String text) {
		/*
		 * if text from DB in ref ranges or refferal center
		 * to wode to be seen user can touch and details will display in a dialog
		 */
		if(theView == this.referralLabTV){
			
		
		//check referral center first
		if(!text.toLowerCase().contains("n/a") && !text.toLowerCase().contains("contact")){
			//must contain some details so ad onclick listener
			theView.setOnClickListener(this);
		}
		}
		if(theView == this.refRangesTV){
			
			
			//check referral center first
			if(!text.toLowerCase().contains("n/a") && !text.toLowerCase().contains("contact")){
				//must contain some details so ad onclick listener
				theView.setOnClickListener(this);
			}
			}
	}//end checkifdiaplay




	private int getBitmapResourceForTestTubetype() {
		/*
		 * return a int represnting test tube image resource from a choice form
		 * a array of resource bitmaps depending on what the string of sepciman
		 * requirments are
		 */

		sepcimanRequirments = sepcimanRequirmentsTV.getText().toString().toLowerCase();
		textName = textNameTV.getText().toString().toLowerCase();
		departmentName = departmentNameTV.getText().toString().toLowerCase();
		
		

		/*
		 *  if no of the if:else are meet cover a generic resource image , haem
		 *  Use nested if:else sattements with || and && conditions
		 */
		
		

		if (textName.toLowerCase().contains("willbrands")
				&& textName.contains("newborn")) {
			return R.drawable.paeds_2citrate;

		}
		else if (sepcimanRequirments.toLowerCase().contains("EDTA".toLowerCase())
				&& sepcimanRequirments.contains("9ml")) {
			return R.drawable.edta9ml;

		}
		
		
		else if (sepcimanRequirments.toLowerCase().contains("EDTA".toLowerCase())
				&& sepcimanRequirments.contains("newbo") && !sepcimanRequirments.contains("adult")) {
			return R.drawable.paed_edta;
		}
		else if (sepcimanRequirments.toLowerCase().contains("cit".toLowerCase())
				&& sepcimanRequirments.contains("newbo") && !sepcimanRequirments.contains("adult")) {
			return R.drawable.paed_citrate;
		}
		
		else if (sepcimanRequirments.toLowerCase().contains("EDTA".toLowerCase())
				&& sepcimanRequirments.contains("newbo")) {
			return R.drawable.edta_adult_newborn;

		
	

		} else if (sepcimanRequirments.toLowerCase().contains("EDTA".toLowerCase())
				&& sepcimanRequirments.contains("10ml")) {
			return R.drawable.edta9ml;

			// serums


			// edta
		}else if (sepcimanRequirments.toLowerCase().contains("EDTA".toLowerCase()) &&
				!sepcimanRequirments.toLowerCase().contains("newb".toLowerCase())) {
				return R.drawable.edta_adult;
		
		
		}else if (sepcimanRequirments.toLowerCase().contains("ser")
				|| sepcimanRequirments.toLowerCase().contains("clot")) {
			return R.drawable.serum;

			// lithoum heaprins

		}else if (sepcimanRequirments.toLowerCase().contains("li") &&
				!sepcimanRequirments.toLowerCase().contains("adult")
					&& sepcimanRequirments.toLowerCase().contains("newb")
					|| sepcimanRequirments.toLowerCase().contains("paed")) {
				return R.drawable.paed_lithhep;
				
		}else if (sepcimanRequirments.toLowerCase().contains("li")
				&& sepcimanRequirments.toLowerCase().contains("newb")
				|| sepcimanRequirments.toLowerCase().contains("paed")) {
			return R.drawable.lithium_hep_adult_paed;

		

			
		} else if (sepcimanRequirments.toLowerCase().contains("li")
				|| sepcimanRequirments.toLowerCase().contains("hep")) {
			return R.drawable.lihep;

		} 
		// coags lupus and thrombohilia, factor assays and Ahemopglobinopathy
		
		else if (sepcimanRequirments.toLowerCase().contains("cit")
				&& !sepcimanRequirments.toLowerCase().contains("adult")
				&& sepcimanRequirments.toLowerCase().contains("new")
				|| sepcimanRequirments.toLowerCase().contains("paed")) {
			return R.drawable.paed_citrate;
			
		}else if (sepcimanRequirments.toLowerCase().contains("cit")
					&& textName.toLowerCase().contains("adult")
					&& textName.toLowerCase().contains("von")) {
				return R.drawable.testtube_2_coags;

		}else if (sepcimanRequirments.toLowerCase().contains("cit")) {
			return R.drawable.citrate;

		

		} else if (textName.contains("lup")) {
			return R.drawable.lupusscreen;

		} else if (textName.contains("throm")) {
			return R.drawable.thrombophilia_screen;

		} else if (textName.contains("fac")) {
			return R.drawable.testtube_2_coags;

		} else if (textName.contains("fac") && textName.contains("newb")
				|| textName.contains("paed")) {
			return R.drawable.paeds_2citrate;

		} else if (textName.contains("haemagl")) {
			return R.drawable.haemoglobinopathy_screen;

		}

		// others and sepcific test profiles and names

		else if (textName.contains("acyl")) {
			return R.drawable.guthrie_card;

		} else if (textName.contains("anti-xa")) {
			return R.drawable.citrate_adult_newborn;

		} else if (textName.contains("haemagl")) {
			return R.drawable.haemoglobinopathy_screen;

		} else if (textName.contains("alpha-feto")) {
			return R.drawable.amnoticfluid;

		} else if (textName.contains("amino")) {
			return R.drawable.universal_container;

		} else if (textName.contains("gas")) {
			return R.drawable.bloodgas2;

		} else if (textName.contains("gas")) {
			return R.drawable.bloodgas2;

		} else if (sepcimanRequirments.contains("swab")) {
			return R.drawable.swabs_all;

		} else if (sepcimanRequirments.contains("univer")) {
			return R.drawable.universal_container;

		} else if (sepcimanRequirments.contains("univer")) {
			return R.drawable.universal_container;

		} else if (sepcimanRequirments.contains("urine")) {
			return R.drawable.universal_container;

		} else if (sepcimanRequirments.contains("tip")) {
			return R.drawable.canulae_tip;

		} else if (sepcimanRequirments.contains("blood")
				&& sepcimanRequirments.contains("cult")) {
			return R.drawable.bloodcultuers;

		} else if (sepcimanRequirments.contains("stool")
				|| sepcimanRequirments.contains("fae")) {
			return R.drawable.stoll;

		} else if (textName.contains("crossmat")) {
			return R.drawable.edta_adult_newborn;

		} else if (textName.contains("csf")) {
			return R.drawable.csf;

		} else if (sepcimanRequirments.contains("cystic")) {
			return R.drawable.edta_adult_newborn;

		} else if (textName.contains("cytolog")) {
			return R.drawable.cytologyspeciman;

		} else if (textName.contains("cytolog") && textName.contains("kar")) {
			return R.drawable.lithium_hep_adult_paed;

		} else if (sepcimanRequirments.contains("skin")
				|| sepcimanRequirments.contains("biop")) {
			return R.drawable.skin_biposy;

		} else if (sepcimanRequirments.contains("cystic")) {
			return R.drawable.paed_edta;

		} else if (sepcimanRequirments.contains("scrap")) {
			return R.drawable.fungal_scrappings;

		} else if (sepcimanRequirments.contains("oxal")) {
			return R.drawable.flourideoxalte;

		}else if (textName.contains("biotinidase")) {
			return R.drawable.lithium_hep_adult_paed;

		}else if (departmentName.toLowerCase().contains("micro".toLowerCase())) {
			return R.drawable.swabs_all;

		}
		
		else if (departmentName.toLowerCase().contains("Hist".toLowerCase())&& (textName.toLowerCase().contains("Surg".toLowerCase()))) {
			return R.drawable.histo_formulin;

		}
		else if (departmentName.toLowerCase().contains("Hist".toLowerCase())) {
			return R.drawable.histology_casesstes;

		}
		

		// returin default imae of tubes if no conditions are meet
		return R.drawable.default_image_collection_of_tubes;

	}// end getBitMapResourseForTest

	private int getBitMapResopurceForTheDispline() {
		/*
		 * return a int representing recource bitmap from arrays of resource
		 * chosedn fromrandomly
		 */

		int[] microResourceSelection = { R.drawable.micro3,
				R.drawable.micro_baccili, R.drawable.micro_bacilli_on_surface,
				R.drawable.micro_culturing_plate,
				R.drawable.micro_prof_looking_plates,
				R.drawable.micro_rupture_rbc };

		int[] heamResourceSelection = { R.drawable.blood_clot,
				R.drawable.bloodvessel, R.drawable.haem_mainpic2,
				R.drawable.haem_mainpic4, R.drawable.haem_mainpic5 };

		int[] bioResourceSelection = { R.drawable.bio_dna_blue,
				R.drawable.bio_pro, R.drawable.bluetubes, R.drawable.needle,
				R.drawable.biochem_pipetting, R.drawable.conicalflasks };

		int[] histologyResourceSelection = { R.drawable.histology_prof_slides,
				R.drawable.biochem_pipetting, R.drawable.conicalflasks };

		departmentName = departmentNameTV.getText().toString().toLowerCase();

		// if micro discpline

		if (departmentName.contains("mic")) {
			Random whichBitmap = new Random();
			int myBitmap = microResourceSelection[whichBitmap
					.nextInt(microResourceSelection.length)];
			return myBitmap;

		}

		// if haematology discpline

		

		// if haematology discpline

		if (departmentName.contains("haem")) {
			Random whichBitmap = new Random();
			int myBitmap = heamResourceSelection[whichBitmap
					.nextInt(heamResourceSelection.length)];
			return myBitmap;

		}

		// if bio discpline

		if (departmentName.contains("bio")) {
			Random whichBitmap = new Random();
			int myBitmap = bioResourceSelection[whichBitmap
					.nextInt(bioResourceSelection.length)];
			return myBitmap;

		}

		// if histology discpline

		if (departmentName.contains("his")) {
			Random whichBitmap = new Random();
			int myBitmap = histologyResourceSelection[whichBitmap
					.nextInt(histologyResourceSelection.length)];
			return myBitmap;

		}

		
		
		
		// if referral discpline

		if (departmentName.contains("ref")) {
			Random whichBitmap = new Random();
			int myBitmap = whichBitmap.nextInt(bioResourceSelection.length);
			int retrunImage = bioResourceSelection[myBitmap];
			//int myBitmap = bioResourceSelection[whichBitmap
					//.nextInt(bioResourceSelection.length)];
			return retrunImage;

		}

		// if all else fails return a defulat resource

		return R.drawable.conicalflasks;
	}

	private void intialiseWidgets() {
		/*
		 * bind all xml widgest and views to java code Some textviews will have
		 * allot of String data in multople lines wrapped in a Tablerow so we
		 * must set auto scrolling listeners on these tectviews To use scroll in
		 * textvoews they also need the attibutes in xml ie android:maxLines =
		 * "AN_INTEGER" android:scrollbars = "vertical" where an integer is set
		 * to 2 lines in this case
		 */

		
		//get urls fro websites t naviget too when test referred
		this.kingsColleggeWebSiteURL = "http://kingspath.co.uk/tests/haematology/55/";
		this.SJHwebsiteURL="http://www.stjames.ie/Departments/DepartmentsA-Z/L/LaboratoryMedicineDirectorate/DepartmentOverview/";
		this.stVincentswebsiteURL = "http://www.stvincents.ie/Patients_&_Visitors/Information_for_GPs_/Laboratory_Information.htm";
		this.TSHwebsiteURL="http://www.childrensnational.org/DepartmentsandPrograms/default.aspx?Id=380&Type=Dept&Name=Laboratory%20Medicine";
		this.drugTreamentCenterwebsiteURL="http://www.addictionireland.ie/services/lab.asp";
		this.TDLWebsiteURL="https://www.tdlpathology.com/test-information";
		this.IBTSwebsiteURL="http://www.giveblood.ie/Clinical_Services/";
		this.materWebsiteURL="http://www.mater.ie/services/depts/p/pathology/";
		this.OLCHwebsiteURL="http://www.olchc.ie";
		this.addenbrokesWebsiteURL="http://www.cuh.org.uk/addenbrookes/services/clinical/genetics/genetics_labs/genetics_labs_index.html";
		sheffieldNHSwebsiteURL="http://www.sheffieldchildrens.nhs.uk/our-services/laboratory-medicine/";
		this.manchesterUHwebsiteURL="http://www.cmft.nhs.uk/info-for-health-professionals/laboratory-medicine";
		this.beumountWebsiteURL="http://www.beaumontlaboratory.com";
		this.cherryOrcahrdWebsiteURL="http://www.hse.ie/eng/services/list/5/publichealth/publichealthlabs/Public_Health_Laboratory_Dublin/";
		this.AMNCHwebsiteURL="http://www.amnch.ie/Departments-Clinics/Departments-A-Z/Laboratory/";
		this.TDLGenticsWebsiteURL="https://www.tdlpathology.com/services-divisions/tdl-genetics";
		this.OLCHGenticsURL="http://www.genetics.ie";
		this.NCHCDWebsiteURL="http://www.stjames.ie/Departments/DepartmentsA-Z/N/NationalCentreforHereditaryCoagulationDisorders/DepartmentOverview/";
		this.IBGRLBristolWebsiteURL="http://ibgrl.blood.co.uk/ReferenceServices/RefServframes.htm";
		this.biominisClaymonWebsiteURL="http://www.biomnis.ie/TestMenu.aspx";
		this.medLabPathologyWebsiteURL="http://www.sonichealthcare.ie/test-information/tests-a-z/tests-a.aspx";
		this.newcastleRIWebsiteURL="http://www.newcastle-hospitals.org.uk/services/13623.aspx";
		this.royalBromptonNHSWebsiteURL="http://www.rbht.nhs.uk/healthprofessionals/clinical-departments/laboratories/";
		this.willLinkNHSWebsiteURL="http://www.manchesterbrc.org/OurFacilities/WillinkBiochemicalGeneticsUnit.php";
		this.nationalHospitalLondonWebsiteURL="https://www.nhs.uk/Services/hospitals/Overview/DefaultView.aspx?id=1107";
		this.westernScotlandGenticsWebsiteURL="http://www.nhsggc.org.uk/content/default.asp?page=s1100";
		this.stateLabWebsiteURL="http://www.statelab.ie";
		this.instittueOfNerologyLondoneURL="http://docs.huihoo.com/android/2.3/resources/samples/index.html";
		this.belfastCityHospitalURL="http://www.eddnal.com/directory/detail.php?lab_id=172";
		
		
		
		// textviews

		
		textNameTV = (TextView) this
				.findViewById(R.id.tv_testSelected_TopNotes);
		departmentNameTV = (TextView) this
				.findViewById(R.id.tv_testSelected_Displine);
		inHouse_vs_RefferedTV = (TextView) this
				.findViewById(R.id.tv_setelectedTest_TestName);
		sepcimanRequirmentsTV = (TextView) this
				.findViewById(R.id.tv_selectedTest_MainText);
		TAT_TV = (TextView) this.findViewById(R.id.tvTable_TATText);
		TAT_UrgentTV = (TextView) this.findViewById(R.id.tvTable_TATUrgetInput);
		testCodeTV = (TextView) this.findViewById(R.id.tvTable_TestCode_Input);
		fixationTV = (TextView) this.findViewById(R.id.tvTFixation_Input);
		referralLabTV = (TextView) this
				.findViewById(R.id.tvTable_Referral_Center_Input);
		refRangesTV = (TextView) this.findViewById(R.id.tvrefRanges_Input);
		phineNumberTV = (TextView) this.findViewById(R.id.tv_title);
		commentsTV = (TextView) this.findViewById(R.id.tv_selectedTest_Comments);

		// add on click listenr to phone number text voew so we can make a phone
		// call
		phineNumberTV.setOnClickListener(this);// we implemeny onClickListener

		
		//if it is a refferal test call a dialog when clicked with option to voew the webite of center
		String refferalOrNot = inHouse_vs_RefferedTV.getText().toString();
		if(refferalOrNot.toLowerCase().contains("ex")){
			this.referralLabTV.setOnClickListener(this);
		}
		
		
		// imageviews and image button

		displineImageIV = (ImageView) this
				.findViewById(R.id.iv_selectedTest_TestImage);
		testImageIV = (ImageView) this
				.findViewById(R.id.image2_selectedTest_Tubes);

		// the liek button to open the app in google play and rate/like

		likeIV = (ImageButton) this.findViewById(R.id.btnImg_Like);
		likeIV.setOnClickListener(this);

	}// end intilaise widgets

	@Override
	public void onClick(View buttonClicked) {
		// handle the like button and phone call intent

		switch (buttonClicked.getId()) {
		
		
			
		case R.id.tv_selectedTest_Comments:
			
			//open url to fill out OLCH form
			Intent openWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.genetics.ie/cytogenetics/"));
			//http://www.genetics.ie/documents/request-form-for-genetic-testing.pdf
			
			//Intent in=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.twitter.com/"));
            startActivity(openWebsite);
			
			
			break;
		case R.id.tvrefRanges_Input:
			displayDialog(this.refRangesTV.getText().toString(), "Reference Ranges");
			break;
			
		case R.id.tvTable_Referral_Center_Input:
			
			displayreferralWebsiteDialog(this.referralLabTV.getText().toString(), "Referral Center");
			break;

		case R.id.tv_title:

			// make a phone call and rerun to activity after it
			phineNumber = phineNumberTV.getText().toString();
			
			//check if devoce can make calls first, if not display dialog
			boolean canDeviceMakeCall = isTelePhoneEnalbled();
			if(canDeviceMakeCall){
				
			

			// add PhoneListener for monitoring phone state ie call ends
			MyPhoneListener phoneListener = new MyPhoneListener();
			TelephonyManager telephonyManager = (TelephonyManager) this
					.getSystemService(Context.TELEPHONY_SERVICE);

			// receive notfiviatipns of telephones state
			telephonyManager.listen(phoneListener,
					PhoneStateListener.LISTEN_CALL_STATE);

			try {
				/*
				 * set the phone number data, dial will open the phone dialler,
				 * call will ake the call immediatly, also need to add permissions to manifast for 
				 * reading call sate and making calls
				 */

				String uri = "tel" + phineNumber;
				Intent callIntent = new Intent(Intent.ACTION_DIAL,
						Uri.parse(uri));
				startActivity(callIntent);

			} catch (Exception e) {
				Toast.makeText(
						getApplicationContext(),
						"Invalid Number! The number must be an external Hosptial Number!",
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}

			}else if(!canDeviceMakeCall){
				//dvice cannot make a call
				displayDialog("Your devoce does not hane telephone enabled!!", "Phone Error!");
			}
			break;
			
		case R.id.btnImg_Like:
			
			// start a webview intent here to hit like button once we have publish URL on google play
			
			break;
			
		}// end switch
		
		

	}// end onClick

	
	
	private void displayreferralWebsiteDialog(final String  referralCenter, String title) {
		// display dialog giving user option t naviage to url of referral alb
		
		AlertDialog.Builder build = null;
		AlertDialog dialog = null;
		build = new AlertDialog.Builder(this);
		build.setCancelable(true);
		build.setMessage(referralCenter);
		build.setPositiveButton("Go to Website",
				new DialogInterface.OnClickListener() {

			
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// naviagt to webiste of yes
						
						/*private String OLCHwebsiteURL, kingsColleggeWebSiteURL, TDLWebsiteURL, SJHwebsiteURL, stVincentswebsiteURL,
			TSHwebsiteURL, drugTreamentCenterwebsiteURL, IBTSwebsiteURL, materWebsiteURL, addenbrokesWebsiteURL,
			sheffieldNHSwebsiteURL, manchesterUHwebsiteURL, beumountWebsiteURL, cherryOrcahrdWebsiteURL,
			NVRLWebsiteURL, AMNCHwebsiteURL, TDLGenticsWebsiteURL, OLCHGenticsURL,
			NCHCDWebsiteURL, IBGRLBristolWebsiteURL, biominisClaymonWebsiteURL, medLabPathologyWebsiteURL,
			newcastleRIWebsiteURL, royalBromptonNHSWebsiteURL, willLinkNHSWebsiteURL, nationalHospitalLondonWebsiteURL,
			westernScotlandGenticsWebsiteURL, stateLabWebsiteURL, instittueOfNerologyLondoneURL, belfastCityHospitalURL;
			*/
						
						String URL= "";
						if(referralCenter.toLowerCase().contains("nchc")){
							URL = SelectedTestDetails.this.NCHCDWebsiteURL;
							
						}else if(referralCenter.toLowerCase().contains("olch")){
							URL = SelectedTestDetails.this.OLCHwebsiteURL;
							
						}else if(referralCenter.toLowerCase().contains("king")){
							URL = SelectedTestDetails.this.kingsColleggeWebSiteURL;
						}else if(referralCenter.toLowerCase().contains("tdl")){
							URL = SelectedTestDetails.this.kingsColleggeWebSiteURL;
						}else if(referralCenter.toLowerCase().contains("sjh")){
							URL = SelectedTestDetails.this.SJHwebsiteURL;
						}else if(referralCenter.toLowerCase().contains("vincen")){
							URL = SelectedTestDetails.this.stVincentswebsiteURL;
						}else if(referralCenter.toLowerCase().contains("tsh")){
							URL = SelectedTestDetails.this.TSHwebsiteURL;
						}else if(referralCenter.toLowerCase().contains("drug")){
							URL = SelectedTestDetails.this.drugTreamentCenterwebsiteURL;
						}else if(referralCenter.toLowerCase().contains("ibts")){
							URL = SelectedTestDetails.this.IBTSwebsiteURL;
						}else if(referralCenter.toLowerCase().contains("mater")){
							URL = SelectedTestDetails.this.materWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("adden")){
							URL = SelectedTestDetails.this.addenbrokesWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("sheff")){
							URL = SelectedTestDetails.this.sheffieldNHSwebsiteURL;
						}else if(referralCenter.toLowerCase().contains("man")){
							URL = SelectedTestDetails.this.manchesterUHwebsiteURL;
						}else if(referralCenter.toLowerCase().contains("mount")){
							URL = SelectedTestDetails.this.beumountWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("cherry")){
							URL = SelectedTestDetails.this.cherryOrcahrdWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("nvrl")){
							URL = SelectedTestDetails.this.NVRLWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("amnc")||referralCenter.toLowerCase().contains("tal")){
							URL = SelectedTestDetails.this.AMNCHwebsiteURL;
						}else if(referralCenter.toLowerCase().contains("cherry")){
							URL = SelectedTestDetails.this.cherryOrcahrdWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("tdl")&& referralCenter.toLowerCase().contains("gen")){
							URL = SelectedTestDetails.this.TDLGenticsWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("olch")&& (referralCenter.toLowerCase().contains("genet"))){
							URL = SelectedTestDetails.this.OLCHGenticsURL;
						}else if(referralCenter.toLowerCase().contains("nchc")){
							URL = SelectedTestDetails.this.NCHCDWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("ibgr")){
							URL = SelectedTestDetails.this.IBGRLBristolWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("biomin") ||referralCenter.toLowerCase().contains("claym") ){
							URL = SelectedTestDetails.this.biominisClaymonWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("medlab")){
							URL = SelectedTestDetails.this.medLabPathologyWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("newcastl")){
							URL = SelectedTestDetails.this.newcastleRIWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("bromp")){
							URL = SelectedTestDetails.this.royalBromptonNHSWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("will")){
							URL = SelectedTestDetails.this.willLinkNHSWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("london") && referralCenter.toLowerCase().contains("national")){
							URL = SelectedTestDetails.this.nationalHospitalLondonWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("west") && referralCenter.toLowerCase().contains("scotland")){
							URL = SelectedTestDetails.this.westernScotlandGenticsWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("state")){
							URL = SelectedTestDetails.this.stateLabWebsiteURL;
						}else if(referralCenter.toLowerCase().contains("inst")&& referralCenter.toLowerCase().contains("neurol")){
							URL = SelectedTestDetails.this.instittueOfNerologyLondoneURL;
						}
						else if(referralCenter.toLowerCase().contains("belfast")){
							URL = SelectedTestDetails.this.belfastCityHospitalURL;
						}
							
						
						try{
						
						//open url to fill out OLCH form
						Intent openWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
						//http://www.genetics.ie/documents/request-form-for-genetic-testing.pdf
						
						//Intent in=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.twitter.com/"));
			            startActivity(openWebsite);
						}catch(Exception e){
							e.printStackTrace();
							SelectedTestDetails.this.displayDialog("Not found", "Refrral Center Website");
						}

					}// end on click
				});// end pos button take photo

		// get photo from SD card option
		build.setNegativeButton("OK",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						//finish();

					}// end onclick
				});// end pos button take photo
		dialog = build.create();
		dialog.setTitle(title);
		dialog.show();
		
		
		
		
		
	}//end displayRefWebsiteURl




	private boolean isTelePhoneEnalbled() {
		// check to see if phone is enabled on device
		
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		
		return telephonyManager != null && telephonyManager.getSimState()==TelephonyManager.SIM_STATE_READY;
	}




	private void displayDialog(String string, String title) {
		// dipslays any mesdsage passed a a alert dialog 
		
		String titleMessage = title;
		String message = string;
		AlertDialog alert = new AlertDialog.Builder(this).create();
		alert.setTitle(titleMessage);
		alert.setMessage(message);
		alert.setButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// quit this activity and return to logdve to avoid null pointer http exceptions
				//finish();
				
			}
		});
		
		alert.show();
		
		
	}//end display dialog



	private class MyPhoneListener extends PhoneStateListener {
		
		/*
		 * inner class is a phone listener and returns the user to this activity
		 * once call is over, also need to add permissions to manifast for reading call sate and making calls
		 */

		private boolean onCall = false;

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

			switch (state) {

			case TelephonyManager.CALL_STATE_RINGING:

				// phone ringing...

				Toast.makeText(SelectedTestDetails.this,
						incomingNumber + " calls you",

						Toast.LENGTH_LONG).show();

				break;

			case TelephonyManager.CALL_STATE_OFFHOOK:

				// one call exists that is dialing, active, or on hold

				Toast.makeText(SelectedTestDetails.this, "on call...",

				Toast.LENGTH_LONG).show();

				// because user answers the incoming call

				onCall = true;

				break;

			case TelephonyManager.CALL_STATE_IDLE:
				// in initialization of the class and at the end of phone call

				// detect flag from CALL_STATE_OFFHOOK

				if (onCall == true) {

					Toast.makeText(SelectedTestDetails.this,
							"restart app after call",

							Toast.LENGTH_LONG).show();

					// restart our application

					Intent restart = getBaseContext().getPackageManager().getLaunchIntentForPackage(
									getBaseContext().getPackageName());

					restart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

					startActivity(restart);

					onCall = false;

				}

				break;
			}// end switch

		}// end onCallStateChanges

	}// end inner class MyPhoneListener

}// end SelectedTestDetails outer class
