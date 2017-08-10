package com.xj.core_lib.imgloader;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * @author: xiaohuijun
 * @date: 2016/8/2
 * @email: xiaohuijun1992@163.com
 */
@GlideModule
public class GlideConfiguration extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //builder.setMemoryCache(new LruResourceCache(10 * 1024 * 1024));
        //builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

}
