package com.sttx.bookmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sttx.bookmanager.dao.BookMapper;
import com.sttx.bookmanager.po.Book;
import com.sttx.bookmanager.service.IBookService;
import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.bookmanager.util.pages.BeanUtil;
import com.sttx.bookmanager.util.pages.PagedResult;

@Service("bookService")
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookMapper bookMapper;

    public int insertSelective(Book book) {

        try {
            return bookMapper.insertSelective(book);
        } catch (UserException e) {
            throw new UserException("操作失败");
        }
    }

    public Book selectByPrimaryKey(String bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }

    public PagedResult<Book> selectBookPages(Book book, Integer pageNo, Integer pageSize) {
        /**
         * 默认是12条
         */
        pageNo = pageNo == null ? (Integer) 1 : pageNo;
        pageSize = pageSize == null ? (Integer) 8 : pageSize;
        PageHelper.startPage(pageNo, pageSize);// 告诉插件开始分页

        List<Book> bookList = bookMapper.selectBookPages(book);
        PagedResult<Book> bookPagedResult = BeanUtil.toPagedResult(bookList);

        bookPagedResult.setPageNo(pageNo);
        bookPagedResult.setPageSize(pageSize);

        return bookPagedResult;
    }

    public int updateByPrimaryKeySelective(Book book) {
        return bookMapper.updateByPrimaryKeySelective(book);
    }

    public int unmountBook(String bookId) {
        Book book = new Book();
        book.setBookId(bookId);
        book.setBookStatus(-1);
        return bookMapper.updateByPrimaryKeySelective(book);
    }

    public int mountBook(String bookId) {
        Book book = new Book();
        book.setBookId(bookId);
        book.setBookStatus(1);
        return bookMapper.updateByPrimaryKeySelective(book);
    }

    public int countByBookId(String bookId) {
        return bookMapper.countByBookId(bookId);
    }

}
