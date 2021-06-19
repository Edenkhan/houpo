package com.youruan.dentistry.core.enroll;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx.pay")
@Getter
@Setter
public class WxPayProperties {
    private String appId;
    private String appSecret;
    /**
     * 商户号
     */
    private String mchid;
    /**
     * 商户密钥
     */
    private String privateKey;
    /**
     * 微信支付成功回调地址
     */
    private String notifyUrl;

}
