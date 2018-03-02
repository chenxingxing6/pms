package com.peace.pms.util;

/**
 * 授权展示方式
 * @Author: cxx
 * @Date: 2018/3/1 15:45
 */

public enum Display {

    DEFAULT("default"),        //适用：sina                    ————默认的授权页面，适用于web浏览器。
    PAGE("page"),            //适用：人人，百度            ————全屏形式的授权页面(默认)，适用于web应用。
    IFRAME("iframe"),        //适用：人人
    POPUP("popup"),            //适用：人人，百度            ————弹框形式的授权页面，适用于桌面软件应用和web应用。
    TOUCH("touch"),            //适用：人人                    ————人人智能移动终端，最小尺寸（320px*480px）
    MOBILE("mobile"),        //适用：人人，百度，sina，qq    ————人人不支持js的移动终端，最小尺寸（480*800px），百度智能机。
    TV("tv"),                //适用：百度                    ————电视等超大显示屏使用的授权页面。
    PAD("pad"),                //适用：百度                    ————IPad/Android等智能平板电脑使用的授权页面。
    CLIENT("client"),        //适用：sina                    ————客户端版本授权页面，适用于PC桌面应用。
    APPONWEIBO("apponweibo"),//适用：sina                ————默认的站内应用授权页，授权后不返回access_token，只刷新站内应用父框架。
    WAP("wap");                //适用：sina                    ————wap版授权页面，适用于非智能手机。

    Display(String type) {
        this.type = type;
    }
    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
