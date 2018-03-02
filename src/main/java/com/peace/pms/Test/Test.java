package com.peace.pms.Test;

import com.peace.pms.oauth.*;
import com.peace.pms.util.TokenUtil;
import java.io.UnsupportedEncodingException;


/**
 * Oauth 测试
 * @author cxx
 */
public class Test {

	/**
	 * 注意，测试时先·更改hosts
	 * TokenUtil.randomState() OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
	 * 回调时比较 state 参数是否一致
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		//********************qq test start********************//
		System.out.println(TokenUtil.randomState());
		OauthQQ qq = OauthQQ.me();
		System.out.println("QQ："+ qq.getAuthorizeUrl(TokenUtil.randomState()));
		//********************test end********************//

		//********************baidu test start********************//
		System.out.println(TokenUtil.randomState());
		OauthBaidu baidu = OauthBaidu.me();
		System.out.println("百度："+ baidu.getAuthorizeUrl(TokenUtil.randomState()));
		//********************test end********************//


		//********************sina test start********************//
		System.out.println(TokenUtil.randomState());
		OauthSina sina = OauthSina.me();
		System.out.println("新浪微博："+ sina.getAuthorizeUrl(TokenUtil.randomState()));
		//********************test end********************//

		//********************Douban test start********************//
		System.out.println(TokenUtil.randomState());
		OauthDouban db = OauthDouban.me();
		System.out.println("豆瓣："+ db.getAuthorizeUrl(TokenUtil.randomState()));
		//********************test end********************//

		//********************开源中国osc test start********************//
		System.out.println(TokenUtil.randomState());
		OauthOsc osc = OauthOsc.me();
        System.out.println("开源中国osc：" +osc.getAuthorizeUrl(TokenUtil.randomState()));
		//********************test end********************//

		//********************人人网 test start********************//
		System.out.println(TokenUtil.randomState());
		OauthRenren o = OauthRenren.me();
		System.out.println("人人网："+o.getAuthorizeUrl(TokenUtil.randomState(),"PC"));
		//********************test end********************//

		//********************Github test start********************//
		System.out.println(TokenUtil.randomState());
		OauthGithub g = OauthGithub.me();
		System.out.println("Github："+g.getAuthorizeUrl(TokenUtil.randomState()));
		//********************test end********************//

		//********************Weixin test start********************//
		System.out.println(TokenUtil.randomState());
		OauthWeixin ow=OauthWeixin.me();
		System.out.println("微信："+ow.getAuthorizeUrl(TokenUtil.randomState()));
		//********************test end********************//
	}
}
