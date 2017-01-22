package com.example.apputil.network.helper;

/**
 * 网络请求异常类处理处
 */
public class ServerException extends Throwable {
    public ServerException(String detailMessage) {
        super(detailMessage);
    }
}
