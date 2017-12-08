package com.sttx.bookmanager.service;

import java.util.List;

public interface IBaseService<T> {
    int deleteByPrimaryKey(String id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
    
    List<T> selectList(T t);

    public int batchSaveImg(List<T> tList);
}
