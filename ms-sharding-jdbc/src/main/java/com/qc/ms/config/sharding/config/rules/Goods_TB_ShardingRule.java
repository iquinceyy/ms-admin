package com.qc.ms.config.sharding.config.rules;


import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 作者：森林杜夫人
 * 商品表的分片规则
 */
public class Goods_TB_ShardingRule implements PreciseShardingAlgorithm<String> {


    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<String> shardingValue) {
        String tbName = getTbName(shardingValue.getValue());

        if (StringUtils.isEmpty(tbName)) {
            throw new UnsupportedOperationException("未找到分片的商品表：" + shardingValue.getValue());
        }
        return tbName;

    }

    public static String getTbName(String province) {
        switch (province) {
            case "重庆市":
                return "goods_chongqing";
            case "北京市":
                return "goods_beijing";
        }
        return null;
    }

}
