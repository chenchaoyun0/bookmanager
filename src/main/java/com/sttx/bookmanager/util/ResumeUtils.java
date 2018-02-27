package com.sttx.bookmanager.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.ImageBase64Vo;
import com.sttx.bookmanager.po.ImageVo;
import com.sttx.bookmanager.po.ResumeVo;
import com.sttx.bookmanager.po.ScrollImage;
import com.sttx.bookmanager.util.file.NfsFileUtils;

public class ResumeUtils {
  private static final Logger log = LoggerFactory.getLogger(ResumeUtils.class);
    private static ResumeVo resumeVo = new ResumeVo();

    public static ResumeVo getResumeVo() {
        return resumeVo;
    }

    public static void initResumeVo() {
        try {
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
        } catch (Exception e) {
            log.error("加载简历图片异常:{}", e);
        }
    }

    private static List<ImageBase64Vo> getBase64StrList(String dir) {
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
