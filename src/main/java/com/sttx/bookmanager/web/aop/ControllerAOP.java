package com.sttx.bookmanager.web.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.map.AddressUtils;
import com.sttx.bookmanager.util.map.IPAddressMap;
import com.sttx.bookmanager.util.map.IPUtils;
import com.sttx.bookmanager.util.map.vo.IPAddressData;
import com.sttx.bookmanager.util.map.vo.IPAddressVo;

import cn.itcast.commons.CommonUtils;

public class ControllerAOP {
    private static Logger log = Logger.getLogger(ControllerAOP.class);
    @Autowired
    private ILogService logService;
    public Object aroundMethod(ProceedingJoinPoint joinpoint) {// ProceedingJoinPoint为通知
        Object obj = null;
        try {
            long start = System.currentTimeMillis();
            log.info("+++++controller方法开始...");
            obj = joinpoint.proceed();// 执行通知的方法
            long end = System.currentTimeMillis();
            long actionTime = end - start;
            log.info("++++++controller方法结束,执行时间为:" + (actionTime));
            /**
             * 记录日志
             */
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest req = sra.getRequest();
            Object target = joinpoint.getTarget();
            String methodName = joinpoint.getSignature().getName();
            // String className =
            // h.getBean().getClass().getSuperclass().getSimpleName();
            String className = target.getClass().getSimpleName();
            // String methodName = h.getMethod().getName();
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
            /**
             * 
             */
            return obj;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;

    }
}
