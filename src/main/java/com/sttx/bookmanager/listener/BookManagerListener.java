package com.sttx.bookmanager.listener;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.init.InitSpring;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;

public class BookManagerListener implements MessageListener {
    private static Logger log = Logger.getLogger(InitSpring.class);
    private static ClassPathXmlApplicationContext cpxac;
    private ILogService logService;
    static {
        cpxac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
    }

    public void onMessage(Message m) {
        try {

            if (m instanceof TextMessage) { //接收文本消息   
                TextMessage message = (TextMessage) m;
                System.out.println("文本消息" + message.getText());
            } else if (m instanceof MapMessage) { //接收键值对消息   
                MapMessage message = (MapMessage) m;
                System.out.println("键值对消息 ");
            } else if (m instanceof StreamMessage) { //接收流消息   
                StreamMessage message = (StreamMessage) m;
                System.out.println("流消息" + message.readString());
                System.out.println("流消息" + message.readLong());
            } else if (m instanceof BytesMessage) { //接收字节消息   
                byte[] b = new byte[1024];
                int len = -1;
                BytesMessage message = (BytesMessage) m;
                while ((len = message.readBytes(b)) != -1) {
                    System.out.println("字节消息" + new String(b, 0, len));
                    String jsonstr = new String(b, 0, len);
                    System.out.println(jsonstr);
                }
            } else if (m instanceof ObjectMessage) { //接收对象消息   
                ObjectMessage message = (ObjectMessage) m;
                logService = cpxac.getBean("logService", ILogService.class);
                TLog tLog = (TLog) message.getObject();
                log.info("+++++读取到的消息:" + JSONObject.toJSONString(tLog));
                int i = logService.insert(tLog);
                log.info("+++++更新日志表+" + i);
                log.info("+++++收到消息的ID：" + m.getJMSMessageID() + "收到消息的优先级：" + m.getJMSPriority());
            } else {
                System.out.println(m);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
