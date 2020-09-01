package com.lolko.frame_module.redis;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * <h2> 缓存表 </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年08月31日 16:37
 */
@Entity(tableName = "cache")
public class CacheEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String key;
    public String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}