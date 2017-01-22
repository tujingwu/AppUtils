package com.example.apputil.network.apihelper;

import com.example.apputil.network.helper.RetrofitHelper;
import com.example.apputil.network.helper.RxjavaHelper;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tujingwu on 2016/11/21.
 */
public class PostImgHelper extends BaseApiHelper {

    private static final String POST_IMG_JSON = "{\"act\":\"token.chat.uploadImg\"}";//上传图片拼接在后面的json
    private static final String token = " 82055bd82e5baa1150a073fcbb739d9ddbe1dd63";

    public static Observable<String> getSimple3(List<String> pathList) {
        return RetrofitHelper.imgPostApiAccess()
                .baseApiAccess(token, POST_IMG_JSON, getParts(pathList))
                .compose(RxjavaHelper.handleResult())//这里过滤了需要的数据
                .map(new Func1<Object, String>() {
                    @Override
                    public String call(Object o) {
                        return mGson.toJson(o);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static List<MultipartBody.Part> getParts(List<String> pathList) {
        //获取多张待上传图片的地址列表--也可以待上传的文件路径
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型

        //单张图片或多张图片
        for (int i = 0; i < pathList.size(); i++) {
            File file = new File(pathList.get(i));//filePath 图片地址
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            // builder.addFormDataPart("imgfile" + i, file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
            builder.addFormDataPart("uploadedimg", file.getName(), imageBody);//uploadedimg 后台接收图片流的参数名
        }

        return builder.build().parts();
    }
}
