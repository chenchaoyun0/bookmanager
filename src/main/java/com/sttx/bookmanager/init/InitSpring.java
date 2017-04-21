package com.sttx.bookmanager.init;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sttx.bookmanager.service.ILogService;

@Component
public class InitSpring implements ApplicationListener<ApplicationEvent> {
    private static Logger log = Logger.getLogger(InitSpring.class);
    @Autowired
    private ILogService logService;

    @Override
    public void onApplicationEvent(ApplicationEvent arg0) {
        log.info("+++++初始化容器开始...");
        //        String tLogName = PropertiesUtil.getFilePath("properties/activemq.properties", "tLogName");
        //        ActiveMQUtil.setMessageListener(new BookManagerTLogListener(), tLogName);
        //        //---初始化
        //        TLog tLog = new TLog();
        //        PagedResult<TLog> pages = logService.selectLogPages(tLog, null, null);
        //        Long totalc = logService.selectLogSumCount();
        //        String tLogPages = PropertiesUtil.getFilePath("properties/activemq.properties", "tLogPages");
        //        String totalcount = PropertiesUtil.getFilePath("properties/activemq.properties", "totalcount");
        //        ActiveMQUtil.sendObjectMessage(tLogPages, pages);
        //        ActiveMQUtil.sendObjectMessage(tLogPages, pages);
        //        ActiveMQUtil.sendTextMessage(totalcount, totalc + "");
        //        ActiveMQUtil.sendTextMessage(totalcount, totalc + "");
    }

}
