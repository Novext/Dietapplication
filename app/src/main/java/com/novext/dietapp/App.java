package com.novext.dietapp;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class App extends Application {
    static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        app = this;
    }

    public static App getInstance(){
        return app;
    }
}
