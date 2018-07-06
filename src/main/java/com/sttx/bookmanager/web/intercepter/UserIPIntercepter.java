package com.sttx.bookmanager.web.intercepter;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sttx.bookmanager.dao.BlackListMapper;
import com.sttx.bookmanager.po.BlackLisEntity;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.map.IPUtils;
import com.sttx.bookmanager.util.time.DateConvertUtils;

import tk.mybatis.mapper.entity.Example;

public class UserIPIntercepter implements HandlerInterceptor {
  private static final Logger log = LoggerFactory.getLogger(UserIPIntercepter.class);
  @Autowired
  private ILogService logService;
  @Autowired
  private BlackListMapper blackListMapper;

  private static final String GOOGLEBOT = "Googlebot";

  private static final String SPIDER = "spider";

  // 执行Handler完成执行此方法
  // 应用场景：统一异常处理，统一日志处理
  public void afterCompletion(HttpServletRequest req, HttpServletResponse response, Object handler, Exception e)
    throws Exception {
    if (handler instanceof HandlerMethod) {
      HandlerMethod h = (HandlerMethod)handler;

    }
    log.info("执行Handler完成执行此方法...");
  }

  // 进入Handler方法之后，返回modelAndView之前执行
  // 应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，
  // 也可以在这里统一指定视图
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
    throws Exception {
    log.info("进入Handler方法之后，返回modelAndView之前执行...");
  }

  // 进入 Handler方法之前执行
  // 用于身份认证、身份授权
  // 比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    log.info("进入 Handler方法之前执行...");
    try {
      /**
       * 保存用户浏览器信息
       */
      String agentStr = request.getHeader("user-agent");
      log.info("用户浏览器信息agentStr:{}", agentStr);
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
        //
        Example exampleUpdate = new Example(BlackLisEntity.class);
        exampleUpdate.createCriteria().andEqualTo("id", blackLisEntity.getId());
        int updateByPrimaryKey = blackListMapper.updateByExampleSelective(blackLisEntity, exampleUpdate);
        log.info("ip：{}已被禁止访问,updateByPrimaryKey:{}", ip, updateByPrimaryKey);
        return false;

      } else {

        boolean b = agentStr.contains(SPIDER) || agentStr.contains(GOOGLEBOT);

        if (b) {
          blackLisEntity = new BlackLisEntity();
          blackLisEntity.setCount(1l);
          blackLisEntity.setLasttime(lasttime);
          blackLisEntity.setPath(path);
          blackLisEntity.setIp(ip);
          blackLisEntity.setIsblock(0);
          int insert = blackListMapper.insert(blackLisEntity);

          log.info("ip：{}已被禁止访问,insert:{}", ip, insert);
          return false;
        }else{
          return true;
        }
      }

    } catch (Exception e) {
      log.error("拦截器异常:{}", e);
      return true;
    }
  }

}
