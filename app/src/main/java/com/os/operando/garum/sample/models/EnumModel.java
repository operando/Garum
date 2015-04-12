package com.os.operando.garum.sample.models;

import com.os.operando.garum.annotations.Pref;
import com.os.operando.garum.annotations.PrefKey;
import com.os.operando.garum.models.PrefModel;
import com.os.operando.garum.sample.enums.ProgramLanguage;

import lombok.ToString;

@ToString
@Pref(name = "enum_model")
public class EnumModel extends PrefModel {

    @PrefKey
    public ProgramLanguage programLanguage;

}