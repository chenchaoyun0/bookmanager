package com.sttx.bookmanager;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import com.sttx.bookmanager.util.spring.SpringUtils;

@SpringBootApplication
@ServletComponentScan
public class CashLoanManagerApplication extends SpringBootServletInitializer
        implements  ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(CashLoanManagerApplication.class);
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
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("开机服务执行的操作....");
        DataSource dataSource = SpringUtils.getBean("dataSource", DataSource.class);
        log.info("dataSource:{}", dataSource);
        try {
            DatabaseMetaData databaseMetaData = dataSource.getConnection().getMetaData();
            String url = databaseMetaData.getURL();
            log.info("url:{}", url);
            String userName = databaseMetaData.getUserName();
            log.info("userName:{}", userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}