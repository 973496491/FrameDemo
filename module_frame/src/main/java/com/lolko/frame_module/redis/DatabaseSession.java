package com.lolko.frame_module.redis;

/**
 * <h2>  </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年08月31日 16:39
 */
public final class DatabaseSession {

    private DatabaseSession() {
    }

    public static DatabaseConfig get() {
        return DatabaseInitialize.getDatabaseConfig();
    }
}