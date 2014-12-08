package com.os.operando.garum.serializers;

public abstract class TypeSerializer<D, S> {
    public abstract Class<D> getDeserializedType();

    public abstract Class<S> getSerializedType();

    public abstract S serialize(D data);

    public abstract D deserialize(S data);
}