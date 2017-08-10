package com.xj.androidartice.common.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xj.androidartice.R;
import com.xj.core_lib.event.BusProvider;
import com.xj.core_lib.event.CoustomEvent;
import com.xj.core_lib.event.IBus;
import com.xj.core_lib.mvp.XActivity;
import com.xj.core_lib.mvp.XPresent;
import com.xj.core_lib.toastcompat.ToastCompat;
import com.xj.core_lib.utils.util.AppManager;

/**
 * Created by Administrator on 2017/8/4.
 */

public abstract class BaseActivity<p extends XPresent> extends XActivity implements View.OnClickListener {
    public IBus eventBus;
    public CoustomEvent event;


    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initMData(savedInstanceState);
        setViewListener();
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
    public p newP() {
        return newMP();
    }

    @Override
    public void finish() {
        super.finish();
        AppManager.getAppManager().rmoveActivity(this);
    }

    public void showToast(int resId) {
        ToastCompat.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show();
    }

    public void showToast(String resId) {
        ToastCompat.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public Toolbar initToolBar(String title) {

        Toolbar toolbar = initToolBar();
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        return toolbar;
    }

    public Toolbar initToolBar(int title) {
        Toolbar toolbar = initToolBar();
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        return toolbar;
    }

    public Toolbar initToolBar(String title, int imgRight) {
        Toolbar toolbar = initToolBar();
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        ImageView toolbar_right = (ImageView) toolbar.findViewById(R.id.toolbar_right);
        toolbar_right.setImageResource(imgRight);
        toolbar_right.setVisibility(View.VISIBLE);
        return toolbar;
    }


    public Toolbar initToolBar(String title, View viewRight) {
        Toolbar toolbar = initToolBar();
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        LinearLayout toolbar_right_layout = (LinearLayout) toolbar.findViewById(R.id.toolbar_right_layout);
        if (toolbar_right_layout.getChildCount() > 0) {
            toolbar_right_layout.removeAllViews();
        }
        toolbar_right_layout.addView(viewRight);
        toolbar_right_layout.setVisibility(View.VISIBLE);
        return toolbar;
    }


    public Toolbar initToolBar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }

    public <V extends View> V $(V v, int id) {
        return (V) findViewById(id);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar_back) {
            finish();
        }
    }

    public abstract void initView();

    public abstract void initMData(Bundle savedInstanceState);

    public abstract int getMLayoutId();

    public abstract p newMP();

    public abstract void setViewListener();
}
