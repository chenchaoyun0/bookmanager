package com.sttx.bookmanager.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.spring.SpringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class ActiveMQTest {
  private static final Logger logger = LoggerFactory.getLogger(ActiveMQTest.class);

    @Test
    public void testSelectByPrimaryKey() {
        ILogService logService = SpringUtils.getBean("logService", ILogService.class);
        logger.info("+++++logService:{}", logService);
    }

}
