package com.sttx.bookmanager.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.IBaseMongoRepository;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.pages.PagedResult;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MongoLogTest {
    private static final Logger logger = LoggerFactory.getLogger(MongoLogTest.class);
    @Resource
    private IBaseMongoRepository<TLog> baseMongoRepository;

    @Autowired
    private ILogService logService;
    
    @Resource
    private MongoTemplate mongoTemplate;
    
    @Test
    public void testSelectLogPages() {
        ProjectionOperation project = Aggregation.project("logId","userIp","userName","userNickName","userAddress","userJwd","module","action","actionTime","operTime","count");
        TypedAggregation<TLog> aggregation = Aggregation.newAggregation(
               TLog.class
                , Aggregation.sort(Sort.Direction.DESC, "operTime")
               ,Aggregation.group("userIp").sum("count").as("count")
               .first("logId").as("logId").first("userIp").as("userIp").first("userName").as("userName")
               .first("userNickName").as("userNickName").first("userAddress").as("userAddress")
               .first("userJwd").as("userJwd").first("module").as("module").first("action")
               .as("action").first("actionTime").as("actionTime").first("operTime").as("operTime")
                , Aggregation.match(Criteria.where("userIp").is("127.0.0.1"))
                ,Aggregation.skip(0l)
                , Aggregation.limit(2l)
            );
        
        AggregationResults<BasicDBObject> aggregate = mongoTemplate.aggregate(aggregation,BasicDBObject.class);
        List<TLog> logList = new ArrayList<>();
        for (BasicDBObject basicDBObject : aggregate) {
            String action = basicDBObject.getString("action");
            long actionTime = basicDBObject.getLong("actionTime");
            long count = basicDBObject.getLong("count");
            String logId = basicDBObject.getString("logId");
            String module = basicDBObject.getString("module");
            String operTime = basicDBObject.getString("operTime");
            String userAddress = basicDBObject.getString("userAddress");
            String userIp = basicDBObject.getString("userIp");
            String userJwd = basicDBObject.getString("userJwd");
            String userName = basicDBObject.getString("userName");
            String userNickName = basicDBObject.getString("userNickName");
            TLog tLog = new TLog(userName, userNickName, userAddress, userJwd, module, action, actionTime, operTime, count);
            tLog.setLogId(logId);
            tLog.setUserIp(userIp);
            logList.add(tLog);
        }
        System.err.println(JSONObject.toJSONString(logList));

    }
    @Test
    public void testSelectOne() {
        Criteria criteria = new Criteria();
        criteria.and("logId").is("07F963B6B34D45B499BCD242CF369418");
        Query query = Query.query(criteria);
        TLog tLog = mongoTemplate.findOne(query , TLog.class);
        logger.info(">>>>>>>>>page :{}",JSONObject.toJSON(tLog));
        
    }
    @Test
    public void testDelOne() {
        Criteria criteria = new Criteria();
        criteria.and("userIp").is("139.224.92.172");
        Query query = Query.query(criteria);
        //WriteResult remove = mongoTemplate.remove(query, TLog.class);
        //int n = remove.getN();
        //logger.info(">>>>>>>>>n :{}",JSONObject.toJSON(n));
        TLog tLog = mongoTemplate.findOne(query , TLog.class);
        logger.info(">>>>>>>>>page :{}",JSONObject.toJSON(tLog));
        
    }
    @Test
    public void testSaveOne() {
        TLog tLog = new TLog();
        tLog.setAction("indexHome");
        tLog.setActionTime(29l);
        tLog.setCount(0l);
        tLog.setLogId("A55D9D20B88D421D92FA96D986EC65D2");
        tLog.setModule("IndexHomeController");
        tLog.setUserJwd("未知");
        tLog.setOperTime("2017-03-09 02:29:38:655");
        tLog.setUserAddress("未分配或者内网IP----");
        tLog.setUserIp("127.0.0.1");
        tLog.setUserName("游客用户");
        tLog.setUserNickName("游客用户");
        logger.info(">>>>>>>>>page :{}",JSONObject.toJSON(tLog));
        mongoTemplate.save(tLog);
    }
    @Test
    public void testBatch() {
        TLog tLog = new TLog();
        int pageNo = 1;
        int pageSize = 8;
        PagedResult<TLog> pages = logService.selectLogPages(tLog, pageNo, pageSize);
        logger.info(">>>>>>>>>page :{}", JSONObject.toJSON(pages));
        long total = pages.getTotal();
        logger.info(">>>>>>>>>page.getTotal :{}", JSONObject.toJSON(total));
        PagedResult<TLog> pageAll = logService.selectLogPages(tLog, pageNo, Integer.parseInt(total + ""));
        List<TLog> dataList = pageAll.getDataList();
        for (TLog tLog2 : dataList) {
            logger.info(">>>>>>>>>tLog2 :{}", JSONObject.toJSON(tLog2));
            mongoTemplate.save(tLog2);
        }
    }

}
