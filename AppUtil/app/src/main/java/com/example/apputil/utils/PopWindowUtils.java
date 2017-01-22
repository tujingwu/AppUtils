package com.example.apputil.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by tujingwu on 2016/12/8.
 * popupwindow工具类
 */
public class PopWindowUtils {

    /**
     * @param context  上下文
     * @param layoutId popupwindow布局
     * @return
     */
    public static PopupWindow createPop(Context context, int layoutId) {
        View popView = LayoutInflater.from(context).inflate(layoutId, null);
        PopupWindow popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw2 = new ColorDrawable(Color.WHITE);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        popWindow.setBackgroundDrawable(dw2);
        return popWindow;
    }

    /**
     * @param context  上下文
     * @param layoutId popupwindow布局
     * @param width    popupwindow弹窗宽
     * @param height   popupwindow弹窗高
     * @return
     */
    public static PopupWindow createPop(Context context, int layoutId, int width, int height) {
        View popView = LayoutInflater.from(context).inflate(layoutId, null);
        PopupWindow popWindow = new PopupWindow(popView, width, height, true);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw2 = new ColorDrawable(Color.WHITE);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        popWindow.setBackgroundDrawable(dw2);
        return popWindow;
    }

    /**
     * @param context  上下文
     * @param layoutId popupwindow布局
     * @param width    popupwindow弹窗宽
     * @param height   popupwindow弹窗高
     * @param color    popupwindow背景颜色
     * @return
     */
    public static PopupWindow createPop(Context context, int layoutId, int width, int height, int color) {
        View popView = LayoutInflater.from(context).inflate(layoutId, null);
        PopupWindow popWindow = new PopupWindow(popView, width, height, true);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw2 = new ColorDrawable(color);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        popWindow.setBackgroundDrawable(dw2);
        return popWindow;
    }
}
