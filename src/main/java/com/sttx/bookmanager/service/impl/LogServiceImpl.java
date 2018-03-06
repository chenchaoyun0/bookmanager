package com.sttx.bookmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sttx.bookmanager.dao.TLogMapper;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.IBaseMongoRepository;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.bookmanager.util.pages.BeanUtil;
import com.sttx.bookmanager.util.pages.PagedResult;

@Service("logService")
public class LogServiceImpl implements ILogService {

    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    private TLogMapper tLogMapper;

    @Resource
    private IBaseMongoRepository<TLog> baseMongoRepository;
    
    @Resource
    private MongoTemplate mongoTemplate;

    public int insertSelective(TLog tLog) {
        try {
            log.info("++++++++++++++++++++保存日志begin...");
            int save = baseMongoRepository.save(tLog);
            tLogMapper.insertSelective(tLog);
            log.info("++++++++++++++++++++保存日志end...");
            return save;
        } catch (UserException e) {
            throw new UserException("操作失败");
        }
    }

    @Override
    public TLog selectByUserIp(String userIp) {
        try {
            Criteria criteria = new Criteria();
            criteria.and("userIp").is(userIp);
            Query query = Query.query(criteria);
            TLog findOne = baseMongoRepository.findOne(query, TLog.class);
            return findOne;
        } catch (UserException e) {
            throw new UserException("操作失败");
        }
    }

    @Override
    public int insert(TLog tLog) {
        try {
            return insertSelective(tLog);
        } catch (UserException e) {
            throw new UserException("操作失败");
        }
    }

    @Override
    public int updateByPrimaryKey(TLog record) {
        try {
            return tLogMapper.updateByPrimaryKeySelective(record);
        } catch (UserException e) {
            throw new UserException("操作失败");
        }
    }

    @Override
    public PagedResult<TLog> selectLogPages(TLog tLog, Integer pageNo, Integer pageSize) {
        /**
         * 默认是12条
         */
        pageNo = pageNo == null ? (Integer) 1 : pageNo;
        pageSize = pageSize == null ? (Integer) 8 : pageSize;
        PageHelper.startPage(pageNo, pageSize);// 告诉插件开始分页
        List<TLog> selectLogPages = tLogMapper.selectLogPages(tLog);
        PagedResult<TLog> logPagedResult = BeanUtil.toPagedResult(selectLogPages);

        logPagedResult.setPageNo(pageNo);
        logPagedResult.setPageSize(pageSize);

        return logPagedResult;
    }

    @Override
    public PagedResult<TLog> selectLogPagesByMongo(TLog tLog, Integer pageNo, Integer pageSize) {
        /**
         * 默认是12条
         */
        pageNo = pageNo == null ? (Integer) 1 : pageNo;
        pageSize = pageSize == null ? (Integer) 8 : pageSize;
        
        TypedAggregation<TLog> aggregationCount = Aggregation.newAggregation(
                TLog.class
                ,Aggregation.group("userIp").sum("count").as("count")
                );
        AggregationResults<BasicDBObject> aggregateCount = mongoTemplate.aggregate(aggregationCount,BasicDBObject.class);
        
        int total = aggregateCount.getMappedResults().size();
        TypedAggregation<TLog> aggregation = Aggregation.newAggregation(
                TLog.class
                ,Aggregation.group("userIp").sum("count").as("count")
                .first("logId").as("logId").first("userIp").as("userIp").first("userName").as("userName")
                .first("userNickName").as("userNickName").first("userAddress").as("userAddress")
                .first("userJwd").as("userJwd").first("module").as("module").first("action")
                .as("action").first("actionTime").as("actionTime").first("operTime").as("operTime")
                 //,Aggregation.match(Criteria.where("totalNum").gte(85))
                 ,Aggregation.sort(Sort.Direction.DESC, "operTime")
                 ,Aggregation.skip(Long.parseLong((pageNo>1?(pageNo-1)*pageSize:0)+""))
                 ,Aggregation.limit(pageSize)
             );
         
         AggregationResults<BasicDBObject> aggregate = mongoTemplate.aggregate(aggregation,BasicDBObject.class);
         List<TLog> logList=new ArrayList<>();
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
             TLog tLogMongo = new TLog(userName, userNickName, userAddress, userJwd, module, action, actionTime, operTime, count);
             tLogMongo.setLogId(logId);
             tLogMongo.setUserIp(userIp);
             logList.add(tLogMongo);
         }
        
