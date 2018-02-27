package com.sttx.bookmanager.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sttx.bookmanager.po.Book;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.IBookService;
import com.sttx.bookmanager.util.pages.PagedResult;

@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@WebAppConfiguration
public class BookTest {
  private static final Logger logger = LoggerFactory.getLogger(BookTest.class);
    @Autowired
    private IBookService bookService;

    @Test
    public void testSelectByPrimaryKey() {

        Book book = bookService.selectByPrimaryKey("000B45A551D94506B3E34B89269BA6B2");
        logger.info("testSelectByPrimaryKey~:{}" + book);

    }

    //
    @Test
    public void testSelectBookPages() {
        Book book = new Book();
        book.setUserId("8CFB8863D10F4145A9FCAFAF3D8B11B8");
        User user = new User();
        user.setLoginName("");
        book.setUser(user);
        PagedResult<Book> pagedResult = bookService.selectBookPages(book, 1, 8);
        logger.info("总条数:{}" + pagedResult.getTotal());
        logger.info("总页数:{}" + pagedResult.getPages());
        logger.info("当前页:{}" + pagedResult.getPageNo());
        logger.info("未知:{}" + pagedResult.getPageOffset());
        logger.info("条件:{}" + pagedResult.getStrWhere());
        logger.info("数据长度:{}" + pagedResult.getDataList().size());
        logger.info("数据:{}:" + pagedResult.getDataList());
    }

    @Test
    public void testUnmountBook() {
        int i = bookService.unmountBook("000B45A551D94506B3E34B89269BA6B2");
        logger.info("testUnmountBook:{}:" + i);
    }

}
