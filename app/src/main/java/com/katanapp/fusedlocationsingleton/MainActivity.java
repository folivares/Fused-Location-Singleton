package com.katanapp.fusedlocationsingleton;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * author: Federico Olivares
 */
public class MainActivity extends AppCompatActivity {

    /***********************************************************************************************
     * properties
     **********************************************************************************************/
    private TextView mPositionContainer; // print location here

    /***********************************************************************************************
     * lifecycle
     **********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // get text view
        mPositionContainer = (TextView) findViewById(R.id.positionContainerTxt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // start location updates
        FusedLocationSingleton.getInstance().startLocationUpdates();
        // register observer for location updates
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(mLocationUpdated,
                new IntentFilter(Const.INTENT_FILTER_LOCATION_UPDATE));
    }

    @Override
    protected void onPause() {
        super.onPause();
        // stop location updates
        FusedLocationSingleton.getInstance().stopLocationUpdates();
        // unregister observer
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(mLocationUpdated);
    }

    /***********************************************************************************************
     * local broadcast receiver
     **********************************************************************************************/
    /**
     * handle new location
     */
    private BroadcastReceiver mLocationUpdated = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                Location location = (Location) intent.getParcelableExtra(Const.LBM_EVENT_LOCATION_UPDATE);
                mPositionContainer.setText("Lat: " + location.getLatitude()
                        + " Lon: " + location.getLongitude());
            } catch (Exception e) {
                //
            }
        }
    };

}