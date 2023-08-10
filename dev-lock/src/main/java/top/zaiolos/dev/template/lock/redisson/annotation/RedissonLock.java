package top.zaiolos.dev.template.lock.redisson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author zdk
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedissonLock {

    /**
     * key的前缀,默认取方法全限定名
     * @return key的前缀
     */
    String prefix() default "";

    /**
     * springEl 表达式
     * @return springEl表达式
     */
    String key();

    /**
     * 锁超时时间,默认-1,不等待直接失败,redisson默认也是-1
     * @return 时间
     */
    int timeout() default -1;

    /**
     * 等待锁的时间单位，默认毫秒
     * @return 单位
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;
}
