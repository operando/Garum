package com.os.operando.garum.serializers;


import java.util.Date;

public final class DateSerializer extends TypeSerializer<Date, Long> {
    public Class<Date> getDeserializedType() {
        return Date.class;
    }

    public Class<Long> getSerializedType() {
        return long.class;
    }

    public Long serialize(Date data) {
        return data == null ? null : data.getTime();
    }

    public Date deserialize(Long data) {
        return data == null ? null : new Date(data);
    }
}