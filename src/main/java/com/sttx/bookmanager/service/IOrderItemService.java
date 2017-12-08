package com.sttx.bookmanager.service;

import java.util.Map;

import com.sttx.bookmanager.po.OrderItem;
import com.sttx.bookmanager.util.pages.PagedResult;

public interface IOrderItemService {
    /**
     * 读者申请借书
     * 
     * @param orderItem
     * @return
     */
    int insertSelective(OrderItem orderItem);

    /**
     * 获取当前读者的借书情况。包括正在申请借书，已借出，还有正在申请还书的情况
     * 
     * @param UserId
     * @return
     */
    PagedResult<Map> selectNowOrderByUserId(String userId, Integer pageNo, Integer pageSize);

    /**
     * 申请还书
     * 
     * @param itemId
     * @return
     */
    int requestReturn(String itemId);

    /**
     * 管理员 根据条件获取所有书籍
     * 
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PagedResult<OrderItem> selectOrderItemPages(OrderItem orderItem, Integer pageNo, Integer pageSize);

    /**
     * 更新状态，根据id更新
     * 
     * @param itemId
     * @return
     */
    int updateOrderItem(OrderItem orderItem);

    /**
     * 查看是否已经借过这本书
     * 
     * @param bookId
     * @param userId
     * @return
     */
    int countThisOrder(OrderItem orderItem);
}
