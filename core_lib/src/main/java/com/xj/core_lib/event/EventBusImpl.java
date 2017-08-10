package com.xj.core_lib.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/8/4.
 */

public class EventBusImpl implements IBus {
    private EventBus eventBus;

    public EventBusImpl() {
        eventBus = EventBus.getDefault();
    }

    @Override
    public void register(Object object) {
        if (eventBus != null) eventBus.register(object);
    }

    @Override
    public void unregister(Object object) {
        if (eventBus != null) eventBus.unregister(object);
    }

    @Override
    public void post(IEvent event) {
        if (eventBus != null)
            eventBus.post(event);
    }

    @Override
    public void postSticky(IEvent event) {
        if (eventBus != null)
            eventBus.postSticky(event);
    }

    public static IBus get() {
        return Holder.instance;
    }

    private static class Holder {
        private static final EventBusImpl instance = new EventBusImpl();
    }
}
