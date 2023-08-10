package top.zaiolos.dev.template.lock.redisson.aspect;

import cn.hutool.core.util.StrUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;
import top.zaiolos.dev.template.lock.redisson.annotation.RedissonLock;
import top.zaiolos.dev.template.lock.redisson.service.RedissonService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Redisson分布式锁注解切面
 * @author zdk
 */
@Slf4j
@Component
@Aspect
@Order(0)
public class RedissonLockAspect {
    @Autowired
    private RedissonService redissonService;
    private static final ExpressionParser PARSER = new SpelExpressionParser();
    private static final DefaultParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    @Around("@annotation(top.zaiolos.dev.template.lock.redisson.annotation.RedissonLock)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);
        // 未指定prefix就用方法全限定名
        String prefix = StrUtil.isBlank(redissonLock.prefix()) ? getMethodKey(method) : redissonLock.prefix();
        // 传入了key就解析spel表达式
        String key = parseSpEl(method, joinPoint.getArgs(), redissonLock.key());
        return redissonService.executeWithLockThrows(prefix + ":" + key, redissonLock.timeout(), redissonLock.unit(), joinPoint::proceed);
    }

    private String parseSpEl(Method method, Object[] args, String spEl) {
        //解析参数名
        String[] params = Optional.ofNullable(PARAMETER_NAME_DISCOVERER.getParameterNames(method)).orElse(new String[]{});
        //el解析需要的上下文对象
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);
        }
        Expression expression = PARSER.parseExpression(spEl);
        return expression.getValue(context, String.class);
    }

    public String getMethodKey(Method method) {
        return method.getDeclaringClass() + "#" + method.getName();
    }


}
