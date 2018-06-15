package com.sttx.bookmanager.json;

import java.io.InputStream;

import org.apache.cxf.helpers.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @Description
 * @author chenchaoyun[chenchaoyun@sttxtech.com]
 * @date 2017年7月28日 下午12:27:50
 */
public class TestJSONString {

    @Test
    public void testCast() throws Exception {
        InputStream in = TestJSONString.class.getClassLoader().getResourceAsStream("FieldV2.json");
        String jsonString = FileUtils.normalizeCRLF(in);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        FieldV2Resp resp = jsonObject.toJavaObject(FieldV2Resp.class);
//        log.info("resp:{}", JSONObject.toJSON(resp));
    }
}
