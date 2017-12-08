package com.sttx.bookmanager.po;

import java.io.Serializable;

public class TLogKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private String logId;

    private String userIp;

    public TLogKey() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TLogKey(String logId, String userIp) {
        super();
        this.logId = logId;
        this.userIp = userIp;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }
}