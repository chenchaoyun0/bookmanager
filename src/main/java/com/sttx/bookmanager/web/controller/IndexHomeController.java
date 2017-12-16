package com.sttx.bookmanager.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.Book;
import com.sttx.bookmanager.po.ResumeVo;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.service.IResumeService;
import com.sttx.bookmanager.util.LogUtil;
import com.sttx.bookmanager.util.file.NfsFileUtils;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.ddp.logger.DdpLoggerFactory;

@Controller
public class IndexHomeController {
    private static final Logger log = DdpLoggerFactory.getLogger(IndexHomeController.class);
    @Autowired
    private ILogService logService;
    @Autowired
    private IResumeService resumeService;

    @RequestMapping("/indexHome")
    public String indexHome(Model model, HttpServletRequest request, ModelAndView modelAndView, Integer pageNo, Integer pageSize) {
        // return "forward:/book/selectBookPages";
        TLog tLog = new TLog();
        PagedResult<TLog> pages = logService.selectLogPages(tLog, pageNo, pageSize);
        Long totalcount = logService.selectLogSumCount();
        String url = request.getRequestURI();
        pages.setUrl(url);
        model.addAttribute("pages", pages);
        model.addAttribute("totalcount", totalcount);
        log.info(">>>>>>>>>pages getTotal:{}", JSONObject.toJSON(pages.getTotal()));
        // return "redirect:job/m2/index.html";
        return "ipLog";
    }
    @RequestMapping("/")
    public String indexResume(Model model, HttpServletRequest request, ModelAndView modelAndView) {

        ResumeVo resumeVo = resumeService.findResumeVo();

        log.info("缓存中的信息 resumeVo.getImageVo:{}", LogUtil.formatLog(resumeVo.getImageVo()));

        model.addAttribute("resumeVo", resumeVo);

        return "/job/m2/resume";
    }

    @RequestMapping("/resume")
    public String resume() {
        return "redirect:/";
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

    @RequestMapping("/testImg")
    public String testImg(HttpServletRequest request, Model model) {
        String nfsFileName = NfsFileUtils.getNfsUrl() + "ad.jpg";
        String imageBase64Str = NfsFileUtils.getImageBase64Str(nfsFileName);
        Book book = new Book();
        book.setBookAuthor("chenchaoyun");
        book.setBookImg(imageBase64Str);
        model.addAttribute("book", book);
        return "testImg";
    }
}
