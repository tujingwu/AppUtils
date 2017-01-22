package com.example.apputil.utils;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.apputil.R;

/**
 * Created by tujingwu on 2016/11/16.
 * dialog对话框工具类
 * setDialog         :普通加载dialog
 * getButtonDialog   :底部弹出dialog
 * getNormDialog    :正常的提示对话框AlertDialog
 */
public class DialogUtils {

    /**
     * 普通dialog
     *
     * @param context     上下文
     * @param layoutRecId dialog布局
     * @return
     */
    public static Dialog setDialog(Context context, int layoutRecId) {
        Dialog dialog = new Dialog(context, R.style.dialogStyle);
        dialog.setCancelable(true);// 不可以用“返回键”取消
        dialog.setCanceledOnTouchOutside(false);//设置外部不能取消
        dialog.setContentView(layoutRecId);
        return dialog;
    }

    public static Dialog setDialog(Context context, int layoutRecId, int animRecId) {
        Dialog dialog = new Dialog(context, R.style.dialogStyle);
        dialog.setCancelable(true);// 不可以用“返回键”取消
        dialog.setCanceledOnTouchOutside(false);//设置外部不能取消
        dialog.setContentView(layoutRecId);
        Window window = dialog.getWindow();
        window.setWindowAnimations(animRecId);
        return dialog;
    }

    /**
     * 底部弹出的dialog
     *
     * @param context     上下文
     * @param layoutRecId dialog布局
     * @return
     */
    public static Dialog getButtonDialog(Context context, int layoutRecId, int distanceButton) {
        Dialog dialog = new Dialog(context, R.style.dialogStyle);
        dialog.setCancelable(true);// 不可以用“返回键”取消
        dialog.setContentView(layoutRecId);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        //设置dialog距离底部多高
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.y = distanceButton;
        window.setAttributes(lp);

        WindowManager.LayoutParams as = window.getAttributes();
        // 以下这两句是为了保证按钮可以水平满屏
        as.width = ViewGroup.LayoutParams.MATCH_PARENT;
        as.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        return dialog;
    }

    /**
     * 底部弹出的dialog
     *
     * @param context        上下文
     * @param layoutRecId    dialog布局
     * @param distanceButton dialog距离底部
     * @param animResId      动画资源
     * @return dialog实例
     */
    public static Dialog getButtonDialog(Context context, int layoutRecId, int distanceButton, int animResId) {
        Dialog dialog = new Dialog(context, R.style.dialogStyle);
        dialog.setCancelable(true);// 不可以用“返回键”取消
        dialog.setContentView(layoutRecId);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        //dialog动画
        window.setWindowAnimations(R.style.DialogAnim1);
        //设置dialog距离底部多高
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.y = distanceButton;
        window.setAttributes(lp);

        WindowManager.LayoutParams as = window.getAttributes();
        // 以下这两句是为了保证按钮可以水平满屏
        as.width = ViewGroup.LayoutParams.MATCH_PARENT;
        as.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        return dialog;
    }

    /**
     * AlertDialog
     * 正常dialog弹出 例如提示 提醒等对话框
     *
     * @param context    上下文
     * @param layotRecId dialog布局
     * @return
     */
    public static Dialog getNormDialog(Context context, int layotRecId) {
        final Dialog dialog = new AlertDialog.Builder(context, R.style.dialogStyle).create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setContentView(layotRecId);
        window.setWindowAnimations(R.style.DialogAnim2);
        return dialog;
    }
}
