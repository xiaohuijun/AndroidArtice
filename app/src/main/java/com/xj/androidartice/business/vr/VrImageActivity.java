package com.xj.androidartice.business.vr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.xj.androidartice.R;
import com.xj.androidartice.common.base.BaseActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/8/10.
 */

public class VrImageActivity extends BaseActivity<VrImagePresenter> {
    VrPanoramaView vrPanoramaView;

    ImageTask imageTask;

    @Override
    public void initView() {
        vrPanoramaView = $(vrPanoramaView, R.id.vr_pview);
        //2.2.设置初始化参数
        vrPanoramaView.setDisplayMode(VrWidgetView.DisplayMode.EMBEDDED);
        //删除不需要连接，信息图标
        //vrPanoramaView.setInfoButtonEnabled(true);
        //隐藏全屏按钮
        //vrPanoramaView.setFullscreenButtonEnabled(true);
    }

    @Override
    public void initMData(Bundle savedInstanceState) {
        imageTask = new ImageTask();
        imageTask.execute();
    }

    @Override
    public int getMLayoutId() {
        return R.layout.activity_vr_image;
    }

    @Override
    public VrImagePresenter newMP() {
        return new VrImagePresenter();
    }

    @Override
    public void setViewListener() {

    }

    private VrPanoramaEventListener listener;

    private class ImageTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... params) {
            //2.4.从资产目录打开一个流
            try {
                InputStream inputStream = getAssets().open("andes.jpg");
                //2.5.使用BitmapFactory转换成Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //2.6任务执行完后,可获取Bitmap图片
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                //loadImageFromBitmap加载bitmap到显示控件 参1.bitmap 参2 显示参数的封装
                VrPanoramaView.Options options = new VrPanoramaView.Options();
                //加载立体图片，上部分显示在左眼，下部分显示在右眼
                options.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
                if (listener == null) {
                    listener = new VrPanoramaEventListener() {

                        @Override
                        public void onDisplayModeChanged(int newDisplayMode) {
                            super.onDisplayModeChanged(newDisplayMode);
                        }

                        @Override
                        public void onLoadError(String errorMessage) {
                            super.onLoadError(errorMessage);
                            //处理加载失败的情况
                            Toast.makeText(context, "错误消息:" + errorMessage, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLoadSuccess() {
                            super.onLoadSuccess();
                            //成功的情况提示下现在要进行全景图片的展示
                            Toast.makeText(context, "进入vr:", Toast.LENGTH_SHORT).show();
                        }
                    };
                    // 增加加载出错的业务逻辑处理
                    vrPanoramaView.setEventListener(listener);
                }
                //2.7.让控件加载bitmap对象
                vrPanoramaView.loadImageFromBitmap(bitmap, options);
                //2.8.如果loadImageFromBitmap加载失败需要提示用户相关信息则需要添加事件监听器listener
            }
        }
    }

    //步骤三。VrPanoramaView控件退到后台，回到屏幕，销毁处理细节
    //3.1.退到后台.暂停显示
    @Override
    protected void onPause() {
        super.onPause();
        if (vrPanoramaView != null) {
            vrPanoramaView.pauseRendering();
        }
    }

    //3.2.回到屏幕,恢复显示
    @Override
    protected void onResume() {
        super.onResume();
        if (vrPanoramaView != null) {
            vrPanoramaView.resumeRendering();
        }
    }

    //3.3.退出界面停止显示
    @Override
    protected void onDestroy() {
        if (vrPanoramaView != null) {
            vrPanoramaView.shutdown();
        }
        if (imageTask != null && !imageTask.isCancelled()) {//销毁任务
            imageTask.cancel(true);
            imageTask = null;
        }
        super.onDestroy();
    }
}
