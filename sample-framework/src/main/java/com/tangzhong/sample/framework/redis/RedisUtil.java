package com.tangzhong.sample.framework.redis;

import com.tangzhong.sample.framework.core.util.SpringUtil;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author tangzhong
 * @date   2025-09-28 15:45
 * @since  V1.0.0
 */
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class RedisUtil {

    private RedisUtil(){}

    private static final RedisTemplate REDIS_TEMPLATE;
    static {
        REDIS_TEMPLATE = SpringUtil.getBean(RedisTemplate.class, "redisTemplate");
    }

    public static RedisTemplate getRedisTemplate(){
        return REDIS_TEMPLATE;
    }

    public static <T> void setValue(String key, T value) {
        REDIS_TEMPLATE.opsForValue().set(key, value);
    }

    public static <T> void setValue(String key, T value, Long timeout) {
        REDIS_TEMPLATE.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public static <T> T getValue(String key) {
        ValueOperations<String, T> valueOperations = REDIS_TEMPLATE.opsForValue();
        return valueOperations.get(key);
    }

    public static void delete(String key) {
        REDIS_TEMPLATE.delete(key);
    }

    public static boolean hasKey(String key) {
        return REDIS_TEMPLATE.hasKey(key);
    }

    public static <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            REDIS_TEMPLATE.opsForHash().putAll(key, dataMap);
        }
    }

    public static <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        REDIS_TEMPLATE.opsForHash().put(key, hKey, value);
    }

    public static <T> Map<String, T> getCacheMapValue(final String key) {
        Map<Object, Object> rawMap = REDIS_TEMPLATE.opsForHash().entries(key);
        return (Map<String, T>) (Map<?, ?>) rawMap;
    }

    public static <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = REDIS_TEMPLATE.opsForHash();
        return opsForHash.get(key, hKey);
    }

}