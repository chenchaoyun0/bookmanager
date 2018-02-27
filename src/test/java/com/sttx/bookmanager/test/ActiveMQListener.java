package com.sttx.bookmanager.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sttx.bookmanager.listener.BookManagerTLogListener;

public class ActiveMQListener {
  private static final Logger logger = LoggerFactory.getLogger(ActiveMQListener.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpxac
            = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        BookManagerTLogListener bean = cpxac.getBean("bookManagerTLogListener", BookManagerTLogListener.class);
        logger.info("+++++" + bean);
    }

}
