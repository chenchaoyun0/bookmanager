package com.sttx.bookmanager.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sttx.bookmanager.dao.BookMapper;
import com.sttx.bookmanager.dao.OrderItemMapper;
import com.sttx.bookmanager.po.Book;
import com.sttx.bookmanager.po.ItemStatus;
import com.sttx.bookmanager.po.OrderItem;
import com.sttx.bookmanager.service.IOrderItemService;
import com.sttx.bookmanager.util.pages.BeanUtil;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.time.StandardTime;

@Service("orderItemService")
public class OrderItemServiceImpl implements IOrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private BookMapper bookMapper;

    public int insertSelective(OrderItem orderItem) {
        int result = 0;
        if (orderItem.getUserId() == null || "".equals(orderItem.getUserId()))
            return -1; // -1为用户未登录
        if (orderItem.getBookId() == null || "".equals(orderItem.getBookId()))
            return -2; // -2为书籍信息为空
        result = orderItemMapper.insertSelective(orderItem);
        return result;
    }

    public PagedResult<Map> selectNowOrderByUserId(String userId, Integer pageNo, Integer pageSize) {
        if (userId == null || "".equals(userId))
            return null;

        pageNo = pageNo == null ? (Integer) 1 : pageNo;
        pageSize = pageSize == null ? (Integer) 8 : pageSize;
        PageHelper.startPage(pageNo, pageSize);// 告诉插件开始分页

        List<Map> list = orderItemMapper.selectNowOrderByUserId(userId);
        PagedResult<Map> bookPagedResult = BeanUtil.toPagedResult(list);

        for (Map map : list) {
            try {
                String lendTime = (String) map.get("lend_Time");
                SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.sss");
                Date date = sp.parse(lendTime);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.MONTH, +3);
                map.put("return_time", sp.format(cal.getTime()));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                map.put("return_time", "");
            }
        }

        bookPagedResult.setPageNo(pageNo);
        bookPagedResult.setPageSize(pageSize);

        return bookPagedResult;
    }

    public int requestReturn(String itemId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setItemStatus(ItemStatus.RETURN_REQUEST.getStatus());
        return orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    public PagedResult<OrderItem> selectOrderItemPages(OrderItem orderItem, Integer pageNo, Integer pageSize) {

        pageNo = pageNo == null ? (Integer) 1 : pageNo;
        pageSize = pageSize == null ? (Integer) 8 : pageSize;
        PageHelper.startPage(pageNo, pageSize);// 告诉插件开始分页

        List<OrderItem> list = orderItemMapper.selectOrderItemPages(orderItem);
        PagedResult<OrderItem> bookPagedResult = BeanUtil.toPagedResult(list);
        return bookPagedResult;
    }

    public int updateOrderItem(OrderItem orderItem) {
        Book book = new Book();
        // 首先获取书的id号，然后根据id号得出书剩下的数量
        book.setBookId(orderItem.getBookId());
        int count = bookMapper.countByBookId(orderItem.getBookId());
        if (orderItem.getItemStatus() == ItemStatus.LEND_ACCEPT.getStatus()) {
            count--;
            book.setBookRemain(count);
            bookMapper.updateByPrimaryKeySelective(book);
        } else if (orderItem.getItemStatus() == ItemStatus.RETURN_ACCEPT.getStatus()) {
            count++;
            book.setBookRemain(count);
            bookMapper.updateByPrimaryKeySelective(book);
        }
        return orderItemMapper.updateByPrimaryKeySelective(orderItem);

    }

    public int countThisOrder(OrderItem item) {
        item.setLendTime(StandardTime.getTime().substring(0, 10));
        return orderItemMapper.countThisOrder(item);
    }

}
