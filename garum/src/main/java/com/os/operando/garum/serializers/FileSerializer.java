package com.os.operando.garum.serializers;

import java.io.File;

public final class FileSerializer extends TypeSerializer<File, String> {
    public Class<File> getDeserializedType() {
        return File.class;
    }

    public Class<String> getSerializedType() {
        return String.class;
    }

    public String serialize(File data) {
        if (data == null) {
            return null;
        }
        return data.toString();
    }

    public File deserialize(String data) {
        if (data == null) {
            return null;
        }
        return new File(data);
    }
}
