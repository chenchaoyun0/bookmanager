package com.sttx.bookmanager.util.passwd;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sttx.bookmanager.util.exception.UserException;

/**
 * ClassName: SHA <br/>
 * Function:这是SHA密码的加密工具<br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016-1-8 上午12:24:04 <br/>
 *
 * @author ccy
 * @version
 * @since JDK 1.6
 */
public class SHA {
    public static final String ALGORITHM = "SHA-256";

    public static String getSHA256(String mes) {
        MessageDigest md = null;
        String digestStr = "";
        try {
            md = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            if (null != md) {
                byte[] origBytes;
                origBytes = mes.getBytes("UTF-8");

                md.update(origBytes);
                byte[] digestRes = md.digest();
                digestStr = getDigestStr(digestRes);

            }
        } catch (UnsupportedEncodingException e) {
            throw new UserException("系统错误");
        }
        return digestStr;
    }

    private static String getDigestStr(byte[] origBytes) {
        String tempStr = null;
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < origBytes.length; i++) {
            tempStr = Integer.toHexString(origBytes[i] & 0xff);
            if (tempStr.length() == 1) {
                stb.append("0");
            }
            stb.append(tempStr);

        }
        return stb.toString();
    }
}
