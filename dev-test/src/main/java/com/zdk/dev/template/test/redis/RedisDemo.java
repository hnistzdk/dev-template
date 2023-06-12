package com.zdk.dev.template.test.redis;

import com.zdk.dev.template.lock.redisson.service.RedissonService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Description
 * @Author zdk
 * @Date 2023/6/12 17:51
 */
@Service
public class RedisDemo {

//    @Resource
//    private RedisService redisService;

    @Resource
    private RedissonService redissonService;


    @PostConstruct
    public void exec(){
//        RedisTemplate redisTemplate = redisService.getRedisTemplate();
//        System.out.println("redisTemplate = " + redisTemplate);
//
        System.out.println("redissonService = " + redissonService);
    }

}
