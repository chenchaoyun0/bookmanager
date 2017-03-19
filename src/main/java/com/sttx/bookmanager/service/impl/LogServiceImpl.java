package com.sttx.bookmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esotericsoftware.minlog.Log;
import com.github.pagehelper.PageHelper;
import com.sttx.bookmanager.dao.TLogMapper;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.bookmanager.util.pages.BeanUtil;
import com.sttx.bookmanager.util.pages.PagedResult;

@Service("logService")
public class LogServiceImpl implements ILogService {
    @Autowired
    private TLogMapper tLogMapper;

    public int insertSelective(TLog tLog) {
        try {
            Log.info("++++++++++++++++++++保存日志begin...");
            int insertSelective = tLogMapper.insertSelective(tLog);
            Log.info("++++++++++++++++++++保存日志end...");
            return insertSelective;
        } catch (UserException e) {
            throw new UserException("操作失败");
        }
    }

    @Override
    public TLog selectByUserIp(String userIp) {
        try {
            return tLogMapper.selectByUserIp(userIp);
        } catch (UserException e) {
            throw new UserException("操作失败");
        }
    }

    @Override
    public int insert(TLog tLog) {
        try {
            return tLogMapper.insert(tLog);
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
        pageSize = pageSize == null ? (Integer) 20 : pageSize;
        PageHelper.startPage(pageNo, pageSize);// 告诉插件开始分页
        List<TLog> selectLogPages = tLogMapper.selectLogPages(tLog);
        PagedResult<TLog> logPagedResult = BeanUtil.toPagedResult(selectLogPages);

        logPagedResult.setPageNo(pageNo);
        logPagedResult.setPageSize(pageSize);

        return logPagedResult;
    }

    @Override
    public Long selectLogSumCount() {
        return tLogMapper.selectLogSumCount();
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
}
