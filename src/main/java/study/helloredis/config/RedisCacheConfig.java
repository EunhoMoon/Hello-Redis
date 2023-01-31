package study.helloredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;

import static java.time.Duration.ofSeconds;
import static org.springframework.data.redis.cache.CacheKeyPrefix.simple;
import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(ofSeconds(10))    // 기본 TTL
                .computePrefixWith(simple())
                .serializeKeysWith(
                        SerializationPair.fromSerializer(new StringRedisSerializer())
                );

        HashMap<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put(
                "userAgeCache",
                defaultCacheConfig().entryTtl(Duration.ofSeconds(5))    // 특정 캐시에 대한 TTL
        );

        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .cacheDefaults(config)
                .withInitialCacheConfigurations(configMap)
                .build();
    }

}
