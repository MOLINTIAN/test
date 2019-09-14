package com.stu.handlerrpc.ComplexRPC.service;


import com.stu.handlerrpc.ComplexRPC.code.RPCDecoder;
import com.stu.handlerrpc.ComplexRPC.code.RPCEncoder;
import com.stu.handlerrpc.ComplexRPC.util.RpcRequest;
import com.stu.handlerrpc.ComplexRPC.util.RpcResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServiceServer {

    private int port = 0;

    public ServiceServer(int port) {
        this.port = port;
    }

    NioEventLoopGroup boss = new NioEventLoopGroup();
    NioEventLoopGroup worker = new NioEventLoopGroup();
    ServerBootstrap bootstrap = new ServerBootstrap();
    public void run(){
        System.out.println("running on port ："+port);
        try {
            bootstrap.group(boss,worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new RPCDecoder(RpcRequest.class));
                    p.addLast(new RPCEncoder(RpcResponse.class));
                    p.addLast(new ServiceProviderHandler());
                }
            });

            ChannelFuture channelFuture = bootstrap.bind("127.0.0.1", port).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }



    public static void main(String[] args) {
        ServiceServer serviceServer = new ServiceServer(9999);
        serviceServer.run();
    }


}
