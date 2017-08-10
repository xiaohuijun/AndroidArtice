package com.xj.core_lib.event;

/**
 * Created by wanglei on 2016/12/22.
 */

public interface IBus {

    void register(Object object);

    void unregister(Object object);

    void post(IEvent event);

    void postSticky(IEvent event);


    interface IEvent {
        int getEvent();

        Object getData();

        Object getData1();
    }

}
