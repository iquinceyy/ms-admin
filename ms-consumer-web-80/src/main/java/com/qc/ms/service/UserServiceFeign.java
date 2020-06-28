package com.qc.ms.service;

import com.qc.ms.pojo.entity.User;
import com.qc.ms.pojo.query.UserQuery;
import com.qc.ms.service.fallback.FallBackFactory;
import com.qc.ms.service.fallback.UserServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * quincey
 * Date 2020/6/24 20:26
 */

//@FeignClient(value = "user-rest-service",path = "user",fallback = UserServiceFallBack.class)
//@FeignClient(value = "user-rest-service",path = "user",fallbackFactory = FallBackFactory.class)
@FeignClient(value = "user-rest-service",path = "user",configuration = FeignClientConfig.class)
public interface UserServiceFeign {

    // 产生熔断的原因：1内部报错 2业务超时 3请求并发量太大了。

    @RequestMapping("/findUserById/{id}")// 第一种路径传参
    User findUserById(@PathVariable Long id);


    // 这个就好比是路径拼接参数，提供方不需要加任何注解都能获取参数值。
    @RequestMapping("/listUser")
    List<User> listUser(UserQuery query);

    @RequestMapping("/listUsers")
    List<User> listUsers(@RequestParam String name, @RequestParam Long userId);
}
