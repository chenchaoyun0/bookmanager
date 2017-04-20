package com.sttx.bookmanager.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.esotericsoftware.minlog.Log;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.mq.ActiveMQUtil;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.properties.PropertiesUtil;
import com.sttx.ddp.logger.DdpLoggerFactory;

@Controller
public class IndexHomeController {
    private static final Logger logger = DdpLoggerFactory.getLogger(IndexHomeController.class);
    @Autowired
    private ILogService logService;

    @RequestMapping("/")
    public String indexHome(Model model, HttpServletRequest request, ModelAndView modelAndView, Integer pageNo,
            Integer pageSize) {
        //        return "forward:/book/selectBookPages";
        TLog tLog = new TLog();
        if (pageNo == null) {
            String tLogPages = PropertiesUtil.getFilePath("properties/activemq.properties", "tLogPages");
            String totalcount = PropertiesUtil.getFilePath("properties/activemq.properties", "totalcount");
            PagedResult<TLog> pages = (PagedResult<TLog>) ActiveMQUtil.getObjectMessage(tLogPages);
            if (pages == null) {
                Log.info("+++++++++++++++++++++++++消息队列没有主页数据");
                PagedResult<TLog> pages1 = logService.selectLogPages(tLog, pageNo, pageSize);
                Long totalcount1 = logService.selectLogSumCount();
                String url = request.getRequestURI();
                pages1.setUrl(url);
                model.addAttribute("pages", pages1);
                model.addAttribute("totalcount", totalcount1);
                return "ipLog";
            }
            String textMessage = ActiveMQUtil.getTextMessage(totalcount);
            String url = request.getRequestURI();
            pages.setUrl(url);
            model.addAttribute("pages", pages);
            model.addAttribute("totalcount", textMessage);
            return "ipLog";
        }
        PagedResult<TLog> pages = logService.selectLogPages(tLog, pageNo, pageSize);
        Long totalcount = logService.selectLogSumCount();
        String url = request.getRequestURI();
        pages.setUrl(url);
        model.addAttribute("pages", pages);
        model.addAttribute("totalcount", totalcount);
        return "ipLog";
    }

    @RequestMapping(value = "/indexHomeForIp", method = RequestMethod.GET)
    public String indexHomeForIp(String userIp, Model model, HttpServletRequest request, ModelAndView modelAndView,
            Integer pageNo, Integer pageSize) {
        //        return "forward:/book/selectBookPages";
        PagedResult<TLog> pages = logService.selectLogPagesForIp(userIp, pageNo, pageSize);
        Long totalcount = logService.selectLogSumCount();
        String url = request.getRequestURI();
        pages.setStrWhere("userIp=" + userIp);
        pages.setUrl(url);
        model.addAttribute("pages", pages);
        model.addAttribute("totalcount", totalcount);
        return "ipLog";
    }
}
