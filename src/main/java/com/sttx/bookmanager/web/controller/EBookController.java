package com.sttx.bookmanager.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sttx.bookmanager.po.EBook;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.IEBookService;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.properties.PropertiesUtil;
import com.sttx.bookmanager.util.uuidno.UUID2NO;

import cn.itcast.commons.CommonUtils;

@Controller
@RequestMapping("/ebook")
public class EBookController {
    @Autowired
    private IEBookService eBookService;

    @RequestMapping(value = "/uploadEBookInput", method = RequestMethod.GET)
    public String uploadEBookInput() {
        return "ebook/uploadEBook";
    }

    @RequestMapping(value = "/selectEBookPages", method = { RequestMethod.GET, RequestMethod.POST })
    public String selectEBookPages(EBook eBook, String loginName, Model model, ModelAndView modelAndView,
            HttpServletRequest request, Integer pageNo, Integer pageSize,
            @RequestParam(value = "userId", required = false) String userId) {
        User user = new User();
        user.setLoginName(loginName);
        eBook.setUser(user);
        if (!"".equals(userId) && userId != null) {
            eBook.setUserId(userId);
        }
        PagedResult<EBook> pages = eBookService.selectEBookPages(eBook, pageNo, pageSize);
        String url = request.getRequestURI();
        pages.setUrl(url);
        model.addAttribute("eBook", eBook);
        model.addAttribute("pages", pages);

        return "ebook/eBookList";
    }

