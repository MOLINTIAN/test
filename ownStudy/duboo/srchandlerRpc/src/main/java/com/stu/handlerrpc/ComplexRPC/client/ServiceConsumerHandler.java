package com.stu.handlerrpc.ComplexRPC.client;

import com.stu.handlerrpc.ComplexRPC.util.RpcRequest;
import com.stu.handlerrpc.ComplexRPC.util.RpcResponse;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.Callable;

public class ServiceConsumerHandler extends ChannelHandlerAdapter implements Callable{

    private ChannelHandlerContext context;
    private RpcResponse rpcResponse;
    private Object result;
    private RpcRequest params;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       context = ctx;

    }

    // 读取管道消息
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        rpcResponse = (RpcResponse) msg;
        result = rpcResponse.getResult();
        // 线程唤醒
        notify();
    }

    // 启动的方法
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(params);
        // 线程挂起
        wait();
        return result;
    }


    public RpcRequest getParams() {
        return params;
    }

    public void setParams(RpcRequest params) {
        this.params = params;
    }
}
