package com.sttx.bookmanager.web.intercepter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.map.AddressUtils;
import com.sttx.bookmanager.util.map.IPAddressMap;
import com.sttx.bookmanager.util.map.IPUtils;
import com.sttx.bookmanager.util.map.vo.IPAddressData;
import com.sttx.bookmanager.util.map.vo.IPAddressVo;
import com.sttx.ddp.logger.DdpLoggerFactory;

import cn.itcast.commons.CommonUtils;

public class UserIPIntercepter implements HandlerInterceptor {
    private static final Logger log = DdpLoggerFactory.getLogger(UserIPIntercepter.class);
    @Autowired
    private ILogService logService;
    private static final ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    // 执行Handler完成执行此方法
    // 应用场景：统一异常处理，统一日志处理
    public void afterCompletion(HttpServletRequest req, HttpServletResponse response, Object handler, Exception e) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;
            String className = h.getBean().getClass().getSuperclass().getSimpleName();
            String methodName = h.getMethod().getName();
            String requestURI = req.getRequestURI();
            // log.info("+++++className:" + className);
            // log.info("+++++methodName:" + methodName);
            // log.info("+++++requestURI:" + requestURI);
            /**
             * 保存日志
             */
            String remoteHost = req.getRemoteHost();
            // log.info("+++++remoteHost:" + remoteHost);
            String localAddr = req.getLocalAddr();
            // log.info("+++++localAddr:" + localAddr);
            String remoteAddr = req.getRemoteAddr();
            // log.info("+++++remoteAddr:" + remoteAddr);
            String remoteUser = req.getRemoteUser();
            // log.info("+++++remoteUser:" + remoteUser);
            String localName = req.getLocalName();
            // log.info("+++++localName:" + localName);
            String serverName = req.getServerName();
            // log.info("+++++serverName:" + serverName);
            String userName = "游客用户";
            // HttpSession session = req.getSession();
            // String userName = (session != null && (User)
            // session.getAttribute("userLogin") != null)
            // ? ((User) session.getAttribute("userLogin")).getLoginName() :
            // "游客用户";
            String userNickName = "未设置";
            String userIp = IPUtils.getIpAddr(req);
            //
            long end = System.currentTimeMillis();
            Long start = startTime.get();
            Long actionTime = end - start;
            String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
            log.info("查找访问来源是否存在日志...begin");
            TLog logDb = logService.selectByUserIp(userIp);
            log.info("查找访问来源是否存在日志...end");
            log.info("+++++保存日志 exit begin...logDb" + JSONObject.toJSONString(logDb));
            if (logDb == null) {
                //
                String userAddress = "";
                IPAddressVo ipAddressVo = AddressUtils.getIPAddressVo(userIp);
                if (ipAddressVo == null || !"0".equals(ipAddressVo.getCode())) {
                    userAddress = "搜不到你,请尝试刷新";
                } else {
                    IPAddressData data = ipAddressVo.getData();
                    String area = data.getArea();
                    String country = data.getCountry();
                    String province = data.getRegion();
                    String city = data.getCity();
                    String isp = data.getIsp();
                    userAddress = area + "," + province + "," + city + "," + country + "," + isp;
                }

                // String userAddress = addresses.equals("0") ?
                // "未知区域~~~搜不到你,请尝试刷新"
                String userJWD = Arrays.toString(IPAddressMap.getIPXY(userIp));
                // new
                TLog tLog = new TLog(userName, userNickName, userAddress, userJWD, className, methodName, actionTime, operTime, 1l);
                tLog.setLogId(CommonUtils.uuid());
                tLog.setUserIp(userIp);
                tLog.setOperTime(operTime);
                tLog.setActionTime(actionTime);
                log.info("+++++保存日志 new begin...参数" + JSONObject.toJSONString(tLog));
                // ActiveMQUtil.sendObjectMessage("tLog", tLog);
                int insert = logService.insert(tLog);
                log.info("+++++保存日志 new end...+++++insert:{}" + insert);
            } else {
                logDb.setLogId(CommonUtils.uuid());
                logDb.setUserIp(userIp);
                logDb.setOperTime(operTime);
                logDb.setModule(className);
                logDb.setAction(methodName);
                logDb.setActionTime(actionTime);
                log.info("+++++保存日志 exit begin...参数" + JSONObject.toJSONString(logDb));
                // ActiveMQUtil.sendObjectMessage("tLog", tLog);
                int insert = logService.insert(logDb);
                log.info("+++++保存日志 exit end...+++++insert:{}" + insert);
            }
        }
    }

    // 进入Handler方法之后，返回modelAndView之前执行
    // 应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，
    // 也可以在这里统一指定视图
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

    }

    // 进入 Handler方法之前执行
    // 用于身份认证、身份授权
    // 比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        startTime.set(start);
        return true;
    }

}
