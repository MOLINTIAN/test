package com.stu.duboo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.stu.dubboapi.model.User;
import com.stu.dubboapi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableDubbo
public class SrdubooComsumerApplicationTests {

    @Reference(
            version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345"
    )
    UserService userService;

    @Test
    public void RPCTest() {
        User user = userService.getUser();
        System.out.print(user);
    }

}
