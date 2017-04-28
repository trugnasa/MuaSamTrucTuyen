package com.project.luulinhson.muasamtructuyen.App;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Admin on 4/14/2017.
 */

public class App extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
