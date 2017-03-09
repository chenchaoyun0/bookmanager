package com.sttx.bookmanager.web.filter;

import java.io.Serializable;

/**
 * {
    "Status": 0,
    "Description": "OK",
    "Base_info": {
        "city": "北京",
        "country": "中国",
        "county": "",
        "isp": "联通",
        "province": "北京"
    },
    "Net_info": null
}
 * @Description
 * @author chenchaoyun[chenchaoyun@sttxtech.com]
 * @date 2017年3月9日 下午9:29:17
 */
public class BaiduIpPO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Status;
    private String Description;
    private Base_info Base_info;
    private Net_info Net_info;

    /**
     * @return the status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        Status = status;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * @return the base_info
     */
    public Base_info getBase_info() {
        return Base_info;
    }

    /**
     * @param base_info the base_info to set
     */
    public void setBase_info(Base_info base_info) {
        Base_info = base_info;
    }

    /**
     * @return the net_info
     */
    public Net_info getNet_info() {
        return Net_info;
    }

    /**
     * @param net_info the net_info to set
     */
    public void setNet_info(Net_info net_info) {
        Net_info = net_info;
    }

}
