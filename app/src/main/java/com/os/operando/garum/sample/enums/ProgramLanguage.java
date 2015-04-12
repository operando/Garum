package com.os.operando.garum.sample.enums;

public enum ProgramLanguage {
    JAVA(0),
    PHP(1),
    RUBY(2),
    JAVASCRIPT(3);

    private final int id;

    ProgramLanguage(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ProgramLanguage valueOf(int id) {
        for (ProgramLanguage num : values()) {
            if (num.getId() == id) {
                return num;
            }
        }
        return null;
    }

}