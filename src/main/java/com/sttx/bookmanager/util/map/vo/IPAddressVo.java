package com.sttx.bookmanager.util.map.vo;

import java.io.Serializable;

/**
 * 
 * @Description
 * @author chenchaoyun[chenchaoyun@sttxtech.com]
 * @date 2017年8月24日 下午1:23:26
 */
public class IPAddressVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private IPAddressData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public IPAddressData getData() {
        return data;
    }

    public void setData(IPAddressData data) {
        this.data = data;
    }

}
