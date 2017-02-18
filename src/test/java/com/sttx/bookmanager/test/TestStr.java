package com.sttx.bookmanager.test;

import com.sttx.bookmanager.util.uuidno.UUID2NO;

public class TestStr {
    public static void main(String[] args) {

        String uuid2no = UUID2NO.getUUID2NO();

        System.out.println(uuid2no);

        String originalFilename = "asdasd.jpg";
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        System.out.println(substring);

        System.out.println("dassdasd\r\ndad");
    }
}
