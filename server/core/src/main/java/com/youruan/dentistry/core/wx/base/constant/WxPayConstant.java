package com.youruan.dentistry.core.wx.base.constant;

/**
 * 微信支付相关url
 */
public class WxPayConstant {

    /**
     * 微信JSAPI下单URL
     */
    public static final String PLACE_ORDER_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";

    /**
     * 微信支付回调地址
     */
    public static final String NOTIFY_URI = "/api/enroll/notify";
}
