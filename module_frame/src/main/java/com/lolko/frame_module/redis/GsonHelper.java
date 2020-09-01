package com.lolko.frame_module.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * <h2>  </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年08月31日 16:40
 */
public class GsonHelper {

    private GsonHelper() {
        // no instance
    }

    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T toObject(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> List<T> toList(String json, Class<? extends T[]> clazz) {
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }
}