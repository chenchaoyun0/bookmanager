package com.sttx.bookmanager.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sttx.bookmanager.po.ItemStatus;
import com.sttx.bookmanager.po.OrderItem;
import com.sttx.bookmanager.service.IBookService;
import com.sttx.bookmanager.service.IOrderItemService;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.time.StandardTime;

import cn.itcast.commons.CommonUtils;

@Controller
@RequestMapping("/order")
public class OrderController {
    private static final Logger LOGGER = Logger.getLogger(BookController.class);
    @Autowired
    private IOrderItemService orderItemService;
    @Autowired
    private IBookService bookService;

    @RequestMapping("/lendBook")
    @ResponseBody
    public int lendBook(String bookId, String userId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setBookId(bookId);
        orderItem.setUserId(userId);
        // 是否书籍已经被借完
        if (bookService.countByBookId(bookId) == 0)
            return 0;
        // 是否今天你已经借过这本书。第二天可以再借一次
        else if (orderItemService.countThisOrder(orderItem) > 0) {
            return -9;
        } else {
            orderItem.setItemId(CommonUtils.uuid());
            orderItem.setLendCount(1);// 默认为借一本
            String time = StandardTime.getTime();
            orderItem.setLendTime(time);
            orderItem.setItemStatus(ItemStatus.LEND_REQUEST.getStatus());
            orderItem.setItemFlag(1);
            return orderItemService.insertSelective(orderItem);
        }
    }

    @RequestMapping("/skipToOrderBook")
    public String skipToOrderBook(HttpServletRequest request, HttpServletResponse response) {
        return "order/myBookOrderList";
    }

    @RequestMapping("/orderBook")
    @ResponseBody
    public PagedResult<Map> orderBook(String userId, int pageNo, int pageSize) {
        if (pageNo == 0 || pageSize == 0)
            return null;
        PagedResult<Map> list = orderItemService.selectNowOrderByUserId(userId, pageNo, pageSize);

        return list;
    }

    @RequestMapping("/returnBook/{itemId}")
    @ResponseBody
    public int returnBook(@PathVariable("itemId") String itemId) {
        return orderItemService.requestReturn(itemId);
    }

    @RequestMapping("/admin")
    public String admin() {
        return "/admin/orderadmin";
    }

    @RequestMapping("/adminData")
    @ResponseBody
    public PagedResult<OrderItem> adminData(OrderItem orderItem, Integer pageNo, Integer pageSize) {
        PagedResult<OrderItem> list = orderItemService.selectOrderItemPages(orderItem, pageNo, pageSize);
        return list;
    }

    @RequestMapping("/updateOrderItem")
    @ResponseBody
    public int updateOrderItem(String bookId, String itemId, int status) {
        // 在借书的时候，需要判断该书是否数量已经为0，若为0，则告诉管理员该书数量已经为0
        // 判断条件为 状态为借书 该书数量已经为0
        if (status == ItemStatus.LEND_ACCEPT.getStatus() && bookService.countByBookId(bookId) == 0) {
            return -1;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setItemStatus(status);
        orderItem.setBookId(bookId);
        // if(status==1){
        // bookService.decBookCount(){};
        // }
        return orderItemService.updateOrderItem(orderItem);
    }
}