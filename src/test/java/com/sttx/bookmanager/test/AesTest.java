package com.sttx.bookmanager.test;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.util.tuling.Aes;
import com.sttx.bookmanager.util.tuling.Md5;
import com.sttx.bookmanager.util.tuling.PostServer;

/**
 * 加密请求测试类
 * 
 * @author 图灵机器人
 *
 */
public class AesTest {

    @Test
    public void testAes() {
        // 图灵网站上的secret
        String secret = "6010dd4e5210b874";
        // 图灵网站上的apiKey
        String apiKey = "cd6707a4c47d43dcb8d73fb7f4ca57bd";
        String cmd = "你好丑";// 测试用例
        // 待加密的json数据
        String data = "{\"key\":\"" + apiKey + "\",\"info\":\"" + cmd + "\"}";
        // 获取时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());

        // 生成密钥
        String keyParam = secret + timestamp + apiKey;
        String key = Md5.MD5(keyParam);

        // 加密
        Aes mc = new Aes(key);
        data = mc.encrypt(data);

        // 封装请求参数
        JSONObject json = new JSONObject();
        json.put("key", apiKey);
        json.put("timestamp", timestamp);
        json.put("data", data);
        // 请求图灵api
        String result = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");
        System.out.println(result);
    }

}