package com.ebndrnk.ShedulerYandexTaxi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
public class PriceRedisRepositoryTests {

    @Container
    public static GenericContainer redis = new GenericContainer("redis:latest")
            .withExposedPorts(6379);
    @Autowired
    private StringRedisTemplate redisTemplate;

    @BeforeAll
    static void setUp() {
        redis.start();
    }

    @AfterAll
    static void tearDown() {
        redis.stop();
    }

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

    @Test
    public void PriceRedisRepository_Save_ReturnLast(){
        // Arange
        String key = "testKey";
        String value = "100";

        String key2 = "testKey";
        String value2 = "200";

        String key3 = "testKey";
        String value3 = "300";

        //act

        redisTemplate.opsForValue().set(key, value);
        redisTemplate.opsForValue().set(key2, value2);
        redisTemplate.opsForValue().set(key3, value3);
        String valueAfterSaved = redisTemplate.opsForValue().get("testKey");

        //Assert
        assertThat(valueAfterSaved).isNotNull();
        assertThat(valueAfterSaved).isEqualTo(value3);
    }
}
