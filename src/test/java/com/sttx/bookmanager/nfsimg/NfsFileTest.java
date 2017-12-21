package com.sttx.bookmanager.nfsimg;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.ImageBase64Vo;
import com.sttx.bookmanager.po.ImageVo;
import com.sttx.bookmanager.po.ResumeVo;
import com.sttx.bookmanager.po.ScrollImage;
import com.sttx.bookmanager.util.LogUtil;
import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.bookmanager.util.file.NfsFileUtils;
import com.sttx.bookmanager.util.properties.PropertiesUtil;
import com.sttx.ddp.logger.DdpLoggerFactory;
import com.sun.xfile.XFileOutputStream;

/**
 * 
 * @Description 共享文件路径测试
 * @author chenchaoyun[chenchaoyun@sttxtech.com]
 * @date 2017年6月22日 下午5:03:47
 */
public class NfsFileTest {
    private static Logger log = DdpLoggerFactory.getLogger(NfsFileTest.class);
    private static String nfsUrl = null;
    static {
        nfsUrl = PropertiesUtil.getFilePath("properties/nfs.properties", "nfsUrl");
    }
    @Test
    public void read() {
        /**
         * read
         */
        log.info("nfsUrl:{}", nfsUrl);
        String nfsFileName = nfsUrl + "test.txt";
        String localFileName = "H:\\test.txt";
        NfsFileUtils.copyNfsFile2Local(nfsFileName, localFileName);
    }

    @Test
    public void readDocx() {
        /**
         * read
         */
        String nfsFileName = nfsUrl + "resume/docx/chenchaoyun-resume.docx";
        log.info("nfsFileName:{}", nfsFileName);
        InputStream inputStream = NfsFileUtils.readNfsFile2Stream(nfsFileName);
    }

    @Test
    public void readImg() throws UserException {
        /**
         * read
         */
        log.info("nfsUrl:{}", nfsUrl);
        String nfsFileName = nfsUrl + "ad.jpg";
        byte[] bs = NfsFileUtils.readNfsFile2Byte(nfsFileName);
        log.info("bs length:{}", bs.length);
        String imageBase64Str = NfsFileUtils.getImageBase64Str(bs);
        log.info("imageBase64Str:{}", imageBase64Str);
    }

    @Test
    public void writeImg() throws Exception {
        InputStream input = new FileInputStream(new File("H:\\ad.jpg"));
        boolean b = NfsFileUtils.mkdirFile("nfs://39.108.0.229:/u01/upload/bookImg/chenchaoyun/2017-08-26/564810052569910/1/");
        log.info("b:{}", b);
        int i = NfsFileUtils.uploadFile(input, new XFileOutputStream(
                "nfs://39.108.0.229:/u01/upload/bookImg/chenchaoyun/2017-08-26/564810052569910/1/564810052569910-defaultBookImg--1.jpg"));
        log.info("i:{}", i);
    }

    @Test
    public void readResumeVo() {
        ResumeVo resumeVo = new ResumeVo();
        ImageVo imageVo = new ImageVo();
        String baseDir = NfsFileUtils.getNfsUrl() + "images/";
        // 头像
        String myPhoto = null;
        String myPhotoDir = "myPhoto/logo.png";
        myPhoto = NfsFileUtils.getImageBase64Str(baseDir + myPhotoDir);
        // log.info("myPhoto:{}", myPhoto);
        imageVo.setMyPhoto(myPhoto);
        /**
         * 滚动图
         */
        List<ScrollImage> scrollImageList = new ArrayList<>();
        String scrollImageDir = baseDir + "scrollImage/";
        String[] filesScrollImage = NfsFileUtils.readDirFiles(scrollImageDir);
        log.info("filesScrollImage:{}", JSONObject.toJSON(filesScrollImage));
        for (String fileName : filesScrollImage) {
            String imageBase64Str = NfsFileUtils.getImageBase64Str(scrollImageDir + fileName);
            ScrollImage scrollImage = new ScrollImage();
            String imgDesc = "";
            if (fileName.startsWith("01")) {
                imgDesc = "工作之余学习，搭建的gerrit+jenkins 持续集成系统";
            } else if (fileName.startsWith("02")) {
                imgDesc = "实习期练手，基于Spring、Springmvc、Mybatis开发的图书馆管理系统";
            } else if (fileName.startsWith("03")) {
                imgDesc = "大学时期，手机电子商城JavaWeb项目";

            } else if (fileName.startsWith("04")) {
                imgDesc = "实习期项目，Zookeeper 节点管理系统";

            } else if (fileName.startsWith("05")) {
                imgDesc = "工作之余学习，搭建的gerrit+jenkins 持续集成系统";
            } else if (fileName.startsWith("06")) {

            } else {
                imgDesc = "this image has no name";
            }
            scrollImage.setImageDesc(imgDesc);
            scrollImage.setImageStr(imageBase64Str);
            scrollImageList.add(scrollImage);
        }
        imageVo.setScrollImageList(scrollImageList);
        /**
         * springboot
         */
        String springbootDir = baseDir + "springBootImage/";
        List<ImageBase64Vo> springBootImageList = getBase64StrList(springbootDir);
        imageVo.setSpringBootImageList(springBootImageList);
        log.info("springBootImageList:{}", springBootImageList.size());
        /**
         * devps
         */
        String devpsDir = baseDir + "devpsImage/";
        List<ImageBase64Vo> devpsImageList = getBase64StrList(devpsDir);
        imageVo.setDevpsImageList(devpsImageList);
        log.info("devpsImageList:{}", devpsImageList.size());
        /**
         * apps
         */
        String appsDir = baseDir + "appsImage/";
        List<ImageBase64Vo> appsImageList = getBase64StrList(appsDir);
        imageVo.setAppsImageList(appsImageList);
        log.info("appsImageList:{}", appsImageList.size());
        //
        resumeVo.setImageVo(imageVo);

        log.info("resumeVo:{}", LogUtil.formatLog(resumeVo.getImageVo()));
    }

    private List<ImageBase64Vo> getBase64StrList(String dir) {
        List<ImageBase64Vo> arrayList = new ArrayList<>();
        String[] dirImage = NfsFileUtils.readDirFiles(dir);
        log.info("dir:{}", JSONObject.toJSON(dirImage));
        for (String fileName : dirImage) {
            String imageBase64Str = NfsFileUtils.getImageBase64Str(dir + fileName);
            ImageBase64Vo imageBase64Vo = new ImageBase64Vo();
            imageBase64Vo.setBase64Str(imageBase64Str);
            arrayList.add(imageBase64Vo);
        }
        return arrayList;
    }

}
