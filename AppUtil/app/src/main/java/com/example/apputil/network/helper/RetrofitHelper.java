package com.example.apputil.network.helper;

import com.example.apputil.network.api.BaseNormalApiService;
import com.example.apputil.network.api.ImgPostApiService;
import com.example.apputil.network.interceptor.ImgTokenInterceptor;
import com.example.apputil.network.interceptor.TokenInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static final String API_BASE_URL = "http://www.zier365.com/";
    private static OkHttpClient mOkHttpClient, mOkHttpClient2;

    static {
        initOkHttpClient();
        initOkHttpClient2();
    }

    /**
     * 正常网络请求
     *
     * @return
     */
    public static BaseNormalApiService callBaseNormalApiAccess() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                        // .addConverterFactory(ScalarsConverterFactory.create()) 这是字符串
                .addConverterFactory(GsonConverterFactory.create())//这是gson
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        return retrofit.create(BaseNormalApiService.class);
    }

    /**
     * 上传图片网络请求
     *
     * @return
     */
    public static ImgPostApiService imgPostApiAccess() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                        // .addConverterFactory(ScalarsConverterFactory.create()) 这是字符串
                .addConverterFactory(GsonConverterFactory.create())//这是gson
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(mOkHttpClient2)
                .build();
        return retrofit.create(ImgPostApiService.class);
    }


    /**
     * 初始化OKHttpClient
     * 设置缓存
     * 设置超时时间
     * 设置UA拦截器
     */
    private static void initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    //设置Http缓存
                    // Cache cache = new Cache(new File(MainApplication.getInstance()
                    //        .getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
                    mOkHttpClient = new OkHttpClient.Builder()
                            // .cache(cache)
                            // .addInterceptor(interceptor)
                            // .addNetworkInterceptor(new StethoInterceptor())
                            .addInterceptor(new TokenInterceptor())//设置token拦截器 用于判断token是否过期
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    private static void initOkHttpClient2() {
        if (mOkHttpClient2 == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient2 == null) {
                    //设置Http缓存
                    // Cache cache = new Cache(new File(MainApplication.getInstance()
                    //        .getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
                    mOkHttpClient2 = new OkHttpClient.Builder()
                            // .cache(cache)
                            // .addInterceptor(interceptor)
                            // .addNetworkInterceptor(new StethoInterceptor())
                            .addInterceptor(new ImgTokenInterceptor())//设置上传图片token拦截器 用于判断token是否过期
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }
}
