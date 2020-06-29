package com.qc.ms.service.impl;

import com.qc.ms.config.rabbitmq.FanoutExchangeConfig;
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
}
