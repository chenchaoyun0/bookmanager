package com.sttx.bookmanager.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.sttx.bookmanager.init.InitSpring;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.mq.ActiveMQUtil;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.properties.PropertiesUtil;
import com.sttx.bookmanager.util.spring.SpringUtils;

public class BookManagerTLogListListener implements MessageListener {
    private static Logger log = Logger.getLogger(InitSpring.class);

    public void onMessage(Message m) {
        try {
            TextMessage msg = (TextMessage) m;
            log.info("+++++读取到的消息:" + msg.getText());
            log.info("+++++收到消息的ID：" + m.getJMSMessageID() + "收到消息的优先级：" + m.getJMSPriority());
            ILogService logService = SpringUtils.getBean("logService", ILogService.class);
            //---初始化
            TLog tLog = new TLog();
            PagedResult<TLog> pages = logService.selectLogPages(tLog, null, null);
            Long totalc = logService.selectLogSumCount();
            String tLogPages = PropertiesUtil.getFilePath("properties/activemq.properties", "tLogPages");
            String totalcount = PropertiesUtil.getFilePath("properties/activemq.properties", "totalcount");
            ActiveMQUtil.sendObjectMessage(tLogPages, pages);
            ActiveMQUtil.sendObjectMessage(tLogPages, pages);
            ActiveMQUtil.sendTextMessage(totalcount, totalc + "");

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
