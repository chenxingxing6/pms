package com.peace.pms.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author: cxx
 * @Date: 2018/3/1 15:38
 */
public class OathConfig {

    private static Properties props = new Properties();

    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("oauth.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setProps(Properties prop) {
        props = prop;
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }
}
