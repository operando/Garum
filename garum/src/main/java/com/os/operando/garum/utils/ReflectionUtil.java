package com.os.operando.garum.utils;

import com.os.operando.garum.annotations.PrefKey;
import com.os.operando.garum.models.PrefModel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public final class ReflectionUtil {

    private ReflectionUtil() {
    }

    public static Set<Field> getDeclaredPrefKeyFields(Class<?> type) {
        Set<Field> declaredPrefKeyFields = Collections.emptySet();
        if (ReflectionUtil.isSubclassOf(type, PrefModel.class) || PrefModel.class.equals(type)) {
            declaredPrefKeyFields = new LinkedHashSet<Field>();
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(PrefKey.class)) {
                    declaredPrefKeyFields.add(field);
                }
            }
            Class<?> parentType = type.getSuperclass();
            if (parentType != null) {
                declaredPrefKeyFields.addAll(getDeclaredPrefKeyFields(parentType));
            }
        }
        return declaredPrefKeyFields;
    }

    public static boolean isModel(Class<?> type) {
        return isSubclassOf(type, PrefModel.class) && (!Modifier.isAbstract(type.getModifiers()));
    }

    public static boolean isSubclassOf(Class<?> type, Class<?> superClass) {
        if (type.getSuperclass() != null) {
            if (type.getSuperclass().equals(superClass)) {
                return true;
            }
            return isSubclassOf(type.getSuperclass(), superClass);
        }
        return false;
    }
}
