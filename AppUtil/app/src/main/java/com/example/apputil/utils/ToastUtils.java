package com.example.apputil.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by tujingwu on 2016/11/16.
 */
public class ToastUtils {
    private static Toast mToast;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    /**
     * 防止多次点击 安全的显示toast
     *
     * @param context
     * @param message
     */
    public static void show(final Context context, final String message) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(context,
                            message,
                            Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(message);
                }
                mToast.show();
            }
        });
    }

    /**
     * 防止多次点击 安全的显示toast
     *
     * @param context
     * @param message
     */
    public static void show(final Context context, final CharSequence message) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(context,
                            message,
                            Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(message);
                }
                mToast.show();
            }
        });
    }
}
