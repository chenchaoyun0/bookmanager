package com.sttx.bookmanager.test;

import org.apache.log4j.Logger;

import com.sttx.bookmanager.util.mq.ActiveMQUtil;

public class TestSendQ {
    private static Logger log = Logger.getLogger(TestSendQ.class);

    public static void main(String[] args) {
        //        TLog tLog = new TLog();
        //        tLog.setLogId(CommonUtils.uuid());
        //        tLog.setAction("dasdasd");
        //        tLog.setUserIp("127.0.0.1");
        //        tLog.setActionTime(1110l);
        //        tLog.setCount(10l);
        //        tLog.setModule("dasd");
        //        tLog.setOperTime("e2343");
        //        tLog.setUserAddress("dsavfdds");
        //        tLog.setUserJwd("Dasdsa");
        //        tLog.setUserName("ccy");
        //        tLog.setUserNickName(" ccy 陈超允");
        //        ActiveMQUtil.sendObjectMessage("tLog", tLog);
        //
        ActiveMQUtil.sendTextMessage("tLogPagesIsNull", "主页被访问了一次");
        ActiveMQUtil.close();
    }

}
