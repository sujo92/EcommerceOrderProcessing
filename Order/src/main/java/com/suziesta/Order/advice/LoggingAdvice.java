package com.suziesta.Order.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value="execution( * com.suziesta.Order.controller.*.*(..))|| execution(* com.suziesta.Order.service.*.*(..)))")
    public void myPointcut(){
    }

    @Around("myPointcut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className =  pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        try {
            log.info("method invoked "+className+" : "+methodName+"()"+"arguments : "+mapper.writeValueAsString(array));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Object object = pjp.proceed();
        log.info(className+" : "+methodName+"()"+"response : "+mapper.writeValueAsString(object));
        return object;
    }
}
