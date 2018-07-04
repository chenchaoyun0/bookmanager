package com.sttx.bookmanager.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.dao.StudentMapper;
import com.sttx.bookmanager.dao.TLogMapper;
import com.sttx.bookmanager.po.Student;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.web.vo.TodayCountVo;

import tk.mybatis.mapper.entity.Example;

@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml",
  "classpath:spring/applicationContext-service.xml", "classpath:spring/applicationContext-transation.xml"})
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest
public class LogTest {
  private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
  @Autowired
  private ILogService logService;

  @Autowired
  private TLogMapper tLogMapper;

  @Autowired
  private StudentMapper studentMapper;

  @Test
  public void saveStudent() {
    Student student = new Student();
    student.setName("test");
    student.setScore(new BigDecimal("10.98"));
    int insertSelective = studentMapper.insertSelective(student);
    logger.info("insertPo:{}", JSONObject.toJSONString(insertSelective));
    logger.info("student.getId:{}", JSONObject.toJSONString(student.getId()));
  }

  @Test
  public void testSelectLogPages() {
    TLog tLog = new TLog();
    PagedResult<TLog> pagedResult = logService.selectLogPages(tLog, null, null);
    logger.info("+++++:{}", JSONObject.toJSON(pagedResult.getDataList().get(0)));
    PagedResult<TLog> pagedResult2 = logService.selectLogPages(new TLog(), null, null);
  }

  @Test
  public void testSelectByPrimaryKey() {
    /**
     * { "action": "indexHome", "actionTime": 29, "count": 0, "logId": "A55D9D20B88D421D92FA96D986EC65D2", "module":
     * "IndexHomeController", "operTime": "2017-03-09 02:29:38:655", "userAddress": "未分配或者内网IP----", "userIp":
     * "127.0.0.1", "userJwd": "未知", "userName": "游客用户", "userNickName": "未设置" }
     */
    TLog tLog = new TLog();
    tLog.setAction("indexHome");
    tLog.setActionTime(29l);
    tLog.setCount(0l);
    tLog.setModule("IndexHomeController");
    tLog.setUserJwd("未知");
    tLog.setOperTime("2017-03-09 02:29:38:655");
    tLog.setUserAddress("未分配或者内网IP----");
    tLog.setUserIp("127.0.0.1");
    tLog.setUserName("游客用户");
    tLog.setUserNickName("游客用户");
    logger.info("insertSelective~:{}" + JSONObject.toJSONString(tLog));
    int i = logService.insertSelective(tLog);
    logger.info("insertSelective~:{}" + i);
  }

  @Test
  public void testSelectLogPagesByMongo() {
    TLog tLog = new TLog();
    PagedResult<TLog> pagedResult = logService.selectLogPagesByMongo(tLog, 1, 8);
    logger.info("+++++:{}", JSONObject.toJSON(pagedResult));
  }

  @Test
  public void testSelectLogSumCount() {
    Long selectLogSumCount = logService.selectLogSumCount();
    logger.info("+++++:{}", JSONObject.toJSON(selectLogSumCount));
  }

  @Test
  public void testSelectLogByIp() {
    PagedResult<TLog> pagedResult = logService.selectLogPagesForIp("127.0.0.1", null, null);
    logger.info("+++++:{}", JSONObject.toJSON(pagedResult));
  }

  @Test
  public void testTodayCount() {
    TodayCountVo todayCount = logService.todayCount();
    logger.info("+++++:{}", JSONObject.toJSON(todayCount));
  }

  @Test
  public void testid() {
    Example example = new Example(TLog.class);
    // 注意：排序使用的是列名
    example.setOrderByClause("oper_Time DESC");
    // 如果需要其他条件
    // 掌机类型名字
    // 条件查询使用的是属性名
    //example.createCriteria().andEqualTo("logId", "4DDE6E6D58494ABC8162DCC101E85AE7");
    List<TLog> dataList = tLogMapper.selectByExample(example);

    logger.info("dataList:{}", dataList.size());
//    int size = dataList.size();
//    for (int i = 0; i < dataList.size(); i++) {
//      TLog tLog = dataList.get(i);
//      int updateId = tLogMapper.updateId(size--, tLog.getLogId());
//      System.out.println("updateId:" + updateId);
//      System.out.println(i);
//    }
  }

}
