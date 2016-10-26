package com.couchbase.lite;

import org.apache.commons.cli.*;

/**
 * Created by hideki on 10/25/16.
 */
public class LiteServOptions {
    // constant variables
    public static final int DEFAULT_PORT = 59840;
    public static final String DEFAULT_STORAGE = "SQLite";
    public static final String DEFAULT_USER = "";
    public static final String DEFAULT_PASSWORD = "";

    // instance variables
    private boolean help = false;
    private String dir = null;
    private int port = DEFAULT_PORT;
    private String user = DEFAULT_USER;
    private String password = DEFAULT_PASSWORD;
    private String storage = DEFAULT_STORAGE;
    private String dbpassword = null;

    // not allow to instantiate
    private LiteServOptions() {
    }

    // parse command line argument, and return LiteServOptions
    public static LiteServOptions parse(String[] args) throws ParseException {
        LiteServOptions liteServOptions = new LiteServOptions();

        // parsing command arguments
        Options options = new Options();
        options.addOption("help", "help", false, "Print this message");
        options.addOption("dir", "dir", true, "Alternate directory to store databases in");
        options.addOption("port", "port", true, "Port to listen on (defaults to 59840)");
        options.addOption("user", "user", true, "Username for connecting to remote database");
        options.addOption("password", "password", true, "Password for connecting to remote database");
        options.addOption("storage", "storage", true, "Set default storage engine: 'SQLite' or 'ForestDB'");
        options.addOption("dbpassword", "dbpassword", true, "Register password to open a database");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption("help"))
            liteServOptions.help = true;
        if (cmd.hasOption("dir"))
            liteServOptions.dir = cmd.getOptionValue("dir");
        if (cmd.hasOption("port"))
            liteServOptions.port = Integer.parseInt(cmd.getOptionValue("port"));
        if (cmd.hasOption("user"))
            liteServOptions.user = cmd.getOptionValue("user");
        if (cmd.hasOption("password"))
            liteServOptions.password = cmd.getOptionValue("password");
        if (cmd.hasOption("storage"))
            liteServOptions.storage = cmd.getOptionValue("storage");
        if (cmd.hasOption("dbpassword"))
            liteServOptions.dbpassword = cmd.getOptionValue("dbpassword");
        return liteServOptions;
    }

    // print usage
    public static void printUsage() {
        System.out.println(
                "USAGE: LiteServ\n" +
                        "\t[--dir <databaseDir>]    Alternate directory to store databases in\n" +
                        "\t[--port <listeningPort>] Port to listen on (defaults to 59840)\n" +
                        //"\t[--readonly]             Enables read-only mode\n" +
                        //"\t[--auth]                 REST API requires HTTP auth\n" +
                        //"\t[--pull <URL>]           Pull from remote database\n" +
                        //"\t[--push <URL>]           Push to remote database\n" +
                        //"\t[--create-target]        Create replication target database\n" +
                        //"\t[--continuous]           Continuous replication\n" +
                        "\t[--user <username>]      Username for connecting to remote database\n" +
                        "\t[--password <password>]  Password for connecting to remote database\n" +
                        //"\t[--realm <realm>]        HTTP realm for connecting to remote database\n" +
                        //"\t[--revs_limit <n>        Sets default max rev-tree depth for databases\n" +
                        //"\t[--ssl]                  Serve over SSL\n" +
                        //"\t[--sslas <identityname>] Identity pref name to use for SSL serving\n" +
                        "\t[--storage <engine>]     Set default storage engine: 'SQLite' or 'ForestDB'\n" +
                        "\t[--dbpassword <dbname>=<password>]  Register password to open a database\n" +
                        "Runs Couchbase Lite as a faceless server.\n");
    }

    // getters for varialbes

    public boolean isHelp() {
        return help;
    }

    public String getDir() {
        return dir;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getStorage() {
        return storage;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    @Override
    public String toString() {
        return "LiteServOptions{" +
                "help=" + help +
                ", dir='" + dir + '\'' +
                ", port=" + port +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", storage='" + storage + '\'' +
                ", dbpassword='" + dbpassword + '\'' +
                '}';
    }
}
