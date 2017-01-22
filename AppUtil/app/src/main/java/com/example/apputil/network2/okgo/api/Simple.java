package com.example.apputil.network2.okgo.api;

import android.app.Activity;
import android.util.Log;

import com.example.apputil.network2.okgo.callback.JsonCallback;
import com.example.apputil.network2.okgo.callback.JsonConvert;
import com.example.apputil.network2.okgo.module.BaseBean;
import com.example.apputil.network2.okgo.module.Login;
import com.lzy.okgo.OkGo;
import com.lzy.okrx.RxAdapter;

import okhttp3.Call;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by tujingwu on 2016/11/23.
 */
public class Simple {
    static String url = "http://www.zier365.com/a/index.php/?access_token=f6fc8ee73e0875e8c763ea841a2672e57744d233";

    //传递网络请求回调
    public static void getSimple1(final Activity activity, final JsonCallback json) {

        OkGo.post(url)
                .tag(activity)
                .upJson("{\"act\":\"token.classes.getClassOnes\"}")
                .execute(new JsonCallback<BaseBean<Login>>() {

                    @Override
                    public void onSuccess(BaseBean<Login> beanBaseBean, Call call, Response response) {
                        //Log.e("ddd", "onSuccess:" + beanBaseBean.getResult().toString());
                        json.onSuccess(beanBaseBean, call, response);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.e("ddd", "Exception:" + e.getMessage());
                        json.onError(call, response, e);
                    }
                });
        ;
    }

    //rx形式回调封装
    public static Observable<Login> getSimple2(Activity activity) {
        return OkGo.post(url)
                .upJson("{\"act\":\"token.classes.getClassOnes\"}")
                .getCall(new JsonConvert<Login>() {
                }, RxAdapter.<Login>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        // Log.e("ddd", "开始请求数据");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