         PagedResult<TLog> pagedResult=new PagedResult<>();
         pagedResult.setDataList(logList);
         pagedResult.setPageNo(pageNo);
         pagedResult.setPageSize(pageSize);
         pagedResult.setTotal(total);
         pagedResult.setPages((total  +  pageSize  - 1) / pageSize);
        
        return pagedResult;
    }

    @Override
    public Long selectLogSumCount() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("$sum").sum("count").as("totalcount")
                );
        AggregationResults<BasicDBObject> aggregateCount = mongoTemplate.aggregate(aggregation,"tLog",BasicDBObject.class);
        List<BasicDBObject> mappedResults = aggregateCount.getMappedResults();
        long totalcount=0;
        for (BasicDBObject basicDBObject : mappedResults) {
            totalcount = basicDBObject.getLong("totalcount");
        }
        
        return totalcount;
    }

    @Override
    public PagedResult<TLog> selectLogPagesForIp(String userIp, Integer pageNo, Integer pageSize) {
        /**
         * 默认是12条
         */
        pageNo = pageNo == null ? (Integer) 1 : pageNo;
        pageSize = pageSize == null ? (Integer) 20 : pageSize;
        PageHelper.startPage(pageNo, pageSize);// 告诉插件开始分页
        List<TLog> selectLogPages = tLogMapper.selectLogPagesForIp(userIp);
        PagedResult<TLog> logPagedResult = BeanUtil.toPagedResult(selectLogPages);

        logPagedResult.setPageNo(pageNo);
        logPagedResult.setPageSize(pageSize);

        return logPagedResult;
    }
    
    
    @Override
    public PagedResult<TLog> selectLogPagesForIpByMongo(String userIp, Integer pageNo, Integer pageSize) {
        /**
         * 默认是12条
         */
        pageNo = pageNo == null ? (Integer) 1 : pageNo;
        pageSize = pageSize == null ? (Integer) 8 : pageSize;
        
        TypedAggregation<TLog> aggregationCount = Aggregation.newAggregation(
                TLog.class
                ,Aggregation.match(Criteria.where("userIp").is(userIp))
                );
        AggregationResults<BasicDBObject> aggregateCount = mongoTemplate.aggregate(aggregationCount,BasicDBObject.class);
        
        int total = aggregateCount.getMappedResults().size();
        
        TypedAggregation<TLog> aggregation = Aggregation.newAggregation(
                TLog.class
                ,Aggregation.group("logId").max("operTime").as("operTime").sum("count").as("count")
                .first("logId").as("logId").first("userIp").as("userIp").first("userName").as("userName")
                .first("userNickName").as("userNickName").first("userAddress").as("userAddress")
                .first("userJwd").as("userJwd").first("module").as("module").first("action")
                .as("action").first("actionTime").as("actionTime").first("operTime").as("operTime")
                ,Aggregation.match(Criteria.where("userIp").is(userIp))
                 ,Aggregation.sort(Sort.Direction.DESC, "operTime")
                 ,Aggregation.skip(Long.parseLong((pageNo>1?(pageNo-1)*pageSize:0)+""))
                 ,Aggregation.limit(pageSize)
             );
         
         AggregationResults<BasicDBObject> aggregate = mongoTemplate.aggregate(aggregation,BasicDBObject.class);
         List<TLog> logList=new ArrayList<>();
         for (BasicDBObject basicDBObject : aggregate) {
             String action = basicDBObject.getString("action");
             long actionTime = basicDBObject.getLong("actionTime");
             long count = basicDBObject.getLong("count");
             String logId = basicDBObject.getString("logId");
             String module = basicDBObject.getString("module");
             String operTime = basicDBObject.getString("operTime");
             String userAddress = basicDBObject.getString("userAddress");
             String userIp2 = basicDBObject.getString("userIp");
             String userJwd = basicDBObject.getString("userJwd");
             String userName = basicDBObject.getString("userName");
             String userNickName = basicDBObject.getString("userNickName");
             TLog tLogMongo = new TLog(userName, userNickName, userAddress, userJwd, module, action, actionTime, operTime, count);
             tLogMongo.setLogId(logId);
             tLogMongo.setUserIp(userIp2);
             logList.add(tLogMongo);
         }
        
         PagedResult<TLog> pagedResult=new PagedResult<>();
         pagedResult.setDataList(logList);
         pagedResult.setPageNo(pageNo);
         pagedResult.setPageSize(pageSize);
         pagedResult.setTotal(total);
         pagedResult.setPages((total  +  pageSize  - 1) / pageSize);
        
        return pagedResult;
    }
}
