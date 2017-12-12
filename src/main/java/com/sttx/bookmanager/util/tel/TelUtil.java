package com.sttx.bookmanager.util.tel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TelUtil {
    // public static void main(String[] args) {
    // String telHome = TelUtil.getTelHome("18734170206");
    // System.out.println(telHome.toString());
    // }

    public static String getTelHome(String tel) {
        String httpUrl = "http://apis.baidu.com/apistore/mobilephoneservice/mobilephone";
        String httpArg = "tel=" + tel;
        String jsonResult = request(httpUrl, httpArg);
        System.out.println(jsonResult);
        return jsonResult;
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
