package com.sttx.bookmanager.web.filter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

public class BaiduIP {
    public static void main(String[] args) {
        getBaiduIpPO("112.90.82.139");
    }

    public static BaiduIpPO getBaiduIpPO(String ip) {
        String httpUrl = "http://apis.baidu.com/bdyunfenxi/intelligence/ip";
        String httpArg = "ip=" + ip;
        String jsonResult = request(httpUrl, httpArg);
        System.out.println(jsonResult);
        BaiduIpPO parseObject = JSONObject.parseObject(jsonResult, BaiduIpPO.class);
        System.out.println("---" + JSONObject.toJSON(parseObject));
        return parseObject;
    }

    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "cf1058d371ead3adc3c309a5791e8ca7");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
