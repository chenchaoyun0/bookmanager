package com.sttx.bookmanager.web.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.pages.ThreadLocalContext;

public class ControllerAOP {
    @Autowired
    private ILogService logService;
    private static Logger log = Logger.getLogger(ControllerAOP.class);

    public Object aroundMethod(ProceedingJoinPoint joinpoint) {// ProceedingJoinPoint为通知
        Object obj = null;
        try {
            long start = System.currentTimeMillis();
            log.info("+++++controller方法开始...");
            obj = joinpoint.proceed();// 执行通知的方法
            long end = System.currentTimeMillis();
            long actionTime = end - start;
            log.info("++++++controller方法结束,执行时间为:" + (actionTime));
            ThreadLocalContext.getControllerexcutime().set(actionTime);
            return obj;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;

    }
}
