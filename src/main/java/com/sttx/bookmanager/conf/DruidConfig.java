package com.sttx.bookmanager.conf;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.sttx.bookmanager.CashLoanManagerApplication;
import com.sttx.ddp.logger.DdpLoggerFactory;

@Configuration
public class DruidConfig {
    private static final Logger log = DdpLoggerFactory.getLogger(CashLoanManagerApplication.class);

    @Resource
    DataSource datasource;

    @Bean
    @Primary
    public DataSource dataSource() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>datasource:{}", datasource);
        return datasource;
    }

}