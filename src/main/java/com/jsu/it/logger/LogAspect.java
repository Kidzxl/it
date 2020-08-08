package com.jsu.it.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component

public class LogAspect {

//    Logger logger = LoggerFactory.getLogger(getClass());
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.jsu.it.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("请求地址: "+request.getRequestURL().toString());
        logger.info("请求方式 : " + request.getMethod());
        logger.info("请求IP : " + request.getRemoteAddr());
        logger.info("请求的类的方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("请求参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("请求返回值 : " + ret);
    }

    //后置异常通知
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp){

    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint jp){
    }
    
}