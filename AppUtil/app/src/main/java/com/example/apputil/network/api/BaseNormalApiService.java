package com.example.apputil.network.api;


import com.example.apputil.network.api.basemodule.ObjResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface BaseNormalApiService {
    /**
     * 不带token普通post请求
     */
    @POST("a/")
    Observable<ObjResponse<Object>> baseApiAccess(@Query("json") String json);

    /**
     * 带token的post请求
     *
     * @param access_token
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("a/index.php/")
    Observable<ObjResponse<Object>> baseApiAccess(@Query("access_token") String access_token, @Field("json") String json);//BaseRespone<Object>//@Path("token") String token

}