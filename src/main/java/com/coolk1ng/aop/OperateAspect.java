package com.coolk1ng.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author coolk1ng
 * @since 2022/12/30 21:54
 */
@Slf4j
@Component
@Aspect
public class OperateAspect {
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));

    @Pointcut("@annotation(com.coolk1ng.aop.RecordOperate)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        threadPoolExecutor.execute(() -> {
            try {
                //方法签名
                MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
                RecordOperate annotation = methodSignature.getMethod().getAnnotation(RecordOperate.class);

                Class<? extends Convert> convert = annotation.convert();
                Convert logConvert = convert.newInstance();
                OperateLogDO operateLogDO = logConvert.convert(proceedingJoinPoint.getArgs()[0]);
                operateLogDO.setDesc(annotation.desc());
                operateLogDO.setResult(result.toString());

                log.info("insert operateLog " + JSON.toJSONString(operateLogDO));
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }
}
