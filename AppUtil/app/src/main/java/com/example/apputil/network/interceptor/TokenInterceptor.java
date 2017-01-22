package com.example.apputil.network.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tujingwu on 2016/11/21.
 * 正常普通网络请求token过期或其它 拦截器
 */
public class TokenInterceptor implements Interceptor {
    private String responseUrl = "http://www.zier365.com/a/index.php/?access_token=";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();
        Response oldResponse = chain.proceed(oldRequest);
        int code = oldResponse.code();
        if (code == 401) {
            //token过期就重新请求token在继续请求
            //Log.e("ddd", "token过期" + code);
            RequestBody requestBody = oldRequest.body();
              Log.e("ddd", "requestBody:" + requestBody.contentLength());
            Request newRequest = oldRequest.newBuilder()
                    .url(responseUrl + "请求到有用的新的token")
                    .post(requestBody)
                    .build();
            return chain.proceed(newRequest);
        }

        return oldResponse;
    }
}
