package com.shimh.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author linguochao
 * @Description TODO
 * @Date 2020/1/22 13:02
 */
@Component
public class RedisClient {
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 写入缓存
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }



    /**
     * 设置值和过期时间
     *
     * @param key
     * @param seconds 过期时间
     * @param value
     */
    public void setex(String key, int seconds, String value) {
        redisTemplate.opsForValue().set(key, value,seconds, TimeUnit.SECONDS );
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }


    /**
     * 获取缓存
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     *  可模糊后去KEY 列表
     *
     * @param key
     * @return
     */
    public Set<String> keys(String key) {
        return redisTemplate.keys(key);
    }

    /**
     * 删除缓存
     * @param key
     * @return
     */
    public boolean del(String key) {
        redisTemplate.delete(key);
        return true;
    }


    /**
     * 获取栈的第一个元素
     *
     * @param key
     * @return
     */
    public String lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }


    /**
     * 入栈
     *
     * @param key
     * @return
     */
    public Long lpush(String key,String value) {
        return redisTemplate.opsForList().leftPush(key,value);
    }

    /**
     * 递减操作
     * @param key
     * @return
     */
    public  Long decr(String key){
        return redisTemplate.opsForValue().increment(key, -1);
    }

    /**
     * 递增操作
     * @param key
     * @return
     */
    public  Long incr(String key){
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 如果key空则设值，否则忽略
     * @param key
     * @param value
     */
    public void setnx(String key, String value)  {
        redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 设值失效时间(秒)
     * @param key
     * @return
     */
    public void expire(String key, int seconds){
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }
}
