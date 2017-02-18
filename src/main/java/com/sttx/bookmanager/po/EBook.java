package com.sttx.bookmanager.po;

import java.io.Serializable;

public class EBook implements Serializable {
    private String ebookId;
    private String userId;
    private String ebookName;
    private Integer ebookCountry;
    private String ebookNo;
    private String ebookHouse;
    private String ebookImg;
    private String ebookDesc;
    private String ebookAuthor;
    private String ebookType;
    private Integer ebookSize;
    private String ebookPath;
    private String ebookUploadTime;
    private Integer ebookDownloadCount;
    private Integer ebookFlag;
    private User user;

    public Integer getEbookCountry() {
        return ebookCountry;
    }

    public void setEbookCountry(Integer ebookCountry) {
        this.ebookCountry = ebookCountry;
    }

    public String getEbookHouse() {
        return ebookHouse;
    }

    public void setEbookHouse(String ebookHouse) {
        this.ebookHouse = ebookHouse;
    }

    public String getEbookImg() {
        return ebookImg;
    }

    public void setEbookImg(String ebookImg) {
        this.ebookImg = ebookImg;
    }

    public String getEbookDesc() {
        return ebookDesc;
    }

    public void setEbookDesc(String ebookDesc) {
        this.ebookDesc = ebookDesc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEbookId() {
        return ebookId;
    }

    public void setEbookId(String ebookId) {
        this.ebookId = ebookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEbookName() {
        return ebookName;
    }

    public void setEbookName(String ebookName) {
        this.ebookName = ebookName == null ? null : ebookName.trim();
    }

    public String getEbookNo() {
        return ebookNo;
    }

    public void setEbookNo(String ebookNo) {
        this.ebookNo = ebookNo == null ? null : ebookNo.trim();
    }

    public String getEbookAuthor() {
        return ebookAuthor;
    }

    public void setEbookAuthor(String ebookAuthor) {
        this.ebookAuthor = ebookAuthor == null ? null : ebookAuthor.trim();
    }

    public String getEbookType() {
        return ebookType;
    }

    public void setEbookType(String ebookType) {
        this.ebookType = ebookType == null ? null : ebookType.trim();
    }

    public Integer getEbookSize() {
        return ebookSize;
    }

    public void setEbookSize(Integer ebookSize) {
        this.ebookSize = ebookSize;
    }

    public String getEbookPath() {
        return ebookPath;
    }

    public void setEbookPath(String ebookPath) {
        this.ebookPath = ebookPath == null ? null : ebookPath.trim();
    }

    public String getEbookUploadTime() {
        return ebookUploadTime;
    }

    public void setEbookUploadTime(String ebookUploadTime) {
        this.ebookUploadTime = ebookUploadTime;
    }

    public Integer getEbookDownloadCount() {
        return ebookDownloadCount;
    }

    public void setEbookDownloadCount(Integer ebookDownloadCount) {
        this.ebookDownloadCount = ebookDownloadCount == null ? null : ebookDownloadCount;
    }

    public Integer getEbookFlag() {
        return ebookFlag;
    }

    public void setEbookFlag(Integer ebookFlag) {
        this.ebookFlag = ebookFlag;
    }
}