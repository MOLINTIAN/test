package com.stu.demo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo // 已配置了扫描包但实际启动却没有起效,加入注解使其生效
public class SrcdubooProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrcdubooProviderApplication.class, args);
    }

}
