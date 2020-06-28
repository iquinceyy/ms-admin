package com.qc.ms.rest.user;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.qc.ms.pojo.entity.User;
import com.qc.ms.pojo.query.UserQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * quincey
 * Date 2020/6/23 15:46
 */

@RequestMapping("/user/")
@RestController
public class UserRest {

    @Value("${server.port}")
    String port;

//    name="execution.isolation.thread.interruptOnTimeout
//          execution.isolation.thread.interruptOnTimeout
//    execution.isolation.thread.timeoutInMilliseconds
    @RequestMapping("/findUserById/{id}")
    @HystrixCommand(fallbackMethod = "findUserByIdBackup",
            commandProperties = {@HystrixProperty(name =HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "3000")})
    User findUserById(@PathVariable Long id){
//        System.err.println((1 / 0));
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        if(id ==123){
//            System.err.println((1 / 0));
//        }

        User u = new User();
        u.setName("张三" + port);
        return u;
//        return "张三"+port;
    }

    User findUserByIdBackup(@PathVariable Long id){
        User u = new User();
        u.setName("张三" + port);
        return u;
//        return "熔断备份333";
    }


    // 请注意，@RequestBody千万不能少：因为feign此时已经把请求参数当作post请求，而且contenType=application/json
    @RequestMapping("/listUser")
    List<User> listUser(@RequestBody UserQuery query){
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setName("李四");
        user.setUserId(1L);

        list.add(user);
        return list;
    }
    @RequestMapping("/listUsers")
    List<User> listUsers( String name, Long userId) {
        System.err.println("1234556");
        return null;
    }

}
