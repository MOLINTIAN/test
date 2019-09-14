package com.stu.handlerrpc.simpleRPC.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServiceProviderHandler extends ChannelHandlerAdapter {

    private static final Service SERVICE = new ServiceProvider();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 客服端传入的msg
        String s = msg.toString();
        System.out.println("get str from client:"+s);
        // 这个方法需要有返回值，写入
        ctx.writeAndFlush(SERVICE.sayHelloWithName(s));
    }
}
