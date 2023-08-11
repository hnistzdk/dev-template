package top.zaiolos.dev.template.lock.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

/**
 * @Description redisson自动配置 前提 redisson.enable=true
 * @Author zdk
 * @Date 2023/6/12 17:37
 */
@Configuration
//@ConditionalOnProperty(name = {"zaiolos.redis.enable","zaiolos.redisson.enable"},havingValue = "true")
@ConditionalOnProperty(name = {"spring.redis.redisson.file"},havingValue = "classpath:redisson.yaml")
public class RedissonConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Primary
    @Bean
    public RedissonClient getRedisson(){

        Config config = new Config();
        //单机模式
        config.useSingleServer()
                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setPassword(redisProperties.getPassword())
                .setDatabase(0);
        return Redisson.create(config);
    }
}
