package com.sttx.bookmanager.util.uuidno;

import cn.itcast.commons.CommonUtils;

public class UUID2NO {
    public static String getUUID2NO() {
        String uuid = CommonUtils.uuid().toLowerCase();
        char[] ds = uuid.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ds.length; i++) {
            sb.append((int) ds[i]);
        }
        return sb.toString().substring(0, 15);
    }
}