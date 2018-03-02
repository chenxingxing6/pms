package com.peace.pms.oauth;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;
import com.peace.pms.util.OathConfig;
import com.peace.pms.util.TokenUtil;
import org.apache.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 新浪微博
 * @Author: cxx
 * @Date: 2018/3/1 19:10
 */
public class OauthSina extends Oauth {
    private static Logger log = LoggerFactory.getLogger(OauthSina.class);
    private static final String AUTH_URL = "https://api.weibo.com/oauth2/authorize";
    private static final String TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
    private static final String TOKEN_INFO_URL = "https://api.weibo.com/oauth2/get_token_info";
    private static final String USER_INFO_URL = "https://api.weibo.com/2/users/show.json";
    private static OauthSina oauthSina = new OauthSina();

    /**
     * 用于链式操作
     * @return
     */
    public static OauthSina me() {
        return oauthSina;
    }

    public OauthSina() {
        setClientId(OathConfig.getValue("openid_sina"));
        setClientSecret(OathConfig.getValue("openkey_sina"));
        setRedirectUri(OathConfig.getValue("redirect_sina"));
    }

    /**
     * @throws UnsupportedEncodingException
     * 获取授权url
     * DOC：http://open.weibo.com/wiki/Oauth2/authorize
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String getAuthorizeUrl(String state) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("response_type", "code");
        params.put("client_id", getClientId());
        params.put("redirect_uri", getRedirectUri());
        if (StringUtils.isNotBlank(state)) {
            params.put("state", state); //OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
        }
        return super.getAuthorizeUrl(AUTH_URL, params);
    }

    /**
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * 获取token
     * @param @param code
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String getTokenByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException{
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", code);
        params.put("client_id", getClientId());
        params.put("client_secret", getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", getRedirectUri());
        String token = TokenUtil.getAccessToken(super.doPost(TOKEN_URL, params));
        log.debug(token);
        return token;
    }

    /**
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * 获取TokenInfo
     * @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String getTokenInfo(String accessToken) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException{
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        String openid = TokenUtil.getUid(super.doPost(TOKEN_INFO_URL, params));
        log.debug(openid);
        return openid;
    }

    /**
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * 获取用户信息
     * DOC：http://open.weibo.com/wiki/2/users/show
     * @param accessToken
     * @param uid
     * @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String getUserInfo(String accessToken, String uid) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException{
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("access_token", accessToken);
        String userInfo = super.doGet(USER_INFO_URL, params);
        log.debug(userInfo);
        return userInfo;
    }

    /**
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * 根据code一步获取用户信息
     * @param @param args    设定文件
     * @return void    返回类型
     * @throws
     */
    public JSONObject getUserInfoByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException{
        String accessToken = getTokenByCode(code);
        if (StringUtils.isBlank(accessToken)) {
            return null;
        }
        String uid = getTokenInfo(accessToken);
        if (StringUtils.isBlank(uid)) {
            return null;
        }
        JSONObject dataMap = JSON.parseObject(getUserInfo(accessToken, uid));
        dataMap.put("access_token", accessToken);
        log.debug(dataMap.toString());
        return dataMap;
    }
}

