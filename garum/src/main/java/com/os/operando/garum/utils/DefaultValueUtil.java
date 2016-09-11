package com.os.operando.garum.utils;

import android.content.res.Resources;

import com.os.operando.garum.ResId;
import com.os.operando.garum.annotations.DefaultBoolean;
import com.os.operando.garum.annotations.DefaultFloat;
import com.os.operando.garum.annotations.DefaultInt;
import com.os.operando.garum.annotations.DefaultLong;
import com.os.operando.garum.annotations.DefaultString;
import com.os.operando.garum.annotations.DefaultStringSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DefaultValueUtil {

    public static int getDefaultIntValue(DefaultInt defaultInt, Resources resources) {
        if (defaultInt == null) {
            return 0;
        }
        int resId = defaultInt.redId();
        if (isResIdDefault(resId)) {
            return defaultInt.value();
        }
        return resources.getInteger(resId);
    }

    public static String getDefaultStringValue(DefaultString defaultString, Resources resources) {
        if (defaultString == null) {
            return null;
        }
        int resId = defaultString.redId();
        if (isResIdDefault(resId)) {
            return defaultString.value();
        }
        return resources.getString(resId);
    }

    public static boolean getDefaultBooleanValue(DefaultBoolean defaultBoolean, Resources resources) {
        if (defaultBoolean == null) {
            return false;
        }
        int resId = defaultBoolean.redId();
        if (isResIdDefault(resId)) {
            return defaultBoolean.value();
        }
        return resources.getBoolean(resId);
    }

    public static long getDefaultLongValue(DefaultLong defaultLong) {
        if (defaultLong == null) {
            return 0;
        }
        return defaultLong.value();
    }

    public static float getDefaultFloatValue(DefaultFloat defaultFloat) {
        if (defaultFloat == null) {
            return 0.0f;
        }
        return defaultFloat.value();
    }

    public static Set<String> getDefaultSetValue(DefaultStringSet defaultStringSet, Resources resources) {
        if (defaultStringSet == null) {
            return new HashSet<>();
        }
        int resId = defaultStringSet.redId();
        if (isResIdDefault(resId)) {
            return new HashSet<>(Arrays.asList(defaultStringSet.value()));
        }
        return new HashSet<>(Arrays.asList(resources.getStringArray(resId)));
    }

    private static boolean isResIdDefault(int resId) {
        return resId == ResId.DEFAULT_VALUE;
    }
}
