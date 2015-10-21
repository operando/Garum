Change Log
==========

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