package com.xj.androidartice;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/8/7.
 */

public class InitIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_INIT = "com.xiaohuijun.administrator.mymvp.action.INIT";

    public InitIntentService() {
        super("InitIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionInit(Context context) {
        Intent intent = new Intent(context, InitIntentService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                handleActionInit();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionInit() {
        // TODO: Handle action init  这里进行一些三方库 sdk的初始化

    }

}
