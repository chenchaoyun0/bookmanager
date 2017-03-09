package com.sttx.bookmanager.web.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class IPAddressMap {
    public static void main(String[] args) {
        String[] ipxy = getIPXY("123.123.52.104");
        String s = Arrays.toString(ipxy);
        System.err.println(s);
    }

    /**
    * 获取指定IP对应的经纬度（为空返回当前机器经纬度）
    * 
    * @param ip
    * @return
    */
    public static String[] getIPXY(String ip) {

        String ak = "orsN0xHVa8H0VxcMWW0uGf03";
        if (null == ip) {
            ip = "";
        }

        try {

            URL url = new URL("http://api.map.baidu.com/location/ip?ak=" + ak + "&ip=" + ip + "&coor=bd09ll");
            InputStream inputStream = url.openStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputReader);
            StringBuffer sb = new StringBuffer();
            String str;
            do {
                str = reader.readLine();
                sb.append(str);
            } while (null != str);

            str = sb.toString();
            if (null == str || str.isEmpty()) {
                return null;
            }

            // 获取坐标位子
            int index = str.indexOf("point");
            int end = str.indexOf("}}", index);

            if (index == -1 || end == -1) {
                return null;
            }

            str = str.substring(index - 1, end + 1);
            if (null == str || str.isEmpty()) {
                return null;
            }

            String[] ss = str.split(":");
            if (ss.length != 4) {
                return null;
            }

            String x = ss[2].split(",")[0];
            String y = ss[3];

            x = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", 1));
            y = y.substring(y.indexOf("\"") + 1, y.indexOf("\"", 1));

            return new String[] { x, y };

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
