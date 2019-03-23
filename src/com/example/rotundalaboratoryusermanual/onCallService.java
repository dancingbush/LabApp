package com.example.rotundalaboratoryusermanual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class onCallService extends Activity implements OnClickListener{
	
	
	private TextView mainHeading, subHeading, subHeading2, details;
	private Button home;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.general_inof);
		
		intilizeWidgets();
		setTextViewDetails();
		
	}
	
	
	

	private void setTextViewDetails() {
		// TODO Auto-generated method stub
		
		
		mainHeading.setText("On Call Service");
		subHeading.setText("Bleep 568");
		subHeading2.setText("Tests availble by department");
		
		Spanned haem = Html.fromHtml("<b>Haematology</b>");
		Spanned bio = Html.fromHtml("<b>Biochemistry</b>");
		Spanned Transfusion = Html.fromHtml("<b>Transfusion</b>");
		Spanned Microbiology = Html.fromHtml("<b>Micriobiology</b>");
		Spanned comment = Html.fromHtml("<b><i>must relevant clinical details and be clearly marked urgent</b></i>");
		
		
		details.setText("\n" +
				"On call requests "+comment+"\n\n"+
				"On call service operates 1800-0800 weekdays, 1230 Saturday morning to 0800 Monday.\n" +
				"A routine servie operates 0900-1230 Saturday Mornings.\n" +
				"Bank Holiday Mondays qulaify as a on call service.\n\n" +
				"" +
				"Inform the scientist on duty via bleep if samples require processing on call." +
				"\nUrgent Specimens or sample requiring separation should be left in l" +
				"aboratory reception on the blue tray at the hatch. Any other specimens being left into the laboratory out of " +
				"hours but which do not require processing" +
				" until the next day should be placed in the specimen " +
				"fridge in laboratory reception.\n\n");
		
		details.append(haem+"\n1. FBC & Diff.\n2. Coagulation Screens (PT, APTT, Fib).\n" +
				"3. Sickle Screen.\n" +
				"4. Malaria screen.\n\n");
		details.append(bio +"\nAll routine biochemistry (gentamicin assays on Sunday morning only)" +
				"(With the exception of the following: Lipid profile, Urine Biochemistry and Fructosamine)\n" +
				"Oestradiol, FSH and LH for HARI unit - Sunday and bnal holiday mornings only.\n\n");
		details.append(Transfusion+"\n1. ABO/Rhesus grops and antibody screening.\n" +
				"2. DCT\n" +
				"3. Issue of anti-D if patient senstising event>72hrs " +
				"by next working day.\n" +
				"4. Issue of blood and blood products to neonates.\n" +
				"5. Crossmatch and issue of blood to adults.\n" +
				"6. Antibody investigations on in patients where required.\n\n");
		details.append(Microbiology+"\n1.CSF examination and culture.\n" +
				"2. Urine examination and culture.\n" +
				"3. Culture of wound swabs where required.\n" +
				"4. Blood cultures: loading onto analyzer and reproting of postive gram stains." +
				"Reporting of 36 hour incubation with no growth on Saturday, Sunday and Bank Holiday mornings\n" +
				"5. Dispatch of emergency virology specimen to National Virus Reference Laboratory, " +
				"dispatch of emergency vancomycin levels to " +
				"TSH and dispatch of emergency stool samples " +
				"for c.diff to Beaumont Hospital.");
		
		
		
	}

	private void intilizeWidgets() {
		// TODO Auto-generated method stub
		
		this.mainHeading = (TextView) this.findViewById(R.id.tv_general_InfoMainHeading);
		this.subHeading = (TextView) this.findViewById(R.id.tv_courseName_details);
		this.subHeading2 = (TextView) this.findViewById(R.id.whatuLearn);
		this.details = (TextView) this.findViewById(R.id.tv_generalInfo_MianText);
		
		
		
		
		this.home = (Button) this.findViewById(R.id.btn_genralInfo_Home);
		home.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// return to main menu when clicked
		Intent intent = new Intent(onCallService.this, MainMenu.class);
		startActivity(intent);
		
		
	}

	

}
