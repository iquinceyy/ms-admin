package com.qc.ms.service.impl;

import com.qc.ms.config.rabbitmq.DirectExchangeConfig;
import com.qc.ms.config.rabbitmq.FanoutExchangeConfig;
import com.qc.ms.config.rabbitmq.TopicExchangeConfig;
import com.qc.ms.pojo.entity.User;
import com.qc.ms.service.ListenerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * quincey
 * Date 2020/6/29 17:45
 */

@Service
public class ListenerServiceImpl implements ListenerService {
    @Override
    public Object registerUser(String name) {
        return null;
    }

    @RabbitListener(queues = {FanoutExchangeConfig.QIANFENG_JAVA_QUEUE_1})
    public void suiyi(User user) {
        System.err.println(user);
        // 接收到user之后，就应该根据user处理业务 只要业务层不报错，就默认你收到消息了
//        try {
//            System.err.println(1 / 0);// 假设你业务报错了，消息组件就会一直给你发。这一点很像（支付宝和微信支付通知）
//        } catch (Exception e) {
//        }
    }

    @RabbitListener(queues = {FanoutExchangeConfig.QIANFENG_JAVA_QUEUE_1})
    public void suiyi1(User user) {
        System.err.println(user);
        // 接收到user之后，就应该根据user处理业务 只要业务层不报错，就默认你收到消息了
//        try {
//            System.err.println(1 / 0);// 假设你业务报错了，消息组件就会一直给你发。这一点很像（支付宝和微信支付通知）
//        } catch (Exception e) {
//        }
    }

    @RabbitListener(queues = {FanoutExchangeConfig.QIANFENG_JAVA_QUEUE_2})
    public void suiyi2(User user) {
        System.err.println(user);// 只要业务层不报错，就默认你收到消息了
        // 接收到user之后，就应该根据user处理业务
//        try {
//            System.err.println(1 / 0);// 假设你业务报错了，消息组件就会一直给你发。这一点很像（支付宝和微信支付通知）
//
//        } catch (Exception e) {
//
//        }

    }
    // 自动负载均衡:监听同一个队列的时候，自动把消息 负载均衡的去给每一个 监听器消费（幂等性）
    @RabbitListener(queues = {DirectExchangeConfig.QIANFENG_JAVA_QUEUE_3})// 监听三队列
    public void suiyi3(String name) {
        System.err.println("suiyi3" + name);// 只要业务层不报错，就默认你收到消息了
        // 接收到user之后，就应该根据user处理业务
    }

    @RabbitListener(queues = {DirectExchangeConfig.QIANFENG_JAVA_QUEUE_3})// 监听三队列
    public void suiyi5(String name) {
        System.err.println("suiyi5" + name);// 只要业务层不报错，就默认你收到消息了
        // 接收到user之后，就应该根据user处理业务
    }


    @RabbitListener(queues = {DirectExchangeConfig.QIANFENG_JAVA_QUEUE_4})// 监听三队列(直连模式，这个队列将不会收到消息。)
    public void suiyi4(String name) {
        System.err.println(name);// 只要业务层不报错，就默认你收到消息了
        // 接收到user之后，就应该根据user处理业务
    }

//    +++++=======+++++ 监听主题模式（路由模式）

    // 自动负载均衡:监听同一个队列的时候，自动把消息 负载均衡的去给每一个 监听器消费（幂等性）
    @RabbitListener(queues = {TopicExchangeConfig.QIANFENG_JAVA_QUEUE_5})// 监听三队列
    public void topic5(String name) {
        System.err.println("topic5" + name);// 只要业务层不报错，就默认你收到消息了
        // 接收到user之后，就应该根据user处理业务
    }

    // 自动负载均衡:监听同一个队列的时候，自动把消息 负载均衡的去给每一个 监听器消费（幂等性）
    @RabbitListener(queues = {TopicExchangeConfig.QIANFENG_JAVA_QUEUE_6})// 监听三队列
    public void topic6(String name) {
        System.err.println("topic6" + name);// 只要业务层不报错，就默认你收到消息了
        // 接收到user之后，就应该根据user处理业务
    }

    // 自动负载均衡:监听同一个队列的时候，自动把消息 负载均衡的去给每一个 监听器消费（幂等性）
    @RabbitListener(queues = {TopicExchangeConfig.QIANFENG_JAVA_QUEUE_7})// 监听三队列
    public void topic7(String name) {
        System.err.println("topic7" + name);// 只要业务层不报错，就默认你收到消息了
        // 接收到user之后，就应该根据user处理业务
    }

}
