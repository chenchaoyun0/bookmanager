package com.sttx.bookmanager.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.dictionary.ImgLinkType;
import com.sttx.bookmanager.po.Book;
import com.sttx.bookmanager.po.GridfsImg;
import com.sttx.bookmanager.po.TImg;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.IBaseMongoRepository;
import com.sttx.bookmanager.service.IBookService;
import com.sttx.bookmanager.service.IImgService;
import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.bookmanager.util.file.NfsFileUtils;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.properties.PropertiesUtil;
import com.sttx.bookmanager.util.uuidno.UUID2NO;

import cn.itcast.commons.CommonUtils;

@Controller
@RequestMapping("/book")
public class BookController {
  private static final Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private IBookService bookService;
    @Autowired
    private IImgService imgService;
    @Resource
    private IBaseMongoRepository baseMongoRepository;
    @RequestMapping(value = "/uploadBookInput", method = { RequestMethod.GET, RequestMethod.POST })
    public String uploadBookInput() {

        return "book/uploadBook";
    }

    @RequestMapping(value = "/uploadBookSubmit", method = RequestMethod.POST)
    public String uploadBookSubmit(@ModelAttribute("book") Book book, HttpServletResponse response, HttpServletRequest request, Model model,
            @RequestParam("bookFile") MultipartFile[] bookFile) throws IOException {
        /* 验证码 */
        // ...方便测试暂时不做校验
        String sessionCode = (String) request.getSession().getAttribute("session_vcode");
        String paramCode = request.getParameter("verifyCode");
        if (!paramCode.equalsIgnoreCase(sessionCode)) {// 验证码
            request.setAttribute("error_code", "验证码错误");
            return "book/uploadBook";
        }
        /* 获取登陆用户 */
        User user = (User) request.getSession().getAttribute("userLogin");
        String bookId = CommonUtils.uuid();
        book.setBookId(bookId);
        book.setBookNo(UUID2NO.getUUID2NO());
        book.setBookStatus(1);
        book.setBookRemain(book.getBookCount());
        book.setUserId(user.getUserId());
        book.setBookUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        book.setBookFlag(1);
        /**
         * 图片处理
         */
        String bookPath = null;
        String dbPath = null;
        String outPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "bookImg.dbpath");
        log.info("bookFile:{}", bookFile.length);
        List<TImg> imgList = new ArrayList<>();
        for (int i = 0; i < bookFile.length; i++) {
            MultipartFile myfile = bookFile[i];
            if (myfile.isEmpty()) {
                TImg tImg = new TImg();
                tImg.setCreateTime(new Date());
                tImg.setCreateUser(user.getLoginName());
                tImg.setImgId(CommonUtils.uuid());
                // 未选择图片择读取默认图片
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("defaultBookImg.jpg");
                //
                GridfsImg gridfsImg=new GridfsImg();
                gridfsImg.setIn(inputStream);
                gridfsImg.setAliases(tImg.getImgId());
                gridfsImg.setFileName("defaultBookImg.jpg");
                //
                String saveImg = baseMongoRepository.saveImg(gridfsImg);
                tImg.setImgPath(saveImg);
                tImg.setLastModifyTime(new Date());
                tImg.setLastModifyUser(user.getLoginName());
                tImg.setLinkId(bookId);
                tImg.setLinkType(ImgLinkType.BookImg.getCode());
                log.info("保存图片信息begin...tImg:{}", JSONObject.toJSON(tImg));
                imgList.add(tImg);
            } else {
                // 格式错误
                String originalFilename = myfile.getOriginalFilename();
                String imgEnd = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                if (!NfsFileUtils.isImgFile(imgEnd)) {
                    model.addAttribute("book", book);
                    model.addAttribute("errorMsg", "请上传jpg格式的图片");
                    return "book/uploadBook";
                }

                log.info("文件长度: " + myfile.getSize());
                log.info("文件类型: " + myfile.getContentType());
                log.info("文件名称: " + myfile.getName());
                log.info("文件原名: " + myfile.getOriginalFilename());
                log.info("========================================");
                // 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
                /* 保存文件夹 */
                /* 真实路径 */
                TImg tImg = new TImg();
                tImg.setCreateTime(new Date());
                tImg.setCreateUser(user.getLoginName());
                tImg.setImgId(CommonUtils.uuid());
                //
                GridfsImg gridfsImg = new GridfsImg();
                gridfsImg.setIn(myfile.getInputStream());
                gridfsImg.setAliases(tImg.getImgId());
                gridfsImg.setFileName(originalFilename);
                //
                String url = baseMongoRepository.saveImg(gridfsImg);
                String saveImg = baseMongoRepository.saveImg(gridfsImg);
                tImg.setImgPath(saveImg);
                tImg.setLastModifyTime(new Date());
                tImg.setLastModifyUser(user.getLoginName());
                tImg.setLinkId(bookId);
                tImg.setLinkType(ImgLinkType.BookImg.getCode());
                log.info("保存图片信息begin...tImg:{}", JSONObject.toJSON(tImg));
                imgList.add(tImg);
            }
        }
        /**
         * 插入数据库
         */
        try {
            book.setBookImg(imgList.size() + "");
            log.info("imgList size:{}条", imgList.size());
            int j = bookService.insertSelective(book);
            log.info("保存图书:{}条", j);
            log.info("imgList:{}", JSONObject.toJSON(imgList));
            int i = imgService.batchSaveImg(imgList);
            log.info("保存图片:{}条", i);
        } catch (UserException e) {
            model.addAttribute("book", book);
            model.addAttribute("errorMsg", e.getMessage());
            return "book/uploadBook";
        }

