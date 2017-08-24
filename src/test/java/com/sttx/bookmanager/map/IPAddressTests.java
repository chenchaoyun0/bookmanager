package com.sttx.bookmanager.map;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.util.map.AddressUtils;
import com.sttx.bookmanager.util.map.vo.IPAddressVo;

public class IPAddressTests {
    @Test
    public void getAddressByIp() throws Exception {
        // 参数ip
        String ip = "219.136.134.157";
        // json_result用于接收返回的json数据
        String json_result = null;
        try {
            json_result = AddressUtils.getAddresses("ip=" + ip, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(json_result);
        IPAddressVo ipAddressVo = JSONObject.toJavaObject(jsonObject, IPAddressVo.class);
        System.out.println(JSONObject.toJSON(ipAddressVo));
    }
}
