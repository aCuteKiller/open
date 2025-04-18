package bai.demo.searchThink.utils.redis;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils<V> {
    private RedisTemplate<String, V> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Resource
    public void setRedisTemplate(RedisTemplate<String, V> redisTemplate) {
        if (Objects.isNull(redisTemplate)) {
            logger.error("========================Redis初始化配置失败，请检查配置项========================");
        } else {
            this.redisTemplate = redisTemplate;
            logger.info("========================Redis初始化成功========================");
        }
    }

    /*forVal*/
    public void set(String key, V val) {
        redisTemplate.opsForValue().set(key, val);
    }

    public void set(String key, V val, Long timeout) {
        redisTemplate.opsForValue().set(key, val, timeout, TimeUnit.SECONDS);
    }

    public void increment(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    public void setExpires(String key, Long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public V get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /*forHash*/
    public void hSet(String key, String hKey, V val) {
        redisTemplate.opsForHash().put(key, hKey, val);
    }

    public void hSet(String key, String hKey, V val, Long timeout) {
        redisTemplate.opsForHash().put(key, hKey, val);
        redisTemplate.expire(key + "_" + hKey, timeout, TimeUnit.SECONDS);
    }

    public V hGet(String key, String hKey) {
        return (V) redisTemplate.opsForHash().get(key, hKey);
    }

    public void hRemove(String key, String hKey) {
        redisTemplate.opsForHash().delete(key, hKey);
    }

    public boolean hExists(String key, String hKey) {
        return redisTemplate.opsForHash().hasKey(key, hKey);
    }

    public Long hSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    public Long size(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    /*forAll*/
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    public void deleteAllKeys() {
        // 使用SCAN命令来迭代key
        Cursor<byte[]> cursor = redisTemplate.executeWithStickyConnection(connection ->
                connection.scan(ScanOptions.scanOptions().count(100).match("*").build()));

        while (cursor.hasNext()) {
            byte[] key = cursor.next();
            // 删除key
            redisTemplate.delete(new String(key));
        }

        // 关闭游标
        cursor.close();
    }

    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public Long getExpires(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

}
