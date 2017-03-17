package com.sttx.bookmanager.web.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserIPIntercepter implements HandlerInterceptor {
    private static Logger log = Logger.getLogger(UserIPIntercepter.class);

    // 执行Handler完成执行此方法
    // 应用场景：统一异常处理，统一日志处理
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
            throws Exception {
    }

    // 进入Handler方法之后，返回modelAndView之前执行
    // 应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，
    // 也可以在这里统一指定视图
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {

    }

    // 进入 Handler方法之前执行
    // 用于身份认证、身份授权
    // 比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;
            String className = h.getBean().getClass().getSimpleName();
            String methodName = h.getMethod().getName();
            String requestURI = request.getRequestURI();
            log.info("+++++className:" + className);
            log.info("+++++methodName:" + methodName);
            log.info("+++++requestURI:" + requestURI);
        }
        return true;
    }

}
