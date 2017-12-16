package com.sttx.bookmanager.po;

import java.io.Serializable;

import com.sttx.bookmanager.util.HideImg;

public class ScrollImage implements Serializable {
    private static final long serialVersionUID = 1L;
    @HideImg
    private String imageStr;
    private String imageDesc;

    public String getImageStr() {
        return imageStr;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

}
