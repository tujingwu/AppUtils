package com.example.apputil.picture;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.apputil.R;
import com.example.apputil.baseactivity.BaseActivity;
import com.maplejaw.library.PhotoPickActivity;
import com.maplejaw.library.util.ImageLoader;
import com.maplejaw.library.util.PictureUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tujingwu on 2016/11/25.
 */
public class AlbumActivity extends BaseActivity {
    @Bind(R.id.gridView)
    GridView mGridView;
    private MyAdapter mMyAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_album;
    }

    private void initData() {
        mGridView.setAdapter(mMyAdapter = new MyAdapter(mList = new ArrayList<String>()));
    }

    public void btnClick(View view) {
        Intent intent = new Intent(this, PhotoPickActivity.class);
        switch (view.getId()) {
            case R.id.btn1:
                intent.putExtra(PhotoPickActivity.IS_SHOW_CAMERA, true);
                startActivityForResult(intent, 123);
                break;
            case R.id.btn2:
                intent.putExtra(PhotoPickActivity.MAX_PICK_COUNT, 6);
                startActivityForResult(intent, 1234);
                break;
            case R.id.btn3:
                intent.putExtra(PhotoPickActivity.MAX_PICK_COUNT, 9);
                intent.putExtra(PhotoPickActivity.IS_SHOW_CAMERA, true);
                startActivityForResult(intent, 1234);
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case 1234:
                List<String> list = data.getStringArrayListExtra(PhotoPickActivity.SELECT_PHOTO_LIST);
                //  PictureUtil.cropPhoto(this, Uri.parse("file://"+list.get(0)));
                mList.clear();
                mList.addAll(list);
                mMyAdapter.notifyDataSetChanged();
                break;
            case 123:
                List<String> list1 = data.getStringArrayListExtra(PhotoPickActivity.SELECT_PHOTO_LIST);
                PictureUtil.cropPhotoRetrunBitmap(this, Uri.parse("file://" + list1.get(0)), 100, 124);//系统自带的裁剪界面
                mList.clear();
                mList.addAll(list1);
                mMyAdapter.notifyDataSetChanged();
                break;

            /*case 124:
                Bitmap bitmap = data.getParcelableExtra("data");
                if (bitmap != null) {
                    mImageView.setImageBitmap(bitmap);
                }
            *//*    List<String> list1= data.getStringArrayListExtra(PhotoPickActivity.SELECT_PHOTO_LIST);
                PictureUtil.cropPhoto2(this, Uri.parse("file://"+list1.get(0)),100,124);
              *//**//*  mList.clear();
                mList.addAll(list);
                mMyAdapter.notifyDataSetChanged();*//*
                break;*/
        }
    }

    class MyAdapter extends BaseAdapter {
        private List<String> mList;

        public MyAdapter(List<String> list) {
            this.mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public String getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.album_item, parent, false);
                imageView = (ImageView) convertView.findViewById(R.id.ic_image_pick);
                convertView.setTag(imageView);
            } else {
                imageView = (ImageView) convertView.getTag();
            }
            ImageLoader.getInstance().loadImage(getItem(position), imageView);
            return convertView;
        }
    }
}
