package com.example.apputil.network.api;


import com.example.apputil.network.api.basemodule.ObjResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;


public interface ImgPostApiService {

    /**
     * 没有进度上传单张或多张图片(或文件)
     *
     * @param access_token token值
     * @param partList     上传图片的url集合
     * @return
     */
    //?access_token=e1e20200aec7723c7f42b2452f966f3064f604c9&json={"act":"token.chat.uploadImg"}
    @Multipart
    @POST("a/")
    Observable<ObjResponse<Object>> baseApiAccess(@Query("access_token") String access_token,
                                                  @Query("json") String json,
                                                  @Part List<MultipartBody.Part> partList);
}