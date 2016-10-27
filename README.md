# liteserv-java
LiteServ-java is a LiteServ app that runs on iOS or tvOS devices.

##How to build and run?

###Requirement
- Java 8

###Get the code
 ```
 $git clone https://github.com/couchbaselabs/liteserv-java.git
 ```
 
###Build with gradle
1. `cd liteserv-java`
2. `./gradlew build`

Note: all output goes to build directory

###Create zip file
1. `./gradlew zip`

Note: all output goes to build directory
Note: script file (run.sh) in zip file, you can customize it to set different parameters.

###Run with gradle
1. `./gradlew runApp`

##How to change default settings?
Before running the app, you can setup environment variables to set the app settings. The app settings consist of:

Name       | Default value| Description|
-----------|--------------|------------|
port       |49850         |Listener port to listen on
storage    |SQLite        |Set default storage engine: 'SQLite' or 'ForestDB'
dbpasswords|Empty value   |Register passwords to open a database. Format: db1=pass1,db2=pass2,..

