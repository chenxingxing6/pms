**SSM对第三方登陆(QQ/微信/Github/微博/开源中国/人人网/豆瓣/百度)进行整合**
 1. QQ : [QQ开发者平台](https://connect.qq.com/) 
 2. 微信： [微信开发者平台](https://open.weixin.qq.com/)  
 3. 豆瓣：[豆瓣开发平台](https://open.weixin.qq.com/) 
 4. Github：[Github开发平台教程](http://open.weibo.com/wiki/index.php)
 5. 人人网：[人人网开发平台](http://dev.renren.com/) 
 6.  新浪: [新浪开放平台](http://open.weibo.com/wiki/index.php) 
 7. 开源中国：[开源中国开发平台](https://my.oschina.net/)


oauth.properties中含有我的网站的**appkey**可用来测试
```
## qq openid
openid_qq = 100413274
openkey_qq = 0738a22b862ead14bb7976272b0a1eec
redirect_qq = http://www.dreamlu.net/api/qq/callback

## sina openid
openid_sina = 4193705357
openkey_sina = bc7bbdfe92be06b42b38206f8bca3645
redirect_sina = http://www.dreamlu.net/api/sina/callback

## baidu openid
openid_baidu = xwKOgtKjbbrn9dOb7ZkGrAo5
openkey_baidu = dNlKN4vVqgZvROrWW8twc4wESGWkSfF8
redirect_baidu = http://www.dreamlu.net/api/baidu/callback

## renren openid
openid_renren = 80cd6ab8bc924c97b78e06568196456e
openkey_renren = 51aab0e2633f43a2aeda89f299a7b4f8
redirect_renren = http://www.dreamlu.net/api/renren/callback

## osc openid
openid_osc = R6XS1Qnhist6jy5UABer
openkey_osc = llvILNi5ThQj2YwgbM6qx7BOEKIfJjjM
redirect_osc = http://www.dreamlu.net/api/osc/callback

## douban openid
openid_douban = 04e962ea4e22c5980ebc28beea6850c8
openkey_douban = d7ae6ac47ddf75e2
redirect_douban = http://www.dreamlu.net/api/douban/callback

## github openid `no tested`
openid_github = 32413513513
openkey_github = rew534563456rfh
redirect_github = http://www.dreamlu.net/api/github/callback

## weixin openid `no tested`
openid_weixin = wxacab1bf00bbcff11
openkey_weixin = bd2b23927f725a6b9a1e28165c787e80
redirect_weixin = http://www.dreamlu.net/api/weixin/callback
```
测试时先更改hosts（具体查看[这篇博客](http://blog.csdn.net/m0_37499059/article/details/79417395)）
```
127.0.0.1 www.dreamlu.net
```
**测试过程为**
 - 调用getAuthorizeUrl()获取url,在浏览器访问url，拿到回调的code参数
 - 调用getUserInfoByCode()传入刚获取的code！
![这里写图片描述](http://img.blog.csdn.net/20180301215424950)

**核心代码**
```
/**
 * 构造授权请求url
 * @return void    返回类型
 * @throws
 */
@RequestMapping("/login")
public String index(HttpServletRequest request){
    //state就是一个随机数，保证安全
    String state = TokenUtil.randomState();
    try {
        String url = OauthQQ.me().getAuthorizeUrl(state);
        return "redirect:"+url;
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    return "redirect:/index.jsp";
}
```

回调api：
```
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
        JSONObject userInfo = OauthQQ.me().getUserInfoByCode(code);
        log.error(userInfo.toString());
        String type = "qq";
        String openid = userInfo.getString("openid");
        String nickname = userInfo.getString("nickname");
        String photoUrl = userInfo.getString("figureurl_2");
        // 将相关信息存储数据库...
        return userInfo.toString();
    }catch(Exception e){
        e.printStackTrace();
    }
    //这里你们可以自己修改，授权成功后，调到首页
    return "error";
}
```

**我这里演示一下QQ第三方登录：**
![这里写图片描述](http://img.blog.csdn.net/20180301215544658)
![这里写图片描述](http://img.blog.csdn.net/20180301215833294)
![这里写图片描述](http://img.blog.csdn.net/20180301220627681)
![这里写图片描述](http://img.blog.csdn.net/20180301220737805)
_____________
**遇到问题可以Q我：1527072012**
**博客教程**：[点击](http://blog.csdn.net/m0_37499059/article/details/79417395)
