package com.os.operando.garum.serializers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectSerializer extends TypeSerializer<JSONObject, String> {
    public Class<JSONObject> getDeserializedType() {
        return JSONObject.class;
    }

    public Class<String> getSerializedType() {
        return String.class;
    }

    public String serialize(JSONObject data) {
        return data == null ? null : data.toString();
    }

    public JSONObject deserialize(String data) {
        if (data == null) {
            return new JSONObject();
        }
        try {
            return new JSONObject(data);
        } catch (JSONException e) {
            return new JSONObject();
        }
    }
}