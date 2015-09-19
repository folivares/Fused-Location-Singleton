package com.katanapp.fusedlocationsingleton;

import android.app.Application;
import android.content.Context;

/**
 * author: Federico Olivares
 */
public class FusedLocationSingletonAPP extends Application{

    /***********************************************************************************************
     * properties
     **********************************************************************************************/
    private static FusedLocationSingletonAPP mInstanceApplication;
    private static Context mContextApplication;

    /***********************************************************************************************
     * methods
     **********************************************************************************************/

    public void onCreate(){
        super.onCreate();
        // set app context
        mContextApplication = getApplicationContext();
    }

    /**
     * retrieve application context
     * @return Context
     */
    public static Context getAppContext(){
        return mContextApplication;
    }

}