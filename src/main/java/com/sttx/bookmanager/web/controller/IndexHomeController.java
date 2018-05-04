package com.sttx.bookmanager.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.sttx.bookmanager.service.IBookService;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.service.IResumeService;
import com.sttx.bookmanager.util.excel.ExportToExcelUtil;
import com.sttx.bookmanager.util.file.NfsFileUtils;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.properties.PropertiesUtil;
import com.sttx.bookmanager.util.tts.XunfeiLib;

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
        PagedResult<TLog> pages = logService.selectLogPagesForIp(userIp, pageNo, pageSize);
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
//    @RequestMapping("/ttsData/{data}")
//    public void ttsData(HttpServletRequest request,@PathVariable("data")String data,HttpServletResponse response) throws Exception {
//      request.setCharacterEncoding("UTF-8");//解决乱码
//
//      //换成你在讯飞申请的APPID
//      SpeechUtility.createUtility("appid=5ac38223");
//
//      //合成监听器
//      SynthesizeToUriListener synthesizeToUriListener = XunfeiLib.getSynthesize();
//
//      String fileName=XunfeiLib.getFileName("tts_test.pcm");
//      XunfeiLib.delDone(fileName);
//
//      //1.创建SpeechSynthesizer对象
//      SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer( );
//      //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
//      mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
//      mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速，范围0~100
//      mTts.setParameter(SpeechConstant.PITCH, "50");//设置语调，范围0~100
//      mTts.setParameter(SpeechConstant.VOLUME, "50");//设置音量，范围0~100
//
//      //3.开始合成
//      //设置合成音频保存位置（可自定义保存位置），默认保存在“./tts_test.pcm”
//      mTts.synthesizeToUri(data,fileName ,synthesizeToUriListener);
//
//      //设置最长时间
//      int timeOut=30;
//      int star=0;
//
//      //校验文件是否生成
//      while(!XunfeiLib.checkDone(fileName)){
//
//          try {           
//              Thread.sleep(1000);
//              star++;             
//              if(star>timeOut){
//                  throw new Exception("合成超过"+timeOut+"秒！");
//              }
//          } catch (Exception e) {
//              // TODO 自动生成的 catch 块
//              e.printStackTrace();
//              break;
//          } 
//
//      }
//
//      this.sayPlay(fileName, request, response);
//    }
    
    /**
     * 将音频内容输出到请求中
     * 
     * @param fileName
     * @param request
     * @param response
     */
    private  void sayPlay (String fileName,HttpServletRequest request,HttpServletResponse response) {

         //输出 wav IO流
         try{

             response.setHeader("Content-Type", "audio/mpeg");
             File file = new File(fileName);
             int len_l = (int) file.length();
             byte[] buf = new byte[2048];
             FileInputStream fis = new FileInputStream(file);
             OutputStream out = response.getOutputStream();

             //写入WAV文件头信息
             out.write(XunfeiLib.getWAVHeader(len_l,8000,2,16));

             len_l = fis.read(buf);
             while (len_l != -1) {
                 out.write(buf, 0, len_l);
                len_l = fis.read(buf);
             }
             out.flush();
             out.close();
             fis.close();

             //删除文件和清除队列信息
             XunfeiLib.delDone(fileName);
             file.delete();
         }catch (Exception e){
             System.out.println(e);
         }     

    }

}
