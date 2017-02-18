package com.sttx.bookmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sttx.bookmanager.po.Book;

public interface BookMapper {
    int deleteByPrimaryKey(String bookId);

    int insert(Book record);

    /**
     * 
     * @Title: insertSelective
     * @Description: 上传新的书籍
     * @param book
     * @return
     * @return: int
     */
    int insertSelective(Book book);

    Book selectByPrimaryKey(String bookId);

    /**
     * 
     * @Title: updateByPrimaryKeySelective
     * @Description: 更新图书信息
     * @param book
     * @return
     * @return: int
     */
    int updateByPrimaryKeySelective(Book book);

    int updateByPrimaryKey(Book record);

    /**
     * 
     * @Title: selectBookDetailMes
     * @Description: 通过图书id查找详细信息
     * @param bookId
     * @return
     * @return: Book
     */
    Book selectBookDetailMes(@Param("bookId") String bookId);

    /**
     * 
     * @Title: selectBookPages
     * @Description: 插件分页，多条件查询
     * @param book
     * @return
     * @return: List<Book>
     */
    List<Book> selectBookPages(@Param("book") Book book);

    /**
     * 查某书在架的有多少本
     * 
     * @param bookId
     */
    int countByBookId(String bookId);

}