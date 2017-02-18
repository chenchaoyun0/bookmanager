package com.sttx.bookmanager.po;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Book implements Serializable {
    private String bookId;

    private String userId;

    private Integer bookCountry;

    private String bookHouse;

    private String bookNo;

    private String bookImg;
    private BufferedImage bookImage;
    private String bookName;

    private String bookAuthor;

    private Double bookPrice;

    private String bookDesc;

    private Integer bookCount;
    private String bookByTime;
    private Integer bookRemain;

    private String bookUploadTime;
    private int bookType;
    private Integer bookStatus;

    private Integer bookFlag;

    private User user;

    public int getBookType() {
        return bookType;
    }

    public void setBookType(int bookType) {
        this.bookType = bookType;
    }

    public String getBookHouse() {
        return bookHouse;
    }

    public void setBookHouse(String bookHouse) {
        this.bookHouse = bookHouse;
    }

    public String getBookByTime() {
        return bookByTime;
    }

    public void setBookByTime(String bookByTime) {
        this.bookByTime = bookByTime;
    }

    public Integer getBookCountry() {
        return bookCountry;
    }

    public void setBookCountry(Integer bookCountry) {
        this.bookCountry = bookCountry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo == null ? null : bookNo.trim();
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg == null ? null : bookImg.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public BufferedImage getBookImage() {
        return bookImage;
    }

    public void setBookImage(BufferedImage bookImage) {
        this.bookImage = bookImage;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc == null ? null : bookDesc.trim();
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Integer getBookRemain() {
        return bookRemain;
    }

    public void setBookRemain(Integer bookRemain) {
        this.bookRemain = bookRemain;
    }

    public String getBookUploadTime() {
        return bookUploadTime;
    }

    public void setBookUploadTime(String bookUploadTime) {
        this.bookUploadTime = bookUploadTime;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Integer getBookFlag() {
        return bookFlag;
    }

    public void setBookFlag(Integer bookFlag) {
        this.bookFlag = bookFlag;
    }
}