package com.xj.core_lib.utils.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                    if (activityStack == null) {
                        activityStack = new Stack<Activity>();
                    }
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 从堆栈中移除Activity
     * @param activity
     */
    public void rmoveActivity(Activity activity){
        activityStack.removeElement(activity);
    }
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        if (activityStack.size() > 0)
            activityStack.clear();
    }

    /**
     * 除了什么之外，结束所有Activity
     */
    public void finishAllExcept(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)
                    && !activityStack.get(i).getClass().equals(cls)) {
                activityStack.get(i).finish();
            }
        }
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)
                    && !activityStack.get(i).getClass().equals(cls)) {
                activityStack.remove(i);
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp(Context context) {
        try {
            if (activityStack != null)
                finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }
}