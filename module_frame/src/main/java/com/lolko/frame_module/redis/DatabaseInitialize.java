package com.lolko.frame_module.redis;

import android.content.Context;

import androidx.room.Room;

/**
 * <h2>  </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年08月31日 16:38
 */
public class DatabaseInitialize {

    private static final String NAME = "lolko";
    private static DatabaseConfig databaseConfig;

    public DatabaseInitialize() {
    }

    public static void init(Context context) {
        // 生成数据库实例
        databaseConfig = Room
                .databaseBuilder(context.getApplicationContext(), DatabaseConfig.class, NAME)
                .allowMainThreadQueries() // 允许主线程中查询
                .build();
    }

    static DatabaseConfig getDatabaseConfig() {
        if (databaseConfig == null) {
            throw new NullPointerException("DatabaseInitialize.init(context) has not call, remember call this function in your Application.class");
        }
        return databaseConfig;
    }
}