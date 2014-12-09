package com.os.operando.garum.serializers;

import java.util.Calendar;

public final class CalendarSerializer extends TypeSerializer<Calendar, Long> {
    public Class<Calendar> getDeserializedType() {
        return Calendar.class;
    }

    public Class<Long> getSerializedType() {
        return long.class;
    }

    public Long serialize(Calendar data) {
        return data == null ? null : data.getTimeInMillis();
    }

    public Calendar deserialize(Long data) {
        if (data == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data);
        return calendar;
    }
}
