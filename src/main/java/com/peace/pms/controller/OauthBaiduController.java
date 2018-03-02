package com.peace.pms.controller;

import com.alibaba.fastjson.JSONObject;
import com.peace.pms.oauth.OauthBaidu;
import com.peace.pms.oauth.OauthQQ;
import com.peace.pms.util.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/api/baidu")
public class OauthBaiduController {
    //OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
    private static final String SESSION_STATE = "_SESSION_STATE_BAIDU_";
    private static Logger log = LoggerFactory.getLogger(OauthBaiduController.class);

    @RequestMapping("/callback")
    @ResponseBody
    public String callback(HttpServletRequest request){
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        // 取消了授权
        if (StringUtils.isBlank(state)||StringUtils.isBlank(code)){
            return "取消了授权";
        }
        //清除state以防下次登录授权失败
        //session.removeAttribute(SESSION_STATE);
        //获取用户信息
        try{
            JSONObject userInfo = OauthBaidu.me().getUserInfoByCode(code);
            log.debug(userInfo.toString());
            String type = "baidu";
            // 将相关信息存储数据库...
            return userInfo.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        //这里你们可以自己修改，授权成功后，调到首页
        return "error";
    }

    /**
     * 构造授权请求url
     * @return void    返回类型
     * @throws
     */
    @RequestMapping("/login")
    public String index(HttpServletRequest request, HttpServletResponse response){
        String state = TokenUtil.randomState();
        //state就是一个随机数，保证安全
        try {
            String url = OauthBaidu.me().getAuthorizeUrl(state);
            return "redirect:"+url;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/index.jsp";
    }
}
