package com.xj.core_lib.event;

/**
 * Created by Administrator on 2017/8/4.
 */

public class CoustomEvent implements IBus.IEvent {
    private int event;
    private Object data;
    private Object data1;

    public CoustomEvent() {
    }

    public CoustomEvent(int event) {
        this.event = event;
    }

    public CoustomEvent(int event, Object data) {
        this.event = event;
        this.data = data;
    }

    public CoustomEvent(int event, Object data, Object data1) {
        this.event = event;
        this.data = data;
        this.data1 = data1;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setData1(Object data1) {
        this.data1 = data1;
    }

    @Override
    public int getEvent() {
        return event;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public Object getData1() {
        return data1;
    }

    @Override
    public String toString() {
        return "CoustomEvent{" +
                "event=" + event +
                ", data=" + data +
                ", data1=" + data1 +
                '}';
    }
}
