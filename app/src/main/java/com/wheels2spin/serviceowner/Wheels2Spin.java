package com.wheels2spin.serviceowner;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ui.parse.Address;
import com.wheels2spin.serviceowner.parse.Bike;
import com.wheels2spin.serviceowner.parse.Rent;

/**
 * Created by Abhinav Goel on 3/29/2015.
 */
public class Wheels2Spin extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Bike.class);
        ParseObject.registerSubclass(Rent.class);
        ParseObject.registerSubclass(Address.class);
        Parse.enableLocalDatastore(this);
        // Required - Initialize the Parse SDK
        Parse.initialize(this, getString(R.string.parse_app_id),
                getString(R.string.parse_client_key));

        
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

    }
}
