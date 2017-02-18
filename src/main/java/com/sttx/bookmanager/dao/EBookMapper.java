package com.sttx.bookmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sttx.bookmanager.po.EBook;

public interface EBookMapper {
    int deleteByPrimaryKey(String ebookId);

    int insert(EBook eBook);

    /**
     * 
     * @Title: insertSelective
     * @Description: 上传电子书
     * @param eBook
     * @return
     * @return: int
     */
    int insertSelective(EBook eBook);

    EBook selectByPrimaryKey(String ebookId);

    int updateByPrimaryKeySelective(EBook eBook);

    int updateByPrimaryKey(EBook eBook);

    /**
     * 
     * @Title: selectEBookPages
     * @Description: 插件分页，带条件查询
     * @param eBook
     * @return
     * @return: List<EBook>
     */
    List<EBook> selectEBookPages(@Param("eBook") EBook eBook);

    int updateDownloadCount(@Param("ebookId") String ebookId);
}