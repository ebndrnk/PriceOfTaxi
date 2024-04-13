package com.ebndrnk.ShedulerYandexTaxi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PriceRedisRepository {
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public PriceRedisRepository(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getActualPrice(){
        return redisTemplate.opsForValue().get("price");
    }


}
