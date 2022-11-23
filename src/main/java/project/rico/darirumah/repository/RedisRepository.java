package project.rico.darirumah.repository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.model.Checkpoint;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class RedisRepository {
    @Autowired
    @Qualifier(AppConstant.REDIS_TEMPLATE_NAME)
    private RedisTemplate<String, Checkpoint> redisTemplate;
    private ValueOperations<String, Checkpoint> dupkeyOps;
    private final TimeUnit LOCALE_TU =TimeUnit.SECONDS;

    @PostConstruct
    private void init() {
        dupkeyOps = redisTemplate.opsForValue();
    }

    public Checkpoint findKey(String key) {
        return dupkeyOps.get(key);
    }

    public void saveKey(Checkpoint data) {
        this.dupkeyOps.set(data.getKey(), data, data.getTimeToLive(), LOCALE_TU);
    }

    public void deleteKey(String key) {
        dupkeyOps.getOperations().delete(key);
    }
}