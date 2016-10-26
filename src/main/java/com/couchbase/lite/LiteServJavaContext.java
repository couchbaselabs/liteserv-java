package com.couchbase.lite;

import java.io.File;

/**
 * Created by hideki on 10/25/16.
 */
public class LiteServJavaContext extends JavaContext {
    private String rootDir = null;

    public LiteServJavaContext() {
        super();
    }

    public LiteServJavaContext(String rootDir) {
        super();
        this.rootDir = rootDir;
    }

    @Override
    public File getFilesDir() {
        if(rootDir != null)
            return new File(rootDir);
        else
            return super.getFilesDir();
    }
}
