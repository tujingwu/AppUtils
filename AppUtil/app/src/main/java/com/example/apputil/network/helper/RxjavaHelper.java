package com.example.apputil.network.helper;

import com.example.apputil.network.api.basemodule.ObjResponse;
import com.google.gson.Gson;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class RxjavaHelper {
    static Gson mGson;

    static {
        mGson = new Gson();
    }

    /**
     * 对Retrofit2.0网络请求结果根据服务器返回码数据进行预处理
     *
     * @return
     */
    public static <T> Observable.Transformer<ObjResponse<T>, T> handleResult() {
        return new Observable.Transformer<ObjResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<ObjResponse<T>> tObservable) {
                return tObservable.flatMap(new Func1<ObjResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(ObjResponse<T> result) {
                        //                        Track.i("result from network : " + result);
                        if ("0".equals(result.getError())) {//对返回的结果进行判断
                            return createData(result.getResult());
                        } else {
                            return Observable.error(new ServerException(result.getMsg()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /**
     * 创建返回成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }

    /**
     * 对正确数据进行转换
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<String, T> filterResult(T t) {
        return new Observable.Transformer<String, T>() {

            @Override
            public Observable<T> call(Observable<String> objectObservable) {
                return objectObservable.flatMap(new Func1<String, Observable<T>>() {
                    @Override
                    public Observable<T> call(String o) {

                        return createData((T) o);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
