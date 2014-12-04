package com.os.operando.garum.utils;

import android.content.Context;
import android.support.v4.util.LruCache;

import com.os.operando.garum.Configuration;
import com.os.operando.garum.ModelInfo;
import com.os.operando.garum.PrefInfo;
import com.os.operando.garum.models.PrefModel;
import com.os.operando.garum.serializers.TypeSerializer;

import java.io.IOException;
import java.util.Collection;

public final class Cache {

    public static final int DEFAULT_CACHE_SIZE = 1024;
    private static Context context;
    private static ModelInfo modelInfo;
    private static LruCache<String, PrefModel> entities;
    private static boolean isInitialized = false;

    private Cache() {
    }

    public static synchronized void initialize(Configuration configuration) {
        if (isInitialized) {
            GarumLog.v("Garum already initialized.");
            return;
        }
        context = configuration.getContext();
        try {
            modelInfo = new ModelInfo(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        entities = new LruCache<String, PrefModel>(DEFAULT_CACHE_SIZE);
        isInitialized = true;
        GarumLog.v("Garum initialized successfully.");
    }


    public static synchronized void dispose() {
        entities = null;
        modelInfo = null;
        isInitialized = false;
        GarumLog.v("Garum disposed. Call initialize to use library.");
    }

    public static boolean isInitialized() {
        return isInitialized;
    }

    public static Context getContext() {
        return context;
    }

    public static synchronized Collection<PrefInfo> getPrefInfos() {
        return modelInfo.getPrefInfos();
    }

    public static synchronized PrefInfo getPrefInfo(Class<? extends PrefModel> type) {
        return modelInfo.getPrefInfo(type);
    }

    public static synchronized String getPrefName(Class<? extends PrefModel> type) {
        return modelInfo.getPrefInfo(type).getPrefName();
    }

    public static synchronized TypeSerializer getParserForType(Class<?> type) {
        return modelInfo.getTypeSerializer(type);
    }
}
