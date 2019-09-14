package com.stu.handlerrpc.ComplexRPC.service;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHelloWithName(String name) {
        return "hello "+name;
    }
}
