package com.sttx.bookmanager.web.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sttx.bookmanager.po.User;

public class UserLoginIntercepter implements HandlerInterceptor {
    // 执行Handler完成执行此方法
    // 应用场景：统一异常处理，统一日志处理
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 获取请求的url
        // 判断url是否是公开 地址（实际使用时将公开 地址配置配置文件中）
        // 这里公开地址是登陆提交的地址
        String url = request.getRequestURI();
        if (url.indexOf("userLoginInput.action") >= 0 || url.indexOf("indexHome.action") >= 0
                || url.indexOf("selectBookPages") >= 0 || url.indexOf("selectEBookPages") >= 0
                || url.indexOf("selectBookDetail") >= 0) {
            // 如果进行登陆提交，放行
            return true;
        }

        User user = (User) request.getSession().getAttribute("userLogin");

        if (user != null)

        {
            return true;
        }

        // 转发登陆页面
        response.sendRedirect(request.getContextPath() + "/user/userLoginInput.action");
        return false;
        // return true;
    }

}
