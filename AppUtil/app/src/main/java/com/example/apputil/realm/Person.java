package com.example.apputil.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tujingwu on 2016/11/25.
 * <p/>
 * 相当与数据库的表的字段 该表类一定要继承RealmObject
 */
public class Person extends RealmObject {

    @PrimaryKey //主键 -- 如果声明为主键 在set数据时一定不能为空或不填
    private long user_id;
    //@Required  -- 代表一定要填的字段
    private String name;
    private String age;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
