package com.sttx.bookmanager.util.spring;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.sttx.ddp.logger.DdpLoggerFactory;

@Component
public class SpringUtils implements ApplicationContextAware {
    private static Logger log = DdpLoggerFactory.getLogger(SpringUtils.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("SpringUtils setApplicationContext............................................................");
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
        }
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
