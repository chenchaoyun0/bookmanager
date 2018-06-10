package com.sttx.bookmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.po.TLogKey;
import com.sttx.bookmanager.web.vo.TodayCountVo;

public interface TLogMapper {
    int deleteByPrimaryKey(TLogKey key);

    int insert(TLog record);

    int insertSelective(TLog record);

    TLog selectByUserIp(String userIp);

    TLog selectByPrimaryKey(TLogKey key);

    int updateByPrimaryKeySelective(TLog record);

    int updateByPrimaryKey(TLog record);

    Long selectLogSumCount();

    List<TLog> selectLogPages(@Param("tLog") TLog tLog);

    List<TLog> selectLogPagesForIp(@Param("userIp") String userIp);
    
    TodayCountVo todayCount(String todayBegin,String todayEnd);
}