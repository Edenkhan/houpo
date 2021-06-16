package com.youruan.dentistry.core.enroll.domain.pay;

import lombok.Getter;
import lombok.Setter;

/**
 * 支付参数
 */
@Getter
@Setter
public class PayParam {

    /**
     * 应用ID
     */
    private String appId;
    /**
     * 时间戳
     */
    private String timeStamp;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 订单详情扩展字符串
     */
    private String packageValue;
    /**
     * 签名方式
     */
    private String signType;
    /**
     * 签名
     */
    private String paySign;

}
