package com.sttx.bookmanager.service;

import com.sttx.bookmanager.po.TLog;

public interface ILogService {
    public int insertSelective(TLog tLog);

    TLog selectByUserIp(String userIp);

    int insert(TLog tLog);

    int updateByPrimaryKey(TLog record);
}
