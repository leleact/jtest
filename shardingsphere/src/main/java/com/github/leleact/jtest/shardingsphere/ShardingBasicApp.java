package com.github.leleact.jtest.shardingsphere;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.MergeStatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;

import org.apache.shardingsphere.infra.algorithm.core.config.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.infra.config.mode.PersistRepositoryConfiguration;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.mode.repository.standalone.StandalonePersistRepositoryConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

/**
 * basic.
 *
 * @author leleact
 * @since 2024-08-10
 */
public class ShardingBasicApp {
    private static final String schemaName = "shardingsphere_db";

    public static void main(String[] args) throws Exception {
        ShardingBasicApp app = new ShardingBasicApp();
        ModeConfiguration modeConfig = app.getModeConfig();
        Map<String, DataSource> dataSourceMap = app.getDataSourceMap();
        Collection<RuleConfiguration> ruleConfigs = app.getRuleConfigs();
        Properties props = new Properties();
        props.put("sql.show", "true");
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(schemaName,
            modeConfig,
            dataSourceMap,
            ruleConfigs,
            props);
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("select * from t_order where user_id = ? and order_id=?")) {
                stmt.setString(1, "abc");
                stmt.setString(2, "efg");
                stmt.execute();
                stmt.getResultSet();
            }
        }
    }

    private ModeConfiguration getModeConfig() {
        Properties props = new Properties();
        PersistRepositoryConfiguration configuration = new StandalonePersistRepositoryConfiguration("JDBC", props);
        return new ModeConfiguration("Standalone", configuration);
    }

    public Map<String, DataSource> getDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        {
            DruidDataSource ds = createDataSource("sharding_db0");
            dataSourceMap.put("ds0", ds);
        }
        {
            DruidDataSource ds = createDataSource("sharding_db1");
            dataSourceMap.put("ds1", ds);
        }
        {
            DruidDataSource ds = createDataSource("sharding_db2");
            dataSourceMap.put("ds2", ds);
        }
        {
            DruidDataSource ds = createDataSource("sharding_db3");
            dataSourceMap.put("ds3", ds);
        }
        return dataSourceMap;
    }

    @SneakyThrows
    private DruidDataSource createDataSource(String dbName) {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl("jdbc:mysql://10.0.1.3:3306" + "/" + dbName + "?createDatabaseIfNotExist=true");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setInitialSize(5);
        ds.setMinIdle(5);
        ds.setMaxActive(20);
        ds.setMaxWait(5000);
        ds.setTimeBetweenEvictionRunsMillis(1000);
        ds.setMinEvictableIdleTimeMillis(60000);
        ds.setKeepAlive(true);
        ds.setValidationQuery("SELECT 1");
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);
        ds.setFilters("stat,slf4j");
        ds.setProxyFilters(getFilter());
        ds.init();
        createTables(ds);
        return ds;
    }

    @SneakyThrows
    private void createTables(DataSource ds) {
        URL url = this.getClass().getClassLoader().getResource("META-INF/sql/init.sql");
        assert url != null;
        String initSql = IOUtils.toString(url, StandardCharsets.UTF_8);
        String[] sqlArr = initSql.split(";");
        try (Connection conn = ds.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                for (String s : sqlArr) {
                    if (StringUtils.isNotBlank(s)) {
                        stmt.execute(s);
                    }
                }
            }
        }
    }

    private Collection<RuleConfiguration> getRuleConfigs() {
        Collection<RuleConfiguration> ruleConfigs = new ArrayList<>();

        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("t_order",
            "ds${0..3}.t_order_${0..1}");

        orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id",
            "dbShardingAlgorithm"));

        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id",
            "tableShardingAlgorithm"));

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(orderTableRuleConfig);

        ruleConfigs.add(shardingRuleConfig);

        Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds$->{user_id.hashCode() % 4}");
        shardingRuleConfig.getShardingAlgorithms()
                          .put("dbShardingAlgorithm", new AlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

        Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order_$->{order_id.hashCode() % 2}");
        shardingRuleConfig.getShardingAlgorithms()
                          .put("tableShardingAlgorithm",
                              new AlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

        return ruleConfigs;
    }

    private List<Filter> getFilter() {
        List<Filter> filters = new ArrayList<>();
        {
            MergeStatFilter f = new MergeStatFilter();
            f.setSlowSqlMillis(3000);
            f.setLogSlowSql(true);
            f.setMergeSql(true);
        }
        return filters;
    }
}
