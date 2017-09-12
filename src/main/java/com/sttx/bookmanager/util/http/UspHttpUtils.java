package com.sttx.bookmanager.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.ddp.logger.DdpLoggerFactory;

public class UspHttpUtils {
    private final static Logger log = DdpLoggerFactory.getLogger(UspHttpUtils.class);

    public static <T> T doPostJsonType(String uri, Map<String, Object> msg, Class<T> cla) throws UserException {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("content-type", "application/json;charset=UTF-8");
        httpPost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        T t = null;
        try {
            StringEntity entity = new StringEntity(CcyJsonUtil.toJson(msg), "utf-8");
            ((HttpEntityEnclosingRequestBase) httpPost).setEntity(entity);
            CloseableHttpResponse httpResponse = HttpClients.createDefault().execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
            if (200 != statusCode) {
                log.error("连接异常,statusCode:{},reasonPhrase:{}", statusCode, reasonPhrase);
                return CcyJsonUtil.fromJson("{\"resCode\":\"USPB1050\",\"resMsg\":\"连接restful异常,statusCode:" + statusCode + "\"}", cla);
            }
            String response = EntityUtils.toString(httpResponse.getEntity());
            t = CcyJsonUtil.fromJson(response, cla);
        } catch (IOException e) {
            log.error("UspHttpUtils doPost 抛出异常,{}", e);
            throw new UserException("USPB1050", e);// 发送POST请求失败
        }
        return t;

    }

    /** 向指定 URL 发送POST方法的请求 */
    public static String doPostStringType(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
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
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("发送 POST 请求出现异常", e);
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
                log.error("关闭流异常", ex);
            }
        }
        return result;
    }
}
