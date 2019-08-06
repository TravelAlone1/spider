package com.lx.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * @Author: lx
 * @Date: 2019/7/28 0028 上午 11:05
 */
public class RedisCache {


    public RedisCache(String addr, int port) {
        this.addr = addr;
        this.port = port;
    }

    private String addr;

    private int port;


    public Jedis getJedis() {
        return new Jedis(addr, port);

    }

    public Long expire(String var1, int var2){
        Jedis jedis = new Jedis();
        return jedis.expire(var1,var2);
    }

    public String set(String key,String value){
        Jedis jedis = getJedis();
        return jedis.set(key,value);
    }

    public String hmSet(String key, Map<String,String> hash){
        Jedis jedis = getJedis();
        return jedis.hmset(key,hash);

    }

    public Long hdel(String var1, String... var2){
        Jedis jedis = getJedis();
        return jedis.hdel(var1, var2);
    }

    public Long lpush(String var1, String... var2){
        Jedis jedis = getJedis();
        return jedis.lpush(var1,var2);
    }

    public Long rpush(String var1, String... var2){
        Jedis jedis = getJedis();
        return jedis.rpush(var1,var2);
    }

    public List<String> blpop(int var1, String... var2){
        Jedis jedis = getJedis();
        return jedis.blpop(var1, var2);
    }

    public List<String> brpop(int var1, String... var2){
        Jedis jedis = getJedis();
        return jedis.brpop(var1, var2);
    }

    public Long sadd(String var1, String... var2){
        Jedis jedis = getJedis();
        return jedis.sadd(var1,var2);
    }

    public Long srem(String var1, String... var2){
        Jedis jedis = getJedis();
        return jedis.srem(var1,var2);
    }

    public Long  zadd(String var1, double var2, String var4){
        Jedis jedis = getJedis();
        return jedis.zadd(var1,var2,var4);
    }

    public Long zrem(String var1, String... var2){
        Jedis jedis = getJedis();
        return jedis.zrem(var1, var2);
    }



    public String getAddr() {
        return addr;
    }

    public int getPort() {
        return port;
    }
}
