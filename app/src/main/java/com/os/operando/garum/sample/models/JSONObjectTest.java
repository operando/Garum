package com.os.operando.garum.sample.models;

import com.os.operando.garum.annotations.Pref;
import com.os.operando.garum.annotations.PrefKey;
import com.os.operando.garum.models.PrefModel;

import org.json.JSONObject;

import lombok.ToString;

@ToString
@Pref(name = "json_object_test")
public class JSONObjectTest extends PrefModel {

    @PrefKey
    public JSONObject jsonObject;
}