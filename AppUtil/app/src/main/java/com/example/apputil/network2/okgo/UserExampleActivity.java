package com.example.apputil.network2.okgo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.apputil.network2.okgo.api.Simple;
import com.example.apputil.network2.okgo.callback.JsonCallback;
import com.example.apputil.network2.okgo.module.Login;

import okhttp3.Call;
import okhttp3.Response;
import rx.functions.Action1;

/**
 * Created by tujingwu on 2016/12/29.
 */
public class UserExampleActivity extends AppCompatActivity {
    String url = "http://www.zier365.com/a/index.php/?access_token=939b119394b999ee91206e67c7603b0780d3d197";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //网络请求使用例子

        //1、传统请求方式
        Simple.getSimple1(this, new JsonCallback() {
            @Override
            public void onSuccess(Object o, Call call, Response response) {

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Log.e("ddd", "Exception:" + e.getMessage());
            }
        });




        //2、Rx使用例子
        Simple.getSimple2(this)
                .subscribe(new Action1<Login>() {
                    @Override
                    public void call(Login bean) {
                        //Log.e("ddd", "onSuccess:" + bean.getResult().toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("ddd", "Exception:" + throwable.getMessage());
                    }
                });
    }
}
