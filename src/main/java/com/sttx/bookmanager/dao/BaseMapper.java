package com.sttx.bookmanager.dao;

import java.util.List;

public interface BaseMapper<T> {
    int deleteByPrimaryKey(String id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

    List<T> selectList(T t);
}
