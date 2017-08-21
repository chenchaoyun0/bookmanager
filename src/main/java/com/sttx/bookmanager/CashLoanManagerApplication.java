package com.sttx.bookmanager;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.sttx.ddp.logger.DdpLoggerFactory;

@SpringBootApplication
@ServletComponentScan
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, // （如果使用Hibernate时，需要加）
        DataSourceTransactionManagerAutoConfiguration.class, })

public class CashLoanManagerApplication extends SpringBootServletInitializer {
    private static final Logger log = DdpLoggerFactory.getLogger(CashLoanManagerApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CashLoanManagerApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CashLoanManagerApplication.class, args);
        log.info(">>>>>经理端启动成功!");
    }

}