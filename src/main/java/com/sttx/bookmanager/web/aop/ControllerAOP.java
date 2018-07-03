package com.sttx.bookmanager.web.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.BrowserType;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.Manufacturer;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.RenderingEngine;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

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
      ServletRequestAttributes sra = (ServletRequestAttributes)ra;
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

      /**
       * 保存用户浏览器信息
       */
      String agentStr = req.getHeader("user-agent");
      UserAgent agent = UserAgent.parseUserAgentString(agentStr);
      // 浏览器
      Browser browser = agent.getBrowser();
      // 浏览器版本
      Version version = agent.getBrowserVersion();
      // 系统
      OperatingSystem os = agent.getOperatingSystem();
      /**
       * 保存字段
       */
      // 浏览器类型
      BrowserType browserType = browser.getBrowserType();
      // 浏览器名称和版本
      String browserAndVersion = String.format("%s-%s", browser.getGroup().getName(), version.getVersion());
      // 浏览器厂商
      Manufacturer manufacturer = browser.getManufacturer();
      // 浏览器引擎
      RenderingEngine renderingEngine = browser.getRenderingEngine();
      // 系统名称
      String sysName = os.getName();
      // 产品系列
      OperatingSystem operatingSystem = os.getGroup();
      // 生成厂商
      Manufacturer sysManufacturer = os.getManufacturer();
      // 设备类型
      DeviceType deviceType = os.getDeviceType();

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
          if (StringUtils.isBlank(area)) {
            userAddress = province + "," + city + "," + country + "," + isp;
          } else {
            userAddress = area + "," + province + "," + city + "," + country + "," + isp;
          }
        }

        // String userAddress = addresses.equals("0") ?
        // "未知区域~~~搜不到你,请尝试刷新"
        String userJWD = Arrays.toString(IPAddressMap.getIPXY(userIp));
        // new
        TLog tLog
          = new TLog(userName, userNickName, userAddress, userJWD, className, methodName, actionTime, operTime, 1l);
        tLog.setLogId(CommonUtils.uuid());
        tLog.setUserIp(userIp);
        tLog.setOperTime(operTime);
        tLog.setActionTime(actionTime);

        // 浏览器信息
        tLog.setBrowserAndVersion(browserAndVersion);
        tLog.setBrowserType(browserType.name());
        tLog.setManufacturer(manufacturer.name());
        tLog.setRenderingEngine(renderingEngine.name());
        tLog.setSysName(sysName);
        tLog.setOperatingSystem(operatingSystem.name());
        tLog.setSysManufacturer(sysManufacturer.name());
        tLog.setDeviceType(deviceType.name());

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
        // 浏览器信息
        logDb.setBrowserAndVersion(browserAndVersion);
        logDb.setBrowserType(browserType.name());
        logDb.setManufacturer(manufacturer.name());
        logDb.setRenderingEngine(renderingEngine.name());
        logDb.setSysName(sysName);
        logDb.setOperatingSystem(operatingSystem.name());
        logDb.setSysManufacturer(sysManufacturer.name());
        logDb.setDeviceType(deviceType.name());

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
