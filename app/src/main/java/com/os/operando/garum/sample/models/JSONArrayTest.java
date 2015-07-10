package com.os.operando.garum.sample.models;

import com.os.operando.garum.annotations.Pref;
import com.os.operando.garum.annotations.PrefKey;
import com.os.operando.garum.models.PrefModel;

import org.json.JSONArray;

import lombok.ToString;

@ToString
@Pref(name = "json_array_test")
public class JSONArrayTest extends PrefModel {

    @PrefKey
    public JSONArray jsonArray;
}
