package com.lolko.frame_module.redis;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * <h2>  </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年08月31日 16:39
 */
@Dao
public interface CacheDao {

    @Insert
    void insertCaches(CacheEntity... cacheEntities);

    @Update
    void updateCaches(CacheEntity... cacheEntities);

    @Query("SELECT * FROM cache WHERE `key` = :key LIMIT 0,1")
    CacheEntity findByKey(String key);

    @Delete
    void deleteCaches(CacheEntity... cacheEntities);

    @Query("SELECT * FROM cache")
    CacheEntity[] findAll();
}