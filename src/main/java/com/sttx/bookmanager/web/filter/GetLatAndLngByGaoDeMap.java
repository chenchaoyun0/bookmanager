package com.sttx.bookmanager.web.filter;

/** 
 * 高的的地图 
 * @author jueyue 
 * 
 */  
public class GetLatAndLngByGaoDeMap { 
	public static void main(String[] args) {
		String point = getPoint("中国,华南,广西壮族自治区,来宾市");
		String substring = "中国,华南,广西壮族自治区,来宾市,电信".substring(0, "中国,华南,广西壮族自治区,来宾市,电信".lastIndexOf(","));
		System.out.println(substring);
		System.out.println(point);
	}
	public static String getPoint(String address){
        try {    
               String sCurrentLine;    
               String sTotalString;    
               sCurrentLine = "";    
               sTotalString = "";    
               java.io.InputStream l_urlStream;    
                 
               java.net.URL l_url = new java.net.URL("http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=702632E1add3d4953d0f105f27c294b9&callback=showLocation");    
               java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();    
               l_connection.connect();    
               l_urlStream = l_connection.getInputStream();    
               java.io.BufferedReader l_reader = new java.io.BufferedReader(new java.io.InputStreamReader(l_urlStream));     
               String str=l_reader.readLine();
               System.err.println(str);
               //用经度分割返回的网页代码  
               String s=","+"\""+"lat"+"\""+":";  
               String strs[]=str.split(s, 2);  
               String s1="\""+"lng"+"\""+":";  
              String a[]=strs[0].split(s1, 2);  
//              shop.setLng(a[1]);  
              System.out.println(a[1]);
              s1="}"+","+"\"";  
             String a1[]=strs[1].split(s1, 2);  
//              shop.setLat(a1[0]);  
             System.out.println(a1[0]);
             return "东经:"+a[1]+" 北纬："+a1[0];
           } catch (Exception e) {    
               throw new RuntimeException(e);   
           }    
   }  
} 