package com.cby.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisClient {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
