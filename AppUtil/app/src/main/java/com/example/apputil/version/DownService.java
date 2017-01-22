package com.example.apputil.version;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.apputil.network2.okgo.callback.JsonCallback;
import com.example.apputil.network2.okgo.module.BaseBean;
import com.example.apputil.network2.okgo.module.Login;
import com.example.apputil.utils.AppUtils;
import com.example.apputil.utils.FileUtils;
import com.example.apputil.utils.SDCardUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by tujingwu on 2016/12/29.
 */
public class DownService extends Service {
    private String sdCardPath = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //在这里后台去更新app
        // checkUpdate();
        return super.onStartCommand(intent, flags, startId);
    }

    //检察是否需要更新
    private void checkUpdate() {
        OkGo.post("check url")
                .tag(getApplication())
                .upJson("检查更新json字符串")
                .execute(new JsonCallback<BaseBean<Login>>() {
                    @Override
                    public void onSuccess(BaseBean<Login> beanBaseBean, Call call, Response response) {
                        //Log.e("ddd", "onSuccess:" + beanBaseBean.getResult().toString());
                        //判断是否需要更新app
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    //需要更新就后台下载app
    public void downApp() {
      /*  FileCallback():空参构造
        FileCallback(String destFileName):可以额外指定文件下载完成后的文件名
        FileCallback(String destFileDir, String destFileName):可以额外指定文件的下载目录和下载完成后的文件名*/
        //判断sd卡是否可用
        boolean sdCardEnable = SDCardUtils.isSDCardEnable();
        if (sdCardEnable) {
            //sdcard可用就获取sdcard路径存放app
            sdCardPath = SDCardUtils.getSDCardPath();
            //如果原来有旧的app就删除掉
            FileUtils.deleteFile(sdCardPath + "myApp");
            //下载需要更新的app
            OkGo.get("Urls.URL_DOWNLOAD")//
                    .tag(getApplication())//
                    .execute(new FileCallback(sdCardPath + "myApp", "myApp.app") {  //文件下载时，可以指定下载的文件目录和文件名
                        @Override
                        public void onSuccess(File file, Call call, Response response) {
                            // file 即为文件数据，文件保存在指定目录
                            //下载成功后安装app
                            AppUtils.installApp(getApplicationContext(), sdCardPath + "myApp");
                        }

                        @Override
                        public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                            //这里回调下载进度(该回调在主线程,可以直接更新ui)
                        }
                    });
        }

    }

}
