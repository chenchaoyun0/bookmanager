package com.sttx.bookmanager.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ThreadNum implements Runnable {
    private ThreadLocal<Integer> tl = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            ThreadNum threadNum = new ThreadNum();
            threadNum.run();
        }
    }

    @Override
    public void run() {
        try {
            int j = 0;
            tl.set(j);
            for (int i = 0; i < 100; i++) {
                long startTime = System.currentTimeMillis();
                System.out.println("+++++begin...i:" + i);
                URL url = new URL("http://www.ccy123.cn/");
                URLConnection url_conn = url.openConnection();
                url_conn.setConnectTimeout(1000);
                //                url_conn.connect();
                InputStream ips = url_conn.getInputStream();
                System.out.println("+++++end...i:" + i);
                long endTime = System.currentTimeMillis();
                System.out.println("+++++time:" + (endTime - startTime));
                BufferedReader br = new BufferedReader(new InputStreamReader(ips));
                String line = null;
                //                while ((line = br.readLine()) != null) {
                //                    System.out.println(line.length());
                //                }
                j = tl.get() + 1;
                tl.set(j);
                //                System.out.println(tl.get());

            }
            if (tl.get() == 100) {
                System.out.println("==============���100��===========");
                tl.set(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
