package com.couchbase.lite;

import com.couchbase.lite.listener.Credentials;
import com.couchbase.lite.listener.LiteListener;
import com.couchbase.lite.util.Log;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by hideki on 10/24/16.
 */
public class LiteServ {
    public static final String TAG = "LiteServ";
    private static final String DATABASE_NAME = "cblite-test";

    LiteListener listener = null;

    public LiteServ() {
        configureLogging();
    }

    public void runLiteServ(LiteServOptions liteServOpts) throws IOException, InterruptedException, CouchbaseLiteException {
        // initialize manager
        Manager manager = initManager(liteServOpts);
        // open default database
        Database database = openDatabase(manager, DATABASE_NAME, liteServOpts);
        // run listener
        runListener(manager, liteServOpts);
    }

    protected Manager initManager(LiteServOptions liteServOpts) throws IOException {
        Manager manager = new Manager(new LiteServJavaContext(liteServOpts.getDir()), new ManagerOptions());
        manager.setStorageType(liteServOpts.getStorage());
        if (liteServOpts.getDbpassword() != null && liteServOpts.getDbpassword().length() > 0) {
            String[] passwords = liteServOpts.getDbpassword().split(",");
            for (String password : passwords) {
                String[] items = password.split("=");
                manager.registerEncryptionKey(items[1], items[0]);
            }
        }
        return manager;
    }

    protected Database openDatabase(Manager manager, String databaseName, LiteServOptions liteServOpts)
            throws CouchbaseLiteException {
        DatabaseOptions options = new DatabaseOptions();
        options.setCreate(true);
        options.setStorageType(liteServOpts.getStorage());
        if (liteServOpts.getDbpassword() != null && liteServOpts.getDbpassword().length() > 0) {
            Map keys = manager.getEncryptionKeys();
            if (keys.containsKey(databaseName))
                options.setEncryptionKey(keys.get(databaseName));
        }
        Database database = manager.openDatabase(databaseName, options);
        database.open();
        return database;
    }

    protected void runListener(Manager manager, LiteServOptions liteServOpts) throws InterruptedException {
        listener = new LiteListener(manager, liteServOpts.getPort(), new Credentials(liteServOpts.getUser(), liteServOpts.getPassword()));
        int listenerPort = listener.getListenPort();
        Thread listenerThread = new Thread(listener);
        listenerThread.start();
        listenerThread.join();
    }

    public void stop() {
        if (listener != null)
            listener.stop();
    }

    public static void configureLogging() {
        Manager.enableLogging(TAG, Log.VERBOSE);
        Manager.enableLogging(Log.TAG, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_SYNC_ASYNC_TASK, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_SYNC, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_QUERY, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_VIEW, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_DATABASE, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_BATCHER, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_ROUTER, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_CHANGE_TRACKER, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_LISTENER, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_REMOTE_REQUEST, Log.VERBOSE);
    }


    public static void main(String[] args) throws IOException, InterruptedException, CouchbaseLiteException {
        LiteServOptions options = null;
        try {
            options = LiteServOptions.parse(args);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        if (options.isHelp()) {
            LiteServOptions.printUsage();
            return;
        }

        // parsing command arguments
        final LiteServ liteServ = new LiteServ();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                liteServ.stop();
            }
        });
        liteServ.runLiteServ(options);
    }
}
