package com.xj.androidartice.common.wedigt;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.xj.core_lib.log.XLog;

/**
 * Created by Administrator on 2017/8/7.
 */

public class DragViewLayout extends RelativeLayout {

    private ViewDragHelper dragHelper;
    private View autoBackView;
    private int autoBackViewX;
    private int autoBackViewY;
    private float visRatio = 0.3f;

    public DragViewLayout(Context context) {
        super(context);
        initDragHelper();
    }

    public DragViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDragHelper();
    }

    public DragViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDragHelper();
    }

    @TargetApi(21)
    public DragViewLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initDragHelper();
    }

    private void initDragHelper() {
        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelperCallBack());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true))
            invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        autoBackView.layout(0, (int) (getHeight() * (1 - visRatio)), getWidth(), (int) (getHeight() * (1 - visRatio)) + autoBackView.getMeasuredHeight());
        autoBackViewX = autoBackView.getLeft();
        autoBackViewY = autoBackView.getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        autoBackView = getChildAt(0);
    }

    class ViewDragHelperCallBack extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == autoBackView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            final int leftBound = getPaddingLeft();
            final int rightBound = getWidth() - child.getWidth() - leftBound;

            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

            return newLeft;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (releasedChild == autoBackView) {
                dragHelper.settleCapturedViewAt(autoBackViewX, releasedChild.getTop() < getHeight() / 2 ? 0 : autoBackViewY);
                invalidate();
            }
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return super.getViewHorizontalDragRange(child);
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return child == autoBackView ? child.getMeasuredHeight() : 0;
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

    }
}
