package com.youruan.dentistry.core.wx.base.constant;

public interface WxConstant {

    String APP_ID = "wxfcffb0d00f2da17b";
    String APP_SECRET = "2fcf880c2503141e09bb0a55623f82f1";

    /**
     * 获取access_token的url
     */
    String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret="+APP_SECRET;
    /**
     * 创建公众号菜单url
     */
    String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    /**
     * 获取微信用户授权url
     */
    String BASIC_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID+"&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=GHOST#wechat_redirect";
    /**
     * 用户授权，微信重定向地址
     */
    String REDIRECT_URI = "/api/oauth/callback";
    /**
     * 获取用户授权时access_token的url
     */
    String BASIC_OAUTH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APP_ID+"&secret="+APP_SECRET+"&code=%s&grant_type=authorization_code";
    /**
     * 获取微信用户信息的url
     */
    String BASIC_GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
}
