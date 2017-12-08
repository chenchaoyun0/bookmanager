package com.sttx.bookmanager.post;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;

import com.sttx.bookmanager.util.http.HttpPostUtil;
import com.sttx.bookmanager.util.http.UspHttpUtils;
import com.sttx.ddp.logger.DdpLoggerFactory;

public class TestSendPost {
    private final static Logger log = DdpLoggerFactory.getLogger(UspHttpUtils.class);
    @Test
    public void test() throws Exception {
        Map<String, Object> msg=new HashMap<>();
        msg.put("loginName", "chenchaoyun");
        msg.put("userPwd", "111111");
        String resp = UspHttpUtils.doPostJsonType("http://39.108.0.229/", msg, String.class);
        log.info("resp:{}", resp);
    }

    @Test
    public void test2() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("loginName=");
        sb.append("chenchaoyun");
        sb.append("&");
        sb.append("userPwd=");
        sb.append("111111");
        String resp = UspHttpUtils.doPostStringType("http://39.108.0.229/user/userLoginSubmit.action", sb.toString());
        log.info("resp:{}", resp);
    }

    @Test
    public void test3() throws Exception {
        HttpPostUtil u = new HttpPostUtil("http://39.108.0.229/");
        byte[] b = u.send();
        String result = new String(b);
        log.info("result:{}", result);
    }

    @Test
    public void test4() throws Exception {
        HttpPostUtil u = new HttpPostUtil("http://39.108.0.229/book/selectBookPages");
        byte[] b = u.send();
        String result = new String(b);
        log.info("result:{}", result);
    }
}
