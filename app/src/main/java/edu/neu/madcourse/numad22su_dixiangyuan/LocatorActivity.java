package edu.neu.madcourse.numad22su_dixiangyuan;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;

public class LocatorActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private TextView locatorTV;
    private TextView distanceTV;
    private Boolean runSwitch = true;
    private float distance;
    private Location savedLocation = null;
    private int precision = 2;


    private Handler textHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locator_activity);
        locatorTV = findViewById(R.id.locatorTV);
        distanceTV = findViewById(R.id.distanceTV);
        distance = 0;
        runThread();
    }

    public void getTheLocation() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        boolean IsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!IsEnabled) {
            locatorTV.setText("Please enable the right to access your location.");
        } else {
            Log.e("12", "8888888");
            getUserLocation();
        }
    }

    private void getUserLocation() {
        Log.e("12", "111111");
        if ((ActivityCompat.checkSelfPermission(LocatorActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(LocatorActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            Log.e("12", "999999");
            locatorTV.setText("Locator cannot work since location accessing is denied");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            Log.e("12", "2222222");
            Location currentNetworkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location currentPassiveLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            if (currentNetworkLocation != null) {
                Log.e("12", "44444444");
                if (savedLocation != null) {
                    distance += currentNetworkLocation.distanceTo(savedLocation);
                }
                savedLocation = currentNetworkLocation;
            } else if (currentPassiveLocation != null) {
                Log.e("12", "555555555");
                if (savedLocation != null) {
                    distance += currentPassiveLocation.distanceTo(savedLocation);
                }
                savedLocation = currentPassiveLocation;
            } else {
                Log.e("12", "6666666");
                Snackbar.make(locatorTV, "Finding your location...\nPlease wait...",
                        Snackbar.LENGTH_LONG).setAction("Action", null).show();
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        0, 0, v -> {
                });
            }
        }
    }

    public void runThread() {
        LocatorActivity.PrimeThread runIt = new LocatorActivity.PrimeThread();
        runIt.start();
    }

    class PrimeThread extends Thread {
        @Override
        public void run() {
            while (runSwitch) {
                getTheLocation();
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        distanceTV.setText(String.valueOf(distance));
                        locatorTV.setText(String.format(String.format("Latitude: %%.%df\nLongitude: %%.%df",
                                        precision, precision),
                                savedLocation.getLatitude(), savedLocation.getLongitude()));
                    }
                });
                try{
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void reset(View v) {
        distance = 0;
    }

    public void increasePre(View v) {
        Log.e("12", "in");
        if(precision<7) {
            precision++;
            refresh();
        } else {
            Snackbar.make(v, "Precision now is the highest.",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    public void decreasePre(View v) {
        if(precision>0) {
            precision--;
            refresh();
        } else {
            Snackbar.make(v, "Precision now is the lowest.",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    public void refresh() {
        getTheLocation();
        distanceTV.setText(String.valueOf(distance));
    }

}
