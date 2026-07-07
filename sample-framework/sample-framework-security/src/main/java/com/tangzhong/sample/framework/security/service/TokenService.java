package com.tangzhong.sample.framework.security.service;

import com.tangzhong.sample.framework.redis.util.RedisUtil;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 16:35
 * @since  V1.0.0
 */
@Component
public class TokenService {

    private static final String PREFIX = "login:token:";

    private static final long EXPIRE = 60 * 60 * 24;

    public void save(Long userId, String token) {
        RedisUtil.setValue(PREFIX + userId, token, EXPIRE);
    }

    public String get(Long userId) {
        return RedisUtil.getValue(PREFIX + userId);
    }

    public void remove(Long userId) {
        if(Objects.nonNull(userId)){
            RedisUtil.delete(PREFIX + userId);
        }
    }

    public boolean exists(Long userId) {
        return RedisUtil.hasKey(PREFIX + userId);
    }

}