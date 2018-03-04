package com.sttx.bookmanager.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sttx.bookmanager.po.SpringDataPageable;
import com.sttx.bookmanager.service.IBaseMongoRepository;


@Repository("baseMongoRepository")
public class BaseMongoRepositoryImpl<T> implements IBaseMongoRepository<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseMongoRepositoryImpl.class);

    @Resource
    private MongoOperations  mongoOperations;

    @Override
    public Page<T> selectPage(int start, int iDisplayLength, Query query, Class<T> clazz, Order... orders) {
        long listQueryStart = System.currentTimeMillis();
        // sort

        Sort sort = Sort.by(orders);
        if (null != sort) {
            query.with(sort);
        }
        // query
        Page<T> pagelist = selectPage(start, iDisplayLength, query, clazz);
        logger.info("selectPage {} cost {} ms ", clazz.getSimpleName(), (System.currentTimeMillis() - listQueryStart));
        return pagelist;
    }

    @Override
    public Page<T> selectPage(int start, int iDisplayLength, Query query, Class<T> clazz) {
        // query
        long count = mongoOperations.count(query, clazz);
        SpringDataPageable pageable = new SpringDataPageable();
        //自己计算..
        int pagenumber=(start/iDisplayLength)+1;
        // 开始页
        pageable.setPagenumber(pagenumber);
        // 每页条数
        pageable.setPagesize(iDisplayLength);
        List<T> list =mongoOperations.find(query.with(pageable), clazz);
        Page<T> pagelist = new PageImpl<T>(list, pageable, count);
        return pagelist;
    }

}
