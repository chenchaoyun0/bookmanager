package com.sttx.bookmanager.conf;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.sttx.bookmanager.CashLoanManagerApplication;

@Configuration
public class DruidConfig {
  private static final Logger log = LoggerFactory.getLogger(DruidConfig.class);

    @Resource
    DataSource datasource;

    @Bean
    @Primary
    public DataSource dataSource() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>datasource:{}", datasource);
        return datasource;
    }

}