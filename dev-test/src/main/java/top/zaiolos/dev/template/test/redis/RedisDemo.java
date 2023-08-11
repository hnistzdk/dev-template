package top.zaiolos.dev.template.test.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.zaiolos.dev.template.cache.redis.service.RedisService;
import top.zaiolos.dev.template.lock.redisson.service.RedissonService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Description
 * @Author zdk
 * @Date 2023/6/12 17:51
 */
@Service
public class RedisDemo {

    @Autowired(required = false)
    private RedisService redisService;

    @Resource
    private RedissonService redissonService;


    @PostConstruct
    public void exec(){
//        RedisTemplate redisTemplate = redisService.getRedisTemplate();
//        System.out.println("redisTemplate = " + redisTemplate);
//
//        System.out.println("redissonService = " + redissonService);
    }

}
