package com.youruan.dentistry.core.wx.oauth.service;

import com.youruan.dentistry.core.wx.oauth.domain.UserLogin;
import com.youruan.dentistry.core.wx.oauth.domain.WxUserInfo;

public interface OAuthService {

    /**
     * 用户授权，回调地址
     */
    WxUserInfo callback(String code, String state);

    /**
     * 微信用户注册
     * 返回用户id
     */
    UserLogin register(WxUserInfo wxUserInfo);
}
