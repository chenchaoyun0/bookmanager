package com.sttx.bookmanager.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.pages.PagedResult;
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
        PagedResult<TLog> pages = logService.selectLogPages(tLog, pageNo, pageSize);
        long totalcount = logService.selectLogSumCount();
        String url = request.getRequestURI();
        pages.setUrl(url);
        model.addAttribute("pages", pages);
        model.addAttribute("totalcount", totalcount);
        logger.info("+++++查询日志列表:{}" + JSONObject.toJSONString(pages));
        return "ipLog";
    }
}
