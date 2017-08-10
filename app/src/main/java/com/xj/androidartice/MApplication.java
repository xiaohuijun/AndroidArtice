package com.xj.androidartice;

import android.app.Application;
import android.content.Context;

import com.xj.androidartice.common.net.NetConfig;

/**
 * Created by Administrator on 2017/8/2.
 */

public class MApplication extends Application {
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        init();
        InitIntentService.startActionInit(context);
    }

    private void init() {
        NetConfig.initNetWorking(getApplicationContext());
    }


}
