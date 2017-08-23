package com.sttx.bookmanager;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.sttx.ddp.logger.DdpLoggerFactory;

@SpringBootApplication
@ServletComponentScan
public class CashLoanManagerApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {
    private static final Logger log = DdpLoggerFactory.getLogger(CashLoanManagerApplication.class);
    private static String contextPath = null;
    private static String port = null;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CashLoanManagerApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CashLoanManagerApplication.class, args);
        log.info(">>>>>图书管理系统启动成功!");
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (contextPath != null) {
            container.setContextPath(contextPath);
        }
        if (port != null) {
            container.setPort(Integer.valueOf(port));
        }
    }

}