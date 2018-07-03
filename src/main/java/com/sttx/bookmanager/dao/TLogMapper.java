package com.sttx.bookmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.web.vo.TodayCountVo;

import tk.mybatis.mapper.common.BaseMapper;

public interface TLogMapper extends BaseMapper<TLog>{

    Long selectLogSumCount();

    List<TLog> selectLogPages(@Param("tLog") TLog tLog);

    List<TLog> selectLogPagesForIp(@Param("userIp") String userIp);
    
    TodayCountVo todayCount(String todayBegin,String todayEnd);

    long totalPathCount(@Param("path")String path);
}