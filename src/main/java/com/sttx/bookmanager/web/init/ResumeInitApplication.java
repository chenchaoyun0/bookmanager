package com.sttx.bookmanager.web.init;

import org.slf4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sttx.bookmanager.po.ImageVo;
import com.sttx.bookmanager.util.LogUtil;
import com.sttx.bookmanager.util.ResumeUtils;
import com.sttx.ddp.logger.DdpLoggerFactory;

@Component
public class ResumeInitApplication implements ApplicationRunner {
    private static final Logger log = DdpLoggerFactory.getLogger(ResumeInitApplication.class);

    @Override
    public void run(ApplicationArguments var1) throws Exception {
        try {
            log.info("启动加载简历图片begin...");
            ResumeUtils.initResumeVo();
            log.info("启动加载简历图片end...");
            ImageVo imageVo = ResumeUtils.getResumeVo().getImageVo();
            log.info("加载简历图片 imageVo:{}", LogUtil.formatLog(imageVo));
        } catch (Exception e) {
            log.error("启动加载简历图片异常:{}", e);
        }
    }

}
