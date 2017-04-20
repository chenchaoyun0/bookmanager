package com.sttx.bookmanager.util.spring;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sttx.ddp.logger.DdpLoggerFactory;

public class SpringUtils {
    private static Logger log = DdpLoggerFactory.getLogger(SpringUtils.class);
    private static ApplicationContext applicationContext;
    static {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> cls) {
        return applicationContext.getBean(cls);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> cls) {
        return applicationContext.getBeansOfType(cls);
    }

    public static <T> T getBean(String name, Class<T> cls) {
        return applicationContext.getBean(name, cls);
    }

}
