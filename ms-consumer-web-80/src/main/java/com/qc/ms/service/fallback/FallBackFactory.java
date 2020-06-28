package com.qc.ms.service.fallback;

import com.qc.ms.pojo.entity.User;
import com.qc.ms.pojo.query.UserQuery;
import com.qc.ms.service.UserServiceFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * quincey
 * Date 2020/6/25 10:04
 */

@Component
public class FallBackFactory implements FallbackFactory<UserServiceFeign> {
    @Override
    public UserServiceFeign create(Throwable throwable) {

        return new UserServiceFeign() {
            @Override
            public User findUserById(Long id) {
                System.err.println("我是服务降级的findUserById=====FallbackFactory<UserServiceFeign>");
                return null;
            }

            @Override
            public List<User> listUser(UserQuery query) {
                System.err.println("我是服务降级的listUser=====FallbackFactory<UserServiceFeign>");
                return null;
            }

            @Override
            public List<User> listUsers(String name, Long id) {
                return null;
            }
        };
    }
}
