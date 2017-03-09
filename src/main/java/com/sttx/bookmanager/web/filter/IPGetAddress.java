package com.sttx.bookmanager.web.filter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

/** 
 * 高的的地图 
 * @author jueyue 
 * 
 */
public class IPGetAddress {
    public static void main(String[] args) throws Exception {
        String address = getAddress("219.159.235.102");
        System.out.println(address);
    }

    public static String getAddress(String ip) {
        Map<String, String> map = null;
        try {
            map = IPGetAddress.getPoint(ip);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return map.get("country") + "-" + map.get("province") + "-" + map.get("city") + "-" + map.get("isp");
    }

    public static Map<String, String> getPoint(String ip) throws Exception {
        URL url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=" + ip);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
        out.flush();
        out.close();
        //一旦发送成功，用以下方法就可以得到服务器的回应：
        String res;
        InputStream l_urlStream;
        l_urlStream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(l_urlStream, "UTF-8"));
        StringBuilder sb = new StringBuilder("");
        while ((res = in.readLine()) != null) {
            sb.append(res.trim());
        }
        String str = sb.toString();
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(str)) {
            int addStart = str.indexOf("=");
            int end = str.indexOf(";");
            if (addStart > 0) {
                String address1 = str.substring(addStart + 2, end);
                System.out.println(address1);
                JSONObject jsonObject = JSONObject.fromObject(address1);
                @SuppressWarnings("unchecked")
                Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
                for (Entry<String, Object> entry : mapJson.entrySet()) {
                    map.put(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        return map;
    }
}