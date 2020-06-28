package com.qc.ms.service.fallback;

import com.qc.ms.pojo.entity.User;
import com.qc.ms.pojo.query.UserQuery;
import com.qc.ms.service.UserServiceFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * quincey
 * Date 2020/6/24 21:36
 */

@Component
public class UserServiceFallBack implements UserServiceFeign {

    @Override
    public User findUserById(Long id) {
        System.err.println("我是服务降级的findUserById-----UserServiceFeign");
        return null;
    }

    @Override
    public List<User> listUser(UserQuery query) {
        System.err.println("我是服务降级的listUser-----UserServiceFeign");
        return null;
    }

    @Override
    public List<User> listUsers( String name, Long userId) {
        return null;
    }
}
