package com.stu.handlerrpc.simpleRPC.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.Callable;

/**
 * 客服端处理类,通过call暴露出去
 */
public class ServiceConsumerHandler extends ChannelHandlerAdapter implements Callable{

    // 上下文对象
    private ChannelHandlerContext context;
    // 服务端返回的结构
    private String result;
    // 反射参数
    private String params;

    //  ChannelHandlerContext初始化
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       context = ctx;

    }

    // 读取服务端返回结果
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // msg服务端返回的结果
        result = msg.toString();
        // 注意锁的等待和唤醒
        notify();
    }

    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(params);
        // 等待唤醒
        wait();
        return result;
    }


    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
