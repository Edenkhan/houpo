package com.youruan.dentistry.core.wx.access.service;

public interface AccessService {
    /**
     * 对接微信公众号
     */
    String access(String signature, String timestamp, String nonce, String echostr);
}
