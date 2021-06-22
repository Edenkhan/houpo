package com.youruan.dentistry.core.enroll.domain;

import com.youruan.dentistry.core.base.domain.BasicDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 报表
 */
@Getter
@Setter
public class Enroll extends BasicDomain {

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 预支付id
     */
    private String prepayId;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 类型【职场百分百】
     */
    public static final int TYPE_WORKPLACE = 0;
    /**
     * 类型【就业直通车】
     */
    public static final int TYPE_EMPLOYMENT = TYPE_WORKPLACE + 1;
    /**
     * 类型【普通活动】
     */
    public static final int TYPE_GENERAL = TYPE_EMPLOYMENT + 1;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 订单状态[未支付]
     */
    public static final int ORDER_STATUS_NOT = 0;
    /**
     * 订单状态[已支付]
     */
    public static final int ORDER_STATUS_OK = (ORDER_STATUS_NOT + 1);
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 活动id
     */
    private Long activityId;

}
