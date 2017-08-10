package com.xj.androidartice.common.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.xj.androidartice.common.bean.User;

/**
 * Created by Administrator on 2017/8/9.
 */
@Database(entities = {User.class}, version = 1, exportSchema = true)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase sInstance;

    public static AppDataBase getDatabase(Context context) {
        if (sInstance == null) {
            synchronized (AppDataBase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "user.db")
                            .build();
                }
            }
        }
        return sInstance;
    }

    public abstract UserDao getUserDao();

}
