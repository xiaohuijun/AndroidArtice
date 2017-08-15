package com.xj.androidartice.common.base;

import android.os.Bundle;

import com.xj.core_lib.event.BusProvider;
import com.xj.core_lib.event.CoustomEvent;
import com.xj.core_lib.event.IBus;
import com.xj.core_lib.mvp.XLazyFragment;
import com.xj.core_lib.mvp.XPresent;

/**
 * Created by Administrator on 2017/8/4.
 */

public abstract class BaseLazyFragment<P extends XPresent> extends XLazyFragment<P> {
    public IBus eventBus;
    public CoustomEvent event;

    @Override
    public void initData(Bundle savedInstanceState) {
        initMData(savedInstanceState);
        if (useEventBus()) {
            eventBus = BusProvider.getBus();
            event = new CoustomEvent();
        }
    }

    @Override
    public int getLayoutId() {
        return getMLayoutId();
    }

    @Override
    public P newP() {
        return newMP();
    }

    public abstract void initMData(Bundle savedInstanceState);

    public abstract int getMLayoutId();

    public abstract P newMP();
}
