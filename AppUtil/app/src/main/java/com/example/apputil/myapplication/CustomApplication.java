package com.example.apputil.myapplication;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by tujingwu on 2016/11/16.
 * 全局配置
 */
public class CustomApplication extends Application {
    private final String REALM_NAME = "realm.name";

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initOkgo();
    }

    //初始化okgo网络请求框架
    private void initOkgo() {

    }

    //初始化并且配置realm数据库
    private void initRealm() {
        //文档https://realm.io/cn/docs/java/latest/#section-28
        //博客介绍：http://www.cnblogs.com/liushilin/p/5752099.html
        Realm.init(this);//初始化realm数据库
        RealmConfiguration config = new RealmConfiguration.Builder()
                // .schemaVersion(0)//数据库版本
                // .migration(new MyMigration())//在这里做数据库迁移操作
                .name(REALM_NAME)
                .build();

        Realm.setDefaultConfiguration(config);
    }

}
