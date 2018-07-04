package com.sttx.bookmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.web.vo.TodayCountVo;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

public interface TLogMapper extends Mapper<TLog>{

    Long selectLogSumCount();

    TLog selectByUserIp(@Param("userIp") String userIp);
    
    List<TLog> selectLogPages(@Param("tLog") TLog tLog);

    List<TLog> selectLogPagesForIp(@Param("userIp") String userIp);
    
    TodayCountVo todayCount(String todayBegin,String todayEnd);

    long totalPathCount(@Param("path")String path);
    
    int updateId(long id,String logId);
}