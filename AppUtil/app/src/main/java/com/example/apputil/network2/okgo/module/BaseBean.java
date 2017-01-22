package com.example.apputil.network2.okgo.module;

/**
 * Created by tujingwu on 2016/12/29.
 * 一个全局实体类
 */
public class BaseBean<T> {


    public int code;
    public String msg;
    public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
