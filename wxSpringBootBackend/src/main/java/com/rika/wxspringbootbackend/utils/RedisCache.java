package com.rika.wxspringbootbackend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 *
 */
@Component
public class RedisCache {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 给指定的 key 添加过期时间
     *
     * @param key 键
     * @param time 时间（秒）
     * @return boolean
     */
    public boolean expire(String key, long time) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, time, TimeUnit.SECONDS));
    }

    /**
     * 获取 key 的过期时间
     *
     * @return Long
     */
    public Long getTime(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 查询指定 key 是否存在
     *
     * @param key 键
     * @return boolean
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 根据 key 取值
     *
     * @param key 键
     * @return value
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 将值放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将值放入缓存并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间（秒） -1为无期限
     */
    public void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 删除 redis 中指定键
     *
     * @param key 键
     * @return boolean
     */
    public boolean del(String key) {
        if (hasKey(key)) {
            return Boolean.TRUE.equals(redisTemplate.delete(key));
        }
        return true;
    }

    // ...
    // 更多操作封装省略

}
