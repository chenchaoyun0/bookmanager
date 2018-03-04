package com.sttx.bookmanager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Query;

public interface IBaseMongoRepository<T> {

    public Page<T> selectPage(int start, int iDisplayLength, Query query, Class<T> clazz, Order... orders);

    public Page<T> selectPage(int start, int iDisplayLength, Query query, Class<T> clazz);

}
