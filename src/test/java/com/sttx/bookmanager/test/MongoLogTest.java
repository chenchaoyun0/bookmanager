package com.sttx.bookmanager.test;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.IBaseMongoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MongoLogTest {
    private static final Logger logger = LoggerFactory.getLogger(MongoLogTest.class);
    @Resource
    private IBaseMongoRepository<TLog> baseMongoRepository;

    @Resource
    private MongoOperations  mongoOperations;
    
    @Test
    public void testSelectLogPages() {
        Criteria criteria = new Criteria();
        criteria.and("logId").is("001B710B17384ECB86894A4D5BDAD2EC");
        Order order=new Order(Direction.DESC, "operTime");
        Query query = Query.query(criteria);
        Page<TLog> page = baseMongoRepository.selectPage(0, 10, query, TLog.class,order);
        logger.info(">>>>>>>>>page :{}",JSONObject.toJSON(page));
    }
    @Test
    public void testSelectOne() {
        Query query=new Query(Criteria.where("logId").is("BEBE87CA2F6241BCA06513CA4A5530E2"));
        TLog tLog = mongoOperations.findOne(query , TLog.class);
        logger.info(">>>>>>>>>page :{}",JSONObject.toJSON(tLog));
    }

}
