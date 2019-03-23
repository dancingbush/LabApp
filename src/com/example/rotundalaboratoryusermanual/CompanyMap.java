package com.example.rotundalaboratoryusermanual;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.GesturePoint;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class CompanyMap extends FragmentActivity implements
				GooglePlayServicesClient.ConnectionCallbacks,
				GooglePlayServicesClient.OnConnectionFailedListener {
	
	
	/*
	 *Add Google map V2API.jar as a libary by Project/Properties/Android/Libarys/Add .... select the hgoogle jar(
	 *this ,ust be dooaded thorugh Windoew SDK updates if not already done).
	 *See instructions here https://docs.google.com/document/pub?id=19nQzvKP-CVLd7_VrpwnHfl-AE9fjbJySowONZZtNHzw
	 *
	 *
	 * my SHA-1 devoling key form ADT/Prferences/Android Buid s
	 * C4:77:DA:10:2C:C1:D0:51:B5:99:39:4E:CC:29:5E:6D:AF:55:F4:A8
	 * 
	 * My Google API key is AIzaSyBcvfHSI1u-NEQo6VBx-CtxyrkLxsq7GQc
	 * from here https://code.google.com/apis/console/?noredirect#project:746362085267:access
	 * 
	 * 
	 */
	double user_current_latitude; 
	double user_current_longitude; 
	LocationClient mLocationClient;
	// You should get your company latitude & longitude from website like: http://itouchmap.com/latlong.html or www.latlong.net
	// For this example I made my destination target is Makkah (Al Masjid AlHaram).
	double company_latitude  = 53.353027;// You should replace this with your Company location latitude
	double company_longitude = -6.263554;// You should replace this with your Company location longitude
	LatLng company_latitude_longitude; 
	String showDirectionsURL; //This URL used to show directions from user current location to the destination target

	GesturePoint Company_Location; // This GeoPoint should be your company GeoPoint (Your Company point on Google Map)
	private GoogleMap mMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		//Creating new GeoPoint for your Company,  a standard to Multiply it by 1000000.
		company_latitude_longitude = new LatLng(company_latitude, company_longitude);
		setUpMapIfNeeded();		
	}


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * added a marker at "The Open University" in the UK.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(company_latitude_longitude).title("Marker"));
        // Move the camera instantly to location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(company_latitude_longitude, 3));

        // Zoom in, animating the camera. increase zoomTo(value) if you want to zoom in more and vice versa
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2400, null);
    }

	
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		/*
		 *When user selectews Menu button on teh device the directions will show 
		 *  Creating "Show Direction" Menu item:
		 */
		
		menu.add(0, 1, 0, "Show Directions").setIcon(android.R.drawable.ic_menu_directions);
		return super.onCreateOptionsMenu(menu);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

	    //If neither GPS nor Wireless/Network Location Services are Enabled: Show Alert dialog.
	    if( !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
	    	!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setTitle("Enable location services");  // GPS not found
	        builder.setIcon(android.R.drawable.ic_dialog_alert);
	        builder.setMessage("You need to enable GPS / Wireless or Network location services to show directions, Enable it now?"); // Want to enable?
	        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialogInterface, int i) {
	                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
	            }
	        });
	        builder.setNegativeButton("No", null);
	        builder.create().show();
	        //If GPS is enabled:
	    }else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
    		establishConnection();
	        //If GPS is disabled & Wireless/Mobile Network is enabled:
	    }else if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
	    		&& locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
	    	Toast.makeText(CompanyMap.this, "Wireless/Mobile Network used to detect location, for accurate detection please enable GPS service",Toast.LENGTH_LONG).show();
			establishConnection();
	    }
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * This method check User's Internet connection
	 * @return true if online, false if offline
	 */
	private boolean hasInternetConnection() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(MainMenu.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	/**
	 * This method is used to get the link address needed in show direction process
	 * @return Show Directions URL Address
	 */
	public String getShowDirectionsURL() {
		// getting user location		
		// This is the URL Link that show directions from the User current
		// location to the Company location
		showDirectionsURL = "http://maps.google.com/maps?saddr="
				+ user_current_latitude + "," + user_current_longitude
				+ "&daddr=" + company_latitude + "," + company_longitude;
	    Log.i("userLocation", "return url: " + showDirectionsURL);

		return showDirectionsURL;
	}
	
	/*
	 * this method used to get current User location, and update it if he moved.
	 */
	public void establishConnection(){
	    Log.i("userLocation", "establishing connection");

		mLocationClient = new LocationClient(this, this, this);
		mLocationClient.connect();
	    Log.i("userLocation", ".connect()");

}


	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
	    Log.i("userLocation", "connected!");

		Location mCurrentLocation;
	    mCurrentLocation = mLocationClient.getLastLocation();
	    try {
	    user_current_latitude = mCurrentLocation.getLatitude();
	    user_current_longitude = mCurrentLocation.getLongitude();
	    } catch (NullPointerException e) {
			// null because GPS not enabled
			Toast.makeText(CompanyMap.this, "location is not available, please try again later",Toast.LENGTH_LONG).show();
		    Log.i("userLocation", "null exception");
		}
	    Log.i("userLocation", "latitude: " + user_current_latitude);
	    Log.i("userLocation", "longitude: " + user_current_longitude);


	    Intent GPS = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(getShowDirectionsURL()));
		GPS.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
		startActivity(GPS);
		/*
		 * Check if user is connected to Internet
		 */
		if(hasInternetConnection() == false){ 
	    	Toast.makeText(CompanyMap.this, "You need Internet connection to show directions",Toast.LENGTH_LONG).show();
		}
	}


	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
	


}
