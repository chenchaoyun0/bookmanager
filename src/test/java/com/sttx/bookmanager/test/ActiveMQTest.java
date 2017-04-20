package com.sttx.bookmanager.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sttx.ddp.logger.DdpLoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class ActiveMQTest {
    private static final Logger logger = DdpLoggerFactory.getLogger(ActiveMQTest.class);

    @Test
    public void testSelectByPrimaryKey() {

    }

}
