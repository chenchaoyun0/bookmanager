package com.sttx.bookmanager.web.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ResumeInitApplication implements ApplicationRunner {
  private static final Logger log = LoggerFactory.getLogger(ResumeInitApplication.class);

    @Override
    public void run(ApplicationArguments var1) throws Exception {
        try {
            log.info("启动初始化执行操作begin...");
            // log.info("启动加载简历图片begin...");
            // ResumeUtils.initResumeVo();
            // log.info("启动加载简历图片end...");
            // ImageVo imageVo = ResumeUtils.getResumeVo().getImageVo();
            // log.info("加载简历图片 imageVo:{}", LogUtil.formatLog(imageVo));
        } catch (Exception e) {
            log.error("启动初始化执行操作异常:{}", e);
        }
    }

}
