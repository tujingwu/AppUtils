package com.example.apputil.utils.listview_adapter.simple;

import android.content.Context;
import android.widget.TextView;

import com.example.apputil.R;
import com.example.apputil.utils.listview_adapter.CommonAdapter;
import com.example.apputil.utils.listview_adapter.ViewHolder;

import java.util.List;

/**
 * Created by tujingwu on 2017/1/11.
 * listview封装的适配器 单个视图适配器使用例子
 */
public class SigleItemAdapter extends CommonAdapter<String> {

    /**
     * @param context
     * @param datas
     * @param //layoutId
     */
    public SigleItemAdapter(Context context, List<String> datas) {
        super(context, datas, R.layout.item1);
    }

    @Override
    public void convert(ViewHolder holder, String s) {
        ((TextView) (holder.getView(R.id.tv))).setText(s);
    }
}
