package com.os.operando.garum;

import android.content.Context;

import com.os.operando.garum.utils.Cache;
import com.os.operando.garum.utils.GarumLog;

public class Garum {

    public static void initialize(Context context) {
        initialize(new Configuration.Builder(context).create(), false);
    }

    public static void initialize(Context context, boolean loggingEnabled) {
        initialize(new Configuration.Builder(context).create(), loggingEnabled);
    }

    public static void initialize(Configuration configuration) {
        initialize(configuration, false);
    }

    public static void initialize(Configuration configuration, boolean loggingEnabled) {
        setLoggingEnabled(loggingEnabled);
        Cache.initialize(configuration);
    }

    public static void setLoggingEnabled(boolean enabled) {
        GarumLog.setEnabled(enabled);
    }

}
