package com.lolko.frame_module.redis;

/**
 * <h2>  </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年08月31日 16:39
 */
public final class CacheService {

    private static final String USER_KEY = "CACHE_SERVICE_USER_KEY";

    private CacheService() {
    }

    private static CacheDao getRepository() {
        return DatabaseSession.get().mCacheDao();
    }

    /**
     * 设置缓存
     */
    public static void set(String key, String value) {
        CacheEntity cacheEntity;
        cacheEntity = getRepository().findByKey(key);
        if (cacheEntity == null) {
            cacheEntity = new CacheEntity();
            cacheEntity.setKey(key);
            cacheEntity.setValue(value);
            getRepository().insertCaches(cacheEntity);
        } else {
            cacheEntity.setValue(value);
            getRepository().updateCaches(cacheEntity);
        }
    }

    /**
     * 设置缓存
     */
    public static void set(String key, Object value) {
        CacheEntity cacheEntity = getRepository().findByKey(key);
        if (cacheEntity == null) {
            cacheEntity = new CacheEntity();
            cacheEntity.setKey(key);
            String jsonValue = GsonHelper.toJson(value);
            cacheEntity.setValue(jsonValue);
            getRepository().insertCaches(cacheEntity);
        } else {
            String jsonValue = GsonHelper.toJson(value);
            cacheEntity.setValue(jsonValue);
            getRepository().updateCaches(cacheEntity);
        }
    }

    public static <T> void set(T user) {
        CacheEntity cacheEntity = getRepository().findByKey(USER_KEY);
        if (cacheEntity == null) {
            cacheEntity = new CacheEntity();
            cacheEntity.setKey(USER_KEY);
            String jsonValue = GsonHelper.toJson(user);
            cacheEntity.setValue(jsonValue);
            getRepository().insertCaches(cacheEntity);
        } else {
            String jsonValue = GsonHelper.toJson(user);
            cacheEntity.setValue(jsonValue);
            getRepository().updateCaches(cacheEntity);
        }
    }

    /**
     * 获取缓存
     */
    public static String get(String key) {
        CacheEntity cacheEntity = getRepository().findByKey(key);
        if (cacheEntity == null) {
            return null;
        }
        return cacheEntity.getValue();
    }

    /**
     * 获取缓存对象
     */
    public static <T> T get(String key, Class<T> classOfT) {
        CacheEntity cacheEntity = getRepository().findByKey(key);
        if (cacheEntity == null) {
            return null;
        }
        String jsonValue = cacheEntity.getValue();
        return GsonHelper.toObject(jsonValue, classOfT);
    }

    /**
     * 获取缓存对象
     */
    public static <T> T get(Class<T> classOfT) {
        CacheEntity cacheEntity = getRepository().findByKey(USER_KEY);
        if (cacheEntity == null) {
            return null;
        }
        String jsonValue = cacheEntity.getValue();
        return GsonHelper.toObject(jsonValue, classOfT);
    }

    /**
     * 删除缓存
     */
    public static void delete(String key) {
        CacheEntity cacheEntity = getRepository().findByKey(key);
        if (cacheEntity != null) {
            getRepository().deleteCaches(cacheEntity);
        }
    }

    /**
     * 删除缓存
     */
    public static void delete() {
        CacheEntity cacheEntity = getRepository().findByKey(USER_KEY);
        if (cacheEntity != null) {
            getRepository().deleteCaches(cacheEntity);
        }
    }

    /**
     * 删除全部缓存
     */
    public static void clearAll() {
        CacheEntity[] cacheEntities = getRepository().findAll();
        if (cacheEntities != null && cacheEntities.length != 0) {
            getRepository().deleteCaches(cacheEntities);
        }
    }
}