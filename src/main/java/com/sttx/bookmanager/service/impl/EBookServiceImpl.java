package com.sttx.bookmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sttx.bookmanager.dao.EBookMapper;
import com.sttx.bookmanager.po.EBook;
import com.sttx.bookmanager.service.IEBookService;
import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.bookmanager.util.pages.BeanUtil;
import com.sttx.bookmanager.util.pages.PagedResult;

@Service("eBookService")
public class EBookServiceImpl implements IEBookService {
    @Autowired
    private EBookMapper eBookMapper;

    public int insertSelective(EBook eBook) {
        try {
            return eBookMapper.insertSelective(eBook);
        } catch (Exception e) {
            throw new UserException("上传失败");
        }
    }

    public EBook selectByPrimaryKey(String ebookId) {

        try {
            return eBookMapper.selectByPrimaryKey(ebookId);
        } catch (Exception e) {
            throw new UserException("查询失败");
        }
    }

    public PagedResult<EBook> selectEBookPages(EBook eBook, Integer pageNo, Integer pageSize) {
        pageNo = (pageNo == null) ? (Integer) 1 : pageNo;
        pageSize = (pageSize == null) ? (Integer) 5 : pageSize;
        PageHelper.startPage(pageNo, pageSize);

        List<EBook> bookList = eBookMapper.selectEBookPages(eBook);
        PagedResult<EBook> bookPagedResult = BeanUtil.toPagedResult(bookList);

        bookPagedResult.setPageNo(pageNo);
        bookPagedResult.setPageSize(pageSize);

        return bookPagedResult;
    }

    public int updateDownloadCount(String ebookId) {
        return eBookMapper.updateDownloadCount(ebookId);
    }

    public int deleteByPrimaryKey(String ebookId) {
        return eBookMapper.deleteByPrimaryKey(ebookId);
    }
}
