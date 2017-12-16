package com.sttx.bookmanager.po;

import java.io.Serializable;

import com.sttx.bookmanager.util.HideImg;

public class ImageBase64Vo implements Serializable {

    private static final long serialVersionUID = 1L;
    @HideImg
    private String base64Str;

    public String getBase64Str() {
        return base64Str;
    }

    public void setBase64Str(String base64Str) {
        this.base64Str = base64Str;
    }

}
