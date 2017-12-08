package com.sttx.bookmanager.service;

import com.sttx.bookmanager.po.EBook;
import com.sttx.bookmanager.util.pages.PagedResult;

public interface IEBookService {
    /**
     * 
     * @Title: insertSelective
     * @Description: 上传电子书
     * @param eBook
     * @return
     * @return: int
     */
    int insertSelective(EBook eBook);

    /**
     * 
     * @Title: selectByPrimaryKey
     * @Description: 根据id查找电子书,下载电子书
     * @param ebookId
     * @return
     * @return: EBook
     */
    EBook selectByPrimaryKey(String ebookId);

    /**
     * 
     * @Title: selectEBookPages
     * @Description: 插件分页，带条件查询
     * @param book
     * @param pageNo
     * @param pageSize
     * @return
     * @return: PagedResult<EBook>
     */
    PagedResult<EBook> selectEBookPages(EBook eBook, Integer pageNo, Integer pageSize);

    /**
     * 
     * @Title: updateDownloadCount
     * @Description:下载次数
     * @param ebookId
     * @return
     * @return: int
     */
    int updateDownloadCount(String ebookId);

    /**
     * 
     * @Title: deleteByPrimaryKey
     * @Description: 删除电子书
     * @param ebookId
     * @return
     * @return: int
     */
    int deleteByPrimaryKey(String ebookId);
}
