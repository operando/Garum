package com.os.operando.garum.serializers;

import android.net.Uri;

public final class UriSerializer extends TypeSerializer<Uri, String> {
    public Class<Uri> getDeserializedType() {
        return Uri.class;
    }

    public Class<String> getSerializedType() {
        return String.class;
    }

    public String serialize(Uri data) {
        if (data == null) {
            return null;
        }
        return data.toString();
    }

    public Uri deserialize(String data) {
        if (data == null) {
            return null;
        }
        return Uri.parse(data);
    }
}
