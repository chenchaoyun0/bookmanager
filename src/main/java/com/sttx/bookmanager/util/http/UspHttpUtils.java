package com.sttx.bookmanager.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;

public class UspHttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(UspHttpUtils.class);

    public static <T> T doPostJsonType(String uri, Object msg, Class<T> cla) throws Exception {
        T t = null;
        try {
            String response = doPostJsonType(uri, msg);
            t = toJavaObj(cla, response);
        } catch (Exception e) {
            logger.error("UspHttpUtils doPost 抛出异常,{}", e);
            throw new Exception("USPB1050", e);// 发送POST请求失败
        }
        return t;

    }

    private static <T> T toJavaObj(Class<T> cla, String response) {
        logger.info("response:{}", response);
        return JSONObject.parseObject(response, cla);
    }

    public static String doPostJsonType(String uri, Object msg) throws Exception {
        String response = null;
        try {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.addHeader("content-type", "application/json;charset=UTF-8");
            httpPost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            StringEntity entity = new StringEntity(JsonUtil.toJson(msg), "utf-8");
            ((HttpEntityEnclosingRequestBase) httpPost).setEntity(entity);
            CloseableHttpResponse httpResponse = HttpClients.createDefault().execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
            if (200 != statusCode) {
                logger.error("连接异常,statusCode:{},reasonPhrase:{}", statusCode, reasonPhrase);
                return "{\"resCode\":\"USPB1050\",\"resMsg\":\"连接restful异常,statusCode:" + statusCode + "\"}";
            }
            response = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            logger.error("UspHttpUtils doPost 抛出异常,{}", e);
            throw new Exception("USPB1050", e);// 发送POST请求失败
        }
        return response;

    }

    public static <T> T doPostStringType(String url, String param, Class<T> cla) {
        String response = doPostStringType(url, param);
        return toJavaObj(cla, response);
    }

    /** 向指定 URL 发送POST方法的请求 */
    public static String doPostStringType(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.error("发送 POST 请求出现异常", e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error("关闭流异常", ex);
            }
        }
        return result.toString();
    }

    public static String doGetStringType(String uri) throws Exception {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        String errorMsg = null;
        logger.info("uri:{}" + uri);
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(uri);
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 != statusCode) {
                errorMsg = "连接异常,statusCode:" + statusCode;
                logger.error(errorMsg);
                return errorMsg;
            }
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            logger.error("UspHttpUtils doPostStringType 抛出异常,{}", e);
            throw new Exception(e);
        }
    }

    public static String doGetStringType(String uri, Map<String, String> params) throws Exception {
        StringBuilder sb = new StringBuilder(uri + "?");
        for (Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (StringUtils.isNotBlank(value)) {
                sb.append(key + "=" + value + "&");
            } else {
                logger.error("doGetStringType value为空 key=" + key + "value=" + value);
            }
        }
        return doGetStringType(sb.toString());
    }

    public static <T> T doGetStringType(String uri, Class<T> cla) throws Exception {
        String response = doGetStringType(uri);
        return toJavaObj(cla, response);
    }

}
