package com.xj.androidartice.common.net;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.GzipRequestInterceptor;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/8/2.
 */

public class NetConfig {

    public static void initNetWorking(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new GzipRequestInterceptor())
                .build();
        AndroidNetworking.initialize(context, okHttpClient);
        AndroidNetworking.enableLogging();
    }
}
