package com.sttx.bookmanager.util.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StandardTime {
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.sss");
        String time = sdf.format(date);
        return time;
    }
}
