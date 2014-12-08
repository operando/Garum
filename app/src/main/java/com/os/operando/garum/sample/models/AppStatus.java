package com.os.operando.garum.sample.models;

import com.os.operando.garum.annotations.Pref;
import com.os.operando.garum.annotations.PrefKey;
import com.os.operando.garum.models.PrefModel;

import lombok.ToString;

@ToString
@Pref(name = "app_status")
public class AppStatus extends PrefModel {

    @PrefKey
    public String appName;

    @PrefKey
    public int startupCount;

    @PrefKey
    public boolean showNotification;
}
