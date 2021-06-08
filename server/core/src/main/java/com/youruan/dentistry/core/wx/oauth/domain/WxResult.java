package com.youruan.dentistry.core.wx.oauth.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信返回数据
 */
@Setter
@Getter
public class WxResult {

    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

}
