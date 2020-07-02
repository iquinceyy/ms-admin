/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qc.ms.config.sharding.config;

import com.qc.ms.config.sharding.algorithm.Create_Time_Range_ShardingTableAlgorithm;
import com.qc.ms.config.sharding.algorithm.Create_Time_ShardingTableAlgorithm;
import com.qc.ms.config.sharding.algorithm.PreciseModuloShardingTableAlgorithm;
import com.qc.ms.config.sharding.algorithm.RangeModuloShardingTableAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 只是分表用
 * 飞车分库分表配置文件
 * 森林杜
 */
@Slf4j
public final class MS_ShardingTablesConfiguration implements DataSourceConfiguration {
    private static String workId;
    private String SNOWFLAKE = "SNOWFLAKE";// 雪花主键生成器
    // 创建一个精确分片算法（取模精确分片算法）
    private PreciseShardingAlgorithm preciseShardingAlgorithm = new PreciseModuloShardingTableAlgorithm();// 创建一个取模的精确分片算法
    private RangeShardingAlgorithm rangeShardingAlgorithm = new RangeModuloShardingTableAlgorithm();// 创建一个范围分片算法


    private PreciseShardingAlgorithm create_time_shardingtablealgorithm = new Create_Time_ShardingTableAlgorithm();// 创建一个时间分片算法
    private RangeShardingAlgorithm create_time_range_shardingtablealgorithm = new Create_Time_Range_ShardingTableAlgorithm();// 创建一个时间范围算法


    private String ip_port_db;// mysql的 ip:端口/数据库
    private Environment environment;
    private String showSql = "true";// 指的是是否开启sql语句打印。默认是不开启的


    public MS_ShardingTablesConfiguration(String ip_port_db, Environment environment) {
        this.ip_port_db = ip_port_db;
        this.environment = environment;
        showSql = "true";// 默认使用开发环境
    }

    @Override
    public DataSource getDataSource() throws SQLException {// 获取数据源

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.setDefaultKeyGeneratorConfig(new KeyGeneratorConfiguration(SNOWFLAKE, "user_id", getProperties()));
        //  *****************************开始**********************************
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());// 用户表
        //  *******************************结束*********************************************
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", preciseShardingAlgorithm, rangeShardingAlgorithm));// 设置 默认user_id分片
        Properties properties = new Properties();
        properties.setProperty("sql.show", showSql);// dev开启sql打印,pro关闭
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, properties);
    }


    // 私有的方法，获取用户表的分片配置规则的方法
    private TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("user", "sharding_test_db.user_copy${1..5}");
        result.setKeyGeneratorConfig(new KeyGeneratorConfiguration(SNOWFLAKE, "user_id", getProperties()));// 设置主键生成策略
        return result;
    }


    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("sharding_test_db", DataSourceUtil.createDataSource(ip_port_db));// 创建一个数据源
        return result;
    }

    private Properties getProperties() {
        Properties result = new Properties();// 创建一个属性文件
        result.setProperty("worker.id", workId);// 设置一个工作id
        result.setProperty("max.vibration.offset", "63");// 还需要给他一个序列
        return result;
    }

    /**
     * 根据机器IP获取工作进程Id,如果线上机器的IP二进制表示的最后10位不重复,建议使用此种方式
     * ,列如机器的IP为192.168.1.108,二进制表示:11000000 10101000 00000001 01101100
     * ,截取最后10位 01 01101100,转为十进制364,设置workerId为364.
     */
    static {// 一个静态代码块
        InetAddress address;
        try {
            // 首先得到IP地址，例如192.168.1.108
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
        // IP地址byte[]数组形式，这个byte数组的长度是4，数组0~3下标对应的值分别是192，168，1，108
        byte[] ipAddressByteArray = address.getAddress();
        // 由这里计算workerId源码可知，workId由两部分组成：
        // 第一部分(ipAddressByteArray[ipAddressByteArray.length - 2] & 0B11) << Byte.SIZE：ipAddressByteArray[ipAddressByteArray.length - 2]即取byte[]倒数第二个值，即1，然后&0B11，即只取最后2位（IP段倒数第二个段取2位，IP段最后一位取全部8位，总计10位），然后左移Byte.SIZE，即左移8位（因为这一部分取得的是IP段中倒数第二个段的值）；
        // 第二部分(ipAddressByteArray[ipAddressByteArray.length - 1] & 0xFF)：ipAddressByteArray[ipAddressByteArray.length - 1]即取byte[]最后一位，即108，然后&0xFF，即通过位运算将byte转为int；
        // 最后将第一部分得到的值加上第二部分得到的值就是最终的workId
        workId = (((ipAddressByteArray[ipAddressByteArray.length - 2] & 0B11) << Byte.SIZE) + (ipAddressByteArray[ipAddressByteArray.length - 1] & 0xFF)) + "";
        log.info(workId);
    }
}