    @RequestMapping(value = "/uploadEBookSubmit", method = RequestMethod.POST)
    public String uploadEBookSubmit(@ModelAttribute("eBook") EBook eBook, Model model, HttpServletRequest request,
            @RequestParam MultipartFile ebookFile, @RequestParam MultipartFile ebookImgFile) {
        /* 验证码 */
        // ...方便测试暂时不做校验
        String sessionCode = (String) request.getSession().getAttribute("session_vcode");
        String paramCode = request.getParameter("verifyCode");
        if (!paramCode.equalsIgnoreCase(sessionCode)) {// 验证码
            model.addAttribute("ebook", eBook);
            request.setAttribute("error_code", "验证码错误");
            return "ebook/uploadEBook";
        }
        /* 获取登陆用户 */
        User user = (User) request.getSession().getAttribute("userLogin");
        eBook.setEbookId(CommonUtils.uuid());
        eBook.setEbookNo(UUID2NO.getUUID2NO());
        eBook.setUserId(user.getUserId());
        eBook.setEbookUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        eBook.setEbookDownloadCount(0);
        eBook.setEbookFlag(1);
        // 文件
        if (!ebookFile.isEmpty()) {
            String realPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "ebook.FilePath");
            String originalFilename = ebookFile.getOriginalFilename();
            String ebookType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            eBook.setEbookType(ebookType);
            String ebookPath = user.getLoginName() + "/" + ebookType + "/"
                    + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/" + eBook.getEbookNo() + "/"
                    + eBook.getEbookNo() + "-" + originalFilename;
            /* 数据库保存路径 */
            String dbpathfile = PropertiesUtil.getFilePath("uploadFilePath.properties", "ebook.dbpathfile") + ebookPath;
            eBook.setEbookPath(dbpathfile);
            eBook.setEbookSize(Integer.parseInt(ebookFile.getSize() + ""));
            try {
                FileUtils.copyInputStreamToFile(ebookFile.getInputStream(), new File(realPath + ebookPath));
            } catch (Exception e) {
                model.addAttribute("ebook", eBook);
                model.addAttribute("errorMsg", "上传失败");
                return "ebook/uploadEBook";
            }

        } else {
            model.addAttribute("ebook", eBook);
            model.addAttribute("errorMsg", "请选择上传文件");
            return "ebook/uploadEBook";
        }
        // 图片
        if (ebookImgFile.isEmpty()) {
            // 未选择图片择读取默认图片
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ebookimg.jpg");
            String realPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "ebook.ImgPath");
            String ebookPath = user.getLoginName() + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/"
                    + eBook.getEbookNo() + "/" + eBook.getEbookNo() + "-" + "defaultebookimg.jpg";
            /* 数据库保存路径 */
            String dbPathImg = PropertiesUtil.getFilePath("uploadFilePath.properties", "ebook.dbpathimg") + ebookPath;
            eBook.setEbookImg(dbPathImg);
            /* 保存到硬盘 */
            try {
                FileUtils.copyInputStreamToFile(inputStream, new File(realPath + ebookPath));
            } catch (IOException e) {
                model.addAttribute("ebook", eBook);
                model.addAttribute("errorMsg", "上传出错");
                return "ebook/uploadEBook";
            }
        } else {
            // 格式不对
            String originalFilename = ebookImgFile.getOriginalFilename();
            if (!originalFilename.substring(originalFilename.lastIndexOf(".") + 1).equals("jpg")) {
                model.addAttribute("ebook", eBook);
                model.addAttribute("errorMsg", "请上传jpg格式的图片");
                return "ebook/uploadEBook";
            }

            String realPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "ebook.ImgPath");
            String ebookPath = user.getLoginName() + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/"
                    + eBook.getEbookNo() + "/" + eBook.getEbookNo() + "-" + originalFilename;
            /* 数据库保存路径 */
            String dbPathImg = PropertiesUtil.getFilePath("uploadFilePath.properties", "ebook.dbpathimg") + ebookPath;
            eBook.setEbookImg(dbPathImg);
            /* 保存到硬盘 */
            try {
                FileUtils.copyInputStreamToFile(ebookImgFile.getInputStream(), new File(realPath + ebookPath));
            } catch (IOException e) {
                model.addAttribute("ebook", eBook);
                model.addAttribute("errorMsg", "上传不行");
                return "ebook/uploadEBook";
            }

        }
        /**
         * 更新数据库
         */
        try {
            eBookService.insertSelective(eBook);
        } catch (Exception e) {
            model.addAttribute("ebook", eBook);
            model.addAttribute("errorMsg", "上传有误");
            return "ebook/uploadEBook";
        }
        model.addAttribute("successMsg", "上传成功");
        return "ebook/uploadEBook";
    }

    @RequestMapping("/downloadFile/{ebookId}")
    public String downloadFile(@PathVariable("ebookId") String ebookId, Model model, HttpServletResponse response,
            HttpServletRequest request) throws Exception {

        EBook eBook = eBookService.selectByPrimaryKey(ebookId);
        String realPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "ebook.FilePath");
        String ebookPath = eBook.getEbookPath();
        String s1 = ebookPath.substring(ebookPath.lastIndexOf("/") + 1);
        String fileName = s1.substring(s1.indexOf("-") + 1);
        fileName = new String(fileName.getBytes("UTF-8"), "iso8859-1");
        response.reset();// 去除空白行
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 指定下载的文件名
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            File file = new File(realPath + ebookPath);
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            request.setAttribute("msg", "要下载的文件不存在" + e.getMessage());
            return "forward:/error/msg.jsp";
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
            int i = eBookService.updateDownloadCount(eBook.getEbookId());// 更新下载量
            System.out.println(i);
        }
        return null;
    }

    @RequestMapping(value = "/deleteEBook/{eBookId}", method = RequestMethod.GET)
    public String deleteEBook(@PathVariable("eBookId") String eBookId, HttpServletRequest request) {
        /**
         * 验证用户
         */
        EBook eBook = eBookService.selectByPrimaryKey(eBookId);
        User userLogin = (User) request.getSession().getAttribute("userLogin");
        if (!userLogin.getUserId().equals(eBook.getUserId()) && userLogin.getUserRole() != 1) {
            return "forward:/ebook/selectEBookPages";
        }

        int i = eBookService.deleteByPrimaryKey(eBookId);
        String realPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "ebook.FilePath");
        // 得到文件
        if (i > 0) {
            File file = new File(realPath + eBook.getEbookPath());
            if (file.exists()) {
                try {
                    FileUtils.deleteQuietly(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return "forward:/ebook/selectEBookPages";
    }
}
