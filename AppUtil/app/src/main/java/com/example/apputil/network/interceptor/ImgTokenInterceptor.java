package com.example.apputil.network.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tujingwu on 2016/11/21.
 * 图片token过期 拦截器
 */
public class ImgTokenInterceptor implements Interceptor {
    private String responseUrl = "http://www.zier365.com/a/index.php/?access_token=";
    private String json = "上传图片的json";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Response oldResponse = chain.proceed(oldRequest);
        int code = oldResponse.code();
        if (code == 401) {
            //token过期就重新请求token在继续原来的请求
            //完整图片上传url：
            //http://www.zier365.com/a/?access_token=d061e14c98ed3400a7d11f2e5daf971fa029fb32&json={"act":"token.chat.uploadImg"}
            //Log.e("ddd", "token过期" + code);
            RequestBody requestBody = oldRequest.body();
           // Log.e("ddd", "requestBody:" + requestBody.contentLength());
            Request newRequest = oldRequest.newBuilder()
                    .url(responseUrl + "请求到有用的新的token"+json)
                    .post(requestBody)
                    .build();
            return chain.proceed(newRequest);
        }

        return oldResponse;
    }
}
