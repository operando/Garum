package com.os.operando.garum.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;

import com.os.operando.garum.PrefInfo;
import com.os.operando.garum.annotations.DefaultBoolean;
import com.os.operando.garum.annotations.DefaultFloat;
import com.os.operando.garum.annotations.DefaultInt;
import com.os.operando.garum.annotations.DefaultLong;
import com.os.operando.garum.annotations.DefaultString;
import com.os.operando.garum.annotations.DefaultStringSet;
import com.os.operando.garum.serializers.TypeSerializer;
import com.os.operando.garum.utils.Cache;
import com.os.operando.garum.utils.GarumLog;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PrefModel {

    private final PrefInfo prefInfo;

    private SharedPreferences.Editor editor;

    public PrefModel() {
        prefInfo = Cache.getPrefInfo(getClass());
        load();
    }

    public void apply() {
        setValuesEditor();
        editor.apply();
    }

    public boolean save() {
        setValuesEditor();
        boolean success = editor.commit();
        return success;
    }

    public boolean clear() {
        boolean success = editor.clear().commit();
        if (success) {
            load();
        }
        return success;
    }

    private void load() {
        Context context = Cache.getContext();
        Resources resources = context.getResources();
        SharedPreferences sp = Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB ?
                context.getSharedPreferences(prefInfo.getPrefName(), Context.MODE_PRIVATE)
                : context.getSharedPreferences(prefInfo.getPrefName(), prefInfo.getMode().getMode());
        editor = sp.edit();
        Map<String, ?> all = sp.getAll();
        for (Field field : prefInfo.getKeys()) {
            final String KeyName = prefInfo.getKeyName(field);
            Class<?> fieldType = field.getType();
            Object value = all.get(KeyName);
            field.setAccessible(true);
            try {
                if (value != null) {
                    final TypeSerializer typeSerializer = Cache.getParserForType(fieldType);
                    if (typeSerializer != null && (value = typeSerializer.deserialize(value)) != null) {
                        fieldType = value.getClass();
                        if (!fieldType.equals(typeSerializer.getSerializedType())) {
                            GarumLog.w(String.format("TypeSerializer returned wrong type: expected a %s but got a %s",
                                    typeSerializer.getSerializedType(), fieldType));
                        }
                    }
                    field.set(this, value);
                } else {
                    if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                        final DefaultInt defaultInt = field.getAnnotation(DefaultInt.class);
                        if (defaultInt == null) {
                            field.set(this, 0);
                        } else {
                            int resId = defaultInt.redId();
                            if (resId == -1) {
                                field.set(this, defaultInt.value());
                            } else {
                                field.set(this, resources.getInteger(resId));
                            }
                        }
                    } else if (fieldType.equals(String.class)) {
                        final DefaultString defaultString = field.getAnnotation(DefaultString.class);
                        if (defaultString == null) {
                            field.set(this, null);
                        } else {
                            int resId = defaultString.redId();
                            if (resId == -1) {
                                field.set(this, defaultString.value());
                            } else {
                                field.set(this, context.getString(resId));
                            }
                        }
                    } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                        final DefaultBoolean defaultBoolean = field.getAnnotation(DefaultBoolean.class);
                        if (defaultBoolean == null) {
                            field.set(this, false);
                        } else {
                            int resId = defaultBoolean.redId();
                            if (resId == -1) {
                                field.set(this, defaultBoolean.value());
                            } else {
                                field.set(this, resources.getBoolean(resId));
                            }
                        }
                    } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                        final DefaultLong defaultLong = field.getAnnotation(DefaultLong.class);
                        if (defaultLong == null) {
                            field.set(this, 0);
                        } else {
                            field.set(this, defaultLong.value());
                        }
                    } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                        final DefaultFloat defaultFloat = field.getAnnotation(DefaultFloat.class);
                        if (defaultFloat == null) {
                            field.set(this, 0.0F);
                        } else {
                            field.set(this, defaultFloat.value());
                        }
                    } else if (fieldType.equals(Set.class) && field.getGenericType() instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                        if (Arrays.asList(parameterizedType.getActualTypeArguments()).contains(String.class)) {
                            final DefaultStringSet defaultStringSet = field.getAnnotation(DefaultStringSet.class);
                            if (defaultStringSet == null) {
                                field.set(this, new HashSet<String>());
                            } else {
                                int resId = defaultStringSet.redId();
                                if (resId == -1) {
                                    field.set(this, new HashSet<String>(Arrays.asList(defaultStringSet.value())));
                                } else {
                                    field.set(this, new HashSet<String>(
                                            Arrays.asList(resources.getStringArray(resId))));
                                }
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private void setValuesEditor() {
        for (Field field : prefInfo.getKeys()) {
            final String keyName = prefInfo.getKeyName(field);
            Class<?> fieldType = field.getType();
            field.setAccessible(true);
            try {
                Object value = field.get(this);

                // can not be cast to the null except String class.
                if (value == null && !fieldType.equals(String.class)) {
                    continue;
                }

                final TypeSerializer typeSerializer = Cache.getParserForType(fieldType);
                if (typeSerializer != null && (value = typeSerializer.serialize(value)) != null) {
                    fieldType = value.getClass();
                    if (!fieldType.equals(typeSerializer.getSerializedType())) {
                        GarumLog.w(String.format("TypeSerializer returned wrong type: expected a %s but got a %s",
                                typeSerializer.getSerializedType(), fieldType));
                    }
                }
                if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                    editor.putInt(keyName, (Integer) value);
                } else if (fieldType.equals(String.class)) {
                    editor.putString(keyName, (String) value);
                } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                    editor.putBoolean(keyName, (Boolean) value);
                } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                    editor.putLong(keyName, (Long) value);
                } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                    editor.putFloat(keyName, (Float) value);
                } else if (fieldType.equals(Set.class) && field.getGenericType() instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                    if (Arrays.asList(parameterizedType.getActualTypeArguments()).contains(String.class)) {
                        editor.putStringSet(keyName, (Set<String>) value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
