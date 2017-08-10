package com.xj.core_lib;


import com.xj.core_lib.event.BusProvider;
import com.xj.core_lib.router.Router;

/**
 * Created by wanglei on 2016/12/4.
 */

public class AppConfig {
    // #log
    public static final boolean LOG = true;
    public static final String LOG_TAG = "core_lib";

    // #cache
    public static final String CACHE_SP_NAME = "config";
    public static final String CACHE_DISK_DIR = "cache";

    // #router
    public static final int ROUTER_ANIM_ENTER = Router.RES_NONE;
    public static final int ROUTER_ANIM_EXIT = Router.RES_NONE;


    // #dev model
    public static final boolean DEV = true;

    //可以切换 rxbus和eventbus
    public static final BusProvider.BusType BUS_TYPE = BusProvider.BusType.EVENT_BUS;
}
