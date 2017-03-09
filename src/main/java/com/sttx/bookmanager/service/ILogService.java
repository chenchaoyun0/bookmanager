package com.sttx.bookmanager.service;

import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.util.pages.PagedResult;

public interface ILogService {
    public int insertSelective(TLog tLog);

    TLog selectByUserIp(String userIp);

    int insert(TLog tLog);

    int updateByPrimaryKey(TLog record);

    Long selectLogSumCount();

    PagedResult<TLog> selectLogPages(TLog tLog, Integer pageNo, Integer pageSize);
}
