package com.sttx.bookmanager.dao;

import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.po.TLogKey;

public interface TLogMapper {
    int deleteByPrimaryKey(TLogKey key);

    int insert(TLog record);

    int insertSelective(TLog record);

    TLog selectByUserIp(String userIp);

    TLog selectByPrimaryKey(TLogKey key);

    int updateByPrimaryKeySelective(TLog record);

    int updateByPrimaryKey(TLog record);
}