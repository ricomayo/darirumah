package project.rico.darirumah.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.model.Checkpoint;

@Slf4j
@Configuration
public class RedisTemplateConfig {
/*
    @Bean(AppConstant.REDIS_TEMPLATE_NAME)
    public RedisTemplate<String, Checkpoint> redisTemplate(@Qualifier(AppConstant.REDIS_CLIENT_NAME) RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Checkpoint> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        RedisSerializer<Checkpoint> checkpointRedisSerializer = new Jackson2JsonRedisSerializer<>(Checkpoint.class);

        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(checkpointRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(checkpointRedisSerializer);
        log.info("Successfully created redis template");
        return template;
    }

    @Bean(AppConstant.REDIS_STRING_TEMPLATE_NAME)
    public RedisTemplate<String, String> redisStringTemplate(@Qualifier(AppConstant.REDIS_CLIENT_NAME) RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();

        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);
        log.info("Successfully created redis string template");
        return template;
    }
*/
}
