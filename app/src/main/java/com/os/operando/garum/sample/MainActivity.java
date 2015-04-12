package com.os.operando.garum.sample;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.os.operando.garum.sample.enums.ProgramLanguage;
import com.os.operando.garum.sample.models.AppStatus;
import com.os.operando.garum.sample.models.EnumModel;
import com.os.operando.garum.sample.models.PrefTest;
import com.os.operando.garum.sample.models.UseStatus;

import java.io.File;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PrefTest prefTest = new PrefTest();
        prefTest.setFile(new File("../test"));
        prefTest.setUri(Uri.parse("content://test/test"));
        prefTest.save();
        PrefTest prefTest2 = new PrefTest();
        Log.d("Garum", prefTest2.toString());
        Log.d("Garum", prefTest2.getFile().toString());
        UseStatus s = new UseStatus();
        Date date = s.getLastUsed();
        if (date != null) {
            ((TextView) findViewById(R.id.last_used_date)).setText("last used  : " + date.toString());
        } else {
            ((TextView) findViewById(R.id.last_used_date)).setText("last used  : null");
        }

        EnumModel em = new EnumModel();
        Log.d("Garum", em.toString());
        em.programLanguage = ProgramLanguage.RUBY;
        em.save();
        Log.d("Garum", em.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UseStatus s = new UseStatus();
        s.setLastUsed(new Date());
        s.setCalendar(Calendar.getInstance());
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
