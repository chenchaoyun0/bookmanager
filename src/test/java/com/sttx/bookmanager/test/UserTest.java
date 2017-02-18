package com.sttx.bookmanager.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sttx.bookmanager.po.Book;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.IUserService;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.ddp.logger.DdpLoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class UserTest {
    private static final Logger logger = DdpLoggerFactory.getLogger(UserTest.class);
    @Autowired
    private IUserService userService;

    @Test
    public void testSelectByPrimaryKey() {

        User user = userService.selectByPrimaryKey("1");
        userService.selectByPrimaryKey("1");
        logger.info("testSelectByPrimaryKey :{}" + user);

    }

    @Test
    public void testDeleteByPrimaryKey() {

        int user = userService.deleteByPrimaryKey("9B666806E2474F7C9C6628166409ECD9");
        logger.info("testDeleteByPrimaryKey:{}" + user);

    }

    @Test
    public void testfindUserBookListByUserId() {

        List<Book> userBookList = userService.findUserBookListByUserId("1");
        logger.info("testfindUserBookListByUserId:{}" + userBookList);

    }

    @Test
    public void testIsEmail() {

        boolean b = userService.existUserEmail("1");
        logger.info("testIsEmail:{}" + b);

    }

    @Test
    public void testSelectUserPages() {
        PagedResult<User> pagedResult = userService.selectUserPages(null, 1, 5);
        logger.info("总条数:{}" + pagedResult.getTotal());
        logger.info("总页数:{}" + pagedResult.getPages());
        logger.info("当前页:{}" + pagedResult.getPageNo());
        logger.info("未知:{}" + pagedResult.getPageOffset());
        logger.info("条件:{}" + pagedResult.getStrWhere());
        logger.info("数据长度:{}" + pagedResult.getDataList().size());
        logger.info("数据:{}:" + pagedResult.getDataList());
    }

    @Test
    public void testUpdatePermission() {
        userService.updatePermission("2F1CE4A814744C8186F8E3B6902CA180");
    }

}
