package project.rico.darirumah.config.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("redis")
public class RedisProperties {

    private String REDIS_HOST = "localhost";
    private String REDIS_PASSWORD = "ssss";
    private int REDIS_PORT = 6379;
    private int REDIS_DB_INDEX = 0;
}