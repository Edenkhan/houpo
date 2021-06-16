package com.youruan.dentistry.core.enroll.domain.pay;

import lombok.Getter;
import lombok.Setter;

/**
 * 下单参数
 */
@Setter
@Getter
public class PlaceOrderParam {

    /**
     * 应用ID
     */
    private String appid;
    /**
     * 直连商户号
     */
    private String mchid;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 通知地址
     */
    private String notify_url;
    /**
     * 订单金额
     */
    private Amount amount;
    /**
     * 支付者
     */
    private Payer payer;

}
