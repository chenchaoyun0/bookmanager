package com.sttx.bookmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.sttx.bookmanager.dao.EBookMapper;
import com.sttx.bookmanager.po.EBook;
import com.sttx.bookmanager.po.TImg;
import com.sttx.bookmanager.service.IEBookService;
import com.sttx.bookmanager.service.IImgService;
import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.bookmanager.util.file.NfsFileUtils;
import com.sttx.bookmanager.util.pages.BeanUtil;
import com.sttx.bookmanager.util.pages.PagedResult;

@Service("eBookService")
public class EBookServiceImpl implements IEBookService {
    @Autowired
    private EBookMapper eBookMapper;
    private static final Logger log = LoggerFactory.getLogger(EBookServiceImpl.class);
    @Autowired
    private IImgService imgService;
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
        log.info("bookList:{}", JSONObject.toJSON(bookList));
        List<EBook> arrayList = new ArrayList<EBook>();
        for (EBook eBook2 : bookList) {
            TImg tImg = new TImg();
            tImg.setLinkId(eBook2.getEbookId());
            log.info("查询图书图片begin...");
            List<TImg> imgList = imgService.selectList(tImg);
            log.info("查询图书图片end...imgList:{}", JSONObject.toJSON(imgList));
            TImg tImg2 = imgList.get(0);
            String imgPath = tImg2.getImgPath();
            String imageBase64Str = NfsFileUtils.getImageBase64Str(NfsFileUtils.getNfsUrl() + imgPath);
            eBook2.setEbookImg(imageBase64Str);
            arrayList.add(eBook2);
        }

        PagedResult<EBook> bookPagedResult = BeanUtil.toPagedResult(arrayList);

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
