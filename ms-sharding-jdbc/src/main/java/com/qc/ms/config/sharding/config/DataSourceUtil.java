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


import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;


public final class DataSourceUtil {// 数据源工具类。就是配置数据源的

    /**
     * ipPortDb ip:port/数据库名称
     * @param ipPortDb
     * @return
     */
    public static DataSource createDataSource(final String ipPortDb) {
        DruidDataSource druidDataSource = new DruidDataSource();// 德鲁伊连接池
        druidDataSource.setUrl("jdbc:mysql://" + ipPortDb + "?serverTimezone=Asia/Shanghai");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        return druidDataSource;
    }
}
