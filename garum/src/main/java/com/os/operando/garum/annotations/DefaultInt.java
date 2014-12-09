package com.os.operando.garum.annotations;

import com.os.operando.garum.ResId;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DefaultInt {
    int value() default 0;

    int redId() default ResId.DEFAULT_VALUE;
}
