package com.xj.androidartice.business.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.view.View;

import com.xj.androidartice.R;
import com.xj.androidartice.business.home.prestener.HomePrestener;
import com.xj.androidartice.common.base.BaseActivity;
import com.xj.androidartice.common.bean.User;
import com.xj.androidartice.common.database.AppDataBase;
import com.xj.androidartice.common.database.UserDao;
import com.xj.ui_lib.vasSonic.BrowserActivity;
import com.xj.ui_lib.vasSonic.SonicJavaScriptInterface;
import com.xj.ui_lib.vasSonic.SonicModle;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.completable.CompletableFromAction;
import io.reactivex.schedulers.Schedulers;

import static com.xj.androidartice.R.id.tv_get;
import static com.xj.androidartice.R.id.tv_update;

/**
 * Created by Administrator on 2017/8/3.
 */

public class HomeActivity extends BaseActivity<HomePrestener> {
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private UserDao userDao;

    @Override
    public void initView() {

    }

    @Override
    public void initMData(Bundle savedInstanceState) {
        userDao = AppDataBase.getDatabase(context).getUserDao();
    }


    @Override
    public int getMLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public HomePrestener newMP() {
        return new HomePrestener();
    }

    @Override
    public void setViewListener() {
        findViewById(R.id.tv_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BrowserActivity.class);
                intent.putExtra(BrowserActivity.PARAM_URL, "http://www.baidu.com");
                intent.putExtra(BrowserActivity.PARAM_MODE, SonicModle.MODE_SONIC_WITH_OFFLINE_CACHE);
                intent.putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis());
                startActivityForResult(intent, -1);
            }
        });

        findViewById(R.id.tv_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ShareCompat.IntentBuilder.from(context)
                        .setType("text/plain")
                        .setText("文字分享")
                        .getIntent();
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });

        findViewById(R.id.tv_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User(1, "aaa", "18", "0");
                mDisposable.add(new CompletableFromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                if (userDao != null)
                                    userDao.addUser(user);
                            }
                        }).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action() {
                                    @Override
                                    public void run() throws Exception {
                                        showToast("add_user");
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(@NonNull Throwable throwable) throws Exception {
                                        showToast("add_user_fail");
                                    }
                                })
                );
            }
        });


        findViewById(tv_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDisposable.add(userDao.getRxUserByName("张三").subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(@NonNull User user) throws Exception {
                                showToast(user.toString());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                showToast("get_user_fail");
                            }
                        }));
            }
        });

        findViewById(tv_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User(1, "aaa", "19", "0");
                mDisposable.add(userDao.addUser(user).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(@NonNull Integer integer) throws Exception {
                                showToast(user.toString());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                showToast("update_user_fail");
                            }
                        }));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.clear();
    }
}
