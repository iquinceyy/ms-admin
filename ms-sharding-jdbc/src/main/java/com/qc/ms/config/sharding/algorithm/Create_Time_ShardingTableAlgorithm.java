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

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * 按照创建时间来精确分片
 */
public final class Create_Time_ShardingTableAlgorithm implements PreciseShardingAlgorithm<Date> {

    /**
     * 根据分片键id尾号（0-9）找对应的表
     *
     * @param tableNames
     * @param shardingValue
     * @return
     */
    @Override
    public String doSharding(final Collection<String> tableNames, final PreciseShardingValue<Date> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        Date createTime = shardingValue.getValue();// 创建时间
        Calendar instance = Calendar.getInstance();
        instance.setTime(createTime);
        int i = instance.get(Calendar.MONTH) + 1;// 月份

        for (String each : tableNames) {
            int x = i % 12;//(0-11);
            String substring = each.substring((logicTableName+"_copy").length());
            if (Integer.parseInt(substring) == x) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }


}
