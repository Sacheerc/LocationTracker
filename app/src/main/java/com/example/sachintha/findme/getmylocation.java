package com.example.sachintha.findme;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class getmylocation extends Activity implements LocationListener {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat,txt,distance;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protector);
        txtLat = (TextView) findViewById(R.id.textView3);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);

    }
    @Override
    public void onLocationChanged(Location location) {
        protector protector1=new protector();
        txt = (TextView) findViewById(R.id.textView2);
        try {
            protector1.getJSON("http://bagtracker.000webhostapp.com/getdata.php");
            txt.setText(protector1.ans);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        txtLat = (TextView) findViewById(R.id.textView3);
        distance=(TextView) findViewById(R.id.distance);
        txtLat.setText("Latitude: " + location.getLatitude() + "   Longitude: " + location.getLongitude());
        double distanceInst=distance(protector1.latnew,protector1.langnew,location.getLatitude(),location.getLongitude());
        distance.setText(Double.toString(distanceInst)+ " meters");
        if(distanceInst>50){
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(MainActivity.number,null,"Allert",null,null);
                Toast.makeText(getmylocation.this,"Sent..!",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(getmylocation.this,"Failed..!",Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getmylocation.this,home.class);
            startActivity(intent);
            locationManager.removeUpdates(this);
            locationManager = null;
            Toast.makeText(getmylocation.this,"Allert..!",Toast.LENGTH_SHORT).show();
        }
//        txtLat.setText("hello");
    }


    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return (dist*1609.34);
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    public static void main(String[] args) {
        System.out.println(distance(0,0,1,0));
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }
}
