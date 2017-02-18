package com.sttx.bookmanager.test;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sttx.bookmanager.dao.OrderItemMapper;
import com.sttx.bookmanager.po.ItemStatus;
import com.sttx.bookmanager.po.OrderItem;
import com.sttx.ddp.logger.DdpLoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class OrderItemServiceImplTest {

    @Autowired
    private OrderItemMapper orderItemMapper;

    private static final Logger logger = DdpLoggerFactory.getLogger(OrderItemServiceImplTest.class);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Ignore
    public final void testSelectNowOrderByUserId() {
        // OrderItemServiceImpl o = new OrderItemServiceImpl();
        // PagedResult<Map> list =
        // o.selectNowOrderByUserId("2F1CE4A814744C8186F8E3B6902CA180", 1, 5);
        List<Map> list = orderItemMapper.selectNowOrderByUserId("2F1CE4A814744C8186F8E3B6902CA180");
        logger.info("testSelectNowOrderByUserId:{}" + list);
    }

    @Test
    public final void testRequestReturn() {
        OrderItem order = new OrderItem();
        order.setItemStatus(ItemStatus.RETURN_REQUEST.getStatus());
        order.setItemId("870C64A287F6425F8F79C3F7B72EDBE1");
        orderItemMapper.updateByPrimaryKeySelective(order);
    }

    @Test
    public final void testSelectOrderItemPages() {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId("2DF54AC0B7F34056B299CFC8E0702A0E");
        List<OrderItem> list = orderItemMapper.selectOrderItemPages(orderItem);
        logger.info("testSelectOrderItemPages:{}" + list);
    }

    @Test
    public final void countThisOrder() {
        OrderItem orderItem = new OrderItem();
        orderItem.setBookId("4D6D0CA72F794D0B82FFB90C563ED6C5");
        orderItem.setUserId("2F1CE4A814744C8186F8E3B6902CA180");
        orderItem.setLendTime("2016-08-15");
        int i = orderItemMapper.countThisOrder(orderItem);
        logger.info("countThisOrder:{}" + i);
    }

}
