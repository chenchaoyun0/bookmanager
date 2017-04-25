package com.sttx.bookmanager.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.mq.ActiveMQUtil;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.properties.PropertiesUtil;
import com.sttx.bookmanager.util.spring.SpringUtils;

public class BookManagerTLogPagesIsNullListener implements MessageListener {
    private static Logger log = Logger.getLogger(BookManagerTLogPagesIsNullListener.class);

    public void onMessage(Message m) {
        log.info("+++++读取到的消息SimpleName:" + m.getClass().getSimpleName());
        try {
            ILogService logService = SpringUtils.getBean("logService", ILogService.class);
            TLog tLog = new TLog();
            PagedResult<TLog> pages = logService.selectLogPages(tLog, null, null);
            Long totalc = logService.selectLogSumCount();
            String tLogPages = PropertiesUtil.getFilePath("properties/activemq.properties", "tLogPages");
            String totalcount = PropertiesUtil.getFilePath("properties/activemq.properties", "totalcount");
            ActiveMQUtil.sendObjectMessage(tLogPages, pages);
            ActiveMQUtil.sendTextMessage(totalcount, totalc + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
