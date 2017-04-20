package com.sttx.bookmanager.init;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.sttx.bookmanager.listener.BookManagerListener;
import com.sttx.bookmanager.util.mq.ActiveMQUtil;

public class InitSpring implements ApplicationListener<ApplicationEvent> {
    private static Logger log = Logger.getLogger(InitSpring.class);

    @Override
    public void onApplicationEvent(ApplicationEvent arg0) {
        log.info("+++++初始化容器开始...");
        ActiveMQUtil.setMessageListener(new BookManagerListener(), "tLog");
    }

}
