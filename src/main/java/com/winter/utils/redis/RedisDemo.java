package com.winter.utils.redis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

public class RedisDemo {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(1000);//最大空闲时间
        config.setMaxWaitMillis(1000); //最大等待时间
        config.setMaxTotal(500); //redis池中最大对象个数
        JedisShardInfo sharInfo = new JedisShardInfo("101.132.191.77", 6379);
        sharInfo.setPassword("123456");
        Jedis jedis = new Jedis(sharInfo);
        String result = jedis.set("name", "zhangsan");
        System.out.println(result);
        String name = jedis.get("name");
        System.out.println(name);


        List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
        list.add(sharInfo);
        ShardedJedisPool pool = new ShardedJedisPool(config, list);
        ShardedJedis jedis2 = pool.getResource();
        jedis2.set("haha", "测试");
        System.out.println(jedis2.get("haha"));



        jedis.close();

    }

}
