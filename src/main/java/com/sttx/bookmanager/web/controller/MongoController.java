package com.sttx.bookmanager.web.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.po.TLog;

@Controller
@RequestMapping("/mongo")
public class MongoController {
    private static final Logger log = LoggerFactory.getLogger(MongoController.class);
    @Resource
    private MongoOperations mongoOperations;

    @RequestMapping(value = "/mongo", method = {RequestMethod.GET, RequestMethod.POST})
    public void mongo(HttpServletResponse response) throws IOException {
        Query query=new Query(Criteria.where("logId").is("BEBE87CA2F6241BCA06513CA4A5530E2"));
        TLog tLog = mongoOperations.findOne(query , TLog.class);
        String jsonString = JSONObject.toJSONString(tLog);
        log.info(">>>>>>>>>page :{}",jsonString);
        response.getWriter().print(jsonString);
    }

}
