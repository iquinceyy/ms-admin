package com.qc.ms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * quincey
 * Date 2020/6/23 16:03
 */

@RestController
public class IndexController {

    @RequestMapping("/")
    String index(){
        return "pages/back/index";
    }
}
