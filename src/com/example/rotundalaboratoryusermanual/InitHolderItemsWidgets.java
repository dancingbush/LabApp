package com.example.rotundalaboratoryusermanual;

import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;




public class InitHolderItemsWidgets {

	/*
	 * Intilasation of the the holder vieew layouts.
	 * by default some fodd images are added for intolisation of the holder
	 * 
	 * We get each widget (imageview or textview) from the holder_layout.xml via cm.getCirleMenuItemsArray().get(index).getHolderChildView(R.id.etc)
	 * 
	 * 
	 * Set the text of each menu otem here, the image that rotates  on the bottom in menu_circle.xml, via bodyText1.setText(text);
	 * The main image is set titleImage1, set via a drawbale resource
	 * 
	 * TO set the image that allows rotation of the menu, set images in teh circe_menu.xml
	 */

	
	public static void init(CircleMenu cm){
		
		//menu item 1 Blood Cultiers order of draw 1
		
		ImageView titleimage1=(ImageView) cm.getCircleMenuItemsArray().get(0).getHolderChildView(R.id.titleimage);
		titleimage1.setImageResource(R.drawable.bloodcultuers);
        TextView bodytext1=(TextView)cm.getCircleMenuItemsArray().get(0).getHolderChildView(R.id.bodytext);
        bodytext1.setText(Html.fromHtml("<b>Blood Cultuers <br></br><br></br>Order of draw (Adults and Newborn bloods): </b> 1" ));
		bodytext1.append("\n\nAerobic follwed by anaerobic.\n" +
				"Blood cultures should always be drawn first to reduce the potential hazard bacterial, fungal, quantitative, CMV and/or other specimen contamination.\n" +
				"If insufficient for both aerobic and anaerobic use aerobic only." +
				"  \n\nPostive results phoned to ward/consultant. Incubation time 5 days." );
		
		//menu item 2 Soduim Citrate Tube
		
		ImageView titleimage2=(ImageView) cm.getCircleMenuItemsArray().get(1).getHolderChildView(R.id.titleimage);
		titleimage2.setImageResource(R.drawable.citrate);
        TextView bodytext2=(TextView)cm.getCircleMenuItemsArray().get(1).getHolderChildView(R.id.bodytext);
		bodytext2.setText(Html.fromHtml("<b>Soduim Citrate 3ml (Blue Top- Adult)<br></br><br></br>Order of draw: </b> 2" ));
		bodytext2.append("\n\nFill to arrow line on side of tube.  Send to lab immediately, 4hrs stability only.\n\n" +
				"Must be drawn prior to collection of serum tubes to prevent contamination with clot activator and interference with the coagulation cascade.\n" +
				"\nDeterminations: Coagulation studies, Thrombophilia studies, " +
				"Lupus (Protein C, Antithrombin, P2 Mutation, + FVL + Protein S)" +
				" DDMIER. \n\nFill to arrow line on side of tube.  \n\nAfter collection, invert tube 4-8 times" );
		
		
		//menu item 3 Serum order of draw 3
		
		ImageView titleimage3=(ImageView) cm.getCircleMenuItemsArray().get(2).getHolderChildView(R.id.titleimage);
		titleimage3.setImageResource(R.drawable.serum);
        TextView bodytext3=(TextView)cm.getCircleMenuItemsArray().get(2).getHolderChildView(R.id.bodytext);
        bodytext3.setText(Html.fromHtml("<b>Serum SST (Clotting Accelerator with seperator (yellow ring))<br></br><br></br>Order of draw: </b>  3 " ));
		bodytext3.append("\n\n" +
				"Serum tubes are coated on the inside walls with clot activator because the surface of a plastic tube does not promote coagulation.\n" +
				"\nDeterminations: Antenatal Virology Screen: Rubella, HIV, Hep B &C, Hep B Core, " +
				"VZ, Syphilis. \n" +
				"Virology serology, Hepatitis B, C, HIV, VZ, CMV, Rubella, Torch Screen, " +
				"Measles, Hepatitis B & C PCR, Autoantibodies." +
				" \n\nPCR samples must be brought to the laboratory immediately. " +
				"Latest permitted time – 2pm." +
				"\n\nAfter collection, invert tube 5 – 10 times" );
		
		
		//menu item 4 Serum Gel no clot accelteratpr, oder of draw 4
		
		ImageView titleimage4=(ImageView) cm.getCircleMenuItemsArray().get(3).getHolderChildView(R.id.titleimage);
		titleimage4.setImageResource(R.drawable.serum);
        TextView bodytext4=(TextView)cm.getCircleMenuItemsArray().get(3).getHolderChildView(R.id.bodytext);
        bodytext4.setText(Html.fromHtml("<b>Serum 4ml  (Lid with black ring-No Clotted Accelerator)<br></br><br></br>Order of draw: </b>  4 " ));
		bodytext4.append("\n\nDeterminatons: Endocrinology (Hormone), Ferritin. CA-125. Triple test. Vit. B12,Folate." +
				"\nSamples in the laboratory before 12pm will be tested on the same day. A serum ferritin sample " +
				"is required for Haemoglobinopathy screen.\n Triple test to be in lab by 2pm for same day despatch." +
				"\n\nAfter collection, invert tube 5 – 10 times" );
		
		
		//menu item 5 Lithum heparin adult order of draw 5
		
		ImageView titleimage5=(ImageView) cm.getCircleMenuItemsArray().get(4).getHolderChildView(R.id.titleimage);
		titleimage5.setImageResource(R.drawable.lithium_hep_adult);
        TextView bodytext5=(TextView)cm.getCircleMenuItemsArray().get(4).getHolderChildView(R.id.bodytext);
        bodytext5.setText(Html.fromHtml("<b>Lithium Heparin 4ml  (Green Top with black ring (No Gel)- Adult)<br></br><br></br>Order of draw: </b>  5 " ));
		bodytext5.append("\n\nCytogenetics (Karayotype) (Chromosomes)" +
				"\n\nAfter collection, invert tube 5 – 10 times" );
		
		//menu item 6, lithium heparin Gel with yellow ring, order of draw 6
		
		ImageView titleimage6=(ImageView) cm.getCircleMenuItemsArray().get(5).getHolderChildView(R.id.titleimage);
		titleimage6.setImageResource(R.drawable.lithium_hep_adult);
        TextView bodytext6=(TextView)cm.getCircleMenuItemsArray().get(5).getHolderChildView(R.id.bodytext);
        bodytext6.setText(Html.fromHtml("<b>Lithium Heparin Gel 4ml  (Green Top with yellow ring- Adult)<br></br><br></br>Order of draw: </b>  6 " ));
		bodytext6.append("\n\nRoutine Biochemistry (UE's, LFTs etc). " +
				"\nSample to arrive in laboratory within 5 hours " +
				"\nLipid profile requires a fasting sample(14 hours))" +
				"\n\nAfter collection, invert tube 5 – 10 times" );
		
		
		//menu item 7 EDTA Adult 2.5m order of draw 7
		
		
		ImageView titleimage7=(ImageView) cm.getCircleMenuItemsArray().get(6).getHolderChildView(R.id.titleimage);
		titleimage7.setImageResource(R.drawable.edta_adult);
        TextView bodytext7=(TextView)cm.getCircleMenuItemsArray().get(6).getHolderChildView(R.id.bodytext);
        bodytext7.setText(Html.fromHtml("<b>EDTA Plasma 2.5ml/9ml (Purple Top- Adult)<br></br><br></br>Order of draw: </b>  7 " ));
		bodytext7.append("\n\nFor Transfusion use 9ml samples. Hand write with " +
				"hospital number, date of birth and full name. " +
				"Tube to be signed by the person taking the sample." +
	
				"\nDeterminations: Antenatal Blood Group, Group and save, Crossmatch, " +
				"Anti-D Quantitation. \n\nHIV PCR, HIV Viral Load," +
				"\n\nHaematology use 2.5ml. FBC, Kleihauer, Molecular Genetics, Homocysteine, Haemoglobinopathy screen, " +
				"Red Cell Folate, ESR, Cyclosporin, HbA1C, Reticulocytes,P II Mutation (Thrombophilias), " +
				"FMH, Malaria Screen, Blood Films (must be <2 hours old fro malaria nd blood film.)" +
				"\n\nSamples for homocysteine to be sent to laboratory immediately. " +
				"\n2 bottles  EDTA and 1 serum required for haemoglobinopathy screen. " +
				"PCR samples must be brought to the laboratory immediately " +
				"Latest permitted time – 2pm." +
				"\nFMH: Sample to be taken at least 30 mins post delivery. " +
				"\n\nAfter collection, invert tube 8 – 10 times" );
		
		//menu item 8 Flouride Oxalalte order of draw 8
		
		ImageView titleimage8=(ImageView) cm.getCircleMenuItemsArray().get(7).getHolderChildView(R.id.titleimage);
		titleimage8.setImageResource(R.drawable.flourideoxalte);
        TextView bodytext8=(TextView)cm.getCircleMenuItemsArray().get(7).getHolderChildView(R.id.bodytext);
        bodytext8.setText(Html.fromHtml("<b>Flouride Oxalate Plasma 4ml  (Grey Top- Adult)<br></br><br></br>Order of draw: </b>  8 " ));
		bodytext8.append("\n\nGlucose, Glucose Tolerance Testing." +
				"\nCollection time must be handwritten on the specimen tube " +
				"\n\nAfter collection, invert tube 5 – 10 times" );
		
		//menu item 9 Paedaitric Tubes: Citrate no 2 (blood cultuers first)
		
		ImageView titleimage9=(ImageView) cm.getCircleMenuItemsArray().get(8).getHolderChildView(R.id.titleimage);
		titleimage9.setImageResource(R.drawable.paed_citrate);
        TextView bodytext9=(TextView)cm.getCircleMenuItemsArray().get(8).getHolderChildView(R.id.bodytext);
        bodytext9.setText(Html.fromHtml("<b>Soduim Citrate Plasma 9NC 1.3ml  (Green Top- Paediatric)<br></br><br></br>Paediatric Order of draw: </b>  2 " ));
		bodytext9.append("\n\nMust fill to 1.3 mL mark.\n Send to the laboratory a.s.a.p." +
				"\nSamples must be tested within 4 hours of venopuncture. " +
				"\nDeterminations: Factor assyas and general coagulation. " +
				"\n\nInvert and mix gently to avoid Haemolysis Clotting." );
		
       
		//menu item 10, Paediatric no anticoaulant order of draw 3
		
		ImageView titleimage10=(ImageView) cm.getCircleMenuItemsArray().get(9).getHolderChildView(R.id.titleimage);
		titleimage10.setImageResource(R.drawable.paed_clotted);
        TextView bodytext10=(TextView)cm.getCircleMenuItemsArray().get(9).getHolderChildView(R.id.bodytext);
        bodytext10.setText(Html.fromHtml("<b>Clotted 0.4-2ml  (Transparent Top- Paediatric)<br></br><br></br>Paediatric Order of draw: </b>  3 " ));
		bodytext10.append("\n\nPaediatric TORCH Hepatitis B, TP(syphilis) antibody." +
				"\n\nNo additive or anticoagulant." );
		
		
		//menu item 11, Paediatric Lithium Heparin with gel order of draw 3
		
		ImageView titleimage11=(ImageView) cm.getCircleMenuItemsArray().get(10).getHolderChildView(R.id.titleimage);
		titleimage11.setImageResource(R.drawable.paed_lihep_gel);
        TextView bodytext11=(TextView)cm.getCircleMenuItemsArray().get(10).getHolderChildView(R.id.bodytext);
        bodytext11.setText(Html.fromHtml("<b>Lithiu Heparin with Gel  (Transparent Top- Preferred Paediatric Tube)<br></br><br></br>Paediatric Order of draw: </b>  4 " ));
        bodytext11.append("\n\nPreferred tube for all paediatric Biochemistry sample EXCEPT **" +
        		"(See next Li Hep no Gel- glucose and Newcastle workup).\n" +
        		"\n\nDeterminations: Urea, Na, K, Ca, Po4, Creatinine, Bilirubin, LFT, Protein, Albumin, CRP." +
        		"\n\nMix gently immediately." );
		
		//menu item 12, Paediatric Lithium Heparin no gel order of draw 4
		
		ImageView titleimage12=(ImageView) cm.getCircleMenuItemsArray().get(11).getHolderChildView(R.id.titleimage);
		titleimage12.setImageResource(R.drawable.paed_lithhep);
        TextView bodytext12=(TextView)cm.getCircleMenuItemsArray().get(11).getHolderChildView(R.id.bodytext);
        bodytext12.setText(Html.fromHtml("<b>Lithiu Heparin with no Gel  (Orange Top- Paediatric Tube)<br></br><br></br>Paediatric Order of draw: </b>  5 " ));
        bodytext12.append("\n\nThis is the 2nd preference bottle for Paediatric samples. " +
        		"Samples must be received in laboratory within 15 minutes." +
        		"\n\nDeterminations: Urea, Na,K,Ca,Po4, Creatinine, Bilirubin, LFT, Protein, Albumin, CRP. Ammonia, Karyotyping." +
        		
        		"\n\nMix gently immediately." );
		
      //menu item 12, Paediatric Flourdie oxalate  no gel order of draw 6 
        
		ImageView titleimage13=(ImageView) cm.getCircleMenuItemsArray().get(12).getHolderChildView(R.id.titleimage);
		titleimage13.setImageResource(R.drawable.paed_flourideoxalate);
        TextView bodytext13=(TextView)cm.getCircleMenuItemsArray().get(12).getHolderChildView(R.id.bodytext);
        bodytext13.setText(Html.fromHtml("<b>Flouride Oxalate (Yellow Top- Paediatric Tube)<br></br><br></br>Paediatric Order of draw: </b>  6 " ));
        bodytext13.append("\n\nMust use for Glucose and lactate.\n" +
        		"Samples must be received in laboratory within 15 minutes." +
        		"\n\nMix gently immediately." );
        
        
      //menu item 14, Paediatric EDTA no gel order of draw 7
        
		ImageView titleimage14=(ImageView) cm.getCircleMenuItemsArray().get(13).getHolderChildView(R.id.titleimage);
		titleimage14.setImageResource(R.drawable.paed_edta);
        TextView bodytext14=(TextView)cm.getCircleMenuItemsArray().get(13).getHolderChildView(R.id.bodytext);
        bodytext14.setText(Html.fromHtml("<b>EDTA Plasma 1.3ml  (Red Top- Paediatric)<br></br><br></br>Paediatric Order of draw: </b>  1 " ));
		bodytext14.append("\n\nDeterminations: \nPaediatric Blood group,DCT, Antibody Screen. \nFor transfusion specimans,  Handwrite patient’s name, hospital number, " +
				"date of birth on tube label and sign.\n" +
				"\nPaediatric FBC, ReticulocytesEMA, G-6PDPK, Red cell enzymes, " +
				"\n\nHIV PCR and Viral Load, CMV PCR," +
				"\n\nGroup B Strep,PCR, Blood Films." +
				"\n\nAfter collection, invert tube gently to avoid haemolysis" );
        
		
		
		//if we want t add more menu tems we add the here, temp commeneted our
		
        
		/*
		 
		ImageView titleimage15=(ImageView) cm.getCircleMenuItemsArray().get(14).getHolderChildView(R.id.titleimage);
		titleimage15.setImageResource(R.drawable.cat_preview_pasta);
        TextView bodytext15=(TextView)cm.getCircleMenuItemsArray().get(14).getHolderChildView(R.id.bodytext);
		bodytext15.setText("Pasta is a type of noodle and is a staple food of traditional Italian cuisine, with the first reference dating to 1154 in Sicily. It is also commonly used to refer to the variety of pasta dishes. Typically pasta is ....");
		
		ImageView titleimage16=(ImageView) cm.getCircleMenuItemsArray().get(15).getHolderChildView(R.id.titleimage);
		titleimage16.setImageResource(R.drawable.cat_preview_pie);
        TextView bodytext16=(TextView)cm.getCircleMenuItemsArray().get(15).getHolderChildView(R.id.bodytext);
		bodytext16.setText("Pies are defined by their crusts. A filled pie (also single-crust or bottom-crust), has pastry lining the baking dish, and the filling is placed on top of the pastry but left open. A top-crust pie, which may also be ....");
		
		ImageView titleimage17=(ImageView) cm.getCircleMenuItemsArray().get(16).getHolderChildView(R.id.titleimage);
		titleimage17.setImageResource(R.drawable.cat_preview_pizza);
        TextView bodytext17=(TextView)cm.getCircleMenuItemsArray().get(16).getHolderChildView(R.id.bodytext);
		bodytext17.setText("Pizzais an oven-baked, flat, round bread typically topped with a tomato sauce, cheese and various toppings. The modern pizza was originally invented in Naples, Italy, and the dish has since become popular in many parts of the world. ....");
		
		ImageView titleimage18=(ImageView) cm.getCircleMenuItemsArray().get(17).getHolderChildView(R.id.titleimage);
		titleimage18.setImageResource(R.drawable.cat_preview_salad);
        TextView bodytext18=(TextView)cm.getCircleMenuItemsArray().get(17).getHolderChildView(R.id.bodytext);
		bodytext18.setText("Salad is a popular, ready-to-eat dish made of heterogeneous ingredients, usually served chilled or at a moderate temperature. Many people use the word salad to describe light, savory leafy vegetable dishes, most often served with a ....");
	*/
	
	}

}
