package com.sttx.bookmanager.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;

import cn.itcast.commons.CommonUtils;

public class ThreadMain {
    private static int count = 0;
    private static final Logger logger = LoggerFactory.getLogger(ThreadMain.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-*.xml");
        logger.info("+++++context:" + context);
        final ILogService iLogService = context.getBean(ILogService.class);
        logger.info("+++++iLogService:" + iLogService);
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TLog tLog = new TLog();
                        tLog.setAction("indexHome");
                        tLog.setActionTime(29l);
                        tLog.setCount(0l);
                        tLog.setLogId(CommonUtils.uuid());
                        tLog.setModule("IndexHomeController");
                        tLog.setUserJwd("未知");
                        tLog.setOperTime("2017-03-09 02:29:38:655");
                        tLog.setUserAddress("未分配或者内网IP----");
                        tLog.setUserIp("127.0.0.1");
                        tLog.setUserName("游客用户");
                        tLog.setUserNickName("游客用户");
                        logger.info("insertSelective~:{}" + JSONObject.toJSONString(tLog));
                        int i = iLogService.insert(tLog);
                        logger.info("insertSelective~:{}" + i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
            }).start();
        }
        System.out.println("--->count:" + count);
    }
}
