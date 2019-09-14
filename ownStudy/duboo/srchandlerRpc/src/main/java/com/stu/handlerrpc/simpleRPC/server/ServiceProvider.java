package com.stu.handlerrpc.simpleRPC.server;

public class ServiceProvider implements Service {
    @Override
    public String sayHelloWithName(String name) {
        return "hello "+name;
    }
}
