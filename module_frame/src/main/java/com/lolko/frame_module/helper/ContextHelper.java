package com.lolko.frame_module.helper;

import android.content.Context;

/**
 * <h2> 获取Context </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年07月27日 9:47
 */
@SuppressWarnings({"unused", "RedundantSuppression"})
public class ContextHelper {

    private static Context sAppContext;

    /**
     * 子模块和主模块需要共享全局上下文，故需要在app module初始化时传入
     */
    public static void init(Context appContext) {
        if (sAppContext == null) {
            sAppContext = appContext.getApplicationContext();
        }
    }

    public static Context getAppContext() {
        return sAppContext;
    }

}
