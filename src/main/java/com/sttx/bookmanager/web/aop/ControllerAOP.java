package com.sttx.bookmanager.web.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.web.filter.BaiduIP;
import com.sttx.bookmanager.web.filter.Base_info;
import com.sttx.bookmanager.web.filter.IPAddressMap;
import com.sttx.bookmanager.web.filter.IPUtils;
import com.sttx.bookmanager.web.filter.SysContent;

import cn.itcast.commons.CommonUtils;

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
            /**
             * 保存日志
             */
            String module = joinpoint.getSignature().getDeclaringType().getSimpleName();
            String action = joinpoint.getSignature().getName();
            HttpServletRequest req = SysContent.getRequest();
            HttpServletResponse resp = SysContent.getResponse();
            HttpSession session = SysContent.getSession();
            String userName = (session != null && (User) session.getAttribute("userLogin") != null)
                    ? ((User) session.getAttribute("userLogin")).getLoginName() : "游客用户";
            String userNickName = "未设置";
            String userIp = IPUtils.getIpAddr(req);
            //            String addresses = UtilIPAddress.getAddresses("ip=" + IPUtils.getIpAddr(req), "utf-8");
            Base_info base_info = BaiduIP.getBaiduIpPO(userIp).getBase_info();
            String country = base_info.getCountry();
            String province = base_info.getProvince();
            String city = base_info.getCity();
            String county = base_info.getCounty();
            String isp = base_info.getIsp();
            String userAddress = country + "," + province + "," + city + "," + county + "," + isp;
            //            String userAddress = addresses.equals("0") ? "未知区域~~~搜不到你,请尝试刷新" : addresses;
            long actionTime = end - start;
            String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
            String userJWD = Arrays.toString(IPAddressMap.getIPXY(userIp));
            //new
            TLog tLog = new TLog(userName, userNickName, userAddress, userJWD, module, action, actionTime, operTime,
                    1l);
            tLog.setLogId(CommonUtils.uuid());
            tLog.setUserIp(userIp);
            //select
            TLog tLog2 = logService.selectByUserIp(userIp);
            log.info("根据ip查询日志+++++result:" + JSONObject.toJSON(tLog2));
            if (tLog2 != null) {
                tLog.setCount(tLog2.getCount() + 1);
                tLog.setLogId(tLog2.getLogId());
                logService.updateByPrimaryKey(tLog);
            } else {

                log.info("+++++保存日志begin...参数" + JSONObject.toJSONString(tLog));
                int i = logService.insert(tLog);
                log.info("+++++保存日志end...+++++result:" + i);
            }
            /**
             * 结束
             */
            log.info("++++++controller方法结束,执行时间为:" + (actionTime));
            return obj;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;

    }
}
