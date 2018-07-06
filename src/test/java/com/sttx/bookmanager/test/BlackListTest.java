package com.sttx.bookmanager.test;

import java.util.Date;
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
import com.sttx.bookmanager.dao.BlackListMapper;
import com.sttx.bookmanager.po.BlackLisEntity;
import com.sttx.bookmanager.po.VisitorProfile;
import com.sttx.bookmanager.service.IVisitorProfileService;
import com.sttx.bookmanager.util.time.DateConvertUtils;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

@ContextConfiguration(locations = { "classpath:spring/applicationContext-dao.xml", "classpath:spring/applicationContext-service.xml",
    "classpath:spring/applicationContext-transation.xml" })
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest
@Slf4j
public class BlackListTest {
  private static final Logger logger = LoggerFactory.getLogger(BlackListTest.class);
  @Autowired
  private BlackListMapper blackListMapper;

  @Test
  public void visitors() {
    String lasttime = DateConvertUtils.format(new Date(), DateConvertUtils.DATE_TIME_FORMAT);
    String path ="/test";
    //
    Example example = new Example(BlackLisEntity.class);
    example.createCriteria().andEqualTo("ip", "203.208.60.168");
    BlackLisEntity blackLisEntity = blackListMapper.selectOneByExample(example);

    if (blackLisEntity != null) {
      // 更新次数
      blackLisEntity.setBrowserAndVersion("setBrowserAndVersion");
      blackLisEntity.setCount(blackLisEntity.getCount() + 1);
      blackLisEntity.setLasttime(lasttime);
      blackLisEntity.setPath(path);
      //
      Example exampleUpdate = new Example(BlackLisEntity.class);
      exampleUpdate.createCriteria().andEqualTo("id", 1l);
      int updateByPrimaryKey = blackListMapper.updateByExampleSelective(blackLisEntity, exampleUpdate);
      log.info("已被禁止访问,updateByPrimaryKey:{}",  updateByPrimaryKey);

    }
  }

}
