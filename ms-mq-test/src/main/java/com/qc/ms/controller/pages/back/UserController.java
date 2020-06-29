package com.qc.ms.controller.pages.back;

import com.qc.ms.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * quincey
 * Date 2020/6/29 17:30
 */

@RestController
@RequestMapping("/pages/back/user")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("/registerUser/{name}")
    Object registerUser(@PathVariable String name){
        return userService.registerUser(name);
    }
}
