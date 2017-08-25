package com.sttx.bookmanager.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
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

import com.sttx.bookmanager.po.Book;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.IBookService;
import com.sttx.bookmanager.util.excel.ExportToExcelUtil;
import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.bookmanager.util.file.NfsFileUtils;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.properties.PropertiesUtil;
import com.sttx.bookmanager.util.uuidno.UUID2NO;
import com.sttx.ddp.logger.DdpLoggerFactory;
import com.sun.xfile.XFileOutputStream;

import cn.itcast.commons.CommonUtils;

@Controller
@RequestMapping("/book")
public class BookController {
    private static Logger log = DdpLoggerFactory.getLogger(BookController.class);
    @Autowired
    private IBookService bookService;

    @RequestMapping(value = "/uploadBookInput", method = { RequestMethod.GET, RequestMethod.POST })
    public String uploadBookInput() {

        return "book/uploadBook";
    }

    @RequestMapping(value = "/uploadBookSubmit", method = RequestMethod.POST)
    public String uploadBookSubmit(@ModelAttribute("book") Book book, HttpServletResponse response,
            HttpServletRequest request, Model model, @RequestParam MultipartFile[] bookFile) throws IOException {
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

        book.setBookId(CommonUtils.uuid());
        book.setBookNo(UUID2NO.getUUID2NO());
        book.setBookStatus(1);
        book.setBookRemain(book.getBookCount());
        book.setUserId(user.getUserId());
        book.setBookUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        book.setBookFlag(1);
        /**
         * 图片处理
         */
        StringBuilder bookImg = new StringBuilder(",");
        String realPath = NfsFileUtils.getNfsUrl();
        String bookPath = null;
        String dbPath = null;
        if (bookFile.length <= 0) {
            // 未选择图片择读取默认图片
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("defaultBookImg.jpg");
            // 读取配置文件，将文件上传至虚拟目录
            // nfs://192.168.1.xxx:/u01/upload/
            // 二级目录
            bookPath = user.getLoginName() + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/" + book.getBookNo() + "/"
                    + book.getBookNo() + "-defaultBookImg-01.jpg";
            log.info("bookPath:{}", bookPath);
            /* 数据库保存路径 */
            dbPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "bookImg.dbpath") + bookPath;
            log.info("dbPath:{}", dbPath);
            /* 保存到硬盘 */
            String uploadPath = realPath + bookPath;
            log.info("uploadPath:{}", uploadPath);
            NfsFileUtils.uploadFile(inputStream, new XFileOutputStream(uploadPath));
            /* 将所有文件路径用，隔开保存 */
            bookImg.append(dbPath).append(",");
        }
        for (int i = 0; i < bookFile.length; i++) {
            MultipartFile myfile = bookFile[i];
            if (myfile.isEmpty()) {
                // 未选择图片择读取默认图片
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("defaultBookImg.jpg");
                // 读取配置文件，将文件上传至虚拟目录
                // nfs://192.168.1.xxx:/u01/upload/
                // 二级目录
                bookPath = user.getLoginName() + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/"
                        + book.getBookNo() + "/" + book.getBookNo() + "-defaultBookImg" + i + ".jpg";
                log.info("bookPath:{}", bookPath);
                /* 数据库保存路径 */
                dbPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "bookImg.dbpath") + bookPath;
                log.info("dbPath:{}", dbPath);
                /* 保存到硬盘 */
                String uploadPath = realPath + bookPath;
                log.info("uploadPath:{}", uploadPath);
                NfsFileUtils.uploadFile(inputStream, new XFileOutputStream(uploadPath));
                /* 将所有文件路径用，隔开保存 */
                bookImg.append(dbPath).append(",");
            } else {
                // 格式错误
                String originalFilename = myfile.getOriginalFilename();
                if (!originalFilename.substring(originalFilename.lastIndexOf(".") + 1).equals("jpg")) {
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
                bookPath = user.getLoginName() + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/"
                        + book.getBookNo() + "/" + book.getBookNo() + "-" + i
                        + originalFilename.substring(originalFilename.lastIndexOf("."));
                log.info("bookPath:{}", bookPath);
                /* 数据库路径 */
                dbPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "bookImg.dbpath") + bookPath;
                log.info("bookPath:{}", dbPath);

                // 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
                /* 保存到硬盘 */
                String uploadPath = realPath + bookPath;
                log.info("uploadPath:{}", uploadPath);
                NfsFileUtils.uploadFile(myfile.getInputStream(), new XFileOutputStream(uploadPath));

                /**/
                bookImg.append(dbPath).append(",");
            }
        }
        // 去掉最后一个，号
        book.setBookImg(bookImg.substring(0, bookImg.lastIndexOf(",")));

        /**
         * 插入数据库
         */
        try {
            bookService.insertSelective(book);
        } catch (UserException e) {
            model.addAttribute("book", book);
            model.addAttribute("errorMsg", e.getMessage());
            return "book/uploadBook";
        }

        model.addAttribute("successMsg", "上传成功");
        model.addAttribute("bookImg", book.getBookImg().split(","));
        return "book/uploadBook";
    }

    @RequestMapping(value = "selectBookPages", method = { RequestMethod.GET, RequestMethod.POST })
    public String selectBookPages(Book book, String loginName, Model model, HttpServletRequest request,
            ModelAndView modelAndView, Integer pageNo, Integer pageSize,
            @RequestParam(value = "userId", required = false) String userId) {
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

        model.addAttribute("pages", pages);

        return "indexHome";
    }

    @RequestMapping(value = "selectBookDetail/{bookId}", method = { RequestMethod.GET })
    public String selectBookDetail(@PathVariable("bookId") String bookId, Model model) {
        Book book = bookService.selectByPrimaryKey(bookId);
        model.addAttribute("book", book);
        model.addAttribute("bookImg", book.getBookImg().split(","));
        return "book/bookDetail";
    }

    @RequestMapping(value = "updateBookInput/{bookId}", method = { RequestMethod.GET })
    public String updateBook(@PathVariable("bookId") String bookId, Model model) {
        Book book = bookService.selectByPrimaryKey(bookId);
        model.addAttribute("book", book);
        model.addAttribute("bookImg", book.getBookImg().split(","));
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
            String s = bookImg.substring(0, bookImg.indexOf(",")).substring(bookImg.indexOf("bookImg") + 8);
            String realPath = filePath + s;
            BufferedImage img = ImageIO.read(new File(realPath));
            b.setBookImage(img);
        }

        try {
            ExportToExcelUtil.out(response, bookList);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
