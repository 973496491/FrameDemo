package com.lolko.frame_module.redis;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * <h2> 初始化 </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年08月31日 16:38
 */
@Database(entities = {CacheEntity.class}, version = 1, exportSchema = false)
public abstract class DatabaseConfig extends RoomDatabase {
    public abstract CacheDao mCacheDao();
}