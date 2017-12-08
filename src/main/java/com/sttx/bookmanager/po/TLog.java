package com.sttx.bookmanager.po;

import java.io.Serializable;

public class TLog extends TLogKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;

    private String userNickName;

    private String userAddress;

    private String userJwd;

    private String module;

    private String action;

    private Long actionTime;

    private String operTime;

    private Long count;

    public TLog() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TLog(String userName, String userNickName, String userAddress, String userJwd, String module, String action,
            Long actionTime, String operTime, Long count) {
        super();
        this.userName = userName;
        this.userNickName = userNickName;
        this.userAddress = userAddress;
        this.userJwd = userJwd;
        this.module = module;
        this.action = action;
        this.actionTime = actionTime;
        this.operTime = operTime;
        this.count = count;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName == null ? null : userNickName.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserJwd() {
        return userJwd;
    }

    public void setUserJwd(String userJwd) {
        this.userJwd = userJwd == null ? null : userJwd.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public Long getActionTime() {
        return actionTime;
    }

    public void setActionTime(Long actionTime) {
        this.actionTime = actionTime;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}