package com.sttx.bookmanager.web.intercepter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sttx.bookmanager.dao.BlackListMapper;
import com.sttx.bookmanager.po.BlackLisEntity;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.map.IPUtils;
import com.sttx.bookmanager.util.time.DateConvertUtils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.BrowserType;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.Manufacturer;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.RenderingEngine;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;
@Slf4j
public class UserIPIntercepter implements HandlerInterceptor {
  @Autowired
  private ILogService logService;
  @Autowired
  private BlackListMapper blackListMapper;

  private static final String GOOGLEBOT = "Googlebot";

  private static final String SPIDER = "spider";
  private static final String UBUNTU = "Ubuntu";
  private static final String CENTOS = "Centos";
  private static final String CENTOS_UP = "CentOS";

  // 执行Handler完成执行此方法
  // 应用场景：统一异常处理，统一日志处理
  public void afterCompletion(HttpServletRequest req, HttpServletResponse response, Object handler, Exception e)
    throws Exception {
    if (handler instanceof HandlerMethod) {
      HandlerMethod h = (HandlerMethod)handler;

    }
    //log.info("执行Handler完成执行此方法...");
  }

  // 进入Handler方法之后，返回modelAndView之前执行
  // 应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，
  // 也可以在这里统一指定视图
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
    throws Exception {
    //log.info("进入Handler方法之后，返回modelAndView之前执行...");
  }

  // 进入 Handler方法之前执行
  // 用于身份认证、身份授权
  // 比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    //log.info("进入 Handler方法之前执行...");
    try {
      /**
       * 保存用户浏览器信息
       */
      String agentStr = request.getHeader("user-agent");
      log.info("用户浏览器信息agentStr:{}", agentStr);
      /**
       * 保存用户浏览器信息
       */
      UserAgent agent = UserAgent.parseUserAgentString(agentStr);
      // 浏览器
      Browser browser = agent.getBrowser()==null?Browser.UNKNOWN:agent.getBrowser();
      // 浏览器版本
      Version version = agent.getBrowserVersion();
      // 系统
      OperatingSystem os = agent.getOperatingSystem()==null?OperatingSystem.UNKNOWN:agent.getOperatingSystem();
      /**
       * 保存字段
       */
      // 浏览器类型
      BrowserType browserType = browser.getBrowserType();
      // 浏览器名称和版本
      String browserAndVersion = String.format("%s-%s", browser.getGroup().getName(), version==null?"未知":version.getVersion());
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

      // Googlebot,spider
      String ip = IPUtils.getIpAddr(request);
      String lasttime = DateConvertUtils.format(new Date(), DateConvertUtils.DATE_TIME_FORMAT);
      String path = request.getRequestURI();

      //
      Example example = new Example(BlackLisEntity.class);
      example.createCriteria().andEqualTo("ip", ip);
      BlackLisEntity blackLisEntity = blackListMapper.selectOneByExample(example);

      if (blackLisEntity != null) {
        // 更新次数
        blackLisEntity.setCount(blackLisEntity.getCount() + 1);
        blackLisEntity.setLasttime(lasttime);
        blackLisEntity.setPath(path);
     // 浏览器信息
        blackLisEntity.setBrowserAndVersion(browserAndVersion);
        blackLisEntity.setBrowserType(browserType.name());
        blackLisEntity.setManufacturer(manufacturer.name());
        blackLisEntity.setRenderingEngine(renderingEngine.name());
        blackLisEntity.setSysName(sysName);
        blackLisEntity.setOperatingSystem(operatingSystem.name());
        blackLisEntity.setSysManufacturer(sysManufacturer.name());
        blackLisEntity.setDeviceType(deviceType.name());
        //
        Example exampleUpdate = new Example(BlackLisEntity.class);
        exampleUpdate.createCriteria().andEqualTo("id", blackLisEntity.getId());
        int updateByPrimaryKey = blackListMapper.updateByExampleSelective(blackLisEntity, exampleUpdate);
        log.info("ip：{}已被禁止访问,updateByPrimaryKey:{}", ip, updateByPrimaryKey);
        return false;

      } else {

        boolean b = agentStr.contains(SPIDER) || agentStr.contains(GOOGLEBOT) || agentStr.contains(UBUNTU)
          || agentStr.contains(CENTOS) || agentStr.contains(CENTOS_UP);
        boolean c=sysName.contains(CENTOS)||sysName.contains(CENTOS_UP)||sysName.contains(UBUNTU);
        boolean d = operatingSystem.name().equalsIgnoreCase("LINUX");

        if (b||c||d) {
          blackLisEntity = new BlackLisEntity();
          blackLisEntity.setCount(1l);
          blackLisEntity.setLasttime(lasttime);
          blackLisEntity.setPath(path);
          blackLisEntity.setIp(ip);
          blackLisEntity.setIsblock(0);
       // 浏览器信息
          blackLisEntity.setBrowserAndVersion(browserAndVersion);
          blackLisEntity.setBrowserType(browserType.name());
          blackLisEntity.setManufacturer(manufacturer.name());
          blackLisEntity.setRenderingEngine(renderingEngine.name());
          blackLisEntity.setSysName(sysName);
          blackLisEntity.setOperatingSystem(operatingSystem.name());
          blackLisEntity.setSysManufacturer(sysManufacturer.name());
          blackLisEntity.setDeviceType(deviceType.name());
          int insert = blackListMapper.insert(blackLisEntity);

          log.info("ip：{}已被禁止访问,insert:{}", ip, insert);
          return false;
        } else {
          return true;
        }
      }

    } catch (Exception e) {
      log.error("拦截器异常:{}", e);
      return true;
    }
  }

}
