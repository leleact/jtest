package com.github.leleact.jtest.shardingsphere;

import lombok.SneakyThrows;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * basic test.
 *
 * @author leleact
 * @since 2024-08-11
 */
public class ShardingSphereBasicTests {

    @SneakyThrows
    @Test
    public void initShardingSphereTest() {
        Assertions.assertDoesNotThrow(() -> {
            ShardingSphereDataSourceFactory.createDataSource(new ModeConfiguration("Standalone", null));
        });
    }
}
