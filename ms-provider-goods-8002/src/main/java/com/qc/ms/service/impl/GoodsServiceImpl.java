package com.qc.ms.service.impl;

import com.qc.ms.pojo.entity.Goods;
import com.qc.ms.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * quincey
 * Date 2020/7/1 20:12
 */
//  这些 代码应该都在web端  Controller中
@Service
public class GoodsServiceImpl implements GoodsService {
    //GoodsDao 调用 goodsDao

//    @Resource
//    EsService esService  注入es

//    @Resource
//    Rabbitmq rabbitmq  注入rabbitmq

    @Override
    public Object addGoods(Goods goods) {// 性能几乎不会被影响

        // 1. 调用goodsDao.insertSelective(goods)
        // 2. 根据上一步结果: // 可能能会比较耗时
        // 3. 调用消息组件发送增加商品到ES服务器的消息（发消息时间可以忽略）
        // 4. Rabbitmq.convertAndSen("交换机","",goods,消息承载体);
        // 5. Esservice.add("goods",goods);//远程调用----耗时时间----->发消息
        return null;
    }

    // @RabbitListener()  监听交换机
    // addGoodsToEs(){
    // Esservice.add("goods",goods)
    // }


}




























