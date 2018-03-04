package com.sttx.bookmanager.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.Book;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.IBaseMongoRepository;
import com.sttx.bookmanager.service.IBookService;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.service.IResumeService;
import com.sttx.bookmanager.util.excel.ExportToExcelUtil;
import com.sttx.bookmanager.util.file.NfsFileUtils;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.properties.PropertiesUtil;

@Controller
public class IndexHomeController {
  private static final Logger log = LoggerFactory.getLogger(IndexHomeController.class);
    @Autowired
    private ILogService logService;
    @Autowired
    private IResumeService resumeService;
    
    @Autowired
    private IBookService bookService;

    @RequestMapping("/indexHome")
    public String indexHome(Model model, HttpServletRequest request, ModelAndView modelAndView, Integer pageNo, Integer pageSize) {
        String realPath = request.getServletContext().getRealPath("resources/ehcache.xml");
        log.info(">>>>>>>>>realPath:{}",realPath);
        // return "forward:/book/selectBookPages";
        TLog tLog = new TLog();
        PagedResult<TLog> pages = logService.selectLogPagesByMongo(tLog, pageNo, pageSize);
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

        // ResumeVo resumeVo = resumeService.findResumeVo();

        // log.info("缓存中的信息 resumeVo.getImageVo:{}",
        // LogUtil.formatLog(resumeVo.getImageVo()));

        // model.addAttribute("resumeVo", resumeVo);
        // 此方法加重浏览器解析base64 照片负担更慢了=-=

        return "/job/m2/resume";
        // return "forward:/book/selectBookPages";
    }

    @RequestMapping("/resume")
    public String resume() {
        return "job/m2/resume";
    }

    @RequestMapping(value = "/indexHomeForIp", method = RequestMethod.GET)
    public String indexHomeForIp(String userIp, Model model, HttpServletRequest request, ModelAndView modelAndView,
            Integer pageNo, Integer pageSize) {
        //        return "forward:/book/selectBookPages";
        PagedResult<TLog> pages = logService.selectLogPagesForIpByMongo(userIp, pageNo, pageSize);
        Long totalcount = logService.selectLogSumCount();
        String url = request.getRequestURI();
        pages.setStrWhere("userIp=" + userIp);
        pages.setUrl(url);
        model.addAttribute("pages", pages);
        model.addAttribute("totalcount", totalcount);
        return "ipLog";
    }

    @RequestMapping("/downloadResumeDocx")
    public String downloadResumeDocx(Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {

        String fileName = "北京-Java开发工程师-陈超允.docx";
        fileName = new String(fileName.getBytes("UTF-8"), "iso8859-1");
        response.reset();// 去除空白行
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 指定下载的文件名
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        InputStream bis = null;
        try {
            String nfsFileName = NfsFileUtils.getNfsUrl() + "resume/docx/chenchaoyun-resume.docx";
            log.info("nfsFileName:{}", nfsFileName);
            bis = NfsFileUtils.readNfsFile2Stream(nfsFileName);
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copy(bis, outputStream);
        } catch (Exception e) {
            log.error("要下载的文件不存在", e);
            request.setAttribute("msg", "要下载的文件不存在" + e.getMessage());
            return "forward:/error/msg.jsp";
        } finally {
        }
        return null;
    }

    @RequestMapping("/exportBookListExcel/{pageNo}/{pageSize}")
    public void exportBookListExcel(Book book, String loginName, HttpServletResponse response,
        @PathVariable("pageNo") Integer pageNo, @RequestParam(value = "userId", required = false) String userId,
        @PathVariable("pageSize") Integer pageSize) throws IOException {
        User user = new User();
        user.setLoginName(loginName);
        book.setUser(user);
        if (!"".equals(userId) && userId != null) {
            book.setUserId(userId);
        }
        /* 分页 */
        PagedResult<Book> pages = bookService.selectBookPages(book, pageNo, pageSize);
        List<Book> bookList = pages.getDataList();
        String filePath = PropertiesUtil.getFilePath("uploadFilePath.properties", "book.ImgPath");
        for (int i = 0; i < bookList.size(); i++) {
            Book b = bookList.get(i);
            String bookImg = b.getBookImg();
            bookImg = StringUtils.remove(bookImg, NfsFileUtils.jspImgSrc);
            byte[] byt = NfsFileUtils.base64Str2Byte(bookImg);
            ByteArrayInputStream in = new ByteArrayInputStream(byt);
            BufferedImage img = ImageIO.read(in);
            b.setBookImage(img);
        }

        try {
            ExportToExcelUtil.out(response, bookList);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
