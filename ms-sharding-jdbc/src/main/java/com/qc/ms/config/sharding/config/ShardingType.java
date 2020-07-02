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

public enum ShardingType {

    SHARDING_DATABASES,// 分库

    SHARDING_TABLES,//分表

    SHARDING_DATABASES_AND_TABLES,// 分库分表

    MASTER_SLAVE,// 主从

    SHARDING_MASTER_SLAVE,// 读写分离

    ENCRYPT
}
