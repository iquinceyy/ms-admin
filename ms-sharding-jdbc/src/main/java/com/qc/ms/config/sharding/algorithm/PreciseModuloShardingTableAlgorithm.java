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

package com.qc.ms.config.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 对表做一个精确分片算法（将真正的实际表名称返回）
 */

public final class PreciseModuloShardingTableAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     * 根据分片键id尾号（0-9）找对应的表
     *
     * @param tableNames    所有你配置的参数分片的表名称
     * @param shardingValue 分片键的值
     * @return 实际表的名称
     */
    @Override
    public String doSharding(final Collection<String> tableNames, final PreciseShardingValue<Long> shardingValue) {


        for (String each : tableNames) {
            // 如果表名称以当前分片键的值取模相等，那么就返回这个实际的表名称
            if (each.endsWith(shardingValue.getValue() % 10 + "")) {
                return each;// 返回实际的表名称
            }
        }
        throw new UnsupportedOperationException();
    }


}
