package com.qc.ms.rest.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * quincey
 * Date 2020/6/23 15:46
 */

@RequestMapping("/user/")
@RestController
public class UserRest {

    @Value("${server.port}")
    String port;

    @RequestMapping("/getUserById/{id}")
    Object qwe(@PathVariable Long id){
        return "张三"+port;
    }
}
