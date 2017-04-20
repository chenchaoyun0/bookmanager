package com.sttx.bookmanager.util.mq;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.sttx.bookmanager.listener.BookManagerListener;
import com.sttx.bookmanager.util.properties.PropertiesUtil;

public class ActiveMQUtil {
    // ConnectionFactory ：连接工厂，JMS 用它创建连接
    private static ConnectionFactory connectionFactory;
    // Connection ：JMS 客户端到JMS Provider 的连接
    private static Connection connection = null;
    // Session： 一个发送或接收消息的会话
    private static Session session;
    private static Logger log = Logger.getLogger(ActiveMQUtil.class);
    // sendQueues：发送方队列map
    public static Map<String, MessageProducer> sendQueues = new ConcurrentHashMap<String, MessageProducer>();
    // getQueues：消费方队列map
    public static Map<String, MessageConsumer> getQueues = new ConcurrentHashMap<String, MessageConsumer>();
    static {
        log.info("----------------初始化消息队列-----------------");
        String url = PropertiesUtil.getFilePath("properties/activemq.properties", "mq.activemqUrl");
        log.info("----------------" + url);
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, url);
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE.booleanValue(), 1);
        } catch (Exception e) {
            log.error("初始化消息队列连接异常", e);
        }
    }

    public static MessageProducer getMessageProducer(String sendQueueName) {
        log.info("----------------获取MessageProducer-----------------");
        if (sendQueues.containsKey(sendQueueName))
            return ((MessageProducer) sendQueues.get(sendQueueName));
        try {
            // Destination ：消息的目的地;消息发送给谁
            Destination destination = session.createQueue(sendQueueName);
            // 得到消息发送者
            MessageProducer producer = session.createProducer(destination);
            // 放入Map
            sendQueues.put(sendQueueName, producer);
            return producer;
        } catch (JMSException e) {
            log.error("获取消息队列MessageProducer异常", e);
        }

        return ((MessageProducer) sendQueues.get(sendQueueName));
    }

    public static MessageConsumer getMessageConsumer(String getQueueName) {
        log.info("----------------获取MessageConsumer-----------------");
        if (getQueues.containsKey(getQueueName))
            return ((MessageConsumer) getQueues.get(getQueueName));
        try {
            // Destination ：消息的目的地;消息发送给谁
            Destination destination = session.createQueue(getQueueName);
            // 得到消息发送者
            MessageConsumer consumer = session.createConsumer(destination);
            // 放入Map
            getQueues.put(getQueueName, consumer);
            return consumer;
        } catch (JMSException e) {
            log.error("获取消息队列MessageConsumer异常", e);
        }

        return ((MessageConsumer) getQueues.get(getQueueName));
    }

    public static void sendTextMessage(String sendQueueName, String text) {
        log.info("----------------发送文本消息sendTextMessage-----------------");
        try {
            // 创建文本类消息队列
            TextMessage message = session.createTextMessage(text);
            getMessageProducer(sendQueueName).send(message);
            log.info("----------------发出文本消息 :{}" + sendQueueName + "data:{}-----------------" + text);
        } catch (JMSException e) {
            log.error("sendTextMessage 发送文本消息异常", e);
        }
    }

    public static void sendObjectMessage(String sendQueueName, Serializable serial) {
        log.info("----------------发送对象消息sendObjectMessage-----------------");
        try {
            // 创建对象类消息队列
            ObjectMessage message = session.createObjectMessage(serial);
            getMessageProducer(sendQueueName).send(message);
            log.info("----------------发出对象消息 :{}" + sendQueueName + "data:{}-----------------" + serial);
        } catch (JMSException e) {
            log.error("sendObjectMessage 发送对象消息异常", e);
        }
    }

    public static String getTextMessage(String getQueueName) {
        log.info("----------------接收文本消息getTextMessage-----------------");
        try {
            // 创建文本类消息队列,100s
            TextMessage message = (TextMessage) getMessageConsumer(getQueueName).receive(5000);
            if (message != null)
                return message.getText();
        } catch (JMSException e) {
            log.error("getTextMessage 接收文本消息异常", e);
        }
        return null;
    }

    public static void setMessageListener(MessageListener messageListener, String sendQueueName) {
        log.info("----------------设置消息队列监听setMessageListener-----------------");
        MessageConsumer messageConsumer = getMessageConsumer(sendQueueName);
        try {
            messageConsumer.setMessageListener(new BookManagerListener());
        } catch (JMSException e) {
            log.error("setMessageListener 设置消息队列监听", e);
        }
    }

    public static Serializable getObjectMessage(String getQueueName) {
        log.info("----------------接收对象消息getObjectMessage-----------------");
        try {
            ObjectMessage message = (ObjectMessage) getMessageConsumer(getQueueName).receive(5000);
            if (message != null)
                return message.getObject();
        } catch (JMSException e) {
            log.error("getObjectMessage 接收对象消息异常", e);
        }
        return null;
    }

    public static void close() {
        log.info("----------------关闭session-----------------");
        try {
            session.close();
        } catch (JMSException e) {
            log.error("close 关闭session异常", e);
        }
        log.info("----------------关闭connection-----------------");
        try {
            connection.close();
        } catch (JMSException e) {
            log.error("close 关闭connection异常", e);
        }
    }
}