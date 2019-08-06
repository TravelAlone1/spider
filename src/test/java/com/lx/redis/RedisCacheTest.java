package com.lx.redis;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lx
 * @Date: 2019/7/28 0028 上午 11:21
 */
public class RedisCacheTest {


    RedisCache redisCache=new RedisCache("127.0.0.1",6379);

    @Test
    public void test(){
        //redisCache.set("name","admin");
        //System.out.println( redisCache.set("name","lx"));


        /*Map<String,String> hash = new HashMap<String, String>();
        hash.put("username","aaa");
        hash.put("password","bbb");
        System.out.println(redisCache.hmSet("user:1",hash));*/

        //redisCache.lpush("run",new String[]{"aaa","bbb"});
        //System.out.println(redisCache.lpush("run",new String[]{"aaa","bbb"}));
        redisCache.expire("user:1",5);
    }

    @Test
    public void Test3(){

        System.out.println(100%3);
        System.out.println(100%3.0);


        short s =199;
        int i=s;
        System.out.println(i);


    }

}
