package bai.demo.searchThink.utils.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig<V> {

    @Bean("redisTemplate")
    RedisTemplate<String, V> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, V> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());//设置key序列化
        template.setValueSerializer(RedisSerializer.json());//设置值序列化
        template.setHashKeySerializer(RedisSerializer.string());//设置hashKey序列化
        template.setHashValueSerializer(RedisSerializer.json());//设置hashValue序列化
        template.afterPropertiesSet();
        return template;
    }
}
