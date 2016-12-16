# liteserv-java
LiteServ-java is a LiteServ app that runs on Linux

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

Notes
- all output goes to build directory

###Create zip file
1. `./gradlew zip`

Notes:
- all output goes to build directory
- there is a script file (run.sh) in zip file, you can customize it to set different parameters.

###Run with gradle
1. `./gradlew runApp`

##How to change default settings?
Before running the app, you can setup environment variables to set the app settings. The app settings consist of:

Name       | Default value| Description|
-----------|--------------|------------|
help       |              | print usage
dir        |<application dir> | directory store the database
port       |49850         |Listener port to listen on
user       |"" | Username for connecting to remote database
password   |"" | Password for connecting to remote database
storage    |SQLite        |Set default storage engine: 'SQLite' or 'ForestDB'
dbpasswords|Empty value   |Register passwords to open a database. Format: db1=pass1,db2=pass2,..

