package com.sttx.bookmanager.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.dictionary.ImgLinkType;
import com.sttx.bookmanager.po.EBook;
import com.sttx.bookmanager.po.GridfsImg;
import com.sttx.bookmanager.po.TImg;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.IBaseMongoRepository;
import com.sttx.bookmanager.service.IEBookService;
import com.sttx.bookmanager.service.IImgService;
import com.sttx.bookmanager.util.file.NfsFileUtils;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.properties.PropertiesUtil;
import com.sttx.bookmanager.util.uuidno.UUID2NO;

import cn.itcast.commons.CommonUtils;

@Controller
@RequestMapping("/ebook")
public class EBookController {
    @Autowired
    private IEBookService eBookService;
    @Autowired
    private IImgService imgService;
    private static final Logger log = LoggerFactory.getLogger(EBookController.class);
    @Resource
    private IBaseMongoRepository baseMongoRepository;
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
            @RequestParam MultipartFile ebookFile, @RequestParam MultipartFile ebookImgFile) throws IOException {
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
        String eBookId = CommonUtils.uuid();
        eBook.setEbookId(eBookId);
        eBook.setEbookNo(UUID2NO.getUUID2NO());
        eBook.setUserId(user.getUserId());
        eBook.setEbookUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        eBook.setEbookDownloadCount(0);
        eBook.setEbookFlag(1);
        // 文件
        if (!ebookFile.isEmpty()) {
            String outPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "ebook.dbpathfile");
            String originalFilename = ebookFile.getOriginalFilename();
            String ebookType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            eBook.setEbookType(ebookType);

            GridfsImg gridfsImg = new GridfsImg();
            gridfsImg.setIn(ebookFile.getInputStream());
            gridfsImg.setAliases(eBook.getEbookId());
            gridfsImg.setFileName(originalFilename);
            //
            String url = baseMongoRepository.saveImg(gridfsImg);

            eBook.setEbookPath(url);
            eBook.setEbookSize(Integer.parseInt(ebookFile.getSize() + ""));

        } else {
            model.addAttribute("ebook", eBook);
            model.addAttribute("errorMsg", "请选择上传文件");
            return "ebook/uploadEBook";
        }
        // 图片
        List<TImg> imgList = new ArrayList<>();
        if (ebookImgFile.isEmpty()) {
            TImg tImg = new TImg();
            tImg.setCreateTime(new Date());
            tImg.setCreateUser(user.getLoginName());
            tImg.setImgId(CommonUtils.uuid());
            String outPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "ebook.dbpathimg");
            // 未选择图片择读取默认图片
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ebookimg.jpg");
            String ebookPath = outPath + user.getLoginName() + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/"
                    + eBook.getEbookNo() + "/" + eBook.getEbookNo() + "-" + "defaultebookimg.jpg";
            log.info("ebookPath:{}", ebookPath);
            /* 数据库保存路径 */
            String dbPathImg = ebookPath;
            log.info("dbPathImg:{}", dbPathImg);
            eBook.setEbookImg(dbPathImg);
            /* 保存到硬盘 */
            GridfsImg gridfsImg = new GridfsImg();
            gridfsImg.setIn(inputStream);
            gridfsImg.setAliases(tImg.getImgId());
            gridfsImg.setFileName("defaultebookimg.jpg");
            //
            String url = baseMongoRepository.saveImg(gridfsImg);
            tImg.setImgPath(url);
            tImg.setLastModifyTime(new Date());
            tImg.setLastModifyUser(user.getLoginName());
            tImg.setLinkId(eBookId);
            tImg.setLinkType(ImgLinkType.EBookImg.getCode());
            log.info("保存图片信息begin...tImg:{}", JSONObject.toJSON(tImg));
            imgList.add(tImg);
        } else {
            TImg tImg = new TImg();
            tImg.setCreateTime(new Date());
            tImg.setCreateUser(user.getLoginName());
            tImg.setImgId(CommonUtils.uuid());
            String outPath = PropertiesUtil.getFilePath("uploadFilePath.properties", "ebook.dbpathimg");
            // 格式不对
            String originalFilename = ebookImgFile.getOriginalFilename();
            String imgEnd = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            if (!NfsFileUtils.isImgFile(imgEnd)) {
                model.addAttribute("ebook", eBook);
                model.addAttribute("errorMsg", "请上传正确格式的图片");
                return "ebook/uploadEBook";
            }

            GridfsImg gridfsImg = new GridfsImg();
            gridfsImg.setIn(ebookImgFile.getInputStream());
            gridfsImg.setAliases(eBook.getEbookId());
            gridfsImg.setFileName(originalFilename);
            //
            String url = baseMongoRepository.saveImg(gridfsImg);
            eBook.setEbookImg(url);

        }
        /**
         * 更新数据库
         */
        try {
            eBookService.insertSelective(eBook);
            imgService.batchSaveImg(imgList);
        } catch (Exception e) {
            model.addAttribute("ebook", eBook);
            model.addAttribute("errorMsg", "上传有误");
            return "ebook/uploadEBook";
        }
        List<String> imageBase64StrList = NfsFileUtils.getImageBase64StrList(imgList);
        model.addAttribute("eimg", imageBase64StrList.get(0));
        eBook.setEbookImg(imageBase64StrList.get(0));
        model.addAttribute("successMsg", "上传成功");
        return "ebook/uploadEBook";
    }

    @RequestMapping("/downloadFile/{ebookId}")
    public String downloadFile(@PathVariable("ebookId") String ebookId, Model model, HttpServletResponse response,
            HttpServletRequest request) throws Exception {

        EBook eBook = eBookService.selectByPrimaryKey(ebookId);
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
        InputStream bis = null;
        try {
      String idstr = StringUtils.substringAfterLast(ebookPath, "/");
      bis = baseMongoRepository.getInputStreamById(new ObjectId(idstr));
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copy(bis, outputStream);
        } catch (Exception e) {
            request.setAttribute("msg", "要下载的文件不存在" + e.getMessage());
            return "forward:/error/msg.jsp";
        } finally {
            if (bis != null)
                bis.close();
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
        // 得到文件
        if (i > 0) {
          String ebookPath = eBook.getEbookPath();
          String idstr = StringUtils.substringAfterLast(ebookPath, "/");
          baseMongoRepository.delFile(new ObjectId(idstr));
        }

        return "forward:/ebook/selectEBookPages";
    }
}
