package com.sttx.bookmanager.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.ddp.logger.DdpLoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class LogTest {
    private static final Logger logger = DdpLoggerFactory.getLogger(LogTest.class);
    @Autowired
    private ILogService logService;

    @Test
    public void testSelectByPrimaryKey() {
        /**
         * {
        "action": "indexHome",
        "actionTime": 29,
        "count": 0,
        "logId": "A55D9D20B88D421D92FA96D986EC65D2",
        "module": "IndexHomeController",
        "operTime": "2017-03-09 02:29:38:655",
        "userAddress": "未分配或者内网IP----",
        "userIp": "127.0.0.1",
        "userJwd": "未知",
        "userName": "游客用户",
        "userNickName": "未设置"
        }
         */
        TLog tLog = new TLog();
        tLog.setAction("indexHome");
        tLog.setActionTime(29l);
        tLog.setCount(0l);
        tLog.setLogId("A55D9D20B88D421D92FA96D986EC65D2");
        tLog.setModule("IndexHomeController");
        tLog.setUserJwd("未知");
        tLog.setOperTime("2017-03-09 02:29:38:655");
        tLog.setUserAddress("未分配或者内网IP----");
        tLog.setUserIp("127.0.0.1");
        tLog.setUserName("游客用户");
        tLog.setUserNickName("游客用户");
        logger.info("insertSelective~:{}" + JSONObject.toJSONString(tLog));
        int i = logService.insertSelective(tLog);
        logger.info("insertSelective~:{}" + i);

    }

}
