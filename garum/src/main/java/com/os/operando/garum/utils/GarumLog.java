package com.os.operando.garum.utils;

import android.util.Log;

import com.os.operando.garum.Garum;

public final class GarumLog {

    private static final String TGA = Garum.class.getSimpleName();
    private static boolean enabled = false;

    private GarumLog() {
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        GarumLog.enabled = enabled;
    }

    public static boolean isLoggingEnabled() {
        return enabled;
    }

    public static int v(String msg) {
        return enabled ? Log.v(TGA, msg) : 0;
    }

    public static int v(String tag, String msg) {
        return enabled ? android.util.Log.v(tag, msg) : 0;
    }

    public static int v(String msg, Throwable tr) {
        return enabled ? Log.v(TGA, msg, tr) : 0;
    }

    public static int v(String tag, String msg, Throwable tr) {
        return enabled ? android.util.Log.v(tag, msg, tr) : 0;
    }

    public static int d(String msg) {
        return enabled ? android.util.Log.d(TGA, msg) : 0;
    }

    public static int d(String tag, String msg) {
        return enabled ? android.util.Log.d(tag, msg) : 0;
    }

    public static int d(String msg, Throwable tr) {
        return enabled ? android.util.Log.d(TGA, msg, tr) : 0;
    }

    public static int d(String tag, String msg, Throwable tr) {
        return enabled ? android.util.Log.d(tag, msg, tr) : 0;
    }

    public static int i(String msg) {
        return enabled ? android.util.Log.i(TGA, msg) : 0;
    }

    public static int i(String tag, String msg) {
        return enabled ? android.util.Log.i(tag, msg) : 0;
    }

    public static int i(String msg, Throwable tr) {
        return enabled ? android.util.Log.i(TGA, msg, tr) : 0;
    }

    public static int i(String tag, String msg, Throwable tr) {
        return enabled ? android.util.Log.i(tag, msg, tr) : 0;
    }

    public static int w(String msg) {
        return enabled ? android.util.Log.w(TGA, msg) : 0;
    }

    public static int w(String tag, String msg) {
        return enabled ? android.util.Log.w(tag, msg) : 0;
    }

    public static int w(String msg, Throwable tr) {
        return enabled ? android.util.Log.w(TGA, msg, tr) : 0;
    }

    public static int w(String tag, String msg, Throwable tr) {
        return enabled ? android.util.Log.w(tag, msg, tr) : 0;
    }

    public static int e(String msg) {
        return enabled ? android.util.Log.e(TGA, msg) : 0;
    }

    public static int e(String tag, String msg) {
        return enabled ? android.util.Log.e(tag, msg) : 0;
    }

    public static int e(String msg, Throwable tr) {
        return enabled ? android.util.Log.e(TGA, msg, tr) : 0;
    }

    public static int e(String tag, String msg, Throwable tr) {
        return enabled ? android.util.Log.e(tag, msg, tr) : 0;
    }

    public static int t(String msg, Object... args) {
        return enabled ? android.util.Log.v("test", String.format(msg, args)) : 0;
    }
}
