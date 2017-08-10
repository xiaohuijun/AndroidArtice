package com.xj.core_lib.imgloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * glide图片加载封装类
 */
public class ImageLoader {


    private static RequestOptions getRequestOptions(int resourceId, int width, int height, Transformation<Bitmap> transformation) {
        RequestOptions options = new RequestOptions()
                .placeholder(resourceId)
                .fallback(resourceId)
                .error(resourceId)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        //.diskCacheStrategy(DiskCacheStrategy.ALL)//设置缓存策略。DiskCacheStrategy.SOURCE：缓存原始数据，DiskCacheStrategy.RESULT：缓存变换(如缩放、裁剪等)后的资源数据，DiskCacheStrategy.NONE：什么都不缓存，DiskCacheStrategy.ALL：缓存SOURC和RESULT。默认采用DiskCacheStrategy.RESULT策略，对于download only操作要使用DiskCacheStrategy.SOURCE。
        //.priority(Priority.HIGH)//指定加载的优先级，优先级越高越优先加载，但不保证所有图片都按序加载。枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW。默认为Priority.NORMAL。
        //.dontAnimate()// 移除所有的动画。
        //.animate(int animationId)//在异步加载资源完成时会执行该动画。
        //.animate(ViewPropertyAnimation.Animator animator)//在异步加载资源完成时会执行该动画。
        //.placeholder(resourceId)//设置资源加载过程中的占位Drawable。
        //.placeholder(Drawable drawable)//设置资源加载过程中的占位Drawable。
        //.fallback(resourceId)//设置model为空时要显示的Drawable。如果没设置fallback，model为空时将显示error的Drawable，如果error的Drawable也没设置，就显示placeholder的Drawable。
        //.fallback(Drawable drawable)//设置model为空时显示的Drawable。
        //.error(resourceId)//设置load失败时显示的Drawable。
        //.error(Drawable drawable)//设置load失败时显示的Drawable。
        //.listener(RequestListener<? super ModelType, TranscodeType> requestListener)//监听资源加载的请求状态，可以使用两个回调：onResourceReady(R resource, T model, Target<R> target, boolean isFromMemoryCache, boolean isFirstResource)和onException(Exception e, T model, Target&lt;R&gt; target, boolean isFirstResource)，但不要每次请求都使用新的监听器，要避免不必要的内存申请，可以使用单例进行统一的异常监听和处理。
        //.skipMemoryCache(boolean skip)//设置是否跳过内存缓存，但不保证一定不被缓存（比如请求已经在加载资源且没设置跳过内存缓存，这个资源就会被缓存在内存中）。
        // .override(int width, int height)//重新设置Target的宽高值（单位为pixel）。
        // .into(Y target)//设置资源将被加载到的Target。
        // .into(ImageView view)//设置资源将被加载到的ImageView。取消该ImageView之前所有的加载并释放资源。
        // .into(int width, int height)// 后台线程加载时要加载资源的宽高值（单位为pixel）。
        // .preload(int width, int height)//预加载resource到缓存中（单位为pixel）。
        // .asBitmap()//无论资源是不是gif动画，都作为Bitmap对待。如果是gif动画会停在第一帧。
        // .asGif()//把资源作为GifDrawable对待。如果资源不是gif动画将会失败，会回调.error()。
        // .centerCrop()
        if (width != 0 && height != 0) options.override(width, height);
        if (transformation != null) options.transform(transformation);
        return options;
    }

    /**
     * Load Image
     *
     * @param context
     * @param url
     * @param view
     */
    public static void load(Context context, String url, ImageView view, int resourceId) {
        Glide.with(context)
                .load(url)
                .thumbnail(0.1f)//请求给定系数的缩略图
                .apply(getRequestOptions(resourceId, 0, 0, null))
                .into(view);

    }

    /**
     * Load Image
     *
     * @param context
     * @param uri
     * @param view
     */
    public static void load(Context context, Uri uri, ImageView view, int resourceId, int width, int height) {
        Glide.with(context)
                .load(uri)
                .thumbnail(0.1f)//请求给定系数的缩略图
                .apply(getRequestOptions(resourceId, width, height, null))
                .into(view);

    }

    /**
     * Load Image
     *
     * @param context
     * @param resouceId
     * @param view
     */
    public static void load(Context context, int resouceId, ImageView view) {
        Glide.with(context)
                .load(resouceId)
                .thumbnail(0.1f)//请求给定系数的缩略图
                .apply(getRequestOptions(resouceId, 0, 0, null))
                .into(view);

    }

    /**
     * @param activity
     * @param url
     * @param view
     * @param resourceId //占位图
     */
    public static void Load(Activity activity, String url, ImageView view, int resourceId) {
        Glide.with(activity)
                .load(url)
                .thumbnail(0.1f)//请求给定系数的缩略图

                .into(view);
    }

    /**
     * @param fragment
     * @param url
     * @param view
     * @param resourceId
     */
    public static void load(Fragment fragment, String url, ImageView view, int resourceId) {
        Glide.with(fragment)
                .load(url)
                .thumbnail(0.1f)//请求给定系数的缩略图
                .apply(getRequestOptions(resourceId, 0, 0, null))
                .into(view);
    }


    /**
     * 圆形裁剪
     *
     * @param context
     * @param url
     * @param view
     * @param resourceId
     */
    public static void loadCropCircle(Context context, String url, ImageView view, int resourceId) {
        Glide.with(context)
                .load(url)
                .thumbnail(0.1f)//请求给定系数的缩略图
                .apply(getRequestOptions(resourceId, 0, 0, new CropCircleTransformation(context)))
                .into(view);
    }

    /**
     * 圆角裁剪
     *
     * @param context
     * @param url
     * @param view
     * @param resourceId
     */
    public static void loadRoundedCorners(Context context, String url, ImageView view, int resourceId, int radius, int margin) {
        Glide.with(context)
                .load(url)
                .apply(getRequestOptions(resourceId, 0, 0, new RoundedCornersTransformation(context, radius, margin)))
                .into(view);
    }

    /**
     * 高斯模糊
     *
     * @param context
     * @param url
     * @param view
     * @param resourceId
     */
    public static void loadBlur(Context context, String url, ImageView view, int resourceId) {
        Glide.with(context)
                .load(url)
                .thumbnail(0.1f)//请求给定系数的缩略图
                .apply(getRequestOptions(resourceId, 0, 0, new BlurTransformation(context)))
                .into(view);
    }


    /**
     * 清除图片缓存
     *
     * @param context
     */
    public static void clearDiskCache(final Context context) {
        // // 必须在UI线程中调用
        Glide.get(context).clearMemory();
        // 必须在后台线程中调用，建议同时clearMemory()
        Glide.get(context).clearDiskCache();

    }
}
