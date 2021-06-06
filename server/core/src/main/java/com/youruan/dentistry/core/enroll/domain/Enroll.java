package com.youruan.dentistry.core.enroll.domain;

import com.youruan.dentistry.core.base.domain.BasicDomain;
import lombok.Getter;
import lombok.Setter;

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
     * 类型
     */
    private Integer type;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 电话
     */
    private String phoneNumber;
    /**
     * 订单状态
     */
    private Boolean orderStatus;


}
