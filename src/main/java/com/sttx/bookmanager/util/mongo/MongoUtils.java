package com.sttx.bookmanager.util.mongo;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.util.BookManagerBeanUtils;

public class MongoUtils {
    public static void buildGroupOperation() {
        ProjectionOperation project =
                Aggregation.project("logId", "userIp", "userName", "userNickName", "userAddress",
                        "userJwd", "module", "action", "actionTime", "operTime", "count");
        GroupOperation as = Aggregation.group("userIp").sum("count").as("count").first("logId")
                .as("logId").first("userIp").as("userIp").first("userName").as("userName")
                .first("userNickName").as("userNickName").first("userAddress").as("userAddress")
                .first("userJwd").as("userJwd").first("module").as("module").first("action")
                .as("action").first("actionTime").as("actionTime").first("operTime").as("operTime");

    }
}
