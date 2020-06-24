package com.qc.ms.service;

import com.qc.ms.pojo.entity.User;
import com.qc.ms.pojo.query.UserQuery;
import com.qc.ms.service.fallback.UserServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * quincey
 * Date 2020/6/24 20:26
 */

@FeignClient(value = "user-rest-service",path = "user",fallback = UserServiceFallBack.class)
public interface UserServiceFeign {

    @RequestMapping("/findUserById/{id}")
    Object findUserById(@PathVariable Long id);


    @RequestMapping("/listUser")
    List<User> listUser(UserQuery query);
}
