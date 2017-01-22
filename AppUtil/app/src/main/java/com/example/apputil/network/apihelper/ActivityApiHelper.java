package com.example.apputil.network.apihelper;

import com.example.apputil.network.helper.RetrofitHelper;
import com.example.apputil.network.helper.RxjavaHelper;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ActivityApiHelper extends BaseApiHelper {

    /**
     * 带token的post请求例子 除了字符串还可以是
     *
     * @return
     */
    public static Observable<String> getSimple() {
        return RetrofitHelper.callBaseNormalApiAccess()
                .baseApiAccess("这里填token", "json字符串")
               // .compose(RxjavaHelper.handleResult())//这里过滤了需要的数据
                .map(new Func1<Object, String>() {
                    @Override
                    public String call(Object o) {
                        return mGson.toJson(o);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 不带token的post请求
     *
     * @return
     */
    public static Observable<String> getSimple2() {
        return RetrofitHelper.callBaseNormalApiAccess()
                .baseApiAccess("json字符串")
                .compose(RxjavaHelper.handleResult())//这里过滤了需要的数据
                .map(new Func1<Object, String>() {
                    @Override
                    public String call(Object o) {
                        return mGson.toJson(o);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
