package com.cby.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisClient {
    @Autowired
    private RedisTemplate redisTemplate;


    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(final String key ,final Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean zAdd(String key, Object value, double score) {
        try {
            redisTemplate.opsForZSet().add(key,value,score);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Set<Object> zRevRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().reverseRange(key, start, end);
        } catch (Exception e) {
            return new HashSet<>();
        }
    }

    public boolean set(final String key, final Object value, final long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void del(final String key) {
        redisTemplate.delete(key);
    }

    public void del(final Set<String> keys) {
        redisTemplate.delete(keys);
    }

    public Set<String> keys(final String pattern) {
        return pattern == null?null:redisTemplate.keys(pattern);
    }

    public Set<Object> zRange(final String key, final int start, final int end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }
}
