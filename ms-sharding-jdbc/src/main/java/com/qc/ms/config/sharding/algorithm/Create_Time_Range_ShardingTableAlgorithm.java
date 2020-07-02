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

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.*;

/**
 * 按照创建时间来范围分片
 */
public final class Create_Time_Range_ShardingTableAlgorithm implements RangeShardingAlgorithm<Date> {


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        Range<Date> valueRange = shardingValue.getValueRange();
        Date date = valueRange.upperEndpoint();// 创建时间的上限
        Date start;
        if (valueRange.hasLowerBound()) {// 有下限
            start = valueRange.lowerEndpoint();// 创建时间的下限
        } else {// 没有下限，默认就找这两个月的
            Calendar instance = Calendar.getInstance();// 最大时间
            instance.setTime(date);
            instance.set(Calendar.MONTH, instance.get(Calendar.MONTH) - 1);
            start = instance.getTime();
        }

        Set<Integer> monthBetween = getMonthBetween(start, date);
        Set<String> set = new HashSet<>();
        for (String each : availableTargetNames) {
            String substring = each.substring((logicTableName+"_copy").length());
            int i1 = Integer.parseInt(substring);
            for (Integer integer : monthBetween) {
                int i = integer % 12;// 对12取模，得到（0-11）
                if (i1 == i) {// 说明当前表在包含的月份内
                    set.add(each);
                    break;
                }
            }
        }
        return set;
    }

    private Set<Integer> getMonthBetween(Date minDate, Date maxDate) {
        Set<Integer> result = new HashSet<>();
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(minDate);
        max.setTime(maxDate);
        Calendar curr = min;
        while (curr.before(max)) {
            result.add(curr.get(Calendar.MONTH) + 1);
            curr.add(Calendar.MONTH, 1);
        }
        result.add(max.get(Calendar.MONTH) + 1);
        return result;
    }

}
