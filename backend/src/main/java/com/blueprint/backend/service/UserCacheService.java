package com.blueprint.backend.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.blueprint.backend.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserCacheService {
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public UserCacheService(StringRedisTemplate redisTemplate, ObjectMapper objectMapper){
        this.redisTemplate =redisTemplate;
        this.objectMapper = objectMapper;
    }

    public void saveUser(Long id, Object user) throws Exception {
        String json = objectMapper.writeValueAsString(user);
        redisTemplate.opsForValue().set("user:"+id,json);
    }
    
    public User getUser(Long id) throws Exception {
        String json = redisTemplate.opsForValue().get("user:"+id);
        if(json == null){
            return null;
        }else{
            return objectMapper.readValue(json,User.class);
        }
    }

    public void deleteUser(Long id){
        redisTemplate.delete("user:"+id);
    }
}

