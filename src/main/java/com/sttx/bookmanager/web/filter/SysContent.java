package com.sttx.bookmanager.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** 
 * Web上下文，保存所有request与response 
 *  
 */
public class SysContent {
    private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

    public static HttpServletRequest getRequest() {
        return requestLocal.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestLocal.set(request);
    }

    public static HttpServletResponse getResponse() {
        return responseLocal.get();
    }

    public static void setResponse(HttpServletResponse response) {
        responseLocal.set(response);
    }

    public static HttpSession getSession() {
        return requestLocal.get().getSession();
    }
}