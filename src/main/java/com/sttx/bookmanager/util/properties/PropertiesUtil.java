package com.sttx.bookmanager.util.properties;

import java.io.IOException;
import java.util.Properties;

import com.sttx.bookmanager.util.exception.UserException;

public class PropertiesUtil {
    private static Properties props = new Properties();

    public static String getFilePath(String propertiesFilePath, String propertiesKey) {
        try {
            // 获取配置文件内容
            props.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesFilePath));
        } catch (IOException e) {
            throw new UserException("读取配置文件出错");
        }
        return props.getProperty(propertiesKey);
    }
}
