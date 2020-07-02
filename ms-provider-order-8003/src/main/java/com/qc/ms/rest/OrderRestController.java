package com.qc.ms.rest;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * quincey
 * Date 2020/6/30 16:16
 */

@RestController
@RequestMapping("/order")
public class OrderRestController {

    @Resource
    DiscoveryClient discoveryClient;

    @RequestMapping("/test")
    Object test(){
        List<ServiceInstance> instances = discoveryClient.getInstances("order-rest-qc");
        System.out.println(instances);

        // 后续省略

        // restTemplate.
        return "能不能成功！";
    }

}
