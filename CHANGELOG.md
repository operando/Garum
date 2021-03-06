Change Log
==========

## Version 0.9.0 *(2016-09-14)*

* Group attribute of dependency has been changed to **com.os.operando.garum**.
* New Serializer
 * [DoubleSerializer](https://github.com/operando/Garum/blob/master/garum/src/main/java/com/os/operando/garum/serializers/DoubleSerializer.java)
* Custom Type Serializer is able to set the default value


## Version 0.1.1 *(2016-04-09)*

* Fix bug
 * Not clear Type Serializers field after PrefModel#clear method execution  [#7](https://github.com/operando/Garum/issues/7)

## Version 0.1.0 *(2015-10-22)*

* Fix bug
 * Fix crash when no typeSerealizers are set. [#3](https://github.com/operando/Garum/pull/3)
* Unsupported Pref of MODE
 * Unsupported Context.MODE_MULTI_PROCESS of SharedPreferences Mode
* New Serializers
 * [JSONObjectSerializer](https://github.com/operando/Garum/blob/master/garum/src/main/java/com/os/operando/garum/serializers/JSONObjectSerializer.java)
 * [JSONArraySerializer](https://github.com/operando/Garum/blob/master/garum/src/main/java/com/os/operando/garum/serializers/JSONArraySerializer.java)


## Version 0.0.3 *(2015-04-12)*

* Fix bug
 * Auto class load if not loaded TypeSerializer


## Version 0.0.2 *(2014-12-10)*

* New Serializers
 * CalendarSerializer
 * FileSerializer
 * UriSerializer
* Executing the getDefaultSharedPreferences if name is empty
* All change default value annotations

## Version 0.0.1 *(2014-12-04)*

Initial release.
