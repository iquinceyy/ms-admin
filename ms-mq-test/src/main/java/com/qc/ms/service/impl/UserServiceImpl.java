package com.qc.ms.service.impl;

import com.qc.ms.config.rabbitmq.DirectExchangeConfig;
import com.qc.ms.config.rabbitmq.FanoutExchangeConfig;
import com.qc.ms.config.rabbitmq.TopicExchangeConfig;
import com.qc.ms.pojo.entity.User;
import com.qc.ms.service.UserService;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * quincey
 * Date 2020/6/29 17:33
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    RabbitTemplate rabbitTemplate;
    @Override
    public Object registerUser(String name) {
        // 第一步：向user表插入数据，省略
        // 向rabbitmq发送消息。
        User u = new User();
        u.setName("123"+name);
        u.setUserId(99L);
        CorrelationData correlationData =new CorrelationData(u.getUserId().toString());
        rabbitTemplate.convertAndSend(FanoutExchangeConfig.QIANFENG_JAVA_FANOUT_EXCHANGE,"null",u,correlationData);
        return "ok";
    }

    @Override
    public Object testSendRoutingKey(String name) {

        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(DirectExchangeConfig.QIANFENG_JAVA_DIRECT_EXCHANGE, DirectExchangeConfig.QIANFENG_JAVA_ROUTINGKEY_3, name + i);
        }
        return "ok";
    }

    @Override
    public Object testSendTopic(String name) {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(TopicExchangeConfig.QIANFENG_JAVA_TOPIC_EXCHANGE, "qianfeng.#", name + i);
        }
        return "ok";
    }
}
