package com.os.operando.garum.sample;

import android.app.Application;

import com.os.operando.garum.Garum;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Garum.initialize(getApplicationContext());

//        Configuration.Builder builder = new Configuration.Builder(getApplicationContext());
//        builder.setModelClasses(AppStatus.class, PrefModel.class, UseStatus.class);
//        Garum.initialize(getApplicationContext(), true);
    }
}