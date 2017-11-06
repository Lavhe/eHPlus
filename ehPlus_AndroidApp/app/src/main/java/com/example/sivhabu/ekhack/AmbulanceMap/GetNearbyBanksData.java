package com.example.sivhabu.ekhack.AmbulanceMap;

import android.os.AsyncTask;
import android.util.Log;

import com.example.sivhabu.ekhack.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class GetNearbyBanksData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... params) {
        try {
            Log.d("GetNearbyBanksData", "doInBackground entered");
            mMap = (GoogleMap) params[0];
            url = (String) params[1];
            UrlConnection urlConnection = new UrlConnection();
            googlePlacesData = urlConnection.readUrl(url);
            Log.d("GooglePlacesReadTask", "doInBackground Exit");
        } catch (Exception e) {
            Log.d("GooglePlacesReadTask", e.toString());
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("GooglePlacesReadTask", "onPostExecute Entered");
        List<HashMap<String, String>> nearbyPlacesList = null;
        DataParser dataParser = new DataParser();
        nearbyPlacesList =  dataParser.parse(result);
        ShowNearbyPlaces(nearbyPlacesList);
        Log.d("GooglePlacesReadTask", "onPostExecute Exit");
    }

    private void ShowNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList) {

        MarkerOptions onMarker = new MarkerOptions();
        LatLng latL1 = new LatLng(-26.2395843,28.1355629);
        LatLng latL2 = new LatLng(-26.2401719,28.1334835);

        onMarker.position(latL1);

        onMarker.title(CalculationByDistance(latL1,latL2) + " KM away");
        //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        onMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_ambulance_2));
        onMarker.rotation(170);
        mMap.addMarker(onMarker);

        for (int i = 0; i < nearbyPlacesList.size(); i++) {
            Log.d("onPostExecute","Entered into showing locations");
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);

            LatLng latLng2 = new LatLng(-26.2643725,28.1233248);
            markerOptions.title(CalculationByDistance(latLng,latLng2) + " KM away");
            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_ambulance));
            markerOptions.rotation((int)(Math.random() * ((360 - 0) + 1) + 0));
            mMap.addMarker(markerOptions);

            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(9
            ));
            Log.i("BankGet", "sddsf");
        }
        Log.i("BankGet", "sddsf");
    }

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return kmInDec;
    }



}
