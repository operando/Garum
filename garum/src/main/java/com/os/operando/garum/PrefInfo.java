package com.os.operando.garum;

import android.text.TextUtils;

import com.os.operando.garum.annotations.Pref;
import com.os.operando.garum.annotations.PrefKey;
import com.os.operando.garum.models.PrefModel;
import com.os.operando.garum.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PrefInfo {

    private String prefName;
    private Map<Field, String> keyNames = new LinkedHashMap<>();

    public PrefInfo(Class<? extends PrefModel> type) {
        setPrefName(type);
        List<Field> fields = new LinkedList<>(ReflectionUtil.getDeclaredPrefKeyFields(type));
        for (Field field : fields) {
            putKeyName(field);
        }
    }

    public String getPrefName() {
        return prefName;
    }

    public Collection<Field> getKeys() {
        return keyNames.keySet();
    }

    public String getKeyName(Field field) {
        return keyNames.get(field);
    }

    void setPrefName(Class<? extends PrefModel> type) {
        final Pref prefAnnotation = type.getAnnotation(Pref.class);
        if (prefAnnotation != null) {
            prefName = prefAnnotation.name();
        } else {
            prefName = type.getSimpleName();
        }
    }

    void putKeyName(Field field) {
        if (field == null || !field.isAnnotationPresent(PrefKey.class)) {
            throw new IllegalArgumentException();
        }
        final PrefKey keyAnnotation = field.getAnnotation(PrefKey.class);
        String keyName = keyAnnotation.value();
        if (TextUtils.isEmpty(keyName)) {
            keyName = field.getName();
        }
        keyNames.put(field, keyName);
    }
}