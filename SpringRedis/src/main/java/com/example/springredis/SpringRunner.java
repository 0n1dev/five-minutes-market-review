package com.example.springredis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringRunner implements ApplicationRunner {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String key = "testtest";

        TestEntity test = new TestEntity();
        test.setId("Test2");
        test.setUsername("테스트");
        test.setAge(15);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(mapper.convertValue(test, Map.class));
        redisTemplate.opsForHash().put(key,"test2", json);
        String jsonResult = (String) redisTemplate.opsForHash().get(key,"test2");

        TestEntity obj = mapper.readValue(jsonResult, TestEntity.class);
        System.out.println(obj.getUsername());
    }
}
