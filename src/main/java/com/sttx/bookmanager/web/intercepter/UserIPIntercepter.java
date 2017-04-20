package com.sttx.bookmanager.web.intercepter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.util.mq.ActiveMQUtil;
import com.sttx.bookmanager.util.pages.ThreadLocalContext;
import com.sttx.bookmanager.web.filter.BaiduIP;
import com.sttx.bookmanager.web.filter.Base_info;
import com.sttx.bookmanager.web.filter.IPAddressMap;
import com.sttx.bookmanager.web.filter.IPGetAddress;
import com.sttx.bookmanager.web.filter.IPUtils;
import com.sttx.bookmanager.web.filter.UtilIPAddress;

import cn.itcast.commons.CommonUtils;

public class UserIPIntercepter implements HandlerInterceptor {
    private static Logger log = Logger.getLogger(UserIPIntercepter.class);

    // 执行Handler完成执行此方法
    // 应用场景：统一异常处理，统一日志处理
    public void afterCompletion(HttpServletRequest req, HttpServletResponse response, Object handler, Exception e)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;
            String className = h.getBean().getClass().getSuperclass().getSimpleName();
            String methodName = h.getMethod().getName();
            String requestURI = req.getRequestURI();
            log.info("+++++className:" + className);
            log.info("+++++methodName:" + methodName);
            log.info("+++++requestURI:" + requestURI);
            /**
             * 保存日志
             */
            String remoteHost = req.getRemoteHost();
            log.info("+++++remoteHost:" + remoteHost);
            String localAddr = req.getLocalAddr();
            log.info("+++++localAddr:" + localAddr);
            String remoteAddr = req.getRemoteAddr();
            log.info("+++++remoteAddr:" + remoteAddr);
            String remoteUser = req.getRemoteUser();
            log.info("+++++remoteUser:" + remoteUser);
            String localName = req.getLocalName();
            log.info("+++++localName:" + localName);
            String serverName = req.getServerName();
            log.info("+++++serverName:" + serverName);
            HttpSession session = req.getSession();
            String userName = (session != null && (User) session.getAttribute("userLogin") != null)
                    ? ((User) session.getAttribute("userLogin")).getLoginName() : "游客用户";
            String userNickName = "未设置";
            String userIp = IPUtils.getIpAddr(req);
            //            String addresses = UtilIPAddress.getAddresses("ip=" + IPUtils.getIpAddr(req), "utf-8");
            Base_info base_info = BaiduIP.getBaiduIpPO(userIp).getBase_info();
            String userAddress = "";
            if (base_info == null) {
                userAddress = UtilIPAddress.getAddresses("ip=" + IPUtils.getIpAddr(req), "utf-8");
                userAddress = null;
                if ("0".equals(userAddress) || userAddress == null) {
                    userAddress = IPGetAddress.getAddress(userIp);
                }
            } else {
                String country = base_info.getCountry();
                String province = base_info.getProvince();
                String city = base_info.getCity();
                String county = base_info.getCounty();
                String isp = base_info.getIsp();
                userAddress = country + "," + province + "," + city + "," + county + "," + isp;

            }
            //            String userAddress = addresses.equals("0") ? "未知区域~~~搜不到你,请尝试刷新" : addresses;
            String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
            String userJWD = Arrays.toString(IPAddressMap.getIPXY(userIp));
            Long actionTime = ThreadLocalContext.getControllerexcutime().get();
            //new
            TLog tLog = new TLog(userName, userNickName, userAddress, userJWD, className, methodName, actionTime,
                    operTime, 1l);
            tLog.setLogId(CommonUtils.uuid());
            tLog.setUserIp(userIp);
            log.info("+++++保存日志begin...参数" + JSONObject.toJSONString(tLog));
            ActiveMQUtil.sendObjectMessage("tLog", tLog);
            log.info("+++++保存日志end...+++++");
        }
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
        return true;
    }

}
