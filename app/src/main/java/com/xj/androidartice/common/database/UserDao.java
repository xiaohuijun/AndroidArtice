package com.xj.androidartice.common.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.xj.androidartice.common.bean.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2017/8/9.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    public List<User> getAllUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Flowable<Integer> addUser(User user);

    @Delete()
    public int deleteUser(User user);

    @Query("SELECT * FROM user WHERE name= :name")
    public User getUserByName(String name);

    @Query("SELECT * FROM user WHERE name= :name")
    public Flowable<User> getRxUserByName(String name);
}
