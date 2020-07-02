package com.qc.ms.config.sharding;

import com.qc.ms.config.sharding.config.MS_ShardingTablesConfiguration;
import com.qc.ms.config.sharding.config.ShardingType;
import com.qc.ms.config.sharding.config.other.MasterSlaveConfiguration;
import com.qc.ms.config.sharding.config.other.ShardingDatabasesAndTablesConfigurationPrecise;
import com.qc.ms.config.sharding.config.other.ShardingDatabasesConfigurationPrecise;
import com.qc.ms.config.sharding.config.other.ShardingMasterSlaveConfigurationPrecise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * quincey
 * Date 2020/7/2 19:55
 */

@Configuration
public class DataSourceFactory {// 数据源工厂配置
    Logger logger = LoggerFactory.getLogger(DataSourceFactory.class);

    @Resource
    Environment environment;

    public DataSource newInstance(final ShardingType shardingType) throws SQLException {
        switch (shardingType) {// 到底采用哪种策略
            case SHARDING_DATABASES:// 分库
                return new ShardingDatabasesConfigurationPrecise().getDataSource();
            case SHARDING_TABLES:// 分表
                return new MS_ShardingTablesConfiguration(getMysqlIpPortDbName(), environment).getDataSource();
            case SHARDING_DATABASES_AND_TABLES:// 分库分表
                return new ShardingDatabasesAndTablesConfigurationPrecise().getDataSource();
            case MASTER_SLAVE:// 主从
                return new MasterSlaveConfiguration().getDataSource();
            case SHARDING_MASTER_SLAVE:// 读写分离
                return new ShardingMasterSlaveConfigurationPrecise().getDataSource();
            default:
                throw new UnsupportedOperationException(shardingType.name());
        }
    }

    @Bean
// 配置数据源
    DataSource dataSource() throws SQLException {
        return newInstance(ShardingType.SHARDING_TABLES);// 传一个枚举
    }

    private String getMysqlIpPortDbName() {
        String dbUrl = "120.25.223.121/sharding_test_db";// 默认使用开发环境
        // 按道理来说应该  这里应该跟环境来得到正确的数据库

        return dbUrl;
    }
}
