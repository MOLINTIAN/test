package com.stu.handlerrpc.simpleRPC.client;

import com.stu.handlerrpc.simpleRPC.server.Service;

public class ServiceConsumer {

    public static void main(String[] args) throws Exception{
        ServiceClient client = new ServiceClient();
        Service proxy = (Service) client.createProxy(Service.class);
        int i = 0;
        for (;;){
            Thread.sleep(1000);
            String result = proxy.sayHelloWithName("bywind"+i);
            System.out.println("from rpc server:"+result);
            i++;
        }

    }
}