        model.addAttribute("successMsg", "上传成功");
        List<String> imageBase64StrList = NfsFileUtils.getImageBase64StrList(imgList);
        model.addAttribute("imgList", imageBase64StrList);
        return "book/uploadBook";
    }

    @RequestMapping(value = "selectBookPages", method = { RequestMethod.GET, RequestMethod.POST })
    public String selectBookPages(Book book, String loginName, Model model, HttpServletRequest request, ModelAndView modelAndView,
            Integer pageNo, Integer pageSize, @RequestParam(value = "userId", required = false) String userId) {
        User user = new User();
        user.setLoginName(loginName);
        book.setUser(user);
        if (!"".equals(userId) && userId != null) {
            book.setUserId(userId);
        }
        /* 分页 */
        PagedResult<Book> pages = bookService.selectBookPages(book, pageNo, pageSize);
        String url = request.getRequestURI();
        pages.setUrl(url);
        //
        model.addAttribute("pages", pages);

        return "indexHome";
    }

    @RequestMapping(value = "selectBookDetail/{bookId}", method = { RequestMethod.GET })
    public String selectBookDetail(@PathVariable("bookId") String bookId, Model model) {
        Book book = bookService.selectByPrimaryKey(bookId);

        model.addAttribute("book", book);
        TImg tImg = new TImg();
        tImg.setLinkId(bookId);
        log.info("查询图书图片begin...");
        List<TImg> imgList = imgService.selectList(tImg);
        log.info("查询图书图片end...imgList:{}", JSONObject.toJSON(imgList));
        List<String> imageBase64StrList = NfsFileUtils.getImageBase64StrList(imgList);
        model.addAttribute("imgList", imageBase64StrList);
        return "book/bookDetail";
    }

    @RequestMapping(value = "updateBookInput/{bookId}", method = { RequestMethod.GET })
    public String updateBook(@PathVariable("bookId") String bookId, Model model) {
        Book book = bookService.selectByPrimaryKey(bookId);
        model.addAttribute("book", book);
        //
        TImg tImg = new TImg();
        tImg.setLinkId(bookId);
        log.info("查询图书图片begin...");
        List<TImg> imgList = imgService.selectList(tImg);
        log.info("查询图书图片end...imgList:{}", JSONObject.toJSON(imgList));
        List<String> imageBase64StrList = NfsFileUtils.getImageBase64StrList(imgList);
        model.addAttribute("imgList", imageBase64StrList);
        return "book/updateBook";
    }

    @RequestMapping(value = "updateBookSubmit", method = { RequestMethod.POST })
    public String updateBookSubmit(@ModelAttribute("book") Book book, Model model, HttpServletRequest request) {
        // ...方便测试暂时不做校验
        String sessionCode = (String) request.getSession().getAttribute("session_vcode");
        String paramCode = request.getParameter("verifyCode");
        if (!paramCode.equalsIgnoreCase(sessionCode)) {// 验证码
            model.addAttribute("book", book);
            request.setAttribute("error_code", "验证码错误");
            return "book/updateBook";
        }
        bookService.updateByPrimaryKeySelective(book);
        model.addAttribute("book", book);
        model.addAttribute("successMsg", "更新成功");
        return "book/updateBook";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "/admin/bookadmin";
    }

    @RequestMapping("/adminData")
    @ResponseBody
    public PagedResult<Book> adminData(Book book, Integer pageNo, Integer pageSize) {
        User user = new User();
        book.setUser(user);
        PagedResult<Book> list = bookService.selectBookPages(book, pageNo, pageSize);
        return list;
    }

    @RequestMapping("/unmountBook/{bookId}")
    @ResponseBody
    public int unmountBook(@PathVariable("bookId") String bookId) {

        return bookService.unmountBook(bookId);
    }

    @RequestMapping("/mountBook/{bookId}")
    @ResponseBody
    public int mountBook(@PathVariable("bookId") String bookId) {

        return bookService.mountBook(bookId);
    }
}
