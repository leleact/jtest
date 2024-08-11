package com.github.leleact.jtest.shardingsphere;

import ch.vorburger.mariadb4j.DB;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * MySQL extend for junit5.
 *
 * @author leleact
 * @since 2024-08-10
 */
@Slf4j
public class Junit5Extend4MySQL implements Extension, BeforeAllCallback, AfterAllCallback {
    private DB db = null;

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        if (db != null) {
            db.stop();
            log.info("db instance stop");
        }
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        db = DB.newEmbeddedDB(0);
        db.start();
        log.info("db instance start");
    }

    public int getPort() {
        if (db != null) {
            return db.getConfiguration().getPort();
        } else {
            return 0;
        }
    }
}
