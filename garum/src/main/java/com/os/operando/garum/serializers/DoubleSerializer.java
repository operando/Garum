package com.os.operando.garum.serializers;


public final class DoubleSerializer extends TypeSerializer<Double, Long> {

    @Override
    public Class<Double> getDeserializedType() {
        return double.class;
    }

    @Override
    public Class<Long> getSerializedType() {
        return long.class;
    }

    @Override
    public Long serialize(Double data) {
        return Double.doubleToRawLongBits(data);
    }

    @Override
    public Double deserialize(Long data) {
        return Double.longBitsToDouble(data);
    }

    @Override
    public Double getDefaultValue() {
        return 0.0d;
    }
}