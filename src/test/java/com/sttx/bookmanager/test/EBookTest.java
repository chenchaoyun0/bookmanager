package com.sttx.bookmanager.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sttx.bookmanager.po.EBook;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.IEBookService;
import com.sttx.bookmanager.util.pages.PagedResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class EBookTest {
  private static final Logger logger = LoggerFactory.getLogger(EBookTest.class);
    @Autowired
    private IEBookService eBookService;

    @Test
    public void testSelectbyPrimKey() {
        EBook eBook = eBookService.selectByPrimaryKey("0578ACA2860441C1B08867EA6915A4F6");
        logger.info("testSelectEbookByPrimKey" + eBook);
    }

    @Test
    public void testSelectEBookPages() {
        EBook eBook = new EBook();
        User user = new User();
        user.setLoginName("");
        eBook.setUser(user);
        PagedResult<EBook> pagedResult = eBookService.selectEBookPages(eBook, 1, 15);
        logger.info("总条数" + pagedResult.getTotal());
        logger.info("总页数" + pagedResult.getPages());
        logger.info("当前页" + pagedResult.getPageNo());
        logger.info("未知" + pagedResult.getPageOffset());
        logger.info("条件" + pagedResult.getStrWhere());
        logger.info("数据长度" + pagedResult.getDataList().size());
    }

}
