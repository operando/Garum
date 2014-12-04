package com.os.operando.garum.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.os.operando.garum.sample.models.AppStatus;
import com.os.operando.garum.sample.models.PrefTest;
import com.os.operando.garum.sample.models.UseStatus;

import java.util.Date;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new PrefTest().save();
        UseStatus s = new UseStatus();
        Date date = s.getLastUsed();
        if (date != null) {
            ((TextView) findViewById(R.id.last_used_date)).setText("last used  : " + date.toString());
        } else {
            ((TextView) findViewById(R.id.last_used_date)).setText("last used  : null");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UseStatus s = new UseStatus();
        s.setLastUsed(new Date());
        s.save();
    }

    public void onClick(View v) {
        AppStatus appStatus = new AppStatus();
        appStatus.startupCount = ++appStatus.startupCount;
        appStatus.save();
    }

    public void onSave(View v) {
        AppStatus appStatus = new AppStatus();
        Log.d(TAG, appStatus.toString());
        appStatus.appName = "Garum";
        appStatus.showNotification = true;
        appStatus.save();
    }
}
