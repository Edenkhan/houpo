package com.youruan.dentistry.core.wx.oauth.service.impl;

import com.alibaba.fastjson.JSON;
import com.youruan.dentistry.core.wx.base.constant.WxConstant;
import com.youruan.dentistry.core.wx.base.utils.HttpClientUtils;
import com.youruan.dentistry.core.wx.oauth.domain.WxResult;
import com.youruan.dentistry.core.wx.oauth.service.OAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class BasicOAuthService implements OAuthService {

    @Value("${wx.domain}")
    private String domain;

    @Override
    public void auth() {
        try {
            String encodeUrl = URLEncoder.encode(domain + WxConstant.REDIRECT_URI, "UTF-8");
            String oauthUrl = String.format(WxConstant.BASIC_OAUTH_URL,encodeUrl);
            System.out.println("oauthUrl = " + oauthUrl);
            HttpClientUtils.doGet(oauthUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void callback(String code, String state) {
        if(!state.equals("GHOST")) {
            throw new RuntimeException("回调错误");
        }
        String getAuthAccessTokenUrl = String.format(WxConstant.BASIC_OAUTH_ACCESS_TOKEN_URL,code);
        String json = HttpClientUtils.doGet(getAuthAccessTokenUrl);
        WxResult wxResult = JSON.parseObject(json, WxResult.class);
        System.out.println(wxResult);
    }
}
