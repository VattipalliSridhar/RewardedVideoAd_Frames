package com.simpleapps.rewardedvideoad_frames;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;



public class Myapplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

            try {
            Class.forName("android.os.AsyncTask");
        } catch(Throwable ignore) {
        }
        MobileAds.initialize(getApplicationContext(),getResources().getString(R.string.app_id));

    }
}
