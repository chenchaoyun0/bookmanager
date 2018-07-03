package com.sttx.bookmanager.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.VisitorProfile;
import com.sttx.bookmanager.service.IVisitorProfileService;

@ContextConfiguration(locations = { "classpath:spring/applicationContext-dao.xml", "classpath:spring/applicationContext-service.xml",
    "classpath:spring/applicationContext-transation.xml" })
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest
public class VisitorProfileTest {
  private static final Logger logger = LoggerFactory.getLogger(VisitorProfileTest.class);
  @Autowired
  private IVisitorProfileService visitorProfileService;

  @Test
  public void visitors() {
    List<VisitorProfile> visitors = visitorProfileService.visitors();
    logger.info("insertPo:{}", JSONObject.toJSONString(visitors));
  }

}
