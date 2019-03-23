package com.example.rotundalaboratoryusermanual;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class about extends Activity {

	/*
	 * General descition of Rotnda nd lab, qulaity poilcy etc
	 * 
	 */
	
	private TextView rotundaHospialLabTitle, routndaHospitalAbout, visionTitle, theVision, qualityPolicyTitle,
			theQulaityPolicy, achivementsTitle, theAchivments, historyTitle, historyText1, historyText2;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.about);
		
		intiliseWidgets();
		
		setTextForAllTextViews();
		
	}

	private void setTextForAllTextViews() {
		// set text 
		
		rotundaHospialLabTitle.setText("Rotunda Hospital Dept of Laboratory Medicine\n" +
				"(Maternoty Hosiptal of the year 2013)\n\n");
		routndaHospitalAbout.setText("Bartholomew Mosse, surgeon and man-midwife founded the Rotunda Hospital in 1745. " +
				" Originally known as The Dublin Lying-In Hospital, " +
				"it was the first maternity training hospital of its kind.\n\n" +
				"The first pathology laboratory opened in the Rotunda Hospital in 1897.\n" +
				"The Laboratory has continued to expand to the present day adopting the latest " +
				"diagnostic tools available to medical scientists." +
				"  This app is intended to give the user an overall view of the services " +
				"provided by the pathology department.\n\n");
		
		visionTitle.setText("Rotunda Statgic Plan 2014-2016\n\n");
		theVision.setText("As the leading Voluntary provider of maternity, neontatal," +
				"gynaecology, and reproductive care, our mission is to " +
				"excel in the delivery of safe, innovative and responsiveservices for women and " +
				"their families.\n" +
				"To deliver on the stated vision and mission" +
				" we are concentrating on five priority areas, with corresponding strategic objectives.\n" +
				" - Patient and Family Centred Care,\n" +
				" - Resources,\n" +
				" - Performance,\n" +
				" - Leadership,\n"
				+" -Research and Education\n\n");
		
		qualityPolicyTitle.setText("Quality Statment\n\n");
		theQulaityPolicy.setText("The Department of Laboratory Medicine at the Rotunda Hospital undergoes continuous " +
				"review  through quality assurance and audit activities " +
				"and is committed to providing" +
				" a service of the highest quality and shall be" +
				" aware and take into consideration the needs and " +
				"requirements of its users.\n\n" );
		this.theAchivments.setText("\n\nThe Department of Laboratory Medicine has achieved accreditation to international standards  under "+ Html.fromHtml("<i>ISO 15189</i>")+" Medical laboratories-Particular" +
				" Requirements for Quality and Competence and " +
				 Html.fromHtml("ISO 22870 Point of Care Testing-Requirement")+ " for" +
				" Quality and Competency");
		
		
		
		
	}

	private void intiliseWidgets() {
		// intilise all textviews
		
		
		rotundaHospialLabTitle = (TextView) this.findViewById(R.id.tv_aboutTitleOfCompany);
		routndaHospitalAbout = (TextView) this.findViewById(R.id.tv_about_CompanySummary);
		visionTitle = (TextView) this.findViewById(R.id.tv_about_heading2);
		theVision = (TextView) this.findViewById(R.id.tv_text_about_UnderHeading2);
		qualityPolicyTitle = (TextView) this.findViewById(R.id.tv_about_heading3);
		theQulaityPolicy = (TextView) this.findViewById(R.id.tv_about_textUnderHaeding3);
		achivementsTitle = (TextView) this.findViewById(R.id.tv_about_headiing4);
		theAchivments = (TextView) this.findViewById(R.id.tv_about_textunder_heading4);
		
		
		//set the remianing textviews to invisble until used
		
		historyTitle = (TextView) this.findViewById(R.id.tV_about_heading5);
		historyText1= (TextView) this.findViewById(R.id.tV_about_textUnderHeading5);
		historyText2 = (TextView) this.findViewById(R.id.tv_about_textUnderHeading5B);
		historyTitle.setText("\nComplinace");
		historyText1.setText("\n\nThe department will comply with the requirements of:\nn\n");
		
		
		
		/*
		historyTitle.setVisibility(View.GONE);//when gone is invisible and dosent take up any space
		historyText1.setVisibility(View.GONE);
		historyText2.setVisibility(View.GONE);
		*/
		
	}
	
	

}//end class
