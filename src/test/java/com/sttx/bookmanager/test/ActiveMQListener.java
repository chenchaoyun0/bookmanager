package com.sttx.bookmanager.test;

import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sttx.bookmanager.init.InitSpring;
import com.sttx.ddp.logger.DdpLoggerFactory;

public class ActiveMQListener {
    private static final Logger logger = DdpLoggerFactory.getLogger(ActiveMQListener.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpxac = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-*.xml");
        InitSpring bean = cpxac.getBean("initSpring", InitSpring.class);
        logger.info("+++++" + bean);
    }

}
