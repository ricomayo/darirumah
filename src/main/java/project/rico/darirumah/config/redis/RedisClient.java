package project.rico.darirumah.config.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import project.rico.darirumah.config.AppConstant;

public class RedisClient {
/*
    @Autowired
    @Qualifier(AppConstant.REDIS_CONFIG_NAME)
    private RedisProperties redisProperties;

    @Bean(AppConstant.REDIS_CLIENT_NAME)
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisProperties.getREDIS_HOST(), redisProperties.getREDIS_PORT());
        redisStandaloneConfiguration.setPassword(redisProperties.getREDIS_PASSWORD());
        redisStandaloneConfiguration.setDatabase(redisProperties.getREDIS_DB_INDEX());

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

 */
}