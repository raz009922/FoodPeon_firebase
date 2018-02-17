package com.example.dc_raz.foodpeon_again.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dc_raz.foodpeon_again.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener {
    private int PLACE_PICKER_REQUEST = 1;
    private TextView tvPlaceDetails;
    String TAG = "why?";


    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    Button button;
    LatLng dhaka = new LatLng(23.777176, 90.399452);
    LatLng cBazar = new LatLng(21.427228, 92.005807);
    LatLng gulistan;
    LatLng tejgaon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        //button=(Button) findViewById(R.id.button);
        tvPlaceDetails = (TextView) findViewById(R.id.aaa);
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//        button.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View v) {
//
//
//
////                final double Distance = CalculationByDistance(gulistan,tejgaon);
////                button.setText(""+Distance+" KM");
//
//
//            }
//        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    protected void onStart() {

        //start the map with Android Place Picker API
        super.onStart();
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(MapsActivity.this), PLACE_PICKER_REQUEST);

            Log.d(TAG, "click");

        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
            Log.d(TAG, "not click");

        }
    }

    @Override
    public void onBackPressed() {
        //When User Press a Back button.. it creates a new of activity of this..the code goes here.we can do with it fragment too.
        super.onBackPressed();
        startActivity(new Intent(MapsActivity.this, MapsActivity.class));
        finish();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
//                Place place = PlacePicker.getPlace(data, this);
//                String toastMsg = String.format("Place: %s", place.getName());
//                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                Place place = PlacePicker.getPlace(data, this);

                StringBuilder stBuilder = new StringBuilder();
                String placename = String.format("%s", place.getName());
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                String address = String.format("%s", place.getAddress());
                stBuilder.append("Name: ");
                stBuilder.append(placename);
                stBuilder.append("\n");
                stBuilder.append("Latitude: ");
                stBuilder.append(latitude);
                stBuilder.append("\n");
                stBuilder.append("Logitude: ");
                stBuilder.append(longitude);
                stBuilder.append("\n");
                stBuilder.append("Address: ");
                stBuilder.append(address);
                //tvPlaceDetails.setText(stBuilder.toString());
                Toast.makeText(this, stBuilder.toString(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(MapsActivity.this, KitchenActivity.class));
                finish();

            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
//        int Radius = 6371;
//        double lat1 = StartP.latitude;
//        double lat2 = EndP.latitude;
//        double lon1 = StartP.longitude;
//        double lon2 = EndP.longitude;
//        double dLat = Math.toRadians(lat2 - lat1);
//        double dLon = Math.toRadians(lon2 - lon1);
//        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
//                + Math.cos(Math.toRadians(lat1))
//                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
//                * Math.sin(dLon / 2);
//        double c = 2 * Math.asin(Math.sqrt(a));
//        double valueResult = Radius * c;
//        double km = valueResult / 1;
//        DecimalFormat newFormat = new DecimalFormat("####");
//        int kmInDec = Integer.valueOf(newFormat.format(km));
//        double meter = valueResult % 1000;
//        int meterInDec = Integer.valueOf(newFormat.format(meter));
//        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
//                + " Meter   " + meterInDec);
//
//        return Radius * c;
//    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, connectionResult.getErrorMessage() + "", Toast.LENGTH_SHORT).show();

    }
}

