package com.os.operando.garum;

import android.net.Uri;

import com.os.operando.garum.models.PrefModel;
import com.os.operando.garum.serializers.CalendarSerializer;
import com.os.operando.garum.serializers.DateSerializer;
import com.os.operando.garum.serializers.DoubleSerializer;
import com.os.operando.garum.serializers.FileSerializer;
import com.os.operando.garum.serializers.JSONArraySerializer;
import com.os.operando.garum.serializers.JSONObjectSerializer;
import com.os.operando.garum.serializers.TypeSerializer;
import com.os.operando.garum.serializers.UriSerializer;
import com.os.operando.garum.utils.GarumLog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelInfo {

    private Map<Class<? extends PrefModel>, PrefInfo> prefInfos = new HashMap<>();
    private Map<Class<?>, TypeSerializer> typeSerializers = new HashMap<Class<?>, TypeSerializer>() {
        {
            put(java.util.Date.class, new DateSerializer());
            put(Calendar.class, new CalendarSerializer());
            put(Uri.class, new UriSerializer());
            put(File.class, new FileSerializer());
            put(JSONObject.class, new JSONObjectSerializer());
            put(JSONArray.class, new JSONArraySerializer());
            put(Double.class, new DoubleSerializer());
        }
    };


    public ModelInfo(Configuration configuration) throws IOException {
        if (!loadModelConfiguration(configuration)) {
            // TODO:throw exception
        }
        GarumLog.i("ModelInfo loaded.");
    }

    public TypeSerializer getTypeSerializer(Class<?> type) {
        return typeSerializers.get(type);
    }

    public void setTypeSerializer(Class<? extends TypeSerializer> typeSerializer) {
        try {
            TypeSerializer instance = typeSerializer.newInstance();
            typeSerializers.put(instance.getDeserializedType(), instance);
        } catch (InstantiationException e) {
            GarumLog.e("Couldn't instantiate TypeSerializer.", e);
        } catch (IllegalAccessException e) {
            GarumLog.e("IllegalAccessException", e);
        }
    }

    private boolean loadModelConfiguration(Configuration configuration) {
        if (!configuration.isValid()) {
            return false;
        }

        final List<Class<? extends PrefModel>> models = configuration.getModelClasses();
        if (models != null) {
            for (Class<? extends PrefModel> model : models) {
                prefInfos.put(model, new PrefInfo(model));
            }
        }

        final List<Class<? extends TypeSerializer>> typeSerializerList = configuration.getTypeSerializers();
        if (typeSerializerList != null) {
            for (Class<? extends TypeSerializer> typeSerializer : typeSerializerList) {
                setTypeSerializer(typeSerializer);
            }
        }

        return true;
    }

    public Collection<PrefInfo> getPrefInfos() {
        return prefInfos.values();
    }

    public PrefInfo getPrefInfo(Class<? extends PrefModel> type) {
        return prefInfos.get(type);
    }
}
