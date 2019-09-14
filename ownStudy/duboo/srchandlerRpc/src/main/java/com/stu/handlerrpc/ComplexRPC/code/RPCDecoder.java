package com.stu.handlerrpc.ComplexRPC.code;

import com.stu.handlerrpc.ComplexRPC.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码
 */
// ByteToMessageDecoder是netty提供的一个类
public class RPCDecoder extends ByteToMessageDecoder {
    private Class<?> genericClass;

    public RPCDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        final int length = in.readableBytes();
        final byte[] bytes = new byte[length];
        in.readBytes(bytes, 0, length);
        Object obj = SerializationUtil.deserialize(bytes, genericClass);
        out.add(obj);
    }

}
