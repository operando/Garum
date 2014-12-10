# Garum

Annotation style SharedPreferences.

![Garum](image_garum.jpg)

# How to use

## Model

```
@Pref(name = "app_status")
public class AppStatus extends PrefModel {

    @PrefKey
    public String appName;

    @PrefKey
    public int startupCount;

    @PrefKey
    public boolean showNotification;
}
```

## Save

```
AppStatus appStatus = new AppStatus();
appStatus.appName = "Garum";
appStatus.startupCount = ++appStatus.startupCount;
appStatus.showNotification = true;
appStatus.save();
```

## XML

/data/data/<package name>/shared_prefs/app_status.xml

```
<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <string name="appName">Garum</string>
    <int name="startupCount" value="10" />
    <boolean name="showNotification" value="true" />
</map>

```

## Documentation

Please refer to the Wiki.

* [Home](https://github.com/operando/Garum/wiki)
* [Introduction](https://github.com/operando/Garum/wiki/Introduction)
* [Creating your SharedPreferences model](https://github.com/operando/Garum/wiki/Creating-your-SharedPreferences-model)
* [@Pref Annotation](https://github.com/operando/Garum/wiki/@Pref-Annotation)

## Download

[Download the latest JAR](https://github.com/operando/Garum/blob/gh-pages/latest_jar/garum-0.0.2.jar?raw=true).

## License

[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

    Copyright (C) 2014 Shinobu Okano

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.