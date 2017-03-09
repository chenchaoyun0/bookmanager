package com.sttx.bookmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sttx.bookmanager.dao.TLogMapper;
import com.sttx.bookmanager.po.TLog;
import com.sttx.bookmanager.service.ILogService;
import com.sttx.bookmanager.util.exception.UserException;

@Service("logService")
public class LogServiceImpl implements ILogService {
    @Autowired
    private TLogMapper tLogMapper;

    public int insertSelective(TLog tLog) {
        try {
            return tLogMapper.insertSelective(tLog);
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
}
