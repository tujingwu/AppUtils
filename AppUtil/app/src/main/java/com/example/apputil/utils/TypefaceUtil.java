package com.example.apputil.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 设置字体样式 工具类
 * <p/>
 * Android中可显示文本的控件都直接或间接继承自TextView，
 * 批量替换字体的原理就是从指定的View节点开始递归遍历所有子View，
 * 如果子View类型是TextView类型或其子类型则替换字体，如果子View是ViewGroup类型则重复这一过程
 *
 *字体设置介绍http://blog.csdn.net/Gold_brick/article/details/52865369
 */
public class TypefaceUtil {

    /**
     * <p>Replace the font of specified view and it's children</p>
     *
     * @param root     The root view.
     * @param fontPath font file path relative to 'assets' directory.
     */
    public static void replaceFont(@NonNull View root, String fontPath) {
        if (TextUtils.isEmpty(fontPath)) {
            return;
        }


        if (root instanceof TextView) { // If view is TextView or it's subclass, replace it's font
            TextView textView = (TextView) root;
            int style = Typeface.NORMAL;
            if (textView.getTypeface() != null) {
                style = textView.getTypeface().getStyle();
            }
            textView.setTypeface(createTypeface(root.getContext(), fontPath), style);
        } else if (root instanceof ViewGroup) { // If view is ViewGroup, apply this method on it's child views
            ViewGroup viewGroup = (ViewGroup) root;
            for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                replaceFont(viewGroup.getChildAt(i), fontPath);
            }
        }
    }

    /**
     * <p>Replace the font of specified view and it's children</p>
     *
     * @param context  The view corresponding to the activity.
     * @param fontPath font file path relative to 'assets' directory.
     */
    public static void replaceFont(@NonNull Activity context, String fontPath) {
        replaceFont(getRootView(context), fontPath);
    }


    /*
     * Create a Typeface instance with your font file
     */
    public static Typeface createTypeface(Context context, String fontPath) {
        return Typeface.createFromAsset(context.getAssets(), fontPath);
    }

    /**
     * 从Activity 获取 rootView 根节点
     *
     * @param context
     * @return 当前activity布局的根节点
     */
    public static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }
}