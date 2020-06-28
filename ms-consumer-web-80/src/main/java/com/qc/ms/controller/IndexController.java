package com.qc.ms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * quincey
 * Date 2020/6/23 16:03
 */

@RestController
public class IndexController {

//    @RequestMapping("/")
//    String index(){
//        return "pages/back/index";
//    }

    @Value("${spring.application.name}")
    String appName;

    @RequestMapping("/")
    String index() {
        return appName;
    }


}
