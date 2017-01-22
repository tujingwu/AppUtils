package com.example.apputil.utils.listview_adapter.simple;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apputil.R;
import com.example.apputil.utils.listview_adapter.MultiItemCommonAdapter;
import com.example.apputil.utils.listview_adapter.MultiItemTypeSupport;
import com.example.apputil.utils.listview_adapter.ViewHolder;
import com.example.apputil.utils.listview_adapter.simple.bean.Bean;

import java.util.List;

/**
 * Created by tujingwu on 2017/1/11.
 * listivew多视图封装的适配器使用例子
 */
public class MoreItemAdapter extends MultiItemCommonAdapter<Bean> {

    public MoreItemAdapter(Context context, List<Bean> datas) {
        super(context, datas, new MultiItemTypeSupport<Bean>() {
            @Override
            public int getLayoutId(int position, Bean bean) {//返回布局
                if (bean.getItemType() == 0)
                    return R.layout.item1;
                else if (bean.getItemType() == 1)
                    return R.layout.item2;

                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override//返回的类型 数字只能 从0开始 不然 会报数字越界异常
            public int getItemViewType(int postion, Bean bean) {//返回视图类型标识
                if (bean.getItemType() == 0)
                    return 0;
                else if (bean.getItemType() == 1)
                    return 1;

                return -1;
            }
        });
    }

    @Override
    public void convert(ViewHolder holder, Bean bean) {
        switch (holder.getLayoutId()) {
            case R.layout.item1:
                ((TextView) (holder.getView(R.id.tv))).setText(bean.getMsg());
                break;
            case R.layout.item2:
                ((ImageView) (holder.getView(R.id.img))).setImageResource(R.mipmap.delete);
                break;
        }
    }
}
