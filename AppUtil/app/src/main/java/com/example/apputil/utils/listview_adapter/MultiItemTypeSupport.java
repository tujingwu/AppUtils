package com.example.apputil.utils.listview_adapter;

/**
 * @param <T> listview多视图接口
 */
public interface MultiItemTypeSupport<T> {
    int getLayoutId(int position, T t);

    int getViewTypeCount();

    int getItemViewType(int postion, T t);
}