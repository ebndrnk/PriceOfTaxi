package com.ebndrnk.ShedulerYandexTaxi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class PriceRedisRepositoryTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void PriceRedisRepository_Save_ReturnSavedInfo(){
        // Arange
        String key = "testKey";
        String value = "100";

        //act

        redisTemplate.opsForValue().set(key, value);
        String valueAfterSaved = redisTemplate.opsForValue().get("testKey");

        //Assert
        assertThat(valueAfterSaved).isNotNull();
        assertThat(valueAfterSaved).isEqualTo(value);
    }
}
