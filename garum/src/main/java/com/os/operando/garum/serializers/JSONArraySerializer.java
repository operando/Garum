package com.os.operando.garum.serializers;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONArraySerializer extends TypeSerializer<JSONArray, String> {
    public Class<JSONArray> getDeserializedType() {
        return JSONArray.class;
    }

    public Class<String> getSerializedType() {
        return String.class;
    }

    public String serialize(JSONArray data) {
        return data == null ? null : data.toString();
    }

    public JSONArray deserialize(String data) {
        if (data == null) {
            return new JSONArray();
        }
        try {
            return new JSONArray(data);
        } catch (JSONException e) {
            return new JSONArray();
        }
    }
}