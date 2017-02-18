package com.sttx.bookmanager.po;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private String userId;

    private String loginName;

    private String realName;

    private Integer userSex;

    private String userPwd;

    private String userEmail;

    private String userTel;

    private String userBirthday;

    private String userHead;

    private String userCode;

    private Integer userStatus;

    private String userRegisttime;

    private int userRole;

    private Integer userFlag;

    /* 封装类 */
    private List<Book> bookList;
    private List<OrderItem> orderList;
    private List<EBook> eBookList;

    public List<Book> getBookList() {
        return bookList;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public List<OrderItem> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderItem> orderList) {
        this.orderList = orderList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<EBook> geteBookList() {
        return eBookList;
    }

    public void seteBookList(List<EBook> eBookList) {
        this.eBookList = eBookList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead == null ? null : userHead.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserRegisttime() {
        return userRegisttime;
    }

    public void setUserRegisttime(String userRegisttime) {
        this.userRegisttime = userRegisttime;
    }

    public Integer getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(Integer userFlag) {
        this.userFlag = userFlag;
    }
}