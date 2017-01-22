package com.example.apputil.network.apihelper;

import android.content.Context;

import com.google.gson.Gson;


public class BaseApiHelper {
    static Gson mGson;
    static Context mContext;

    static {
        mGson = new Gson();

        //获取token的操作，考虑在api方法获取当前最新token
    }

    /**
     * 获取token
     *
     * @return
     */
    /*public static String token() {
        String json = mACache.getAsString(CacheConfig.Key_User_Token);
        UserToken userToken = mGson.fromJson(json,UserToken.class);
        return userToken == null ? "82055bd82e5baa1150a073fcbb739d9ddbe1dd63" : userToken.getAccess_token();
    }*/
}
