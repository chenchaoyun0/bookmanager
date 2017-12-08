package com.sttx.bookmanager.test;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.util.mq.ActiveMQUtil;
import com.sttx.bookmanager.util.pages.PagedResult;

public class TestReceiveQ {
    private static Logger log = Logger.getLogger(TestReceiveQ.class);

    public static void main(String[] args) {
        String message = ActiveMQUtil.getTextMessage("tLogPagesIsNull");
        log.info("+++++message:{}" + JSONObject.toJSON(message));
        PagedResult<TLog> pages = (PagedResult<TLog>) ActiveMQUtil.getObjectMessage("tLogPages");
        log.info("+++++pages:{}" + JSONObject.toJSON(pages));
        ActiveMQUtil.sendTextMessage("tLogPagesIsNull", "主页被访问了一次");
        ActiveMQUtil.close();
    }

}
