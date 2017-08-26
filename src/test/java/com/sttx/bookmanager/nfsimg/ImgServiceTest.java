package com.sttx.bookmanager.nfsimg;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.dictionary.ImgLinkType;
import com.sttx.bookmanager.po.TImg;
import com.sttx.bookmanager.service.IImgService;
import com.sttx.ddp.logger.DdpLoggerFactory;

import cn.itcast.commons.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
@WebAppConfiguration
public class ImgServiceTest {
    private static final Logger logger = DdpLoggerFactory.getLogger(ImgServiceTest.class);
    @Autowired
    private IImgService imgService;

    @Test
    public void testSelectByPrimaryKey() {
        TImg tImg = imgService.selectByPrimaryKey("");
        logger.info("tImg>>>>>>>>>>>>>>>>>>>>>:{}", JSONObject.toJSON(tImg));
    }

    @Test
    public void testSelectList() {
        TImg t=new TImg();
        List<TImg> selectList = imgService.selectList(t);
        logger.info("selectList>>>>>>>>>>>>>>>>>>>>>:{}", JSONObject.toJSON(selectList));
    }

    @Test
    @Ignore
    public void insertSelective() {
        TImg tImg = new TImg();
        tImg.setImgId(CommonUtils.uuid());
        tImg.setCreateTime(new Date());
        tImg.setCreateUser("chenchaoyun");
        tImg.setImgPath("bookImg/xx/xx");
        tImg.setLinkId("xxx");
        tImg.setLinkType(ImgLinkType.BookImg.getCode());
        int i = imgService.insertSelective(tImg);
        logger.info("tImg>>>>>>>>>>>>>>>>>>>>>:{}", i);
    }


}
