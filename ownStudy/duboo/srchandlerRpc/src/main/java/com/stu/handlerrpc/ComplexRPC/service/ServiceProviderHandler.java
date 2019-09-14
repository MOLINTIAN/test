package com.stu.handlerrpc.ComplexRPC.service;

import com.stu.handlerrpc.ComplexRPC.util.RpcRequest;
import com.stu.handlerrpc.ComplexRPC.util.RpcResponse;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * 服务提供者的handler
 */
public class ServiceProviderHandler extends ChannelHandlerAdapter {

    private static final HashMap<String,Object> SERVICE = new HashMap<String, Object>();

    static {
        SERVICE.put(GoodByeService.class.getName(),new GoodByeServiceImpl());
        SERVICE.put(HelloService.class.getName(),new HelloServiceImpl());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("get obj from client:"+msg);
        RpcRequest request = (RpcRequest) msg;
        Object result = handle(request);
        RpcResponse response = new RpcResponse();
        response.setRequestId(request.getRequestId());
        response.setResult(result);
        ctx.writeAndFlush(response);
    }

    public Object handle(RpcRequest request){
        String className = request.getClassName();
        Object object = SERVICE.get(className);
        Class<?>[] parameterTypes = request.getParameterTypes();
        String methodName = request.getMethodName();
        Object[] args = request.getArgs();

        Class<?> targetClass = object.getClass();

        // cglib利用
        FastClass fastClass = FastClass.create(targetClass);
        FastMethod method = fastClass.getMethod(methodName, parameterTypes);
        Object invoke = null;
        try {
            invoke = method.invoke(object, args);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return invoke;
    }
}
