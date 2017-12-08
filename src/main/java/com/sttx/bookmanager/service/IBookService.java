package com.sttx.bookmanager.service;

import com.sttx.bookmanager.po.Book;
import com.sttx.bookmanager.util.pages.PagedResult;

public interface IBookService {
    int insertSelective(Book book);

    Book selectByPrimaryKey(String bookId);

    /* 插件分页，带查询 */
    PagedResult<Book> selectBookPages(Book book, Integer pageNo, Integer pageSize);

    /**
     * 
     * @Title: updateByPrimaryKeySelective
     * @Description:更新
     * @param book
     * @return
     * @return: int
     */
    int updateByPrimaryKeySelective(Book book);

    /**
     * 下架书籍
     * 
     * @param bookId
     * @return
     */
    int unmountBook(String bookId);

    /**
     * 上架书籍
     * 
     * @param bookId
     * @return
     */
    int mountBook(String bookId);

    /**
     * 查某一本书的在架数量是否为正数
     * 
     * @param bookId
     * @return
     */
    int countByBookId(String bookId);
}
