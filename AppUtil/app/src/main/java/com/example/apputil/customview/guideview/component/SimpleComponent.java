package com.example.apputil.customview.guideview.component;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apputil.R;
import com.example.apputil.customview.guideview.Component;


/**
 * Created by binIoter on 16/6/17.
 */
public class SimpleComponent implements Component {

    @Override
    public View getView(LayoutInflater inflater) {

        //这里的布局是添加提示的图片和文字也就是添加要提示的view
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.guide_hint1, null);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "引导层被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    /*LinearLayout ll = new LinearLayout(inflater.getContext());
    LinearLayout.LayoutParams param =
            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
    ll.setOrientation(LinearLayout.VERTICAL);
    ll.setLayoutParams(param);
    TextView textView = new TextView(inflater.getContext());
    textView.setText("~~弹出对话框~~摸我");
    textView.setTextColor(Color.WHITE);
    textView.setTextSize(20);
    ImageView imageView = new ImageView(inflater.getContext());
    imageView.setImageResource(R.mipmap.arrow);
    ll.removeAllViews();
    ll.addView(textView);
    ll.addView(imageView);
    ll.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Toast.makeText(view.getContext(), "引导层被点击了", Toast.LENGTH_SHORT).show();
      }
    });*/
        return ll;
    }

    @Override
    public int getAnchor() {
        return Component.ANCHOR_BOTTOM;
    }

    @Override
    public int getFitPosition() {
        return Component.FIT_END;
    }

    @Override
    public int getXOffset() {
        return 0;
    }

    @Override
    public int getYOffset() {
        return 10;
    }
}
