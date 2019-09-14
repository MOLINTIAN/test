package com.stu.handlerrpc.ComplexRPC.util;

import java.io.Serializable;

/**
 * RPC返回信息类
 */
public class RpcResponse implements Serializable {
    // requestId匹配一个请求，一个返回
    private String requestId;
    private Object result;

    public RpcResponse(){

    }
    public RpcResponse(String requestId, Object result) {
        this.requestId = requestId;
        this.result = result;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
