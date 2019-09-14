package com.stu.handlerrpc.ComplexRPC.client;


import com.stu.handlerrpc.ComplexRPC.service.GoodByeService;
import com.stu.handlerrpc.ComplexRPC.service.HelloService;
import com.stu.handlerrpc.ComplexRPC.service.Person;

public class ServiceConsumer {

    // 启动
    public static void main(String[] args) throws Exception{
        ServiceClient client = new ServiceClient();
        HelloService helloService = (HelloService)
                client.createProxy(HelloService.class);
        String bywind = helloService.sayHelloWithName("bywind");
        System.out.println("helloService :"+bywind);

        GoodByeService goodByeService = (GoodByeService)
                client.createProxy(GoodByeService.class);
        String bywind1 = goodByeService.sayGoodbye(new Person("bywind", 28));
        System.out.println("goodByeService : "+bywind1);
    }
}
