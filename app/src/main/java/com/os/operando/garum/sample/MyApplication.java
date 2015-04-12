package com.os.operando.garum.sample;

import android.app.Application;

import com.os.operando.garum.Garum;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Garum.initialize(getApplicationContext(), true);

//        Configuration.Builder builder = new Configuration.Builder(getApplicationContext());
//        builder.setModelClasses(PrefTest.class, AppStatus.class, PrefModel.class, UseStatus.class, EnumModel.class);
//        builder.setTypeSerializers(ProgramLanguageSerializer.class);
//        Garum.initialize(builder.create(), true);
    }
}