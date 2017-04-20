package com.sttx.bookmanager.test;

import org.apache.log4j.Logger;

import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.util.mq.ActiveMQUtil;

public class TestReceiveQ {
    private static Logger log = Logger.getLogger(TestReceiveQ.class);

    public static void main(String[] args) {
        String message = ActiveMQUtil.getTextMessage("ccyQueue");
        log.info("接收到的:" + message);
        //-----------------
        for (int i = 0; i < 10; i++) {
            User user = (User) ActiveMQUtil.getObjectMessage("ccyObj");
            log.info("接收到的:" + user);
        }
        log.info("sendQueues size:" + ActiveMQUtil.getQueues.size());
        ActiveMQUtil.close();
    }

}
