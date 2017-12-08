package com.sttx.bookmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sttx.bookmanager.po.OrderItem;

public interface OrderItemMapper {
    int deleteByPrimaryKey(String itemId);

    int insert(OrderItem item);

    /**
     * 
     * @Title: insertSelective
     * @Description: 用户借书，生成借书条目
     * @param item
     * @return
     * @return: int
     */
    int insertSelective(OrderItem item);

    OrderItem selectByPrimaryKey(String itemId);

    /**
     * 
     * @Title: updateByPrimaryKeySelective
     * @Description: 管理员更新借书条目信息，具体表现为： （2） 管理员选择同意借出或者拒绝 （3） 用户申请还书，等待管理员确认 （4）
     *               管理员确认收到图书，流程结束
     * @param item
     * @return
     * @return: int
     */
    int updateByPrimaryKeySelective(OrderItem item);

    int updateByPrimaryKey(OrderItem item);

    /**
     * 
     * @Title: selectOrderItemPages
     * @Description: 借书单列表
     * @param item
     * @return
     * @return: List<OrderItem>
     */
    List<OrderItem> selectOrderItemPages(@Param("item") OrderItem item);

    /**
     * 获取当前读者的借书情况。包括正在申请借书，已借出，还有正在申请还书的情况
     * 
     * @param UserId
     * @return
     */
    List<Map> selectNowOrderByUserId(String UserId);

    /**
     * 查看用户是否已经借了该本书
     * 
     * @param bookId
     * @param userId
     * @return
     */
    int countThisOrder(OrderItem item);
}