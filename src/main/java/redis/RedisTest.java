package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisTest {
    @Test
    public void testConnection() {
        //获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //存入字符串
        jedis.set("username", "zs");
        //关闭连接
        jedis.close();
    }

    @Test
    public void getString() {
        //如果使用空参构造，默认值 "localhost",6379端口
        Jedis jedis = new Jedis();
        String username = jedis.get("username");
        //setex方法可以设置值并且指定过期时间
        //10s后会自动删除该键值对
        jedis.setex("code", 10, "1234");
        System.out.println(username);
        jedis.close();
    }

    @Test
    public void operateHash() {
        Jedis jedis = new Jedis();
        //存值
        jedis.hset("map", "name", "ls");
        jedis.hset("map", "age", "20");
        //取值
        String name = jedis.hget("map", "name");
        System.out.println("hash-->" + name);

        //
        Map<String, String> map = jedis.hgetAll("map");
        Set<String> set = map.keySet();
        for (String key : set) {
            String value = map.get(key);
            System.out.println("value-->" + value);
        }
        jedis.close();
    }

    @Test
    public void operateList() {
        Jedis jedis = new Jedis();
        jedis.del("list");
        //存
        jedis.lpush("list", "a");
        jedis.rpush("list", "b", "c");

        //范围获取
        List<String> values = jedis.lrange("list", 0, -1);
        for (int i = 0; i < values.size(); i++) {
            System.out.println("list-->" + values.get(i));
        }

        String lValue = jedis.lpop("list");
        System.out.println("lPop-->" + lValue);

        String rValue = jedis.rpop("list");
        System.out.println("rPop-->" + rValue);

        for (int i = 0; i < jedis.lrange("list", 0, -1).size(); i++) {
            System.out.println("list-2->" + values.get(i));
        }
        jedis.close();
    }


    @Test
    public void operateSet() {
        //不允许重复
        Jedis jedis = new Jedis();
        jedis.sadd("set", "java", "php", "c++");
        Set<String> set = jedis.smembers("set");
        System.out.println(set);
        jedis.close();
    }

    @Test
    public void operateSortSet() {
        Jedis jedis = new Jedis();
        jedis.zadd("sort", 10, "亚瑟");
        jedis.zadd("sort", 5, "后裔");
        jedis.zadd("sort", 20, "随悟空");

        Set<String> sortSet = jedis.zrange("sort", 0, -1);
        System.out.println(sortSet);
        jedis.close();
    }

    @Test
    public void testPool() {
        //创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);

        //创建pool对象
        JedisPool jedisPool = new JedisPool(config);
        //获取连接
        Jedis jedis = jedisPool.getResource();
        jedis.set("user", "ww");
        //关闭连接，归还到连接池中
        jedis.close();
    }
}
