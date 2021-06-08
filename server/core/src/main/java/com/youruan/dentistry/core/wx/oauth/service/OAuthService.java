package com.youruan.dentistry.core.wx.oauth.service;

public interface OAuthService {
    /**
     * 用户授权登录
     */
    void auth();

    /**
     * 用户授权，回调地址
     */
    void callback(String code, String state);
}
