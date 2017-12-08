package com.sttx.bookmanager.util.pages;

import java.util.List;

public class PagedResult<T> extends BaseEntity {

    /* serialVersionUID */
    private static final long serialVersionUID = 1L;
    private long pageNo;// 当前页

    private long pageSize;// 一页条数

    private long total;// 总条数

    private long pages;// 总页数
    private int pageOffset;
    private List<T> dataList;// 数据
    private String url;
    private String strWhere;

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

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
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
    public String toString() {
        return "PagedResult [pageOffset=" + pageOffset + ", dataList=" + dataList + ", pageNo=" + pageNo + ", pageSize="
                + pageSize + ", total=" + total + ", pages=" + pages + "]";
    }

}
