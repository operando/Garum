package com.os.operando.garum.sample.models;


import android.net.Uri;

import com.os.operando.garum.annotations.DefaultBoolean;
import com.os.operando.garum.annotations.DefaultInt;
import com.os.operando.garum.annotations.DefaultString;
import com.os.operando.garum.annotations.DefaultStringSet;
import com.os.operando.garum.annotations.Pref;
import com.os.operando.garum.annotations.PrefKey;
import com.os.operando.garum.models.PrefModel;
import com.os.operando.garum.sample.R;

import java.io.File;
import java.util.Set;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Pref(name = "pref_test", mode = Pref.MODE.MODE_PRIVATE)
public class PrefTest extends PrefModel {

    @PrefKey
    @DefaultString("test")
    private String str;

    @PrefKey
    @DefaultInt(11111)
    private int intValue;

    @PrefKey
    private Integer integerValue;

    @PrefKey
    @DefaultBoolean(true)
    private boolean boolValue;

    // DefaultValue < redId < Save Value
    @PrefKey
    @DefaultString(value = "test", redId = R.string.hello_world)
    private String strRes;

    @PrefKey
    @DefaultStringSet(value = {"1", "2", "3"}, redId = R.array.test_string_array)
    private Set<String> set;

    @PrefKey
    private File file;

    @PrefKey
    private Uri uri;
}
