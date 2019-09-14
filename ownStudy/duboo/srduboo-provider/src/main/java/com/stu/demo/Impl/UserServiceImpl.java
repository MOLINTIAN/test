package com.stu.demo.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.stu.dubboapi.model.User;
import com.stu.dubboapi.service.UserService;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.register.id}"
)
public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        return new User("zhouyi", 26);
    }
}
