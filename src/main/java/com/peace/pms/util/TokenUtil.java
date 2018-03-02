package com.peace.pms.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.RandomStringUtils;
import com.alibaba.fastjson.JSONObject;
/**
 * Token 帮助类
 * @Author: cxx
 * @Date: 2018/3/1 15:41
 */
public class TokenUtil {
    private static final String STR_S = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 参考自 qq sdk
     * @param @param string
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getAccessToken(String string) {
        String accessToken = "";
        try {
            JSONObject json = JSONObject.parseObject(string);
            if (null != json) {
                accessToken = json.getString("access_token");
            }
        } catch (Exception e) {
            Matcher m = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$").matcher(string);
            if (m.find()) {
                accessToken = m.group(1);
            } else {
                Matcher m2 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)$").matcher(string);
                if (m2.find()) {
                    accessToken = m2.group(1);
                }
            }
        }
        return accessToken;
    }

    /**
     * 匹配openid
     * @param @param string
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getOpenId(String string) {
        String openid = null;
        Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(string);
        if (m.find())
            openid = m.group(1);
        return openid;
    }

    /**
     * sina uid于qq分离
     * @Title: getUid
     * @param @param string
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getUid(String string){
        JSONObject json = JSONObject.parseObject(string);
        return json.getString("uid");
    }

    /**
     * 生成一个随机数
     * @return
     */
    public static String randomState() {
        return RandomStringUtils.random(24, STR_S);
    }
}
