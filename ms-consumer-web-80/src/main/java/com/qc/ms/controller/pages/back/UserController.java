package com.qc.ms.controller.pages.back;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.qc.ms.pojo.entity.User;
import com.qc.ms.pojo.query.UserQuery;
import com.qc.ms.service.UserServiceFeign;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * quincey
 * Date 2020/6/23 16:02
 */

@RestController
@RequestMapping("/pages/back/user")
public class UserController {
    @Resource
    UserServiceFeign userServiceFeign;
    @Resource
    RestTemplate restTemplate;// restTemplate 目前是不在容器之中的，一定需要先注入到容器之中

    @Resource
    EurekaClient eurekaClient;

    //@Resource
    //DiscoveryClient discoveryClient; 需要主启动加注解和依赖

    @RequestMapping("/findUserById/{id}")
    Object findUserById(@PathVariable Long id) {
        // 1  restTemplate.getForObject():// 得到请求结果，并且转换成 object对象
        //  2 restTemplate.postForObject()
        // 3 <T> ResponseEntity<T> exchange(String var1, HttpMethod var2, @Nullable HttpEntity<?> var3, Class<T> var4, Object... var5) throws RestClientException;
        // 4 restTemplate.execute()
        // 目前来说，你只知道的是 用户微服务的名字 user-rest-service // 这种方式也不会用，因为太麻烦了？什么是面向接口开发？

        // String forObject = restTemplate.getForObject("http://localhost:8001/user/findUserById/" + id, String.class);

        //  InstanceInfo nextServerFromEureka = discoveryClient.getNextServerFromEureka("USER-REST-SERVICE", false);
// eureka会根据 服务的名称依次的找寻一个服务的信息，给服务的消费方 有个缓存（默认情况下是30秒）
//        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("USER-REST-SERVICE", false);
//
//        String homePageUrl = nextServerFromEureka.getHomePageUrl();// 缓存的一个服务器ip地址
//
//        String forObject = restTemplate.getForObject(homePageUrl + "/user/getUserById/" + id, String.class);

        //到这里了 先去 服务端 再回来
//        String forObject = restTemplate.getForObject("http://USER-REST-SERVICE/user/getUserById/" + id, String.class);
        // 【面试重点】Ribbon 负载均衡组件
//        System.err.println(forObject);

        // 后边有ribbon可以做负载均衡策略自定义

        // 【面试重点】hystrix做熔断和服务降级

        // 【实操重点】feign（就包含ribbon,包含hystrix） 技术：就是一种把http（封装成接口）的技术。去访问一个http请求的时候，直接调用接口即可。(实际工作中会用的方式)
        Object forObject = userServiceFeign.findUserById(id);



        UserQuery userQuery = new UserQuery();
        userQuery.setName("张三");
        userQuery.setUserId(123L);

        List<User> userList = userServiceFeign.listUser(userQuery);
        System.out.println(userList);
        return forObject;



    }
}
