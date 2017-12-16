package com.sttx.bookmanager.po;

import java.io.Serializable;
import java.util.List;

import com.sttx.bookmanager.util.HideImg;

/**
 * base64
 * 
 * @Description
 * @author chenchaoyun[chenchaoyun@sttxtech.com]
 * @date 2017年12月16日 下午5:49:11
 */
public class ImageVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @HideImg
    private String myPhoto;
    private List<ScrollImage> scrollImageList;
    private List<ImageBase64Vo> springBootImageList;
    private List<ImageBase64Vo> devpsImageList;
    private List<ImageBase64Vo> appsImageList;

    public String getMyPhoto() {
        return myPhoto;
    }

    public void setMyPhoto(String myPhoto) {
        this.myPhoto = myPhoto;
    }

    public List<ScrollImage> getScrollImageList() {
        return scrollImageList;
    }

    public void setScrollImageList(List<ScrollImage> scrollImageList) {
        this.scrollImageList = scrollImageList;
    }

    public List<ImageBase64Vo> getSpringBootImageList() {
        return springBootImageList;
    }

    public void setSpringBootImageList(List<ImageBase64Vo> springBootImageList) {
        this.springBootImageList = springBootImageList;
    }

    public List<ImageBase64Vo> getDevpsImageList() {
        return devpsImageList;
    }

    public void setDevpsImageList(List<ImageBase64Vo> devpsImageList) {
        this.devpsImageList = devpsImageList;
    }

    public List<ImageBase64Vo> getAppsImageList() {
        return appsImageList;
    }

    public void setAppsImageList(List<ImageBase64Vo> appsImageList) {
        this.appsImageList = appsImageList;
    }


}
