package com.sttx.bookmanager.po;

import java.util.List;

/**
 * base64
 * 
 * @Description
 * @author chenchaoyun[chenchaoyun@sttxtech.com]
 * @date 2017年12月16日 下午5:49:11
 */
public class ImageVo {
    private String myPhotol;
    private List<ScrollImage> scrollImageList;
    private List<String> springBootImageList;
    private List<String> devpsImageList;
    private List<String> appsImageList;

    public String getMyPhotol() {
        return myPhotol;
    }

    public void setMyPhotol(String myPhotol) {
        this.myPhotol = myPhotol;
    }

    public List<ScrollImage> getScrollImageList() {
        return scrollImageList;
    }

    public void setScrollImageList(List<ScrollImage> scrollImageList) {
        this.scrollImageList = scrollImageList;
    }

    public List<String> getSpringBootImageList() {
        return springBootImageList;
    }

    public void setSpringBootImageList(List<String> springBootImageList) {
        this.springBootImageList = springBootImageList;
    }

    public List<String> getDevpsImageList() {
        return devpsImageList;
    }

    public void setDevpsImageList(List<String> devpsImageList) {
        this.devpsImageList = devpsImageList;
    }

    public List<String> getAppsImageList() {
        return appsImageList;
    }

    public void setAppsImageList(List<String> appsImageList) {
        this.appsImageList = appsImageList;
    }

}
