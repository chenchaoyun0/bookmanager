package com.sttx.bookmanager.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class ControllerAOP {

    public Object aroundMethod(ProceedingJoinPoint joinpoint) throws Throwable {// ProceedingJoinPoint为通知
        Object obj = null;
        try {
            System.out.println("controller方法开始");
            System.out.println(joinpoint.getSignature().getDeclaringType().getSimpleName() + "."
                    + joinpoint.getSignature().getName() + "{");
            long start = System.currentTimeMillis();
            obj = joinpoint.proceed();// 执行通知的方法
            long end = System.currentTimeMillis();
            System.out.println("执行时间为:" + (end - start));
            System.out.println("}");
            return obj;
        } catch (Throwable e) {
            try {
                System.out.println(joinpoint.getSignature().getName() + "retry.");
                // 此处为方法执行失败调用的方法,可以多次调用通知方法，达到重试的目的
                // 但是proceed()方法至少调用一次
                joinpoint.proceed();
            } catch (Throwable e1) {
                // TODO Auto-generated catch block
                System.out.println(joinpoint.getSignature().getName() + "failure.");
                throw new Throwable(e1);
            }
            throw new Throwable(e);
        }

    }
}
