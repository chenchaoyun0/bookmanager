package com.sttx.bookmanager.post;

import com.sttx.bookmanager.util.http.UspHttpUtils;

public class TestHttpUtils {
  public static void main(String[] args) {
    try {
      String result = UspHttpUtils.doGetStringType("http://58.87.121.73:9091/info");
      System.out.println(result);
    } catch (Exception e) {
       e.printStackTrace();
    }

  }
}
