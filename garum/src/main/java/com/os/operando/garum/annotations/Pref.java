package com.os.operando.garum.annotations;

import android.content.Context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pref {
    enum MODE {
        MODE_PRIVATE(Context.MODE_PRIVATE),
        MODE_MULTI_PROCESS(Context.MODE_MULTI_PROCESS);

        private final int mode;

        private MODE(final int mode) {
            this.mode = mode;
        }

        public int getMode() {
            return mode;
        }
    }

    String name();

    MODE mode() default MODE.MODE_PRIVATE;
}