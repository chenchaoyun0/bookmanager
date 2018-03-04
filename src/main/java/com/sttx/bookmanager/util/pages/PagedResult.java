package com.sttx.bookmanager.util.pages;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagedResult<T> extends BaseEntity {

    /* serialVersionUID */
    private static final long serialVersionUID = 1L;
    private int pageNo;// 当前页

    private int pageSize;// 一页条数

    private long total;// 总条数

    private long pages;// 总页数
    private int pageOffset;
    private List<T> dataList;// 数据
    private String url;
    private String strWhere;
    
 // 排序条件
    private Sort sort;

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public String getStrWhere() {
        return strWhere;
    }

    public void setStrWhere(String strWhere) {
        this.strWhere = strWhere;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }
    
    @Override
    public Pageable next() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pageable first() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasPrevious() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String toString() {
        return "PagedResult [pageOffset=" + pageOffset + ", dataList=" + dataList + ", pageNo=" + pageNo + ", pageSize="
                + pageSize + ", total=" + total + ", pages=" + pages + "]";
    }

 // 当前页面
    @Override
    public int getPageNumber() {
        return getPageNo();
    }

    // 每一页显示的条数
    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    // 第二页所需要增加的数量
    @Override
    public int getOffset() {
        return (getPageNo() - 1) * getPageSize();
    }

    @Override
    public Sort getSort() {
        return sort;
    }

}
