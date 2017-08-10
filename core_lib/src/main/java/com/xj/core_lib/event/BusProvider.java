package com.xj.core_lib.event;

import com.xj.core_lib.AppConfig;

import org.greenrobot.eventbus.EventBus;

import static com.xj.core_lib.event.BusProvider.BusType.EVENT_BUS;

/**
 * Created by wanglei on 2016/12/22.
 */

public class BusProvider {

    private static IBus bus;

    public static IBus getBus() {
        if (bus == null) {
            synchronized (BusProvider.class) {
                if (bus == null) {
                    bus = AppConfig.BUS_TYPE== EVENT_BUS?EventBusImpl.get():RxBusImpl.get();
                }
            }
        }
        return bus;
    }

    public enum BusType {
        RX_BUS,
        EVENT_BUS
    }

}
